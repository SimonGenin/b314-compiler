package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.PCode.PCodePrinter;
import be.unamur.info.b314.compiler.symtab.*;
import be.unamur.info.b314.compiler.symtab.FunctionSymbol;
import org.antlr.symtab.*;
import sun.reflect.generics.tree.TypeSignature;

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

    private int whenIndex;

    private int spaceVar;

    private int ifIndex;

    private int whileIndex;

    private int currentDepth;

    private boolean initVarPhase;

    public PCodeVisitor(SymbolTable symTable, PCodePrinter printer){
        this.symTable=symTable;
        this.printer=printer;
        this.scope = this.symTable.GLOBALS;
    }

    @Override
    public Object visitRoot(B314Parser.RootContext ctx) {
        currentScope=this.scope;
/*
        //TODO test
        System.out.println("index : "+this.getVarIndex("v3"));
        System.out.println("index : "+this.getVarIndex("v2"));
        System.out.println("index : "+this.getVarIndex("v4"));

        TypedSymbol sym=(TypedSymbol) currentScope.resolve("v3");
        System.out.println("le type est "+this.getPCodeTypes("fct5"));

*/



        return super.visitRoot(ctx);
    }

    @Override
    public Object visitProgramRule(B314Parser.ProgramRuleContext ctx) {

        ////Visit global decl of var and fct
        ctx.globdecl().accept(this);



        //Visit When clause
        for (B314Parser.ClauseWhenContext clauseWhenContext : ctx.clauseWhen()) {
            clauseWhenContext.accept(this);
        }

        //Visit Default clause
        ctx.clauseDefault().accept(this);

        printer.printComments("");
        printer.printComments("------START PROGRAM-----");

        //Begin of the program
        printer.printDefineLabel("begin");

        for (int i = 0; i <this.whenIndex ; i++) {
            printer.printMarkStack(0);
            printer.printCallUserProcedure(0,"when"+i);
        }
        printer.printMarkStack(0);
        printer.printCallUserProcedure(0,"default");

        return null;
    }

    @Override
    public Object visitWhenClause(B314Parser.WhenClauseContext ctx) {
        this.spaceVar=5;

        printer.printComments("");
        printer.printComments("Begin whenClause : "+this.whenIndex);

        this.currentDepth=1;

        printer.printDefineLabel("when"+this.whenIndex);

        //Save currentScope
        Scope oldScope = currentScope;

        //Load FunctionScope of when
        currentScope = this.getFunctionScope(Integer.toString(this.whenIndex));
        this.whenIndex++;

        //Load Bool
        ctx.exprBool().accept(this);

        //If false jump at the end
        printer.printFalseJump("endWhen"+(this.whenIndex-1));

        //Visit local decl to count the space
        if(ctx.localvardecl()!=null) {
            ctx.localvardecl().accept(this);
        }

        printer.printSetStackPointer(this.spaceVar);

        //Visit local decl to init var
        if(ctx.localvardecl()!=null) {
            this.initVarPhase=true;
            ctx.localvardecl().accept(this);
            this.initVarPhase=false;
        }

        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }

        printer.printDefineLabel("endWhen"+(this.whenIndex-1));
        currentScope=oldScope;

        printer.printReturnFromProcedure();

        printer.printComments("End whenClause : "+(this.whenIndex-1));
        this.currentDepth =0;
        return null;
    }

    @Override
    public Object visitDefaultClause(B314Parser.DefaultClauseContext ctx) {


        this.spaceVar=5;
        this.currentDepth =1;

        printer.printComments("");
        printer.printComments("Begin Default");

        printer.printDefineLabel("default");
        //Save currentScope
        Scope oldScope =currentScope;

        //Load FunctionScope of when
        currentScope = this.getFunctionScope("default");

        //Visit local decl to count space for var
        if(ctx.localvardecl()!=null) {
            ctx.localvardecl().accept(this);
        }

        printer.printSetStackPointer(this.spaceVar);

        //Visit local decl to init var
        if(ctx.localvardecl()!=null) {
            this.initVarPhase=true;
            ctx.localvardecl().accept(this);
            this.initVarPhase=false;
        }

        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }

        currentScope=oldScope;

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
            printer.printStop();
        }

        printer.printReturnFromProcedure();
        printer.printComments("End Default");

        this.currentDepth =0;
        return null;
    }

    @Override
    public Object visitIfThenDoneInstr(B314Parser.IfThenDoneInstrContext ctx) {

        this.ifIndex++;
        int i =this.ifIndex;

        //Load val of bool
        ctx.exprBool().accept(this);

        //Jump if false
        printer.printFalseJump("If_false"+i);

        //Do instruction
        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }

        printer.printDefineLabel("If_false"+i);

        return null;
    }

    @Override
    public Object visitIfThenElseDoneInstr(B314Parser.IfThenElseDoneInstrContext ctx) {

        this.ifIndex++;
        int i =this.ifIndex;
        //Load val of bool
        ctx.exprBool().accept(this);

        printer.printNot();

        //Jump if false
        printer.printFalseJump("If_true"+i);

        //Do instruction else
        ctx.setinstrucion(1).accept(this);
        //Go to the end of the if
        printer.printUnconditionalJump("If_end"+i);

        //begin then
        printer.printDefineLabel("If_true"+i);
        //Do instruction then
        ctx.setinstrucion(0).accept(this);

        //end of if
        printer.printDefineLabel("If_end"+i);

        return null;
    }


    @Override
    public Object visitComputeInstr(B314Parser.ComputeInstrContext ctx) {

        //Load val expr
        ctx.expr().accept(this);
        //Pop val
        printer.printPop();

        return null;
    }

    @Override
    public Object visitSetToInstr(B314Parser.SetToInstrContext ctx) {
        //Load adress var
        ctx.exprL().accept(this);

        //Load value et to var
        ctx.expr().accept(this);

        //TODO Gérer les cas ou tab et fct
        //Store the Value
       // printer.printStore(this.getPCodeTypes(ctx.exprL().getText()));

        return null;
    }

    @Override
    public Object visitWhileDoDoneInstr(B314Parser.WhileDoDoneInstrContext ctx) {

        this.whileIndex++;
        int i =this.whenIndex;

        //Come back after intruction in the loop
        printer.printDefineLabel("back_while_test"+i);

        //Load val of bool
        ctx.exprBool().accept(this);

        //Jump if false
        printer.printFalseJump("Go_out_loop"+i);

        //Do instruction
        for (B314Parser.InstructionContext instructionContext : ctx.instruction()) {
            instructionContext.accept(this);
        }
        //Go to test
        printer.printUnconditionalJump("back_while_test"+i);

        printer.printDefineLabel("Go_out_loop"+i);

        return null;
    }



    @Override
    public Object visitShootAct(B314Parser.ShootActContext ctx) {
        if(ctx.NORTH()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,5);
        }
        if(ctx.EAST()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,6);
        }
        if(ctx.SOUTH()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,7);
        }
        if(ctx.WEST()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,8);
        }

        printer.printPrin();
        printer.printStop();

        return null;
    }

    @Override
    public Object visitMoveAct(B314Parser.MoveActContext ctx) {
        if(ctx.NORTH()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,1);
        }
        if(ctx.EAST()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,2);
        }
        if(ctx.SOUTH()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,3);
        }
        if(ctx.WEST()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,4);
        }

        printer.printPrin();
        printer.printStop();

        return null;
    }

    @Override
    public Object visitUseAct(B314Parser.UseActContext ctx) {
        if(ctx.MAP()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,9);
        }
        if(ctx.RADIO()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,10);
        }
        if(ctx.FRUITS()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,11);
        }
        if(ctx.SODA()!=null){
            printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,12);
        }

        printer.printPrin();
        printer.printStop();

        return null;
    }

    @Override
    public Object visitDoNothingAct(B314Parser.DoNothingActContext ctx) {

        printer.printLoadConstant(PCodePrinter.PCodeTypes.Int,0);
        printer.printPrin();
        printer.printStop();
        return null;
    }

    @Override
    public Object visitFunctionDeclaration(B314Parser.FunctionDeclarationContext ctx) {

        this.currentDepth=1;

        printer.printComments("");
        printer.printComments("Begin function : "+ctx.ID().getText());

        this.spaceVar=5;

        //Save currentScope
        Scope oldScope =currentScope;

        //Load FunctionScope
        String id = ctx.ID().getText();
        currentScope = this.getFunctionScope(id);

        //Define label with the id of the function
        printer.printDefineLabel(id);

        //Visit param
        for (B314Parser.VardeclContext vardeclContext : ctx.vardecl()) {
          //TODO gérer le chargement des param
            vardeclContext.accept(this);
        }

        //Visit local decl to count the space
        if(ctx.localvardecl()!=null) {
            ctx.localvardecl().accept(this);
        }

        printer.printSetStackPointer(this.spaceVar);

        //Visit local decl to init var
        if(ctx.localvardecl()!=null) {
            this.initVarPhase=true;
            ctx.localvardecl().accept(this);
            this.initVarPhase=false;
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

        printer.printComments("End function : "+ctx.ID().getText());

        this.currentDepth=0;

        return null;
    }

    @Override
    public Object visitGlobalDeclaration(B314Parser.GlobalDeclarationContext ctx) {
        this.spaceVar=99;

        printer.printComments("Global_Declaration_Start");

        // We start by visiting the vars decl for calculate space mem
        if (ctx.vardecl() != null) {
            for (B314Parser.VardeclContext decl : ctx.vardecl()) {
                decl.accept(this);
            }
        }
        System.out.println("Reserve space for Global Declaration : "+this.spaceVar);
        printer.printSetStackPointer(this.spaceVar);

        if (ctx.vardecl() != null) {
            this.initVarPhase =true;
            for (B314Parser.VardeclContext decl : ctx.vardecl()) {
                decl.accept(this);
            }
            this.initVarPhase=false;
        }

        //Read 99 value
        this.initEnvVar();

        //Jump at the begin of the program
        printer.printUnconditionalJump("begin");

        //Visit fct decl
        if (ctx.fctdecl() != null){
            for (B314Parser.FctdeclContext fctdeclContext : ctx.fctdecl()) {
                fctdeclContext.accept(this);
            }
        }
        printer.printComments("Global_Declaration_finish");
        return null;
    }

    @Override
    public Object visitVariableDeclaration(B314Parser.VariableDeclarationContext ctx) {

        if (ctx.type().scalar()!=null){
            //add 1 to space for scalar var
            this.spaceVar=this.spaceVar+1;
            if (this.initVarPhase){
                printer.printComments("Init var : "+ctx.ID().getText());
                printer.printLoadAdress(this.getPCodeTypes(ctx.ID().getText()),0,this.getVarIndex(ctx.ID().getText())); //adress var
                printer.printLoadConstant(this.getPCodeTypes(ctx.ID().getText()),0); //val init
                printer.printStore(this.getPCodeTypes(ctx.ID().getText()));//Init var
            }
        }
        else {
            //add size of array for array var
            int x = Integer.parseInt(ctx.type().array().NUMBER(0).getText());
            int y=1;
            if (ctx.type().array().NUMBER(1)!=null){
                y = Integer.parseInt(ctx.type().array().NUMBER(1).getText());
            }
            this.spaceVar=this.spaceVar+(x*y);

            //Init array value
            if (this.initVarPhase){
                printer.printComments("Init array : "+ctx.ID().getText());
                for (int i = (this.getVarIndex(ctx.ID().getText())); i < (this.getVarIndex(ctx.ID().getText())+(x*y)); i++) {

                        printer.printLoadAdress(this.getPCodeTypes(ctx.ID().getText()),0,i); //adress var
                        printer.printLoadConstant(this.getPCodeTypes(ctx.ID().getText()),0); //val init
                        printer.printStore(this.getPCodeTypes(ctx.ID().getText()));//Init var
                    }
                printer.printComments("End Init array : "+ctx.ID().getText());
            }


        }

        return null;
    }

    @Override
    public Object visitLocalDeclaration(B314Parser.LocalDeclarationContext ctx) {

        // We start by visiting the vars
        if (ctx.vardecl() != null) {
            //Calculate space
            for (B314Parser.VardeclContext decl : ctx.vardecl()) {
                decl.accept(this);
            }
        }
        return null;
    }


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
       if(ctx.DIV()!=null) {
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
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 3);
        }
        if(ctx.RADIO()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 4);
        }
        if(ctx.AMMO()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 5);
        }
        if(ctx.FRUITS()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 6);
        }
        if(ctx.SODA()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 7);
        }
        return null;
    }

    @Override
    public Object visitLifeExpr(B314Parser.LifeExprContext ctx) {
        printer.printLoad(PCodePrinter.PCodeTypes.Int,currentDepth,8);
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
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 0);
        }
        if(ctx.LONGITUDE()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 1);
        }
        if(ctx.GRID()!=null) {
            printer.printLoad(PCodePrinter.PCodeTypes.Int, currentDepth, 2);
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
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,9);
            }
            if(ctx.EAST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,10);
            }
            if(ctx.SOUTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,11);
            }
            if(ctx.WEST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,12);
            }
        }
        else {
            if(ctx.NORTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,13);
            }
            if(ctx.EAST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,14);
            }
            if(ctx.SOUTH()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,15);
            }
            if(ctx.WEST()!=null){
                printer.printLoad(PCodePrinter.PCodeTypes.Bool,currentDepth,16);
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
                //load adress nearby
                printer.printLoadAdress(PCodePrinter.PCodeTypes.Int,currentDepth,17);
                //Load value x
                ctx.exprInt(0).accept(this);
                //shift x
                printer.printIndexedAdressComputation(9);

                //Load y value
                ctx.exprInt(1).accept(this);
                //shift y
                printer.printIndexedAdressComputation(1);

                //Load value at adress x*9 + y +17
                printer.printIndexedFetch(PCodePrinter.PCodeTypes.Int);
                //TODO vérifier val

            }
            if (ctx.exprId()!=null){
                ctx.exprId().accept(this);
            }
        }
        return null;
    }



    @Override
    public Object visitFctCallExprID(B314Parser.FctCallExprIDContext ctx) {

        printer.printComments("Call : "+ctx.identifier().getText());
        printer.printMarkStack(1);//TODO vérifier diff prof

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
        //TODO ajouter profondeur


        printer.printLoad(this.getPCodeTypes(ctx.getText()),this.getVarDepth(ctx.getText()),this.getVarIndex(ctx.getText()));
        return null;
    }

    @Override
    public Object visitExprL(B314Parser.ExprLContext ctx) {
        //TODO ajouter profondeur
        //System.out.println(ctx.getText());
        /*if (ctx.identifier()!=null){
            printer.printLoadAdress(this.getPCodeTypes(ctx.identifier().getText()),0,this.getVarIndex(ctx.getText()));
        }
        else {
           // printer.printLoadAdress(this.getPCodeTypes());
        }
*/
        return null;
    }

    @Override
    public Object visitArrayExpr(B314Parser.ArrayExprContext ctx) {

        TypedSymbol sym=(TypedSymbol) currentScope.resolve(ctx.identifier().getText());
        be.unamur.info.b314.compiler.symtab.ArrayType array =(be.unamur.info.b314.compiler.symtab.ArrayType) sym.getType();

        //Load first ind
        ctx.exprInt(0).accept(this);
        //Shift adress
        printer.printIndexedAdressComputation(array.firstArg);

        if(array.secondArg!=null){
            ctx.exprInt(1).accept(this);
            printer.printIndexedAdressComputation(1);
        }

        if(sym.getType().toString().equals("integer[]")){
            printer.printIndexedFetch(PCodePrinter.PCodeTypes.Int);
        }

        if(sym.getType().toString().equals("boolean[]")){
            printer.printIndexedFetch(PCodePrinter.PCodeTypes.Bool);
        }


        return null;
    }

    @Override
    public Object visitIdentifier(B314Parser.IdentifierContext ctx) {
        return super.visitIdentifier(ctx);
    }

    public void initEnvVar(){
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
        //TODO retirer si pas nécessaire
        FunctionSymbol sym = (FunctionSymbol) this.scope.resolve(nameFct);

        //Fonction scope
        Scope fctScope = (Scope) sym.getAllSymbols().get(0).getScope();



        TypedSymbol symbol =(TypedSymbol) fctScope.resolve(nameVar);


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

    /**
     *
     * @param nameVar var's name
     * @return PCodesTypes of variable (nameVar)
     */
    private PCodePrinter.PCodeTypes getPCodeTypes(String nameVar){
        TypedSymbol sym =(TypedSymbol)this.currentScope.resolve(nameVar);

        if (sym.getType().toString().equals("integer")||sym.getType().toString().equals("integer[]")||sym.getType().toString().equals("square")||sym.getType().toString().equals("square[]")) {
            return PCodePrinter.PCodeTypes.Int;
        }
        else {
            return PCodePrinter.PCodeTypes.Bool;
        }
    }

    /**
     *
     * @param nameVar var's name
     * @return Index of var in the Scope
     */
    private int getVarIndex(String nameVar){

        BaseSymbol symbol =(BaseSymbol) currentScope.resolve(nameVar);

        return symbol.getScopeCounter();
    }

    /**
     *
     * @param nameVar var's name
     * @return Depth of var
     */
    private int getVarDepth(String nameVar){
        BaseSymbol sym = (BaseSymbol) currentScope.resolve(nameVar) ;
        return this.currentDepth- sym.getDepth();
    }
}
