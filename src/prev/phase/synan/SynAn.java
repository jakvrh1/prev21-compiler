package prev.phase.synan;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import prev.common.report.Location;
import prev.common.report.Report;
import prev.data.sym.Token;
import prev.phase.Phase;
import prev.phase.lexan.LexAn;

import java.util.BitSet;

/**
 * Syntax analysis phase.
 */
public class SynAn extends Phase {

    // === STATIC ===

    /**
     * The parse tree.
     */
    public static PrevParser.SourceContext tree;

    // ==============

    /**
     * The ANTLR parser that actually performs syntax analysis.
     */
    public final PrevParser parser;

    /**
     * Phase construction: sets up logging and the ANTLR lexer and parser.
     */
    public SynAn(LexAn lexan) {
        super("synan");
        parser = new PrevParser(new CommonTokenStream(lexan.lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
                //super.reportAmbiguity(recognizer, dfa, startIndex, stopIndex, exact, ambigAlts, configs);
                throw new Report.Error("Error reportAmbiguity()");
            }

            @Override
            public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
                //super.reportAttemptingFullContext(recognizer, dfa, startIndex, stopIndex, conflictingAlts, configs);
                throw new Report.Error("Error reportAttemptingFullContext()");
            }

            @Override
            public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
                //super.reportContextSensitivity(recognizer, dfa, startIndex, stopIndex, prediction, configs);
                throw new Report.Error("Error reportContextSensitivity()");
            }

            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                throw new Report.Error(new Location(line, charPositionInLine),
                        "Unexpected symbol '" + ((Token) offendingSymbol).getText() + "'.");
            }
        });
    }

    /**
     * Logs a parse tree.
     *
     * @param tree Parse tree to be logged.
     */
    public void log(ParseTree tree) {
        if (logger == null)
            return;
        if (tree instanceof TerminalNodeImpl) {
            TerminalNodeImpl node = (TerminalNodeImpl) tree;
            Token token = (Token) (node.getPayload());
            token.log(logger);
        }
        if (tree instanceof ParserRuleContext) {
            ParserRuleContext node = (ParserRuleContext) tree;
            logger.begElement("nont");
            logger.addAttribute("label", PrevParser.ruleNames[node.getRuleIndex()]);
            int numChildren = node.getChildCount();
            for (int i = 0; i < numChildren; i++)
                log(node.getChild(i));
            logger.endElement();
        }
    }

}
