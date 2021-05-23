package prev.phase.asmgen;

import prev.data.asm.AsmInstr;
import prev.data.asm.AsmLABEL;
import prev.data.asm.AsmMOVE;
import prev.data.asm.AsmOPER;
import prev.data.imc.code.expr.ImcMEM;
import prev.data.imc.code.expr.ImcTEMP;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.ImcVisitor;
import prev.data.mem.MemLabel;
import prev.data.mem.MemTemp;

import java.util.Vector;

/**
 * Machine code generator for statements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {

    static public boolean IS_PARENT = false;

    @Override
    public Vector<AsmInstr> visit(ImcCJUMP cjump, Object visArg) {
        Vector<AsmInstr> instr = new Vector<>();
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemLabel> jumps = new Vector<>();

        MemTemp mt = cjump.cond.accept(new ExprGenerator(), instr);

        uses.add(mt);

        jumps.add(cjump.negLabel);
        jumps.add(cjump.posLabel);

        instr.add(new AsmOPER("BP `s0," + cjump.posLabel.name, uses, null, jumps));

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
        jumps.add(jump.label);
        instr.add(new AsmOPER("JMP " + jump.label.name, null, null, jumps));
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


        if (move.dst instanceof ImcTEMP) {
            uses.add(move.src.accept(new ExprGenerator(), instr));
            defs.add(move.dst.accept(new ExprGenerator(), instr));
            instr.add(new AsmMOVE("SET `d0,`s0", uses, defs));
        } else if (move.dst instanceof ImcMEM) {
            IS_PARENT = true;
            uses.add(move.dst.accept(new ExprGenerator(), instr));
            uses.add(move.src.accept(new ExprGenerator(), instr));
            instr.add(new AsmOPER("STO `s1,`s0,0", uses, defs, null));
        }

        return instr;
    }

    @Override
    public Vector<AsmInstr> visit(ImcSTMTS stmts, Object visArg) {
        return null;
    }

}
