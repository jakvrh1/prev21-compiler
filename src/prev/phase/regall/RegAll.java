package prev.phase.regall;

import prev.data.asm.AsmInstr;
import prev.data.asm.AsmMOVE;
import prev.data.asm.Code;
import prev.data.mem.MemTemp;
import prev.phase.Phase;
import prev.phase.asmgen.AsmGen;

import java.util.*;

/**
 * Register allocation.
 */
public class RegAll extends Phase {

    /**
     * Mapping of temporary variables to registers.
     */
    public static final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();

    private int REGISTERS = 8;
    private final int FP_REGISTER = 254;

    //private int SPILL_COUNTER = 0;

    public RegAll() {
        super("regall");
    }

    public RegAll(int numRegs) {
        super("regall");
        this.REGISTERS = numRegs;
    }

    public HashMap<MemTemp, HashSet<MemTemp>> codeGraph(Code code) {
        HashMap<MemTemp, HashSet<MemTemp>> G = new HashMap<>();
        G.put(code.frame.FP, new HashSet<>());
        G.put(code.frame.RV, new HashSet<>());

        for (AsmInstr instr : code.instrs) {
            if (instr.defs().size() != 0) {
                if (!G.containsKey(instr.defs().firstElement()))
                    G.put(instr.defs().firstElement(), new HashSet<>());
            }
            for (MemTemp i : instr.in()) {
                if (!G.containsKey(i))
                    G.put(i, new HashSet<>());
                for (MemTemp j : instr.in())
                    if (i != j) G.get(i).add(j);
            }

            Set<MemTemp> outs = instr.out();
            outs.addAll(instr.defs());

            for (MemTemp i : outs) {
                if (!G.containsKey(i))
                    G.put(i, new HashSet<>());
                for (MemTemp j : outs)
                    if (i != j) G.get(i).add(j);
            }
        }

        return G;
    }

    public void allocate() {
        /*
		for(Code code : AsmGen.codes) {
			var G = codeGraph(code);
			for(MemTemp key : G.keySet()) {
				System.out.print("NODE: " + key + ", EDGE: ");
				for(MemTemp val : G.get(key)) {
				    System.out.print(val + ", ");
				}
				System.out.println();
			}
			System.out.println();
		}
         */

        for (Code code : AsmGen.codes) {
            var G = codeGraph(code);
            var simpGraph = new HashMap<MemTemp, HashSet<MemTemp>>(G);

            LinkedList<MemTemp> takenNodes = new LinkedList<>();
            LinkedList<Boolean> nodeStatus = new LinkedList<>();

            // Simplify
            while (takenNodes.size() < G.size()) {
                boolean spill = true;
                LinkedList<MemTemp> remove = new LinkedList<>();
                for (var g : simpGraph.entrySet()) {
                    if (g.getValue().size() < REGISTERS) {
                        takenNodes.add(g.getKey());
                        nodeStatus.add(false);
                        spill = false;
                        remove.add(g.getKey());
                    }
                }

                // Spill
                if (takenNodes.size() < G.size() && spill) {
                    takenNodes.add(simpGraph.keySet().iterator().next());
                    nodeStatus.add(true);
                    remove.add(simpGraph.keySet().iterator().next());
                }

                for (var node : remove) simpGraph.remove(node);
            }


            // Select
            while (!takenNodes.isEmpty()) {
                var t = takenNodes.removeLast();
                var s = nodeStatus.removeLast();

                if (t == code.frame.FP) {
                    tempToReg.put(t, FP_REGISTER);
                    continue;
                }
                boolean[] usedNums = new boolean[REGISTERS];
                for (var a : G.get(t)) {
                    if (tempToReg.containsKey(a) && tempToReg.get(a) != FP_REGISTER)
                        usedNums[tempToReg.get(a)] = true;
                }

                //System.out.println("Barvam " + t.toString());
                //System.out.println(Arrays.toString(usedNums));

                boolean isSpill = s;
                for (int i = 0; i < REGISTERS; ++i) {
                    if (!usedNums[i]) {
                        tempToReg.put(t, i);
                        isSpill = false;
                        break;
                    }
                }

                if (isSpill) {
                    // TODO
                    System.out.println("SPILL");
                    //System.out.println(SPILL_COUNTER++);
                }
            }

            // remove SET $X, $X
            code.instrs.removeIf(instr -> {
                return instr instanceof AsmMOVE && tempToReg.get(instr.defs().firstElement()) == tempToReg.get(instr.uses().firstElement());
            });
        }
    }

    public void log() {
        if (logger == null)
            return;
        for (Code code : AsmGen.codes) {
            logger.begElement("code");
            logger.addAttribute("entrylabel", code.entryLabel.name);
            logger.addAttribute("exitlabel", code.exitLabel.name);
            logger.addAttribute("tempsize", Long.toString(code.tempSize));
            code.frame.log(logger);
            logger.begElement("instructions");
            for (AsmInstr instr : code.instrs) {
                logger.begElement("instruction");
                logger.addAttribute("code", instr.toString(tempToReg));
                logger.begElement("temps");
                logger.addAttribute("name", "use");
                for (MemTemp temp : instr.uses()) {
                    logger.begElement("temp");
                    logger.addAttribute("name", temp.toString());
                    logger.endElement();
                }
                logger.endElement();
                logger.begElement("temps");
                logger.addAttribute("name", "def");
                for (MemTemp temp : instr.defs()) {
                    logger.begElement("temp");
                    logger.addAttribute("name", temp.toString());
                    logger.endElement();
                }
                logger.endElement();
                logger.begElement("temps");
                logger.addAttribute("name", "in");
                for (MemTemp temp : instr.in()) {
                    logger.begElement("temp");
                    logger.addAttribute("name", temp.toString());
                    logger.endElement();
                }
                logger.endElement();
                logger.begElement("temps");
                logger.addAttribute("name", "out");
                for (MemTemp temp : instr.out()) {
                    logger.begElement("temp");
                    logger.addAttribute("name", temp.toString());
                    logger.endElement();
                }
                logger.endElement();
                logger.endElement();
            }
            logger.endElement();
            logger.endElement();
        }
    }

}
