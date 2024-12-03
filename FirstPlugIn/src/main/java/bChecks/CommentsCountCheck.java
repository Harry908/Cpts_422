package bChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class CommentsCountCheck extends AbstractCheck {
	private int count = 0;

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public boolean isCommentNodesRequired() {
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
		count++;
	}

	@Override
	public void beginTree(DetailAST ast) {
		count = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		log(ast.getLineNo(), "Number of comments: " + count + " -HK");
	}
}