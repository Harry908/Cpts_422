package aChecks;

import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVolumeCheck extends AbstractCheck {
	
	private Set<String> visitedTokens;
	private int length = 0;
	@Override
	public int[] getAcceptableTokens() {
	    return HalsteadToken.ALL_TOKENS;
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
	
	@Override 
	public int[] getDefaultTokens() {  
	    return getAcceptableTokens();
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		length++;
		visitedTokens.add(ast.getText());
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		length = 0;
		visitedTokens = new HashSet<>();
	}
	 
	@Override
	public void finishTree(DetailAST ast) {  
		// Volume = N*log2(n)
		// N = length 
		// log2(n)= log(n) / log(2)
		int vocabulary = visitedTokens.size(); // n
		double volume = length * (Math.log(vocabulary) / Math.log(2));
		String formattedVolume = String.format("%.2f", volume);
		
		log(ast.getLineNo(), "Halstead Volume: "+ formattedVolume + " -HK");
	}
}