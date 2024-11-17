package aChecks;

import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadEffortCheck extends AbstractCheck{
	private Set<String> uniqueOperands;
	private Set<String> uniqueOperators;
	private int operands = 0;
	private int operators = 0;
	
	@Override
	public int[] getDefaultTokens() {
		return getAcceptableTokens();
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return HalsteadToken.ALL_TOKENS;
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return new int [0];
	}
	
	@Override
	public void beginTree(DetailAST ast)
	{
		uniqueOperands = new HashSet<>();
		uniqueOperators = new HashSet<>();
		operands = 0;
		operators = 0;
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		if(HalsteadToken.isOperand(ast.getType()))
		{
			operands++;
			uniqueOperands.add(ast.getText());
		}
		else
		{
			operators++;
			uniqueOperators.add(ast.getText());
		}
	}
	
	@Override
	public void finishTree(DetailAST ast)
	{  
		// cast to double
		double n2 = uniqueOperands.size();
		double n1 = uniqueOperators.size();
		double N = operators + operands; //N1 + N2
		double n = n1 + n2;
		
		// Difficulty: D = (n1 / 2) * (N2 / n2) 
		double D =  (n1 / 2.0) * (operands / n2);
		// Volume: V = N*log2(n)
		double V = N * Math.log(n) / Math.log(2);
		// Effort: E = V*D
		double E = V*D;
		String formattedE = String.format("%.2f", E);
		
		log(ast.getLineNo(), "Halstead Effort: "+ formattedE + " -HK");
	}
}