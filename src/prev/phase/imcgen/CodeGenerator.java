package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.phase.memory.*;

public class CodeGenerator extends AstFullVisitor<Object, Stack<MemFrame>> {
    public ExprGenerator eg = new ExprGenerator();

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
    public Object visit(AstSfxExpr sfxExpr, Stack<MemFrame> memFrames) {
        super.visit(sfxExpr, memFrames);
        return eg.visit(sfxExpr, memFrames);
    }
}
