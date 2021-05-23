// Generated from synan/PrevParser.g4 by ANTLR 4.9.1


package prev.phase.synan;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.TerminalNode;
import prev.common.report.Location;
import prev.data.ast.tree.AstTrees;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.type.*;

import java.util.List;
import java.util.Vector;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PrevParser extends Parser {
    public static final int
            LPAREN = 1, RPAREN = 2, LBRACKET = 3, RBRACKET = 4, LBRACE = 5, RBRACE = 6, DOT = 7,
            COMMA = 8, SEMIC = 9, COLON = 10, AND = 11, NOT = 12, OR = 13, PTR = 14, EQU = 15, NEQ = 16,
            LTH = 17, GTH = 18, LEQ = 19, GEQ = 20, ADD = 21, SUB = 22, MUL = 23, DIV = 24, MOD = 25,
            IS = 26, VOID = 27, CHAR = 28, INT = 29, BOOL = 30, DEL = 31, DO = 32, ELSE = 33, FUN = 34,
            IF = 35, NEW = 36, THEN = 37, TYP = 38, VAR = 39, WHERE = 40, WHILE = 41, VOIDCONST = 42,
            BOOLCONST = 43, POINTERCONST = 44, CHARCONST = 45, STRINGCONST = 46, COMMENT = 47,
            INTEGERCONST = 48, IDENTIFIER = 49, WHITESPACE = 50;
    public static final int
            RULE_source = 0, RULE_decl = 1, RULE_type = 2, RULE_expr = 3, RULE_stmt = 4;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\64\u0145\4\2\t\2" +
                    "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\6\2\21\n\2\r\2\16\2\22" +
                    "\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
                    "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\63\n\3\f\3\16\3" +
                    "\66\13\3\5\38\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3D\n\3\3\3" +
                    "\3\3\5\3H\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4" +
                    "\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3" +
                    "\4\7\4j\n\4\f\4\16\4m\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4x\n" +
                    "\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5" +
                    "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0092\n\5\f\5\16\5\u0095\13\5\5\5\u0097" +
                    "\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a4\n\5\f\5\16" +
                    "\5\u00a7\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5" +
                    "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00c2\n\5\3\5\3\5\3\5" +
                    "\3\5\3\5\3\5\3\5\3\5\5\5\u00cc\n\5\3\5\3\5\3\5\5\5\u00d1\n\5\3\5\3\5\3" +
                    "\5\3\5\3\5\3\5\3\5\3\5\5\5\u00db\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3" +
                    "\5\5\5\u00e6\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3" +
                    "\5\3\5\3\5\3\5\5\5\u00f9\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3" +
                    "\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5" +
                    "\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u011e\n\5\f\5\16\5\u0121\13\5\3\5\3\5" +
                    "\3\5\7\5\u0126\n\5\f\5\16\5\u0129\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6" +
                    "\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0143" +
                    "\n\6\3\6\2\3\b\7\2\4\6\b\n\2\2\2\u0176\2\f\3\2\2\2\4G\3\2\2\2\6w\3\2\2" +
                    "\2\b\u00d0\3\2\2\2\n\u0142\3\2\2\2\f\20\b\2\1\2\r\16\5\4\3\2\16\17\b\2" +
                    "\1\2\17\21\3\2\2\2\20\r\3\2\2\2\21\22\3\2\2\2\22\20\3\2\2\2\22\23\3\2" +
                    "\2\2\23\24\3\2\2\2\24\25\b\2\1\2\25\26\7\2\2\3\26\3\3\2\2\2\27\30\7(\2" +
                    "\2\30\31\7\63\2\2\31\32\7\34\2\2\32\33\5\6\4\2\33\34\b\3\1\2\34H\3\2\2" +
                    "\2\35\36\7)\2\2\36\37\7\63\2\2\37 \7\f\2\2 !\5\6\4\2!\"\b\3\1\2\"H\3\2" +
                    "\2\2#$\7$\2\2$%\7\63\2\2%&\7\3\2\2&\'\b\3\1\2\'\67\b\3\1\2()\7\63\2\2" +
                    ")*\7\f\2\2*+\5\6\4\2+\64\b\3\1\2,-\7\n\2\2-.\7\63\2\2./\7\f\2\2/\60\5" +
                    "\6\4\2\60\61\b\3\1\2\61\63\3\2\2\2\62,\3\2\2\2\63\66\3\2\2\2\64\62\3\2" +
                    "\2\2\64\65\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\67(\3\2\2\2\678\3\2\2\28" +
                    "9\3\2\2\29:\7\4\2\2:;\7\f\2\2;<\5\6\4\2<=\b\3\1\2=C\b\3\1\2>?\7\34\2\2" +
                    "?@\5\b\5\2@A\b\3\1\2AB\b\3\1\2BD\3\2\2\2C>\3\2\2\2CD\3\2\2\2DE\3\2\2\2" +
                    "EF\b\3\1\2FH\3\2\2\2G\27\3\2\2\2G\35\3\2\2\2G#\3\2\2\2H\5\3\2\2\2IJ\7" +
                    "\35\2\2Jx\b\4\1\2KL\7\36\2\2Lx\b\4\1\2MN\7\37\2\2Nx\b\4\1\2OP\7 \2\2P" +
                    "x\b\4\1\2QR\7\63\2\2Rx\b\4\1\2ST\7\5\2\2TU\5\b\5\2UV\7\6\2\2VW\5\6\4\2" +
                    "WX\b\4\1\2Xx\3\2\2\2YZ\7\20\2\2Z[\5\6\4\2[\\\b\4\1\2\\x\3\2\2\2]^\7\7" +
                    "\2\2^_\7\63\2\2_`\7\f\2\2`a\5\6\4\2ab\b\4\1\2bk\b\4\1\2cd\7\n\2\2de\7" +
                    "\63\2\2ef\7\f\2\2fg\5\6\4\2gh\b\4\1\2hj\3\2\2\2ic\3\2\2\2jm\3\2\2\2ki" +
                    "\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7\b\2\2op\b\4\1\2px\3\2\2\2q" +
                    "r\7\3\2\2rs\5\6\4\2st\7\4\2\2tu\b\4\1\2uv\b\4\1\2vx\3\2\2\2wI\3\2\2\2" +
                    "wK\3\2\2\2wM\3\2\2\2wO\3\2\2\2wQ\3\2\2\2wS\3\2\2\2wY\3\2\2\2w]\3\2\2\2" +
                    "wq\3\2\2\2x\7\3\2\2\2yz\b\5\1\2z{\7,\2\2{\u00d1\b\5\1\2|}\7-\2\2}\u00d1" +
                    "\b\5\1\2~\177\7.\2\2\177\u00d1\b\5\1\2\u0080\u0081\7/\2\2\u0081\u00d1" +
                    "\b\5\1\2\u0082\u0083\7\60\2\2\u0083\u00d1\b\5\1\2\u0084\u0085\7\62\2\2" +
                    "\u0085\u00d1\b\5\1\2\u0086\u0087\7\63\2\2\u0087\u00d1\b\5\1\2\u0088\u0089" +
                    "\7\63\2\2\u0089\u008a\7\3\2\2\u008a\u0096\b\5\1\2\u008b\u008c\5\b\5\2" +
                    "\u008c\u0093\b\5\1\2\u008d\u008e\7\n\2\2\u008e\u008f\5\b\5\2\u008f\u0090" +
                    "\b\5\1\2\u0090\u0092\3\2\2\2\u0091\u008d\3\2\2\2\u0092\u0095\3\2\2\2\u0093" +
                    "\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2" +
                    "\2\2\u0096\u008b\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098" +
                    "\u0099\7\4\2\2\u0099\u00d1\b\5\1\2\u009a\u009b\7\7\2\2\u009b\u009c\b\5" +
                    "\1\2\u009c\u009d\5\n\6\2\u009d\u009e\b\5\1\2\u009e\u00a5\7\13\2\2\u009f" +
                    "\u00a0\5\n\6\2\u00a0\u00a1\7\13\2\2\u00a1\u00a2\b\5\1\2\u00a2\u00a4\3" +
                    "\2\2\2\u00a3\u009f\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5" +
                    "\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\7\b" +
                    "\2\2\u00a9\u00aa\b\5\1\2\u00aa\u00d1\3\2\2\2\u00ab\u00ac\7\3\2\2\u00ac" +
                    "\u00ad\5\b\5\2\u00ad\u00ae\7\f\2\2\u00ae\u00af\5\6\4\2\u00af\u00b0\7\4" +
                    "\2\2\u00b0\u00b1\b\5\1\2\u00b1\u00d1\3\2\2\2\u00b2\u00b3\7\3\2\2\u00b3" +
                    "\u00b4\5\b\5\2\u00b4\u00b5\7\4\2\2\u00b5\u00b6\b\5\1\2\u00b6\u00b7\b\5" +
                    "\1\2\u00b7\u00d1\3\2\2\2\u00b8\u00c1\b\5\1\2\u00b9\u00ba\7\16\2\2\u00ba" +
                    "\u00c2\b\5\1\2\u00bb\u00bc\7\20\2\2\u00bc\u00c2\b\5\1\2\u00bd\u00be\7" +
                    "\27\2\2\u00be\u00c2\b\5\1\2\u00bf\u00c0\7\30\2\2\u00c0\u00c2\b\5\1\2\u00c1" +
                    "\u00b9\3\2\2\2\u00c1\u00bb\3\2\2\2\u00c1\u00bd\3\2\2\2\u00c1\u00bf\3\2" +
                    "\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\5\b\5\n\u00c4\u00c5\b\5\1\2\u00c5" +
                    "\u00d1\3\2\2\2\u00c6\u00cb\b\5\1\2\u00c7\u00c8\7&\2\2\u00c8\u00cc\b\5" +
                    "\1\2\u00c9\u00ca\7!\2\2\u00ca\u00cc\b\5\1\2\u00cb\u00c7\3\2\2\2\u00cb" +
                    "\u00c9\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\5\b\5\t\u00ce\u00cf\b\5" +
                    "\1\2\u00cf\u00d1\3\2\2\2\u00d0y\3\2\2\2\u00d0|\3\2\2\2\u00d0~\3\2\2\2" +
                    "\u00d0\u0080\3\2\2\2\u00d0\u0082\3\2\2\2\u00d0\u0084\3\2\2\2\u00d0\u0086" +
                    "\3\2\2\2\u00d0\u0088\3\2\2\2\u00d0\u009a\3\2\2\2\u00d0\u00ab\3\2\2\2\u00d0" +
                    "\u00b2\3\2\2\2\u00d0\u00b8\3\2\2\2\u00d0\u00c6\3\2\2\2\u00d1\u0127\3\2" +
                    "\2\2\u00d2\u00d3\f\b\2\2\u00d3\u00da\b\5\1\2\u00d4\u00d5\7\31\2\2\u00d5" +
                    "\u00db\b\5\1\2\u00d6\u00d7\7\32\2\2\u00d7\u00db\b\5\1\2\u00d8\u00d9\7" +
                    "\33\2\2\u00d9\u00db\b\5\1\2\u00da\u00d4\3\2\2\2\u00da\u00d6\3\2\2\2\u00da" +
                    "\u00d8\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00dd\5\b\5\t\u00dd\u00de\b\5" +
                    "\1\2\u00de\u0126\3\2\2\2\u00df\u00e0\f\7\2\2\u00e0\u00e5\b\5\1\2\u00e1" +
                    "\u00e2\7\27\2\2\u00e2\u00e6\b\5\1\2\u00e3\u00e4\7\30\2\2\u00e4\u00e6\b" +
                    "\5\1\2\u00e5\u00e1\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7" +
                    "\u00e8\5\b\5\b\u00e8\u00e9\b\5\1\2\u00e9\u0126\3\2\2\2\u00ea\u00eb\f\6" +
                    "\2\2\u00eb\u00f8\b\5\1\2\u00ec\u00ed\7\21\2\2\u00ed\u00f9\b\5\1\2\u00ee" +
                    "\u00ef\7\22\2\2\u00ef\u00f9\b\5\1\2\u00f0\u00f1\7\23\2\2\u00f1\u00f9\b" +
                    "\5\1\2\u00f2\u00f3\7\24\2\2\u00f3\u00f9\b\5\1\2\u00f4\u00f5\7\25\2\2\u00f5" +
                    "\u00f9\b\5\1\2\u00f6\u00f7\7\26\2\2\u00f7\u00f9\b\5\1\2\u00f8\u00ec\3" +
                    "\2\2\2\u00f8\u00ee\3\2\2\2\u00f8\u00f0\3\2\2\2\u00f8\u00f2\3\2\2\2\u00f8" +
                    "\u00f4\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\5\b" +
                    "\5\7\u00fb\u00fc\b\5\1\2\u00fc\u0126\3\2\2\2\u00fd\u00fe\f\5\2\2\u00fe" +
                    "\u00ff\7\r\2\2\u00ff\u0100\5\b\5\6\u0100\u0101\b\5\1\2\u0101\u0126\3\2" +
                    "\2\2\u0102\u0103\f\4\2\2\u0103\u0104\7\17\2\2\u0104\u0105\5\b\5\5\u0105" +
                    "\u0106\b\5\1\2\u0106\u0126\3\2\2\2\u0107\u0108\f\r\2\2\u0108\u0109\7\5" +
                    "\2\2\u0109\u010a\5\b\5\2\u010a\u010b\7\6\2\2\u010b\u010c\b\5\1\2\u010c" +
                    "\u0126\3\2\2\2\u010d\u010e\f\f\2\2\u010e\u010f\7\20\2\2\u010f\u0126\b" +
                    "\5\1\2\u0110\u0111\f\13\2\2\u0111\u0112\7\t\2\2\u0112\u0113\7\63\2\2\u0113" +
                    "\u0126\b\5\1\2\u0114\u0115\f\3\2\2\u0115\u0116\7*\2\2\u0116\u0117\7\7" +
                    "\2\2\u0117\u0118\b\5\1\2\u0118\u0119\5\4\3\2\u0119\u011f\b\5\1\2\u011a" +
                    "\u011b\5\4\3\2\u011b\u011c\b\5\1\2\u011c\u011e\3\2\2\2\u011d\u011a\3\2" +
                    "\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120" +
                    "\u0122\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\7\b\2\2\u0123\u0124\b\5" +
                    "\1\2\u0124\u0126\3\2\2\2\u0125\u00d2\3\2\2\2\u0125\u00df\3\2\2\2\u0125" +
                    "\u00ea\3\2\2\2\u0125\u00fd\3\2\2\2\u0125\u0102\3\2\2\2\u0125\u0107\3\2" +
                    "\2\2\u0125\u010d\3\2\2\2\u0125\u0110\3\2\2\2\u0125\u0114\3\2\2\2\u0126" +
                    "\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0127\u0128\3\2\2\2\u0128\t\3\2\2\2" +
                    "\u0129\u0127\3\2\2\2\u012a\u012b\5\b\5\2\u012b\u012c\b\6\1\2\u012c\u0143" +
                    "\3\2\2\2\u012d\u012e\5\b\5\2\u012e\u012f\7\34\2\2\u012f\u0130\5\b\5\2" +
                    "\u0130\u0131\b\6\1\2\u0131\u0143\3\2\2\2\u0132\u0133\7%\2\2\u0133\u0134" +
                    "\5\b\5\2\u0134\u0135\7\'\2\2\u0135\u0136\5\n\6\2\u0136\u0137\b\6\1\2\u0137" +
                    "\u0138\7#\2\2\u0138\u0139\5\n\6\2\u0139\u013a\b\6\1\2\u013a\u013b\b\6" +
                    "\1\2\u013b\u0143\3\2\2\2\u013c\u013d\7+\2\2\u013d\u013e\5\b\5\2\u013e" +
                    "\u013f\7\"\2\2\u013f\u0140\5\n\6\2\u0140\u0141\b\6\1\2\u0141\u0143\3\2" +
                    "\2\2\u0142\u012a\3\2\2\2\u0142\u012d\3\2\2\2\u0142\u0132\3\2\2\2\u0142" +
                    "\u013c\3\2\2\2\u0143\13\3\2\2\2\26\22\64\67CGkw\u0093\u0096\u00a5\u00c1" +
                    "\u00cb\u00d0\u00da\u00e5\u00f8\u011f\u0125\u0127\u0142";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

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

    public PrevParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
                "source", "decl", "type", "expr", "stmt"
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
    public String getGrammarFileName() {
        return "PrevParser.g4";
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
    public ATN getATN() {
        return _ATN;
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
                            ((SourceContext) _localctx).decl = decl();
                            decls.add(((SourceContext) _localctx).decl.ast);
                        }
                    }
                    setState(16);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0));
                ((SourceContext) _localctx).ast = new AstTrees<AstDecl>(decls);
                setState(19);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
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
                    ((DeclContext) _localctx).TYP = match(TYP);
                    setState(22);
                    ((DeclContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                    setState(23);
                    match(IS);
                    setState(24);
                    ((DeclContext) _localctx).type = type();
                    ((DeclContext) _localctx).ast = new AstTypeDecl(Location.createLocation(((DeclContext) _localctx).TYP, ((DeclContext) _localctx).type.ast), (((DeclContext) _localctx).IDENTIFIER != null ? ((DeclContext) _localctx).IDENTIFIER.getText() : null), ((DeclContext) _localctx).type.ast);
                }
                break;
                case VAR:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(27);
                    ((DeclContext) _localctx).VAR = match(VAR);
                    setState(28);
                    ((DeclContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                    setState(29);
                    match(COLON);
                    setState(30);
                    ((DeclContext) _localctx).type = type();
                    ((DeclContext) _localctx).ast = new AstVarDecl(Location.createLocation(((DeclContext) _localctx).VAR, ((DeclContext) _localctx).type.ast), (((DeclContext) _localctx).IDENTIFIER != null ? ((DeclContext) _localctx).IDENTIFIER.getText() : null), ((DeclContext) _localctx).type.ast);
                }
                break;
                case FUN:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(33);
                    ((DeclContext) _localctx).FUN = match(FUN);
                    setState(34);
                    ((DeclContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                    setState(35);
                    match(LPAREN);
                    Vector<AstParDecl> parameters = new Vector<AstParDecl>();
                    String funName = (((DeclContext) _localctx).IDENTIFIER != null ? ((DeclContext) _localctx).IDENTIFIER.getText() : null);
                    setState(53);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == IDENTIFIER) {
                        {
                            setState(38);
                            ((DeclContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                            setState(39);
                            match(COLON);
                            setState(40);
                            ((DeclContext) _localctx).type = type();
                            parameters.add(new AstParDecl(Location.createLocation(((DeclContext) _localctx).IDENTIFIER, ((DeclContext) _localctx).type.ast), (((DeclContext) _localctx).IDENTIFIER != null ? ((DeclContext) _localctx).IDENTIFIER.getText() : null), ((DeclContext) _localctx).type.ast));
                            setState(50);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == COMMA) {
                                {
                                    {
                                        setState(42);
                                        match(COMMA);
                                        setState(43);
                                        ((DeclContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                                        setState(44);
                                        match(COLON);
                                        setState(45);
                                        ((DeclContext) _localctx).type = type();
                                        parameters.add(new AstParDecl(Location.createLocation(((DeclContext) _localctx).IDENTIFIER, ((DeclContext) _localctx).type.ast), (((DeclContext) _localctx).IDENTIFIER != null ? ((DeclContext) _localctx).IDENTIFIER.getText() : null), ((DeclContext) _localctx).type.ast));
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
                    ((DeclContext) _localctx).type = type();
                    Location location = Location.createLocation(((DeclContext) _localctx).FUN, ((DeclContext) _localctx).type.ast);
                    AstExpr astExpr = null;
                    setState(65);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == IS) {
                        {
                            setState(60);
                            match(IS);
                            setState(61);
                            ((DeclContext) _localctx).expr = expr(0);
                            location = Location.createLocation(((DeclContext) _localctx).FUN, ((DeclContext) _localctx).expr.ast);
                            astExpr = ((DeclContext) _localctx).expr.ast;
                        }
                    }

                    ((DeclContext) _localctx).ast = new AstFunDecl(location, funName, new AstTrees<AstParDecl>(parameters), ((DeclContext) _localctx).type.ast, astExpr);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeContext type() throws RecognitionException {
        TypeContext _localctx = new TypeContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_type);
        int _la;
        try {
            setState(117);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case VOID:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(71);
                    ((TypeContext) _localctx).VOID = match(VOID);
                    ((TypeContext) _localctx).ast = new AstAtomType(Location.createLocation(((TypeContext) _localctx).VOID), AstAtomType.Type.VOID);
                }
                break;
                case CHAR:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(73);
                    ((TypeContext) _localctx).CHAR = match(CHAR);
                    ((TypeContext) _localctx).ast = new AstAtomType(Location.createLocation(((TypeContext) _localctx).CHAR), AstAtomType.Type.CHAR);
                }
                break;
                case INT:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(75);
                    ((TypeContext) _localctx).INT = match(INT);
                    ((TypeContext) _localctx).ast = new AstAtomType(Location.createLocation(((TypeContext) _localctx).INT), AstAtomType.Type.INT);
                }
                break;
                case BOOL:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(77);
                    ((TypeContext) _localctx).BOOL = match(BOOL);
                    ((TypeContext) _localctx).ast = new AstAtomType(Location.createLocation(((TypeContext) _localctx).BOOL), AstAtomType.Type.BOOL);
                }
                break;
                case IDENTIFIER:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(79);
                    ((TypeContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                    ((TypeContext) _localctx).ast = new AstNameType(Location.createLocation(((TypeContext) _localctx).IDENTIFIER), (((TypeContext) _localctx).IDENTIFIER != null ? ((TypeContext) _localctx).IDENTIFIER.getText() : null));
                }
                break;
                case LBRACKET:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(81);
                    ((TypeContext) _localctx).LBRACKET = match(LBRACKET);
                    setState(82);
                    ((TypeContext) _localctx).expr = expr(0);
                    setState(83);
                    match(RBRACKET);
                    setState(84);
                    ((TypeContext) _localctx).type = type();
                    ((TypeContext) _localctx).ast = new AstArrType(Location.createLocation(((TypeContext) _localctx).LBRACKET, ((TypeContext) _localctx).type.ast), ((TypeContext) _localctx).type.ast, ((TypeContext) _localctx).expr.ast);
                }
                break;
                case PTR:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(87);
                    ((TypeContext) _localctx).PTR = match(PTR);
                    setState(88);
                    ((TypeContext) _localctx).type = type();
                    ((TypeContext) _localctx).ast = new AstPtrType(Location.createLocation(((TypeContext) _localctx).PTR, ((TypeContext) _localctx).type.ast), ((TypeContext) _localctx).type.ast);
                }
                break;
                case LBRACE:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(91);
                    ((TypeContext) _localctx).LBRACE = match(LBRACE);
                    setState(92);
                    ((TypeContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                    setState(93);
                    match(COLON);
                    setState(94);
                    ((TypeContext) _localctx).type = type();
                    Vector<AstCompDecl> compDecls = new Vector<AstCompDecl>();
                    compDecls.add(new AstCompDecl(Location.createLocation(((TypeContext) _localctx).IDENTIFIER, ((TypeContext) _localctx).type.ast), (((TypeContext) _localctx).IDENTIFIER != null ? ((TypeContext) _localctx).IDENTIFIER.getText() : null), ((TypeContext) _localctx).type.ast));
                    setState(105);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == COMMA) {
                        {
                            {
                                setState(97);
                                match(COMMA);
                                setState(98);
                                ((TypeContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                                setState(99);
                                match(COLON);
                                setState(100);
                                ((TypeContext) _localctx).type = type();
                                compDecls.add(new AstCompDecl(Location.createLocation(((TypeContext) _localctx).IDENTIFIER, ((TypeContext) _localctx).type.ast), (((TypeContext) _localctx).IDENTIFIER != null ? ((TypeContext) _localctx).IDENTIFIER.getText() : null), ((TypeContext) _localctx).type.ast));
                            }
                        }
                        setState(107);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(108);
                    ((TypeContext) _localctx).RBRACE = match(RBRACE);
                    ((TypeContext) _localctx).ast = new AstRecType(Location.createLocation(((TypeContext) _localctx).LBRACE, ((TypeContext) _localctx).RBRACE), new AstTrees<AstCompDecl>(compDecls));
                }
                break;
                case LPAREN:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(111);
                    ((TypeContext) _localctx).LPAREN = match(LPAREN);
                    setState(112);
                    ((TypeContext) _localctx).type = type();
                    setState(113);
                    ((TypeContext) _localctx).RPAREN = match(RPAREN);
                    ((TypeContext) _localctx).ast = ((TypeContext) _localctx).type.ast;
                    _localctx.ast.relocate(Location.createLocation(((TypeContext) _localctx).LPAREN, ((TypeContext) _localctx).RPAREN));
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
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
                setState(206);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 12, _ctx)) {
                    case 1: {
                        setState(120);
                        ((ExprContext) _localctx).VOIDCONST = match(VOIDCONST);
                        ((ExprContext) _localctx).ast = new AstAtomExpr(Location.createLocation(((ExprContext) _localctx).VOIDCONST), AstAtomExpr.Type.VOID, (((ExprContext) _localctx).VOIDCONST != null ? ((ExprContext) _localctx).VOIDCONST.getText() : null));
                    }
                    break;
                    case 2: {
                        setState(122);
                        ((ExprContext) _localctx).BOOLCONST = match(BOOLCONST);
                        ((ExprContext) _localctx).ast = new AstAtomExpr(Location.createLocation(((ExprContext) _localctx).BOOLCONST), AstAtomExpr.Type.BOOL, (((ExprContext) _localctx).BOOLCONST != null ? ((ExprContext) _localctx).BOOLCONST.getText() : null));
                    }
                    break;
                    case 3: {
                        setState(124);
                        ((ExprContext) _localctx).POINTERCONST = match(POINTERCONST);
                        ((ExprContext) _localctx).ast = new AstAtomExpr(Location.createLocation(((ExprContext) _localctx).POINTERCONST), AstAtomExpr.Type.POINTER, (((ExprContext) _localctx).POINTERCONST != null ? ((ExprContext) _localctx).POINTERCONST.getText() : null));
                    }
                    break;
                    case 4: {
                        setState(126);
                        ((ExprContext) _localctx).CHARCONST = match(CHARCONST);
                        ((ExprContext) _localctx).ast = new AstAtomExpr(Location.createLocation(((ExprContext) _localctx).CHARCONST), AstAtomExpr.Type.CHAR, (((ExprContext) _localctx).CHARCONST != null ? ((ExprContext) _localctx).CHARCONST.getText() : null));
                    }
                    break;
                    case 5: {
                        setState(128);
                        ((ExprContext) _localctx).STRINGCONST = match(STRINGCONST);
                        ((ExprContext) _localctx).ast = new AstAtomExpr(Location.createLocation(((ExprContext) _localctx).STRINGCONST), AstAtomExpr.Type.STRING, (((ExprContext) _localctx).STRINGCONST != null ? ((ExprContext) _localctx).STRINGCONST.getText() : null));
                    }
                    break;
                    case 6: {
                        setState(130);
                        ((ExprContext) _localctx).INTEGERCONST = match(INTEGERCONST);
                        ((ExprContext) _localctx).ast = new AstAtomExpr(Location.createLocation(((ExprContext) _localctx).INTEGERCONST), AstAtomExpr.Type.INT, (((ExprContext) _localctx).INTEGERCONST != null ? ((ExprContext) _localctx).INTEGERCONST.getText() : null));
                    }
                    break;
                    case 7: {
                        setState(132);
                        ((ExprContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                        ((ExprContext) _localctx).ast = new AstNameExpr(Location.createLocation(((ExprContext) _localctx).IDENTIFIER), (((ExprContext) _localctx).IDENTIFIER != null ? ((ExprContext) _localctx).IDENTIFIER.getText() : null));
                    }
                    break;
                    case 8: {
                        setState(134);
                        ((ExprContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                        setState(135);
                        ((ExprContext) _localctx).LPAREN = match(LPAREN);
                        Vector<AstExpr> expressions = new Vector<AstExpr>();
                        setState(148);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << PTR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << NEW) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
                            {
                                setState(137);
                                ((ExprContext) _localctx).expr = expr(0);
                                expressions.add(((ExprContext) _localctx).expr.ast);
                                setState(145);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                while (_la == COMMA) {
                                    {
                                        {
                                            setState(139);
                                            match(COMMA);
                                            setState(140);
                                            ((ExprContext) _localctx).expr = expr(0);
                                            expressions.add(((ExprContext) _localctx).expr.ast);
                                        }
                                    }
                                    setState(147);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                }
                            }
                        }

                        setState(150);
                        ((ExprContext) _localctx).RPAREN = match(RPAREN);
                        ((ExprContext) _localctx).ast = new AstCallExpr(Location.createLocation(((ExprContext) _localctx).IDENTIFIER, ((ExprContext) _localctx).RPAREN), (((ExprContext) _localctx).IDENTIFIER != null ? ((ExprContext) _localctx).IDENTIFIER.getText() : null), new AstTrees<AstExpr>(expressions));
                    }
                    break;
                    case 9: {
                        setState(152);
                        ((ExprContext) _localctx).LBRACE = match(LBRACE);
                        Vector<AstStmt> statements = new Vector<AstStmt>();
                        setState(154);
                        ((ExprContext) _localctx).stmt = stmt();
                        statements.add(((ExprContext) _localctx).stmt.ast);
                        setState(156);
                        match(SEMIC);
                        setState(163);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << LBRACE) | (1L << NOT) | (1L << PTR) | (1L << ADD) | (1L << SUB) | (1L << DEL) | (1L << IF) | (1L << NEW) | (1L << WHILE) | (1L << VOIDCONST) | (1L << BOOLCONST) | (1L << POINTERCONST) | (1L << CHARCONST) | (1L << STRINGCONST) | (1L << INTEGERCONST) | (1L << IDENTIFIER))) != 0)) {
                            {
                                {
                                    setState(157);
                                    ((ExprContext) _localctx).stmt = stmt();
                                    setState(158);
                                    match(SEMIC);
                                    statements.add(((ExprContext) _localctx).stmt.ast);
                                }
                            }
                            setState(165);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                        setState(166);
                        ((ExprContext) _localctx).RBRACE = match(RBRACE);
                        ((ExprContext) _localctx).ast = new AstStmtExpr(Location.createLocation(((ExprContext) _localctx).LBRACE, ((ExprContext) _localctx).RBRACE), new AstTrees<AstStmt>(statements));
                    }
                    break;
                    case 10: {
                        setState(169);
                        ((ExprContext) _localctx).LPAREN = match(LPAREN);
                        setState(170);
                        ((ExprContext) _localctx).expr = expr(0);
                        setState(171);
                        match(COLON);
                        setState(172);
                        ((ExprContext) _localctx).type = type();
                        setState(173);
                        ((ExprContext) _localctx).RPAREN = match(RPAREN);
                        ((ExprContext) _localctx).ast = new AstCastExpr(Location.createLocation(((ExprContext) _localctx).LPAREN, ((ExprContext) _localctx).RPAREN), ((ExprContext) _localctx).expr.ast, ((ExprContext) _localctx).type.ast);
                    }
                    break;
                    case 11: {
                        setState(176);
                        ((ExprContext) _localctx).LPAREN = match(LPAREN);
                        setState(177);
                        ((ExprContext) _localctx).expr = expr(0);
                        setState(178);
                        ((ExprContext) _localctx).RPAREN = match(RPAREN);
                        ((ExprContext) _localctx).ast = ((ExprContext) _localctx).expr.ast;
                        _localctx.ast.relocate(Location.createLocation(((ExprContext) _localctx).LPAREN, ((ExprContext) _localctx).RPAREN));
                    }
                    break;
                    case 12: {
                        AstPfxExpr.Oper opr = null;
                        Location location = null;
                        setState(191);
                        _errHandler.sync(this);
                        switch (_input.LA(1)) {
                            case NOT: {
                                setState(183);
                                ((ExprContext) _localctx).NOT = match(NOT);
                                opr = AstPfxExpr.Oper.NOT;
                                location = Location.createLocation(((ExprContext) _localctx).NOT);
                            }
                            break;
                            case PTR: {
                                setState(185);
                                ((ExprContext) _localctx).PTR = match(PTR);
                                opr = AstPfxExpr.Oper.PTR;
                                location = Location.createLocation(((ExprContext) _localctx).PTR);
                            }
                            break;
                            case ADD: {
                                setState(187);
                                ((ExprContext) _localctx).ADD = match(ADD);
                                opr = AstPfxExpr.Oper.ADD;
                                location = Location.createLocation(((ExprContext) _localctx).ADD);
                            }
                            break;
                            case SUB: {
                                setState(189);
                                ((ExprContext) _localctx).SUB = match(SUB);
                                opr = AstPfxExpr.Oper.SUB;
                                location = Location.createLocation(((ExprContext) _localctx).SUB);
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                        setState(193);
                        ((ExprContext) _localctx).expr = expr(8);
                        ((ExprContext) _localctx).ast = new AstPfxExpr(new Location(location, ((ExprContext) _localctx).expr.ast.location()), opr, ((ExprContext) _localctx).expr.ast);
                    }
                    break;
                    case 13: {
                        AstPfxExpr.Oper opr = null;
                        Location location = null;
                        setState(201);
                        _errHandler.sync(this);
                        switch (_input.LA(1)) {
                            case NEW: {
                                setState(197);
                                ((ExprContext) _localctx).NEW = match(NEW);
                                opr = AstPfxExpr.Oper.NEW;
                                location = Location.createLocation(((ExprContext) _localctx).NEW);
                            }
                            break;
                            case DEL: {
                                setState(199);
                                ((ExprContext) _localctx).DEL = match(DEL);
                                opr = AstPfxExpr.Oper.DEL;
                                location = Location.createLocation(((ExprContext) _localctx).DEL);
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                        setState(203);
                        ((ExprContext) _localctx).expr = expr(7);
                        ((ExprContext) _localctx).ast = new AstPfxExpr(new Location(location, ((ExprContext) _localctx).expr.ast.location()), opr, ((ExprContext) _localctx).expr.ast);
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(293);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(291);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 17, _ctx)) {
                                case 1: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(208);
                                    if (!(precpred(_ctx, 6)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 6)");
                                    AstBinExpr.Oper opr = null;
                                    setState(216);
                                    _errHandler.sync(this);
                                    switch (_input.LA(1)) {
                                        case MUL: {
                                            setState(210);
                                            match(MUL);
                                            opr = AstBinExpr.Oper.MUL;
                                        }
                                        break;
                                        case DIV: {
                                            setState(212);
                                            match(DIV);
                                            opr = AstBinExpr.Oper.DIV;
                                        }
                                        break;
                                        case MOD: {
                                            setState(214);
                                            match(MOD);
                                            opr = AstBinExpr.Oper.MOD;
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(218);
                                    ((ExprContext) _localctx).ex2 = ((ExprContext) _localctx).expr = expr(7);
                                    ((ExprContext) _localctx).ast = new AstBinExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast), opr, ((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast);
                                }
                                break;
                                case 2: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(221);
                                    if (!(precpred(_ctx, 5)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                    AstBinExpr.Oper opr = null;
                                    setState(227);
                                    _errHandler.sync(this);
                                    switch (_input.LA(1)) {
                                        case ADD: {
                                            setState(223);
                                            ((ExprContext) _localctx).ADD = match(ADD);
                                            opr = AstBinExpr.Oper.ADD;
                                        }
                                        break;
                                        case SUB: {
                                            setState(225);
                                            ((ExprContext) _localctx).SUB = match(SUB);
                                            opr = AstBinExpr.Oper.SUB;
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(229);
                                    ((ExprContext) _localctx).ex2 = ((ExprContext) _localctx).expr = expr(6);
                                    ((ExprContext) _localctx).ast = new AstBinExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast), opr, ((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast);
                                }
                                break;
                                case 3: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(232);
                                    if (!(precpred(_ctx, 4)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 4)");
                                    AstBinExpr.Oper opr = null;
                                    setState(246);
                                    _errHandler.sync(this);
                                    switch (_input.LA(1)) {
                                        case EQU: {
                                            setState(234);
                                            match(EQU);
                                            opr = AstBinExpr.Oper.EQU;
                                        }
                                        break;
                                        case NEQ: {
                                            setState(236);
                                            match(NEQ);
                                            opr = AstBinExpr.Oper.NEQ;
                                        }
                                        break;
                                        case LTH: {
                                            setState(238);
                                            match(LTH);
                                            opr = AstBinExpr.Oper.LTH;
                                        }
                                        break;
                                        case GTH: {
                                            setState(240);
                                            match(GTH);
                                            opr = AstBinExpr.Oper.GTH;
                                        }
                                        break;
                                        case LEQ: {
                                            setState(242);
                                            match(LEQ);
                                            opr = AstBinExpr.Oper.LEQ;
                                        }
                                        break;
                                        case GEQ: {
                                            setState(244);
                                            match(GEQ);
                                            opr = AstBinExpr.Oper.GEQ;
                                        }
                                        break;
                                        default:
                                            throw new NoViableAltException(this);
                                    }
                                    setState(248);
                                    ((ExprContext) _localctx).ex2 = ((ExprContext) _localctx).expr = expr(5);
                                    ((ExprContext) _localctx).ast = new AstBinExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast), opr, ((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast);
                                }
                                break;
                                case 4: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(251);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(252);
                                    match(AND);
                                    setState(253);
                                    ((ExprContext) _localctx).ex2 = ((ExprContext) _localctx).expr = expr(4);
                                    ((ExprContext) _localctx).ast = new AstBinExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast), AstBinExpr.Oper.AND, ((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast);
                                }
                                break;
                                case 5: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(256);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(257);
                                    match(OR);
                                    setState(258);
                                    ((ExprContext) _localctx).ex2 = ((ExprContext) _localctx).expr = expr(3);
                                    ((ExprContext) _localctx).ast = new AstBinExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast), AstBinExpr.Oper.OR, ((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast);
                                }
                                break;
                                case 6: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(261);
                                    if (!(precpred(_ctx, 11)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 11)");
                                    setState(262);
                                    match(LBRACKET);
                                    setState(263);
                                    ((ExprContext) _localctx).ex2 = ((ExprContext) _localctx).expr = expr(0);
                                    setState(264);
                                    ((ExprContext) _localctx).RBRACKET = match(RBRACKET);
                                    ((ExprContext) _localctx).ast = new AstArrExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).RBRACKET), ((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).ex2.ast);
                                }
                                break;
                                case 7: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(267);
                                    if (!(precpred(_ctx, 10)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 10)");
                                    setState(268);
                                    ((ExprContext) _localctx).PTR = match(PTR);
                                    ((ExprContext) _localctx).ast = new AstSfxExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).PTR), AstSfxExpr.Oper.PTR, ((ExprContext) _localctx).ex1.ast);
                                }
                                break;
                                case 8: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(270);
                                    if (!(precpred(_ctx, 9)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 9)");
                                    setState(271);
                                    match(DOT);
                                    setState(272);
                                    ((ExprContext) _localctx).IDENTIFIER = match(IDENTIFIER);
                                    ((ExprContext) _localctx).ast = new AstRecExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).IDENTIFIER), ((ExprContext) _localctx).ex1.ast,
                                            new AstNameExpr(Location.createLocation(((ExprContext) _localctx).IDENTIFIER), (((ExprContext) _localctx).IDENTIFIER != null ? ((ExprContext) _localctx).IDENTIFIER.getText() : null)));
                                }
                                break;
                                case 9: {
                                    _localctx = new ExprContext(_parentctx, _parentState);
                                    _localctx.ex1 = _prevctx;
                                    _localctx.ex1 = _prevctx;
                                    pushNewRecursionContext(_localctx, _startState, RULE_expr);
                                    setState(274);
                                    if (!(precpred(_ctx, 1)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 1)");
                                    setState(275);
                                    match(WHERE);
                                    setState(276);
                                    ((ExprContext) _localctx).LBRACE = match(LBRACE);
                                    Vector<AstDecl> declarations = new Vector<AstDecl>();
                                    setState(278);
                                    ((ExprContext) _localctx).decl = decl();
                                    declarations.add(((ExprContext) _localctx).decl.ast);
                                    setState(285);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FUN) | (1L << TYP) | (1L << VAR))) != 0)) {
                                        {
                                            {
                                                setState(280);
                                                ((ExprContext) _localctx).decl = decl();
                                                declarations.add(((ExprContext) _localctx).decl.ast);
                                            }
                                        }
                                        setState(287);
                                        _errHandler.sync(this);
                                        _la = _input.LA(1);
                                    }
                                    setState(288);
                                    ((ExprContext) _localctx).RBRACE = match(RBRACE);
                                    ((ExprContext) _localctx).ast = new AstWhereExpr(Location.createLocation(((ExprContext) _localctx).ex1.ast, ((ExprContext) _localctx).RBRACE), ((ExprContext) _localctx).ex1.ast, new AstTrees<AstDecl>(declarations));
                                }
                                break;
                            }
                        }
                    }
                    setState(295);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final StmtContext stmt() throws RecognitionException {
        StmtContext _localctx = new StmtContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_stmt);
        try {
            setState(320);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 19, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(296);
                    ((StmtContext) _localctx).ex1 = expr(0);
                    ((StmtContext) _localctx).ast = new AstExprStmt(((StmtContext) _localctx).ex1.ast.location(), ((StmtContext) _localctx).ex1.ast);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(299);
                    ((StmtContext) _localctx).ex1 = expr(0);
                    setState(300);
                    match(IS);
                    setState(301);
                    ((StmtContext) _localctx).ex2 = expr(0);
                    ((StmtContext) _localctx).ast = new AstAssignStmt(Location.createLocation(((StmtContext) _localctx).ex1.ast, ((StmtContext) _localctx).ex2.ast), ((StmtContext) _localctx).ex1.ast, ((StmtContext) _localctx).ex2.ast);
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(304);
                    ((StmtContext) _localctx).IF = match(IF);
                    setState(305);
                    ((StmtContext) _localctx).expr = expr(0);
                    setState(306);
                    match(THEN);
                    setState(307);
                    ((StmtContext) _localctx).stmt = stmt();
                    AstStmt thenStmt = ((StmtContext) _localctx).stmt.ast;
                    setState(309);
                    match(ELSE);
                    setState(310);
                    ((StmtContext) _localctx).stmt = stmt();
                    AstStmt elseStmt = ((StmtContext) _localctx).stmt.ast;
                    ((StmtContext) _localctx).ast = new AstIfStmt(Location.createLocation(((StmtContext) _localctx).IF, elseStmt), ((StmtContext) _localctx).expr.ast, thenStmt, elseStmt);
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(314);
                    ((StmtContext) _localctx).WHILE = match(WHILE);
                    setState(315);
                    ((StmtContext) _localctx).expr = expr(0);
                    setState(316);
                    match(DO);
                    setState(317);
                    ((StmtContext) _localctx).stmt = stmt();
                    ((StmtContext) _localctx).ast = new AstWhileStmt(Location.createLocation(((StmtContext) _localctx).WHILE, ((StmtContext) _localctx).stmt.ast), ((StmtContext) _localctx).expr.ast, ((StmtContext) _localctx).stmt.ast);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 3:
                return expr_sempred((ExprContext) _localctx, predIndex);
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

    public static class SourceContext extends ParserRuleContext {
        public AstTrees<AstDecl> ast;
        public DeclContext decl;

        public SourceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode EOF() {
            return getToken(PrevParser.EOF, 0);
        }

        public List<DeclContext> decl() {
            return getRuleContexts(DeclContext.class);
        }

        public DeclContext decl(int i) {
            return getRuleContext(DeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_source;
        }
    }

    public static class DeclContext extends ParserRuleContext {
        public AstDecl ast;
        public Token TYP;
        public Token IDENTIFIER;
        public TypeContext type;
        public Token VAR;
        public Token FUN;
        public ExprContext expr;

        public DeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TYP() {
            return getToken(PrevParser.TYP, 0);
        }

        public List<TerminalNode> IDENTIFIER() {
            return getTokens(PrevParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(int i) {
            return getToken(PrevParser.IDENTIFIER, i);
        }

        public TerminalNode IS() {
            return getToken(PrevParser.IS, 0);
        }

        public List<TypeContext> type() {
            return getRuleContexts(TypeContext.class);
        }

        public TypeContext type(int i) {
            return getRuleContext(TypeContext.class, i);
        }

        public TerminalNode VAR() {
            return getToken(PrevParser.VAR, 0);
        }

        public List<TerminalNode> COLON() {
            return getTokens(PrevParser.COLON);
        }

        public TerminalNode COLON(int i) {
            return getToken(PrevParser.COLON, i);
        }

        public TerminalNode FUN() {
            return getToken(PrevParser.FUN, 0);
        }

        public TerminalNode LPAREN() {
            return getToken(PrevParser.LPAREN, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(PrevParser.RPAREN, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(PrevParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(PrevParser.COMMA, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_decl;
        }
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
        public Token LPAREN;
        public Token RPAREN;

        public TypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode VOID() {
            return getToken(PrevParser.VOID, 0);
        }

        public TerminalNode CHAR() {
            return getToken(PrevParser.CHAR, 0);
        }

        public TerminalNode INT() {
            return getToken(PrevParser.INT, 0);
        }

        public TerminalNode BOOL() {
            return getToken(PrevParser.BOOL, 0);
        }

        public List<TerminalNode> IDENTIFIER() {
            return getTokens(PrevParser.IDENTIFIER);
        }

        public TerminalNode IDENTIFIER(int i) {
            return getToken(PrevParser.IDENTIFIER, i);
        }

        public TerminalNode LBRACKET() {
            return getToken(PrevParser.LBRACKET, 0);
        }

        public ExprContext expr() {
            return getRuleContext(ExprContext.class, 0);
        }

        public TerminalNode RBRACKET() {
            return getToken(PrevParser.RBRACKET, 0);
        }

        public List<TypeContext> type() {
            return getRuleContexts(TypeContext.class);
        }

        public TypeContext type(int i) {
            return getRuleContext(TypeContext.class, i);
        }

        public TerminalNode PTR() {
            return getToken(PrevParser.PTR, 0);
        }

        public TerminalNode LBRACE() {
            return getToken(PrevParser.LBRACE, 0);
        }

        public List<TerminalNode> COLON() {
            return getTokens(PrevParser.COLON);
        }

        public TerminalNode COLON(int i) {
            return getToken(PrevParser.COLON, i);
        }

        public TerminalNode RBRACE() {
            return getToken(PrevParser.RBRACE, 0);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(PrevParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(PrevParser.COMMA, i);
        }

        public TerminalNode LPAREN() {
            return getToken(PrevParser.LPAREN, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(PrevParser.RPAREN, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_type;
        }
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

        public ExprContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode VOIDCONST() {
            return getToken(PrevParser.VOIDCONST, 0);
        }

        public TerminalNode BOOLCONST() {
            return getToken(PrevParser.BOOLCONST, 0);
        }

        public TerminalNode POINTERCONST() {
            return getToken(PrevParser.POINTERCONST, 0);
        }

        public TerminalNode CHARCONST() {
            return getToken(PrevParser.CHARCONST, 0);
        }

        public TerminalNode STRINGCONST() {
            return getToken(PrevParser.STRINGCONST, 0);
        }

        public TerminalNode INTEGERCONST() {
            return getToken(PrevParser.INTEGERCONST, 0);
        }

        public TerminalNode IDENTIFIER() {
            return getToken(PrevParser.IDENTIFIER, 0);
        }

        public TerminalNode LPAREN() {
            return getToken(PrevParser.LPAREN, 0);
        }

        public TerminalNode RPAREN() {
            return getToken(PrevParser.RPAREN, 0);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public List<TerminalNode> COMMA() {
            return getTokens(PrevParser.COMMA);
        }

        public TerminalNode COMMA(int i) {
            return getToken(PrevParser.COMMA, i);
        }

        public TerminalNode LBRACE() {
            return getToken(PrevParser.LBRACE, 0);
        }

        public List<StmtContext> stmt() {
            return getRuleContexts(StmtContext.class);
        }

        public StmtContext stmt(int i) {
            return getRuleContext(StmtContext.class, i);
        }

        public List<TerminalNode> SEMIC() {
            return getTokens(PrevParser.SEMIC);
        }

        public TerminalNode SEMIC(int i) {
            return getToken(PrevParser.SEMIC, i);
        }

        public TerminalNode RBRACE() {
            return getToken(PrevParser.RBRACE, 0);
        }

        public TerminalNode COLON() {
            return getToken(PrevParser.COLON, 0);
        }

        public TypeContext type() {
            return getRuleContext(TypeContext.class, 0);
        }

        public TerminalNode NOT() {
            return getToken(PrevParser.NOT, 0);
        }

        public TerminalNode PTR() {
            return getToken(PrevParser.PTR, 0);
        }

        public TerminalNode ADD() {
            return getToken(PrevParser.ADD, 0);
        }

        public TerminalNode SUB() {
            return getToken(PrevParser.SUB, 0);
        }

        public TerminalNode NEW() {
            return getToken(PrevParser.NEW, 0);
        }

        public TerminalNode DEL() {
            return getToken(PrevParser.DEL, 0);
        }

        public TerminalNode MUL() {
            return getToken(PrevParser.MUL, 0);
        }

        public TerminalNode DIV() {
            return getToken(PrevParser.DIV, 0);
        }

        public TerminalNode MOD() {
            return getToken(PrevParser.MOD, 0);
        }

        public TerminalNode EQU() {
            return getToken(PrevParser.EQU, 0);
        }

        public TerminalNode NEQ() {
            return getToken(PrevParser.NEQ, 0);
        }

        public TerminalNode LTH() {
            return getToken(PrevParser.LTH, 0);
        }

        public TerminalNode GTH() {
            return getToken(PrevParser.GTH, 0);
        }

        public TerminalNode LEQ() {
            return getToken(PrevParser.LEQ, 0);
        }

        public TerminalNode GEQ() {
            return getToken(PrevParser.GEQ, 0);
        }

        public TerminalNode AND() {
            return getToken(PrevParser.AND, 0);
        }

        public TerminalNode OR() {
            return getToken(PrevParser.OR, 0);
        }

        public TerminalNode LBRACKET() {
            return getToken(PrevParser.LBRACKET, 0);
        }

        public TerminalNode RBRACKET() {
            return getToken(PrevParser.RBRACKET, 0);
        }

        public TerminalNode DOT() {
            return getToken(PrevParser.DOT, 0);
        }

        public TerminalNode WHERE() {
            return getToken(PrevParser.WHERE, 0);
        }

        public List<DeclContext> decl() {
            return getRuleContexts(DeclContext.class);
        }

        public DeclContext decl(int i) {
            return getRuleContext(DeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expr;
        }
    }

    public static class StmtContext extends ParserRuleContext {
        public AstStmt ast;
        public ExprContext ex1;
        public ExprContext ex2;
        public Token IF;
        public ExprContext expr;
        public StmtContext stmt;
        public Token WHILE;

        public StmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ExprContext> expr() {
            return getRuleContexts(ExprContext.class);
        }

        public ExprContext expr(int i) {
            return getRuleContext(ExprContext.class, i);
        }

        public TerminalNode IS() {
            return getToken(PrevParser.IS, 0);
        }

        public TerminalNode IF() {
            return getToken(PrevParser.IF, 0);
        }

        public TerminalNode THEN() {
            return getToken(PrevParser.THEN, 0);
        }

        public List<StmtContext> stmt() {
            return getRuleContexts(StmtContext.class);
        }

        public StmtContext stmt(int i) {
            return getRuleContext(StmtContext.class, i);
        }

        public TerminalNode ELSE() {
            return getToken(PrevParser.ELSE, 0);
        }

        public TerminalNode WHILE() {
            return getToken(PrevParser.WHILE, 0);
        }

        public TerminalNode DO() {
            return getToken(PrevParser.DO, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stmt;
        }
    }
}