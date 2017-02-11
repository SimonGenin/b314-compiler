grammar B314;

import B314Words;

root: (instruction)+;

instruction: (decl | expr)+;

// Declaration
decl: vardecl | globvardecl ;
// Var declaration
vardecl: ID AS type SC;
// Global vars declaration
globvardecl: DECLARE AND RETAIN vardecl+;

// Types management
array: scalar LB NUMBER (C NUMBER)? RB;
scalar: BOOLEAN | INTEGER | SQUARE;
type: scalar | array;

// Expressions
expr :
         LP expr RP
       | (MINUS)? NUMBER // entier
       | expr MODULO expr
       | expr (MUL|DIV) expr
       | expr MINUS expr
       | expr PLUS expr
       | NOT expr
       | expr (SMALLER_THAN|GREATER_THAN|EQUALS_TO) expr
       | expr (AND|OR) expr
       | TRUE | FALSE
       | ENNEMI IS (NORTH | SOUTH | EAST | WEST)
       | GRAAL IS (NORTH | SOUTH | EAST | WEST)
       | (MAP | RADIO | AMMO | FRUITS | SODA) COUNT
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
       | LIFE
       | NEARBY LB expr C expr RB
       | ID
       | ID LB expr (C expr)? RB // case
       ;
