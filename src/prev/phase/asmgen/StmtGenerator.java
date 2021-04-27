package prev.phase.asmgen;

import java.util.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;
import prev.data.mem.*;
import prev.data.asm.*;
import prev.common.report.*;

/**
 * Machine code generator for statements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {

    @Override
    public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
        return null;
    }

    @Override
    public Vector<AsmInstr> visit(ImcESTMT eStmt, Object visArg) {
        return null;
    }

    @Override
    public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
        return null;
    }

    @Override
    public Vector<AsmInstr> visit(ImcLABEL label, Object visArg) {
        Vector<AsmInstr> instr = new Vector<>();
        instr.add(new AsmLABEL(label.label));
        return instr;
    }

    @Override
    public Vector<AsmInstr> visit(ImcMOVE move, Object visArg) {
        Vector<AsmInstr> instr = new Vector<>();
        String INSTR_NAME = "SET";

        Vector<MemTemp> uses = new Vector<MemTemp>();
        if(move.src instanceof ImcTEMP)
            uses.add(((ImcTEMP) move.src).temp);

        Vector<MemTemp> defs = new Vector<MemTemp>();
        if(move.dst instanceof ImcTEMP)
            defs.add(((ImcTEMP) move.dst).temp);

        AsmMOVE ai = new AsmMOVE(INSTR_NAME, uses, defs);

        instr.add(ai);
        return instr;
    }

    @Override
    public Vector<AsmInstr> visit(ImcSTMTS stmts, Object visArg) {
        return null;
    }
}
