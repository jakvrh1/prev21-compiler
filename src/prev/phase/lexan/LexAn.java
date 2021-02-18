package prev.phase.lexan;

import java.io.*;
import java.util.BitSet;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

import prev.common.report.*;
import prev.data.sym.Token;
import prev.phase.*;

/**
 * Lexical analysis phase.
 */
public class LexAn extends Phase {

	/** The ANTLR lexer that actually performs lexical analysis. */
	public final PrevLexer lexer;

	/**
	 * Phase construction: sets up logging and the ANTLR lexer.
	 */
	public LexAn() {
		super("lexan");

		String srcFileName = prev.Compiler.cmdLineArgValue("--src-file-name");
		try {
			lexer = new PrevLexer(CharStreams.fromFileName(srcFileName));
			lexer.setTokenFactory(new PrevTokenFactory());

			//added lines
			lexer.removeErrorListeners();
			lexer.addErrorListener(new LexerErrorListener());
		} catch (IOException __) {
			throw new Report.Error("Cannot open file '" + srcFileName + "'.");
		}
	}

	/**
	 * A customized token factory which logs tokens.
	 */
	private class PrevTokenFactory implements TokenFactory<Token> {

		@Override
		public Token create(int type, String text) {
			Token token = new Token(type, text);
			token.log(logger);
			return token;
		}

		@Override
		public Token create(Pair<TokenSource, CharStream> source, int type, String text, int channel, int start,
				int stop, int line, int charPositionInLine) {
			Token token = new Token(source, type, channel, start, stop);
			token.log(logger);
			return token;
		}
	}

	public class LexerErrorListener extends BaseErrorListener {
		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
			throw new LexerError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
		}
	}

	public class LexerError extends RuntimeException{
	    private int line;
	    private int charPositionLine;
	    private String msg;

		public LexerError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
			this.line = line;
			this.charPositionLine = charPositionInLine;
			this.msg = msg;
		}

		@Override
		public String toString() {
			return "LexerError{" +
					"line=" + line +
					", charPositionLine=" + charPositionLine +
					", msg='" + msg + '\'' +
					'}';
		}
	}

}
