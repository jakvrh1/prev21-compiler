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
        Vector<ImcStmt> vecStmts = new Vector<>();
        Cannonical can = new Cannonical();
        ImcExpr e = can.can(root, vecStmts);
        body.addAll(vecStmts);
        body.add(new ImcMOVE(new ImcTEMP(frame.RV), e));
        body.add(new ImcJUMP(exit));

        ImcLin.addCodeChunk(new LinCodeChunk(frame, body, entry, exit));

        return null;
    }

}

