package aChecks;
import java.util.HashSet;
import java.util.Set;

import com.puppycrawl.tools.checkstyle.api.*;
public class HalsteadVocabularyCheck extends AbstractCheck{
	private Set<String> visitedTokens;
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
		visitedTokens.add(ast.getText());
	}
	@Override
	public void beginTree(DetailAST ast)
	{
		visitedTokens = new HashSet<>();
	}
	 
	@Override
	public void finishTree(DetailAST ast)
	{  
		log(ast.getLineNo(), "Halstead Vocabulary: "+ (visitedTokens.size()) + " -HK");
	}
	  
}
