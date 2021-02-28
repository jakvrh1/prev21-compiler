parser grammar PrevParser;

@header {

	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;
	
}

options{
    tokenVocab=PrevLexer;
}

source : prg EOF;

prg : decl decl* ;

decl    :    TYP IDENTIFIER IS type       |
             VAR IDENTIFIER COLON type    |
             FUN IDENTIFIER LPAREN ( IDENTIFIER COLON type ( COMMA IDENTIFIER COLON type )* )? RPAREN COLON type ( IS expr )*  ;

type    :    VOID | CHAR | INT | BOOL                                                 |
             IDENTIFIER                                                               |
             LBRACKET expr RBRACKET type                                              |
             XOR type                                                                 |
             LBRACE IDENTIFIER COLON type ( COMMA IDENTIFIER COLON type )* RBRACE     |
             LPAREN type RPAREN  ;

expr    :    VOIDCONST | BOOLCONST | POINTERCONST | CHARCONST | STRINGCONST | INTEGERCONST              |
             IDENTIFIER                                                                                 |
             IDENTIFIER LPAREN ( expr ( COMMA expr )* )? RPAREN                                         |
             LBRACE stmt SEMIC ( stmt SEMIC )* RBRACE                                                   |
             LPAREN expr COLON type RPAREN                                                              |
             LPAREN expr RPAREN                                                                         |
             expr LBRACKET expr RBRACKET                                                                |
             expr XOR                                                                                   |
             expr DOT IDENTIFIER                                                                        |
             ( NOT | XOR | ADD | SUB ) expr                                                             |
             ( NEW | DEL ) expr                                                                         |
             expr ( PTR | DIV | MOD ) expr                                                              |
             expr ( ADD | SUB ) expr                                                                    |
             expr ( EQU | NEQ | LTH | GTH | LEQ | GEQ ) expr                                            |
             expr AND expr                                                                              |
             expr OR expr                                                                               |
             expr WHERE LBRACE decl decl* RBRACE ;

stmt    :   expr                        |
            expr IS expr                |
            IF expr THEN stmt ELSE stmt |
            WHILE expr DO stmt ;