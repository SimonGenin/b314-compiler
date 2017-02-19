package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.symtab.ArrayType;
import org.antlr.symtab.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class builds the symbol table.
 * It is make out of symbols, types and scopes.
 *
 * It also checks for the use of undeclared symbols
 *
 * Created by Simon on 15/02/17.
 */
public class SymbolTableVisitor extends B314BaseVisitor
{

    private static final Logger LOG = LoggerFactory.getLogger(SymbolTableVisitor.class);

    private SymbolTable symbolTable;

    private Scope currentScope;

    private Symbol pendingSymbol;
    private boolean isPendingSymbolAnArray = false;

    // primitive types
    private PrimitiveType booleanType;
    private PrimitiveType integerType;
    private PrimitiveType squareType;
    private PrimitiveType voidType;

    public SymbolTableVisitor () {

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
        VariableSymbol variableSymbol = new VariableSymbol(ctx.ID().getText());

//        // If the symbol already exist in the current or upper scopes, we throw an exception
//        if (currentScope.resolve(ctx.ID().getText()) != null) {
//            throw new AlreadyUsedIdentifierException("SymbolTableVisitor.visitVariableDeclaration(context)");
//        }

        // We define the scope of the new symbol to the current one.
        currentScope.define(variableSymbol);

        // We save the reference of the symbol for later use in the type definition
        pendingSymbol = variableSymbol;

        // We visit the type definition
        ctx.type().accept(this);

        return null;
    }

    @Override
    public Object visitFunctionDeclaration (B314Parser.FunctionDeclarationContext ctx)
    {

        LOG.debug("[SymTab] visit function declaration");
        LOG.debug("[SymTab] function name is " + ctx.ID().getText());
        LOG.debug("[SymTab] push function " + ctx.ID().getText() + " scope");

        // Creates the scoped symbol from the context, and set it in its parent scope
        FunctionSymbol functionSymbol = new FunctionSymbol(ctx.ID().getText());
        currentScope.define(functionSymbol);

        // Keep a track of the previous scope (parent scope)
        Scope oldScope = currentScope;

        // We set the new current scope: the function symbol
        currentScope = functionSymbol;

        // We set the type of the symbol, using the return type
        if (ctx.VOID() != null) {
            functionSymbol.setType(voidType);
        } else {
            // If it is a scalar, we visit to know more.
            pendingSymbol = functionSymbol;
            ctx.scalar().accept(this);
        }

        // We visit all the parameters declaration of the function, it will add
        // them to the symbol table.
        for (B314Parser.VardeclContext varDeclaration : ctx.vardecl()) {
            varDeclaration.accept(this);
        }

        // We visit the variables declaration.
        if (ctx.localvardecl() != null)
        {
            ctx.localvardecl().accept(this);
        }

        // We reestablish the anterior scope (we leave the function scope)
        currentScope = oldScope;

        LOG.debug("[SymTab] leave function " + ctx.ID().getText() + " scope");

        return null;
    }

    @Override
    public Object visitArray (B314Parser.ArrayContext ctx)
    {

        LOG.debug("[SymTab] symbol type is array");

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
            if (!isArray)
                ((TypedSymbol)pendingSymbol).setType(booleanType);
            else
                ((ArrayType)((TypedSymbol)pendingSymbol).getType()).setType(booleanType);
        }

        if (ctx.INTEGER() != null) {
            if (!isArray)
                ((TypedSymbol)pendingSymbol).setType(integerType);
            else
                ((ArrayType)((TypedSymbol)pendingSymbol).getType()).setType(integerType);
        }

        if (ctx.SQUARE() != null) {
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

        // Define a new local scope to the when structure
        LocalScope localScope = new LocalScope(currentScope);

        // Save the old scope
        Scope oldScope = currentScope;

        // Set the new scope
        currentScope = localScope;

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

        // Define a new local scope to the when structure
        LocalScope localScope = new LocalScope(currentScope);

        // Save the old scope
        Scope oldScope = currentScope;

        // Set the new scope
        currentScope = localScope;

        // visit normally all children
        super.visitDefaultClause(ctx);

        // We exit the when structure, so we put back the old scope
        currentScope = oldScope;

        LOG.debug("[SymTab] leave local scope");

        return null;
    }



    public SymbolTable getSymTab ()
    {
        return symbolTable;
    }
}
