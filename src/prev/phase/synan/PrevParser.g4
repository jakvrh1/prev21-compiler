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
             { $ast = new AstTypeDecl(Location.createLocation($TYP, $type.ast), $IDENTIFIER.text, $type.ast) ; }
             |

             // variable declaration
             VAR IDENTIFIER COLON type
             { $ast = new AstVarDecl(Location.createLocation($VAR, $type.ast), $IDENTIFIER.text, $type.ast) ; }
             |

             // function declaration
             FUN IDENTIFIER LPAREN
             { Vector<AstParDecl> parameters = new Vector<AstParDecl>(); }
             { String funName = $IDENTIFIER.text; }
             ( IDENTIFIER COLON type
             { parameters.add(new AstParDecl(Location.createLocation($IDENTIFIER, $type.ast), $IDENTIFIER.text, $type.ast)); }
             ( COMMA IDENTIFIER COLON type
             { parameters.add(new AstParDecl(Location.createLocation($IDENTIFIER, $type.ast), $IDENTIFIER.text, $type.ast)); }
             )* )? RPAREN COLON type
             { Location location = Location.createLocation($FUN, $type.ast); }
             { AstExpr astExpr = null; }
             ( IS expr
             { location = Location.createLocation($FUN, $expr.ast); }
             { astExpr = $expr.ast; }
             )?
             { $ast = new AstFunDecl(location, funName, new AstTrees<AstParDecl>(parameters), $type.ast, astExpr); }
             ;


type returns [AstType ast] :
             // atomic type
             VOID                           { $ast = new AstAtomType(Location.createLocation($VOID), AstAtomType.Type.VOID); }                                        |
             CHAR                           { $ast = new AstAtomType(Location.createLocation($CHAR), AstAtomType.Type.CHAR); }                                        |
             INT                            { $ast = new AstAtomType(Location.createLocation($INT), AstAtomType.Type.INT); }                                          |
             BOOL                           { $ast = new AstAtomType(Location.createLocation($BOOL), AstAtomType.Type.BOOL); }                                        |
             // named type
             IDENTIFIER                     { $ast = new AstNameType(Location.createLocation($IDENTIFIER) , $IDENTIFIER.text); }                                      |
             // array type
             LBRACKET expr RBRACKET type    { $ast = new AstArrType(Location.createLocation($LBRACKET, $type.ast), $type.ast, $expr.ast); }   |
             // pointer type
             PTR type                       { $ast = new AstPtrType(Location.createLocation($PTR, $type.ast), $type.ast); }                   |

             // record type
             LBRACE IDENTIFIER COLON type
             { Vector<AstCompDecl> compDecls = new Vector<AstCompDecl>(); }
             { compDecls.add(new AstCompDecl(Location.createLocation($IDENTIFIER, $type.ast), $IDENTIFIER.text, $type.ast)); }
             ( COMMA IDENTIFIER COLON type
             { compDecls.add(new AstCompDecl(Location.createLocation($IDENTIFIER, $type.ast), $IDENTIFIER.text, $type.ast));}
             )* RBRACE
             { $ast = new AstRecType(Location.createLocation($LBRACE, $RBRACE), new AstTrees<AstCompDecl>(compDecls)) ;}
             |

             // enclosed type
             LPAREN type RPAREN
             { $ast = $type.ast; }
             ;

expr returns [AstExpr ast] :
            // constant expressions
            VOIDCONST       { $ast = new AstAtomExpr(Location.createLocation($VOIDCONST), AstAtomExpr.Type.VOID, $VOIDCONST.text); }           |
            BOOLCONST       { $ast = new AstAtomExpr(Location.createLocation($BOOLCONST), AstAtomExpr.Type.BOOL, $BOOLCONST.text); }           |
            POINTERCONST    { $ast = new AstAtomExpr(Location.createLocation($POINTERCONST), AstAtomExpr.Type.POINTER, $POINTERCONST.text); }  |
            CHARCONST       { $ast = new AstAtomExpr(Location.createLocation($CHARCONST), AstAtomExpr.Type.CHAR, $CHARCONST.text); }           |
            STRINGCONST     { $ast = new AstAtomExpr(Location.createLocation($STRINGCONST), AstAtomExpr.Type.STRING, $STRINGCONST.text); }     |
            INTEGERCONST    { $ast = new AstAtomExpr(Location.createLocation($INTEGERCONST), AstAtomExpr.Type.INT, $INTEGERCONST.text); }      |
            // variable access
            IDENTIFIER      { $ast = new AstNameExpr(Location.createLocation($IDENTIFIER), $IDENTIFIER.text); }                                |

            // function call
            IDENTIFIER LPAREN
            { Vector<AstExpr> expressions = new Vector<AstExpr>(); }
            ( expr { expressions.add($expr.ast); } ( COMMA expr { expressions.add($expr.ast); })* )?
            RPAREN
            { $ast = new AstCallExpr(Location.createLocation($IDENTIFIER, $RPAREN), $IDENTIFIER.text, new AstTrees<AstExpr>(expressions)) ;}
            |

            // compound expression
            LBRACE
            { Vector<AstStmt> statements = new Vector<AstStmt>();}
            stmt { statements.add($stmt.ast); } SEMIC
            ( stmt SEMIC { statements.add($stmt.ast); } )*
            RBRACE
            { $ast = new AstStmtExpr(Location.createLocation($LBRACE, $RBRACE), new AstTrees<AstStmt>(statements)); }
            |

            // typecast expression
            LPAREN expr COLON type RPAREN
            { $ast = new AstCastExpr(Location.createLocation($LPAREN, $RPAREN), $expr.ast, $type.ast) ; }
            |

            // enclosed expression
            LPAREN expr RPAREN
            { $ast = $expr.ast; }
            |

            // postfix expression : array
            ex1=expr LBRACKET ex2=expr RBRACKET
            { $ast = new AstArrExpr(Location.createLocation($ex1.ast, $RBRACKET), $ex1.ast, $ex2.ast); }
            |

            // postfix expression : pointer
            ex1=expr PTR { $ast = new AstSfxExpr(Location.createLocation($ex1.ast, $PTR), AstSfxExpr.Oper.PTR, $ex1.ast) ;}
            |

            // postfix expression : record
            ex1=expr DOT IDENTIFIER
            { $ast = new AstRecExpr(Location.createLocation($ex1.ast, $IDENTIFIER), $ex1.ast,
            new AstNameExpr(Location.createLocation($IDENTIFIER), $IDENTIFIER.text)); }
            |

            // prefix expression
            { AstPfxExpr.Oper opr = null; Location location = null; }
            (   NOT { opr = AstPfxExpr.Oper.NOT; location = Location.createLocation($NOT); } |
                PTR { opr = AstPfxExpr.Oper.PTR; location = Location.createLocation($PTR); } |
                ADD { opr = AstPfxExpr.Oper.ADD; location = Location.createLocation($ADD); } |
                SUB { opr = AstPfxExpr.Oper.SUB; location = Location.createLocation($SUB); }) expr
            { $ast = new AstPfxExpr(new Location(location, $expr.ast.location()), opr, $expr.ast) ;}
            |

            // allocation expression
            { AstPfxExpr.Oper opr = null; Location location = null;}
            (   NEW { opr = AstPfxExpr.Oper.NEW; location = Location.createLocation($NEW); } |
                DEL { opr = AstPfxExpr.Oper.DEL; location = Location.createLocation($DEL); }) expr
            { $ast = new AstPfxExpr(new Location(location, $expr.ast.location()), opr, $expr.ast); }
            |

            // infix expression : multiplicative operators
            ex1=expr { AstBinExpr.Oper opr = null; }
            (   MUL { opr = AstBinExpr.Oper.MUL; } |
                DIV { opr = AstBinExpr.Oper.DIV; } |
                MOD { opr = AstBinExpr.Oper.MOD; } )
            ex2=expr
            { $ast = new AstBinExpr(Location.createLocation($ex1.ast, $ex2.ast), opr, $ex1.ast, $ex2.ast) ;}
            |

            // infix expression : additive operators
            ex1=expr { AstBinExpr.Oper opr = null; }
            (   ADD { opr = AstBinExpr.Oper.ADD; } |
                SUB { opr = AstBinExpr.Oper.SUB; } )
            ex2=expr
            { $ast = new AstBinExpr(Location.createLocation($ex1.ast, $ex2.ast), opr, $ex1.ast, $ex2.ast) ;}
            |

            // infix expression : relational operators
            ex1=expr { AstBinExpr.Oper opr = null; }
            (   EQU { opr = AstBinExpr.Oper.EQU; } |
                NEQ { opr = AstBinExpr.Oper.NEQ; } |
                LTH { opr = AstBinExpr.Oper.LTH; } |
                GTH { opr = AstBinExpr.Oper.GTH; } |
                LEQ { opr = AstBinExpr.Oper.LEQ; } |
                GEQ { opr = AstBinExpr.Oper.GEQ; } )
            ex2=expr
            { $ast = new AstBinExpr(Location.createLocation($ex1.ast, $ex2.ast), opr, $ex1.ast, $ex2.ast) ;}
            |

            // infix expression : conjunctive operator
            ex1=expr AND ex2=expr
            { $ast = new AstBinExpr(Location.createLocation($ex1.ast, $ex2.ast), AstBinExpr.Oper.AND, $ex1.ast, $ex2.ast) ;}
            |

            // infix expression : disjunctive operator
            ex1=expr OR ex2=expr
            { $ast = new AstBinExpr(Location.createLocation($ex1.ast, $ex2.ast), AstBinExpr.Oper.OR, $ex1.ast, $ex2.ast) ;}
            |

            // where expression
            ex1=expr WHERE LBRACE
            { Vector<AstDecl> declarations = new Vector<AstDecl>(); }
            decl { declarations.add($decl.ast); }
            ( decl { declarations.add($decl.ast); })*
            RBRACE
            { $ast = new AstWhereExpr(Location.createLocation($ex1.ast, $RBRACE), $ex1.ast, new AstTrees<AstDecl>(declarations)); }
            ;

stmt returns [AstStmt ast] :
            // expression statement
            ex1=expr { $ast = new AstExprStmt($ex1.ast.location(), $ex1.ast); }
            |

            // assignment statement
            ex1=expr IS ex2=expr
            { $ast = new AstAssignStmt(Location.createLocation($ex1.ast, $ex2.ast), $ex1.ast, $ex2.ast); }
            |

            // conditional statement
            IF expr THEN stmt { AstStmt thenStmt = $stmt.ast; } ELSE stmt { AstStmt elseStmt = $stmt.ast; }
            { $ast = new AstIfStmt(Location.createLocation($IF, elseStmt), $expr.ast, thenStmt, elseStmt); }
            |

            // loop statement
            WHILE expr DO stmt
            { $ast = new AstWhileStmt(Location.createLocation($WHILE, $stmt.ast), $expr.ast, $stmt.ast); }
            ;