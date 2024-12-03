package aChecks;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadDifficultyCheck extends AbstractCheck {
	private Set<String> uniqueOperands;
	private Set<String> uniqueOperators;
	private int operands = 0;

	@Override
	public int[] getDefaultTokens() {
		return getAcceptableTokens();
	}

	@Override
	public int[] getAcceptableTokens() {
		return HalsteadToken.ALL_TOKENS;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (HalsteadToken.isOperand(ast.getType())) {
			operands++;
			uniqueOperands.add(ast.getText());
		} else {
			uniqueOperators.add(ast.getText());
		}
	}

	@Override
	public void beginTree(DetailAST ast) {
		uniqueOperands = new HashSet<>();
		uniqueOperators = new HashSet<>();
		operands = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		// cast to double
		double n2 = uniqueOperands.size();
		double n1 = uniqueOperators.size();
		// D = (n1 / 2) * (N2 / n2)
		double D = (n1 / 2.0) * (operands / n2);
		String formattedD = String.format("%.2f", D);

		log(ast.getLineNo(), "Halstead Difficulty: " + formattedD + " -HK");
	}
}