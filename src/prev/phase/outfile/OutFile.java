package prev.phase.outfile;

import prev.Compiler;
import prev.data.asm.AsmInstr;
import prev.data.asm.AsmLABEL;
import prev.data.asm.Code;
import prev.data.lin.LinDataChunk;
import prev.phase.asmgen.AsmGen;
import prev.phase.imclin.ImcLin;
import prev.phase.regall.RegAll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class OutFile {
    // MMIX instructions line by line
    public LinkedList<String> mmix = new LinkedList<>();
    public String FILE_NAME = "out.mms";

    private final int STACK_SIZE = 1; // used with SETH
    private long DATA_SEGMENT = 0x2000000000000000L;

    public OutFile() {
    }

    private void addStaticData(LinDataChunk ldc) {
        if (ldc.init == null) {
            mmix.add(ldc.label.name + "\t\tOCTA");
        } else {
            mmix.add(ldc.label.name + "\t\tOCTA " + ldc.init + ",0");
            DATA_SEGMENT += 8; // we add null at the end of string
        }

        if (ldc.size > 8) {
            DATA_SEGMENT += ldc.size;
            mmix.add(String.format("\t\tLOC #%x", DATA_SEGMENT));
        }
    }

    private void dataChunks() {
        mmix.add(String.format("\t\tLOC #%x", DATA_SEGMENT));

        mmix.add("\t\tGREG @");
        mmix.add("OutBuf\tBYTE");
        mmix.add("OutEnd\tBYTE 0");
        mmix.add("InBuf\tBYTE 0");
        mmix.add("InArg\tOCTA InBuf,8");

        long prev_data = DATA_SEGMENT;
        DATA_SEGMENT += 16 + 16;

        for (LinDataChunk ldc : ImcLin.dataChunks()) {
            addStaticData(ldc);
            // We need to add dynamic register for address calculation
            if (DATA_SEGMENT - prev_data > 255) {
                mmix.add("\t\tGREG @");
                prev_data = DATA_SEGMENT;
            }
        }
    }

    // stack pointer $253
    // frame pointer $254

    private void prologue(Code code) {
        mmix.add("\n# FUNCTION " + code.frame.label.name);
        mmix.add("# Prologue");
        long oldFPOffset = code.frame.locsSize + 8;

        storeConstant(0, oldFPOffset, code);

        mmix.add("\t\tSUB $253,$253,$0"); // go to the location where old FP is stored

        mmix.add("\t\tSTO $254,$253,0");              // store the current FP at this location
        mmix.add("\t\tSUB $253,$253,8");              // go one octa futher down (RA)
        mmix.add("\t\tGET $254,rJ");                   // store the return address in FP (RA)
        mmix.add("\t\tSTO $254,$253,0");              // save the return address in the frame
        mmix.add("\t\tADD $253,$253,8");              // go one octa back up

        mmix.add("\t\tADD $253,$253,$0"); // go back to the old SP
        mmix.add("\t\tOR $254,$253,0");               // set the new FP to be the old SP

        storeConstant(1, code.frame.size, null);

        mmix.add("\t\tSUB $253,$253,$1"); // move SP by the stack frame size

        mmix.add("\t\tJMP " + code.entryLabel.name);   // go to the actual function
    }

    private void storeConstant(int reg, long value, Code code) {
        if (code != null)
            mmix.add(code.frame.label.name + "\t\tSETL $" + reg + "," + (value & 0x000000000000FFFFL));
        else
            mmix.add("\t\tSETL $" + reg + "," + (value & 0x000000000000FFFFL));

        if ((value & 0x00000000FFFF0000L) > 0)
            mmix.add("\t\tINCML $" + reg + "," + ((value & 0x00000000FFFF0000L) >> 4 * 4));
        if ((value & 0x0000FFFF00000000L) > 0)
            mmix.add("\t\tINCMH $" + reg + "," + ((value & 0x0000FFFF00000000L) >> 8 * 4));
        if ((value & 0xFFFF000000000000L) > 0)
            mmix.add("\t\tINCH $" + reg + "," + ((value & 0xFFFF000000000000L) >> 12 * 4));
    }

    private void epilogue(Code code) {
        mmix.add("# Epilogue");

        long oldFPOffset = code.frame.locsSize + 8;
        mmix.add(code.exitLabel.name + "\t\tSTO $" + RegAll.tempToReg.get(code.frame.RV) + ",$254,0");

        mmix.add("\t\tOR $253,$254,0"); // set new SP to be old FP

        storeConstant(0, oldFPOffset, null);
        mmix.add("\t\tSUB $0,$254,$0"); // old FP address

        mmix.add("\t\tSUB $1,$0,8"); // RA address
        mmix.add("\t\tLDO $1,$1,0"); // RA value
        mmix.add("\t\tPUT rJ,$1"); // put

        mmix.add("\t\tLDO $254,$0,0"); // set new FP
        mmix.add("\t\tPOP 0,0");
    }

    private void code() {
        for (Code code : AsmGen.codes) {
            prologue(code);
            mmix.add("# Body");

            boolean wasPrevGetChar= false;

            for (int i = 1; i < code.instrs.size(); ++i) {
                String line = "";
                AsmInstr prevInstr = code.instrs.get(i - 1);
                AsmInstr crntInstr = code.instrs.get(i);

                if (prevInstr instanceof AsmLABEL && crntInstr instanceof AsmLABEL) {
                    line = ((AsmLABEL) prevInstr).label.name;
                    line += "\t\tSWYM";
                    mmix.add(line);
                    continue;
                }

                if (crntInstr instanceof AsmLABEL) continue;

                if (prevInstr instanceof AsmLABEL)
                    line = ((AsmLABEL) prevInstr).label.name;

                line += "\t\t" + crntInstr.toString(RegAll.tempToReg);
                mmix.add(line);
            }

            epilogue(code);
        }
    }

    public void setStack() {
        //mmix.add("\t\tLOC Stack_Segment");
        mmix.add("FP\t\tGREG ");
        mmix.add("SP\t\tGREG ");
        mmix.add("new\t\tGREG ");
        mmix.add("Temp\tGREG\n");
    }

    public void createMMIXProgram() {
        mmix.add("# STACK and TEMPS");
        setStack();
        mmix.add("# STATIC DATA");
        dataChunks();
        mmix.add("\n# CODE");
        mmix.add("\t\tLOC #100\n");
        callMain();
        code();

        mmix.add("# INTEGRATED FUNCTIONS");
        IntegratedFunctions.inject(mmix);

        //System.out.println(String.format("%x", DATA_SEGMENT));
    }

    private void callMain() {
        mmix.add("Main\tSETMH FP," + STACK_SIZE);
        mmix.add("\t\tSETMH SP," + STACK_SIZE);
        mmix.add("\t\tSETMH new," + (STACK_SIZE + 1));
        mmix.add("\t\tSETMH Temp," + (STACK_SIZE + 2));
        mmix.add("\t\tPUSHJ $" + Compiler.REGISTERS + ",_main");
        mmix.add("\t\tTRAP 0,Halt,0");
    }

    public void printProgram() {
        for (String line : mmix) {
            System.out.println(line);
        }
    }

    public void writeProgram() {
        try {
            File file = new File(FILE_NAME);
            file.createNewFile();

            FileWriter fw = new FileWriter(file);

            for (String line : mmix) {
                //line = line.replaceAll("\\$254", "fp");
                //line = line.replaceAll("\\$253", "sp");
                fw.write(line + "\n");
            }

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
