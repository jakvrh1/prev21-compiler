package prev.phase.memory;

import prev.data.ast.tree.decl.AstDecl;
import prev.data.ast.tree.decl.AstFunDecl;
import prev.data.ast.tree.decl.AstParDecl;
import prev.data.ast.tree.decl.AstVarDecl;
import prev.data.ast.tree.expr.AstAtomExpr;
import prev.data.ast.tree.expr.AstCallExpr;
import prev.data.ast.tree.expr.AstExpr;
import prev.data.ast.tree.type.AstRecType;
import prev.data.ast.visitor.AstFullVisitor;
import prev.data.mem.*;
import prev.data.typ.SemChar;
import prev.data.typ.SemType;
import prev.phase.seman.SemAn;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, MemEvaluator.Mode> {

    public int depth = -1;
    public LinkedList<FunVars> list = new LinkedList<>();
    public HashSet<AstFunDecl> visitedFuns = new HashSet<>();

    @Override
    public Object visit(AstFunDecl funDecl, Mode mode) {
        if (mode != Mode.FIRST) return null;
        if (funDecl.expr == null) return null;
        if (visitedFuns.contains(funDecl)) return null;

        // mark fun as visited
        visitedFuns.add(funDecl);

        list.add(new FunVars());
        ++depth;

        funDecl.expr.accept(this, Mode.TKO);
        funDecl.pars.accept(this, Mode.TKO);

        MemFrame frame = new MemFrame(new MemLabel(), depth, list.get(depth).locSize, list.get(depth).argSize);
        Memory.frames.put(funDecl, frame);

        super.visit(funDecl, mode);

        --depth;
        list.pop();

        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Mode mode) {
        super.visit(varDecl, mode);
        if (mode == Mode.TKO) {
            SemType type = SemAn.isType.get(varDecl.type);

            // prištejemo takoj FP - offset
            list.get(depth).locSize += type.size();

            MemRelAccess ma = new MemRelAccess(type.size(), -list.get(depth).locSize, depth);
            Memory.accesses.put(varDecl, ma);
        }

        if (mode == Mode.GLOBAL_VARIABLES) {
            SemType type = SemAn.isType.get(varDecl.type);

            MemAbsAccess ma = new MemAbsAccess(type.size(), new MemLabel());
            Memory.accesses.put(varDecl, ma);
        }

        return null;
    }

    @Override
    public Object visit(AstRecType recType, Mode mode) {
        super.visit(recType, mode);

        long offset = 0;
        for (var acd : recType.comps) {
            SemType type = SemAn.isType.get(acd.type);

            MemAccess ma = new MemRelAccess(type.size(), offset, 0);
            offset += type.size();

            Memory.accesses.put(acd, ma);
        }

        return null;
    }

    @Override
    public Object visit(AstParDecl parDecl, Mode mode) {
        super.visit(parDecl, mode);
        if (mode == Mode.TKO) {
            SemType type = SemAn.isType.get(parDecl.type);
            list.get(depth).parSize += type.size();

            MemRelAccess ma = new MemRelAccess(type.size(), list.get(depth).parSize, depth);
            Memory.accesses.put(parDecl, ma);
        }
        return null;
    }

    @Override
    public Object visit(AstCallExpr callExpr, Mode mode) {
        super.visit(callExpr, mode);
        if (mode == Mode.TKO) {
            int funSize = 0;

            for (AstExpr ae : callExpr.args) {
                SemType argType = SemAn.ofType.get(ae);
                funSize += argType.size();
            }
            list.get(depth).argSize = Math.max(list.get(depth).argSize, funSize);

            SemType resultType = SemAn.ofType.get(callExpr);

            list.get(depth).argSize = Math.max(list.get(depth).argSize, resultType.size());

            AstDecl type = SemAn.declaredAt.get(callExpr);
            if (type instanceof AstFunDecl)
                this.visit(((AstFunDecl) type), Mode.FIRST);
        }
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Mode mode) {
        super.visit(atomExpr, mode);
        if (mode != Mode.GLOBAL_VARIABLES) ;
        if (atomExpr.type != AstAtomExpr.Type.STRING) return null;
        if (Memory.strings.get(atomExpr) != null) return null;

        long size = (new SemChar().size()) * (atomExpr.value.length());
        MemAbsAccess maa = new MemAbsAccess(size, new MemLabel(), atomExpr.value);
        Memory.strings.put(atomExpr, maa);

        return null;
    }

    public enum Mode {
        FIRST, TKO, GLOBAL_VARIABLES
    }

    public class FunVars {
        public long parSize = 0;
        public long locSize = 0;
        public long argSize = 0;
    }
}
