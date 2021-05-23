package prev.phase.seman;

import prev.common.report.Report;
import prev.data.ast.tree.decl.AstDecl;
import prev.data.ast.tree.decl.AstParDecl;
import prev.data.ast.tree.decl.AstVarDecl;
import prev.data.ast.tree.expr.AstArrExpr;
import prev.data.ast.tree.expr.AstNameExpr;
import prev.data.ast.tree.expr.AstRecExpr;
import prev.data.ast.tree.expr.AstSfxExpr;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.visitor.AstFullVisitor;
import prev.data.typ.SemPtr;
import prev.data.typ.SemType;

/**
 * Address resolver.
 * <p>
 * The address resolver finds out which expressions denote lvalues and leaves
 * the information in {@link SemAn#isAddr}.
 */
public class AddrResolver extends AstFullVisitor<Object, AddrResolver.Mode> {

    @Override
    public Object visit(AstAssignStmt assignStmt, Mode mode) {
        super.visit(assignStmt, mode);

        if (mode == Mode.SECOND) {
            Boolean o = SemAn.isAddr.get(assignStmt.dst);
            if (o == null || !o)
                throw new Report.Error(assignStmt, "Addr error: Statement on left side needs to be lvalue.");
        }

        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Mode mode) {
        super.visit(nameExpr, mode);
        if (mode == Mode.FIRST) {
            AstDecl decl = SemAn.declaredAt.get(nameExpr);
            if (decl instanceof AstVarDecl)
                SemAn.isAddr.put(nameExpr, true);
            else if (decl instanceof AstParDecl)
                SemAn.isAddr.put(nameExpr, true);
        }

        return null;
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, Mode mode) {
        super.visit(sfxExpr, mode);
        if (mode == Mode.FIRST) {
            SemType type = SemAn.ofType.get(sfxExpr.expr);
            if (type instanceof SemPtr)
                SemAn.isAddr.put(sfxExpr, true);
        }

        return null;
    }

    @Override
    public Object visit(AstArrExpr arrExpr, Mode mode) {
        super.visit(arrExpr, mode);
        if (mode == Mode.FIRST) {
            Boolean a = SemAn.isAddr.get(arrExpr.arr);
            if (a == null)
                throw new Report.Error(arrExpr, "Addr error: isAddr returned null.");
            else
                SemAn.isAddr.put(arrExpr, a);
        }

        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Mode mode) {
        super.visit(recExpr, mode);
        if (mode == Mode.FIRST) {
            Boolean a = SemAn.isAddr.get(recExpr.rec);
            if (a == null)
                throw new Report.Error(recExpr, "Addr error: isAddr returned null.");
            else
                SemAn.isAddr.put(recExpr, a);
        }

        return null;
    }

    public enum Mode {
        FIRST, SECOND
    }
}
