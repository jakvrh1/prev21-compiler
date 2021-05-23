package prev.phase.asmgen;

import prev.Compiler;
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
            instr.set(0, "CMP");
            MemTemp equ_mt = addInstruction(instr, binOp, visArg);

            uses.add(equ_mt);
            defs.add(equ_mt);

            visArg.add(new AsmOPER("ZSN `d0,`s0,1", uses, defs, null));

            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.GTH) {
            instr.set(0, "CMP");
            MemTemp equ_mt = addInstruction(instr, binOp, visArg);

            uses.add(equ_mt);
            defs.add(equ_mt);

            visArg.add(new AsmOPER("ZSP `d0,`s0,1", uses, defs, null));

            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.LEQ) {
            instr.set(0, "CMP");
            MemTemp equ_mt = addInstruction(instr, binOp, visArg);

            uses.add(equ_mt);
            defs.add(equ_mt);

            visArg.add(new AsmOPER("ZSNP `d0,`s0,1", uses, defs, null));

            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.GEQ) {
            instr.set(0, "CMP");
            MemTemp equ_mt = addInstruction(instr, binOp, visArg);

            uses.add(equ_mt);
            defs.add(equ_mt);

            visArg.add(new AsmOPER("ZSNN `d0,`s0,1", uses, defs, null));

            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.MOD) {
            instr.set(0, "DIV");
            addInstruction(instr, binOp, visArg);

            defs.add(new MemTemp());
            visArg.add(new AsmOPER("GET `d0,rR", null, defs, null));

            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.EQU) {
            instr.set(0, "CMP");
            MemTemp equ_mt = addInstruction(instr, binOp, visArg);

            uses.add(equ_mt);
            defs.add(equ_mt);

            visArg.add(new AsmOPER("ZSZ `d0,`s0,1", uses, defs, null));

            return defs.lastElement();
        } else if (binOp.oper == ImcBINOP.Oper.NEQ) {
            instr.set(0, "CMP");
            MemTemp equ_mt = addInstruction(instr, binOp, visArg);

            uses.add(equ_mt);
            defs.add(equ_mt);

            visArg.add(new AsmOPER("ZSNZ `d0,`s0,1", uses, defs, null));

            return defs.lastElement();
        } else {
            switch (binOp.oper) {
                case OR -> instr.set(0, "OR");
                case AND -> instr.set(0, "AND");
                //case EQU -> instr.set(0, "NXOR");
                //case NEQ -> instr.set(0, "XOR");
                case ADD -> instr.set(0, "ADD");
                case SUB -> instr.set(0, "SUB");
                case MUL -> instr.set(0, "MUL");
                case DIV -> instr.set(0, "DIV");
            }

            return addInstruction(instr, binOp, visArg);
        }

    }

    public MemTemp createConstant(long value, Vector<AsmInstr> visArg) {
        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());

        boolean isNegative = (value < 0);

        if (isNegative) value *= -1;


        //visArg.add(new AsmOPER(String.format("AND `d0,$255,%d", 0), null, defs, null));

        visArg.add(new AsmOPER(String.format("SETL `d0,%d", value & 0x000000000000FFFFL), null, defs, null));
        if ((value & 0x00000000FFFF0000L) > 0)
            visArg.add(new AsmOPER(String.format("INCML `d0,%d", ((value & 0x00000000FFFF0000L) >> 4 * 4)), null, defs, null));
        if ((value & 0x0000FFFF00000000L) > 0)
            visArg.add(new AsmOPER(String.format("INCMH `d0,%d", ((value & 0x0000FFFF00000000L) >> 8 * 4)), null, defs, null));
        if ((value & 0xFFFF000000000000L) > 0)
            visArg.add(new AsmOPER(String.format("INCH `d0,%d", ((value & 0xFFFF000000000000L) >> 12 * 4)), null, defs, null));

        if (isNegative) {
            Vector<MemTemp> uses = new Vector<>();
            uses.add(defs.lastElement());
            defs = new Vector<>();
            defs.add(new MemTemp());

            visArg.add(new AsmOPER("NEG `d0,`s0", uses, defs, null));
            return defs.lastElement();
        }

        return defs.lastElement();
    }

    public MemTemp addInstruction(Vector<String> instr, ImcBINOP binOp, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        MemTemp mt = new MemTemp();
        defs.add(mt);

        uses.add(binOp.fstExpr.accept(this, visArg));

        if (binOp.sndExpr instanceof ImcCONST && ((ImcCONST) binOp.sndExpr).value >= 0 && ((ImcCONST) binOp.sndExpr).value <= 255)
            instr.set(3, "" + ((ImcCONST) binOp.sndExpr).value);
        else
            uses.add(binOp.sndExpr.accept(this, visArg));

        visArg.add(new AsmOPER(instr.get(0) + " " + instr.get(1) + "," + instr.get(2) + "," + instr.get(3), uses, defs, null));

        return mt;
    }


    @Override
    public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();

        for (int i = 0; i < call.args.size(); ++i) {
            uses = new Vector<>();
            uses.add(call.args.get(i).accept(this, visArg));
            visArg.add(new AsmOPER("STO `s0,$253," + call.offs.get(i), uses, null, null));
        }

        visArg.add(new AsmOPER("PUSHJ $" + Compiler.REGISTERS + "," + call.label.name, null, null, null));

        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());

        visArg.add(new AsmOPER("LDO `d0,$253,0", null, defs, null));

        return defs.lastElement();
    }

    @Override
    public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
        boolean FLAG = StmtGenerator.IS_PARENT;
        StmtGenerator.IS_PARENT = false;
        MemTemp mt = mem.addr.accept(this, visArg);
        if (FLAG) return mt;

        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        uses.add(mt);
        defs.add(new MemTemp());

        visArg.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));

        return defs.lastElement();
    }

    @Override
    public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());
        visArg.add(new AsmOPER("LDA `d0," + name.label.name, null, defs, null));

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
                //instr = "NOR `d0,`s0,0";
                instr = "ZSZ `d0,`s0,1";
                break;
            case SUB:
                instr = "NEG `d0,`s0";
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
