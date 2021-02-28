// Generated from synan/PrevParser.g4 by ANTLR 4.9.1


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LPAREN=1, RPAREN=2, LBRACKET=3, RBRACKET=4, LBRACE=5, RBRACE=6, DOT=7, 
		COMMA=8, SEMIC=9, COLON=10, AND=11, NOT=12, OR=13, XOR=14, EQU=15, NEQ=16, 
		LTH=17, GTH=18, LEQ=19, GEQ=20, ADD=21, SUB=22, PTR=23, DIV=24, MOD=25, 
		IS=26, VOID=27, CHAR=28, INT=29, BOOL=30, DEL=31, DO=32, ELSE=33, FUN=34, 
		IF=35, NEW=36, THEN=37, TYP=38, VAR=39, WHERE=40, WHILE=41, VOIDCONST=42, 
		BOOLCONST=43, POINTERCONST=44, CHARCONST=45, STRINGCONST=46, COMMENT=47, 
		INTEGERCONST=48, IDENTIFIER=49, WHITESPACE=50;
	public static final int
		RULE_source = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"source"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'['", "']'", "'{'", "'}'", "'.'", "','", "';'", 
			"':'", "'&'", "'!'", "'|'", "'^'", "'=='", "'!='", "'<'", "'>'", "'<='", 
			"'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'void'", "'char'", 
			"'int'", "'bool'", "'del'", "'do'", "'else'", "'fun'", "'if'", "'new'", 
			"'then'", "'typ'", "'var'", "'where'", "'while'", "'none'", null, "'nil'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", 
			"DOT", "COMMA", "SEMIC", "COLON", "AND", "NOT", "OR", "XOR", "EQU", "NEQ", 
			"LTH", "GTH", "LEQ", "GEQ", "ADD", "SUB", "PTR", "DIV", "MOD", "IS", 
			"VOID", "CHAR", "INT", "BOOL", "DEL", "DO", "ELSE", "FUN", "IF", "NEW", 
			"THEN", "TYP", "VAR", "WHERE", "WHILE", "VOIDCONST", "BOOLCONST", "POINTERCONST", 
			"CHARCONST", "STRINGCONST", "COMMENT", "INTEGERCONST", "IDENTIFIER", 
			"WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PrevParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PrevParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SourceContext extends ParserRuleContext {
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\7\4\2\t\2\3\2"+
		"\3\2\3\2\2\2\3\2\2\2\2\5\2\4\3\2\2\2\4\5\3\2\2\2\5\3\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}