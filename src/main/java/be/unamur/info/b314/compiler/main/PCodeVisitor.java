package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.PCode.PCodePrinter;
import be.unamur.info.b314.compiler.symtab.FunctionSymbol;
import org.antlr.symtab.*;

import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created by François on 08-04-17.
 */
public class PCodeVisitor extends B314BaseVisitor {

    private final SymbolTable symTable;

    private final PCodePrinter printer;

    private final GlobalScope scope;

    private Scope currentScope;

    public PCodeVisitor(SymbolTable symTable, PCodePrinter printer){
        this.symTable=symTable;
        this.printer=printer;
        this.scope = this.symTable.GLOBALS;
    }

    @Override
    public Object visitRoot(B314Parser.RootContext ctx) {
        currentScope=this.scope;
        return super.visitRoot(ctx);
    }

    @Override
    public Object visitProgramRule(B314Parser.ProgramRuleContext ctx) {
        return super.visitProgramRule(ctx);
    }

    @Override
    public Object visitWhenClause(B314Parser.WhenClauseContext ctx) {
        return super.visitWhenClause(ctx);
    }

    @Override
    public Object visitDefaultClause(B314Parser.DefaultClauseContext ctx) {

        super.visitDefaultClause(ctx);

        //Test if we have a next action
        //if not prin 0
        int i =0;
        boolean notNext=true;
        while (ctx.instruction(i)!=null){
            String instruction= ctx.instruction(i).getText();
            instruction=instruction.substring(0,4);
            if (instruction.equals("next")){
                notNext=false;
            }
            i++;

        }

        if(notNext){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,0);
            printer.printPrin();
            System.out.println("Return prin 0");
        }

        return null;
    }

    @Override
    public Object visitIfThenDoneInstr(B314Parser.IfThenDoneInstrContext ctx) {

        //Load val of bool
        ctx.exprBool().accept(this);

        //Jump if false
        printer.printFalseJump("false");

        //Do instruction
        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }

        printer.printDefineLabel("false");

        return null;
    }

    @Override
    public Object visitIfThenElseDoneInstr(B314Parser.IfThenElseDoneInstrContext ctx) {

        //Load val of bool
        ctx.exprBool().accept(this);

        printer.printNot();

        //Jump if false
        printer.printFalseJump("true");

        //Do instruction else
        ctx.setinstrucion(1).accept(this);
        //Go to the end of the if
        printer.printUnconditionalJump("end");

        //begin then
        printer.printDefineLabel("true");
        //Do instruction then
        ctx.setinstrucion(0).accept(this);

        //end of if
        printer.printDefineLabel("end");

        return null;
    }


    @Override
    public Object visitComputeInstr(B314Parser.ComputeInstrContext ctx) {
        return super.visitComputeInstr(ctx);
    }

    @Override
    public Object visitSetToInstr(B314Parser.SetToInstrContext ctx) {
        return super.visitSetToInstr(ctx);
    }

    @Override
    public Object visitWhileDoDoneInstr(B314Parser.WhileDoDoneInstrContext ctx) {

        //Come back after intruction in the loop
        printer.printDefineLabel("back_while_test");

        //Load val of bool
        ctx.exprBool().accept(this);

        //Jump if false
        printer.printFalseJump("Go_out_loop");

        //Do instruction
        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }
        //Go to test
        printer.printUnconditionalJump("back_while_test");

        printer.printDefineLabel("Go_out_loop");

        return null;
    }

    @Override
    public Object visitNextInstr(B314Parser.NextInstrContext ctx) {
        return super.visitNextInstr(ctx);
    }

    @Override
    public Object visitShootAct(B314Parser.ShootActContext ctx) {
        return super.visitShootAct(ctx);
    }

    @Override
    public Object visitMoveAct(B314Parser.MoveActContext ctx) {
        return super.visitMoveAct(ctx);
    }

    @Override
    public Object visitUseAct(B314Parser.UseActContext ctx) {
        return super.visitUseAct(ctx);
    }

    @Override
    public Object visitDoNothingAct(B314Parser.DoNothingActContext ctx) {

        printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,0);
        printer.printPrin();
        return null;
    }

    @Override
    public Object visitFunctionDeclaration(B314Parser.FunctionDeclarationContext ctx) {

        //Save currentScope
        Scope oldScope =currentScope;

        //Load FunctionScope
        String id = ctx.ID().getText();
        currentScope = this.getFunctionScope(id);

        //Define label with the id of the function
        printer.printDefineLabel(id);

        //Create SetStackPointer
        if (currentScope.equals(oldScope)){
            printer.printSetStackPointer(5);
        }
        else {
            printer.printSetStackPointer(5+currentScope.getAllSymbols().size());

        }

        for (B314Parser.VardeclContext vardeclContext : ctx.vardecl()) {
          //TODO gérer le chargement des param
        }

        //Do all the instruction
        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }

        //Return to call function
        if (ctx.VOID()!=null) {
            printer.printReturnFromProcedure();
        }
        else{
            printer.printReturnFromFunction();
        }

        //Load the oldScope
        currentScope =oldScope;

        return null;
    }

    @Override
    public Object visitGlobalDeclaration(B314Parser.GlobalDeclarationContext ctx) {
        int space = 0;
        // We start by visiting the vars


        if (ctx.vardecl() != null) {
            for (B314Parser.VardeclContext decl : ctx.vardecl()) {
                space=space+1;
            }
        }
        System.out.println("Reserve space for Global Declaration : "+space);
        printer.printSetStackPointer(space);

        return super.visitGlobalDeclaration(ctx);
    }

    @Override
    public Object visitVariableDeclaration(B314Parser.VariableDeclarationContext ctx) {
        return super.visitVariableDeclaration(ctx);
    }

    @Override
    public Object visitLocalDeclaration(B314Parser.LocalDeclarationContext ctx) {
        int space = 0;
        // We start by visiting the vars


        if (ctx.vardecl() != null) {
            for (B314Parser.VardeclContext decl : ctx.vardecl()) {
                space=space+1;


            }
        }
        System.out.println("Reserve space for Local Declaration : "+space);
        printer.printSetStackPointer(space);
        return null;
    }

    @Override
    public Object visitArray(B314Parser.ArrayContext ctx) {
        //TODO voir comment directement le faire pour toute avec tab symbole
        String scalar = ctx.scalar().getText();
        int number = Integer.parseInt(ctx.NUMBER(0).getText());


        if (ctx.NUMBER(1)!=null){
            number = number*Integer.parseInt(ctx.NUMBER(1).getText());
            if (scalar.equals("square")){
                number =number*2;
            }
        }

        if (scalar.equals("square")){
            number =number*2;
        }

        // Reserve space for array
        System.out.println("Reserve space for array : "+ number);
        printer.printSetStackPointer(number);

        return null;
    }

    @Override
    public Object visitType(B314Parser.TypeContext ctx) {
        return super.visitType(ctx);
    }

    @Override
    public Object visitScalar(B314Parser.ScalarContext ctx) {
        return super.visitScalar(ctx);
    }

    @Override
    public Object visitExpr(B314Parser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }
    //TODO remettre

    @Override
    public Object visitModMulDivExpr(B314Parser.ModMulDivExprContext ctx) {
        //Load Value
        ctx.exprInt(0).accept(this);
        ctx.exprInt(1).accept(this);

        if(ctx.MODULO()!=null){
            printer.printMod(PCodePrinter.PCodeTypes.Int);
        }
        if (ctx.MUL()!=null){
            printer.printMul(PCodePrinter.PCodeTypes.Int);
        }
        else {
           printer.printDiv(PCodePrinter.PCodeTypes.Int);
       }
        return null;
    }

    @Override
    public Object visitIntegerExpr(B314Parser.IntegerExprContext ctx) {

        int value = Integer.parseInt(ctx.getText()); // Get value
        printer.printLoadConstant(PCodePrinter.PCodeTypes.Int, value); // Load constant value

        return null;
    }


    @Override
    public Object visitItemCountExpr(B314Parser.ItemCountExprContext ctx) {

        //Load Value
        if(ctx.MAP()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 3);
        }
        if(ctx.RADIO()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 4);
        }
        if(ctx.AMMO()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 5);
        }
        if(ctx.FRUITS()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 6);
        }
        if(ctx.SODA()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 7);
        }
        return null;
    }

    @Override
    public Object visitLifeExpr(B314Parser.LifeExprContext ctx) {
        printer.printLoad(PCodePrinter.PCodeTypes.Int,0,8);
        return null;
    }

    //TODO remettre
    @Override
    public Object visitPlusMinusExpr(B314Parser.PlusMinusExprContext ctx) {
        //Load value
        ctx.exprInt(0).accept(this);
        ctx.exprInt(1).accept(this);

        if(ctx.PLUS()!=null){
            printer.printAdd(PCodePrinter.PCodeTypes.Int);
        }
        else {
            printer.printSub(PCodePrinter.PCodeTypes.Int);
        }
        return null;
    }

    @Override
    public Object visitLatLongGridSizeExpr(B314Parser.LatLongGridSizeExprContext ctx) {

        //Load Value
        if(ctx.LATITUDE()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 0);
        }
        if(ctx.LONGITUDE()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 1);
        }
        if(ctx.GRID()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, 0, 2);
        }

        return null ;
    }

    @Override
    public Object visitIdIntExpr(B314Parser.IdIntExprContext ctx) {

        return super.visitIdIntExpr(ctx);
    }

    @Override
    public Object visitEqualBoolExpr(B314Parser.EqualBoolExprContext ctx) {

        //Load Values
        ctx.exprBool(0).accept(this);
        ctx.exprBool(1).accept(this);

        printer.printEqualsValues(PCodePrinter.PCodeTypes.Bool);

        return null;
    }

    @Override
    public Object visitEqualIdExpr(B314Parser.EqualIdExprContext ctx) {

        //Load Values
        ctx.exprId(0).accept(this);
        ctx.exprId(1).accept(this);

        //TODO est ce qu on peut comparer 2 tab ?

        TypedSymbol symbol =(TypedSymbol) currentScope.resolve(ctx.exprId(0).getText());

        //Test type of var and print equal
        if(symbol.getType().equals("integer")){
            printer.printEqualsValues(PCodePrinter.PCodeTypes.Int);
        }
        else {
            printer.printEqualsValues(PCodePrinter.PCodeTypes.Bool);
        }


        return null;
    }

    @Override
    public Object visitNotExpr(B314Parser.NotExprContext ctx) {

        //Load value
        ctx.exprBool().accept(this);

        //Not value
        printer.printNot();

        return null;
    }

    @Override
    public Object visitIdBoolExpr(B314Parser.IdBoolExprContext ctx) {
        return super.visitIdBoolExpr(ctx);
    }

    @Override
    public Object visitEqualCaseExpr(B314Parser.EqualCaseExprContext ctx) {


        //TODO Voir comment le type es défini en mem pour le test egualité
        //Load Values
        ctx.exprCase(0).accept(this);
        ctx.exprCase(1).accept(this);

       // printer.printEqualsValues();


        return null;
    }

    @Override
    public Object visitSmthIsDirExpr(B314Parser.SmthIsDirExprContext ctx) {

        //Load Value
        if(ctx.ENNEMI()!=null){
            if(ctx.NORTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,9);
            }
            if(ctx.EAST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,10);
            }
            if(ctx.SOUTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,11);
            }
            if(ctx.WEST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,12);
            }
        }
        else {
            if(ctx.NORTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,13);
            }
            if(ctx.EAST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,14);
            }
            if(ctx.SOUTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,15);
            }
            if(ctx.WEST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,0,16);
            }
        }
        return null;
    }

    @Override
    public Object visitCompExpr(B314Parser.CompExprContext ctx) {

        //Load Values
        ctx.exprInt(0).accept(this);
        ctx.exprInt(1).accept(this);

        if(ctx.EQUALS_TO()!=null){
            printer.printEqualsValues(PCodePrinter.PCodeTypes.Int);
        }
        if (ctx.GREATER_THAN()!=null){
            printer.printGreather(PCodePrinter.PCodeTypes.Int);
        }
        if (ctx.SMALLER_THAN()!=null){
            printer.printLess(PCodePrinter.PCodeTypes.Int);
        }
        return null;
    }

    @Override
    public Object visitTrueFalseExpr(B314Parser.TrueFalseExprContext ctx) {

        if(ctx.TRUE()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Bool,1);
        }
        else {
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Bool,0);
        }

        return null;
    }


    @Override
    public Object visitAndOrExpr(B314Parser.AndOrExprContext ctx) {

        //Load value
        ctx.exprBool(0).accept(this);
        ctx.exprBool(1).accept(this);
        if (ctx.OR()!=null) {
            printer.printOr();
        }
        else {
            printer.printAnd();
        }

        return null;
    }

    @Override
    public Object visitExprCase(B314Parser.ExprCaseContext ctx) {

        //Load Value
        if(ctx.exprCase()!=null){
            ctx.exprCase().accept(this);
        }
        else{
            if(ctx.DIRT()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,0);
            }
            if(ctx.ROCK()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,1);
            }
            if(ctx.VINES()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,2);
            }
            if(ctx.ZOMBIE()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,3);
            }
            if(ctx.PLAYER()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,4);
            }
            if(ctx.ENNEMI()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,5);
            }
            if(ctx.MAP()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,6);
            }
            if(ctx.RADIO()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,7);
            }
            if(ctx.AMMO()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,8);
            }
            if(ctx.FRUITS()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,9);
            }
            if(ctx.SODA()!=null){
                printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,10);
            }
            if(ctx.NEARBY()!=null){
                int x = Integer.parseInt(ctx.exprInt(0).getText()); // Get x
                int y = Integer.parseInt(ctx.exprInt(1).getText()); // Get y

                int address = 17+ x*9+y;

                printer.printLoad(PCodePrinter.PCodeTypes.Int,0,address);
            }
            if (ctx.exprId()!=null){
                ctx.exprId().accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visitParIDExpr(B314Parser.ParIDExprContext ctx) {
        return super.visitParIDExpr(ctx);
    }

    @Override
    public Object visitArrayIndexExprID(B314Parser.ArrayIndexExprIDContext ctx) {
        return super.visitArrayIndexExprID(ctx);
    }

    @Override
    public Object visitFctCallExprID(B314Parser.FctCallExprIDContext ctx) {


        printer.printMarkStack(0);//TODO vérifier diff prof

        //Load all param
        for (B314Parser.ExprContext exprContext : ctx.expr()) {
            exprContext.accept(this);
        }

        //call fct
        printer.printCallUserProcedure(ctx.expr().size(),ctx.identifier().getText());
        return null;
    }

    @Override
    public Object visitIdentifierExprID(B314Parser.IdentifierExprIDContext ctx) {
        return super.visitIdentifierExprID(ctx);
    }

    @Override
    public Object visitExprL(B314Parser.ExprLContext ctx) {
        return super.visitExprL(ctx);
    }

    @Override
    public Object visitArrayExpr(B314Parser.ArrayExprContext ctx) {
        return super.visitArrayExpr(ctx);
    }

    @Override
    public Object visitIdentifier(B314Parser.IdentifierContext ctx) {
        return super.visitIdentifier(ctx);
    }

    public void initEnvVar(){
        printer.printSetStackPointer(99);
        for (int i = 0; i <99 ; i++) {
            printer.printLoadAdress(PCodePrinter.PCodeTypes.Int,0,i);
            printer.printRead();
            printer.printStore(PCodePrinter.PCodeTypes.Int);
        }

    }

    public void endPCode(){
        printer.printStop();
    }

    /**
     * We use this function in the case where the var are not in the global scope
     *
     * @param nameFct Name of the current function scope
     * @param nameVar Name of the Var that we want the PCodeTypes
     * @return PCodeTypes of nameVar
     */

    private PCodePrinter.PCodeTypes typePCVarFct (String nameFct,String nameVar){

        FunctionSymbol sym = (FunctionSymbol) this.scope.resolve(nameFct);

        //Fonction scope
        Scope fctScope = (Scope) sym.getAllSymbols().get(0).getScope();

        System.out.println("test"+fctScope);

        TypedSymbol symbol =(TypedSymbol) fctScope.resolve(nameVar);

        System.out.println("test"+symbol);

       if (symbol.getType().toString().equals("integer")) {
           return PCodePrinter.PCodeTypes.Int;
       }
        else {
           return PCodePrinter.PCodeTypes.Bool;
       }

    }

    /**
     *
     * @param nameFct Name of the function
     * @return Scope of  the function
     */
    private Scope getFunctionScope(String nameFct){
        FunctionSymbol sym = (FunctionSymbol) this.scope.resolve(nameFct);


        try {
            Scope fctScope = (Scope) sym.getAllSymbols().get(0).getScope();
            return fctScope;//Return function scope
        }
        catch (Exception e){
            return currentScope;//If there are not elem in the scope we return the currentScope
        }


    }
}
