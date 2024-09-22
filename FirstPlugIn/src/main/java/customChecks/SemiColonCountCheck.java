package customChecks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SemiColonCountCheck extends AbstractCheck {
  
  private int count = 0;

  @Override
  public int[] getAcceptableTokens() {
    return new int[] {TokenTypes.SEMI };
  }

  @Override
  public int[] getRequiredTokens() {
    return new int[0];
  }

  @Override
  public int[] getDefaultTokens() {
    return new int[] { TokenTypes.SEMI };
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
	  log(ast.getLineNo(), "Semi Colon Count "+ count);
  }
}

