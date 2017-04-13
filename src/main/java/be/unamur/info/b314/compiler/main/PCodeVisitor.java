package be.unamur.info.b314.compiler.main;

import be.unamur.info.b314.compiler.B314BaseVisitor;
import be.unamur.info.b314.compiler.B314Parser;
import be.unamur.info.b314.compiler.PCode.PCodePrinter;

import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created by François on 08-04-17.
 */
public class PCodeVisitor extends B314BaseVisitor {

   // private final Map<String,Integer> symTable;

    private final PCodePrinter printer;

    public PCodeVisitor(/*Map<String,Integer> symTable,*/PCodePrinter printer){
        //this.symTable=symTable;
        this.printer=printer;

    }

    @Override
    public Object visitRoot(B314Parser.RootContext ctx) {
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
    public Object visitSkipInstr(B314Parser.SkipInstrContext ctx) {
        return super.visitSkipInstr(ctx);
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
    public Object visitSetInstruction(B314Parser.SetInstructionContext ctx) {
        //OK
        return super.visitSetInstruction(ctx);
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
        return super.visitWhileDoDoneInstr(ctx);
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
        return super.visitFunctionDeclaration(ctx);
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

    @Override
    public Object visitModMulDivExpr(B314Parser.ModMulDivExprContext ctx) {
        return super.visitModMulDivExpr(ctx);
    }

    @Override
    public Object visitIntegerExpr(B314Parser.IntegerExprContext ctx) {
        return super.visitIntegerExpr(ctx);
    }

    @Override
    public Object visitParIntExpr(B314Parser.ParIntExprContext ctx) {
        return super.visitParIntExpr(ctx);
    }

    @Override
    public Object visitItemCountExpr(B314Parser.ItemCountExprContext ctx) {
        return super.visitItemCountExpr(ctx);
    }

    @Override
    public Object visitLifeExpr(B314Parser.LifeExprContext ctx) {
        return super.visitLifeExpr(ctx);
    }

    @Override
    public Object visitPlusMinusExpr(B314Parser.PlusMinusExprContext ctx) {
        return super.visitPlusMinusExpr(ctx);
    }

    @Override
    public Object visitLatLongGridSizeExpr(B314Parser.LatLongGridSizeExprContext ctx) {
        return super.visitLatLongGridSizeExpr(ctx);
    }

    @Override
    public Object visitIdIntExpr(B314Parser.IdIntExprContext ctx) {
        return super.visitIdIntExpr(ctx);
    }

    @Override
    public Object visitEqualBoolExpr(B314Parser.EqualBoolExprContext ctx) {
        return super.visitEqualBoolExpr(ctx);
    }

    @Override
    public Object visitNotExpr(B314Parser.NotExprContext ctx) {
        return super.visitNotExpr(ctx);
    }

    @Override
    public Object visitIdBoolExpr(B314Parser.IdBoolExprContext ctx) {
        return super.visitIdBoolExpr(ctx);
    }

    @Override
    public Object visitEqualCaseExpr(B314Parser.EqualCaseExprContext ctx) {
        return super.visitEqualCaseExpr(ctx);
    }

    @Override
    public Object visitSmthIsDirExpr(B314Parser.SmthIsDirExprContext ctx) {
        return super.visitSmthIsDirExpr(ctx);
    }

    @Override
    public Object visitCompExpr(B314Parser.CompExprContext ctx) {
        return super.visitCompExpr(ctx);
    }

    @Override
    public Object visitTrueFalseExpr(B314Parser.TrueFalseExprContext ctx) {
        return super.visitTrueFalseExpr(ctx);
    }

    @Override
    public Object visitParBoolExpr(B314Parser.ParBoolExprContext ctx) {
        return super.visitParBoolExpr(ctx);
    }

    @Override
    public Object visitAndOrExpr(B314Parser.AndOrExprContext ctx) {
        return super.visitAndOrExpr(ctx);
    }

    @Override
    public Object visitExprCase(B314Parser.ExprCaseContext ctx) {
        return super.visitExprCase(ctx);
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
        return super.visitFctCallExprID(ctx);
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
}
