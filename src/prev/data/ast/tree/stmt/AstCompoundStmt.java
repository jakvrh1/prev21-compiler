package prev.data.ast.tree.stmt;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.visitor.*;

/**
 * Compound statement.
 */
public class AstCompoundStmt extends AstNode implements AstStmt {

	/** The statements. */
	public final AstTrees<AstStmt> stmts;

	/**
	 * Constructs a compound statement.
	 * 
	 * @param location The location.
	 * @param stmts    The statements.
	 */
	public AstCompoundStmt(Location location, AstTrees<AstStmt> stmts) {
		super(location);
		this.stmts = stmts;
	}

	@Override
	public <Result, Arg> Result accept(AstVisitor<Result, Arg> visitor, Arg arg) {
		return visitor.visit(this, arg);
	}

}
