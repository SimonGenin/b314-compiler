grammar B314;

import B314Words;

root: (instruction)+;

instruction: decl+ ;

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