lexer grammar PrevLexer;

@header {
	package prev.phase.lexan;
	import prev.common.report.*;
	import prev.data.sym.*;
}

@members {
    @Override
	public Token nextToken() {
		return (Token) super.nextToken();
	}
}

LPAREN      : '(' ;
RPAREN      : ')' ;
LBRACKET    : '[' ;
RBRACKET    : ']' ;
LBRACE      : '{' ;
RBRACE      : '}' ;
DOT         : '.' ;
COMMA       : ',' ;
SEMIC       : ';' ;
COLON       : ':' ;
AND         : '&' ;
NOT         : '!' ;
OR          : '|' ;
XOR         : '^' ;
EQU         : '==' ;
NEQ         : '!=' ;
LTH         : '<' ;
GTH         : '>' ;
LEQ         : '<=' ;
GEQ         : '>=' ;
ADD         : '+' ;
SUB         : '-' ;
PTR         : '*' ;
DIV         : '/' ;
MOD         : '%' ;
IS          : '=' ;
VOID        : 'void' ;
CHAR        : 'char' ;
INT         : 'int' ;
BOOL        : 'bool';
DEL         : 'del' ;
DO          : 'do' ;
ELSE        : 'else' ;
FUN         : 'fun' ;
IF          : 'if' ;
NEW         : 'new' ;
THEN        : 'then' ;
TYP         : 'typ' ;
VAR         : 'var' ;
WHERE       : 'where' ;
WHILE       : 'while' ;

VOIDCONST   : 'none' ;
BOOLCONST   : 'true' | 'false' ;
POINTERCONST: 'nil' ;

CHARCONST   : ('\'\\\'\'') | ['][\u0020-\u0026\u0028-\u007e]['] ;
STRINGCONST :  '"' ('\\"' | [\u0020-\u0021\u0023-\u007e])* '"' ;
COMMENT     : '#' ~[\r\n]*;
INTEGERCONST: [0-9]+ ;

IDENTIFIER : [A-Za-z_][A-Za-z_0-9]* ;

WHITESPACE : [ \t\n\r] -> skip ;
