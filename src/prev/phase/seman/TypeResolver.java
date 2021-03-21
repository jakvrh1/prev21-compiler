package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.tree.stmt.AstIfStmt;
import prev.data.ast.tree.stmt.AstWhileStmt;
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

    // T1
    @Override
    public SemType visit(AstAtomType atomType, TypeResolver.Mode mode) {
        super.visit(atomType, mode);

        SemType returnType;
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
                throw new Report.Error(atomType, "Type error: not of type VOID, CHAR, INT or BOOL");
        }

        SemAn.isType.put(atomType, returnType);
        return returnType;
    }

    // T2
    @Override
    public SemType visit(AstArrType arrType, TypeResolver.Mode mode) {
        super.visit(arrType, mode);
        SemType exprType = SemAn.isType.get(arrType.elemType);

        SemType returnType = null;
        if (arrType.numElems instanceof AstAtomExpr && ((AstAtomExpr) arrType.numElems).type == AstAtomExpr.Type.INT) {
            long value;
            try {
                value = Long.parseLong(((AstAtomExpr) arrType.numElems).value);
            } catch (NumberFormatException e) {
                throw new Report.Error(arrType, "Type error: index value value too big");
            }
            if (value <= 0) {
                throw new Report.Error(arrType, "Type error: index value can't be negative");
            }

            if (everyTypeExceptVoid(exprType)) {
                returnType = new SemArr(exprType, value);
            } else {
                throw new Report.Error(arrType, "Type error: array of wrong type");
            }
        }

        //Report.info(arrType, returnType.toString());
        SemAn.isType.put(arrType, returnType);
        return returnType;
    }

    //T3
    @Override
    public SemType visit(AstRecType recType, TypeResolver.Mode mode) {
        super.visit(recType, mode);

        SemType returnType;
        LinkedList<SemType> recs = new LinkedList<>();

        for (AstCompDecl acd : recType.comps) {
            SemType compType = SemAn.isType.get(acd.type);

            if (everyTypeExceptVoid(compType)) {
                recs.add(compType);
            } else {
                throw new Report.Error(recType, "Type error: record type with wrong type");
            }
        }

        returnType = new SemRec(recs);
        SemAn.isType.put(recType, returnType);

        return returnType;
    }

    // T4
    @Override
    public SemType visit(AstPtrType ptrType, TypeResolver.Mode mode) {
        super.visit(ptrType, mode);

        SemType type = SemAn.isType.get(ptrType.baseType);
        SemType returnType = null;

        if (everyType(type)) {
            returnType = new SemPtr(type);
        } else {
            throw new Report.Error(ptrType, "Type error: ptr TODO");
        }

        SemAn.isType.put(ptrType, returnType);
        return returnType;
    }

    // prefix operations
    // v3 prvi del
    // v8 prvi del
    // v9
    @Override
    public SemType visit(AstPfxExpr pfxExpr, Mode mode) {
        super.visit(pfxExpr, mode);
        AstExpr expr = pfxExpr.expr;
        SemType exprType = SemAn.ofType.get(expr);

        SemType returnType;
        switch (pfxExpr.oper) {
            case NOT:
                if (exprType instanceof SemBool)
                    returnType = new SemBool();
                else
                    throw new Report.Error(pfxExpr, "Type error: negation operator ! can only be used on bool");
                break;
            case PTR:
                if (everyType(exprType))
                    returnType = new SemPtr(exprType);
                else
                    throw new Report.Error(pfxExpr, "Type error: pointer operator ^ can only be used on arrays, records and pointers");
                break;
            case ADD:
            case SUB:
                if (exprType instanceof SemInt)
                    returnType = new SemInt();
                else
                    throw new Report.Error(pfxExpr, "Type error: add/sub operator +/- can only be used on integers");
                break;
            case NEW:
                if (exprType instanceof SemInt)
                    returnType = new SemPtr(new SemVoid());
                else
                    throw new Report.Error(pfxExpr, "Type error: new operator can only be used with integers");
                break;
            case DEL:
                if (exprType instanceof SemPtr)
                    returnType = new SemVoid();
                else
                    throw new Report.Error(pfxExpr, "Type error: del operator can only be used with pointers");
            default:
                throw new Report.Error(pfxExpr, "Unexpected prefix operator " + pfxExpr.oper);
        }
        SemAn.ofType.put(pfxExpr, returnType);
        return returnType;
    }

    // T5

    // v1, v2
    @Override
    public SemType visit(AstAtomExpr atomExpr, TypeResolver.Mode mode) {
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

    public boolean everyType(SemType type) {
        if (everyTypeExceptVoid(type)) return true;
        return type instanceof SemVoid;
    }

    public boolean everyTypeExceptVoid(SemType type) {
        if (type instanceof SemBool) return true;
        if (type instanceof SemChar) return true;
        if (type instanceof SemInt) return true;
        if (type instanceof SemPtr && everyType(((SemPtr) type).baseType)) return true;
        if (type instanceof SemArr && everyType(((SemArr) type).elemType)) return true;
        if (type instanceof SemRec) {
            boolean t = true;
            for (int i = 0; i < ((SemRec) type).numComps(); i++) {
                if (!everyTypeExceptVoid(((SemRec) type).compType(i))) t = false;
            }
            return t;
        }
        return false;
    }

    public boolean areTwoTypesSame(SemType type1, SemType type2) {
        if (type1 instanceof SemVoid && type2 instanceof SemVoid) return true;
        if (type1 instanceof SemBool && type2 instanceof SemBool) return true;
        if (type1 instanceof SemChar && type2 instanceof SemChar) return true;
        if (type1 instanceof SemInt && type2 instanceof SemInt) return true;
        if (type1 instanceof SemPtr && type2 instanceof SemPtr && areTwoTypesSame(((SemPtr) type1).baseType, ((SemPtr) type2).baseType))
            return true;
        if (type1 instanceof SemArr && type2 instanceof SemArr && areTwoTypesSame(((SemArr) type1).elemType, ((SemArr) type2).elemType))
            return true;
        if (type1 instanceof SemRec && type2 instanceof SemRec && ((SemRec) type1).numComps() == ((SemRec) type2).numComps()) {
            boolean t = true;

            for (int i = 0; i < ((SemRec) type1).numComps(); i++) {
                if (!areTwoTypesSame(((SemRec) type1).compType(i), ((SemRec) type2).compType(i))) t = false;
            }
            return t;
        }
        return false;
    }

    public boolean isS1V6Type(SemType expr) {
        if (expr instanceof SemBool) return true;
        if (expr instanceof SemChar) return true;
        if (expr instanceof SemInt) return true;
        return expr instanceof SemPtr && everyType(((SemPtr) expr).baseType);
    }

    public boolean isV7Type(SemType expr) {
        if (expr instanceof SemChar) return true;
        if (expr instanceof SemInt) return true;
        return expr instanceof SemPtr && everyType(((SemPtr) expr).baseType);
    }

    // v4, v5, v6, v7
    @Override
    public SemType visit(AstBinExpr binExpr, TypeResolver.Mode mode) {
        super.visit(binExpr, mode);
        SemType exprType1 = SemAn.ofType.get(binExpr.fstExpr);
        SemType exprType2 = SemAn.ofType.get(binExpr.sndExpr);

        SemType returnType;
        switch (binExpr.oper) {
            // v4
            case OR:
            case AND:
                if (exprType1 instanceof SemBool && exprType2 instanceof SemBool)
                    returnType = new SemBool();
                else if (exprType1 instanceof SemBool && !(exprType2 instanceof SemBool))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!(exprType1 instanceof SemBool) && exprType2 instanceof SemBool)
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
                if (exprType1 instanceof SemInt && exprType2 instanceof SemInt)
                    returnType = new SemInt();
                else if (exprType1 instanceof SemInt && !(exprType2 instanceof SemInt))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!(exprType1 instanceof SemInt) && exprType2 instanceof SemInt)
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. First expression is NOT of type bool");
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Neither of them are of type bool");
                break;
            // v6
            case EQU:
            case NEQ:
                if (isS1V6Type(exprType1) && isS1V6Type(exprType2))
                    returnType = new SemBool();
                else if (isS1V6Type(exprType1) && !isS1V6Type(exprType2))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!isS1V6Type(exprType1) && isS1V6Type(exprType2))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. First expression is NOT of type bool");
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Neither of them are of type bool");
                break;
            // v7
            case LEQ:
            case LTH:
            case GEQ:
            case GTH:
                if (isV7Type(exprType1) && isV7Type(exprType2))
                    returnType = new SemBool();
                else if (isV7Type(exprType1) && !isV7Type(exprType2))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Second expression is NOT of type bool");
                else if (!isV7Type(exprType1) && isV7Type(exprType2))
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. First expression is NOT of type bool");
                else
                    throw new Report.Error(binExpr, "Type error: " + getBinaryOperator(binExpr) + " operator can only be used when both expressions are of type bool. Neither of them are of type bool");
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
        SemType exprType = SemAn.ofType.get(sfxExpr.expr);

        SemType returnType;
        switch (sfxExpr.oper) {
            case PTR:
                if (exprType instanceof SemPtr)
                    returnType = ((SemPtr) exprType).baseType;
                else
                    throw new Report.Error(sfxExpr, "Type error: TODO");
                break;
            default:
                throw new Report.Error(sfxExpr, "Unexpected suffix operand");
        }

        SemAn.ofType.put(sfxExpr, returnType);
        return returnType;
    }

    // v10
    @Override
    public SemType visit(AstArrExpr arrExpr, TypeResolver.Mode mode) {
        super.visit(arrExpr, mode);
        SemType exprType = SemAn.ofType.get(arrExpr.arr);
        SemType exprIdx = SemAn.ofType.get(arrExpr.idx);

        SemType returnType;
        if (exprType instanceof SemArr && exprIdx instanceof SemInt)
            returnType = ((SemArr) exprType).elemType;
        else if (!(exprType instanceof SemArr) && exprIdx instanceof SemInt)
            throw new Report.Error(arrExpr, "Type error: expression not an array type");
        else if (exprType instanceof SemArr && !(exprIdx instanceof SemInt))
            throw new Report.Error(arrExpr, "Type error: index of an array is not an integer");
        else
            throw new Report.Error(arrExpr, "Unexpected array expression");


        SemAn.ofType.put(arrExpr, returnType);
        return returnType;
    }

    //v11
    @Override
    public SemType visit(AstRecExpr recExpr, TypeResolver.Mode mode) {
        super.visit(recExpr, mode);
        SemType exprType = SemAn.ofType.get(recExpr.rec);

        return null;
    }

    //v 12
    @Override
    public SemType visit(AstCallExpr callExpr, Mode mode) {
        super.visit(callExpr, mode);
        AstDecl decl = SemAn.declaredAt.get(callExpr);

        if (decl instanceof AstFunDecl) {
            AstFunDecl funDecl = (AstFunDecl) decl;

            if (funDecl.pars.size() != callExpr.args.size()) {
                throw new Report.Error(callExpr, "Type error: argument count difference");
            }
            for (int i = 0; i < funDecl.pars.size(); i++) {
                SemType dclType = SemAn.isType.get(funDecl.pars.get(i).type);
                SemType actType = SemAn.ofType.get(callExpr.args.get(i));
                if (!(areTwoTypesSame(dclType, actType))) {
                    throw new Report.Error("Type error: TODO");
                }
            }
            SemType callType = SemAn.isType.get(funDecl.type);
            SemAn.ofType.put(callExpr, callType);
            return callType;
        } else {
            throw new Report.Error(callExpr, "Type error: unexpected function call");
        }
    }

    //
    @Override
    public SemType visit(AstFunDecl funDecl, TypeResolver.Mode mode) {
        return super.visit(funDecl, mode);
    }

    // v13
    @Override
    public SemType visit(AstStmtExpr stmtExpr, Mode mode) {
        super.visit(stmtExpr, mode);

        int lastIndex = stmtExpr.stmts.size() - 1;
        SemType type = SemAn.ofType.get(stmtExpr.stmts.get(lastIndex));

        SemAn.ofType.put(stmtExpr, type);
        return type;
    }

    // s1
    @Override
    public SemType visit(AstAssignStmt assignStmt, TypeResolver.Mode mode) {
        super.visit(assignStmt, mode);
        SemType exprType1 = SemAn.ofType.get(assignStmt.dst);
        SemType exprType2 = SemAn.ofType.get(assignStmt.src);

        SemType returnType;

        /*if(exprType1 == null || exprType2 == null) {
            System.out.println("Null");
        }
        */

        if (isS1V6Type(exprType1) && areTwoTypesSame(exprType1, exprType2)) {
            //if(isS1V6Type(exprType1) && exprType1.getClass() == exprType2.getClass()) {
            returnType = new SemVoid();
        } else {
            throw new Report.Error(assignStmt, "Statement error: either two expressions are not of the same type or their type is not valid. ");
            //return null;
        }

        SemAn.ofType.put(assignStmt, returnType);
        return returnType;
    }

    // v14

    // s2
    @Override
    public SemType visit(AstIfStmt ifStmt, TypeResolver.Mode mode) {
        super.visit(ifStmt, mode);
        SemType exprType1 = SemAn.ofType.get(ifStmt.cond);

        SemType returnType;
        if (exprType1 instanceof SemBool)
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
        SemType exprType1 = SemAn.ofType.get(whileStmt.cond);

        SemType returnType;
        if (exprType1 instanceof SemBool)
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

        //Report.info(nameType, nameType.toString());

        AstDecl nameDecl = SemAn.declaredAt.get(nameType);

        //Report.info(nameDecl, nameDecl.toString());

        SemType type;
        if (nameDecl instanceof AstTypeDecl) {
            type = SemAn.isType.get(((AstTypeDecl) nameDecl).type);
        } else {
            //throw new Report.Error(nameType, "Type error: TODO1");
            return null;
        }

        SemAn.isType.put(nameType, type);
        return type;
    }

    // d2 d3 d4
    @Override
    public SemType visit(AstNameExpr nameExpr, TypeResolver.Mode mode) {
        super.visit(nameExpr, mode);
        AstDecl nameDecl = SemAn.declaredAt.get(nameExpr);

        SemType type = null;
        if (nameDecl instanceof AstVarDecl) {
            type = SemAn.isType.get(((AstVarDecl) nameDecl).type);

            if (!everyType(type)) {
                //throw new Report.Error(nameExpr, "Type error: TODO2");
                return null;
            }

            SemAn.ofType.put(nameExpr, type);
        } else if (nameDecl instanceof AstFunDecl) {
            LinkedList<SemType> pars = new LinkedList<>();

            for (AstParDecl apd : ((AstFunDecl) nameDecl).pars) {
                SemType parType = SemAn.isType.get(apd.type);
                if (everyType(parType)) {
                    pars.add(parType);
                } else {
                    throw new Report.Error(nameExpr, "Type error: TODO3");
                }
            }
            SemType funType = SemAn.isType.get(((AstFunDecl) nameDecl).type);

            if (((AstFunDecl) nameDecl).expr != null) {
                SemType exprType = SemAn.ofType.get(((AstFunDecl) nameDecl).expr);

                if (!(exprType.getClass() == funType.getClass()))
                    throw new Report.Error(nameExpr, "Type error: TODO4");
            }

            if (everyTypeExceptVoid(funType)) {
                type = new SemFun(pars, funType);
            } else {
                throw new Report.Error(nameExpr, "Type error: TODO5");
            }

            SemAn.ofType.put(nameExpr, funType);
        }

        return type;
    }

    public enum Mode {
        FIRST, SECOND
    }

}
