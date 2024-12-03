package bChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionCountCheck extends AbstractCheck {
	int count = 0;

	@Override
	public int[] getDefaultTokens() {
		return getAcceptableTokens();
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.EXPR };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
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
		log(ast.getLineNo(), "Number of expressions: " + count + " -HK");
	}
}