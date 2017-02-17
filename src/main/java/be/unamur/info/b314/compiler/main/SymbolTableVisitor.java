package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.exception.AlreadyUsedIdentifierException;
import org.antlr.symtab.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;

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
    public Object visitGlobalDeclaration (B314Parser.GlobalDeclarationContext ctx)
    {
        nextDecl = GLOBAL;
        return super.visitGlobalDeclaration(ctx);
    }

    @Override
    public Object visitLocalDeclaration (B314Parser.LocalDeclarationContext ctx)
    {

        nextDecl = LOCAL;
        return super.visitLocalDeclaration(ctx);
    }

    @Override
    public Object visitVariableDeclaration (B314Parser.VariableDeclarationContext ctx)
    {
        VariableSymbol variableSymbol = new VariableSymbol(ctx.ID().getText());

        if (currentScope.resolve(ctx.ID().getText()) != null) {

            throw new AlreadyUsedIdentifierException("SymbolTableVisitor.visitVariableDeclaration()");

        }

        // TODO do we need this ?
        if (nextDecl == GLOBAL)
            variableSymbol.setScope(symbolTable.GLOBALS);
        else
            variableSymbol.setScope(currentScope);

        // we keep the ref of the symbol, and go to the type definition
        pendingSymbol = variableSymbol;
        ctx.type().accept(this);

        return null;
    }

    @Override
    public Object visitFunctionDeclaration (B314Parser.FunctionDeclarationContext ctx)
    {

        FunctionSymbol functionSymbol = new FunctionSymbol(ctx.ID().getText());
        functionSymbol.setScope(currentScope);

        Scope oldScope = currentScope;

        // We set the new scope
        currentScope = functionSymbol;

        // Return type
        if (ctx.VOID() != null) {
            functionSymbol.setType(voidType);
        } else {
            pendingSymbol = functionSymbol;
            ctx.scalar().accept(this);
        }

        // The parameters
        nextDecl = LOCAL;
        for (B314Parser.VardeclContext varDeclaration : ctx.vardecl()) {
            varDeclaration.accept(this);
        }

        if (ctx.localvardecl() != null)
        {
            // the local var declarations
            ctx.localvardecl().accept(this);
        }

        // We reastablish the anterior scope
        currentScope = oldScope;

        return null;
    }

    @Override
    public Object visitArray (B314Parser.ArrayContext ctx)
    {
        // TODO manage the several possible outcomes

        boolean severalArgs = false;

        int firstArg = Integer.parseInt(ctx.NUMBER(0).getText());
        int secondArg = 0;

        ArrayType type;

        if ( ctx.NUMBER(1) != null ) {

            severalArgs = true;
            secondArg = Integer.parseInt(ctx.NUMBER(1).getText());

        }

        if (severalArgs) {

            type = new ArrayType(firstArg, secondArg);

        } else {

            type = new ArrayType(firstArg, null);

        }

        // We set the array type, and visit the scalar for the array type
        ((TypedSymbol) pendingSymbol).setType(type);
        ctx.scalar().accept(this);

        return null;
    }

    @Override
    public Object visitScalar (B314Parser.ScalarContext ctx)
    {

        boolean isArray = false;

        // If we deal with an array
        if (((TypedSymbol)pendingSymbol).getType() != null) {
            isArray = true;
        }

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

    public SymbolTable getSymTab ()
    {
        return symbolTable;
    }
}
