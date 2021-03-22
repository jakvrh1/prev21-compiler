package prev.phase.seman;

import prev.data.ast.tree.decl.AstDecl;
import prev.data.ast.tree.decl.AstParDecl;
import prev.data.ast.tree.decl.AstVarDecl;
import prev.data.ast.tree.expr.*;
import prev.data.ast.visitor.*;
import prev.data.typ.SemPtr;
import prev.data.typ.SemType;

/**
 * Address resolver.
 * 
 * The address resolver finds out which expressions denote lvalues and leaves
 * the information in {@link SemAn#isAddr}.
 */
public class AddrResolver extends AstFullVisitor<Object, Object> {
    @Override
    public Object visit(AstNameExpr nameExpr, Object o) {
        super.visit(nameExpr, o);

        AstDecl decl = SemAn.declaredAt.get(nameExpr);
        if(decl instanceof AstVarDecl) {
            SemAn.isAddr.put(nameExpr, true);
        } else if(decl instanceof AstParDecl) {
            SemAn.isAddr.put(nameExpr, true);
        }
        return null;
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, Object o) {
        super.visit(sfxExpr, o);

        SemType type = SemAn.ofType.get(sfxExpr.expr);
        if(type instanceof SemPtr) {
            SemAn.isAddr.put(sfxExpr, true);
        }

        return null;
    }

    @Override
    public Object visit(AstArrExpr arrExpr, Object o) {
        super.visit(arrExpr, o);

        boolean a = SemAn.isAddr.get(arrExpr.arr);
        SemAn.isAddr.put(arrExpr, a);

        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Object o) {
        super.visit(recExpr, o);

        boolean a = SemAn.isAddr.get(recExpr.rec);
        SemAn.isAddr.put(recExpr, a);
        return null;
    }
}
