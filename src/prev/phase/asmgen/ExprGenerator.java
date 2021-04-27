package prev.phase.asmgen;

import prev.common.report.Report;
import prev.data.asm.AsmInstr;
import prev.data.asm.AsmMOVE;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.ImcVisitor;
import prev.data.mem.MemTemp;

import java.util.Vector;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

    @Override
    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        Vector<String> instr = new Vector<>();
        instr.add("");
        instr.add("`d0");
        instr.add("`s0");
        instr.add("`s1");
        defs.add(new MemTemp());

        if (binOp.oper == ImcBINOP.Oper.LTH ||
                binOp.oper == ImcBINOP.Oper.GTH ||
                binOp.oper == ImcBINOP.Oper.LEQ ||
                binOp.oper == ImcBINOP.Oper.GEQ ||
                binOp.oper == ImcBINOP.Oper.MOD) {
            switch (binOp.oper) {
                case LTH:
                    instr.set(0, "SUB");
                    addInstruction(instr, binOp, visArg, uses, defs);
                    MemTemp s_mt = defs.lastElement();

                    uses = new Vector<>();
                    defs = new Vector<>();

                    uses.add(s_mt);
                    defs.add(new MemTemp());

                    visArg.add(new AsmMOVE("MUL `d0, `s0, -1", uses, defs));
                    return defs.lastElement();
                case GTH:
                    instr.set(0, "SUB");
                    addInstruction(instr, binOp, visArg, uses, defs);
                    return defs.lastElement();
                case LEQ:
                    instr.set(0, "SUB");
                    addInstruction(instr, binOp, visArg, uses, defs);
                    MemTemp s1_mt = defs.lastElement();

                    uses = new Vector<>();
                    defs = new Vector<>();

                    uses.add(s1_mt);
                    defs.add(new MemTemp());

                    visArg.add(new AsmMOVE("CMP `d0, `s0, 1", uses, defs));
                    MemTemp cmp1_mt = defs.lastElement();

                    uses = new Vector<>();
                    defs = new Vector<>();

                    uses.add(cmp1_mt);
                    defs.add(new MemTemp());

                    visArg.add(new AsmMOVE("MUL `d0, `s0, -1", uses, defs));
                    return defs.lastElement();
                case GEQ:
                    instr.set(0, "SUB");
                    addInstruction(instr, binOp, visArg, uses, defs);
                    MemTemp cmp2_mt = defs.lastElement();

                    uses = new Vector<>();
                    defs = new Vector<>();

                    uses.add(cmp2_mt);
                    defs.add(new MemTemp());

                    visArg.add(new AsmMOVE("CMP `d0, `s0, -1", uses, defs));
                    return defs.lastElement();
                case MOD:
                    instr.set(0, "DIV");
                    addInstruction(instr, binOp, visArg, uses, defs);

                    defs = new Vector<>();
                    defs.add(new MemTemp());
                    visArg.add(new AsmMOVE("GET `d0, rR", null, defs));

                    return defs.lastElement();
            }


        } else {
            switch (binOp.oper) {
                case OR -> instr.set(0, "OR");
                case AND -> instr.set(0, "AND");
                case EQU -> instr.set(0, "NXOR");
                case NEQ -> instr.set(0, "XOR");
                case ADD -> instr.set(0, "ADD");
                case SUB -> instr.set(0, "SUB");
                case MUL -> instr.set(0, "MUL");
                case DIV -> instr.set(0, "DIV");
            }

            addInstruction(instr, binOp, visArg, uses, defs);
            return defs.lastElement();
        }

        throw new Report.InternalError();
    }

    public void addInstruction(Vector<String> instr, ImcBINOP binOp, Vector<AsmInstr> visArg, Vector<MemTemp> uses, Vector<MemTemp> defs) {
        if (binOp.fstExpr instanceof ImcCONST) {
            uses.add(binOp.sndExpr.accept(this, visArg));
            visArg.add(new AsmMOVE(instr.get(0) + " " + instr.get(1) + ", " + ((ImcCONST) binOp.fstExpr).value + ", " + instr.get(3), uses, defs));
        } else if (binOp.sndExpr instanceof ImcCONST) {
            uses.add(binOp.fstExpr.accept(this, visArg));
            visArg.add(new AsmMOVE(instr.get(0) + " " + instr.get(1) + ", " + instr.get(2) + ", " + ((ImcCONST) binOp.fstExpr).value, uses, defs));
        } else {
            uses.add(binOp.sndExpr.accept(this, visArg));
            uses.add(binOp.fstExpr.accept(this, visArg));
            visArg.add(new AsmMOVE(instr.get(0) + " " + instr.get(1) + ", " + instr.get(2) + ", " + instr.get(3), uses, defs));
        }
    }

    @Override
    public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
        return null;
    }

    @Override
    public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        String instr = "LDO `d0, `s0, 0";

        MemTemp addr_mt = mem.addr.accept(this, visArg);
        uses.add(addr_mt);
        defs.add(new MemTemp());

        return defs.lastElement();
    }

    @Override
    public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();


        return null;
    }

    @Override
    public MemTemp visit(ImcSEXPR sExpr, Vector<AsmInstr> visArg) {
        return null;
    }

    @Override
    public MemTemp visit(ImcTEMP temp, Vector<AsmInstr> visArg) {
        return temp.temp;
    }

    @Override
    public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        String instr = "";
        switch (unOp.oper) {
            case NOT:
                instr = "NOR `d0, `s0, 0";
                break;
                /*
            case ADD:
                instr = "ADD `d0, `s0, 0";
                break;
                 */
            case SUB:
                instr = "NEG `d0, `s0";
                break;
        }

        MemTemp mt = unOp.subExpr.accept(this, visArg);
        uses.add(mt);
        defs.add(new MemTemp());
        visArg.add(new AsmMOVE(instr, uses, defs));

        return defs.lastElement();
    }
}
