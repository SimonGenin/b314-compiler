lexer grammar B314Words;

// Identifiers

ID: LETTER (LETTER | DIGIT)* ;

NUMBER: (DIGIT)+;

fragment LETTER: 'A'..'Z' | 'a'..'z' ;
fragment DIGIT: '0'..'9' ;

// Comments -> ignored

COMMENT: '/*' .*? '*/' -> skip;

// Whitespaces -> ignored

NEWLINE: '\r'? '\n'  -> skip ;
WS: [ \t]+ -> skip ;

// Primitives
BOOLEAN: 'Bool';
INTEGER: 'Int';
SQUARE : 'Case';

// Symbolic lexemes
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
COMMA : ',';

// assign lexeme
AS: 'as';