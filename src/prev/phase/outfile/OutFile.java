package prev.phase.outfile;

import prev.Compiler;
import prev.data.asm.AsmLABEL;
import prev.data.asm.Code;
import prev.data.lin.LinDataChunk;
import prev.phase.Phase;
import prev.phase.asmgen.AsmGen;
import prev.phase.imclin.ImcLin;
import prev.phase.regall.RegAll;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class OutFile {

    public LinkedList<String> mmix = new LinkedList<>();
    public String FILE_NAME = "out.mms";
    private int STACK_SIZE = 1; // used with SETH

    public OutFile() {
    }

    private String putChar() {
        return "\t\tLDO Temp,$253,8\n" + "\t\tLDA $255,OutBuffer\n" + "\t\tSTB Temp,$255,0\n" + "\t\tTRAP 0,Fputs,StdOut\n";
    }

    private void addStaticData(LinDataChunk ldc) {
        if(ldc.init == null) {
            mmix.add(ldc.label.name + "\t\tOCTA");
        } else {
            mmix.add(ldc.label.name + "\t\tOCTA " + ldc.init + ",0");
        }
    }

    private void dataChunks() {
        mmix.add("\t\tLOC Data_Segment");
        mmix.add("\t\tGREG @");
        mmix.add("OutBuffer\tBYTE");
        mmix.add("\t\tBYTE 0");
        for(LinDataChunk ldc : ImcLin.dataChunks()) {
            addStaticData(ldc);
        }
    }

    // stack pointer $253
    // frame pointer $254

    private void prologue(Code code) {
        //mmix.add(code.frame.label.name);
        mmix.add("\n# FUNCTION " + code.frame.label.name);
        mmix.add("# Prologue");
        long oldFPOffset = code.frame.locsSize + 8;

        storeConstant(0, oldFPOffset, code);

        //mmix.add("SUB $253, $253, " + oldFPOffset); // go to the location where old FP is stored
        mmix.add("\t\tSUB $253,$253,$0"); // go to the location where old FP is stored

        mmix.add("\t\tSTO $254,$253,0");              // store the current FP at this location
        mmix.add("\t\tSUB $253,$253,8");              // go one octa futher down (RA)
        mmix.add("\t\tGET $254,rJ");                   // store the return address in FP (RA)
        mmix.add("\t\tSTO $254,$253,0");              // save the return address in the frame
        mmix.add("\t\tADD $253,$253,8");              // go one octa back up

        //mmix.add("ADD $253, $253, " + oldFPOffset); // go back to the old SP
        mmix.add("\t\tADD $253,$253,$0"); // go back to the old SP
        mmix.add("\t\tOR $254,$253,0");               // set the new FP to be the old SP

        storeConstant(1, code.frame.size, null);

        //mmix.add("SUB $253, $253, " + code.frame.size); // move SP by the stack frame size
        mmix.add("\t\tSUB $253,$253,$1"); // move SP by the stack frame size

        mmix.add("\t\tJMP " + code.entryLabel.name);   // go to the actual function

        /*
        // storing SP
        storeConstant(0, oldFPOffset);
        mmix.add("SUB $1, $253, $0");
        mmix.add("STO $254, $0, 0");
        mmix.add("SUB $2, $0, 8");
         */
    }

    private void storeConstant(int reg, long value, Code code) {
        /*
        if(code != null)
            mmix.add(code.frame.label.name + "\t\tAND $" + reg + ",$" + reg + ",0");
        else
            mmix.add("\t\tAND $" + reg + ",$" + reg + ",0");
         */


        if(code != null)
            mmix.add(code.frame.label.name + "\t\tSETL $" + reg + "," + (value & 0x000000000000FFFFL));
        else
            mmix.add("\t\tSETL $" + reg + "," + (value & 0x000000000000FFFFL));

        if ((value & 0x00000000FFFF0000L) > 0)
            mmix.add("\t\tINCML $" + reg + "," + ((value & 0x00000000FFFF0000L) >> 4*4 ));
        if ((value & 0x0000FFFF00000000L) > 0)
            mmix.add("\t\tINCMH $" + reg + "," + ((value & 0x0000FFFF00000000L) >> 8*4));
        if ((value & 0xFFFF000000000000L) > 0)
            mmix.add("\t\tINCH $" + reg + "," + ((value & 0xFFFF000000000000L) >> 12*4));
    }

    private void epilogue(Code code) {
        //mmix.add(code.exitLabel.name);
        mmix.add("# Epilogue");

        long oldFPOffset = code.frame.locsSize + 8;
        mmix.add(code.exitLabel.name + "\t\tSTO $" + RegAll.tempToReg.get(code.frame.RV) + ",$254,0");
        //mmix.add("STO\t$" + RegAll.tempToReg.get(code.frame.RV) + ", rJ, 0");

        mmix.add("\t\tOR $253,$254,0"); // set new SP to be old FP

        storeConstant(0, oldFPOffset, null);
        mmix.add("\t\tSUB $0,$254,$0"); // old FP address

        mmix.add("\t\tSUB $1,$0,8"); // RA address
        mmix.add("\t\tLDO $1,$1,0"); // RA value
        mmix.add("\t\tPUT rJ,$1"); // put

        mmix.add("\t\tLDO $254,$0,0"); // set new FP
        /*
        storeConstant(1, code.frame.size, null);
        mmix.add("\t\tSUB $1,$253,$1"); //new SP address
        mmix.add("\t\tLDO $253,$1,0"); // set new SP address
         */


        /*
        storeConstant(0, oldFPOffset, null);
        mmix.add("\t\tSUB $0,$254,$0"); // return address
        mmix.add("\t\tADD $1,$0,8");
        mmix.add("\t\tPUT rJ,$1");
        mmix.add("\t\tSTO $0,$254,0");

        storeConstant(1, code.frame.size, null);
        mmix.add("\t\tADD $253,$253,$1"); // restore SP
         */

        mmix.add("\t\tPOP 0,0");
    }

    private void code() {
        for(Code code: AsmGen.codes) {
            prologue(code);
            mmix.add("# Body");
            //System.out.println(code.instrs.lastElement());
            for(int i = 1; i < code.instrs.size(); ++i) {
                String line = "";
                if(code.instrs.get(i - 1) instanceof AsmLABEL && code.instrs.get(i) instanceof AsmLABEL) {
                    line = ((AsmLABEL) code.instrs.get(i - 1)).label.name;
                    line += " SWYM";
                    mmix.add(line);
                    continue;
                }
                if(code.instrs.get(i) instanceof AsmLABEL) continue;

                if(code.instrs.get(i - 1) instanceof AsmLABEL) {
                   line = ((AsmLABEL) code.instrs.get(i - 1)).label.name;
                }
                String temp = "\t\t" + code.instrs.get(i).toString(RegAll.tempToReg);
                if(temp.contains("_putChar")) {
                    mmix.add("\n\t\t#PUTCHAR");
                    line += putChar();
                } else {
                    line += temp;
                }

                mmix.add(line);
            }
            /*for(AsmInstr instr : code.instrs)
                mmix.add("\t" + instr.toString(RegAll.tempToReg));

             */

            epilogue(code);
        }
    }

    public void setStack() {
        //mmix.add("\t\tLOC Stack_Segment");
        mmix.add("FP\t\tGREG ");
        mmix.add("SP\t\tGREG ");
        mmix.add("Temp\tGREG\n");
    }

    public void createMMIXProgram() {
        setStack();
        dataChunks();
        mmix.add("\n\t\tLOC #100\n");
        callMain();
        code();
    }

    private void callMain() {
        mmix.add("Main\tSETMH FP," + STACK_SIZE);
        mmix.add("\t\tSETMH SP," + STACK_SIZE);
        mmix.add("\t\tSETMH Temp," + (STACK_SIZE + 1));
        mmix.add("\t\tPUSHJ $" + Compiler.REGISTERS + ",_main");
        mmix.add("\t\tTRAP 0,Halt,0");
    }

    public void printProgram() {
        for(String line : mmix) {
            //line = line.replaceAll("\\$254", "fp");
            //line = line.replaceAll("\\$253", "sp");
           System.out.println(line);
        }
    }

    public void writeProgram() {
        try {
            File file = new File(FILE_NAME);
            file.createNewFile();

            FileWriter fw = new FileWriter(file);

            for(String line : mmix) {
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
