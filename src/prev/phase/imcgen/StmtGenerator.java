package prev.phase.imcgen;

import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.tree.stmt.AstExprStmt;
import prev.data.ast.tree.stmt.AstIfStmt;
import prev.data.ast.tree.stmt.AstWhileStmt;
import prev.data.ast.visitor.AstVisitor;
import prev.data.imc.code.expr.ImcExpr;
import prev.data.imc.code.stmt.*;
import prev.data.mem.MemFrame;
import prev.data.mem.MemLabel;

import java.util.Stack;
import java.util.Vector;

public class StmtGenerator implements AstVisitor<ImcStmt, Stack<MemFrame>> {
    // ST1
    @Override
    public ImcStmt visit(AstExprStmt exprStmt, Stack<MemFrame> memFrames) {
        ImcGen.stmtImc.put(exprStmt, new ImcESTMT(ImcGen.exprImc.get(exprStmt.expr)));
        return null;
    }

    // ST2
    @Override
    public ImcStmt visit(AstAssignStmt assignStmt, Stack<MemFrame> memFrames) {
        ImcExpr dst = ImcGen.exprImc.get(assignStmt.dst);
        ImcExpr src = ImcGen.exprImc.get(assignStmt.src);
        ImcGen.stmtImc.put(assignStmt, new ImcMOVE(dst, src));
        return null;
    }

    // ST3 ST4
    @Override
    public ImcStmt visit(AstIfStmt ifStmt, Stack<MemFrame> memFrames) {
        Vector<ImcStmt> stmts = new Vector<>();

        MemLabel pos = new MemLabel();
        MemLabel neg = new MemLabel();
        MemLabel end = new MemLabel();

        stmts.add(new ImcCJUMP(ImcGen.exprImc.get(ifStmt.cond), pos, neg));
        stmts.add(new ImcLABEL(pos));
        stmts.add(ImcGen.stmtImc.get(ifStmt.thenStmt));
        stmts.add(new ImcJUMP(end));
        stmts.add(new ImcLABEL(neg));
        stmts.add(ImcGen.stmtImc.get(ifStmt.elseStmt));
        stmts.add(new ImcLABEL(end));

        ImcGen.stmtImc.put(ifStmt, new ImcSTMTS(stmts));
        return null;
    }

    // ST5 ST6
    @Override
    public ImcStmt visit(AstWhileStmt whileStmt, Stack<MemFrame> memFrames) {
        Vector<ImcStmt> stmts = new Vector<>();

        MemLabel start = new MemLabel();
        MemLabel pos = new MemLabel();
        MemLabel end = new MemLabel();

        stmts.add(new ImcLABEL(start));
        stmts.add(new ImcCJUMP(ImcGen.exprImc.get(whileStmt.cond), pos, end));
        stmts.add(new ImcLABEL(pos));
        stmts.add(ImcGen.stmtImc.get(whileStmt.bodyStmt));
        stmts.add(new ImcJUMP(start));
        stmts.add(new ImcLABEL(end));

        ImcGen.stmtImc.put(whileStmt, new ImcSTMTS(stmts));
        return null;
    }
}
