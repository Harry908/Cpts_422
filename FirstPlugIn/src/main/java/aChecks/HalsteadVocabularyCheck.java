package aChecks;
import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.*;
public class HalsteadVocabularyCheck extends AbstractCheck{
	private Set<Integer> visitedTokens;
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
	// Initialize a HashSet to store integers
		getVisitedTokens().add(ast.getType());
	}
	@Override
	public void beginTree(DetailAST ast)
	{
		visitedTokens = new HashSet<>();
	}
	 
	@Override
	public void finishTree(DetailAST ast)
	{  
		log(ast.getLineNo(), "Halstead Vocabulary: "+ getVisitedTokens().size() + " -HK");
	}
	  
	//Getter for testing 
	public Set<Integer> getVisitedTokens() {
		return visitedTokens;
	}

}
