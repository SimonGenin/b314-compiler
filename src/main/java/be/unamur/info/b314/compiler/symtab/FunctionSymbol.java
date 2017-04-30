package be.unamur.info.b314.compiler.symtab;

import be.unamur.info.b314.compiler.exception.ParameterHasSameNameAsEnclosingFunctionException;
import be.unamur.info.b314.compiler.exception.VariableHasSameNameAsEnclosingFunctionException;
import org.antlr.symtab.*;
import org.antlr.v4.runtime.ParserRuleContext;

/** This symbol represents a function ala C, not a method ala Java.
 *  You can associate a node in the parse tree that is responsible
 *  for defining this symbol.
 *
 *  @author modified by Simon Genin, from org.antlr.symtab.FunctionSymbol
 */
public class FunctionSymbol extends SymbolWithScope implements TypedSymbol {

	protected ParserRuleContext defNode;
	protected Type retType;

	public FunctionSymbol (String name) {
		super(name);
		setCounterOffset(5);
	}

	public int getDepth() {
        return this.getEnclosingPathToRoot().size() - 2;
    }

	@Override
	public void define (Symbol sym) throws IllegalArgumentException
	{
		super.define(sym);

		// This bloc will check if parameters or local variables have same name as enclosing function.
		if (sym.getName().equals(this.getName())) {
			if (sym instanceof ParameterSymbol)
				throw new ParameterHasSameNameAsEnclosingFunctionException("Param : " + sym.getName());
			if (sym instanceof VariableSymbol)
				throw new VariableHasSameNameAsEnclosingFunctionException("Var : " + sym.getName());

			System.err.println("Shouldn't be here, there is a critical error");
		}

	}

	public void setDefNode(ParserRuleContext defNode) {
		this.defNode = defNode;
	}

	public ParserRuleContext getDefNode() {
		return defNode;
	}

	@Override
	public Type getType() {
		return retType;
	}

	@Override
	public void setType(Type type) {
		retType = type;
	}

	/** Return the number of VariableSymbols specifically defined in the scope.
	 *  This is useful as either the number of parameters or the number of
	 *  parameters and locals depending on how you build the scope tree.
	 */
	public int getNumberOfVariables() {
		return Utils.filter(symbols.values(), s -> s instanceof VariableSymbol).size();
	}

	public int getNumberOfParameters() {
		return Utils.filter(symbols.values(), s -> s instanceof ParameterSymbol).size();
	}

	public String toString() { return name+":"+super.toString(); }
}
