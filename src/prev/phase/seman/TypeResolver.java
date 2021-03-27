package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.AstFullVisitor;
import prev.data.typ.*;

import java.util.LinkedList;

/**
 * Type resolver.
 * <p>
 * Type resolver computes the values of {@link SemAn#declaresType},
 * {@link SemAn#isType}, and {@link SemAn#ofType}.
 */
public class TypeResolver extends AstFullVisitor<SemType, TypeResolver.Mode> {

    public boolean everyType(SemType type) {
        if(type == null) return false;
        else if(type.actualType() instanceof SemVoid) return true;
        else if (everyTypeExceptVoid(type)) return true;
        return false;
    }

    public boolean everyTypeExceptVoid(SemType type) {
        if(type == null) return false;
        else if (type.actualType() instanceof SemBool) return true;
        else if (type.actualType() instanceof SemChar) return true;
        else if (type.actualType() instanceof SemInt) return true;
        else if (type.actualType() instanceof SemPtr && everyType(((SemPtr) type).baseType)) return true;
        else if (type.actualType() instanceof SemArr && everyType(((SemArr) type).elemType)) return true;
        else if (type.actualType() instanceof SemRec) {
            boolean t = true;
            for (int i = 0; i < ((SemRec) type).numComps(); i++) {
                if (!everyType(((SemRec) type).compType(i))) t = false;
            }
            return t;
        }
        return false;
    }

    public boolean areTwoTypesSame(SemType type1, SemType type2) {
        if(type1 == null || type2 == null) { return false;}
        else if (type1.actualType() instanceof SemName && type2.actualType() instanceof SemName) return true;
        else if (type1.actualType() instanceof SemVoid && type2.actualType() instanceof SemVoid) return true;
        else if (type1.actualType() instanceof SemBool && type2.actualType() instanceof SemBool) return true;
        else if (type1.actualType() instanceof SemChar && type2.actualType() instanceof SemChar) return true;
        else if (type1.actualType() instanceof SemInt && type2.actualType() instanceof SemInt) return true;
        else if (type1.actualType() instanceof SemPtr && type2.actualType() instanceof SemPtr && areTwoTypesSame(((SemPtr) type1).baseType, ((SemPtr) type2).baseType))
            return true;
        else if (type1.actualType() instanceof SemArr && type2.actualType() instanceof SemArr && areTwoTypesSame(((SemArr) type1).elemType, ((SemArr) type2).elemType))
            return true;
        else if (type1.actualType() instanceof SemRec && type2.actualType() instanceof SemRec && ((SemRec) type1).numComps() == ((SemRec) type2).numComps()) {
            boolean t = true;

            for (int i = 0; i < ((SemRec) type1).numComps(); i++) {
                if (!areTwoTypesSame(((SemRec) type1).compType(i), ((SemRec) type2).compType(i))) t = false;
            }
            return t;
        }
        return false;
    }

    // T1
    @Override
    public SemType visit(AstAtomType atomType, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(atomType, mode);

        SemType returnType = null;
        switch (atomType.type) {
            case VOID:
                returnType = new SemVoid();
                break;
            case CHAR:
                returnType = new SemChar();
                break;
            case INT:
                returnType = new SemInt();
                break;
            case BOOL:
                returnType = new SemBool();
                break;
            default:
                throw new Report.Error(atomType, "Type error: not of type VOID/CHAR/INT/BOOL.");
        }

        SemAn.isType.put(atomType, returnType);
        return returnType;
    }

    // T2
    @Override
    public SemType visit(AstArrType arrType, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(arrType, mode);
        SemType exprType = SemAn.isType.get(arrType.elemType);

        SemType returnType = null;
        if (arrType.numElems instanceof AstAtomExpr && ((AstAtomExpr) arrType.numElems).type == AstAtomExpr.Type.INT) {
            long value;
            try {
                value = Long.parseLong(((AstAtomExpr) arrType.numElems).value);
            } catch (NumberFormatException e) {
                throw new Report.Error(arrType, "Type error: index value too big.");
            }
            if (value <= 0) {
                throw new Report.Error(arrType, "Type error: index value can't be non negative .");
            }

            if (everyTypeExceptVoid(exprType)) {
                returnType = new SemArr(exprType, value);
            } else {
                throw new Report.Error(arrType, "Type error: wrong array type");
            }
        } else {
           throw new Report.Error(arrType, "Type error: only non negative constant values can be used for declaring array");
        }

        //Report.info(arrType, returnType.toString());
        SemAn.isType.put(arrType, returnType);
        return returnType;
    }

    //T3
    @Override
    public SemType visit(AstRecType recType, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(recType, mode);

        LinkedList<SemType> recs = new LinkedList<>();
        for (AstCompDecl acd : recType.comps) {
            SemType compType = SemAn.isType.get(acd.type);
            recs.add(compType);
        }

        SemType returnType = new SemRec(recs);
        SemAn.isType.put(recType, returnType);

        return returnType;
    }

    // T4
    @Override
    public SemType visit(AstPtrType ptrType, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(ptrType, mode);

        SemType type = SemAn.isType.get(ptrType.baseType);

        if(type == null) {
            throw new Report.Error(ptrType, "Type error: undeclared type in AstPtrType.");
        }

        SemType returnType = null;

        if (everyType(type)) {
            returnType = new SemPtr(type);
        } else {
            throw new Report.Error(ptrType, "Type error: pointer type.");
        }

        SemAn.isType.put(ptrType, returnType);
        return returnType;
    }

    // prefix operations
    // v3
    // v8 prvi del
    // v9
    @Override
    public SemType visit(AstPfxExpr pfxExpr, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(pfxExpr, mode);
        AstExpr expr = pfxExpr.expr;
        SemType exprType = SemAn.ofType.get(expr);

        SemType returnType;
        switch (pfxExpr.oper) {
            case NOT:
                if (exprType.actualType() instanceof SemBool)
                    returnType = new SemBool();
                else
                    throw new Report.Error(pfxExpr, "Type error: negation operator ! can only be used on bool");
                break;
            case PTR:
                    returnType = new SemPtr(exprType);
                break;
            case ADD:
            case SUB:
                if (exprType.actualType() instanceof SemInt)
                    returnType = new SemInt();
                else
                    throw new Report.Error(pfxExpr, "Type error: add/sub operator +/- can only be used on integers");
                break;
            case NEW:
                if (exprType.actualType() instanceof SemInt)
                    returnType = new SemPtr(new SemVoid());
                else
                    throw new Report.Error(pfxExpr, "Type error: new operator can only be used with integers");
                break;
            case DEL:
                if (exprType.actualType() instanceof SemPtr)
                    returnType = new SemVoid();
                else
                    throw new Report.Error(pfxExpr, "Type error: del operator can only be used with pointers");
            default:
                throw new Report.Error(pfxExpr, "Unexpected prefix operator " + pfxExpr.oper);
        }
        SemAn.ofType.put(pfxExpr, returnType);
        return returnType;
    }


    // v1, v2
    @Override
    public SemType visit(AstAtomExpr atomExpr, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(atomExpr, mode);

        SemType returnType;
        switch (atomExpr.type) {
            case VOID:
                returnType = new SemVoid();
                break;
            case CHAR:
                returnType = new SemChar();
                break;
            case INT:
                returnType = new SemInt();
                break;
            case BOOL:
                returnType = new SemBool();
                break;
            case POINTER:
                returnType = new SemPtr(new SemVoid());
                break;
            case STRING:
                returnType = new SemPtr(new SemChar());
                break;
            default:
                throw new Report.Error(atomExpr, "Unexpected atomic type" + atomExpr.type);
        }

        SemAn.ofType.put(atomExpr, returnType);
        return returnType;
    }

    public String getBinaryOperator(AstBinExpr binExpr) {
        switch (binExpr.oper) {
            case OR:
                return "OR";
            case AND:
                return "AND";
            case ADD:
                return "ADD";
            case SUB:
                return "SUB";
            case DIV:
                return "DIV";
            case MOD:
                return "MOD";
            case MUL:
                return "MUL";
            default:
                return "";
        }
    }

    // v4, v5, v6, v7
    @Override
    public SemType visit(AstBinExpr binExpr, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(binExpr, mode);
        SemType exprType1 = SemAn.ofType.get(binExpr.fstExpr);
        SemType exprType2 = SemAn.ofType.get(binExpr.sndExpr);

        SemType returnType;
        switch (binExpr.oper) {
            // v4
            case OR:
            case AND:
                if (exprType1.actualType() instanceof SemBool && exprType2.actualType() instanceof SemBool)
                    returnType = new SemBool();
                else if (exprType1.actualType() instanceof SemBool && !(exprType2.actualType() instanceof SemBool))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!(exprType1.actualType() instanceof SemBool) && exprType2.actualType() instanceof SemBool)
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. First expression is NOT of type bool");
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Neither of them are of type bool");
                break;
            // v5
            case ADD:
            case SUB:
            case MUL:
            case MOD:
            case DIV:
                if (exprType1.actualType() instanceof SemInt && exprType2.actualType() instanceof SemInt)
                    returnType = new SemInt();
                else if (exprType1.actualType() instanceof SemInt && !(exprType2.actualType()  instanceof SemInt))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!(exprType1.actualType() instanceof SemInt) && exprType2.actualType() instanceof SemInt)
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. First expression is NOT of type bool");
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Neither of them are of type bool");
                break;
            // v6
            case EQU:
            case NEQ:
                boolean good = false;
                if(exprType1 == null) {}
                else if(exprType1.actualType() instanceof SemBool) good = true;
                else if(exprType1.actualType() instanceof SemChar) good = true;
                else if(exprType1.actualType() instanceof SemInt) good = true;
                else if(exprType1.actualType() instanceof SemPtr && everyType(((SemPtr) exprType1).baseType)) good = true;

                if(!good)
                    throw new Report.Error(binExpr, "Type error: type for ==/!= comparison is wrong.");

                if(areTwoTypesSame(exprType1, exprType2))
                    returnType = new SemBool();
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool.");
                break;
            // v7
            case LEQ:
            case LTH:
            case GEQ:
            case GTH:
                good = false;
                if(exprType1 == null) {}
                else if(exprType1.actualType() instanceof SemChar) good = true;
                else if(exprType1.actualType() instanceof SemInt) good = true;
                else if(exprType1.actualType() instanceof SemPtr && everyType(((SemPtr) exprType1).baseType)) good = true;

                if(!good)
                    throw new Report.Error(binExpr, "Type error: type for <=/>=/</> comparison is wrong.");

                if(areTwoTypesSame(exprType1, exprType2))
                    returnType = new SemBool();
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool.");
                break;

            default:
                throw new Report.Error(binExpr, "Unexpected binary operand");
        }

        SemAn.ofType.put(binExpr, returnType);
        return returnType;
    }

    // suffix
    // v8 drugi del
    @Override
    public SemType visit(AstSfxExpr sfxExpr, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(sfxExpr, mode);
        SemType exprType = SemAn.ofType.get(sfxExpr.expr);

        SemType returnType;
        switch (sfxExpr.oper) {
            case PTR:
                if (exprType.actualType() instanceof SemPtr)
                    returnType = ((SemPtr) exprType).baseType;
                else
                    throw new Report.Error(sfxExpr, "Type error: expression not of pointer type.");
                break;
            default:
                throw new Report.Error(sfxExpr, "Unexpected suffix operand");
        }

        SemAn.ofType.put(sfxExpr, returnType);
        return returnType;
    }

    @Override
    public SemType visit(AstCompDecl compDecl, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(compDecl, mode);

        SemType type = SemAn.isType.get(compDecl.type);

        if(type == null) {
            Report.warning("AstCompDecl [" + compDecl.location().toString() + "] is null.");
        }

        if(!everyTypeExceptVoid(type)) {
            throw new Report.Error(compDecl, "Type error: component of type VOID.");
        }
        return null;
    }



    // v10
    @Override
    public SemType visit(AstArrExpr arrExpr, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(arrExpr, mode);
        SemType exprType = SemAn.ofType.get(arrExpr.arr);
        SemType exprIdx = SemAn.ofType.get(arrExpr.idx);

        SemType returnType;
        if (exprType.actualType() instanceof SemArr && exprIdx.actualType() instanceof SemInt)
            returnType = ((SemArr) exprType).elemType;
        else if (!(exprType.actualType() instanceof SemArr) && exprIdx.actualType() instanceof SemInt)
            throw new Report.Error(arrExpr, "Type error: expression not an array type");
        else if (exprType.actualType() instanceof SemArr && !(exprIdx.actualType() instanceof SemInt))
            throw new Report.Error(arrExpr, "Type error: index of an array is not an integer");
        else
            throw new Report.Error(arrExpr, "Unexpected array expression");

        SemAn.ofType.put(arrExpr, returnType);
        return returnType;
    }

    //v11
    @Override
    public SemType visit(AstRecExpr recExpr, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(recExpr, mode);
        SemType exprType = SemAn.ofType.get(recExpr.rec);

        return null;
    }

    //v 12
    @Override
    public SemType visit(AstCallExpr callExpr, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(callExpr, mode);
        AstDecl decl = SemAn.declaredAt.get(callExpr);

        if (decl instanceof AstFunDecl) {
            AstFunDecl funDecl = (AstFunDecl) decl;

            if (funDecl.pars.size() != callExpr.args.size())
                throw new Report.Error(callExpr, "Type error: function argument count difference");

            for (int i = 0; i < funDecl.pars.size(); i++) {
                SemType dclType = SemAn.isType.get(funDecl.pars.get(i).type);

                if(dclType == null) {
                    Report.warning("SemType in function call [" + callExpr.name + "] is null.");
                }

                SemType actType = SemAn.ofType.get(callExpr.args.get(i));

                if (!(areTwoTypesSame(dclType, actType))) {
                    throw new Report.Error(callExpr, "Type error: function argument type difference");
                }
            }

            SemType callType = SemAn.isType.get(funDecl.type);

            if(callType == null) {
                Report.warning("Return type of function call [" + callExpr.name + "] is null.");
            }

            SemAn.ofType.put(callExpr, callType);

            return callType;
        } else {
            throw new Report.Error(callExpr, "Type error: undeclared function call.");
        }
    }

    // v13
    @Override
    public SemType visit(AstStmtExpr stmtExpr, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(stmtExpr, mode);

        int lastIndex = stmtExpr.stmts.size() - 1;
        AstStmt expr =  stmtExpr.stmts.get(lastIndex);

        SemType type = SemAn.ofType.get(stmtExpr.stmts.get(lastIndex));
        SemAn.ofType.put(stmtExpr, type);
        return type;
    }

    @Override
    public SemType visit(AstTypeDecl typeDecl, Mode mode) {
       if(mode == Mode.FIRST) {
           SemName name = new SemName(typeDecl.name);
           SemAn.declaresType.put(typeDecl, name);
           return name;
       }

       super.visit(typeDecl, mode);

       if(mode == Mode.SECOND) {
           SemName name = SemAn.declaresType.get(typeDecl);
           SemType type = SemAn.isType.get(typeDecl.type);

           if(type == null)
               Report.warning("SemType in [" + typeDecl.name + "] is null.");

           name.define(type);
       }

       return null;
    }

    // V14
    @Override
    public SemType visit(AstCastExpr castExpr, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(castExpr, mode);

        SemType expr = SemAn.ofType.get(castExpr.expr);
        boolean good = false;
        if(expr == null) {}
        else if(expr.actualType() instanceof SemChar) good = true;
        else if(expr.actualType() instanceof SemInt) good = true;
        else if(expr.actualType() instanceof SemPtr && everyType(((SemPtr) expr).baseType)) good = true;

        if(!good)
            throw new Report.Error(castExpr, "Type error: typecast expression of invalid type.");

        SemType type = SemAn.isType.get(castExpr.type);

        if(type == null)
            Report.warning("SemType in expression [" + castExpr.location().toString() + "] is null.");

        good = false;
        if(type == null) {}
        else if(type.actualType() instanceof SemChar) good = true;
        else if(type.actualType() instanceof SemInt) good = true;
        else if(type.actualType() instanceof SemPtr && everyType(((SemPtr) type).baseType)) good = true;

        if(!good)
            throw new Report.Error(castExpr, "Type error: typecast type invalid.");

        SemType returnType = type;
        SemAn.ofType.put(castExpr, type);

        return type;
    }

    // V15 drugi del
    @Override
    public SemType visit(AstWhereExpr whereExpr, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(whereExpr, mode);

        SemType type = SemAn.ofType.get(whereExpr.expr);

        return type;
    }

    // d4
    @Override
    public SemType visit(AstFunDecl funDecl, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(funDecl, mode);

        SemType retType = SemAn.isType.get(funDecl.type);

        if(retType == null)
            Report.warning("Return type of function declaration [" + funDecl.name + "] is null.");

        boolean good = false;
        if(retType == null) {}
        else if(retType.actualType() instanceof SemVoid) good = true;
        else if(retType.actualType() instanceof SemBool) good = true;
        else if(retType.actualType() instanceof SemChar) good = true;
        else if(retType.actualType() instanceof SemInt) good = true;
        else if(retType.actualType() instanceof SemPtr && everyType(((SemPtr) retType).baseType)) good = true;

        if(!good)
            throw new Report.Error(funDecl, "Type error: function " + funDecl.name + " with wrong return type.");

        if(funDecl.expr != null) {
            SemType expType = SemAn.ofType.get(funDecl.expr);

            if(!areTwoTypesSame(retType, expType))
                throw new Report.Error(funDecl, "Type error: function " + funDecl.name + " return type doesn't match with expression.");
        }

        return null;
    }

    @Override
    public SemType visit(AstParDecl parDecl, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(parDecl, mode);

        SemType type = SemAn.isType.get(parDecl.type);

        if(type == null)
            Report.warning("SemType of parameter [" + parDecl.location().toString() + "] is null.");

        boolean good = false;
        if(type == null) {}
        else if(type.actualType() instanceof SemBool) good = true;
        else if(type.actualType() instanceof SemChar) good = true;
        else if(type.actualType() instanceof SemInt) good = true;
        else if(type.actualType() instanceof SemPtr && everyType(((SemPtr) type).baseType)) good = true;

        if(!good) {
           throw new Report.Error(parDecl, "Type error: parameter " + parDecl.name + " of wrong type");
        }

        return null;
    }


    @Override
    public SemType visit(AstExprStmt exprStmt, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(exprStmt, mode);

        SemType type = SemAn.ofType.get(exprStmt.expr);
        SemAn.ofType.put(exprStmt, type);

        return null;
    }

    // s1
    @Override
    public SemType visit(AstAssignStmt assignStmt, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(assignStmt, mode);
        SemType exprType1 = SemAn.ofType.get(assignStmt.dst);
        SemType exprType2 = SemAn.ofType.get(assignStmt.src);


        boolean good = false;

        if(exprType1 == null) {}
        else if(exprType1.actualType() instanceof SemBool) good = true;
        else if(exprType1.actualType() instanceof SemChar) good = true;
        else if(exprType1.actualType() instanceof SemInt) good = true;
        else if(exprType1.actualType() instanceof SemPtr && everyType(((SemPtr) exprType1).baseType)) good = true;

        //Report.info(exprType1.getClass().getSimpleName());

        if(!good)
            throw new Report.Error(assignStmt, "Type error: assignment on location [ " + assignStmt.location() + " ] uses wrong expression types.");

        if(!areTwoTypesSame(exprType1, exprType2))
            throw new Report.Error(assignStmt, "Type error: assignment on location [ " + assignStmt.location() + " ] with two different type of expressions.");

        SemType returnType = new SemVoid();
        SemAn.ofType.put(assignStmt, returnType);
        return returnType;
    }

    // s2
    @Override
    public SemType visit(AstIfStmt ifStmt, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(ifStmt, mode);
        SemType exprType1 = SemAn.ofType.get(ifStmt.cond);

        SemType returnType;
        if (exprType1.actualType() instanceof SemBool)
            returnType = new SemVoid();
        else
            throw new Report.Error(ifStmt, "Statement error: condition in if statement not of type bool");

        SemAn.ofType.put(ifStmt, returnType);
        return returnType;
    }

    // s3
    @Override
    public SemType visit(AstWhileStmt whileStmt, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(whileStmt, mode);
        SemType exprType1 = SemAn.ofType.get(whileStmt.cond);

        SemType returnType;
        if (exprType1.actualType() instanceof SemBool)
            returnType = new SemVoid();
        else
            throw new Report.Error(whileStmt, "Statement error: condition in while loop not of type bool");

        SemAn.ofType.put(whileStmt, returnType);
        return returnType;
    }

    // d1
    @Override
    public SemType visit(AstNameType nameType, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(nameType, mode);

        AstDecl nameDecl = SemAn.declaredAt.get(nameType);

        if (nameDecl instanceof AstTypeDecl) {
            SemName name = SemAn.declaresType.get((AstTypeDecl) nameDecl);

            if(name == null)
                Report.warning("SemName in [" + nameType.location() + "] is null.");

            SemAn.isType.put(nameType, name);
            return name;

        } else {
            throw new Report.Error(nameType, "Type error: undeclared type in AstNameType.");
        }
    }

    // D2
    @Override
    public SemType visit(AstVarDecl varDecl, Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(varDecl, mode);

        SemType type = SemAn.isType.get(varDecl.type);

        if(type == null)
            Report.warning("SemType of variable declaration [" + varDecl.name + "] is null, location [" + varDecl.location() + "].");

        if(!everyTypeExceptVoid(type)) {
            throw new Report.Error(varDecl, "Type error: type can't be VOID.");
        }

        return null;
    }

    // d3 d4
    @Override
    public SemType visit(AstNameExpr nameExpr, TypeResolver.Mode mode) {
        if(mode == Mode.FIRST) return null;
        super.visit(nameExpr, mode);
        AstDecl nameDecl = SemAn.declaredAt.get(nameExpr);

        SemType type = null;
        if (nameDecl instanceof AstVarDecl) {
            type = SemAn.isType.get(((AstVarDecl) nameDecl).type);
            if(type == null)
               Report.warning("SemType of name expression [" + nameExpr.name + "] is null, location [" + nameExpr.location() + "].");
        }

        if( nameDecl instanceof AstParDecl) {
            type = SemAn.isType.get(((AstParDecl) nameDecl).type);

            if(type == null)
                Report.warning("SemType of name expression [" + nameExpr.name + "] , location [" + nameExpr.location() + "].");
        }

        SemAn.ofType.put(nameExpr, type);
        return type;
    }

    public enum Mode {
        FIRST, SECOND
    }

}
