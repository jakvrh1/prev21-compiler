package prev.phase.imcgen;

import prev.data.ast.attribute.AstAttribute;
import prev.data.ast.tree.expr.AstExpr;
import prev.data.ast.tree.stmt.AstStmt;
import prev.data.ast.visitor.AstVisitor;
import prev.data.imc.code.expr.ImcExpr;
import prev.data.imc.code.stmt.ImcStmt;
import prev.phase.Phase;

/**
 * Intermediate code generation.
 */
public class ImcGen extends Phase implements AstVisitor<Object, Object> {

    /**
     * Maps statements to intermediate code.
     */
    public static final AstAttribute<AstStmt, ImcStmt> stmtImc = new AstAttribute<AstStmt, ImcStmt>(0);

    /**
     * Maps expressions to intermediate code.
     */
    public static final AstAttribute<AstExpr, ImcExpr> exprImc = new AstAttribute<AstExpr, ImcExpr>(0);

    /**
     * Constructs a new phase for intermediate code generation.
     */
    public ImcGen() {
        super("imcgen");
    }

}
