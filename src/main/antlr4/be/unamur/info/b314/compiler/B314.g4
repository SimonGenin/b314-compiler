grammar B314;

import B314Words;

root: programme;



programme:
            DECLARE AND RETAIN (vardecl SC | fctdecl )*
            WHEN YOUR_TURN (clauseWhen)* clauseDefault
            # programRule
            ;


clauseWhen:
            WHEN expr ( localvardecl )? DO instruction+ DONE
            # whenClause
            ;

clauseDefault:
                BY_DEFAULT ( localvardecl )? DO instruction+ DONE
                # defaultClause
                ;


instruction:
               SKIP_INSTR                                           # skipInstr
             | IF expr THEN instruction+ DONE                       # ifThenDoneInstr
             | IF expr THEN instruction+ ELSE instruction+ DONE     # ifThenElseDoneInstr
             | WHILE expr DO instruction+ DONE                      # whileDoDoneInstr
             | SET expr TO expr                                     # setToInstr
             | COMPUTE expr                                         # computeInstr
             | NEXT action                                          # nextInstr
             ;


action:
          MOVE (NORTH | SOUTH | EAST | WEST)     # moveAct
        | SHOOT (NORTH | SOUTH | EAST | WEST)    # shootAct
        | USE (MAP | RADIO | FRUITS | SODA)      # useAct
        | DO NOTHING                             # doNothingAct
        ;


fctdecl:
          ID AS FUNCTION LP ( vardecl (C vardecl)* )? RP CL (scalar|VOID)
          (localvardecl)?
          DO instruction+ DONE
          # fctDecl
          ;



vardecl:
          ID AS type
          # varDecl
          ;

globvardecl:
              DECLARE AND RETAIN (vardecl SC)+
              # globalVarDecl
              ;

localvardecl:
               DECLARE LOCAL (vardecl SC)+
               # localVarDecl
               ;



array:
        scalar LB NUMBER (C NUMBER)? RB
        # arrayType
        ;

scalar:
        BOOLEAN | INTEGER | SQUARE
        # scalarType
        ;


type: scalar | array;


expr :
         ID LP (expr (C expr)*)? RP                                 # funcCallExpr
       | LP expr RP                                                 # parExrpr
       | (MINUS)? NUMBER                                            # integerExpr
       | expr MODULO expr                                           # modExpr
       | expr (MUL|DIV) expr                                        # mulDivExpr
       | expr (MINUS|PLUS) expr                                     # plusMinusExpr
       | NOT expr                                                   # notExpr
       | expr (SMALLER_THAN|GREATER_THAN|EQUALS_TO) expr            # compExpr
       | expr (AND|OR) expr                                         # andOrExpr
       | (TRUE | FALSE)                                             # trueFalseExpr
       | (ENNEMI|GRAAL) IS (NORTH | SOUTH | EAST | WEST)            # smthIsDirExpr
       | (MAP | RADIO | AMMO | FRUITS | SODA) COUNT                 # itemCountExpr
       | (DIRT | ROCK | VINES)                                      # keyWordExpr
       | (ZOMBIE | PLAYER | ENNEMI)                                 # keyWordExpr
       | (MAP | RADIO | AMMO | FRUITS | SODA | LIFE)                # keyWordExpr
       | NEARBY LB expr C expr RB                                   # nearbyExpr
       | ID                                                         # idExpr
       | ID LB expr (C expr)? RB                                    # caseExpr
       ;
