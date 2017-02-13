grammar B314;

import B314Words;

root: programme;

programme:
            globdecl
            WHEN YOUR TURN (clauseWhen)* clauseDefault
            # programRule
            ;


clauseWhen:
            WHEN expr ( localvardecl )? DO instruction+ DONE
            # whenClause
            ;

clauseDefault:
                BY DEFAULT ( localvardecl )? DO instruction+ DONE
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
          # functionDeclaration
          ;



vardecl:
          ID AS type
          # variableDeclaration
          ;

globdecl:
              DECLARE AND RETAIN (vardecl SC | fctdecl )*
              # globalDeclaration
              ;

localvardecl:
               DECLARE LOCAL (vardecl SC)+
               # localDeclaration
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
         LP expr RP                                                 # parExrpr
       | (MINUS)? NUMBER                                            # integerExpr
       | expr (MODULO|MUL|DIV) expr                                 # modMulDivExpr
       | expr (MINUS|PLUS) expr                                     # plusMinusExpr
       | expr (SMALLER_THAN|GREATER_THAN|EQUALS_TO) expr            # compExpr
       | expr (AND|OR) expr                                         # andOrExpr
       | NOT expr                                                   # notExpr
       | (TRUE | FALSE)                                             # trueFalseExpr
       | (ENNEMI|GRAAL) IS (NORTH | SOUTH | EAST | WEST)            # smthIsDirExpr
       | (MAP | RADIO | AMMO | FRUITS | SODA) COUNT                 # itemCountExpr
       | (DIRT | ROCK | VINES)                                      # keyWordExpr
       | (ZOMBIE | PLAYER | ENNEMI)                                 # keyWordExpr
       | (MAP | RADIO | AMMO | FRUITS | SODA | LIFE)                # keyWordExpr
       | NEARBY LB expr C expr RB                                   # nearbyExpr
       | ID LP (expr (C expr)*)? RP                                 # funcCallExpr
       | ID                                                         # idExpr
       | ID LB expr (C expr)? RB                                    # caseExpr
       ;
