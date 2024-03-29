package prev;

import java.util.*;
import java.util.jar.Attributes;

import org.antlr.v4.runtime.*;

import prev.common.report.*;
import prev.data.mem.MemFrame;
import prev.phase.asmgen.*;
import prev.phase.imcgen.*;
import prev.phase.imclin.*;
import prev.phase.lexan.*;
import prev.phase.livean.*;
import prev.phase.memory.*;
import prev.phase.outfile.OutFile;
import prev.phase.regall.*;
import prev.phase.synan.*;
import prev.phase.abstr.*;
import prev.phase.seman.*;

/**
 * The compiler.
 */
public class Compiler {

	// COMMAND LINE ARGUMENTS

	/** All valid phases of the compiler. */
	private static final String phases = "none|lexan|synan|abstr|seman|memory|imcgen|imclin|asmgen|livean|regall";

	/** Values of command line arguments. */
	private static HashMap<String, String> cmdLine = new HashMap<String, String>();

	public static int REGISTERS = 8;
	private static boolean DEBUG = false;
	private static boolean LOG = true;

	/**
	 * Returns the value of a command line argument.
	 * 
	 * @param cmdLineArgName The name of the command line argument.
	 * @return The value of the specified command line argument or {@code null} if
	 *         the specified command line argument has not been used.
	 */
	public static String cmdLineArgValue(String cmdLineArgName) {
		return cmdLine.get(cmdLineArgName);
	}

	// THE COMPILER'S STARTUP METHOD

	/**
	 * The compiler's startup method.
	 * 
	 * @param args Command line arguments (see {@link prev.Compiler}).
	 */
	public static void main(String[] args) {
		try {
			Report.info("This is PREV'21 compiler:");

			// Scan the command line.
			for (int argc = 0; argc < args.length; argc++) {
				if (args[argc].startsWith("--")) {
					// Command-line switch.
					if (args[argc].matches("--num-regs=.*")) {
						if (cmdLine.get("--num-regs") == null) {
							REGISTERS = Integer.parseInt(args[argc].substring(11));
							continue;
						}
					}
					if (args[argc].matches("--src-file-name=.*")) {
						if (cmdLine.get("--src-file-name") == null) {
							cmdLine.put("--src-file-name", args[argc]);
							continue;
						}
					}
					if (args[argc].matches("--dst-file-name=.*")) {
						if (cmdLine.get("--dst-file-name") == null) {
							cmdLine.put("--dst-file-name", args[argc]);
							continue;
						}
					}
					if (args[argc].matches("--target-phase=(" + phases + "|all)")) {
						if (cmdLine.get("--target-phase") == null) {
							cmdLine.put("--target-phase", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--logged-phase=(" + phases + "|all)")) {
						if (cmdLine.get("--logged-phase") == null) {
							cmdLine.put("--logged-phase", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--xml=.*")) {
						if (cmdLine.get("--xml") == null) {
							cmdLine.put("--xml", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--xsl=.*")) {
						if (cmdLine.get("--xsl") == null) {
							cmdLine.put("--xsl", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					Report.warning("Command line argument '" + args[argc] + "' ignored.");
				} else {
					// Source file name.
					if (cmdLine.get("--src-file-name") == null) {
						cmdLine.put("--src-file-name", args[argc]);
					} else {
						Report.warning("Source file '" + args[argc] + "' ignored.");
					}
				}
			}
			if (cmdLine.get("--src-file-name") == null) {
				throw new Report.Error("Source file not specified.");
			}
			if (cmdLine.get("--dst-file-name") == null) {
				cmdLine.put("--dst-file-name", cmdLine.get("--src-file-name").replaceFirst("\\.[^./]*$", "") + ".mms");
			}
			if (cmdLine.get("--target-phase") == null) {
				cmdLine.put("--target-phase", phases.replaceFirst("^.*\\|", ""));
			}

			//System.out.println("NUM OF REGS: " + REGISTERS);

			// Compilation process carried out phase by phase.
			while (true) {

				// Lexical analysis.
				if (Compiler.cmdLineArgValue("--target-phase").equals("lexan"))
					try (LexAn lexan = new LexAn()) {
						while (lexan.lexer.nextToken().getType() != Token.EOF) {
						}
						break;
					}
				if(DEBUG) System.out.println("Lexical analysis done.");

				// Syntax analysis
				try (LexAn lexan = new LexAn(); SynAn synan = new SynAn(lexan)) {
					SynAn.tree = synan.parser.source();
					if(LOG)
						synan.log(SynAn.tree);
				}
				if(DEBUG) System.out.println("Syntax analysis done.");
				if(Compiler.cmdLineArgValue("--target-phase").equals("synan"))
					break;

				// Abstract syntax tree construction.
				try (Abstr abstr = new Abstr()) {
					Abstr.tree = SynAn.tree.ast;

					if(LOG) {
						AbsLogger logger = new AbsLogger(abstr.logger);
						Abstr.tree.accept(logger, "Decls");
					}
					/*
					AbsLogger logger = new AbsLogger(abstr.logger);
					Abstr.tree.accept(logger, "Decls");

					 */
				}
				if(DEBUG) System.out.println("Abstract syntax tree construction done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("abstr"))
					break;

				// Semantic analysis.
				try (SemAn seman = new SemAn()) {
				    NameResolver nr = new NameResolver();
					Abstr.tree.accept(nr, NameResolver.Mode.FIRST);
					Abstr.tree.accept(nr, NameResolver.Mode.SECOND);

					try {
						TypeResolver tr = new TypeResolver();
						Abstr.tree.accept(tr, TypeResolver.Mode.FIRST);
						Abstr.tree.accept(tr, TypeResolver.Mode.SECOND);
						Abstr.tree.accept(tr, TypeResolver.Mode.THIRD);
						Abstr.tree.accept(tr, TypeResolver.Mode.VALIDATION);
					} catch (StackOverflowError e) {
					    throw new Report.Error("StackOverFlow, possibly found cycle.");
					}

					Abstr.tree.accept(new AddrResolver(), AddrResolver.Mode.FIRST);
					Abstr.tree.accept(new AddrResolver(), AddrResolver.Mode.SECOND);

					if(LOG) {
						AbsLogger logger = new AbsLogger(seman.logger);
						logger.addSubvisitor(new SemLogger(seman.logger));
						Abstr.tree.accept(logger, "Decls");
					}
					/*
					AbsLogger logger = new AbsLogger(seman.logger);
					logger.addSubvisitor(new SemLogger(seman.logger));
					Abstr.tree.accept(logger, "Decls");

					 */
				}
				if(DEBUG) System.out.println("Semantic analysis done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("seman"))
					break;

				// Memory layout.
				try (Memory memory = new Memory()) {
					MemEvaluator me = new MemEvaluator();
					Abstr.tree.accept(me, MemEvaluator.Mode.FIRST);
					Abstr.tree.accept(me, MemEvaluator.Mode.GLOBAL_VARIABLES);

					if(LOG) {
						AbsLogger logger = new AbsLogger(memory.logger);
						logger.addSubvisitor(new SemLogger(memory.logger));
						logger.addSubvisitor(new MemLogger(memory.logger));
						Abstr.tree.accept(logger, "Decls");
					}
					/*
					AbsLogger logger = new AbsLogger(memory.logger);
					logger.addSubvisitor(new SemLogger(memory.logger));
					logger.addSubvisitor(new MemLogger(memory.logger));
					Abstr.tree.accept(logger, "Decls");

					 */
				}
				if(DEBUG) System.out.println("Memory layout done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("memory"))
					break;

				// Intermediate code generation.
				try (ImcGen imcgen = new ImcGen()) {
					Abstr.tree.accept(new CodeGenerator(), new Stack<MemFrame>());

					if(LOG) {
						AbsLogger logger = new AbsLogger(imcgen.logger);
						logger.addSubvisitor(new SemLogger(imcgen.logger));
						logger.addSubvisitor(new MemLogger(imcgen.logger));
						logger.addSubvisitor(new ImcLogger(imcgen.logger));
						Abstr.tree.accept(logger, "Decls");
					}
					/*
					AbsLogger logger = new AbsLogger(imcgen.logger);
					logger.addSubvisitor(new SemLogger(imcgen.logger));
					logger.addSubvisitor(new MemLogger(imcgen.logger));
					logger.addSubvisitor(new ImcLogger(imcgen.logger));
					Abstr.tree.accept(logger, "Decls");
					 */
				}
				if(DEBUG) System.out.println("Intermediate code generator done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("imcgen"))
					break;

				// Linearization of intermediate code.
				try (ImcLin imclin = new ImcLin()) {
					Abstr.tree.accept(new ChunkGenerator(), null);
					if(LOG)
						imclin.log();

					//Interpreter interpreter = new Interpreter(ImcLin.dataChunks(), ImcLin.codeChunks());
					//System.out.println("EXIT CODE: " + interpreter.run("_main"));
				}
				if(DEBUG) System.out.println("Linearization of IMC done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("imclin"))
					break;

				// Machine code generation.
				try (AsmGen asmgen = new AsmGen()) {
					asmgen.genAsmCodes();
					if(LOG)
						asmgen.log();
				}
				if(DEBUG) System.out.println("Machine code generator done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("asmgen"))
					break;

				// Liveness analysis.
				try (LiveAn livean = new LiveAn()) {
					livean.analysis();
					if(LOG)
						livean.log();
				}
				if(DEBUG) System.out.println("Liveness analysis done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("livean"))
					break;

				// Register allocation.
				try (RegAll regall = new RegAll(REGISTERS)) {
					regall.allocate();
					if(LOG)
						regall.log();
				}
				if(DEBUG) System.out.println("Register allocation done.");
				if (Compiler.cmdLineArgValue("--target-phase").equals("regall"))
					break;

				OutFile outFile = new OutFile();
				outFile.createMMIXProgram();
				outFile.writeProgram();

				//if(DEBUG)
					//outFile.printProgram();

				break;
			}

			Report.info("Done.");
		} catch (Report.Error __) {
			System.exit(1);
		}
	}

}
