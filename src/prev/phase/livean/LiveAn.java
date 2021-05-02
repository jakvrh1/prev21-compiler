package prev.phase.livean;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

	public LiveAn() {
		super("livean");
	}

	public void analysis() {

		HashMap<MemLabel, AsmLABEL> jls = new HashMap<>();

		for(int c = 0; c < AsmGen.codes.size(); ++c) {
			int cSize = AsmGen.codes.get(c).instrs.size();
			for (int instr = 0; instr < cSize; ++instr) {
				AsmInstr ai = AsmGen.codes.get(c).instrs.get(instr);
				if(ai instanceof AsmLABEL) {
					jls.put(((AsmLABEL) ai).label, (AsmLABEL) ai);
				}
			}
		}

		for(int i = 0; ; i++) {
		    boolean equal = true;
	    	for(int c = 0; c < AsmGen.codes.size(); ++c) {
				Vector<AsmInstr> instrs = AsmGen.codes.get(c).instrs;
	    	    for(int instr = 0; instr < instrs.size(); ++instr) {
					AsmOPER ai = (AsmOPER) instrs.get(instr);

					HashSet<MemTemp> newIn = ai.out();
					HashSet<MemTemp> newOut = new HashSet<>();

					ai.defs().forEach(newIn::remove);
					newIn.addAll(ai.uses());

					if(instr == instrs.size() - 1)
						break;

					if(!ai.jumps().isEmpty()) {
						for(MemLabel ml : ai.jumps()) {
						    if(jls.get(ml) != null)
								newOut.addAll(jls.get(ml).in());
						}
					} else
					    newOut.addAll(instrs.get(instr + 1).in());

					if(!ai.in().equals(newIn)) equal = false;
					if(!ai.out().equals(newOut)) equal = false;

					ai.removeAllFromIn();
					ai.removeAllFromOut();

					ai.addInTemps(newIn);
					ai.addOutTemp(newOut);
				}
			}

	    	if(equal) break;
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
				logger.addAttribute("code", instr.toString());
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
