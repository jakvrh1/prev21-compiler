package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;

/**
 * Register allocation.
 */
public class RegAll extends Phase {
	
	/** Mapping of temporary variables to registers. */
	public final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();

	public RegAll() {
		super("regall");
	}

	public HashMap<MemTemp, HashSet<MemTemp>> codeGraph(Code code) {
		HashMap<MemTemp, HashSet<MemTemp>> G = new HashMap<>();

		for(AsmInstr instr : code.instrs) {
			for(MemTemp i : instr.in()) {
				if (!G.containsKey(i))
					G.put(i, new HashSet<>());
				for(MemTemp j : instr.in()) {
					G.get(i).add(j);
				}
			}
			for(MemTemp i : instr.out()) {
				if (!G.containsKey(i))
					G.put(i, new HashSet<>());
				for(MemTemp j : instr.out()) {
					G.get(i).add(j);
				}
			}
		}

		return G;
	}

	public void allocate() {
		// TODO
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
