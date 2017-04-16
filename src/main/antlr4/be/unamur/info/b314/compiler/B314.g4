grammar B314;

import B314Words;

root: programme;

programme:
            globdecl
            WHEN YOUR TURN (clauseWhen)* clauseDefault EOF
            # programRule
            ;


clauseWhen:
            WHEN exprBool ( localvardecl )? DO instruction+ DONE
            # whenClause
            ;

clauseDefault:
                BY DEFAULT ( localvardecl )? DO instruction+ DONE
                # defaultClause
                ;


instruction:
               SKIP_INSTR                                            # skipInstr
             | IF exprBool THEN instruction+ DONE                    # ifThenDoneInstr
             | IF exprBool THEN setinstrucion ELSE setinstrucion DONE# ifThenElseDoneInstr
             | WHILE exprBool DO instruction+ DONE                   # whileDoDoneInstr
             | SET exprL TO expr                                     # setToInstr
             | COMPUTE expr                                          # computeInstr
             | NEXT action                                           # nextInstr
             ;

setinstrucion:
                instruction+         #setInstruction
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
        ;

scalar:
        BOOLEAN | INTEGER | SQUARE
        ;


type: scalar | array;

expr :
         LP expr RP
       | exprId
       | exprInt
       | exprBool
       | exprCase
       ;

exprInt :
              LP exprInt RP                                                    # parIntExpr
            | (MINUS)? NUMBER                                                  # integerExpr
            | (MAP | RADIO | AMMO | FRUITS | SODA) COUNT                       # itemCountExpr
            | (LATITUDE | LONGITUDE | GRID SIZE)                               # latLongGridSizeExpr
            | LIFE                                                             # lifeExpr
            | exprInt (MODULO|MUL|DIV) exprInt                                 # modMulDivExpr
            | exprInt (MINUS|PLUS) exprInt                                     # plusMinusExpr
            | exprId                                                           # idIntExpr
            ;

exprBool :
              LP exprBool RP                                                    # parBoolExpr
            | (TRUE | FALSE)                                                    # trueFalseExpr
            | (ENNEMI|GRAAL) IS (NORTH | SOUTH | EAST | WEST)                   # smthIsDirExpr
            | exprId EQUALS_TO exprId                                           # equalIdExpr
            | exprInt (SMALLER_THAN|GREATER_THAN|EQUALS_TO) exprInt             # compExpr
            | exprBool EQUALS_TO exprBool                                       # equalBoolExpr
            | exprCase EQUALS_TO exprCase                                       # equalCaseExpr
            | exprBool (AND|OR) exprBool                                        # andOrExpr
            | NOT exprBool                                                      # notExpr
            | exprId                                                            # idBoolExpr
            ;

exprCase :
              LP exprCase RP
            | DIRT
            | ROCK
            | VINES
            | ZOMBIE
            | PLAYER
            | ENNEMI
            | MAP
            | RADIO
            | AMMO
            | FRUITS
            | SODA
            | NEARBY LB exprInt C exprInt RB
            | exprId
            ;

exprId :
            LP exprId RP                                         # parIDExpr
          | arrayExpr                                            # arrayIndexExprID
          | identifier LP ((expr) (C expr)*)? RP                 # fctCallExprID
          | identifier                                           # identifierExprID
          ;

exprL :
            identifier
          | arrayExpr
          ;

arrayExpr :
             identifier LB exprInt (C exprInt)? RB
             ;


identifier :
              ID
              ;