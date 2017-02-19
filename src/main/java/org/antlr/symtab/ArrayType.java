package org.antlr.symtab;

/** An element within a type type such is used in C or Java where we need to
 *  indicate the type is an array of some element type like float[] or User[].
 *  It also tracks the size as some types indicate the size of the array.
 */
public class ArrayType implements Type {

	protected Type elemType;
	protected final Integer firstArg;
	protected final Integer secondArg;

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

	@Override
	public int getTypeIndex() { return -1; }

	@Override
	public String toString() {
		return elemType+"[]";
	}
}
