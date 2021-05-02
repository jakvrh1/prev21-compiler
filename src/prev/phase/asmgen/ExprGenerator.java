package prev.phase.asmgen;

import prev.data.asm.AsmInstr;
import prev.data.asm.AsmOPER;
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

        if (binOp.oper == ImcBINOP.Oper.LTH) {
            instr.set(0, "SUB");
            MemTemp sub_mt = addInstruction(instr, binOp, visArg, uses, defs);

            uses.add(sub_mt);
            uses.add(createConstant(-1, visArg));
            defs.add(new MemTemp());

            visArg.add(new AsmOPER("MUL `d0, `s0, `s1", uses, defs, null));
            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.GTH) {
            instr.set(0, "SUB");
            return addInstruction(instr, binOp, visArg, uses, defs);
        } else if (binOp.oper == ImcBINOP.Oper.LEQ) {
            instr.set(0, "SUB");
            MemTemp sub_mt = addInstruction(instr, binOp, visArg, uses, defs);

            uses.add(sub_mt);
            defs.add(new MemTemp());

            visArg.add(new AsmOPER("CMP `d0, `s0, 1", uses, defs, null));
            MemTemp cmp_mt = defs.lastElement();

            uses = new Vector<>();
            defs = new Vector<>();

            uses.add(cmp_mt);
            uses.add(createConstant(-1, visArg));
            defs.add(new MemTemp());

            visArg.add(new AsmOPER("MUL `d0, `s0, `s1", uses, defs, null));
            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.GEQ) {
            instr.set(0, "SUB");
            MemTemp cmp_mt = addInstruction(instr, binOp, visArg, uses, defs);

            uses.add(cmp_mt);
            uses.add(createConstant(-1, visArg));
            defs.add(new MemTemp());

            visArg.add(new AsmOPER("CMP `d0, `s0, `s1", uses, defs, null));
            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.MOD) {
            instr.set(0, "DIV");
            addInstruction(instr, binOp, visArg, uses, defs);

            defs.add(new MemTemp());
            visArg.add(new AsmOPER("GET `d0, rR", null, defs, null));

            return defs.lastElement();
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

    }

    public MemTemp createConstant(long value, Vector<AsmInstr> visArg) {
        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());

        boolean isNegative = (value < 0);

        if (isNegative) value *= -1;

        visArg.add(new AsmOPER(String.format("SETL `d0, %d", value & 0x000000000000FFFFL), null, defs, null));
        if ((value & 0x00000000FFFF0000L) > 0)
            visArg.add(new AsmOPER(String.format("INCML `d0, %d", value & 0x00000000FFFF0000L), null, defs, null));
        if ((value & 0x0000FFFF00000000L) > 0)
            visArg.add(new AsmOPER(String.format("INCMH `d0, %d", value & 0x0000FFFF00000000L), null, defs, null));
        if ((value & 0xFFFF000000000000L) > 0)
            visArg.add(new AsmOPER(String.format("INCH `d0, %d", value & 0xFFFF000000000000L), null, defs, null));

        if (isNegative) {
            Vector<MemTemp> uses = new Vector<>();
            uses.add(defs.lastElement());
            defs = new Vector<>();
            defs.add(new MemTemp());

            visArg.add(new AsmOPER("NEG `d0, `s0", uses, defs, null));
            return defs.lastElement();
        }

        return defs.lastElement();
    }

    public MemTemp addInstruction(Vector<String> instr, ImcBINOP binOp, Vector<AsmInstr> visArg, Vector<MemTemp> uses, Vector<MemTemp> defs) {
        MemTemp mt = new MemTemp();
        defs.add(mt);

        uses.add(binOp.fstExpr.accept(this, visArg));

        if (binOp.sndExpr instanceof ImcCONST && ((ImcCONST) binOp.sndExpr).value >= 0 && ((ImcCONST) binOp.sndExpr).value <= 255)
            instr.set(3, "" + ((ImcCONST) binOp.sndExpr).value);
        else
            uses.add(binOp.sndExpr.accept(this, visArg));

        visArg.add(new AsmOPER(instr.get(0) + " " + instr.get(1) + ", " + instr.get(2) + ", " + instr.get(3), uses, defs, null));
        uses = new Vector<>();
        defs = new Vector<>();

        return mt;
    }


    @Override
    public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();

        for(int i = 0; i < call.args.size(); ++i) {
            uses = new Vector<>();
            uses.add(call.args.get(i).accept(this, visArg));
            visArg.add(new AsmOPER("STO `s0, $253, " + call.offs.get(i), uses, null, null));
        }
        uses = new Vector<>();
        MemTemp x = createConstant(call.args.size(), visArg);
        uses.add(x);
        visArg.add(new AsmOPER("PUSHJ `s0, " + call.label.name, uses, null, null));

        uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());

        visArg.add(new AsmOPER("LDO `d0, `s0, 0", uses, defs, null));

        return defs.lastElement();
    }

    @Override
    public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
        MemTemp mt = mem.addr.accept(this, visArg);
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        uses.add(mt);
        defs.add(new MemTemp());

        visArg.add(new AsmOPER("LDO `d0, `s0, 0", uses, defs, null));

        return defs.lastElement();
    }

    @Override
    public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());
        visArg.add(new AsmOPER("LDA `d0, " + name.label.name, null, defs, null));
        visArg.add(new AsmOPER("LDO `d0, `s0, 0", defs, defs, null));

        return defs.lastElement();
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
            case SUB:
                instr = "NEG `d0, `s0";
                break;
        }

        MemTemp mt = unOp.subExpr.accept(this, visArg);
        uses.add(mt);
        defs.add(new MemTemp());
        visArg.add(new AsmOPER(instr, uses, defs, null));

        return defs.lastElement();
    }

    @Override
    public MemTemp visit(ImcCONST constant, Vector<AsmInstr> visArg) {
        return createConstant(constant.value, visArg);
    }
}
