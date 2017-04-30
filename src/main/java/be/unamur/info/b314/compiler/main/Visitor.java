package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.exception.*;
import be.unamur.info.b314.compiler.symtab.ArrayType;
import be.unamur.info.b314.compiler.symtab.FunctionSymbol;
import org.antlr.symtab.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class builds the symbol table.
 * It is make out of symbols, types and scopes.
 *
 * It also checks for the use of undeclared symbols
 * It check the types
 *
 * Created by Simon on 15/02/17.
 */
public class Visitor extends B314BaseVisitor
{

    private static final Logger LOG = LoggerFactory.getLogger(Visitor.class);

    private SymbolTable symbolTable;

    private Scope currentScope;

    private Symbol pendingSymbol;
    private boolean isPendingSymbolAnArray = false;
    private boolean isPendingSymbolAFunctionParameter = false;

    private int whenIndex;

    // primitive types
    private PrimitiveType booleanType;
    private PrimitiveType integerType;
    private PrimitiveType squareType;
    private PrimitiveType voidType;

    private Type lastIdentifierType;

    private boolean isCreatingSymbolTable;

    public Visitor () {

        LOG.debug("[SymTab] initialization");

        symbolTable = new SymbolTable();

        // Define the primitive types
        booleanType = new PrimitiveType("boolean");
        integerType = new PrimitiveType("integer");
        squareType = new PrimitiveType("square");
        voidType = new PrimitiveType("void");

        // Untyped symbols
        VariableSymbol northUntypedSymbol = new VariableSymbol("north");
        VariableSymbol southUntypedSymbol = new VariableSymbol("south");
        VariableSymbol eastUntypedSymbol  = new VariableSymbol("east");
        VariableSymbol westUntypedSymbol  = new VariableSymbol("west");
        VariableSymbol graalUntypedSymbol = new VariableSymbol("graal");
        VariableSymbol gridUntypedSymbol  = new VariableSymbol("grid");
        VariableSymbol sizeUntypedSymbol = new VariableSymbol("size");

        // Integer symbols
        VariableSymbol latitudeVarInteger = new VariableSymbol("latitude");
        VariableSymbol longitudeVarInteger = new VariableSymbol("longitude");
        VariableSymbol lifeVarInteger = new VariableSymbol("life");
        lifeVarInteger.setType(integerType);
        latitudeVarInteger.setType(integerType);
        longitudeVarInteger.setType(integerType);

        // Square symbols
        VariableSymbol ennemiVarSquare  = new VariableSymbol("ennemi");
        VariableSymbol zombieVarSquare  = new VariableSymbol("zombie");
        VariableSymbol playerVarSquare  = new VariableSymbol("player");
        VariableSymbol mapVarSquare = new VariableSymbol("map");
        VariableSymbol radioVarSquare = new VariableSymbol("radio");
        VariableSymbol ammoVarSquare = new VariableSymbol("ammo");
        VariableSymbol fruitsVarSquare = new VariableSymbol("fruits");
        VariableSymbol dirtVarSquare = new VariableSymbol("dirt");
        VariableSymbol rockVarSquare = new VariableSymbol("rock");
        VariableSymbol vinesVarSquare = new VariableSymbol("vines");
        mapVarSquare.setType(squareType);
        radioVarSquare.setType(squareType);
        ammoVarSquare.setType(squareType);
        fruitsVarSquare.setType(squareType);
        dirtVarSquare.setType(squareType);
        rockVarSquare.setType(squareType);
        vinesVarSquare.setType(squareType);
        ennemiVarSquare.setType(squareType);
        zombieVarSquare.setType(squareType);
        playerVarSquare.setType(squareType);

        // The predefined variables' symbols
        symbolTable.definePredefinedSymbol(ennemiVarSquare);
        symbolTable.definePredefinedSymbol(zombieVarSquare);
        symbolTable.definePredefinedSymbol(playerVarSquare);
        symbolTable.definePredefinedSymbol(latitudeVarInteger);
        symbolTable.definePredefinedSymbol(longitudeVarInteger);
        symbolTable.definePredefinedSymbol(northUntypedSymbol);
        symbolTable.definePredefinedSymbol(southUntypedSymbol);
        symbolTable.definePredefinedSymbol(eastUntypedSymbol);
        symbolTable.definePredefinedSymbol(westUntypedSymbol);
        symbolTable.definePredefinedSymbol(graalUntypedSymbol);
        symbolTable.definePredefinedSymbol(gridUntypedSymbol);
        symbolTable.definePredefinedSymbol(sizeUntypedSymbol);
        symbolTable.definePredefinedSymbol(mapVarSquare);
        symbolTable.definePredefinedSymbol(radioVarSquare);
        symbolTable.definePredefinedSymbol(ammoVarSquare);
        symbolTable.definePredefinedSymbol(fruitsVarSquare);
        symbolTable.definePredefinedSymbol(lifeVarInteger);
        symbolTable.definePredefinedSymbol(dirtVarSquare);
        symbolTable.definePredefinedSymbol(rockVarSquare);
        symbolTable.definePredefinedSymbol(vinesVarSquare);

        symbolTable.GLOBALS.setCounterOffset(99);

        LOG.debug("[SymTab] predefined words set up");

    }

    @Override
    public Object visitRoot (B314Parser.RootContext ctx)
    {

        LOG.debug("[SymTab] enter global scope");
        currentScope = symbolTable.GLOBALS;

        super.visitRoot(ctx);

        LOG.debug("[SymTab] leave global scope");
        LOG.debug("Symbol Table has been generated.");

        return null;
    }

    @Override
    public Object visitVariableDeclaration (B314Parser.VariableDeclarationContext ctx)
    {

        LOG.debug("[SymTab] visit variable declaration");
        LOG.debug("[SymTab] variable name is " + ctx.ID().getText());

        // We create the symbol out of the context
        Symbol symbol;

        if (isPendingSymbolAFunctionParameter) {
            symbol = new ParameterSymbol(ctx.ID().getText());
        } else {
            symbol = new VariableSymbol(ctx.ID().getText());
        }

        // We save the reference of the symbol for later use in the type definition
        pendingSymbol = symbol;

        // We visit the type definition
        ctx.type().accept(this);

        // We define the scope of the new symbol to the current one.
        currentScope.define(symbol);

        return null;
    }

    @Override
    public Object visitFunctionDeclaration (B314Parser.FunctionDeclarationContext ctx)
    {

        LOG.debug("[SymTab] visit function declaration");
        LOG.debug("[SymTab] function name is " + ctx.ID().getText());
        LOG.debug("[SymTab] push function " + ctx.ID().getText() + " scope");

        // Keep a track of the previous scope (parent scope)
        Scope oldScope = currentScope;

        if (isCreatingSymbolTable)
        {

            // Creates the scoped symbol from the context, and set it in its parent scope
            FunctionSymbol functionSymbol = new FunctionSymbol(ctx.ID().getText());

            // We add the symbol to the scope
            currentScope.define(functionSymbol);

            // We set the new current scope: the function symbol
            currentScope = functionSymbol;

            // We set the type of the symbol, using the return type
            if (ctx.VOID() != null)
            {
                functionSymbol.setType(voidType);
            }
            else
            {
                // If it is a scalar, we visit to know more.
                pendingSymbol = functionSymbol;
                ctx.scalar().accept(this);
            }

        }

        else
        {
            FunctionSymbol symbol = (FunctionSymbol) currentScope.getSymbol(ctx.ID().getText());

            currentScope = symbol;

            // We visit all the parameters declaration of the function, it will add
            // them to the symbol table.
            isPendingSymbolAFunctionParameter = true;
            for (B314Parser.VardeclContext varDeclaration : ctx.vardecl())
            {
                varDeclaration.accept(this);
            }
            isPendingSymbolAFunctionParameter = false;

            // We visit the variables declaration.
            if (ctx.localvardecl() != null)
            {
                ctx.localvardecl().accept(this);
            }

            for (B314Parser.InstructionContext instructionContext : ctx.instruction())
            {
                instructionContext.accept(this);
            }


            LOG.debug("[SymTab] leave function " + ctx.ID().getText() + " scope");

        }

        // We reestablish the anterior scope (we leave the function scope)
        currentScope = oldScope;

        return null;
    }

    @Override
    public Object visitArray (B314Parser.ArrayContext ctx)
    {

        LOG.debug("[SymTab] symbol type is array");

        if (isPendingSymbolAFunctionParameter)
            throw new InvalidFunctionParameterArray("A function parameter can't be an array");

        // helper variable
        boolean severalArgs = false;

        // We get the first (and mandatory) index
        int firstArg = Integer.parseInt(ctx.NUMBER(0).getText());
        int secondArg = 0;

        ArrayType type;

        // If there is a second argument, we retrieve it.
        if ( ctx.NUMBER(1) != null ) {
            severalArgs = true;
            secondArg = Integer.parseInt(ctx.NUMBER(1).getText());
        }

        // We initialize our type variable
        if (severalArgs) {
            type = new ArrayType(firstArg, secondArg);
        } else {
            type = new ArrayType(firstArg, null);
        }

        // We set the array type to the symbol
        ((TypedSymbol) pendingSymbol).setType(type);

        // We save the fact that we are working on an array, for later use with type definition
        isPendingSymbolAnArray = true;

        // We visit the type definition to decide which primitive is the array made of.
        ctx.scalar().accept(this);

        // We remove the fact that we are working with an array
        isPendingSymbolAnArray = false;

        return null;
    }

    @Override
    public Object visitScalar (B314Parser.ScalarContext ctx)
    {

        LOG.debug("[SymTab] symbol type is scalar");

        // Are we working with an array ?
        boolean isArray = isPendingSymbolAnArray;

        /*
         * Set the type.
         * If it is a var, just set the symbol type.
         * If it is an array, set the primitive type to the arrayType.
         */

        if (ctx.BOOLEAN() != null) {
            LOG.debug("[SymTab] symbol type is scalar => boolean");
            if (!isArray)
                ((TypedSymbol)pendingSymbol).setType(booleanType);
            else
                ((ArrayType)((TypedSymbol)pendingSymbol).getType()).setType(booleanType);
        }

        if (ctx.INTEGER() != null) {
            LOG.debug("[SymTab] symbol type is scalar => integer");
            if (!isArray)
                ((TypedSymbol)pendingSymbol).setType(integerType);
            else
                ((ArrayType)((TypedSymbol)pendingSymbol).getType()).setType(integerType);
        }

        if (ctx.SQUARE() != null) {
            LOG.debug("[SymTab] symbol type is scalar => square");
            if (!isArray)
                ((TypedSymbol)pendingSymbol).setType(squareType);
            else
                ((ArrayType)((TypedSymbol)pendingSymbol).getType()).setType(squareType);
        }

        return null;
    }

    @Override
    public Object visitWhenClause (B314Parser.WhenClauseContext ctx)
    {

        LOG.debug("[SymTab] visit when clause");
        LOG.debug("[SymTab] define new local scope");



        // Creates the scoped symbol from the context, and set it in its parent scope
        FunctionSymbol functionSymbol = new FunctionSymbol(Integer.toString(this.whenIndex));
        this.whenIndex++;


        // We add the when clause to the scope
        currentScope.define(functionSymbol);


        // Save the old scope
        Scope oldScope = currentScope;

        // Set the new scope
        currentScope = functionSymbol;

        // visit normally all children
        super.visitWhenClause(ctx);


        // We exit the when structure, so we put back the old scope
        currentScope = oldScope;

        LOG.debug("[SymTab] leave local scope");

        return null;
    }

    @Override
    public Object visitDefaultClause (B314Parser.DefaultClauseContext ctx)
    {

        LOG.debug("[SymTab] visit default clause : ");       ;
        LOG.debug("[SymTab] define new local scope");

        // Creates the scoped symbol from the context, and set it in its parent scope
        FunctionSymbol functionSymbol = new FunctionSymbol("default");


        // We add the when clause to the scope
        currentScope.define(functionSymbol);


        // Save the old scope
        Scope oldScope = currentScope;

        // Set the new scope
        currentScope = functionSymbol;

        // visit normally all children
        super.visitDefaultClause(ctx);

        // We exit the when structure, so we put back the old scope
        currentScope = oldScope;

        LOG.debug("[SymTab] leave local scope");

        return null;
    }

    @Override
    public Object visitIdentifier (B314Parser.IdentifierContext ctx)
    {

        LOG.debug("[Decl check] Use the symbol : " + ctx.ID().getText());

        // Get the symbol
        Symbol symbol = currentScope.resolve(ctx.ID().getText());

        // Check if a symbol as been declared
        if (symbol == null) {
            throw new UndeclaredSymbolException(ctx.ID().getText());
        }

        // Get the type for "set to" right expr checking
        lastIdentifierType = ((TypedSymbol)symbol).getType();

        // If no problem, we stop
        return null;
    }


    @Override
    public Object visitArrayExpr (B314Parser.ArrayExprContext ctx)
    {

        // We first let the declaration checking kick in.
        ctx.identifier().accept(this);

        // We get the symbol back
        VariableSymbol symbol = (VariableSymbol) currentScope.resolve(ctx.identifier().ID().getText());

        LOG.debug("[Array indexes checking] " + symbol.getName());

        // We get how many indexes are in the array (1 or 2)
        int indexNumber = ((ArrayType)symbol.getType()).getIndexNumber();

        // If there is two indexes when it should be one
        if (indexNumber == 1 && ctx.exprInt(1) != null) {
            throw new TooManyIndexesArrayException(symbol.getName());
        }

        // If there's one index when it should be two
        if (indexNumber == 2 && ctx.exprInt(1) == null) {
            throw new TooFewIndexesArrayException(symbol.getName());
        }


        /*
         * We now check if the expressions have the good type if they are made of
         * an identifier.
         */

        if (ctx.parent.parent instanceof B314Parser.ExprIntContext) {

            if (!symbol.getType().getName().equals(integerType.getName() + "[]")) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        if (ctx.parent.parent instanceof B314Parser.ExprCaseContext) {

            if (!symbol.getType().getName().equals(squareType.getName() + "[]")) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        if (ctx.parent.parent instanceof B314Parser.ExprBoolContext) {

            if (!symbol.getType().getName().equals(booleanType.getName() + "[]")) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }


        // Everything is ok, we continue
        super.visitArrayExpr(ctx);

        return null;
    }

    @Override
    public Object visitSetToInstr (B314Parser.SetToInstrContext ctx)
    {

        TypedSymbol var = null;

        boolean isArray = false;

        if (ctx.exprL().identifier() != null) {
            ctx.exprL().identifier().accept(this);
            var = (TypedSymbol) currentScope.resolve(ctx.exprL().identifier().ID().getText());

        }

        if (ctx.exprL().arrayExpr() != null) {
            isArray = true;
            ctx.exprL().arrayExpr().accept(this);
            var = (TypedSymbol) currentScope.resolve(ctx.exprL().arrayExpr().identifier().ID().getText());
        }

        // Get its type
        assert var != null;
        Type leftType = var.getType();

        // Get the type of the expr on the right
        Type rightType = null;
        Type identifierType = null;

        if (ctx.expr().exprBool() != null) {
            rightType = booleanType;
        }
        else if (ctx.expr().exprInt()  != null) {
            rightType = integerType;
        }
        else if (ctx.expr().exprCase() != null) {
            rightType = squareType;
        }
        else {
            lastIdentifierType = null;
            ctx.expr().exprId().accept(this);
            identifierType = lastIdentifierType;
        }

        // Change if it exists
        if (identifierType != null) {
            rightType = lastIdentifierType;
        }

        // If the types don't match, we throw an exception
        if (!leftRightExprTypeCompare(leftType, rightType)) {

            if (isArray) {
                throw new TypeMismatchException(
                        "Symbol " + ctx.exprL().arrayExpr().identifier().ID().getText() +
                                " is " + leftType + " and is assigned to a " + rightType);
            }

            else {
                throw new TypeMismatchException(
                        "Symbol " + ctx.exprL().identifier().ID().getText() +
                                " is " + leftType + " and is assigned to a " + rightType);
            }

        }



        return null;
    }

    /**
     * Is used when comparing an array with an element.
     * Ex : set boolean[x] to boolean
     * this is two different types, yet it is true.
     *
     * @param left the left expression
     * @param right the right expression
     * @return is an assignation to left alright with type right ?
     */
    private boolean leftRightExprTypeCompare(Type left, Type right) {

        // Basic name
        String typeName = left.getName();

        // We remove the [] at the end of the name
        if (left.getName().contains("[]")) {
            typeName = left.getName().substring(0, left.getName().length() - 2);
        }

        return typeName.equals(right.getName());

    }

    @Override
    public Object visitIdentifierExprID (B314Parser.IdentifierExprIDContext ctx)
    {

        TypedSymbol symbol = null;

        /*
         * TODO the null pointer exception shouldn't be checked here. Actually, it might be useless once decl are checked first.
         * TODO Improve code quality
         */

        if (ctx.parent instanceof B314Parser.ExprBoolContext) {

            symbol = (TypedSymbol) currentScope.resolve(ctx.identifier().ID().getText());

            if (symbol == null) {
                throw new UndeclaredSymbolException(ctx.identifier().ID().getText());
            }

            if (!symbol.getType().equals(booleanType)) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        if (ctx.parent instanceof B314Parser.ExprCaseContext) {

            symbol = (TypedSymbol) currentScope.resolve(ctx.identifier().ID().getText());

            if (symbol == null) {
                throw new UndeclaredSymbolException(ctx.identifier().ID().getText());
            }

            if (!symbol.getType().equals(squareType)) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        if (ctx.parent instanceof B314Parser.ExprIntContext) {

            symbol = (TypedSymbol) currentScope.resolve(ctx.identifier().ID().getText());

            if (symbol == null) {
                throw new UndeclaredSymbolException(ctx.identifier().ID().getText());
            }

            if (!symbol.getType().equals(integerType)) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        return super.visitIdentifierExprID(ctx);
    }

    @Override
    public Object visitFctCallExprID (B314Parser.FctCallExprIDContext ctx)
    {

        TypedSymbol symbol = null;

        /*
         * TODO the null pointer exception shouldn't be checked here. Actually, it might be useless once decl are checked first.
         * TODO Improve code quality
         */

        if (ctx.parent instanceof B314Parser.ExprBoolContext) {

            symbol = (TypedSymbol) currentScope.resolve(ctx.identifier().ID().getText());

            if (symbol == null) {
                throw new UndeclaredSymbolException(ctx.identifier().ID().getText());
            }

            if (!symbol.getType().equals(booleanType)) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        if (ctx.parent instanceof B314Parser.ExprCaseContext) {

            symbol = (TypedSymbol) currentScope.resolve(ctx.identifier().ID().getText());

            if (symbol == null) {
                throw new UndeclaredSymbolException(ctx.identifier().ID().getText());
            }

            if (!symbol.getType().equals(squareType)) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        if (ctx.parent instanceof B314Parser.ExprIntContext) {

            symbol = (TypedSymbol) currentScope.resolve(ctx.identifier().ID().getText());

            if (symbol == null) {
                throw new UndeclaredSymbolException(ctx.identifier().ID().getText());
            }

            if (!symbol.getType().equals(integerType)) {
                throw new TypeMismatchException(ctx.identifier().ID().getText());
            }

        }

        return super.visitFctCallExprID(ctx);
    }

    @Override
    public Object visitGlobalDeclaration (B314Parser.GlobalDeclarationContext ctx)
    {

        isCreatingSymbolTable = true;

        // We start by visiting the vars
        if (ctx.vardecl() != null) {
            for (B314Parser.VardeclContext decl : ctx.vardecl()) {
                decl.accept(this);
            }
        }


        // Then we visit the functions for their symbols
        if (ctx.fctdecl() != null) {
            for (B314Parser.FctdeclContext decl : ctx.fctdecl()) {
                decl.accept(this);
            }
        }

        isCreatingSymbolTable = false;

        // Then we visit the functions for their bodies (and params and all)
        if (ctx.fctdecl() != null) {
            for (B314Parser.FctdeclContext decl : ctx.fctdecl()) {
                decl.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visitEqualIdExpr(B314Parser.EqualIdExprContext ctx) {
        //Check type of exprId
        TypedSymbol var1 = (TypedSymbol) currentScope.resolve(ctx.exprId(0).getText());
        TypedSymbol var2 = (TypedSymbol) currentScope.resolve(ctx.exprId(1).getText().substring(0,ctx.exprId(1).getText().length()-2));

        //TODO Ortho
        if (!var1.getType().equals(var2.getType())){
            throw new TypeMismatchException(
                    "Symbol " + ctx.exprId(0).getText() +
                            " is " + var1 + " and is compare with symbol " + ctx.exprId(1).getText() + " is "+ var2);
        }
        return null;
    }

    public SymbolTable getSymTab ()
    {
        return symbolTable;
    }
}
