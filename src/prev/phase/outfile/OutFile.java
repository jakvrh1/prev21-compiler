package prev.phase.outfile;

import prev.Compiler;
import prev.data.asm.AsmInstr;
import prev.data.asm.AsmOPER;
import prev.data.asm.Code;
import prev.data.lin.LinCodeChunk;
import prev.data.lin.LinDataChunk;
import prev.phase.Phase;
import prev.phase.asmgen.AsmGen;
import prev.phase.imclin.ImcLin;
import prev.phase.regall.RegAll;

import java.util.LinkedList;

public class OutFile extends Phase {

    public LinkedList<String> mmix = new LinkedList<>();

    public OutFile() {
        super("Out");
    }

    private void addStaticData(LinDataChunk ldc) {
        if(ldc.init == null) {
            mmix.add(ldc.label.name + "\tOCTA\t");
        } else {
            mmix.add(ldc.label.name + "\tBYTE\t" + ldc.init);
        }
    }

    private void dataChunks() {
        for(LinDataChunk ldc : ImcLin.dataChunks()) {
            addStaticData(ldc);
        }
    }

    // stack pointer $253
    // frame pointer $254

    private void prologue(Code code) {
        mmix.add(code.frame.label.name);
        long oldFPOffset = code.frame.locsSize + 8;

        storeConstant(0, oldFPOffset);
        //mmix.add("SUB $253, $253, " + oldFPOffset); // go to the location where old FP is stored
        mmix.add("SUB\t$253, $253, $0"); // go to the location where old FP is stored

        mmix.add("STO\t$254, $253, 0");              // store the current FP at this location
        mmix.add("SUB\t$253, $253, 8");              // go one octa futher down
        mmix.add("GET\t$254, rJ");                   // store the return address in FP
        mmix.add("STO\t$254, $253, 0");              // save the return address in the frame
        mmix.add("ADD\t$253, $253, 8");              // go one octa back up

        //mmix.add("ADD $253, $253, " + oldFPOffset); // go back to the old SP
        mmix.add("ADD\t$253, $253, $0"); // go back to the old SP
        mmix.add("OR\t$254, $253, 0");               // set the new FP to be the old SP

        storeConstant(1, code.frame.size);

        //mmix.add("SUB $253, $253, " + code.frame.size); // move SP by the stack frame size
        mmix.add("SUB\t$253, $253, $1"); // move SP by the stack frame size

        mmix.add("JMP\t" + code.entryLabel.name);   // go to the actual function

        /*
        // storing SP
        storeConstant(0, oldFPOffset);
        mmix.add("SUB $1, $253, $0");
        mmix.add("STO $254, $0, 0");
        mmix.add("SUB $2, $0, 8");
         */
    }

    private void storeConstant(int reg, long value) {
        mmix.add("AND\t$" + reg + ", $" + reg + ", 0");
        mmix.add("SETL\t$" + reg + ", " + (value & 0x000000000000FFFFL));
        if ((value & 0x00000000FFFF0000L) > 0)
            mmix.add("INCML\t$" + reg + ", " + (value & 0x00000000FFFF0000L));
        if ((value & 0x0000FFFF00000000L) > 0)
            mmix.add("INCMH\t$" + reg + ", " + (value & 0x0000FFFF00000000L));
        if ((value & 0xFFFF000000000000L) > 0)
            mmix.add("INCH\t$" + reg + ", " + (value & 0xFFFF000000000000L));
    }

    private void epilogue(Code code) {
        mmix.add(code.exitLabel.name);

        long oldFPOffset = code.frame.locsSize + 8;
        mmix.add("STO\t$" + RegAll.tempToReg.get(code.frame.RV) + ", $254, 0");
        //mmix.add("STO\t$" + RegAll.tempToReg.get(code.frame.RV) + ", rJ, 0");


        storeConstant(0, oldFPOffset);
        mmix.add("SUB\t$0, $254, $0");
        mmix.add("ADD\t$1, $0, 8");
        mmix.add("PUT\trJ, $1");
        mmix.add("STO\t$0, $254, 0");

        storeConstant(1, code.frame.size);
        mmix.add("ADD\t$253, $253, $1"); // restore SP

        mmix.add("POP\t0, 0");
    }

    private void code() {
        for(Code code: AsmGen.codes) {
            prologue(code);
            for(AsmInstr instr : code.instrs)
                mmix.add(instr.toString(RegAll.tempToReg));
            epilogue(code);
        }
    }

    public void createMMIXProgram() {
       dataChunks();

       callMain();

       code();
    }

    private void callMain() {
        mmix.add("PUSHJ\t$" + Compiler.REGISTERS + ", _main");
        mmix.add("TRAP\t0, Halt, 0");
    }

    public void printProgram() {
        for(String line : mmix) {
           System.out.println(line);
        }
    }
}
