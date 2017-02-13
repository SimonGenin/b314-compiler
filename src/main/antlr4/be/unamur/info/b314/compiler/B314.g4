grammar B314;

import B314Words;

root: programme;

// the program itself
programme:  DECLARE AND RETAIN (vardecl SC | fctdecl )*
            WHEN YOUR TURN (clauseWhen)* clauseDefault
            ;

// Clauses
clauseWhen: WHEN expr ( localvardecl )? DO instruction+ DONE ;
clauseDefault: BY DEFAULT ( localvardecl )? DO instruction+ DONE ;

// An instruction
instruction:   SKIP_INSTR
             | IF expr THEN instruction+ DONE
             | IF expr THEN instruction+ ELSE instruction+ DONE
             | WHILE expr DO instruction+ DONE
             | SET expr TO expr
             | COMPUTE expr
             | NEXT action
             ;

// An action
action:   MOVE (NORTH | SOUTH | EAST | WEST)
        | SHOOT (NORTH | SOUTH | EAST | WEST)
        | USE (MAP | RADIO | FRUITS | SODA)
        | DO NOTHING
        ;

// Fonction declaration
fctdecl:  ID AS FUNCTION LP ( vardecl (C vardecl)* )? RP CL (scalar|VOID)
          (localvardecl)?
          DO instruction+ DONE
          ;


// Var declaration
vardecl: ID AS type;
// Global vars declaration
globvardecl: DECLARE AND RETAIN (vardecl SC)+ ;
// Local var declaration
localvardecl: DECLARE LOCAL (vardecl SC)+ ;

// Types management
array: scalar LB NUMBER (C NUMBER)? RB;
scalar: BOOLEAN | INTEGER | SQUARE;
type: scalar | array;

// Expressions
expr :
         ID LP (expr (C expr)*)? RP // fonction call
       | LP expr RP
       | (MINUS)? NUMBER // entier
       | expr MODULO expr
       | expr (MUL|DIV) expr
       | expr MINUS expr
       | expr PLUS expr
       | NOT expr
       | expr (SMALLER_THAN|GREATER_THAN|EQUALS_TO) expr
       | expr (AND|OR) expr
       | TRUE | FALSE
       | (ENNEMI|GRAAL) IS (NORTH | SOUTH | EAST | WEST)
       | (MAP | RADIO | AMMO | FRUITS | SODA) COUNT
       | DIRT | ROCK | VINES | ZOMBIE | PLAYER | ENNEMI | MAP | RADIO | AMMO | FRUITS | SODA | LIFE
       | NEARBY LB expr C expr RB
       | ID
       | ID LB expr (C expr)? RB // case
       ;
