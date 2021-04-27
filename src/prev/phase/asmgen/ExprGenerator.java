package prev.phase.asmgen;

import java.util.*;

import prev.common.report.Report;
import prev.data.imc.code.stmt.ImcStmt;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

    @Override
    public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> visArg) {
        String instr = "";
        switch(binOp.oper) {
            case OR:
                break;
            case AND:
                instr = "AND";
                break;
            case EQU:
                break;
            case NEQ:
                break;
            case LTH:
                break;
            case GTH:
                break;
            case LEQ:
                break;
            case GEQ:
                break;
            case ADD:
                instr = "ADD `d0, `s0, ";
                break;
            case SUB:
                instr = "SUB `d0, `s0, ";
                break;
            case MUL:
                instr = "MUL `d0, `s0, ";
                break;
            case DIV:
                instr = "DIV `d0, `s0, ";
                break;
            case MOD:
                break;
        }

        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();

        MemTemp r = new MemTemp();
        defs.add(r);

        if(binOp.fstExpr instanceof ImcCONST )  {
           uses.add(binOp.sndExpr.accept(this, visArg));
           visArg.add(new AsmMOVE(instr + ((ImcCONST) binOp.fstExpr).value, uses, defs));
        } else if(binOp.sndExpr instanceof ImcCONST) {
            uses.add(binOp.fstExpr.accept(this, visArg));
            visArg.add(new AsmMOVE(instr + ((ImcCONST) binOp.sndExpr).value, uses, defs));
        } else {
            uses.add(binOp.sndExpr.accept(this, visArg));
            uses.add(binOp.fstExpr.accept(this, visArg));
            visArg.add(new AsmMOVE(instr, uses, defs));
        }

        return r;
    }

    @Override
    public MemTemp visit(ImcCALL call, Vector<AsmInstr> visArg) {
        return null;
    }

    /*
    @Override
    public MemTemp visit(ImcCONST constant, Vector<AsmInstr> visArg) {
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();
        defs.add(new MemTemp());

        AsmMOVE asmMOVE = new AsmMOVE("ADD ", uses, defs, jumps);
        visArg.add(asmOPER);

        return defs.lastElement();
    }
    */

    @Override
    public MemTemp visit(ImcMEM mem, Vector<AsmInstr> visArg) {
        String instr = "LDO `d0, `s0, 0";
        Vector<MemTemp> uses = new Vector<>();
        Vector<MemTemp> defs = new Vector<>();

        MemTemp r = new MemTemp();
        defs.add(r);

        visArg.add(new AsmMOVE(instr, uses, defs));
        return r;
    }

    @Override
    public MemTemp visit(ImcNAME name, Vector<AsmInstr> visArg) {
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

        return null;
    }
}
