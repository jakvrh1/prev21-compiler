package prev.phase.imclin;

import java.util.*;

import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.lin.*;
import prev.phase.imcgen.*;
import prev.phase.memory.*;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {
    @Override
    public Object visit(AstAtomExpr atomExpr, Object o) {
        super.visit(atomExpr, o);
        switch(atomExpr.type) {
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
        if(ma instanceof MemAbsAccess)
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
        body.addAll(cExpr(root, frame));
        body.add(new ImcJUMP(exit));

        ImcLin.addCodeChunk(new LinCodeChunk(frame, body, entry, exit));

        return null;
    }


    private Vector<ImcStmt> cExpr(ImcExpr expr, MemFrame fr) {
        Vector<ImcStmt> stmts = new Vector<>();
        if(expr instanceof ImcSEXPR) {
            ImcSEXPR sexpr = (ImcSEXPR) expr;
            stmts.addAll(cStmt(sexpr.stmt, fr));
            stmts.addAll(cExpr(sexpr.expr, fr));
        } else if(expr instanceof ImcCALL) {
            ImcCALL callExpr = (ImcCALL) expr;
            stmts.addAll(cCall(callExpr, fr));
        } else {
            stmts.add(new ImcMOVE(new ImcTEMP(fr.RV), expr));
        }
        return stmts;
    }

    private Vector<ImcStmt> cCall(ImcCALL call, MemFrame fr) {
        Vector<ImcStmt> callStmts = new Vector<>();
        Vector<ImcExpr> args = new Vector<>();

        for(ImcExpr arg : call.args) {
            if(arg instanceof ImcCALL) {
                Vector<ImcStmt> sCall = cCall((ImcCALL) arg, fr);
                callStmts.addAll(sCall);
                ImcStmt res = sCall.lastElement();
                if(res instanceof ImcMOVE) {
                    ImcExpr e = ((ImcMOVE) res).dst;
                    if(e instanceof ImcTEMP) {
                        args.add(e);
                    }
                }
            } else {
                ImcTEMP t = new ImcTEMP(new MemTemp());
                ImcMOVE m = new ImcMOVE(t, arg);
                callStmts.add(m);
                args.add(t);
            }
        }

        ImcTEMP t = new ImcTEMP(new MemTemp());
        ImcCALL c = new ImcCALL(call.label, call.offs, args);

        callStmts.add(new ImcMOVE(t, c));
        return callStmts;
    }

    private Vector<ImcStmt> cStmt(ImcStmt stmt, MemFrame fr) {
        Vector<ImcStmt> stmts = new Vector<>();
        if(stmt instanceof ImcSTMTS) {
            for(int i = 0; i < ((ImcSTMTS) stmt).stmts.size(); ++i) {
                stmts.addAll(cStmt(((ImcSTMTS) stmt).stmts.get(i), fr));
            }
        } else if(stmt instanceof ImcESTMT) {
            ImcExpr e = ((ImcESTMT) stmt).expr;
            stmts.addAll(cExpr(e, fr));
        } else {
            stmts.add(stmt);
        }
        return stmts;
    }
}

