package prev.phase.imcgen;

import prev.common.logger.Logger;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.tree.stmt.AstExprStmt;
import prev.data.ast.tree.stmt.AstIfStmt;
import prev.data.ast.tree.stmt.AstWhileStmt;
import prev.data.ast.visitor.AstNullVisitor;
import prev.data.imc.code.expr.ImcExpr;
import prev.data.imc.code.stmt.ImcStmt;

/**
 * Logs intermediate code attached to an abstract syntax tree.
 * <p>
 * (Must be used as a subvisitor of {@link prev.phase.abstr.AbsLogger}.)
 */
public class ImcLogger extends AstNullVisitor<Object, String> {

    /**
     * The logger the log should be written to.
     */
    private final Logger logger;

    /**
     * Construct a new visitor with a logger the log should be written to.
     *
     * @param logger The logger the log should be written to.
     */
    public ImcLogger(Logger logger) {
        this.logger = logger;
    }

    // EXPRESSIONS

    @Override
    public Object visit(AstArrExpr arrExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(arrExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(atomExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstBinExpr binExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(binExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(callExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstCastExpr castExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(castExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(nameExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstPfxExpr pfxExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(pfxExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(recExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(sfxExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstStmtExpr stmtExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(stmtExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstWhereExpr whereExpr, String arg) {
        ImcExpr code = ImcGen.exprImc.get(whereExpr);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    // STATEMENTS

    @Override
    public Object visit(AstAssignStmt assignStmt, String arg) {
        ImcStmt code = ImcGen.stmtImc.get(assignStmt);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstExprStmt exprStmt, String arg) {
        ImcStmt code = ImcGen.stmtImc.get(exprStmt);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstIfStmt ifStmt, String arg) {
        ImcStmt code = ImcGen.stmtImc.get(ifStmt);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

    @Override
    public Object visit(AstWhileStmt whileStmt, String arg) {
        ImcStmt code = ImcGen.stmtImc.get(whileStmt);
        if (code != null) {
            logger.begElement("imcs");
            code.log(logger);
            logger.endElement();
        }
        return null;
    }

}
