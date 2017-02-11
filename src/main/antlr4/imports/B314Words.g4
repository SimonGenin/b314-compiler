lexer grammar B314Words;

// Primitives
BOOLEAN: 'Bool';
INTEGER: 'Int';
SQUARE : 'Case';

// Symbolic lexemes
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
COMMA : ',';
SEMI_COLON: ';';

// assign lexeme
AS: 'as';

// global var lexemes
DECLARE: 'declare';
AND: 'and';
RETAIN: 'retain';

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