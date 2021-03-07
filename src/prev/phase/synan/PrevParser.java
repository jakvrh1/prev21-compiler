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
			setState(69);
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
				 ((DeclContext)_localctx).ast =  new AstTypeDecl(Location.createLocation(((DeclContext)_localctx).TYP, ((DeclContext)_localctx).type.ast), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast) ; 
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
				 ((DeclContext)_localctx).ast =  new AstVarDecl(Location.createLocation(((DeclContext)_localctx).VAR, ((DeclContext)_localctx).type.ast), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast) ; 
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
				 String funName = (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null); 
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(38);
					((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(39);
					match(COLON);
					setState(40);
					((DeclContext)_localctx).type = type();
					 parameters.add(new AstParDecl(Location.createLocation(((DeclContext)_localctx).IDENTIFIER, ((DeclContext)_localctx).type.ast), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast)); 
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(42);
						match(COMMA);
						setState(43);
						((DeclContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						setState(44);
						match(COLON);
						setState(45);
						((DeclContext)_localctx).type = type();
						 parameters.add(new AstParDecl(Location.createLocation(((DeclContext)_localctx).IDENTIFIER, ((DeclContext)_localctx).type.ast), (((DeclContext)_localctx).IDENTIFIER!=null?((DeclContext)_localctx).IDENTIFIER.getText():null), ((DeclContext)_localctx).type.ast)); 
						}
						}
						setState(52);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(55);
				match(RPAREN);
				setState(56);
				match(COLON);
				setState(57);
				((DeclContext)_localctx).type = type();
				 Location location = Location.createLocation(((DeclContext)_localctx).FUN, ((DeclContext)_localctx).type.ast); 
				 AstExpr astExpr = null; 
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IS) {
					{
					setState(60);
					match(IS);
					setState(61);
					((DeclContext)_localctx).expr = expr(0);
					 location = Location.createLocation(((DeclContext)_localctx).FUN, ((DeclContext)_localctx).expr.ast); 
					 astExpr = ((DeclContext)_localctx).expr.ast; 
					}
				}

				 ((DeclContext)_localctx).ast =  new AstFunDecl(location, funName, new AstTrees<AstParDecl>(parameters), ((DeclContext)_localctx).type.ast, astExpr); 
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
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				((TypeContext)_localctx).VOID = match(VOID);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.createLocation(((TypeContext)_localctx).VOID), AstAtomType.Type.VOID); 
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				((TypeContext)_localctx).CHAR = match(CHAR);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.createLocation(((TypeContext)_localctx).CHAR), AstAtomType.Type.CHAR); 
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				((TypeContext)_localctx).INT = match(INT);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.createLocation(((TypeContext)_localctx).INT), AstAtomType.Type.INT); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 4);
				{
				setState(77);
				((TypeContext)_localctx).BOOL = match(BOOL);
				 ((TypeContext)_localctx).ast =  new AstAtomType(Location.createLocation(((TypeContext)_localctx).BOOL), AstAtomType.Type.BOOL); 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 5);
				{
				setState(79);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((TypeContext)_localctx).ast =  new AstNameType(Location.createLocation(((TypeContext)_localctx).IDENTIFIER) , (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null)); 
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 6);
				{
				setState(81);
				((TypeContext)_localctx).LBRACKET = match(LBRACKET);
				setState(82);
				((TypeContext)_localctx).expr = expr(0);
				setState(83);
				match(RBRACKET);
				setState(84);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AstArrType(Location.createLocation(((TypeContext)_localctx).LBRACKET, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).type.ast, ((TypeContext)_localctx).expr.ast); 
				}
				break;
			case PTR:
				enterOuterAlt(_localctx, 7);
				{
				setState(87);
				((TypeContext)_localctx).PTR = match(PTR);
				setState(88);
				((TypeContext)_localctx).type = type();
				 ((TypeContext)_localctx).ast =  new AstPtrType(Location.createLocation(((TypeContext)_localctx).PTR, ((TypeContext)_localctx).type.ast), ((TypeContext)_localctx).type.ast); 
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 8);
				{
				setState(91);
				((TypeContext)_localctx).LBRACE = match(LBRACE);
				setState(92);
				((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(93);
				match(COLON);
				setState(94);
				((TypeContext)_localctx).type = type();
				 Vector<AstCompDecl> compDecls = new Vector<AstCompDecl>(); 
				 compDecls.add(new AstCompDecl(Location.createLocation(((TypeContext)_localctx).IDENTIFIER, ((TypeContext)_localctx).type.ast), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), ((TypeContext)_localctx).type.ast)); 
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(97);
					match(COMMA);
					setState(98);
					((TypeContext)_localctx).IDENTIFIER = match(IDENTIFIER);
					setState(99);
					match(COLON);
					setState(100);
					((TypeContext)_localctx).type = type();
					 compDecls.add(new AstCompDecl(Location.createLocation(((TypeContext)_localctx).IDENTIFIER, ((TypeContext)_localctx).type.ast), (((TypeContext)_localctx).IDENTIFIER!=null?((TypeContext)_localctx).IDENTIFIER.getText():null), ((TypeContext)_localctx).type.ast));
					}
					}
					setState(107);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(108);
				((TypeContext)_localctx).RBRACE = match(RBRACE);
				 ((TypeContext)_localctx).ast =  new AstRecType(Location.createLocation(((TypeContext)_localctx).LBRACE, ((TypeContext)_localctx).RBRACE), new AstTrees<AstCompDecl>(compDecls)) ;
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 9);
				{
				setState(111);
				match(LPAREN);
				setState(112);
				((TypeContext)_localctx).type = type();
				setState(113);
				match(RPAREN);
				 ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).type.ast; 
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
		public ExprContext ex1;
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
		public ExprContext ex2;
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
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(119);
				((ExprContext)_localctx).VOIDCONST = match(VOIDCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.createLocation(((ExprContext)_localctx).VOIDCONST), AstAtomExpr.Type.VOID, (((ExprContext)_localctx).VOIDCONST!=null?((ExprContext)_localctx).VOIDCONST.getText():null)); 
				}
				break;
			case 2:
				{
				setState(121);
				((ExprContext)_localctx).BOOLCONST = match(BOOLCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.createLocation(((ExprContext)_localctx).BOOLCONST), AstAtomExpr.Type.BOOL, (((ExprContext)_localctx).BOOLCONST!=null?((ExprContext)_localctx).BOOLCONST.getText():null)); 
				}
				break;
			case 3:
				{
				setState(123);
				((ExprContext)_localctx).POINTERCONST = match(POINTERCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.createLocation(((ExprContext)_localctx).POINTERCONST), AstAtomExpr.Type.POINTER, (((ExprContext)_localctx).POINTERCONST!=null?((ExprContext)_localctx).POINTERCONST.getText():null)); 
				}
				break;
			case 4:
				{
				setState(125);
				((ExprContext)_localctx).CHARCONST = match(CHARCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.createLocation(((ExprContext)_localctx).CHARCONST), AstAtomExpr.Type.CHAR, (((ExprContext)_localctx).CHARCONST!=null?((ExprContext)_localctx).CHARCONST.getText():null)); 
				}
				break;
			case 5:
				{
				setState(127);
				((ExprContext)_localctx).STRINGCONST = match(STRINGCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.createLocation(((ExprContext)_localctx).STRINGCONST), AstAtomExpr.Type.STRING, (((ExprContext)_localctx).STRINGCONST!=null?((ExprContext)_localctx).STRINGCONST.getText():null)); 
				}
				break;
			case 6:
				{
				setState(129);
				((ExprContext)_localctx).INTEGERCONST = match(INTEGERCONST);
				 ((ExprContext)_localctx).ast =  new AstAtomExpr(Location.createLocation(((ExprContext)_localctx).INTEGERCONST), AstAtomExpr.Type.INT, (((ExprContext)_localctx).INTEGERCONST!=null?((ExprContext)_localctx).INTEGERCONST.getText():null)); 
				}
				break;
			case 7:
				{
				setState(131);
				((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				 ((ExprContext)_localctx).ast =  new AstNameExpr(Location.createLocation(((ExprContext)_localctx).IDENTIFIER), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null)); 
				}
				break;
			case 8:
				{
				setState(133);
				((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(134);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				 Vector<AstExpr> expressions = new Vector<AstExpr>(); 
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << PTR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
					{
					setState(136);
					((ExprContext)_localctx).expr = expr(0);
					 expressions.add(((ExprContext)_localctx).expr.ast); 
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(138);
						match(COMMA);
						setState(139);
						((ExprContext)_localctx).expr = expr(0);
						 expressions.add(((ExprContext)_localctx).expr.ast); 
						}
						}
						setState(146);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(149);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				 ((ExprContext)_localctx).ast =  new AstCallExpr(Location.createLocation(((ExprContext)_localctx).IDENTIFIER, ((ExprContext)_localctx).RPAREN), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null), new AstTrees<AstExpr>(expressions)) ;
				}
				break;
			case 9:
				{
				setState(151);
				((ExprContext)_localctx).LBRACE = match(LBRACE);
				 Vector<AstStmt> statements = new Vector<AstStmt>();
				setState(153);
				((ExprContext)_localctx).stmt = stmt();
				 statements.add(((ExprContext)_localctx).stmt.ast); 
				setState(155);
				match(SEMIC);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << PTR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(156);
					((ExprContext)_localctx).stmt = stmt();
					setState(157);
					match(SEMIC);
					 statements.add(((ExprContext)_localctx).stmt.ast); 
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				((ExprContext)_localctx).RBRACE = match(RBRACE);
				 ((ExprContext)_localctx).ast =  new AstStmtExpr(Location.createLocation(((ExprContext)_localctx).LBRACE, ((ExprContext)_localctx).RBRACE), new AstTrees<AstStmt>(statements)); 
				}
				break;
			case 10:
				{
				setState(168);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				setState(169);
				((ExprContext)_localctx).expr = expr(0);
				setState(170);
				match(COLON);
				setState(171);
				((ExprContext)_localctx).type = type();
				setState(172);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				 ((ExprContext)_localctx).ast =  new AstCastExpr(Location.createLocation(((ExprContext)_localctx).LPAREN, ((ExprContext)_localctx).RPAREN), ((ExprContext)_localctx).expr.ast, ((ExprContext)_localctx).type.ast) ; 
				}
				break;
			case 11:
				{
				setState(175);
				((ExprContext)_localctx).LPAREN = match(LPAREN);
				setState(176);
				((ExprContext)_localctx).expr = expr(0);
				setState(177);
				((ExprContext)_localctx).RPAREN = match(RPAREN);
				 ((ExprContext)_localctx).ast =  ((ExprContext)_localctx).expr.ast; 
				}
				break;
			case 12:
				{
				 AstPfxExpr.Oper opr = null; Location location = null; 
				setState(189);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NOT:
					{
					setState(181);
					((ExprContext)_localctx).NOT = match(NOT);
					 opr = AstPfxExpr.Oper.NOT; location = Location.createLocation(((ExprContext)_localctx).NOT); 
					}
					break;
				case PTR:
					{
					setState(183);
					((ExprContext)_localctx).PTR = match(PTR);
					 opr = AstPfxExpr.Oper.PTR; location = Location.createLocation(((ExprContext)_localctx).PTR); 
					}
					break;
				case ADD:
					{
					setState(185);
					((ExprContext)_localctx).ADD = match(ADD);
					 opr = AstPfxExpr.Oper.ADD; location = Location.createLocation(((ExprContext)_localctx).ADD); 
					}
					break;
				case SUB:
					{
					setState(187);
					((ExprContext)_localctx).SUB = match(SUB);
					 opr = AstPfxExpr.Oper.SUB; location = Location.createLocation(((ExprContext)_localctx).SUB); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(191);
				((ExprContext)_localctx).expr = expr(8);
				 ((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(location, ((ExprContext)_localctx).expr.ast.location()), opr, ((ExprContext)_localctx).expr.ast) ;
				}
				break;
			case 13:
				{
				 AstPfxExpr.Oper opr = null; Location location = null;
				setState(199);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NEW:
					{
					setState(195);
					((ExprContext)_localctx).NEW = match(NEW);
					 opr = AstPfxExpr.Oper.NEW; location = Location.createLocation(((ExprContext)_localctx).NEW); 
					}
					break;
				case DEL:
					{
					setState(197);
					((ExprContext)_localctx).DEL = match(DEL);
					 opr = AstPfxExpr.Oper.DEL; location = Location.createLocation(((ExprContext)_localctx).DEL); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(201);
				((ExprContext)_localctx).expr = expr(7);
				 ((ExprContext)_localctx).ast =  new AstPfxExpr(new Location(location, ((ExprContext)_localctx).expr.ast.location()), opr, ((ExprContext)_localctx).expr.ast); 
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(291);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(289);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(206);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						 AstBinExpr.Oper opr = null; 
						setState(214);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case MUL:
							{
							setState(208);
							match(MUL);
							 opr = AstBinExpr.Oper.MUL; 
							}
							break;
						case DIV:
							{
							setState(210);
							match(DIV);
							 opr = AstBinExpr.Oper.DIV; 
							}
							break;
						case MOD:
							{
							setState(212);
							match(MOD);
							 opr = AstBinExpr.Oper.MOD; 
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(216);
						((ExprContext)_localctx).ex2 = ((ExprContext)_localctx).expr = expr(7);
						 ((ExprContext)_localctx).ast =  new AstBinExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast), opr, ((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast) ;
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(219);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						 AstBinExpr.Oper opr = null; 
						setState(225);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case ADD:
							{
							setState(221);
							((ExprContext)_localctx).ADD = match(ADD);
							 opr = AstBinExpr.Oper.ADD; 
							}
							break;
						case SUB:
							{
							setState(223);
							((ExprContext)_localctx).SUB = match(SUB);
							 opr = AstBinExpr.Oper.SUB; 
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(227);
						((ExprContext)_localctx).ex2 = ((ExprContext)_localctx).expr = expr(6);
						 ((ExprContext)_localctx).ast =  new AstBinExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast), opr, ((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast) ;
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(230);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						 AstBinExpr.Oper opr = null; 
						setState(244);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case EQU:
							{
							setState(232);
							match(EQU);
							 opr = AstBinExpr.Oper.EQU; 
							}
							break;
						case NEQ:
							{
							setState(234);
							match(NEQ);
							 opr = AstBinExpr.Oper.NEQ; 
							}
							break;
						case LTH:
							{
							setState(236);
							match(LTH);
							 opr = AstBinExpr.Oper.LTH; 
							}
							break;
						case GTH:
							{
							setState(238);
							match(GTH);
							 opr = AstBinExpr.Oper.GTH; 
							}
							break;
						case LEQ:
							{
							setState(240);
							match(LEQ);
							 opr = AstBinExpr.Oper.LEQ; 
							}
							break;
						case GEQ:
							{
							setState(242);
							match(GEQ);
							 opr = AstBinExpr.Oper.GEQ; 
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(246);
						((ExprContext)_localctx).ex2 = ((ExprContext)_localctx).expr = expr(5);
						 ((ExprContext)_localctx).ast =  new AstBinExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast), opr, ((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast) ;
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(249);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(250);
						match(AND);
						setState(251);
						((ExprContext)_localctx).ex2 = ((ExprContext)_localctx).expr = expr(4);
						 ((ExprContext)_localctx).ast =  new AstBinExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast), AstBinExpr.Oper.AND, ((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast) ;
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(254);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(255);
						match(OR);
						setState(256);
						((ExprContext)_localctx).ex2 = ((ExprContext)_localctx).expr = expr(3);
						 ((ExprContext)_localctx).ast =  new AstBinExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast), AstBinExpr.Oper.OR, ((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast) ;
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(259);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(260);
						match(LBRACKET);
						setState(261);
						((ExprContext)_localctx).ex2 = ((ExprContext)_localctx).expr = expr(0);
						setState(262);
						((ExprContext)_localctx).RBRACKET = match(RBRACKET);
						 ((ExprContext)_localctx).ast =  new AstArrExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).RBRACKET), ((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).ex2.ast); 
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(265);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(266);
						((ExprContext)_localctx).PTR = match(PTR);
						 ((ExprContext)_localctx).ast =  new AstSfxExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).PTR), AstSfxExpr.Oper.PTR, ((ExprContext)_localctx).ex1.ast) ;
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(268);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(269);
						match(DOT);
						setState(270);
						((ExprContext)_localctx).IDENTIFIER = match(IDENTIFIER);
						 ((ExprContext)_localctx).ast =  new AstRecExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).IDENTIFIER), ((ExprContext)_localctx).ex1.ast,
						                      new AstNameExpr(Location.createLocation(((ExprContext)_localctx).IDENTIFIER), (((ExprContext)_localctx).IDENTIFIER!=null?((ExprContext)_localctx).IDENTIFIER.getText():null))); 
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ex1 = _prevctx;
						_localctx.ex1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(273);
						match(WHERE);
						setState(274);
						((ExprContext)_localctx).LBRACE = match(LBRACE);
						 Vector<AstDecl> declarations = new Vector<AstDecl>(); 
						setState(276);
						((ExprContext)_localctx).decl = decl();
						 declarations.add(((ExprContext)_localctx).decl.ast); 
						setState(283);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0)) {
							{
							{
							setState(278);
							((ExprContext)_localctx).decl = decl();
							 declarations.add(((ExprContext)_localctx).decl.ast); 
							}
							}
							setState(285);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(286);
						((ExprContext)_localctx).RBRACE = match(RBRACE);
						 ((ExprContext)_localctx).ast =  new AstWhereExpr(Location.createLocation(((ExprContext)_localctx).ex1.ast, ((ExprContext)_localctx).RBRACE), ((ExprContext)_localctx).ex1.ast, new AstTrees<AstDecl>(declarations)); 
						}
						break;
					}
					} 
				}
				setState(293);
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
		public ExprContext ex1;
		public ExprContext ex2;
		public Token IF;
		public ExprContext expr;
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
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(294);
				((StmtContext)_localctx).ex1 = expr(0);
				 ((StmtContext)_localctx).ast =  new AstExprStmt(((StmtContext)_localctx).ex1.ast.location(), ((StmtContext)_localctx).ex1.ast); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(297);
				((StmtContext)_localctx).ex1 = expr(0);
				setState(298);
				match(IS);
				setState(299);
				((StmtContext)_localctx).ex2 = expr(0);
				 ((StmtContext)_localctx).ast =  new AstAssignStmt(Location.createLocation(((StmtContext)_localctx).ex1.ast, ((StmtContext)_localctx).ex2.ast), ((StmtContext)_localctx).ex1.ast, ((StmtContext)_localctx).ex2.ast); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(302);
				((StmtContext)_localctx).IF = match(IF);
				setState(303);
				((StmtContext)_localctx).expr = expr(0);
				setState(304);
				match(THEN);
				setState(305);
				((StmtContext)_localctx).stmt = stmt();
				 AstStmt thenStmt = ((StmtContext)_localctx).stmt.ast; 
				setState(307);
				match(ELSE);
				setState(308);
				((StmtContext)_localctx).stmt = stmt();
				 AstStmt elseStmt = ((StmtContext)_localctx).stmt.ast; 
				 ((StmtContext)_localctx).ast =  new AstIfStmt(Location.createLocation(((StmtContext)_localctx).IF, elseStmt), ((StmtContext)_localctx).expr.ast, thenStmt, elseStmt); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(312);
				((StmtContext)_localctx).WHILE = match(WHILE);
				setState(313);
				((StmtContext)_localctx).expr = expr(0);
				setState(314);
				match(DO);
				setState(315);
				((StmtContext)_localctx).stmt = stmt();
				 ((StmtContext)_localctx).ast =  new AstWhileStmt(Location.createLocation(((StmtContext)_localctx).WHILE, ((StmtContext)_localctx).stmt.ast), ((StmtContext)_localctx).expr.ast, ((StmtContext)_localctx).stmt.ast); 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u0143\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\6\2\21\n\2\r\2\16\2\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\63\n\3\f\3\16\3"+
		"\66\13\3\5\38\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3D\n\3\3\3"+
		"\3\3\5\3H\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4j\n\4\f\4\16\4m\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4w\n\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0091\n\5\f\5\16\5\u0094\13\5\5\5\u0096\n"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a3\n\5\f\5\16\5"+
		"\u00a6\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00c0\n\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5\u00ca\n\5\3\5\3\5\3\5\5\5\u00cf\n\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5\u00d9\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5"+
		"\u00e4\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\5\5\u00f7\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\7\5\u011c\n\5\f\5\16\5\u011f\13\5\3\5\3\5\3\5\7"+
		"\5\u0124\n\5\f\5\16\5\u0127\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0141\n"+
		"\6\3\6\2\3\b\7\2\4\6\b\n\2\2\2\u0174\2\f\3\2\2\2\4G\3\2\2\2\6v\3\2\2\2"+
		"\b\u00ce\3\2\2\2\n\u0140\3\2\2\2\f\20\b\2\1\2\r\16\5\4\3\2\16\17\b\2\1"+
		"\2\17\21\3\2\2\2\20\r\3\2\2\2\21\22\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2"+
		"\2\23\24\3\2\2\2\24\25\b\2\1\2\25\26\7\2\2\3\26\3\3\2\2\2\27\30\7(\2\2"+
		"\30\31\7\63\2\2\31\32\7\34\2\2\32\33\5\6\4\2\33\34\b\3\1\2\34H\3\2\2\2"+
		"\35\36\7)\2\2\36\37\7\63\2\2\37 \7\f\2\2 !\5\6\4\2!\"\b\3\1\2\"H\3\2\2"+
		"\2#$\7$\2\2$%\7\63\2\2%&\7\3\2\2&\'\b\3\1\2\'\67\b\3\1\2()\7\63\2\2)*"+
		"\7\f\2\2*+\5\6\4\2+\64\b\3\1\2,-\7\n\2\2-.\7\63\2\2./\7\f\2\2/\60\5\6"+
		"\4\2\60\61\b\3\1\2\61\63\3\2\2\2\62,\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2"+
		"\2\64\65\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\67(\3\2\2\2\678\3\2\2\289\3"+
		"\2\2\29:\7\4\2\2:;\7\f\2\2;<\5\6\4\2<=\b\3\1\2=C\b\3\1\2>?\7\34\2\2?@"+
		"\5\b\5\2@A\b\3\1\2AB\b\3\1\2BD\3\2\2\2C>\3\2\2\2CD\3\2\2\2DE\3\2\2\2E"+
		"F\b\3\1\2FH\3\2\2\2G\27\3\2\2\2G\35\3\2\2\2G#\3\2\2\2H\5\3\2\2\2IJ\7\35"+
		"\2\2Jw\b\4\1\2KL\7\36\2\2Lw\b\4\1\2MN\7\37\2\2Nw\b\4\1\2OP\7 \2\2Pw\b"+
		"\4\1\2QR\7\63\2\2Rw\b\4\1\2ST\7\5\2\2TU\5\b\5\2UV\7\6\2\2VW\5\6\4\2WX"+
		"\b\4\1\2Xw\3\2\2\2YZ\7\20\2\2Z[\5\6\4\2[\\\b\4\1\2\\w\3\2\2\2]^\7\7\2"+
		"\2^_\7\63\2\2_`\7\f\2\2`a\5\6\4\2ab\b\4\1\2bk\b\4\1\2cd\7\n\2\2de\7\63"+
		"\2\2ef\7\f\2\2fg\5\6\4\2gh\b\4\1\2hj\3\2\2\2ic\3\2\2\2jm\3\2\2\2ki\3\2"+
		"\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7\b\2\2op\b\4\1\2pw\3\2\2\2qr\7\3"+
		"\2\2rs\5\6\4\2st\7\4\2\2tu\b\4\1\2uw\3\2\2\2vI\3\2\2\2vK\3\2\2\2vM\3\2"+
		"\2\2vO\3\2\2\2vQ\3\2\2\2vS\3\2\2\2vY\3\2\2\2v]\3\2\2\2vq\3\2\2\2w\7\3"+
		"\2\2\2xy\b\5\1\2yz\7,\2\2z\u00cf\b\5\1\2{|\7-\2\2|\u00cf\b\5\1\2}~\7."+
		"\2\2~\u00cf\b\5\1\2\177\u0080\7/\2\2\u0080\u00cf\b\5\1\2\u0081\u0082\7"+
		"\60\2\2\u0082\u00cf\b\5\1\2\u0083\u0084\7\62\2\2\u0084\u00cf\b\5\1\2\u0085"+
		"\u0086\7\63\2\2\u0086\u00cf\b\5\1\2\u0087\u0088\7\63\2\2\u0088\u0089\7"+
		"\3\2\2\u0089\u0095\b\5\1\2\u008a\u008b\5\b\5\2\u008b\u0092\b\5\1\2\u008c"+
		"\u008d\7\n\2\2\u008d\u008e\5\b\5\2\u008e\u008f\b\5\1\2\u008f\u0091\3\2"+
		"\2\2\u0090\u008c\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u008a\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7\4\2\2\u0098"+
		"\u00cf\b\5\1\2\u0099\u009a\7\7\2\2\u009a\u009b\b\5\1\2\u009b\u009c\5\n"+
		"\6\2\u009c\u009d\b\5\1\2\u009d\u00a4\7\13\2\2\u009e\u009f\5\n\6\2\u009f"+
		"\u00a0\7\13\2\2\u00a0\u00a1\b\5\1\2\u00a1\u00a3\3\2\2\2\u00a2\u009e\3"+
		"\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7\b\2\2\u00a8\u00a9\b\5"+
		"\1\2\u00a9\u00cf\3\2\2\2\u00aa\u00ab\7\3\2\2\u00ab\u00ac\5\b\5\2\u00ac"+
		"\u00ad\7\f\2\2\u00ad\u00ae\5\6\4\2\u00ae\u00af\7\4\2\2\u00af\u00b0\b\5"+
		"\1\2\u00b0\u00cf\3\2\2\2\u00b1\u00b2\7\3\2\2\u00b2\u00b3\5\b\5\2\u00b3"+
		"\u00b4\7\4\2\2\u00b4\u00b5\b\5\1\2\u00b5\u00cf\3\2\2\2\u00b6\u00bf\b\5"+
		"\1\2\u00b7\u00b8\7\16\2\2\u00b8\u00c0\b\5\1\2\u00b9\u00ba\7\20\2\2\u00ba"+
		"\u00c0\b\5\1\2\u00bb\u00bc\7\27\2\2\u00bc\u00c0\b\5\1\2\u00bd\u00be\7"+
		"\30\2\2\u00be\u00c0\b\5\1\2\u00bf\u00b7\3\2\2\2\u00bf\u00b9\3\2\2\2\u00bf"+
		"\u00bb\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\5\b"+
		"\5\n\u00c2\u00c3\b\5\1\2\u00c3\u00cf\3\2\2\2\u00c4\u00c9\b\5\1\2\u00c5"+
		"\u00c6\7&\2\2\u00c6\u00ca\b\5\1\2\u00c7\u00c8\7!\2\2\u00c8\u00ca\b\5\1"+
		"\2\u00c9\u00c5\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc"+
		"\5\b\5\t\u00cc\u00cd\b\5\1\2\u00cd\u00cf\3\2\2\2\u00cex\3\2\2\2\u00ce"+
		"{\3\2\2\2\u00ce}\3\2\2\2\u00ce\177\3\2\2\2\u00ce\u0081\3\2\2\2\u00ce\u0083"+
		"\3\2\2\2\u00ce\u0085\3\2\2\2\u00ce\u0087\3\2\2\2\u00ce\u0099\3\2\2\2\u00ce"+
		"\u00aa\3\2\2\2\u00ce\u00b1\3\2\2\2\u00ce\u00b6\3\2\2\2\u00ce\u00c4\3\2"+
		"\2\2\u00cf\u0125\3\2\2\2\u00d0\u00d1\f\b\2\2\u00d1\u00d8\b\5\1\2\u00d2"+
		"\u00d3\7\31\2\2\u00d3\u00d9\b\5\1\2\u00d4\u00d5\7\32\2\2\u00d5\u00d9\b"+
		"\5\1\2\u00d6\u00d7\7\33\2\2\u00d7\u00d9\b\5\1\2\u00d8\u00d2\3\2\2\2\u00d8"+
		"\u00d4\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\5\b"+
		"\5\t\u00db\u00dc\b\5\1\2\u00dc\u0124\3\2\2\2\u00dd\u00de\f\7\2\2\u00de"+
		"\u00e3\b\5\1\2\u00df\u00e0\7\27\2\2\u00e0\u00e4\b\5\1\2\u00e1\u00e2\7"+
		"\30\2\2\u00e2\u00e4\b\5\1\2\u00e3\u00df\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4"+
		"\u00e5\3\2\2\2\u00e5\u00e6\5\b\5\b\u00e6\u00e7\b\5\1\2\u00e7\u0124\3\2"+
		"\2\2\u00e8\u00e9\f\6\2\2\u00e9\u00f6\b\5\1\2\u00ea\u00eb\7\21\2\2\u00eb"+
		"\u00f7\b\5\1\2\u00ec\u00ed\7\22\2\2\u00ed\u00f7\b\5\1\2\u00ee\u00ef\7"+
		"\23\2\2\u00ef\u00f7\b\5\1\2\u00f0\u00f1\7\24\2\2\u00f1\u00f7\b\5\1\2\u00f2"+
		"\u00f3\7\25\2\2\u00f3\u00f7\b\5\1\2\u00f4\u00f5\7\26\2\2\u00f5\u00f7\b"+
		"\5\1\2\u00f6\u00ea\3\2\2\2\u00f6\u00ec\3\2\2\2\u00f6\u00ee\3\2\2\2\u00f6"+
		"\u00f0\3\2\2\2\u00f6\u00f2\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00f9\5\b\5\7\u00f9\u00fa\b\5\1\2\u00fa\u0124\3\2\2\2\u00fb"+
		"\u00fc\f\5\2\2\u00fc\u00fd\7\r\2\2\u00fd\u00fe\5\b\5\6\u00fe\u00ff\b\5"+
		"\1\2\u00ff\u0124\3\2\2\2\u0100\u0101\f\4\2\2\u0101\u0102\7\17\2\2\u0102"+
		"\u0103\5\b\5\5\u0103\u0104\b\5\1\2\u0104\u0124\3\2\2\2\u0105\u0106\f\r"+
		"\2\2\u0106\u0107\7\5\2\2\u0107\u0108\5\b\5\2\u0108\u0109\7\6\2\2\u0109"+
		"\u010a\b\5\1\2\u010a\u0124\3\2\2\2\u010b\u010c\f\f\2\2\u010c\u010d\7\20"+
		"\2\2\u010d\u0124\b\5\1\2\u010e\u010f\f\13\2\2\u010f\u0110\7\t\2\2\u0110"+
		"\u0111\7\63\2\2\u0111\u0124\b\5\1\2\u0112\u0113\f\3\2\2\u0113\u0114\7"+
		"*\2\2\u0114\u0115\7\7\2\2\u0115\u0116\b\5\1\2\u0116\u0117\5\4\3\2\u0117"+
		"\u011d\b\5\1\2\u0118\u0119\5\4\3\2\u0119\u011a\b\5\1\2\u011a\u011c\3\2"+
		"\2\2\u011b\u0118\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0121\7\b"+
		"\2\2\u0121\u0122\b\5\1\2\u0122\u0124\3\2\2\2\u0123\u00d0\3\2\2\2\u0123"+
		"\u00dd\3\2\2\2\u0123\u00e8\3\2\2\2\u0123\u00fb\3\2\2\2\u0123\u0100\3\2"+
		"\2\2\u0123\u0105\3\2\2\2\u0123\u010b\3\2\2\2\u0123\u010e\3\2\2\2\u0123"+
		"\u0112\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126\t\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\5\b\5\2\u0129\u012a"+
		"\b\6\1\2\u012a\u0141\3\2\2\2\u012b\u012c\5\b\5\2\u012c\u012d\7\34\2\2"+
		"\u012d\u012e\5\b\5\2\u012e\u012f\b\6\1\2\u012f\u0141\3\2\2\2\u0130\u0131"+
		"\7%\2\2\u0131\u0132\5\b\5\2\u0132\u0133\7\'\2\2\u0133\u0134\5\n\6\2\u0134"+
		"\u0135\b\6\1\2\u0135\u0136\7#\2\2\u0136\u0137\5\n\6\2\u0137\u0138\b\6"+
		"\1\2\u0138\u0139\b\6\1\2\u0139\u0141\3\2\2\2\u013a\u013b\7+\2\2\u013b"+
		"\u013c\5\b\5\2\u013c\u013d\7\"\2\2\u013d\u013e\5\n\6\2\u013e\u013f\b\6"+
		"\1\2\u013f\u0141\3\2\2\2\u0140\u0128\3\2\2\2\u0140\u012b\3\2\2\2\u0140"+
		"\u0130\3\2\2\2\u0140\u013a\3\2\2\2\u0141\13\3\2\2\2\26\22\64\67CGkv\u0092"+
		"\u0095\u00a4\u00bf\u00c9\u00ce\u00d8\u00e3\u00f6\u011d\u0123\u0125\u0140";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}