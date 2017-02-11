grammar B314;

import B314Words;

root: (instruction)+;

instruction: decl+ ;

// Declaration
decl: vardecl | globvardecl ;
// Var declaration
vardecl: ID AS type SEMI_COLON;
// Global vars declaration
globvardecl: DECLARE AND RETAIN vardecl+;

// Types management
array: scalar LEFT_BRACKET NUMBER (COMMA NUMBER)? RIGHT_BRACKET;
scalar: BOOLEAN | INTEGER | SQUARE;
type: scalar | array;