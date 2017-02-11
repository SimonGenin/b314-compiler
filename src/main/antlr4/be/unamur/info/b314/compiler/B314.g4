grammar B314;

import B314Words;

root: instruction*;

instruction: vardecl;

// Declaration of variables
vardecl: ID AS type;

// Types management
array: scalar LEFT_BRACKET NUMBER (COMMA NUMBER)? RIGHT_BRACKET;
scalar: BOOLEAN | INTEGER | SQUARE;
type: scalar | array;