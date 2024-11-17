package bChecks;
 
import com.puppycrawl.tools.checkstyle.api.*;

public class LineOfCommentCountCheck extends AbstractCheck {
	private int lineCnt = 0;
	
	@Override
	public int[] getAcceptableTokens() {
		return new int[] {TokenTypes.COMMENT_CONTENT };
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
	}
	@Override
	public void beginTree(DetailAST ast)
	{
		lineCnt = 0;
	}
	 
	@Override
	public void finishTree(DetailAST ast)
	{
		log(ast.getLineNo(), "Comments Count "+ lineCnt + " -HK");
	}
}
