package aChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {

	 private int count = 0;

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
		  log(ast.getLineNo(), "Halstead Length: "+ count + " -HK");
	  }

}
