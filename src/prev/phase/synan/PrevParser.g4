parser grammar PrevParser;

@header {

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
}

options{
    tokenVocab=PrevLexer;
}

source returns [AstTrees<AstDecl> ast] :
             { Vector<AstDecl> decls = new Vector<AstDecl>(); }
             ( decl { decls.add($decl.ast); } )+
             { $ast = new AstTrees<AstDecl>(decls); }
             EOF
             ;

//{ $astDecl = new AstTypeDecl(#TYP.getLine(), IDENTIFIER, type); decls.add($astDecl); }
decl returns [AstDecl ast] :
             // type declaration
             TYP IDENTIFIER IS type
             { $ast = new AstTypeDecl(new Location(Location.consLoc($TYP), $type.ast.location()), $IDENTIFIER.text, $type.ast ) ; }
             |

             // variable declaration
             VAR IDENTIFIER COLON type
             { $ast = new AstVarDecl(new Location(Location.consLoc($VAR), $type.ast.location()), $IDENTIFIER.text, $type.ast ) ; }
             |

             // function declaration
             FUN IDENTIFIER LPAREN
             { Vector<AstParDecl> parameters = new Vector<AstParDecl>(); }
             ( IDENTIFIER COLON type
             { parameters.add(new AstParDecl(new Location(Location.consLoc($IDENTIFIER), $type.ast.location()), $IDENTIFIER.text, $type.ast)); }
             ( COMMA IDENTIFIER COLON type
             { parameters.add(new AstParDecl(new Location(Location.consLoc($IDENTIFIER), $type.ast.location()), $IDENTIFIER.text, $type.ast)); }
             )* )? RPAREN COLON type
             { Location loc = new Location(Location.consLoc($FUN), $type.ast.location()); }
             { AstExpr astExpr = null; }
             ( IS expr
             { loc = new Location(Location.consLoc($FUN), $expr.ast.location()); }
             { astExpr = $expr.ast; }
             )?
             { $ast = new AstFunDecl(loc, $IDENTIFIER.text, new AstTrees<AstParDecl>(parameters), $type.ast, astExpr); }
             ;


type returns [AstType ast] :
             // atomic type
             VOID                           { $ast = new AstAtomType(Location.consLoc($VOID) , AstAtomType.Type.VOID); }                                        |
             CHAR                           { $ast = new AstAtomType(Location.consLoc($CHAR) , AstAtomType.Type.CHAR); }                                        |
             INT                            { $ast = new AstAtomType(Location.consLoc($INT) , AstAtomType.Type.INT); }                                          |
             BOOL                           { $ast = new AstAtomType(Location.consLoc($BOOL) , AstAtomType.Type.BOOL); }                                        |
             // named type
             IDENTIFIER                     { $ast = new AstNameType(Location.consLoc($IDENTIFIER) , $IDENTIFIER.text); }                                       |
             // array type
             LBRACKET expr RBRACKET type    { $ast = new AstArrType(new Location(Location.consLoc($LBRACKET), $type.ast.location()), $type.ast, $expr.ast); }   |
             // pointer type
             PTR type                       { $ast = new AstPtrType(new Location(Location.consLoc($PTR), $type.ast.location()), $type.ast); }                   |

             // record type
             LBRACE IDENTIFIER COLON type
             { Vector<AstCompDecl> compDecls = new Vector<AstCompDecl>(); }
             ( COMMA IDENTIFIER COLON type
             { compDecls.add(new AstCompDecl(new Location(Location.consLoc($IDENTIFIER), $type.ast.location()), $IDENTIFIER.text, $type.ast));}
             )* RBRACE
             { $ast = new AstRecType(new Location(Location.consLoc($LBRACE, $RBRACE)), new AstTrees<AstCompDecl>(compDecls)) ;}
             |

             // enclosed type
             LPAREN type RPAREN  ;


expr returns [AstExpr ast] :
            // constant expressions
            VOIDCONST       { $ast = new AstAtomExpr(Location.consLoc($VOIDCONST), AstAtomExpr.Type.VOID, $VOIDCONST.text); }           |
            BOOLCONST       { $ast = new AstAtomExpr(Location.consLoc($BOOLCONST), AstAtomExpr.Type.BOOL, $BOOLCONST.text); }           |
            POINTERCONST    { $ast = new AstAtomExpr(Location.consLoc($POINTERCONST), AstAtomExpr.Type.POINTER, $POINTERCONST.text); }  |
            CHARCONST       { $ast = new AstAtomExpr(Location.consLoc($CHARCONST), AstAtomExpr.Type.CHAR, $CHARCONST.text); }           |
            STRINGCONST     { $ast = new AstAtomExpr(Location.consLoc($STRINGCONST), AstAtomExpr.Type.STRING, $STRINGCONST.text); }     |
            INTEGERCONST    { $ast = new AstAtomExpr(Location.consLoc($INTEGERCONST), AstAtomExpr.Type.INT, $INTEGERCONST.text); }      |
            // variable access
            IDENTIFIER      { $ast = new AstNameExpr(Location.consLoc($IDENTIFIER), $IDENTIFIER.text); }                                |

            // function call
            IDENTIFIER LPAREN
            { Vector<AstExpr> expressions = new Vector<AstExpr>(); }
            ( expr { expressions.add($expr.ast); } ( COMMA expr { expressions.add($expr.ast); })* )?
            { $ast = new AstCallExpr(Location.consLoc($IDENTIFIER, $RPAREN), $IDENTIFIER.text, new AstTrees<AstExpr>(expressions)) ;}
            RPAREN
            |

            // compound expression
            LBRACE
            { Vector<AstStmt> statements = new Vector<AstStmt>();}
            stmt { statements.add($stmt.ast); } SEMIC
            ( stmt SEMIC { statements.add($stmt.ast); } )*
            RBRACE
            { $ast = new AstStmtExpr(Location.consLoc($LBRACE, $RBRACE), new AstTrees<AstStmt>(statements)); }
            |

            // typecast expression
            LPAREN expr COLON type RPAREN
            { $ast = new AstCastExpr(Location.consLoc($LPAREN, $RPAREN), $expr.ast, $type.ast) ; }
            |

            // enclosed expression
            LPAREN expr RPAREN
            |

            // postfix expression : array
            expr { AstExpr expr1 = $expr.ast; }LBRACKET expr RBRACKET
            { $ast = new AstArrExpr(new Location(expr1.location(), Location.consLoc($RBRACKET)), expr1, $expr.ast); }
            |

            // postfix expression : pointer
            expr PTR { $ast = new AstSfxExpr(new Location($expr.ast.location(), Location.consLoc($PTR)), AstSfxExpr.Oper.PTR, $expr.ast) ;}
            |

            // postfix expression : record
            expr DOT IDENTIFIER
            { $ast = new AstRecExpr(new Location($expr.ast.location(), Location.consLoc($IDENTIFIER)), $expr.ast,
            new AstNameExpr(Location.consLoc($IDENTIFIER), $IDENTIFIER.text)); }
            |

            // prefix expression
            { AstPfxExpr.Oper opr = null; Location loc = null; }
            (   NOT { opr = AstPfxExpr.Oper.NOT; loc = Location.consLoc($NOT);  } |
                PTR { opr = AstPfxExpr.Oper.PTR;  loc = Location.consLoc($PTR); } |
                ADD { opr = AstPfxExpr.Oper.ADD;  loc = Location.consLoc($ADD); } |
                SUB { opr = AstPfxExpr.Oper.SUB;  loc = Location.consLoc($SUB); }) expr
            { $ast = new AstPfxExpr(new Location(loc, $expr.ast.location()), opr, $expr.ast) ;}
            |

            // allocation expression
            { AstPfxExpr.Oper opr = null; Location loc = null;}
            (   NEW { loc = Location.consLoc($NEW); opr = AstPfxExpr.Oper.NEW; } |
                DEL { loc = Location.consLoc($DEL); opr = AstPfxExpr.Oper.DEL; }) expr
            { $ast = new AstPfxExpr(new Location(loc, $expr.ast.location()), opr, $expr.ast); }
            |

            // infix expression : multiplicative operators
            expr { AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = $expr.ast; }
            (   MUL { opr = AstBinExpr.Oper.MUL; loc = Location.consLoc($MUL); } |
                DIV { opr = AstBinExpr.Oper.DIV; loc = Location.consLoc($DIV); } |
                MOD { opr = AstBinExpr.Oper.MOD; loc = Location.consLoc($MOD); } )
            expr { AstExpr expr2 = $expr.ast ;}
            { $ast = new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;}
            |

            // infix expression : additive operators
            expr { AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = $expr.ast; }
            (   ADD { opr = AstBinExpr.Oper.ADD; loc = Location.consLoc($ADD); } |
                SUB { opr = AstBinExpr.Oper.SUB; loc = Location.consLoc($SUB); } )
            expr { AstExpr expr2 = $expr.ast; }
            { $ast = new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;}
            |

            // infix expression : relational operators
            expr { AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = $expr.ast; }
            (   EQU { opr = AstBinExpr.Oper.EQU; loc = Location.consLoc($EQU); } |
                NEQ { opr = AstBinExpr.Oper.NEQ; loc = Location.consLoc($NEQ); } |
                LTH { opr = AstBinExpr.Oper.LTH; loc = Location.consLoc($LTH); } |
                GTH { opr = AstBinExpr.Oper.GTH; loc = Location.consLoc($GTH); } |
                LEQ { opr = AstBinExpr.Oper.LEQ; loc = Location.consLoc($LEQ); } |
                GEQ { opr = AstBinExpr.Oper.GEQ; loc = Location.consLoc($GEQ); } )
            expr { AstExpr expr2 = $expr.ast; }
            { $ast = new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;}
            |

            // infix expression : conjunctive operator
            expr { AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = $expr.ast; }
            AND { opr = AstBinExpr.Oper.AND; loc = Location.consLoc($AND); }
            expr { AstExpr expr2 = $expr.ast; }
            { $ast = new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;}
            |

            // infix expression : disjunctive operator
            expr { AstBinExpr.Oper opr = null; Location loc = null; AstExpr expr1 = $expr.ast; }
            OR { opr = AstBinExpr.Oper.OR; loc = Location.consLoc($OR); }
            expr { AstExpr expr2 = $expr.ast; }
            { $ast = new AstBinExpr(new Location(expr1.location(), expr2.location()), opr, expr1, expr2) ;}
            |

            // where expression
            expr WHERE LBRACE
            { Vector<AstDecl> declarations = new Vector<AstDecl>(); }
            decl { declarations.add($decl.ast); }
            ( decl { declarations.add($decl.ast); })*
            RBRACE
            { $ast = new AstWhereExpr(new Location($expr.ast.location(), Location.consLoc($RBRACE)), $expr.ast, new AstTrees<AstDecl>(declarations)); }
            ;

stmt returns [AstStmt ast] :
            // expression statement
            expr { $ast = new AstExprStmt($expr.ast.location(), $expr.ast) ; }
            |

            // assignment statement
            expr { AstExpr dest = $expr.ast; }  IS expr { AstExpr src = $expr.ast; }
            { $ast = new AstAssignStmt(new Location(dest.location(), src.location()), dest, src); }
            |

            // conditional statement
            IF expr THEN stmt { AstStmt thenStmt = $stmt.ast; } ELSE stmt { AstStmt elseStmt = $stmt.ast; }
            { $ast = new AstIfStmt(new Location(Location.tokenLoc($IF), elseStmt.location()), $expr.ast, thenStmt, elseStmt); }
            |

            // loop statement
            WHILE expr DO stmt
            { $ast = new AstWhileStmt(new Location(Location.tokenLoc($WHILE), $stmt.ast.location()), $expr.ast, $stmt.ast); }
            ;