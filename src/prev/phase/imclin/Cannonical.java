package prev.phase.imclin;

import prev.common.report.Report;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.mem.MemTemp;

import java.util.Vector;

public class Cannonical {

    public ImcExpr can(ImcExpr e, Vector<ImcStmt> v) {
        if (e instanceof ImcBINOP)
            return binExpr((ImcBINOP) e, v);
        if (e instanceof ImcCALL)
            return callExpr((ImcCALL) e, v);
        if (e instanceof ImcCONST)
            return constExpr((ImcCONST) e, v);
        if (e instanceof ImcMEM)
            return memExpr((ImcMEM) e, v);
        if (e instanceof ImcNAME)
            return nameExpr((ImcNAME) e, v);
        if (e instanceof ImcSEXPR)
            return stmtSexpr((ImcSEXPR) e, v);
        if (e instanceof ImcTEMP)
            return tempExpr((ImcTEMP) e, v);
        if (e instanceof ImcUNOP)
            return unOpExpr((ImcUNOP) e, v);

        throw new Report.InternalError();
    }

    public void can(ImcStmt s, Vector<ImcStmt> v) {
        if (s instanceof ImcCJUMP)
            cjumpStmt((ImcCJUMP) s, v);
        else if (s instanceof ImcESTMT)
            eStmt((ImcESTMT) s, v);
        else if (s instanceof ImcJUMP)
            jumpStmt((ImcJUMP) s, v);
        else if (s instanceof ImcLABEL)
            labelStmt((ImcLABEL) s, v);
        else if (s instanceof ImcMOVE)
            moveStmt((ImcMOVE) s, v);
        else if (s instanceof ImcSTMTS)
            vecStmts((ImcSTMTS) s, v);
        else
            throw new Report.InternalError();
    }

    public ImcExpr binExpr(ImcBINOP binop, Vector<ImcStmt> v) {
        ImcExpr ef = can(binop.fstExpr, v);
        ImcExpr es = can(binop.sndExpr, v);
        return new ImcBINOP(binop.oper, ef, es);
    }

    public ImcExpr constExpr(ImcCONST cnst, Vector<ImcStmt> v) {
        return cnst;
    }

    private ImcExpr callExpr(ImcCALL call, Vector<ImcStmt> v) {
        Vector<ImcExpr> args = new Vector<>();

        for (ImcExpr arg : call.args) {
            ImcExpr e = can(arg, v);
            if (arg instanceof ImcCALL) {
                args.add(e);
            } else {
                ImcTEMP t = new ImcTEMP(new MemTemp());
                ImcMOVE m = new ImcMOVE(t, e);
                args.add(t);
                v.add(m);
            }
        }

        ImcTEMP t = new ImcTEMP(new MemTemp());
        ImcCALL c = new ImcCALL(call.label, call.offs, args);
        ImcMOVE m = new ImcMOVE(t, c);
        v.add(m);

        return t;
    }

    public ImcExpr memExpr(ImcMEM mem, Vector<ImcStmt> v) {
        return mem;
    }

    public ImcExpr nameExpr(ImcNAME name, Vector<ImcStmt> v) {
        return name;
    }

    public ImcExpr tempExpr(ImcTEMP t, Vector<ImcStmt> v) {
        return t;
    }

    public ImcExpr unOpExpr(ImcUNOP upe, Vector<ImcStmt> v) {
        ImcExpr e = can(upe.subExpr, v);
        return new ImcUNOP(upe.oper, e);
    }

    public ImcExpr stmtSexpr(ImcSEXPR stmt, Vector<ImcStmt> v) {
        can(stmt.stmt, v);
        return can(stmt.expr, v);
    }

    public void cjumpStmt(ImcCJUMP stmt, Vector<ImcStmt> v) {
        ImcExpr e = can(stmt.cond, v);
        v.add(new ImcCJUMP(e, stmt.posLabel, stmt.negLabel));
    }

    public void eStmt(ImcESTMT stmt, Vector<ImcStmt> v) {
        ImcExpr e = can(stmt.expr, v);
        v.add(new ImcESTMT(e));
    }

    public void jumpStmt(ImcJUMP stmt, Vector<ImcStmt> v) {
        v.add(stmt);
    }

    public void labelStmt(ImcLABEL stmt, Vector<ImcStmt> v) {
        v.add(stmt);
    }

    public void moveStmt(ImcMOVE stmt, Vector<ImcStmt> v) {
        ImcExpr se = can(stmt.src, v);
        ImcExpr de = can(stmt.dst, v);

        v.add(new ImcMOVE(de, se));
    }

    public void vecStmts(ImcSTMTS stmt, Vector<ImcStmt> v) {
        for (ImcStmt s : stmt.stmts)
            can(s, v);
    }
}
