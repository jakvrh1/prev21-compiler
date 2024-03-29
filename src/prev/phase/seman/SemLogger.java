package prev.phase.seman;

import prev.common.logger.Logger;
import prev.data.ast.tree.decl.AstDecl;
import prev.data.ast.tree.decl.AstTypeDecl;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.AstNullVisitor;
import prev.data.typ.SemType;

/**
 * Logs semantic attributes of an abstract syntax tree.
 * <p>
 * (Must be used as a subvisitor of {@link prev.phase.abstr.AbsLogger}.)
 */
public class SemLogger extends AstNullVisitor<Object, String> {

    /**
     * The logger the log should be written to.
     */
    private final Logger logger;

    /**
     * Construct a new visitor with a logger the log should be written to.
     *
     * @param logger The logger the log should be written to.
     */
    public SemLogger(Logger logger) {
        this.logger = logger;
    }

    // DECLARATIONS

    public Object visit(AstTypeDecl typeDecl, String arg) {
        SemType type = SemAn.declaresType.get(typeDecl);
        if (type != null) {
            logger.begElement("declaresType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    // EXPRESSIONS

    public Object visit(AstArrExpr arrExpr, String arg) {
        SemType type = SemAn.ofType.get(arrExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(arrExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, String arg) {
        SemType type = SemAn.ofType.get(atomExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(atomExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstBinExpr binExpr, String arg) {
        SemType type = SemAn.ofType.get(binExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(binExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, String arg) {
        AstDecl decl = SemAn.declaredAt.get(callExpr);
        if (decl != null) {
            logger.begElement("declaredAt");
            logger.addAttribute("idx", Integer.toString(decl.id()));
            logger.addAttribute("location", decl.location().toString());
            logger.endElement();
        }
        SemType type = SemAn.ofType.get(callExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(callExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstCastExpr castExpr, String arg) {
        SemType type = SemAn.ofType.get(castExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(castExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, String arg) {
        AstDecl decl = SemAn.declaredAt.get(nameExpr);
        if (decl != null) {
            logger.begElement("declaredAt");
            logger.addAttribute("idx", Integer.toString(decl.id()));
            logger.addAttribute("location", decl.location().toString());
            logger.endElement();
        }
        SemType type = SemAn.ofType.get(nameExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(nameExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstPfxExpr pfxExpr, String arg) {
        SemType type = SemAn.ofType.get(pfxExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(pfxExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, String arg) {
        SemType type = SemAn.ofType.get(recExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(recExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, String arg) {
        SemType type = SemAn.ofType.get(sfxExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(sfxExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstStmtExpr stmtExpr, String arg) {
        SemType type = SemAn.ofType.get(stmtExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(stmtExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstWhereExpr whereExpr, String arg) {
        SemType type = SemAn.ofType.get(whereExpr);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        Boolean isAddr = SemAn.isAddr.get(whereExpr);
        if ((isAddr != null) && (isAddr == true)) {
            logger.begElement("lvalue");
            logger.endElement();
        }
        return null;
    }

    // STATEMENT

    @Override
    public Object visit(AstAssignStmt assignStmt, String arg) {
        SemType type = SemAn.ofType.get(assignStmt);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstCompoundStmt compoundStmt, String arg) {
        SemType type = SemAn.ofType.get(compoundStmt);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstExprStmt exprStmt, String arg) {
        SemType type = SemAn.ofType.get(exprStmt);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstIfStmt ifStmt, String arg) {
        SemType type = SemAn.ofType.get(ifStmt);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstWhileStmt whileStmt, String arg) {
        SemType type = SemAn.ofType.get(whileStmt);
        if (type != null) {
            logger.begElement("ofType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    // TYPES

    @Override
    public Object visit(AstArrType arrType, String arg) {
        SemType type = SemAn.isType.get(arrType);
        if (type != null) {
            logger.begElement("isType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstAtomType atomType, String arg) {
        SemType type = SemAn.isType.get(atomType);
        if (type != null) {
            logger.begElement("isType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstNameType nameType, String arg) {
        AstDecl decl = SemAn.declaredAt.get(nameType);
        if (decl != null) {
            logger.begElement("declaredAt");
            logger.addAttribute("idx", Integer.toString(decl.id()));
            logger.addAttribute("location", decl.location().toString());
            logger.endElement();
        }
        SemType type = SemAn.isType.get(nameType);
        if (type != null) {
            logger.begElement("isType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstPtrType ptrType, String arg) {
        SemType type = SemAn.isType.get(ptrType);
        if (type != null) {
            logger.begElement("isType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstRecType recType, String arg) {
        SemType type = SemAn.isType.get(recType);
        if (type != null) {
            logger.begElement("isType");
            type.log(logger);
            logger.endElement();
        }
        return null;
    }

}
