package bChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class LoopStatementCountCheck extends AbstractCheck{
private int loopCnt = 0;
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.LITERAL_DO,
						  TokenTypes.LITERAL_FOR,
						  TokenTypes.LITERAL_WHILE};
	}
	
	@Override
	public boolean isCommentNodesRequired()
	{
		return true;
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
		loopCnt++;
	}
	@Override
	public void beginTree(DetailAST ast)
	{
		loopCnt = 0;
	}
	 
	@Override
	public void finishTree(DetailAST ast)
	{
		log(ast.getLineNo(), "Comments Count "+ loopCnt + " -HK");
	}
}
