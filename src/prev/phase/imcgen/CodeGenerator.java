package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.AstAssignStmt;
import prev.data.ast.tree.stmt.AstExprStmt;
import prev.data.ast.tree.stmt.AstIfStmt;
import prev.data.ast.tree.stmt.AstWhileStmt;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.phase.memory.*;

public class CodeGenerator extends AstFullVisitor<Object, Stack<MemFrame>> {
    public ExprGenerator eg = new ExprGenerator();
    public StmtGenerator sg = new StmtGenerator();

    // EXPRESSIONS
    @Override
    public Object visit(AstFunDecl funDecl, Stack<MemFrame> memFrames) {
        memFrames.push(Memory.frames.get(funDecl));
        super.visit(funDecl, memFrames);
        memFrames.pop();
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Stack<MemFrame> memFrames) {
        return eg.visit(atomExpr, memFrames);
    }

    @Override
    public Object visit(AstBinExpr binExpr, Stack<MemFrame> memFrames) {
        super.visit(binExpr, memFrames);
        return eg.visit(binExpr, memFrames);
    }

    @Override
    public Object visit(AstPfxExpr pfxExpr, Stack<MemFrame> memFrames) {
        super.visit(pfxExpr, memFrames);
        return eg.visit(pfxExpr, memFrames);
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Stack<MemFrame> memFrames) {
        super.visit(nameExpr, memFrames);
        return eg.visit(nameExpr, memFrames);
    }

    @Override
    public Object visit(AstArrExpr arrExpr, Stack<MemFrame> memFrames) {
        super.visit(arrExpr, memFrames);
        return eg.visit(arrExpr, memFrames);
    }

    @Override
    public Object visit(AstSfxExpr sfxExpr, Stack<MemFrame> memFrames) {
        super.visit(sfxExpr, memFrames);
        return eg.visit(sfxExpr, memFrames);
    }

    @Override
    public Object visit(AstRecExpr recExpr, Stack<MemFrame> memFrames) {
        super.visit(recExpr, memFrames);
        return eg.visit(recExpr, memFrames);
    }

    @Override
    public Object visit(AstCallExpr callExpr, Stack<MemFrame> memFrames) {
        super.visit(callExpr, memFrames);
        return eg.visit(callExpr, memFrames);
    }

    @Override
    public Object visit(AstCastExpr castExpr, Stack<MemFrame> memFrames) {
        super.visit(castExpr, memFrames);
        return eg.visit(castExpr, memFrames);
    }

    @Override
    public Object visit(AstWhereExpr whereExpr, Stack<MemFrame> memFrames) {
        super.visit(whereExpr, memFrames);
        return eg.visit(whereExpr, memFrames);
    }

    @Override
    public Object visit(AstStmtExpr stmtExpr, Stack<MemFrame> memFrames) {
        super.visit(stmtExpr, memFrames);
        return eg.visit(stmtExpr, memFrames);
    }


    // STATEMENTS

    @Override
    public Object visit(AstExprStmt exprStmt, Stack<MemFrame> memFrames) {
        super.visit(exprStmt, memFrames);
        return sg.visit(exprStmt, memFrames);
    }

    @Override
    public Object visit(AstAssignStmt assignStmt, Stack<MemFrame> memFrames) {
        super.visit(assignStmt, memFrames);
        return sg.visit(assignStmt, memFrames);
    }

    @Override
    public Object visit(AstIfStmt ifStmt, Stack<MemFrame> memFrames) {
        super.visit(ifStmt, memFrames);
        return sg.visit(ifStmt, memFrames);
    }

    @Override
    public Object visit(AstWhileStmt whileStmt, Stack<MemFrame> memFrames) {
        super.visit(whileStmt, memFrames);
        return sg.visit(whileStmt, memFrames);
    }
}
