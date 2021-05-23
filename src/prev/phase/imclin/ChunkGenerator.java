package prev.phase.imclin;

import prev.common.report.Report;
import prev.data.ast.tree.decl.AstFunDecl;
import prev.data.ast.tree.decl.AstVarDecl;
import prev.data.ast.tree.expr.AstAtomExpr;
import prev.data.ast.visitor.AstFullVisitor;
import prev.data.imc.code.expr.ImcExpr;
import prev.data.imc.code.expr.ImcTEMP;
import prev.data.imc.code.stmt.*;
import prev.data.lin.LinCodeChunk;
import prev.data.lin.LinDataChunk;
import prev.data.mem.MemAbsAccess;
import prev.data.mem.MemAccess;
import prev.data.mem.MemFrame;
import prev.data.mem.MemLabel;
import prev.phase.imcgen.ImcGen;
import prev.phase.memory.Memory;

import java.util.HashSet;
import java.util.Vector;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {
    @Override
    public Object visit(AstAtomExpr atomExpr, Object o) {
        super.visit(atomExpr, o);
        switch (atomExpr.type) {
            case STRING:
                ImcLin.addDataChunk(new LinDataChunk(Memory.strings.get(atomExpr)));
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Object o) {
        super.visit(varDecl, o);

        MemAccess ma = Memory.accesses.get(varDecl);
        if (ma instanceof MemAbsAccess)
            ImcLin.addDataChunk(new LinDataChunk((MemAbsAccess) ma));

        return null;
    }

    @Override
    public Object visit(AstFunDecl funDecl, Object o) {
        super.visit(funDecl, o);

        if (funDecl.expr == null)
            return null;

        MemFrame frame = Memory.frames.get(funDecl);
        MemLabel entry = new MemLabel();
        MemLabel exit = new MemLabel();

        ImcExpr root = ImcGen.exprImc.get(funDecl.expr);
        Vector<ImcStmt> body = new Vector<>();

        body.add(new ImcLABEL(entry));
        Vector<ImcStmt> vecStmts = new Vector<>();
        Cannonical can = new Cannonical();
        ImcExpr e = can.can(root, vecStmts);
        body.addAll(vecStmts);
        body.add(new ImcMOVE(new ImcTEMP(frame.RV), e));
        body.add(new ImcJUMP(exit));

        Vector<ImcStmt> basic_body = new Vector<>();
        basicCan(0, body, basic_body);

        Vector<Vector<ImcStmt>> listOfBlocks = createListOfBlocks(basic_body);

        Vector<ImcStmt> listOfTBlocks = traces(listOfBlocks);

        for (int i = 1; i < listOfTBlocks.size(); ++i) {
            if (listOfTBlocks.get(i - 1) instanceof ImcJUMP && listOfTBlocks.get(i) instanceof ImcLABEL) {
                if (((ImcJUMP) listOfTBlocks.get(i - 1)).label == ((ImcLABEL) listOfTBlocks.get(i)).label) {
                    listOfTBlocks.remove(i - 1);
                }
            }
        }

        ImcLin.addCodeChunk(new LinCodeChunk(frame, listOfTBlocks, entry, exit));

        return null;
    }

    public Vector<ImcStmt> traces(Vector<Vector<ImcStmt>> Q) {
        HashSet<MemLabel> marked = new HashSet<>();
        Vector<ImcStmt> res = new Vector<>();

        while (!Q.isEmpty()) {
            Vector<Vector<ImcStmt>> T = new Vector<>();
            Vector<ImcStmt> b = Q.firstElement();
            Q.remove(0);

            if (!(b.firstElement() instanceof ImcLABEL)) throw new Report.InternalError();

            MemLabel ml_b = ((ImcLABEL) b.firstElement()).label;

            while (!marked.contains(ml_b)) {
                marked.add(ml_b);
                T.add(b);

                if (b.lastElement() instanceof ImcJUMP && !marked.contains(((ImcJUMP) b.lastElement()).label)) {
                    ml_b = ((ImcJUMP) b.lastElement()).label;
                    b = getBlock(ml_b, Q);
                } else if (b.lastElement() instanceof ImcCJUMP) {
                    ml_b = ((ImcCJUMP) b.lastElement()).negLabel;
                    b = getBlock(ml_b, Q);
                }
                if (b == null) {
                    break;
                }
            }

            for (var a : T) {
                res.addAll(a);
            }
        }

        return res;
    }

    public Vector<ImcStmt> getBlock(MemLabel ml, Vector<Vector<ImcStmt>> Q) {
        for (Vector<ImcStmt> a : Q) {
            if (!(a.firstElement() instanceof ImcLABEL)) throw new Report.InternalError();
            if (((ImcLABEL) a.firstElement()).label == ml) {
                Q.remove(a);
                return a;
            }
        }
        return null;
    }

    public Vector<Vector<ImcStmt>> createListOfBlocks(Vector<ImcStmt> body) {
        Vector<Vector<ImcStmt>> a = new Vector<>();
        Vector<ImcStmt> b = new Vector<>();

        for (int i = 0; i < body.size(); ++i) {
            b.add(body.get(i));
            if (body.get(i) instanceof ImcJUMP || body.get(i) instanceof ImcCJUMP) {
                a.add(b);
                b = new Vector<>();
            }
        }

        return a;
    }


    public void basicCan(int i, Vector<ImcStmt> old_, Vector<ImcStmt> new_) {
        if (i >= old_.size()) return;
        new_.add(old_.get(i));
        ++i;
        while (i < old_.size()) {

            if (old_.get(i) instanceof ImcLABEL && (old_.get(i - 1) instanceof ImcJUMP || old_.get(i - 1) instanceof ImcCJUMP)) {
                basicCan(i, old_, new_);
                return;
            } else if (old_.get(i) instanceof ImcLABEL) {
                new_.add(new ImcJUMP(((ImcLABEL) old_.get(i)).label));
                basicCan(i, old_, new_);
                return;
            }
            new_.add(old_.get(i));
            ++i;
        }
    }


}

