package bChecks;

import aChecks.HalsteadToken;
import com.puppycrawl.tools.checkstyle.api.*;

public class OperandsCountCheck extends AbstractCheck{
	int count = 0;

	@Override
	public int[] getDefaultTokens() {
		return new int [0];
	}

	@Override
	public int[] getAcceptableTokens() {
		return HalsteadToken.OPERANDS;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int [0];
	}
	@Override
	public void visitToken(DetailAST ast) {
		count++;
	}
	@Override
	public void beginTree(DetailAST ast)
	{
		count = 0;
	}
	 
	@Override
	public void finishTree(DetailAST ast)
	{
		log(ast.getLineNo(), "Number of operands: "+ count + " -HK");
	}
}
