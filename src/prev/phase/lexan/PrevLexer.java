// Generated from lexan/PrevLexer.g4 by ANTLR 4.9.1

package prev.phase.lexan;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevLexer extends Lexer {
    public static final int
            LPAREN = 1, RPAREN = 2, LBRACKET = 3, RBRACKET = 4, LBRACE = 5, RBRACE = 6, DOT = 7,
            COMMA = 8, SEMIC = 9, COLON = 10, AND = 11, NOT = 12, OR = 13, PTR = 14, EQU = 15, NEQ = 16,
            LTH = 17, GTH = 18, LEQ = 19, GEQ = 20, ADD = 21, SUB = 22, MUL = 23, DIV = 24, MOD = 25,
            IS = 26, VOID = 27, CHAR = 28, INT = 29, BOOL = 30, DEL = 31, DO = 32, ELSE = 33, FUN = 34,
            IF = 35, NEW = 36, THEN = 37, TYP = 38, VAR = 39, WHERE = 40, WHILE = 41, VOIDCONST = 42,
            BOOLCONST = 43, POINTERCONST = 44, CHARCONST = 45, STRINGCONST = 46, COMMENT = 47,
            INTEGERCONST = 48, IDENTIFIER = 49, WHITESPACE = 50;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u0123\b\1\4\2" +
                    "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4" +
                    "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22" +
                    "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31" +
                    "\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t" +
                    " \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t" +
                    "+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2" +
                    "\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3" +
                    "\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21" +
                    "\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27" +
                    "\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34" +
                    "\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37" +
                    "\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3%\3%\3" +
                    "%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3*\3" +
                    "*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u00f1\n,\3" +
                    "-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\5.\u00fe\n.\3/\3/\3/\3/\7/\u0104\n/\f" +
                    "/\16/\u0107\13/\3/\3/\3\60\3\60\7\60\u010d\n\60\f\60\16\60\u0110\13\60" +
                    "\3\60\3\60\3\61\6\61\u0115\n\61\r\61\16\61\u0116\3\62\3\62\7\62\u011b" +
                    "\n\62\f\62\16\62\u011e\13\62\3\63\3\63\3\63\3\63\2\2\64\3\3\5\4\7\5\t" +
                    "\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23" +
                    "%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G" +
                    "%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64\3\2\n\3\2))\4\2\"(*\u0080\4" +
                    "\2\"#%\u0080\4\2\f\f\17\17\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f" +
                    "\17\17\"\"\2\u0129\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13" +
                    "\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2" +
                    "\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2" +
                    "!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3" +
                    "\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2" +
                    "\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E" +
                    "\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2" +
                    "\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2" +
                    "\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3g\3\2\2\2\5i\3\2\2\2\7k" +
                    "\3\2\2\2\tm\3\2\2\2\13o\3\2\2\2\rq\3\2\2\2\17s\3\2\2\2\21u\3\2\2\2\23" +
                    "w\3\2\2\2\25y\3\2\2\2\27{\3\2\2\2\31}\3\2\2\2\33\177\3\2\2\2\35\u0081" +
                    "\3\2\2\2\37\u0083\3\2\2\2!\u0086\3\2\2\2#\u0089\3\2\2\2%\u008b\3\2\2\2" +
                    "\'\u008d\3\2\2\2)\u0090\3\2\2\2+\u0093\3\2\2\2-\u0095\3\2\2\2/\u0097\3" +
                    "\2\2\2\61\u0099\3\2\2\2\63\u009b\3\2\2\2\65\u009d\3\2\2\2\67\u009f\3\2" +
                    "\2\29\u00a4\3\2\2\2;\u00a9\3\2\2\2=\u00ad\3\2\2\2?\u00b2\3\2\2\2A\u00b6" +
                    "\3\2\2\2C\u00b9\3\2\2\2E\u00be\3\2\2\2G\u00c2\3\2\2\2I\u00c5\3\2\2\2K" +
                    "\u00c9\3\2\2\2M\u00ce\3\2\2\2O\u00d2\3\2\2\2Q\u00d6\3\2\2\2S\u00dc\3\2" +
                    "\2\2U\u00e2\3\2\2\2W\u00f0\3\2\2\2Y\u00f2\3\2\2\2[\u00fd\3\2\2\2]\u00ff" +
                    "\3\2\2\2_\u010a\3\2\2\2a\u0114\3\2\2\2c\u0118\3\2\2\2e\u011f\3\2\2\2g" +
                    "h\7*\2\2h\4\3\2\2\2ij\7+\2\2j\6\3\2\2\2kl\7]\2\2l\b\3\2\2\2mn\7_\2\2n" +
                    "\n\3\2\2\2op\7}\2\2p\f\3\2\2\2qr\7\177\2\2r\16\3\2\2\2st\7\60\2\2t\20" +
                    "\3\2\2\2uv\7.\2\2v\22\3\2\2\2wx\7=\2\2x\24\3\2\2\2yz\7<\2\2z\26\3\2\2" +
                    "\2{|\7(\2\2|\30\3\2\2\2}~\7#\2\2~\32\3\2\2\2\177\u0080\7~\2\2\u0080\34" +
                    "\3\2\2\2\u0081\u0082\7`\2\2\u0082\36\3\2\2\2\u0083\u0084\7?\2\2\u0084" +
                    "\u0085\7?\2\2\u0085 \3\2\2\2\u0086\u0087\7#\2\2\u0087\u0088\7?\2\2\u0088" +
                    "\"\3\2\2\2\u0089\u008a\7>\2\2\u008a$\3\2\2\2\u008b\u008c\7@\2\2\u008c" +
                    "&\3\2\2\2\u008d\u008e\7>\2\2\u008e\u008f\7?\2\2\u008f(\3\2\2\2\u0090\u0091" +
                    "\7@\2\2\u0091\u0092\7?\2\2\u0092*\3\2\2\2\u0093\u0094\7-\2\2\u0094,\3" +
                    "\2\2\2\u0095\u0096\7/\2\2\u0096.\3\2\2\2\u0097\u0098\7,\2\2\u0098\60\3" +
                    "\2\2\2\u0099\u009a\7\61\2\2\u009a\62\3\2\2\2\u009b\u009c\7\'\2\2\u009c" +
                    "\64\3\2\2\2\u009d\u009e\7?\2\2\u009e\66\3\2\2\2\u009f\u00a0\7x\2\2\u00a0" +
                    "\u00a1\7q\2\2\u00a1\u00a2\7k\2\2\u00a2\u00a3\7f\2\2\u00a38\3\2\2\2\u00a4" +
                    "\u00a5\7e\2\2\u00a5\u00a6\7j\2\2\u00a6\u00a7\7c\2\2\u00a7\u00a8\7t\2\2" +
                    "\u00a8:\3\2\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7v\2" +
                    "\2\u00ac<\3\2\2\2\u00ad\u00ae\7d\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7" +
                    "q\2\2\u00b0\u00b1\7n\2\2\u00b1>\3\2\2\2\u00b2\u00b3\7f\2\2\u00b3\u00b4" +
                    "\7g\2\2\u00b4\u00b5\7n\2\2\u00b5@\3\2\2\2\u00b6\u00b7\7f\2\2\u00b7\u00b8" +
                    "\7q\2\2\u00b8B\3\2\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc" +
                    "\7u\2\2\u00bc\u00bd\7g\2\2\u00bdD\3\2\2\2\u00be\u00bf\7h\2\2\u00bf\u00c0" +
                    "\7w\2\2\u00c0\u00c1\7p\2\2\u00c1F\3\2\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4" +
                    "\7h\2\2\u00c4H\3\2\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8" +
                    "\7y\2\2\u00c8J\3\2\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7j\2\2\u00cb\u00cc" +
                    "\7g\2\2\u00cc\u00cd\7p\2\2\u00cdL\3\2\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0" +
                    "\7{\2\2\u00d0\u00d1\7r\2\2\u00d1N\3\2\2\2\u00d2\u00d3\7x\2\2\u00d3\u00d4" +
                    "\7c\2\2\u00d4\u00d5\7t\2\2\u00d5P\3\2\2\2\u00d6\u00d7\7y\2\2\u00d7\u00d8" +
                    "\7j\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da\7t\2\2\u00da\u00db\7g\2\2\u00db" +
                    "R\3\2\2\2\u00dc\u00dd\7y\2\2\u00dd\u00de\7j\2\2\u00de\u00df\7k\2\2\u00df" +
                    "\u00e0\7n\2\2\u00e0\u00e1\7g\2\2\u00e1T\3\2\2\2\u00e2\u00e3\7p\2\2\u00e3" +
                    "\u00e4\7q\2\2\u00e4\u00e5\7p\2\2\u00e5\u00e6\7g\2\2\u00e6V\3\2\2\2\u00e7" +
                    "\u00e8\7v\2\2\u00e8\u00e9\7t\2\2\u00e9\u00ea\7w\2\2\u00ea\u00f1\7g\2\2" +
                    "\u00eb\u00ec\7h\2\2\u00ec\u00ed\7c\2\2\u00ed\u00ee\7n\2\2\u00ee\u00ef" +
                    "\7u\2\2\u00ef\u00f1\7g\2\2\u00f0\u00e7\3\2\2\2\u00f0\u00eb\3\2\2\2\u00f1" +
                    "X\3\2\2\2\u00f2\u00f3\7p\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5\7n\2\2\u00f5" +
                    "Z\3\2\2\2\u00f6\u00f7\7)\2\2\u00f7\u00f8\7^\2\2\u00f8\u00f9\7)\2\2\u00f9" +
                    "\u00fe\7)\2\2\u00fa\u00fb\t\2\2\2\u00fb\u00fc\t\3\2\2\u00fc\u00fe\t\2" +
                    "\2\2\u00fd\u00f6\3\2\2\2\u00fd\u00fa\3\2\2\2\u00fe\\\3\2\2\2\u00ff\u0105" +
                    "\7$\2\2\u0100\u0101\7^\2\2\u0101\u0104\7$\2\2\u0102\u0104\t\4\2\2\u0103" +
                    "\u0100\3\2\2\2\u0103\u0102\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2" +
                    "\2\2\u0105\u0106\3\2\2\2\u0106\u0108\3\2\2\2\u0107\u0105\3\2\2\2\u0108" +
                    "\u0109\7$\2\2\u0109^\3\2\2\2\u010a\u010e\7%\2\2\u010b\u010d\n\5\2\2\u010c" +
                    "\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2" +
                    "\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\b\60\2\2\u0112" +
                    "`\3\2\2\2\u0113\u0115\t\6\2\2\u0114\u0113\3\2\2\2\u0115\u0116\3\2\2\2" +
                    "\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117b\3\2\2\2\u0118\u011c\t" +
                    "\7\2\2\u0119\u011b\t\b\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2\2\2\u011c" +
                    "\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011dd\3\2\2\2\u011e\u011c\3\2\2\2" +
                    "\u011f\u0120\t\t\2\2\u0120\u0121\3\2\2\2\u0121\u0122\b\63\2\2\u0122f\3" +
                    "\2\2\2\n\2\u00f0\u00fd\u0103\u0105\u010e\u0116\u011c\3\b\2\2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };
    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    static {
        RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION);
    }

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

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public PrevLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
                "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", "DOT",
                "COMMA", "SEMIC", "COLON", "AND", "NOT", "OR", "PTR", "EQU", "NEQ", "LTH",
                "GTH", "LEQ", "GEQ", "ADD", "SUB", "MUL", "DIV", "MOD", "IS", "VOID",
                "CHAR", "INT", "BOOL", "DEL", "DO", "ELSE", "FUN", "IF", "NEW", "THEN",
                "TYP", "VAR", "WHERE", "WHILE", "VOIDCONST", "BOOLCONST", "POINTERCONST",
                "CHARCONST", "STRINGCONST", "COMMENT", "INTEGERCONST", "IDENTIFIER",
                "WHITESPACE"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'('", "')'", "'['", "']'", "'{'", "'}'", "'.'", "','", "';'",
                "':'", "'&'", "'!'", "'|'", "'^'", "'=='", "'!='", "'<'", "'>'", "'<='",
                "'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'='", "'void'", "'char'",
                "'int'", "'bool'", "'del'", "'do'", "'else'", "'fun'", "'if'", "'new'",
                "'then'", "'typ'", "'var'", "'where'", "'while'", "'none'", null, "'nil'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE",
                "DOT", "COMMA", "SEMIC", "COLON", "AND", "NOT", "OR", "PTR", "EQU", "NEQ",
                "LTH", "GTH", "LEQ", "GEQ", "ADD", "SUB", "MUL", "DIV", "MOD", "IS",
                "VOID", "CHAR", "INT", "BOOL", "DEL", "DO", "ELSE", "FUN", "IF", "NEW",
                "THEN", "TYP", "VAR", "WHERE", "WHILE", "VOIDCONST", "BOOLCONST", "POINTERCONST",
                "CHARCONST", "STRINGCONST", "COMMENT", "INTEGERCONST", "IDENTIFIER",
                "WHITESPACE"
        };
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
    public Token nextToken() {
        return (Token) super.nextToken();
    }

    @Override
    public String getGrammarFileName() {
        return "PrevLexer.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }
}