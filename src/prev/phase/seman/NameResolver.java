package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.AstCallExpr;
import prev.data.ast.tree.expr.AstNameExpr;
import prev.data.ast.tree.expr.AstWhereExpr;
import prev.data.ast.visitor.AstFullVisitor;

/**
 * Name resolver.
 * 
 * Name resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in
 * {@link SemAn#declaredAt}.
 */
public class NameResolver extends AstFullVisitor<Object, NameResolver.Mode> {

	private SymbTable symbTable = new SymbTable();

	private int GLOBAL_SCOPE_ID = 1;

	public enum Mode {
		FIRST, SECOND
	};

	@Override
	public Object visit(AstFunDecl funDecl, NameResolver.Mode mode) {
	    if(mode == Mode.FIRST) {
			try {
				symbTable.ins(funDecl.name, funDecl);
			} catch (SymbTable.CannotInsNameException e) {
			    throw new Report.Error(funDecl, "semantic error: redeclaration");
			}
		}

	    if(mode == Mode.SECOND) {
	    	symbTable.newScope();

	    	if(funDecl.pars != null) {
	    		for(AstParDecl par : funDecl.pars) {
					try {
						symbTable.ins(par.name, par);
					} catch (SymbTable.CannotInsNameException e) {
						throw new Report.Error(funDecl, "semantic error: redeclaration");
					}
				}
			}

	    	if(funDecl.expr != null) {
	    		funDecl.expr.accept(this, mode);
			}

			symbTable.oldScope();
		}
	    return null;
	}

	@Override
	public Object visit(AstTypeDecl typeDecl, Mode mode) {
	    if(mode == Mode.FIRST) {
			try {
				symbTable.ins(typeDecl.name, typeDecl);
			} catch (SymbTable.CannotInsNameException e) {
				throw new Report.Error(typeDecl, "semantic error: redeclaration");
			}
		}
	    return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Mode mode) {
		if(mode == Mode.FIRST) {
			try {
				symbTable.ins(varDecl.name, varDecl);
			} catch (SymbTable.CannotInsNameException e) {
				throw new Report.Error(varDecl, "semantic error: redeclaration");
			}
		}
		return null;
	}

	@Override
	public Object visit(AstWhereExpr whereExpr, Mode mode) {
	    if(mode == Mode.SECOND) {
	    	symbTable.newScope();

	    	super.visit(whereExpr, Mode.FIRST);
			super.visit(whereExpr, Mode.SECOND);

			symbTable.oldScope();
		}
	    return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Mode mode) {
		try {
			SemAn.declaredAt.put(callExpr, symbTable.fnd(callExpr.name));
		} catch (SymbTable.CannotFndNameException e) {
			throw new Report.Error(callExpr, "semantic error: redeclaration");
		}
		return null;
	}

	@Override
	public Object visit(AstNameExpr nameExpr, Mode mode) {
		try {
			SemAn.declaredAt.put(nameExpr, symbTable.fnd(nameExpr.name));
		} catch (SymbTable.CannotFndNameException e) {
			throw new Report.Error(nameExpr, "semantic error: redeclaration");
		}
		return null;
	}
}
