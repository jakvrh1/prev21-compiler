package prev.phase.outfile;

import prev.data.asm.AsmInstr;
import prev.data.asm.AsmLABEL;
import prev.data.asm.AsmOPER;
import prev.data.mem.MemLabel;
import prev.phase.regall.RegAll;

import java.util.LinkedList;
import java.util.Vector;

public class IntegratedFunctions {

    static public void inject(LinkedList<String> mmix) {
        for(AsmInstr i : _new())
            mmix.add(i.toString(RegAll.tempToReg));

        for(AsmInstr i : _del())
            mmix.add(i.toString(RegAll.tempToReg));

        for(AsmInstr i : _putChar())
            mmix.add(i.toString(RegAll.tempToReg));

        for(AsmInstr i : _getChar())
            mmix.add(i.toString(RegAll.tempToReg));

        for(AsmInstr i : _exit())
            mmix.add(i.toString(RegAll.tempToReg));
    }

    static public Vector<AsmInstr> _new() {
        Vector<AsmInstr> instrs = new Vector<>();

        instrs.add(new AsmOPER("_new\tSTO new,SP,0", null, null, null));
        instrs.add(new AsmOPER("\t\tLDO $0,SP,8", null, null, null));
        instrs.add(new AsmOPER("\t\tADD new,new,$0", null, null, null));
        instrs.add(new AsmOPER("\t\tPOP 0,0", null, null, null));

        return instrs;
    }

    static public Vector<AsmInstr> _putChar() {
        Vector<AsmInstr> instrs = new Vector<>();

        instrs.add(new AsmOPER("_putChar\tLDA $255,OutBuf", null, null, null));
        instrs.add(new AsmOPER("\t\tLDO $0,SP,8", null, null, null));
        instrs.add(new AsmOPER("\t\tSTB $0,$255,0", null, null, null));
        instrs.add(new AsmOPER("\t\tTRAP 0,Fputs,StdOut", null, null, null));
        instrs.add(new AsmOPER("\t\tSTO $255,SP,0", null, null, null));
        instrs.add(new AsmOPER("\t\tPOP 0,0", null, null, null));

        return instrs;
    }

    static public Vector<AsmInstr> _getChar() {
        Vector<AsmInstr> instrs = new Vector<>();

        instrs.add(new AsmOPER("_getChar\tLDA $255,InArg", null, null, null));
        instrs.add(new AsmOPER("\t\tTRAP 0,Fgets,StdIn", null, null, null));
        instrs.add(new AsmOPER("\t\tLDB $0,InBuf", null, null, null));
        instrs.add(new AsmOPER("\t\tSTO $0,SP,0", null, null, null));
        instrs.add(new AsmOPER("\t\tPOP 0,0", null, null, null));

        return instrs;
    }

    static public Vector<AsmInstr> _del() {
        Vector<AsmInstr> instrs = new Vector<>();

        instrs.add(new AsmOPER("_del\tPOP 0,0", null, null, null));

        return instrs;
    }

    static public Vector<AsmInstr> _exit() {
        Vector<AsmInstr> instrs = new Vector<>();

        instrs.add(new AsmOPER("_exit\tTRAP 0,Halt,0", null, null, null));

        return instrs;
    }


}
