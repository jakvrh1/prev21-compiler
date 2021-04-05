package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.data.imc.code.stmt.*;

public class StmtGenerator implements AstVisitor<ImcStmt, Stack<MemFrame>> {
    @Override
    public ImcStmt visit(AstAssignStmt assignStmt, Stack<MemFrame> memFrames) {

        return null;
    }
}
