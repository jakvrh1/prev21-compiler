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
        Vector<AsmInstr> instr = new Vector<>();
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemLabel> jumps = new Vector<>();
        String INSTR_NAME = "BP `s0, " + cjump.posLabel.name;

        MemTemp mt = cjump.cond.accept(new ExprGenerator(), instr);
        uses.add(mt);
        jumps.add(cjump.posLabel);

        instr.add(new AsmOPER(INSTR_NAME, uses, null, jumps));

        return instr;
    }

    @Override
    public Vector<AsmInstr> visit(ImcESTMT eStmt, Object visArg) {
        Vector<AsmInstr> instr = new Vector<>();
        eStmt.expr.accept(new ExprGenerator(), instr);
        return instr;
    }

    @Override
    public Vector<AsmInstr> visit(ImcJUMP jump, Object visArg) {
        Vector<AsmInstr> instr = new Vector<>();
        Vector<MemLabel> jumps = new Vector<>();
        String INSTR_NAME = "JMP " + jump.label.name;
        jumps.add(jump.label);
        instr.add(new AsmOPER(INSTR_NAME, null, null, jumps));
        return instr;
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
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();

        MemTemp src_mt = move.src.accept(new ExprGenerator(), instr);
        MemTemp dst_mt = move.dst.accept(new ExprGenerator(), instr);

        uses.add(src_mt);
        defs.add(dst_mt);

        if(move.dst instanceof ImcTEMP) {
            String INSTR_NAME = "SET `d0, `s0";
            instr.add(new AsmMOVE(INSTR_NAME, uses, defs));
        } else {
            String INSTR_NAME = "STO `d0, ˙s0, 0";
            instr.add(new AsmMOVE(INSTR_NAME, uses, defs));
        }

        return instr;
    }

    @Override
    public Vector<AsmInstr> visit(ImcSTMTS stmts, Object visArg) {
        return null;
    }

}
