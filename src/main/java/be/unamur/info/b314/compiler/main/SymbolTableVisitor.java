package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.exception.AlreadyUsedIdentifierException;
import org.antlr.symtab.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Created by Simon on 15/02/17.
 */
public class SymbolTableVisitor extends B314BaseVisitor
{

    // TODO when clauses scope

    private static final Logger LOG = LoggerFactory.getLogger(SymbolTableVisitor.class);

    private SymbolTable symbolTable;
    private Scope currentScope;

    private final int LOCAL = 1;
    private final int GLOBAL = 2;
    private int nextDecl = LOCAL;

    private Symbol pendingSymbol;
    private boolean isPendingSymbolAnArray = false;

    // primitive types
    private PrimitiveType booleanType;
    private PrimitiveType integerType;
    private PrimitiveType squareType;
    private PrimitiveType voidType;

    public SymbolTableVisitor () {

        symbolTable = new SymbolTable();

        booleanType = new PrimitiveType("boolean");
        integerType = new PrimitiveType("integer");
        squareType = new PrimitiveType("square");
        voidType = new PrimitiveType("void");

        VariableSymbol predefinedVar1 = new VariableSymbol("ennemi");
        VariableSymbol predefinedVar2 = new VariableSymbol("zombie");
        VariableSymbol predefinedVar3 = new VariableSymbol("player");

        predefinedVar1.setType(squareType);
        predefinedVar2.setType(squareType);
        predefinedVar3.setType(squareType);

        VariableSymbol predefinedVar4 = new VariableSymbol("latitude");
        VariableSymbol predefinedVar5 = new VariableSymbol("longitude");

        predefinedVar4.setType(integerType);
        predefinedVar5.setType(integerType);

        // No type, these are just used symbols
        VariableSymbol predefinedVar6 = new VariableSymbol("north");
        VariableSymbol predefinedVar7 = new VariableSymbol("south");
        VariableSymbol predefinedVar8 = new VariableSymbol("east");
        VariableSymbol predefinedVar9 = new VariableSymbol("west");

        VariableSymbol predefinedVar10 = new VariableSymbol("graal");
        predefinedVar10.setType(squareType);

        // TODO what to do ? Maybe not type, and just handle it using the #name
        VariableSymbol predefinedVar11 = new VariableSymbol("grid");
        VariableSymbol predefinedVar12 = new VariableSymbol("size");

        VariableSymbol predefinedVar13 = new VariableSymbol("map");
        VariableSymbol predefinedVar14 = new VariableSymbol("radio");
        VariableSymbol predefinedVar15 = new VariableSymbol("ammo");
        VariableSymbol predefinedVar16 = new VariableSymbol("fruits");
        VariableSymbol predefinedVar17 = new VariableSymbol("life");

        predefinedVar13.setType(integerType);
        predefinedVar14.setType(integerType);
        predefinedVar15.setType(integerType);
        predefinedVar16.setType(integerType);
        predefinedVar17.setType(integerType);

        VariableSymbol predefinedVar18 = new VariableSymbol("dirt");
        VariableSymbol predefinedVar19 = new VariableSymbol("rock");
        VariableSymbol predefinedVar20 = new VariableSymbol("vines");

        predefinedVar18.setType(squareType);
        predefinedVar19.setType(squareType);
        predefinedVar20.setType(squareType);

        // The predefined variables' symbols
        symbolTable.definePredefinedSymbol(predefinedVar1);
        symbolTable.definePredefinedSymbol(predefinedVar2);
        symbolTable.definePredefinedSymbol(predefinedVar3);
        symbolTable.definePredefinedSymbol(predefinedVar4);
        symbolTable.definePredefinedSymbol(predefinedVar5);
        symbolTable.definePredefinedSymbol(predefinedVar6);
        symbolTable.definePredefinedSymbol(predefinedVar7);
        symbolTable.definePredefinedSymbol(predefinedVar8);
        symbolTable.definePredefinedSymbol(predefinedVar9);
        symbolTable.definePredefinedSymbol(predefinedVar10);
        symbolTable.definePredefinedSymbol(predefinedVar11);
        symbolTable.definePredefinedSymbol(predefinedVar12);
        symbolTable.definePredefinedSymbol(predefinedVar13);
        symbolTable.definePredefinedSymbol(predefinedVar14);
        symbolTable.definePredefinedSymbol(predefinedVar15);
        symbolTable.definePredefinedSymbol(predefinedVar16);
        symbolTable.definePredefinedSymbol(predefinedVar17);
        symbolTable.definePredefinedSymbol(predefinedVar18);
        symbolTable.definePredefinedSymbol(predefinedVar19);
        symbolTable.definePredefinedSymbol(predefinedVar20);

        currentScope = symbolTable.GLOBALS;

    }

    @Override
    public Object visitVariableDeclaration (B314Parser.VariableDeclarationContext ctx)
    {

        // We create the symbol out of the context
        VariableSymbol variableSymbol = new VariableSymbol(ctx.ID().getText());

        // If the symbol already exist in the current or upper scopes, we throw an exception
        if (currentScope.resolve(ctx.ID().getText()) != null) {
            throw new AlreadyUsedIdentifierException("SymbolTableVisitor.visitVariableDeclaration(context)");
        }

        // We define the scope of the new symbol to the current one.
        variableSymbol.setScope(currentScope);

        // We save the reference of the symbol for later use in the type definition
        pendingSymbol = variableSymbol;

        // We visit the type definition
        ctx.type().accept(this);

        return null;
    }

    @Override
    public Object visitFunctionDeclaration (B314Parser.FunctionDeclarationContext ctx)
    {

        // Creates the scoped symbol from the context, and set it in its parent scope
        FunctionSymbol functionSymbol = new FunctionSymbol(ctx.ID().getText());
        functionSymbol.setScope(currentScope);

        // Keep a track of the previous scope (parent scope)
        Scope oldScope = currentScope;

        // We set the new current scope, the function one
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

        return null;
    }

    @Override
    public Object visitArray (B314Parser.ArrayContext ctx)
    {
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

        return null;
    }

    @Override
    public Object visitDefaultClause (B314Parser.DefaultClauseContext ctx)
    {
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

        return null;
    }

    public SymbolTable getSymTab ()
    {
        return symbolTable;
    }
}
