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
		RULE_source = 0, RULE_prg = 1, RULE_decl = 2, RULE_type = 3, RULE_expr = 4, 
		RULE_stmt = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "prg", "decl", "type", "expr", "stmt"
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
		public PrgContext prg() {
			return getRuleContext(PrgContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
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
			setState(12);
			prg();
			setState(13);
			match(EOF);
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

	public static class PrgContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public PrgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prg; }
	}

	public final PrgContext prg() throws RecognitionException {
		PrgContext _localctx = new PrgContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			decl();
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0)) {
				{
				{
				setState(16);
				decl();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class DeclContext extends ParserRuleContext {
		public TerminalNode TYP() { return getToken(PrevParser.TYP, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public List<TerminalNode> IS() { return getTokens(PrevParser.IS); }
		public TerminalNode IS(int i) {
			return getToken(PrevParser.IS, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode VAR() { return getToken(PrevParser.VAR, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode FUN() { return getToken(PrevParser.FUN, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		int _la;
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				match(TYP);
				setState(23);
				match(IDENTIFIER);
				setState(24);
				match(IS);
				setState(25);
				type();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(VAR);
				setState(27);
				match(IDENTIFIER);
				setState(28);
				match(COLON);
				setState(29);
				type();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(30);
				match(FUN);
				setState(31);
				match(IDENTIFIER);
				setState(32);
				match(LPAREN);
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(33);
					match(IDENTIFIER);
					setState(34);
					match(COLON);
					setState(35);
					type();
					setState(42);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(36);
						match(COMMA);
						setState(37);
						match(IDENTIFIER);
						setState(38);
						match(COLON);
						setState(39);
						type();
						}
						}
						setState(44);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(47);
				match(RPAREN);
				setState(48);
				match(COLON);
				setState(49);
				type();
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==IS) {
					{
					{
					setState(50);
					match(IS);
					setState(51);
					expr(0);
					}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(PrevParser.VOID, 0); }
		public TerminalNode CHAR() { return getToken(PrevParser.CHAR, 0); }
		public TerminalNode INT() { return getToken(PrevParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(PrevParser.BOOL, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public TerminalNode LBRACKET() { return getToken(PrevParser.LBRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(PrevParser.RBRACKET, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode XOR() { return getToken(PrevParser.XOR, 0); }
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<TerminalNode> COLON() { return getTokens(PrevParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(PrevParser.COLON, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type);
		int _la;
		try {
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				match(VOID);
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(CHAR);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(INT);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
				match(BOOL);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(63);
				match(IDENTIFIER);
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
				match(LBRACKET);
				setState(65);
				expr(0);
				setState(66);
				match(RBRACKET);
				setState(67);
				type();
				}
				break;
			case XOR:
				enterOuterAlt(_localctx, 7);
				{
				setState(69);
				match(XOR);
				setState(70);
				type();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 8);
				{
				setState(71);
				match(LBRACE);
				setState(72);
				match(IDENTIFIER);
				setState(73);
				match(COLON);
				setState(74);
				type();
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(75);
					match(COMMA);
					setState(76);
					match(IDENTIFIER);
					setState(77);
					match(COLON);
					setState(78);
					type();
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(84);
				match(RBRACE);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 9);
				{
				setState(86);
				match(LPAREN);
				setState(87);
				type();
				setState(88);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode VOIDCONST() { return getToken(PrevParser.VOIDCONST, 0); }
		public TerminalNode BOOLCONST() { return getToken(PrevParser.BOOLCONST, 0); }
		public TerminalNode POINTERCONST() { return getToken(PrevParser.POINTERCONST, 0); }
		public TerminalNode CHARCONST() { return getToken(PrevParser.CHARCONST, 0); }
		public TerminalNode STRINGCONST() { return getToken(PrevParser.STRINGCONST, 0); }
		public TerminalNode INTEGERCONST() { return getToken(PrevParser.INTEGERCONST, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PrevParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PrevParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PrevParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PrevParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PrevParser.COMMA, i);
		}
		public TerminalNode LBRACE() { return getToken(PrevParser.LBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(PrevParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(PrevParser.SEMIC, i);
		}
		public TerminalNode RBRACE() { return getToken(PrevParser.RBRACE, 0); }
		public TerminalNode COLON() { return getToken(PrevParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PrevParser.NOT, 0); }
		public TerminalNode XOR() { return getToken(PrevParser.XOR, 0); }
		public TerminalNode ADD() { return getToken(PrevParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
		public TerminalNode DIV() { return getToken(PrevParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(PrevParser.MOD, 0); }
		public TerminalNode EQU() { return getToken(PrevParser.EQU, 0); }
		public TerminalNode NEQ() { return getToken(PrevParser.NEQ, 0); }
		public TerminalNode LTH() { return getToken(PrevParser.LTH, 0); }
		public TerminalNode GTH() { return getToken(PrevParser.GTH, 0); }
		public TerminalNode LEQ() { return getToken(PrevParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(PrevParser.GEQ, 0); }
		public TerminalNode AND() { return getToken(PrevParser.AND, 0); }
		public TerminalNode OR() { return getToken(PrevParser.OR, 0); }
		public TerminalNode LBRACKET() { return getToken(PrevParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PrevParser.RBRACKET, 0); }
		public TerminalNode DOT() { return getToken(PrevParser.DOT, 0); }
		public TerminalNode WHERE() { return getToken(PrevParser.WHERE, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(93);
				match(VOIDCONST);
				}
				break;
			case 2:
				{
				setState(94);
				match(BOOLCONST);
				}
				break;
			case 3:
				{
				setState(95);
				match(POINTERCONST);
				}
				break;
			case 4:
				{
				setState(96);
				match(CHARCONST);
				}
				break;
			case 5:
				{
				setState(97);
				match(STRINGCONST);
				}
				break;
			case 6:
				{
				setState(98);
				match(INTEGERCONST);
				}
				break;
			case 7:
				{
				setState(99);
				match(IDENTIFIER);
				}
				break;
			case 8:
				{
				setState(100);
				match(IDENTIFIER);
				setState(101);
				match(LPAREN);
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << XOR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(102);
					expr(0);
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(103);
						match(COMMA);
						setState(104);
						expr(0);
						}
						}
						setState(109);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(112);
				match(RPAREN);
				}
				break;
			case 9:
				{
				setState(113);
				match(LBRACE);
				setState(114);
				stmt();
				setState(115);
				match(SEMIC);
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << XOR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(116);
					stmt();
					setState(117);
					match(SEMIC);
					}
					}
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(124);
				match(RBRACE);
				}
				break;
			case 10:
				{
				setState(126);
				match(LPAREN);
				setState(127);
				expr(0);
				setState(128);
				match(COLON);
				setState(129);
				type();
				setState(130);
				match(RPAREN);
				}
				break;
			case 11:
				{
				setState(132);
				match(LPAREN);
				setState(133);
				expr(0);
				setState(134);
				match(RPAREN);
				}
				break;
			case 12:
				{
				setState(136);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << XOR) | (1L << ADD) | (1L << SUB))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(137);
				expr(8);
				}
				break;
			case 13:
				{
				setState(138);
				_la = _input.LA(1);
				if ( !(_la==DEL || _la==NEW) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(139);
				expr(7);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(181);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(179);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(142);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(143);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PTR) | (1L << DIV) | (1L << MOD))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(144);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(145);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(146);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(147);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(149);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQU) | (1L << NEQ) | (1L << LTH) | (1L << GTH) | (1L << LEQ) | (1L << GEQ))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(150);
						expr(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(151);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(152);
						match(AND);
						setState(153);
						expr(4);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(154);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(155);
						match(OR);
						setState(156);
						expr(3);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(157);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(158);
						match(LBRACKET);
						setState(159);
						expr(0);
						setState(160);
						match(RBRACKET);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(162);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(163);
						match(XOR);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(165);
						match(DOT);
						setState(166);
						match(IDENTIFIER);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(167);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(168);
						match(WHERE);
						setState(169);
						match(LBRACE);
						setState(170);
						decl();
						setState(174);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0)) {
							{
							{
							setState(171);
							decl();
							}
							}
							setState(176);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(177);
						match(RBRACE);
						}
						break;
					}
					} 
				}
				setState(183);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IS() { return getToken(PrevParser.IS, 0); }
		public TerminalNode IF() { return getToken(PrevParser.IF, 0); }
		public TerminalNode THEN() { return getToken(PrevParser.THEN, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(PrevParser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(PrevParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(PrevParser.DO, 0); }
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stmt);
		try {
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				expr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(185);
				expr(0);
				setState(186);
				match(IS);
				setState(187);
				expr(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(IF);
				setState(190);
				expr(0);
				setState(191);
				match(THEN);
				setState(192);
				stmt();
				setState(193);
				match(ELSE);
				setState(194);
				stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				match(WHILE);
				setState(197);
				expr(0);
				setState(198);
				match(DO);
				setState(199);
				stmt();
				}
				break;
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u00ce\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\7\3\24\n"+
		"\3\f\3\16\3\27\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\7\4+\n\4\f\4\16\4.\13\4\5\4\60\n\4\3\4\3\4\3\4"+
		"\3\4\3\4\7\4\67\n\4\f\4\16\4:\13\4\5\4<\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5R\n\5\f\5\16"+
		"\5U\13\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5]\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\7\6l\n\6\f\6\16\6o\13\6\5\6q\n\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\7\6z\n\6\f\6\16\6}\13\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u008f\n\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00af\n\6\f\6\16\6\u00b2\13\6\3\6\3\6"+
		"\7\6\u00b6\n\6\f\6\16\6\u00b9\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00cc\n\7\3\7\2\3\n\b\2\4\6\b\n\f"+
		"\2\7\5\2\16\16\20\20\27\30\4\2!!&&\3\2\31\33\3\2\27\30\3\2\21\26\2\u00f2"+
		"\2\16\3\2\2\2\4\21\3\2\2\2\6;\3\2\2\2\b\\\3\2\2\2\n\u008e\3\2\2\2\f\u00cb"+
		"\3\2\2\2\16\17\5\4\3\2\17\20\7\2\2\3\20\3\3\2\2\2\21\25\5\6\4\2\22\24"+
		"\5\6\4\2\23\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\5"+
		"\3\2\2\2\27\25\3\2\2\2\30\31\7(\2\2\31\32\7\63\2\2\32\33\7\34\2\2\33<"+
		"\5\b\5\2\34\35\7)\2\2\35\36\7\63\2\2\36\37\7\f\2\2\37<\5\b\5\2 !\7$\2"+
		"\2!\"\7\63\2\2\"/\7\3\2\2#$\7\63\2\2$%\7\f\2\2%,\5\b\5\2&\'\7\n\2\2\'"+
		"(\7\63\2\2()\7\f\2\2)+\5\b\5\2*&\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2"+
		"-\60\3\2\2\2.,\3\2\2\2/#\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\4\2"+
		"\2\62\63\7\f\2\2\638\5\b\5\2\64\65\7\34\2\2\65\67\5\n\6\2\66\64\3\2\2"+
		"\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29<\3\2\2\2:8\3\2\2\2;\30\3\2\2\2;"+
		"\34\3\2\2\2; \3\2\2\2<\7\3\2\2\2=]\7\35\2\2>]\7\36\2\2?]\7\37\2\2@]\7"+
		" \2\2A]\7\63\2\2BC\7\5\2\2CD\5\n\6\2DE\7\6\2\2EF\5\b\5\2F]\3\2\2\2GH\7"+
		"\20\2\2H]\5\b\5\2IJ\7\7\2\2JK\7\63\2\2KL\7\f\2\2LS\5\b\5\2MN\7\n\2\2N"+
		"O\7\63\2\2OP\7\f\2\2PR\5\b\5\2QM\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2"+
		"TV\3\2\2\2US\3\2\2\2VW\7\b\2\2W]\3\2\2\2XY\7\3\2\2YZ\5\b\5\2Z[\7\4\2\2"+
		"[]\3\2\2\2\\=\3\2\2\2\\>\3\2\2\2\\?\3\2\2\2\\@\3\2\2\2\\A\3\2\2\2\\B\3"+
		"\2\2\2\\G\3\2\2\2\\I\3\2\2\2\\X\3\2\2\2]\t\3\2\2\2^_\b\6\1\2_\u008f\7"+
		",\2\2`\u008f\7-\2\2a\u008f\7.\2\2b\u008f\7/\2\2c\u008f\7\60\2\2d\u008f"+
		"\7\62\2\2e\u008f\7\63\2\2fg\7\63\2\2gp\7\3\2\2hm\5\n\6\2ij\7\n\2\2jl\5"+
		"\n\6\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2nq\3\2\2\2om\3\2\2\2ph\3"+
		"\2\2\2pq\3\2\2\2qr\3\2\2\2r\u008f\7\4\2\2st\7\7\2\2tu\5\f\7\2u{\7\13\2"+
		"\2vw\5\f\7\2wx\7\13\2\2xz\3\2\2\2yv\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2"+
		"\2\2|~\3\2\2\2}{\3\2\2\2~\177\7\b\2\2\177\u008f\3\2\2\2\u0080\u0081\7"+
		"\3\2\2\u0081\u0082\5\n\6\2\u0082\u0083\7\f\2\2\u0083\u0084\5\b\5\2\u0084"+
		"\u0085\7\4\2\2\u0085\u008f\3\2\2\2\u0086\u0087\7\3\2\2\u0087\u0088\5\n"+
		"\6\2\u0088\u0089\7\4\2\2\u0089\u008f\3\2\2\2\u008a\u008b\t\2\2\2\u008b"+
		"\u008f\5\n\6\n\u008c\u008d\t\3\2\2\u008d\u008f\5\n\6\t\u008e^\3\2\2\2"+
		"\u008e`\3\2\2\2\u008ea\3\2\2\2\u008eb\3\2\2\2\u008ec\3\2\2\2\u008ed\3"+
		"\2\2\2\u008ee\3\2\2\2\u008ef\3\2\2\2\u008es\3\2\2\2\u008e\u0080\3\2\2"+
		"\2\u008e\u0086\3\2\2\2\u008e\u008a\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u00b7"+
		"\3\2\2\2\u0090\u0091\f\b\2\2\u0091\u0092\t\4\2\2\u0092\u00b6\5\n\6\t\u0093"+
		"\u0094\f\7\2\2\u0094\u0095\t\5\2\2\u0095\u00b6\5\n\6\b\u0096\u0097\f\6"+
		"\2\2\u0097\u0098\t\6\2\2\u0098\u00b6\5\n\6\7\u0099\u009a\f\5\2\2\u009a"+
		"\u009b\7\r\2\2\u009b\u00b6\5\n\6\6\u009c\u009d\f\4\2\2\u009d\u009e\7\17"+
		"\2\2\u009e\u00b6\5\n\6\5\u009f\u00a0\f\r\2\2\u00a0\u00a1\7\5\2\2\u00a1"+
		"\u00a2\5\n\6\2\u00a2\u00a3\7\6\2\2\u00a3\u00b6\3\2\2\2\u00a4\u00a5\f\f"+
		"\2\2\u00a5\u00b6\7\20\2\2\u00a6\u00a7\f\13\2\2\u00a7\u00a8\7\t\2\2\u00a8"+
		"\u00b6\7\63\2\2\u00a9\u00aa\f\3\2\2\u00aa\u00ab\7*\2\2\u00ab\u00ac\7\7"+
		"\2\2\u00ac\u00b0\5\6\4\2\u00ad\u00af\5\6\4\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2"+
		"\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7\b\2\2\u00b4\u00b6\3\2\2\2\u00b5"+
		"\u0090\3\2\2\2\u00b5\u0093\3\2\2\2\u00b5\u0096\3\2\2\2\u00b5\u0099\3\2"+
		"\2\2\u00b5\u009c\3\2\2\2\u00b5\u009f\3\2\2\2\u00b5\u00a4\3\2\2\2\u00b5"+
		"\u00a6\3\2\2\2\u00b5\u00a9\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\13\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00cc"+
		"\5\n\6\2\u00bb\u00bc\5\n\6\2\u00bc\u00bd\7\34\2\2\u00bd\u00be\5\n\6\2"+
		"\u00be\u00cc\3\2\2\2\u00bf\u00c0\7%\2\2\u00c0\u00c1\5\n\6\2\u00c1\u00c2"+
		"\7\'\2\2\u00c2\u00c3\5\f\7\2\u00c3\u00c4\7#\2\2\u00c4\u00c5\5\f\7\2\u00c5"+
		"\u00cc\3\2\2\2\u00c6\u00c7\7+\2\2\u00c7\u00c8\5\n\6\2\u00c8\u00c9\7\""+
		"\2\2\u00c9\u00ca\5\f\7\2\u00ca\u00cc\3\2\2\2\u00cb\u00ba\3\2\2\2\u00cb"+
		"\u00bb\3\2\2\2\u00cb\u00bf\3\2\2\2\u00cb\u00c6\3\2\2\2\u00cc\r\3\2\2\2"+
		"\21\25,/8;S\\mp{\u008e\u00b0\u00b5\u00b7\u00cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}