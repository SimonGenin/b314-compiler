lexer grammar B314Words;

BOOLEAN: 'Bool';
INTEGER: 'Int';
SQUARE : 'Case';

LB: '[';
RB: ']';
C : ',';
SC: ';';
LP: '(';
RP: ')';
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
MODULO: '%';
SMALLER_THAN: '<';
GREATER_THAN: '>';
EQUALS_TO: '=';

DECLARE: 'declare';
RETAIN: 'retain';
NEARBY: 'nearby';
COUNT: 'count';
IS: 'is';
AS: 'as';
SKIP_INSTR: 'skip';
IF: 'if';
THEN: 'then';
ELSE: 'else';
DONE: 'done';
WHILE: 'while';
DO: 'do';
SET: 'set';
COMPUTE: 'compute';
NEXT: 'next';
MOVE: 'move';
SHOOT: 'shoot';
USE: 'use';
TO: 'to';
NOTHING: 'nothing';

AND: 'and';
OR: 'or';
NOT: 'not';

TRUE: 'true';
FALSE: 'false';

ENNEMI: 'ennemi';
ZOMBIE: 'zombie';
PLAYER: 'player';

LATITUDE: 'latitude';
LONGITUDE: 'longitude';
NORTH: 'north';
SOUTH: 'south';
EAST: 'east';
WEST: 'west';

GRID_SIZE: 'grid size';

GRAAL: 'graal';
MAP: 'map';
RADIO: 'radio';
AMMO: 'ammo';
FRUITS: 'fruits';
SODA: 'soda';

LIFE: 'life';

DIRT: 'dirt';
ROCK: 'rock';
VINES: 'vines';

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