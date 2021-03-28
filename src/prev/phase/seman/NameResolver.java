package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.AstFunDecl;
import prev.data.ast.tree.decl.AstParDecl;
import prev.data.ast.tree.decl.AstTypeDecl;
import prev.data.ast.tree.decl.AstVarDecl;
import prev.data.ast.tree.expr.AstCallExpr;
import prev.data.ast.tree.expr.AstNameExpr;
import prev.data.ast.tree.expr.AstRecExpr;
import prev.data.ast.tree.expr.AstWhereExpr;
import prev.data.ast.tree.type.AstNameType;
import prev.data.ast.visitor.AstFullVisitor;

/**
 * Name resolver.
 * <p>
 * Name resolver connects each node of a abstract syntax tree where a name is
 * used with the node where it is declared. The only exceptions are a record
 * field names which are connected with its declarations by type resolver. The
 * results of the name resolver are stored in			try {
 * SemAn.declaredAt.put(nameExpr, symbTable.fnd(nameExpr.name));
 * } catch (SymbTable.CannotFndNameException e) {
 * throw new Report.Error(nameExpr, "semantic error: cannot find name");
 * }
 * {@link SemAn#declaredAt}.
 */
public class NameResolver extends AstFullVisitor<Object, NameResolver.Mode> {

    private final SymbTable symbTable = new SymbTable();

    private final int GLOBAL_SCOPE_ID = 1;

    @Override
    public Object visit(AstNameType nameType, Mode mode) {
        if (mode == Mode.SECOND) {
            try {
                SemAn.declaredAt.put(nameType, symbTable.fnd(nameType.name));
            } catch (SymbTable.CannotFndNameException e) {
                throw new Report.Error(nameType, "semantic error: cannot find name -> " + nameType.name);
            }
        }
        //super.visit(nameType, mode);
        return null;
    }

	@Override
    public Object visit(AstFunDecl funDecl, NameResolver.Mode mode) {
        if (mode == Mode.FIRST) {
            try {
                symbTable.ins(funDecl.name, funDecl);
            } catch (SymbTable.CannotInsNameException e) {
                throw new Report.Error(funDecl, "semantic error: redeclaration of function -> " + funDecl.name);
            }

            funDecl.type.accept(this, Mode.FIRST);
            funDecl.type.accept(this, Mode.SECOND);
        }

        if (mode == Mode.SECOND) {
            symbTable.newScope();

            if (funDecl.pars != null) {
                funDecl.pars.accept(this, Mode.FIRST);
                funDecl.pars.accept(this, Mode.SECOND);
            }

            if (funDecl.expr != null) {
                funDecl.expr.accept(this, Mode.FIRST);
                funDecl.expr.accept(this, Mode.SECOND);
            }

            symbTable.oldScope();
        }
        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Mode mode) {
        recExpr.rec.accept(this, mode);
        return null;
    }

    @Override
    public Object visit(AstParDecl parDecl, Mode mode) {
        if (mode == Mode.FIRST) {
            try {
                symbTable.ins(parDecl.name, parDecl);
            } catch (SymbTable.CannotInsNameException e) {
                throw new Report.Error(parDecl, "semantic error: redeclaration of function parameter -> " + parDecl.name);
            }
        } else if (mode == Mode.SECOND) {
            super.visit(parDecl, mode);
        }
        //super.visit(parDecl, mode);
        return null;
    }

    @Override
    public Object visit(AstTypeDecl typeDecl, Mode mode) {
        if (mode == Mode.FIRST) {
            try {
                symbTable.ins(typeDecl.name, typeDecl);
            } catch (SymbTable.CannotInsNameException e) {
                throw new Report.Error(typeDecl, "semantic error: redeclaration of type -> " + typeDecl.name);
            }
        }
        return super.visit(typeDecl, mode);
    }

    @Override
    public Object visit(AstVarDecl varDecl, Mode mode) {
        if (mode == Mode.FIRST) {
            try {
                symbTable.ins(varDecl.name, varDecl);
            } catch (SymbTable.CannotInsNameException e) {
                throw new Report.Error(varDecl, "semantic error: redeclaration for variable -> " + varDecl.name);
            }
        }
        return super.visit(varDecl, mode);
    }

    @Override
    public Object visit(AstWhereExpr whereExpr, Mode mode) {
        if (mode == Mode.SECOND) {
            symbTable.newScope();

            super.visit(whereExpr, Mode.FIRST);
            super.visit(whereExpr, Mode.SECOND);

            symbTable.oldScope();
        }
        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, Mode mode) {
        if (mode == Mode.SECOND) {
            try {
                SemAn.declaredAt.put(callExpr, symbTable.fnd(callExpr.name));
            } catch (SymbTable.CannotFndNameException e) {
                throw new Report.Error(callExpr, "semantic error: cannot find name -> " + callExpr.name);
            }
            super.visit(callExpr, mode);
        }
        //super.visit(callExpr, mode);
        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Mode mode) {
        if (mode == Mode.SECOND) {
            try {
                SemAn.declaredAt.put(nameExpr, symbTable.fnd(nameExpr.name));
            } catch (SymbTable.CannotFndNameException e) {
                throw new Report.Error(nameExpr, "semantic error: cannot find name -> " + nameExpr.name);
            }
        }
        return null;
    }

    public enum Mode {
        FIRST, SECOND
    }
}
