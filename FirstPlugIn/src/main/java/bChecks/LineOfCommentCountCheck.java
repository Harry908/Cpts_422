package bChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class LineOfCommentCountCheck extends AbstractCheck {
	private int lineCnt = 0;

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN,
				TokenTypes.BLOCK_COMMENT_END };
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
		if (ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
			lineCnt -= ast.getLineNo();
		} else if (ast.getType() == TokenTypes.BLOCK_COMMENT_END) {
			lineCnt += ast.getLineNo() + 1;
		} else {
			lineCnt++;
		}
	}

	@Override
	public void beginTree(DetailAST ast) {
		lineCnt = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		log(ast.getLineNo(), "Number of lines of comments: " + lineCnt + " -HK");
	}
}