package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import org.antlr.symtab.SymbolTable;
import org.antlr.symtab.VariableSymbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Created by Simon on 15/02/17.
 */
public class SymbolTableVisitor extends B314BaseVisitor
{

    private static final Logger LOG = LoggerFactory.getLogger(SymbolTableVisitor.class);

    private SymbolTable symbolTable;

    public SymbolTableVisitor () {

        symbolTable = new SymbolTable();

        // The predefined variables' symbols

        symbolTable.definePredefinedSymbol(new VariableSymbol("ennemi"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("zombie"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("player"));

        symbolTable.definePredefinedSymbol(new VariableSymbol("latitude"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("longitude"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("north"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("south"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("east"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("west"));

        symbolTable.definePredefinedSymbol(new VariableSymbol("graal"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("grid"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("size"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("map"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("radio"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("ammo"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("fruits"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("life"));

        symbolTable.definePredefinedSymbol(new VariableSymbol("dirt"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("rock"));
        symbolTable.definePredefinedSymbol(new VariableSymbol("vines"));

    }


}
