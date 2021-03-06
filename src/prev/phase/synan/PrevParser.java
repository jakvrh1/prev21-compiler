// Generated from synan/PrevParser.g4 by ANTLR 4.9.1


	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;
	import prev.phase.abstr.*;

	import prev.data.ast.tree.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.type.*;

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
		COMMA=8, SEMIC=9, COLON=10, AND=11, NOT=12, OR=13, PTR=14, EQU=15, NEQ=16, 
		LTH=17, GTH=18, LEQ=19, GEQ=20, ADD=21, SUB=22, MUL=23, DIV=24, MOD=25, 
		IS=26, VOID=27, CHAR=28, INT=29, BOOL=30, DEL=31, DO=32, ELSE=33, FUN=34, 
		IF=35, NEW=36, THEN=37, TYP=38, VAR=39, WHERE=40, WHILE=41, VOIDCONST=42, 
		BOOLCONST=43, POINTERCONST=44, CHARCONST=45, STRINGCONST=46, COMMENT=47, 
		INTEGERCONST=48, IDENTIFIER=49, WHITESPACE=50;
	public static final int
		RULE_source = 0, RULE_decl = 1, RULE_type = 2, RULE_expr = 3, RULE_stmt = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "decl", "type", "expr", "stmt"
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
			"DOT", "COMMA", "SEMIC", "COLON", "AND", "NOT", "OR", "PTR", "EQU", "NEQ", 
			"LTH", "GTH", "LEQ", "GEQ", "ADD", "SUB", "MUL", "DIV", "MOD", "IS", 
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
		public AstTrees<AstDecl> ast;
		public DeclContext decl;
		public TerminalNode EOF() { return getToken(PrevParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 Vector<AstDecl> decls = new Vector<AstDecl>(); 
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(11);
				((SourceContext)_localctx).decl = decl();
				 decls.add(((SourceContext)_localctx).decl.ast); 
				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0) );
			 ((SourceContext)_localctx).ast =  new AstTrees<AstDecl>(decls); 
			setState(19);
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

	public static class DeclContext extends ParserRuleContext {
		public AstDecl ast;
		public Token TYP;
		public Token IDENTIFIER;
		public TypeContext type;
		public Token VAR;
		public Token FUN;
		public ExprContext expr;
		public TerminalNode TYP() { return getToken(PrevParser.TYP, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(PrevParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(PrevParser.IDENTIFIER, i);
		}
		public TerminalNode IS() { return getToken(PrevParser.IS, 0); }
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
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
		enterRule(_localctx, 2, RULE_decl);
		int _la;
		try {
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				((DeclContext)_localctx).TYP = match(TYP);
				setState(22);
				((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(23);
				match(IS);
				setState(24);
				((DeclContext)_localctx).type = type();
				 ((DeclContext)_localctx).ast =  new AstTypeDecl(new Location(Location.consLoc(((DeclContext)_localctx).TYP), ((DeclContext)_localctx).type.ast.location()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast ) ; 
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				((DeclContext)_localctx).VAR = match(VAR);
				setState(28);
				((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(29);
				match(COLON);
				setState(30);
				((DeclContext)_localctx).type = type();
				 ((DeclContext)_localctx).ast =  new AstVarDecl(new Location(Location.consLoc(((DeclContext)_localctx).VAR), ((DeclContext)_localctx).type.ast.location()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast ) ; 
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				((DeclContext)_localctx).FUN = match(FUN);
				setState(34);
				((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(35);
				match(LPAREN);
				 Vector<AstParDecl> parameters = new Vector<AstParDecl>(); 
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(37);
					((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(38);
					match(COLON);
					setState(39);
					((DeclContext)_localctx).type = type();
					 parameters.add(new AstParDecl(new Location(Location.consLoc(((DeclContext)_localctx).IDENTIFIER), ((DeclContext)_localctx).type.ast.location()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast)); 
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(41);
						match(COMMA);
						setState(42);
						((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(43);
						match(COLON);
						setState(44);
						((DeclContext)_localctx).type = type();
						 parameters.add(new AstParDecl(new Location(Location.consLoc(((DeclContext)_localctx).IDENTIFIER), ((DeclContext)_localctx).type.ast.location()), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast)); 
						}
						}
						setState(51);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(54);
				match(RPAREN);
				setState(55);
				match(COLON);
				setState(56);
				((DeclContext)_localctx).type = type();
				 Location loc = new Location(Location.consLoc(((DeclContext)_localctx).FUN), ((DeclContext)_localctx).type.ast.location()); 
				 AstExpr astExpr = null; 
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IS) {
					{
					setState(59);
					match(IS);
					setState(60);
					((DeclContext)_localctx).expr = expr(0);
					 loc = new Location(Location.consLoc(((DeclContext)_localctx).FUN), ((DeclContext)_localctx).expr.ast.location()); 
					 astExpr = ((DeclContext)_localctx).expr.ast; 
					}
				}

				 ((DeclContext)_localctx).ast =  new AstFunDecl(loc, (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), new AstTrees<AstParDecl>(parameters), ((DeclContext)_localctx).type.ast, astExpr); 
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
		public AstType ast;
		public Token VOID;
		public Token CHAR;
		public Token INT;
		public Token BOOL;
		public Token IDENTIFIER;
		public Token LBRACKET;
		public ExprContext expr;
		public TypeContext type;
		public Token PTR;
		public Token LBRACE;
		public Token RBRACE;
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
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
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
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				((TypeContext)_localctx).VOID = match(VOID);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.consLoc(((TypeContext)_localctx).VOID) , AstAtomType.Type.VOID); 
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				((TypeContext)_localctx).CHAR = match(CHAR);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.consLoc(((TypeContext)_localctx).CHAR) , AstAtomType.Type.CHAR); 
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				((TypeContext)_localctx).INT = match(INT);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.consLoc(((TypeContext)_localctx).INT) , AstAtomType.Type.INT); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(76);
				((TypeContext)_localctx).BOOL = match(BOOL);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.consLoc(((TypeContext)_localctx).BOOL) , AstAtomType.Type.BOOL); 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(78);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((TypeContext)_localctx).ast =  new AstNameType(Location.consLoc(((TypeContext)_localctx).IDENTIFIER) , (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null)); 
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(80);
				((TypeContext)_localctx).LBRACKET = match(LBRACKET);
				setState(81);
				((TypeContext)_localctx).expr = expr(0);
				setState(82);
				match(RBRACKET);
				setState(83);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AstArrType(new Location(Location.consLoc(((TypeContext)_localctx).LBRACKET), ((TypeContext)_localctx).type.ast.location()), ((TypeContext)_localctx).type.ast, ((TypeContext)_localctx).expr.ast); 
				}
				break;
			case PTR:
				enterOuterAlt(_localctx, 7);
				{
				setState(86);
				((TypeContext)_localctx).PTR = match(PTR);
				setState(87);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AstPtrType(new Location(Location.consLoc(((TypeContext)_localctx).PTR), ((TypeContext)_localctx).type.ast.location()), ((TypeContext)_localctx).type.ast); 
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 8);
				{
				setState(90);
				((TypeContext)_localctx).LBRACE = match(LBRACE);
				setState(91);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(92);
				match(COLON);
				setState(93);
				((TypeContext)_localctx).type = type();
				 Vector<AstCompDecl> compDecls = new Vector<AstCompDecl>(); 
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(95);
					match(COMMA);
					setState(96);
					((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(97);
					match(COLON);
					setState(98);
					((TypeContext)_localctx).type = type();
					 compDecls.add(new AstCompDecl(new Location(Location.consLoc(((TypeContext)_localctx).IDENTIFIER), ((TypeContext)_localctx).type.ast.location()), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), ((TypeContext)_localctx).type.ast));
					}
					}
					setState(105);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(106);
				((TypeContext)_localctx).RBRACE = match(RBRACE);
				 ((TypeContext)_localctx).ast =  new AstRecType(new Location(Location.consLoc(((TypeContext)_localctx).LBRACE, ((TypeContext)_localctx).RBRACE)), new AstTrees<AstCompDecl>(compDecls)) ;
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 9);
				{
				setState(109);
				match(LPAREN);
				setState(110);
				type();
				setState(111);
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
		public AstExpr ast;
		public Token VOIDCONST;
		public Token BOOLCONST;
		public Token POINTERCONST;
		public Token CHARCONST;
		public Token STRINGCONST;
		public Token INTEGERCONST;
		public Token IDENTIFIER;
		public Token LPAREN;
		public ExprContext expr;
		public Token RPAREN;
		public Token LBRACE;
		public StmtContext stmt;
		public Token RBRACE;
		public TypeContext type;
		public Token NOT;
		public Token PTR;
		public Token ADD;
		public Token SUB;
		public Token NEW;
		public Token DEL;
		public Token MUL;
		public Token DIV;
		public Token MOD;
		public Token EQU;
		public Token NEQ;
		public Token LTH;
		public Token GTH;
		public Token LEQ;
		public Token GEQ;
		public Token AND;
		public Token OR;
		public Token RBRACKET;
		public DeclContext decl;
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
		public TerminalNode PTR() { return getToken(PrevParser.PTR, 0); }
		public TerminalNode ADD() { return getToken(PrevParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(PrevParser.SUB, 0); }
		public TerminalNode NEW() { return getToken(PrevParser.NEW, 0); }
		public TerminalNode DEL() { return getToken(PrevParser.DEL, 0); }
		public TerminalNode MUL() { return getToken(PrevParser.MUL, 0); }
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
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(116);
				((ExprContext)_localctx).VOIDCONST = match(VOIDCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.consLoc(((ExprContext)_localctx).VOIDCONST), AstAtomExpr.Type.VOID, (((ExprContext)_localctx).VOIDCONST!=null?((ExprContext)_localctx).VOIDCONST.getText():null)); 
				}
				break;
			case 2:
				{
				setState(118);
				((ExprContext)_localctx).BOOLCONST = match(BOOLCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.consLoc(((ExprContext)_localctx).BOOLCONST), AstAtomExpr.Type.BOOL, (((ExprContext)_localctx).BOOLCONST!=null?((ExprContext)_localctx).BOOLCONST.getText():null)); 
				}
				break;
			case 3:
				{
				setState(120);
				((ExprContext)_localctx).POINTERCONST = match(POINTERCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.consLoc(((ExprContext)_localctx).POINTERCONST), AstAtomExpr.Type.POINTER, (((ExprContext)_localctx).POINTERCONST!=null?((ExprContext)_localctx).POINTERCONST.getText():null)); 
				}
				break;
			case 4:
				{
				setState(122);
				((ExprContext)_localctx).CHARCONST = match(CHARCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.consLoc(((ExprContext)_localctx).CHARCONST), AstAtomExpr.Type.CHAR, (((ExprContext)_localctx).CHARCONST!=null?((ExprContext)_localctx).CHARCONST.getText():null)); 
				}
				break;
			case 5:
				{
				setState(124);
				((ExprContext)_localctx).STRINGCONST = match(STRINGCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.consLoc(((ExprContext)_localctx).STRINGCONST), AstAtomExpr.Type.STRING, (((ExprContext)_localctx).STRINGCONST!=null?((ExprContext)_localctx).STRINGCONST.getText():null)); 
				}
				break;
			case 6:
				{
				setState(126);
				((ExprContext)_localctx).INTEGERCONST = match(INTEGERCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.consLoc(((ExprContext)_localctx).INTEGERCONST), AstAtomExpr.Type.INT, (((ExprContext)_localctx).INTEGERCONST!=null?((ExprContext)_localctx).INTEGERCONST.getText():null)); 
				}
				break;
			case 7:
				{
				setState(128);
				((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((ExprContext)_localctx).ast =  new AstNameExpr(Location.consLoc(((ExprContext)_localctx).IDENTIFIER), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null)); 
				}
				break;
			case 8:
				{
				setState(130);
				((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(131);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				 Vector<AstExpr> expressions = new Vector<AstExpr>(); 
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << PTR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(133);
					((ExprContext)_localctx).expr = expr(0);
					 expressions.add(((ExprContext)_localctx).expr.ast); 
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(135);
						match(COMMA);
						setState(136);
						((ExprContext)_localctx).expr = expr(0);
						 expressions.add(((ExprContext)_localctx).expr.ast); 
						}
						}
						setState(143);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				 ((ExprContext)_localctx).ast =  new AstCallExpr(Location.consLoc(((ExprContext)_localctx).IDENTIFIER, ((ExprContext)_localctx).RPAREN), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null), new AstTrees<AstExpr>(expressions)) ;
				setState(147);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				}
				break;
			case 9:
				{
				setState(148);
				((ExprContext)_localctx).LBRACE = match(LBRACE);
				 Vector<AstStmt> statements = new Vector<AstStmt>();
				setState(150);
				((ExprContext)_localctx).stmt = stmt();
				 statements.add(((ExprContext)_localctx).stmt.ast); 
				setState(152);
				match(SEMIC);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << PTR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(153);
					((ExprContext)_localctx).stmt = stmt();
					setState(154);
					match(SEMIC);
					 statements.add(((ExprContext)_localctx).stmt.ast); 
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162);
				((ExprContext)_localctx).RBRACE = match(RBRACE);
				 ((ExprContext)_localctx).ast =  new AstStmtExpr(Location.consLoc(((ExprContext)_localctx).LBRACE, ((ExprContext)_localctx).RBRACE), new AstTrees<AstStmt>(statements)); 
				}
				break;
			case 10:
				{
				setState(165);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				setState(166);
				((ExprContext)_localctx).expr = expr(0);
				setState(167);
				match(COLON);
				setState(168);
				((ExprContext)_localctx).type = type();
				setState(169);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				 ((ExprContext)_localctx).ast =  new AstCastExpr(Location.consLoc(((ExprContext)_localctx).LPAREN, ((ExprContext)_localctx).RPAREN), ((ExprContext)_localctx).expr.ast, ((ExprContext)_localctx).type.ast) ; 
				}
				break;
			case 11:
				{
				setState(172);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				setState(173);
				((ExprContext)_localctx).expr = expr(0);
				setState(174);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				}
				break;
			case 12:
				{
				 AstPfxExpr.Oper opr = null; Location loc = null; 
				setState(185);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NOT:
					{
					setState(177);
					((ExprContext)_localctx).NOT = match(NOT);
					 opr = AstPfxExpr.Oper.NOT; loc = Location.consLoc(((ExprContext)_localctx).NOT);  
					}
					break;
				case PTR:
					{
					setState(179);
					((ExprContext)_localctx).PTR = match(PTR);
					 opr = AstPfxExpr.Oper.PTR;  loc = Location.consLoc(((ExprContext)_localctx).PTR); 
					}
					break;
				case ADD:
					{
					setState(181);
					((ExprContext)_localctx).ADD = match(ADD);
					 opr = AstPfxExpr.Oper.ADD;  loc = Location.consLoc(((ExprContext)_localctx).ADD); 
					}
					break;
				case SUB:
					{
					setState(183);
					((ExprContext)_localctx).SUB = match(SUB);
					 opr = AstPfxExpr.Oper.SUB;  loc = Location.consLoc(((ExprContext)_localctx).SUB); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(187);
				((ExprContext)_localctx).expr = expr(8);
				 ((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(loc, ((ExprContext)_localctx).expr.ast.location()), opr, ((ExprContext)_localctx).expr.ast) ;
				}
				break;
			case 13:
				{
				 AstPfxExpr.Oper opr = null; Location loc = null;
				setState(195);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEW:
					{
					setState(191);
					((ExprContext)_localctx).NEW = match(NEW);
					 loc = Location.consLoc(((ExprContext)_localctx).NEW); opr = AstPfxExpr.Oper.NEW; 
					}
					break;
				case DEL:
					{
					setState(193);
					((ExprContext)_localctx).DEL = match(DEL);
					 loc = Location.consLoc(((ExprContext)_localctx).DEL); opr = AstPfxExpr.Oper.DEL; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(197);
				((ExprContext)_localctx).expr = expr(7);
				 ((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(loc, ((ExprContext)_localctx).expr.ast.location()), opr, ((ExprContext)_localctx).expr.ast); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(297);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(295);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(202);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						 AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = ((ExprContext)_localctx).expr.ast; 
						setState(210);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case MUL:
							{
							setState(204);
							((ExprContext)_localctx).MUL = match(MUL);
							 opr = AstBinExpr.Oper.MUL; loc = Location.consLoc(((ExprContext)_localctx).MUL); 
							}
							break;
						case DIV:
							{
							setState(206);
							((ExprContext)_localctx).DIV = match(DIV);
							 opr = AstBinExpr.Oper.DIV; loc = Location.consLoc(((ExprContext)_localctx).DIV); 
							}
							break;
						case MOD:
							{
							setState(208);
							((ExprContext)_localctx).MOD = match(MOD);
							 opr = AstBinExpr.Oper.MOD; loc = Location.consLoc(((ExprContext)_localctx).MOD); 
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(212);
						((ExprContext)_localctx).expr = expr(7);
						 AstExpr expr2 = ((ExprContext)_localctx).expr.ast ;
						 ((ExprContext)_localctx).ast =  new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						 AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = ((ExprContext)_localctx).expr.ast; 
						setState(222);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ADD:
							{
							setState(218);
							((ExprContext)_localctx).ADD = match(ADD);
							 opr = AstBinExpr.Oper.ADD; loc = Location.consLoc(((ExprContext)_localctx).ADD); 
							}
							break;
						case SUB:
							{
							setState(220);
							((ExprContext)_localctx).SUB = match(SUB);
							 opr = AstBinExpr.Oper.SUB; loc = Location.consLoc(((ExprContext)_localctx).SUB); 
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(224);
						((ExprContext)_localctx).expr = expr(6);
						 AstExpr expr2 = ((ExprContext)_localctx).expr.ast; 
						 ((ExprContext)_localctx).ast =  new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(228);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						 AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = ((ExprContext)_localctx).expr.ast; 
						setState(242);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case EQU:
							{
							setState(230);
							((ExprContext)_localctx).EQU = match(EQU);
							 opr = AstBinExpr.Oper.EQU; loc = Location.consLoc(((ExprContext)_localctx).EQU); 
							}
							break;
						case NEQ:
							{
							setState(232);
							((ExprContext)_localctx).NEQ = match(NEQ);
							 opr = AstBinExpr.Oper.NEQ; loc = Location.consLoc(((ExprContext)_localctx).NEQ); 
							}
							break;
						case LTH:
							{
							setState(234);
							((ExprContext)_localctx).LTH = match(LTH);
							 opr = AstBinExpr.Oper.LTH; loc = Location.consLoc(((ExprContext)_localctx).LTH); 
							}
							break;
						case GTH:
							{
							setState(236);
							((ExprContext)_localctx).GTH = match(GTH);
							 opr = AstBinExpr.Oper.GTH; loc = Location.consLoc(((ExprContext)_localctx).GTH); 
							}
							break;
						case LEQ:
							{
							setState(238);
							((ExprContext)_localctx).LEQ = match(LEQ);
							 opr = AstBinExpr.Oper.LEQ; loc = Location.consLoc(((ExprContext)_localctx).LEQ); 
							}
							break;
						case GEQ:
							{
							setState(240);
							((ExprContext)_localctx).GEQ = match(GEQ);
							 opr = AstBinExpr.Oper.GEQ; loc = Location.consLoc(((ExprContext)_localctx).GEQ); 
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(244);
						((ExprContext)_localctx).expr = expr(5);
						 AstExpr expr2 = ((ExprContext)_localctx).expr.ast; 
						 ((ExprContext)_localctx).ast =  new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(248);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						 AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = ((ExprContext)_localctx).expr.ast; 
						setState(250);
						((ExprContext)_localctx).AND = match(AND);
						 opr = AstBinExpr.Oper.AND; loc = Location.consLoc(((ExprContext)_localctx).AND); 
						setState(252);
						((ExprContext)_localctx).expr = expr(4);
						 AstExpr expr2 = ((ExprContext)_localctx).expr.ast; 
						 ((ExprContext)_localctx).ast =  new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(256);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						 AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = ((ExprContext)_localctx).expr.ast; 
						setState(258);
						((ExprContext)_localctx).OR = match(OR);
						 opr = AstBinExpr.Oper.OR; loc = Location.consLoc(((ExprContext)_localctx).OR); 
						setState(260);
						((ExprContext)_localctx).expr = expr(3);
						 AstExpr expr2 = ((ExprContext)_localctx).expr.ast; 
						 ((ExprContext)_localctx).ast =  new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(264);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						 AstExpr expr1 = ((ExprContext)_localctx).expr.ast; 
						setState(266);
						match(LBRACKET);
						setState(267);
						((ExprContext)_localctx).expr = expr(0);
						setState(268);
						((ExprContext)_localctx).RBRACKET = match(RBRACKET);
						 ((ExprContext)_localctx).ast =  new AstArrExpr(new Location(expr1.location(), Location.consLoc(((ExprContext)_localctx).RBRACKET)), expr1, ((ExprContext)_localctx).expr.ast); 
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(271);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(272);
						((ExprContext)_localctx).PTR = match(PTR);
						 ((ExprContext)_localctx).ast =  new AstSfxExpr(new Location(((ExprContext)_localctx).expr.ast.location(), Location.consLoc(((ExprContext)_localctx).PTR)), AstSfxExpr.Oper.PTR, ((ExprContext)_localctx).expr.ast) ;
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(274);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(275);
						match(DOT);
						setState(276);
						((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						 ((ExprContext)_localctx).ast =  new AstRecExpr(new Location(((ExprContext)_localctx).expr.ast.location(), Location.consLoc(((ExprContext)_localctx).IDENTIFIER)), ((ExprContext)_localctx).expr.ast,
						                      new AstNameExpr(Location.consLoc(((ExprContext)_localctx).IDENTIFIER), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null))); 
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(278);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(279);
						match(WHERE);
						setState(280);
						((ExprContext)_localctx).LBRACE = match(LBRACE);
						 Vector<AstDecl> declarations = new Vector<AstDecl>(); 
						setState(282);
						((ExprContext)_localctx).decl = decl();
						 declarations.add(((ExprContext)_localctx).decl.ast); 
						setState(289);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0)) {
							{
							{
							setState(284);
							((ExprContext)_localctx).decl = decl();
							 declarations.add(((ExprContext)_localctx).decl.ast); 
							}
							}
							setState(291);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(292);
						((ExprContext)_localctx).RBRACE = match(RBRACE);
						 ((ExprContext)_localctx).ast =  new AstWhereExpr(new Location(((ExprContext)_localctx).expr.ast.location(), Location.consLoc(((ExprContext)_localctx).RBRACE)), ((ExprContext)_localctx).expr.ast, new AstTrees<AstDecl>(declarations)); 
						}
						break;
					}
					} 
				}
				setState(299);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		public AstStmt ast;
		public ExprContext expr;
		public Token IF;
		public StmtContext stmt;
		public Token WHILE;
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
		enterRule(_localctx, 8, RULE_stmt);
		try {
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				((StmtContext)_localctx).expr = expr(0);
				 ((StmtContext)_localctx).ast =  new AstExprStmt(((StmtContext)_localctx).expr.ast.location(), ((StmtContext)_localctx).expr.ast) ; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				((StmtContext)_localctx).expr = expr(0);
				 AstExpr dest = ((StmtContext)_localctx).expr.ast; 
				setState(305);
				match(IS);
				setState(306);
				((StmtContext)_localctx).expr = expr(0);
				 AstExpr src = ((StmtContext)_localctx).expr.ast; 
				 ((StmtContext)_localctx).ast =  new AstAssignStmt(new Location(dest.location(), src.location()), dest, src); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(310);
				((StmtContext)_localctx).IF = match(IF);
				setState(311);
				((StmtContext)_localctx).expr = expr(0);
				setState(312);
				match(THEN);
				setState(313);
				((StmtContext)_localctx).stmt = stmt();
				 AstStmt thenStmt = ((StmtContext)_localctx).stmt.ast; 
				setState(315);
				match(ELSE);
				setState(316);
				((StmtContext)_localctx).stmt = stmt();
				 AstStmt elseStmt = ((StmtContext)_localctx).stmt.ast; 
				 ((StmtContext)_localctx).ast =  new AstIfStmt(new Location(Location.tokenLoc(((StmtContext)_localctx).IF), elseStmt.location()), ((StmtContext)_localctx).expr.ast, thenStmt, elseStmt); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(320);
				((StmtContext)_localctx).WHILE = match(WHILE);
				setState(321);
				((StmtContext)_localctx).expr = expr(0);
				setState(322);
				match(DO);
				setState(323);
				((StmtContext)_localctx).stmt = stmt();
				 ((StmtContext)_localctx).ast =  new AstWhileStmt(new Location(Location.tokenLoc(((StmtContext)_localctx).WHILE), ((StmtContext)_localctx).stmt.ast.location()), ((StmtContext)_localctx).expr.ast, ((StmtContext)_localctx).stmt.ast); 
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
		case 3:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u014b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\6\2\21\n\2\r\2\16\2\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\62\n\3\f\3\16\3\65"+
		"\13\3\5\3\67\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3C\n\3\3\3"+
		"\3\3\5\3G\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4h\n\4\f\4\16\4k\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4t\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\7\5\u008e\n\5\f\5\16\5\u0091\13\5\5\5\u0093\n\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a0\n\5\f\5\16\5\u00a3\13"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00bc\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5\u00c6\n\5\3\5\3\5\3\5\5\5\u00cb\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5\u00d5\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00e1\n\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\5\5\u00f5\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0122\n\5\f\5\16"+
		"\5\u0125\13\5\3\5\3\5\3\5\7\5\u012a\n\5\f\5\16\5\u012d\13\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0149\n\6\3\6\2\3\b\7\2\4\6\b\n\2\2\2\u017c"+
		"\2\f\3\2\2\2\4F\3\2\2\2\6s\3\2\2\2\b\u00ca\3\2\2\2\n\u0148\3\2\2\2\f\20"+
		"\b\2\1\2\r\16\5\4\3\2\16\17\b\2\1\2\17\21\3\2\2\2\20\r\3\2\2\2\21\22\3"+
		"\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\24\3\2\2\2\24\25\b\2\1\2\25\26\7"+
		"\2\2\3\26\3\3\2\2\2\27\30\7(\2\2\30\31\7\63\2\2\31\32\7\34\2\2\32\33\5"+
		"\6\4\2\33\34\b\3\1\2\34G\3\2\2\2\35\36\7)\2\2\36\37\7\63\2\2\37 \7\f\2"+
		"\2 !\5\6\4\2!\"\b\3\1\2\"G\3\2\2\2#$\7$\2\2$%\7\63\2\2%&\7\3\2\2&\66\b"+
		"\3\1\2\'(\7\63\2\2()\7\f\2\2)*\5\6\4\2*\63\b\3\1\2+,\7\n\2\2,-\7\63\2"+
		"\2-.\7\f\2\2./\5\6\4\2/\60\b\3\1\2\60\62\3\2\2\2\61+\3\2\2\2\62\65\3\2"+
		"\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\66\'\3\2"+
		"\2\2\66\67\3\2\2\2\678\3\2\2\289\7\4\2\29:\7\f\2\2:;\5\6\4\2;<\b\3\1\2"+
		"<B\b\3\1\2=>\7\34\2\2>?\5\b\5\2?@\b\3\1\2@A\b\3\1\2AC\3\2\2\2B=\3\2\2"+
		"\2BC\3\2\2\2CD\3\2\2\2DE\b\3\1\2EG\3\2\2\2F\27\3\2\2\2F\35\3\2\2\2F#\3"+
		"\2\2\2G\5\3\2\2\2HI\7\35\2\2It\b\4\1\2JK\7\36\2\2Kt\b\4\1\2LM\7\37\2\2"+
		"Mt\b\4\1\2NO\7 \2\2Ot\b\4\1\2PQ\7\63\2\2Qt\b\4\1\2RS\7\5\2\2ST\5\b\5\2"+
		"TU\7\6\2\2UV\5\6\4\2VW\b\4\1\2Wt\3\2\2\2XY\7\20\2\2YZ\5\6\4\2Z[\b\4\1"+
		"\2[t\3\2\2\2\\]\7\7\2\2]^\7\63\2\2^_\7\f\2\2_`\5\6\4\2`i\b\4\1\2ab\7\n"+
		"\2\2bc\7\63\2\2cd\7\f\2\2de\5\6\4\2ef\b\4\1\2fh\3\2\2\2ga\3\2\2\2hk\3"+
		"\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7\b\2\2mn\b\4\1\2nt\3"+
		"\2\2\2op\7\3\2\2pq\5\6\4\2qr\7\4\2\2rt\3\2\2\2sH\3\2\2\2sJ\3\2\2\2sL\3"+
		"\2\2\2sN\3\2\2\2sP\3\2\2\2sR\3\2\2\2sX\3\2\2\2s\\\3\2\2\2so\3\2\2\2t\7"+
		"\3\2\2\2uv\b\5\1\2vw\7,\2\2w\u00cb\b\5\1\2xy\7-\2\2y\u00cb\b\5\1\2z{\7"+
		".\2\2{\u00cb\b\5\1\2|}\7/\2\2}\u00cb\b\5\1\2~\177\7\60\2\2\177\u00cb\b"+
		"\5\1\2\u0080\u0081\7\62\2\2\u0081\u00cb\b\5\1\2\u0082\u0083\7\63\2\2\u0083"+
		"\u00cb\b\5\1\2\u0084\u0085\7\63\2\2\u0085\u0086\7\3\2\2\u0086\u0092\b"+
		"\5\1\2\u0087\u0088\5\b\5\2\u0088\u008f\b\5\1\2\u0089\u008a\7\n\2\2\u008a"+
		"\u008b\5\b\5\2\u008b\u008c\b\5\1\2\u008c\u008e\3\2\2\2\u008d\u0089\3\2"+
		"\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0087\3\2\2\2\u0092\u0093\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\5\1\2\u0095\u00cb\7\4\2\2\u0096"+
		"\u0097\7\7\2\2\u0097\u0098\b\5\1\2\u0098\u0099\5\n\6\2\u0099\u009a\b\5"+
		"\1\2\u009a\u00a1\7\13\2\2\u009b\u009c\5\n\6\2\u009c\u009d\7\13\2\2\u009d"+
		"\u009e\b\5\1\2\u009e\u00a0\3\2\2\2\u009f\u009b\3\2\2\2\u00a0\u00a3\3\2"+
		"\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a1\3\2\2\2\u00a4\u00a5\7\b\2\2\u00a5\u00a6\b\5\1\2\u00a6\u00cb\3\2"+
		"\2\2\u00a7\u00a8\7\3\2\2\u00a8\u00a9\5\b\5\2\u00a9\u00aa\7\f\2\2\u00aa"+
		"\u00ab\5\6\4\2\u00ab\u00ac\7\4\2\2\u00ac\u00ad\b\5\1\2\u00ad\u00cb\3\2"+
		"\2\2\u00ae\u00af\7\3\2\2\u00af\u00b0\5\b\5\2\u00b0\u00b1\7\4\2\2\u00b1"+
		"\u00cb\3\2\2\2\u00b2\u00bb\b\5\1\2\u00b3\u00b4\7\16\2\2\u00b4\u00bc\b"+
		"\5\1\2\u00b5\u00b6\7\20\2\2\u00b6\u00bc\b\5\1\2\u00b7\u00b8\7\27\2\2\u00b8"+
		"\u00bc\b\5\1\2\u00b9\u00ba\7\30\2\2\u00ba\u00bc\b\5\1\2\u00bb\u00b3\3"+
		"\2\2\2\u00bb\u00b5\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00be\5\b\5\n\u00be\u00bf\b\5\1\2\u00bf\u00cb\3\2"+
		"\2\2\u00c0\u00c5\b\5\1\2\u00c1\u00c2\7&\2\2\u00c2\u00c6\b\5\1\2\u00c3"+
		"\u00c4\7!\2\2\u00c4\u00c6\b\5\1\2\u00c5\u00c1\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\5\b\5\t\u00c8\u00c9\b\5\1\2\u00c9"+
		"\u00cb\3\2\2\2\u00cau\3\2\2\2\u00cax\3\2\2\2\u00caz\3\2\2\2\u00ca|\3\2"+
		"\2\2\u00ca~\3\2\2\2\u00ca\u0080\3\2\2\2\u00ca\u0082\3\2\2\2\u00ca\u0084"+
		"\3\2\2\2\u00ca\u0096\3\2\2\2\u00ca\u00a7\3\2\2\2\u00ca\u00ae\3\2\2\2\u00ca"+
		"\u00b2\3\2\2\2\u00ca\u00c0\3\2\2\2\u00cb\u012b\3\2\2\2\u00cc\u00cd\f\b"+
		"\2\2\u00cd\u00d4\b\5\1\2\u00ce\u00cf\7\31\2\2\u00cf\u00d5\b\5\1\2\u00d0"+
		"\u00d1\7\32\2\2\u00d1\u00d5\b\5\1\2\u00d2\u00d3\7\33\2\2\u00d3\u00d5\b"+
		"\5\1\2\u00d4\u00ce\3\2\2\2\u00d4\u00d0\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d7\5\b\5\t\u00d7\u00d8\b\5\1\2\u00d8\u00d9\b\5"+
		"\1\2\u00d9\u012a\3\2\2\2\u00da\u00db\f\7\2\2\u00db\u00e0\b\5\1\2\u00dc"+
		"\u00dd\7\27\2\2\u00dd\u00e1\b\5\1\2\u00de\u00df\7\30\2\2\u00df\u00e1\b"+
		"\5\1\2\u00e0\u00dc\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2"+
		"\u00e3\5\b\5\b\u00e3\u00e4\b\5\1\2\u00e4\u00e5\b\5\1\2\u00e5\u012a\3\2"+
		"\2\2\u00e6\u00e7\f\6\2\2\u00e7\u00f4\b\5\1\2\u00e8\u00e9\7\21\2\2\u00e9"+
		"\u00f5\b\5\1\2\u00ea\u00eb\7\22\2\2\u00eb\u00f5\b\5\1\2\u00ec\u00ed\7"+
		"\23\2\2\u00ed\u00f5\b\5\1\2\u00ee\u00ef\7\24\2\2\u00ef\u00f5\b\5\1\2\u00f0"+
		"\u00f1\7\25\2\2\u00f1\u00f5\b\5\1\2\u00f2\u00f3\7\26\2\2\u00f3\u00f5\b"+
		"\5\1\2\u00f4\u00e8\3\2\2\2\u00f4\u00ea\3\2\2\2\u00f4\u00ec\3\2\2\2\u00f4"+
		"\u00ee\3\2\2\2\u00f4\u00f0\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f7\5\b\5\7\u00f7\u00f8\b\5\1\2\u00f8\u00f9\b\5\1\2\u00f9"+
		"\u012a\3\2\2\2\u00fa\u00fb\f\5\2\2\u00fb\u00fc\b\5\1\2\u00fc\u00fd\7\r"+
		"\2\2\u00fd\u00fe\b\5\1\2\u00fe\u00ff\5\b\5\6\u00ff\u0100\b\5\1\2\u0100"+
		"\u0101\b\5\1\2\u0101\u012a\3\2\2\2\u0102\u0103\f\4\2\2\u0103\u0104\b\5"+
		"\1\2\u0104\u0105\7\17\2\2\u0105\u0106\b\5\1\2\u0106\u0107\5\b\5\5\u0107"+
		"\u0108\b\5\1\2\u0108\u0109\b\5\1\2\u0109\u012a\3\2\2\2\u010a\u010b\f\r"+
		"\2\2\u010b\u010c\b\5\1\2\u010c\u010d\7\5\2\2\u010d\u010e\5\b\5\2\u010e"+
		"\u010f\7\6\2\2\u010f\u0110\b\5\1\2\u0110\u012a\3\2\2\2\u0111\u0112\f\f"+
		"\2\2\u0112\u0113\7\20\2\2\u0113\u012a\b\5\1\2\u0114\u0115\f\13\2\2\u0115"+
		"\u0116\7\t\2\2\u0116\u0117\7\63\2\2\u0117\u012a\b\5\1\2\u0118\u0119\f"+
		"\3\2\2\u0119\u011a\7*\2\2\u011a\u011b\7\7\2\2\u011b\u011c\b\5\1\2\u011c"+
		"\u011d\5\4\3\2\u011d\u0123\b\5\1\2\u011e\u011f\5\4\3\2\u011f\u0120\b\5"+
		"\1\2\u0120\u0122\3\2\2\2\u0121\u011e\3\2\2\2\u0122\u0125\3\2\2\2\u0123"+
		"\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125\u0123\3\2"+
		"\2\2\u0126\u0127\7\b\2\2\u0127\u0128\b\5\1\2\u0128\u012a\3\2\2\2\u0129"+
		"\u00cc\3\2\2\2\u0129\u00da\3\2\2\2\u0129\u00e6\3\2\2\2\u0129\u00fa\3\2"+
		"\2\2\u0129\u0102\3\2\2\2\u0129\u010a\3\2\2\2\u0129\u0111\3\2\2\2\u0129"+
		"\u0114\3\2\2\2\u0129\u0118\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2"+
		"\2\2\u012b\u012c\3\2\2\2\u012c\t\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u012f"+
		"\5\b\5\2\u012f\u0130\b\6\1\2\u0130\u0149\3\2\2\2\u0131\u0132\5\b\5\2\u0132"+
		"\u0133\b\6\1\2\u0133\u0134\7\34\2\2\u0134\u0135\5\b\5\2\u0135\u0136\b"+
		"\6\1\2\u0136\u0137\b\6\1\2\u0137\u0149\3\2\2\2\u0138\u0139\7%\2\2\u0139"+
		"\u013a\5\b\5\2\u013a\u013b\7\'\2\2\u013b\u013c\5\n\6\2\u013c\u013d\b\6"+
		"\1\2\u013d\u013e\7#\2\2\u013e\u013f\5\n\6\2\u013f\u0140\b\6\1\2\u0140"+
		"\u0141\b\6\1\2\u0141\u0149\3\2\2\2\u0142\u0143\7+\2\2\u0143\u0144\5\b"+
		"\5\2\u0144\u0145\7\"\2\2\u0145\u0146\5\n\6\2\u0146\u0147\b\6\1\2\u0147"+
		"\u0149\3\2\2\2\u0148\u012e\3\2\2\2\u0148\u0131\3\2\2\2\u0148\u0138\3\2"+
		"\2\2\u0148\u0142\3\2\2\2\u0149\13\3\2\2\2\26\22\63\66BFis\u008f\u0092"+
		"\u00a1\u00bb\u00c5\u00ca\u00d4\u00e0\u00f4\u0123\u0129\u012b\u0148";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}