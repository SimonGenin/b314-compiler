package be.unamur.info.b314.compiler.symtab;

import org.antlr.symtab.Type;

/** An element within a type type such is used in C or Java where we need to
 *  indicate the type is an array of some element type like float[] or User[].
 *  It also tracks the size as some types indicate the size of the array.
 */
public class ArrayType implements Type
{

	protected Type elemType;
	public final Integer firstArg;
	public final Integer secondArg;

	public ArrayType(Integer numFirstArg, Integer numSecondArg)
	{
		this.firstArg = numFirstArg;
		this.secondArg = numSecondArg;
	}

	public void setType (Type type) {

		this.elemType = type;

	}

	@Override
	public String getName() {
		return toString();
	}

	/**
	 * @return number of indexes of the symbol that has this type
	 */
	public int getIndexNumber() {

		if (secondArg == null)
			return 1;
		else return 2;

	}

	@Override
	public int getTypeIndex() { return -1; }

	@Override
	public String toString() {
		return elemType+"[]";
	}
}
