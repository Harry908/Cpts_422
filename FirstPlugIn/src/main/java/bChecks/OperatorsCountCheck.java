package bChecks;

import aChecks.HalsteadToken;
import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorsCountCheck extends AbstractCheck {
	private int count = 0;

	@Override
	public int[] getDefaultTokens() {
		return getAcceptableTokens();
	}

	@Override
	public int[] getAcceptableTokens() {
		return HalsteadToken.OPERATORS;
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
	public void beginTree(DetailAST ast) {
		count = 0;
	}
	 
	@Override
	public void finishTree(DetailAST ast) {
		log(ast.getLineNo(), "Number of operators: "+ count + " -HK");
	}
}