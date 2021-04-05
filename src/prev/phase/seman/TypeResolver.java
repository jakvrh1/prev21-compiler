package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.AstFullVisitor;
import prev.data.typ.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Type resolver.
 * <p>
 * Type resolver computes the values of {@link SemAn#declaresType},
 * {@link SemAn#isType}, and {@link SemAn#ofType}.
 */

public class TypeResolver extends AstFullVisitor<SemType, TypeResolver.Mode> {

    private HashMap<SemRec, SymbTable> declaresComp = new HashMap<>();

    public boolean isIntCharPtr(SemType type) {
        if(type == null) return false;
        else if (type.actualType() instanceof SemChar) return true;
        else if (type.actualType() instanceof SemInt) return true;
        else if (type.actualType() instanceof SemPtr) return true;
        return false;
    }

    public boolean notVoid(SemType type) {
        if (type == null) return false;
        else if (type.actualType() instanceof SemVoid) return false;
        return true;
    }

    public boolean areTwoTypesSame(SemType type1, SemType type2) {
        if (type1 == null || type2 == null) return false;
        else if (type1 == type2) return true;
        else if (type1.actualType() instanceof SemVoid && type2.actualType() instanceof SemVoid) return true;
        else if (type1.actualType() instanceof SemBool && type2.actualType() instanceof SemBool) return true;
        else if (type1.actualType() instanceof SemChar && type2.actualType() instanceof SemChar) return true;
        else if (type1.actualType() instanceof SemInt && type2.actualType() instanceof SemInt) return true;
        else if (type1.actualType() instanceof SemPtr && type2.actualType() instanceof SemPtr && areTwoTypesSame(((SemPtr) type1.actualType()).baseType, ((SemPtr) type2.actualType()).baseType))
            return true;
        else if (type1.actualType() instanceof SemArr && type2.actualType() instanceof SemArr && areTwoTypesSame(((SemArr) type1.actualType()).elemType, ((SemArr) type2.actualType()).elemType))
            return true;
        else if (type1.actualType() instanceof SemRec && type2.actualType() instanceof SemRec && ((SemRec) type1.actualType()).numComps() == ((SemRec) type2.actualType()).numComps()) {
            boolean t = true;

            for (int i = 0; i < ((SemRec) type1.actualType()).numComps(); i++) {
                if (!areTwoTypesSame(((SemRec) type1.actualType()).compType(i), ((SemRec) type2.actualType()).compType(i)))
                    t = false;
            }
            return t;
        }
        return false;
    }

    public String getBinaryOperator(AstBinExpr binExpr) {
        return switch (binExpr.oper) {
            case OR -> "OR";
            case AND -> "AND";
            case ADD -> "ADD";
            case SUB -> "SUB";
            case DIV -> "DIV";
            case MOD -> "MOD";
            case MUL -> "MUL";
            default -> "";
        };
    }

    // T1
    @Override
    public SemType visit(AstAtomType atomType, TypeResolver.Mode mode) {
        super.visit(atomType, mode);
        if(mode != Mode.SECOND) return null;

        SemType returnType = switch (atomType.type) {
            case VOID -> new SemVoid();
            case CHAR -> new SemChar();
            case INT -> new SemInt();
            case BOOL -> new SemBool();
            //default -> throw new Report.Error(atomType, "Type error: not of type VOID/CHAR/INT/BOOL.");
        };

        SemAn.isType.put(atomType, returnType);
        return returnType;
    }


    // T2
    @Override
    public SemType visit(AstArrType arrType, TypeResolver.Mode mode) {
        super.visit(arrType, mode);

        if(mode == Mode.SECOND) {
            SemType exprType = SemAn.isType.get(arrType.elemType);

            if (arrType.numElems instanceof AstAtomExpr && ((AstAtomExpr) arrType.numElems).type == AstAtomExpr.Type.INT) {
                long value;
                try {
                    value = Long.parseLong(((AstAtomExpr) arrType.numElems).value);
                } catch (NumberFormatException e) {
                    throw new Report.Error(arrType, "Type error: index value too big.");
                }

                if (value <= 0)
                    throw new Report.Error(arrType, "Type error: index value can't be non negative .");

                SemType returnType = new SemArr(exprType, value);
                SemAn.isType.put(arrType, returnType);
            } else
                throw new Report.Error(arrType, "Type error: only non negative constant values can be used for declaring array");
        }

       if(mode == Mode.VALIDATION) {
            SemType type = SemAn.isType.get(arrType);
            SemType elemType = ((SemArr)type).elemType;
           if (!notVoid(elemType))
               throw new Report.Error(arrType, "Type error: array must be of non VOID type.");
       }

       return null;
    }

    //T3
    @Override
    public SemType visit(AstRecType recType, TypeResolver.Mode mode) {
        super.visit(recType, mode);

        if (mode == Mode.SECOND) {
            LinkedList<SemType> recs = new LinkedList<>();
            SymbTable st = new SymbTable();
            for (AstCompDecl acd : recType.comps) {
                SemType compType = SemAn.isType.get(acd.type);
                try {
                    st.ins(acd.name, acd);
                } catch (SymbTable.CannotInsNameException e) {
                    throw new Report.Error(recType, "Name error: " + acd.name + ".");
                }
                recs.add(compType);
            }

            SemRec returnType = new SemRec(recs);
            SemAn.isType.put(recType, returnType);
            declaresComp.put(returnType, st);
            return returnType;
        }

        if(mode == Mode.VALIDATION) {
            SemType type = SemAn.isType.get(recType);
            SemRec rec = ((SemRec)type);
            for(int i = 0; i < rec.numComps(); ++i) {
                SemType compType = rec.compType(i);
                if(!notVoid(compType))
                    throw new Report.Error(recType, "Type error: record component of VOID type.");
            }
        }

        return null;
    }


    // T4
    @Override
    public SemType visit(AstPtrType ptrType, TypeResolver.Mode mode) {
        super.visit(ptrType, mode);
        if (mode != Mode.SECOND) return null;

        SemType type = SemAn.isType.get(ptrType.baseType);

        if (type == null)
            throw new Report.Error(ptrType, "Type error: undeclared type in AstPtrType.");

        SemType returnType = null;
        returnType = new SemPtr(type);

        SemAn.isType.put(ptrType, returnType);
        return returnType;
    }

    // prefix operations
    // v3
    // v8 prvi del
    // v9
    @Override
    public SemType visit(AstPfxExpr pfxExpr, Mode mode) {
        super.visit(pfxExpr, mode);
        if (mode != Mode.THIRD) return null;

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
            case ADD, SUB:
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
        super.visit(atomExpr, mode);
        if (mode != Mode.THIRD) return null;

        SemType returnType = switch (atomExpr.type) {
            case VOID -> new SemVoid();
            case CHAR -> new SemChar();
            case INT -> new SemInt();
            case BOOL -> new SemBool();
            case POINTER -> new SemPtr(new SemVoid());
            case STRING -> new SemPtr(new SemChar());
            //default -> throw new Report.Error(atomExpr, "Unexpected atomic type" + atomExpr.type);
        };

        SemAn.ofType.put(atomExpr, returnType);
        return returnType;
    }


    // v4, v5, v6, v7
    @Override
    public SemType visit(AstBinExpr binExpr, TypeResolver.Mode mode) {
        super.visit(binExpr, mode);
        if (mode != Mode.THIRD) return null;

        SemType exprType1 = SemAn.ofType.get(binExpr.fstExpr);
        SemType exprType2 = SemAn.ofType.get(binExpr.sndExpr);

        SemType returnType;
        switch (binExpr.oper) {
            // v4
            case OR, AND:
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
            case ADD, SUB, MUL, MOD, DIV:
                if (exprType1.actualType() instanceof SemInt && exprType2.actualType() instanceof SemInt)
                    returnType = new SemInt();
                else if (exprType1.actualType() instanceof SemInt && !(exprType2.actualType() instanceof SemInt))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!(exprType1.actualType() instanceof SemInt) && exprType2.actualType() instanceof SemInt)
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. First expression is NOT of type bool");
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Neither of them are of type bool");
                break;
            // v6
            case EQU, NEQ:
                boolean good = false;
                if (exprType1 == null) { }
                else if (exprType1.actualType() instanceof SemBool) good = true;
                else if (exprType1.actualType() instanceof SemChar) good = true;
                else if (exprType1.actualType() instanceof SemInt) good = true;
                else if (exprType1.actualType() instanceof SemPtr) good = true;

                if (!good)
                    throw new Report.Error(binExpr, "Type error: type for ==/!= comparison is wrong.");

                if (areTwoTypesSame(exprType1, exprType2))
                    returnType = new SemBool();
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool.");
                break;
            // v7
            case LEQ, LTH, GEQ, GTH:
                good = false;
                if (exprType1 == null) { }
                else if (exprType1.actualType() instanceof SemChar) good = true;
                else if (exprType1.actualType() instanceof SemInt) good = true;
                else if (exprType1.actualType() instanceof SemPtr) good = true;

                if (!good)
                    throw new Report.Error(binExpr, "Type error: type for <=/>=/</> comparison is wrong.");

                if (areTwoTypesSame(exprType1, exprType2))
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
        super.visit(sfxExpr, mode);
        if (mode != Mode.THIRD) return null;
        SemType exprType = SemAn.ofType.get(sfxExpr.expr);

        SemType returnType;
        switch (sfxExpr.oper) {
            case PTR:
                if (exprType.actualType() instanceof SemPtr)
                    returnType = ((SemPtr) exprType.actualType()).baseType;
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
        super.visit(compDecl, mode);
        if (mode != Mode.THIRD) return null;

        SemType type = SemAn.isType.get(compDecl.type);

        if (type == null)
            Report.warning("AstCompDecl [" + compDecl.location().toString() + "] is null.");

        if (!notVoid(type))
            throw new Report.Error(compDecl, "Type error: component of type VOID.");

        return null;
    }


    // v10
    @Override
    public SemType visit(AstArrExpr arrExpr, TypeResolver.Mode mode) {
        super.visit(arrExpr, mode);
        if (mode != Mode.THIRD) return null;

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
        recExpr.rec.accept(this, mode);
        if (mode != Mode.THIRD) return null;

        SemType exprType = SemAn.ofType.get(recExpr.rec);
        exprType = exprType.actualType();

        if(exprType instanceof SemRec) {
            SymbTable st = declaresComp.get(exprType);

            try {
                AstDecl ad = st.fnd(recExpr.comp.name);
                if(ad instanceof AstCompDecl) {
                    SemType type = SemAn.isType.get(((AstCompDecl) ad).type);
                    SemAn.ofType.put(recExpr, type);
                    SemAn.declaredAt.put(recExpr.comp, ad);
                } else {
                    throw new Report.InternalError();
                }
            } catch (SymbTable.CannotFndNameException e) {
                e.printStackTrace();
            }
        } else {
            throw new Report.InternalError();
        }

        return exprType;
    }

    //v 12
    @Override
    public SemType visit(AstCallExpr callExpr, Mode mode) {
        super.visit(callExpr, mode);
        if (mode != Mode.THIRD) return null;
        AstDecl decl = SemAn.declaredAt.get(callExpr);

        if (decl instanceof AstFunDecl) {
            AstFunDecl funDecl = (AstFunDecl) decl;

            if (funDecl.pars.size() != callExpr.args.size())
                throw new Report.Error(callExpr, "Type error: function argument count difference");

            for (int i = 0; i < funDecl.pars.size(); i++) {
                SemType dclType = SemAn.isType.get(funDecl.pars.get(i).type);

                if (dclType == null)
                    Report.warning("SemType in function call [" + callExpr.name + "] is null.");

                SemType actType = SemAn.ofType.get(callExpr.args.get(i));

                if (!(areTwoTypesSame(dclType, actType)))
                    throw new Report.Error(callExpr, "Type error: function argument type difference");
            }

            SemType callType = SemAn.isType.get(funDecl.type);

            if (callType == null)
                Report.warning("Return type of function call [" + callExpr.name + "] is null.");

            SemAn.ofType.put(callExpr, callType);

            return callType;
        } else {
            throw new Report.Error(callExpr, "Type error: undeclared function call.");
        }
    }

    // v13
    @Override
    public SemType visit(AstStmtExpr stmtExpr, Mode mode) {
        super.visit(stmtExpr, mode);
        if (mode != Mode.THIRD) return null;

        int lastIndex = stmtExpr.stmts.size() - 1;
        AstStmt expr = stmtExpr.stmts.get(lastIndex);

        SemType type = SemAn.ofType.get(stmtExpr.stmts.get(lastIndex));
        SemAn.ofType.put(stmtExpr, type);
        return type;
    }

    @Override
    public SemType visit(AstTypeDecl typeDecl, Mode mode) {
        super.visit(typeDecl, mode);

        if (mode == Mode.FIRST) {
            SemName name = new SemName(typeDecl.name);
            SemAn.declaresType.put(typeDecl, name);
        }

        if (mode == Mode.SECOND) {
            SemName name = SemAn.declaresType.get(typeDecl);
            SemType type = SemAn.isType.get(typeDecl.type);

            if (type == null)
                Report.warning("SemType in [" + typeDecl.name + "] is null.");

            name.define(type);
        }

        return null;
    }

    // V14
    @Override
    public SemType visit(AstCastExpr castExpr, Mode mode) {
        super.visit(castExpr, mode);
        if (mode != Mode.THIRD) return null;

        SemType expr = SemAn.ofType.get(castExpr.expr);

        if (!isIntCharPtr(expr))
            throw new Report.Error(castExpr, "Type error: typecast expression of invalid type.");

        SemType type = SemAn.isType.get(castExpr.type);

        if (type == null)
            Report.warning("SemType in expression [" + castExpr.location().toString() + "] is null.");

        if (!isIntCharPtr(expr))
            throw new Report.Error(castExpr, "Type error: typecast type invalid.");

        SemType returnType = type;
        SemAn.ofType.put(castExpr, type);

        return type;
    }

    // V15 drugi del
    @Override
    public SemType visit(AstWhereExpr whereExpr, Mode mode) {
        if (whereExpr.decls != null)
            whereExpr.decls.accept(this, mode);
        if (whereExpr.expr != null)
            whereExpr.expr.accept(this, mode);

        if (mode != Mode.THIRD) return null;

        SemType type = SemAn.ofType.get(whereExpr.expr);
        SemAn.ofType.put(whereExpr, type);

        return type;
    }

    // d4
    @Override
    public SemType visit(AstFunDecl funDecl, TypeResolver.Mode mode) {
        super.visit(funDecl, mode);
        if (mode != Mode.THIRD) return null;

        SemType retType = SemAn.isType.get(funDecl.type);

        if (retType == null)
            Report.warning("Return type of function declaration [" + funDecl.name + "] is null.");

        boolean good = isIntCharPtr(retType);
        if (retType.actualType() instanceof SemVoid) good = true;
        else if (retType.actualType() instanceof SemBool) good = true;

        if (!good)
            throw new Report.Error(funDecl, "Type error: function " + funDecl.name + " with wrong return type.");

        if (funDecl.expr != null) {
            SemType expType = SemAn.ofType.get(funDecl.expr);

            if (!areTwoTypesSame(retType, expType))
                throw new Report.Error(funDecl, "Type error: function " + funDecl.name + " return type doesn't match with expression.");
        }

        return null;
    }

    @Override
    public SemType visit(AstParDecl parDecl, Mode mode) {
        super.visit(parDecl, mode);
        if (mode != Mode.THIRD) return null;

        SemType type = SemAn.isType.get(parDecl.type);

        if (type == null)
            Report.warning("SemType of parameter [" + parDecl.location().toString() + "] is null.");

        boolean good = isIntCharPtr(type);
        if (type.actualType() instanceof SemBool) good = true;

        if (!good)
            throw new Report.Error(parDecl, "Type error: parameter " + parDecl.name + " of wrong type");

        return null;
    }


    @Override
    public SemType visit(AstExprStmt exprStmt, Mode mode) {
        super.visit(exprStmt, mode);
        if (mode != Mode.THIRD) return null;

        SemType type = SemAn.ofType.get(exprStmt.expr);
        SemAn.ofType.put(exprStmt, type);

        return null;
    }

    // s1
    @Override
    public SemType visit(AstAssignStmt assignStmt, TypeResolver.Mode mode) {
        super.visit(assignStmt, mode);
        if (mode != Mode.THIRD) return null;

        SemType exprType1 = SemAn.ofType.get(assignStmt.dst);
        SemType exprType2 = SemAn.ofType.get(assignStmt.src);

        boolean good = isIntCharPtr(exprType1);
        if (exprType1.actualType() instanceof SemBool) good = true;

        if (!good)
            throw new Report.Error(assignStmt, "Type error: assignment on location [ " + assignStmt.location() + " ] uses wrong expression types.");

        if (!areTwoTypesSame(exprType1, exprType2))
            throw new Report.Error(assignStmt, "Type error: assignment on location [ " + assignStmt.location() + " ] with two different type of expressions.");

        SemType returnType = new SemVoid();
        SemAn.ofType.put(assignStmt, returnType);
        return returnType;
    }

    // s2
    @Override
    public SemType visit(AstIfStmt ifStmt, TypeResolver.Mode mode) {
        super.visit(ifStmt, mode);
        if (mode != Mode.THIRD) return null;

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
        super.visit(whileStmt, mode);
        if (mode != Mode.THIRD) return null;

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
        super.visit(nameType, mode);
        if(mode != Mode.SECOND) return null;

        AstDecl nameDecl = SemAn.declaredAt.get(nameType);

        if (nameDecl instanceof AstTypeDecl) {
            SemName name = SemAn.declaresType.get((AstTypeDecl) nameDecl);

            if (name == null)
                Report.warning("SemName in [" + nameType.location() + "] is null.");

            SemAn.isType.put(nameType, name);
            return name;

        } else
            throw new Report.Error(nameType, "Type error: undeclared type in AstNameType.");
    }

    // D2
    @Override
    public SemType visit(AstVarDecl varDecl, Mode mode) {
        super.visit(varDecl, mode);
        if (mode != Mode.THIRD) return null;

        SemType type = SemAn.isType.get(varDecl.type);

        if (type == null)
            Report.warning("SemType of variable declaration [" + varDecl.name + "] is null, location [" + varDecl.location() + "].");

        if(type.actualType() instanceof SemVoid)
            throw new Report.Error(varDecl, "Type error: type can't be VOID.");

        return null;
    }

    // d3 d4
    @Override
    public SemType visit(AstNameExpr nameExpr, TypeResolver.Mode mode) {
        super.visit(nameExpr, mode);
        if (mode != Mode.THIRD) return null;

        AstDecl nameDecl = SemAn.declaredAt.get(nameExpr);

        SemType type = null;
        if (nameDecl instanceof AstVarDecl) {
            type = SemAn.isType.get(((AstVarDecl) nameDecl).type);
            if (type == null)
                Report.warning("SemType of name expression [" + nameExpr.name + "] is null, location [" + nameExpr.location() + "].");
        }

        if (nameDecl instanceof AstParDecl) {
            type = SemAn.isType.get(((AstParDecl) nameDecl).type);

            if (type == null)
                Report.warning("SemType of name expression [" + nameExpr.name + "] , location [" + nameExpr.location() + "].");
        }

        // if fun WHAT TO DO
        if (nameDecl instanceof AstFunDecl)
            Report.warning("Calling function [" + nameExpr.name + "] without ( ).");

        SemAn.ofType.put(nameExpr, type);
        return type;
    }

    public enum Mode {
        FIRST, SECOND, THIRD, VALIDATION
    }

}
