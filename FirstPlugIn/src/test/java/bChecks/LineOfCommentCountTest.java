package bChecks;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class LineOfCommentCountTest {
	LineOfCommentCountCheck testCheck;
	DetailAST mockDetailAST;
	@BeforeEach
	public void setUp() {
		testCheck = spy(new LineOfCommentCountCheck());
        mockDetailAST = mock(DetailAST.class);
        // Initialize the tree
        testCheck.beginTree(mockDetailAST);
    }

	@Test
	public void testGetTokens() {
		int[] a = new int [] {TokenTypes.SINGLE_LINE_COMMENT,TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END};
		assertArrayEquals(a, testCheck.getAcceptableTokens());
		assertArrayEquals(new int [0], testCheck.getRequiredTokens());
		assertArrayEquals(a, testCheck.getDefaultTokens());
	}
	
	// Just verify function call. result can only be check in finishTree (without a getter).
	@Test
	public void testVisitToken() {
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(mockDetailAST).getType();
		doReturn(5).when(mockDetailAST).getLineNo();
	    testCheck.visitToken(mockDetailAST);
	    doReturn(TokenTypes.BLOCK_COMMENT_END).when(mockDetailAST).getType();
		doReturn(10).when(mockDetailAST).getLineNo();
	    testCheck.visitToken(mockDetailAST);
	    when(mockDetailAST.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
	    testCheck.visitToken(mockDetailAST);
	    verify(testCheck, times(3)).visitToken(mockDetailAST);
	}
	
	@Test
	public void testBeginTree() {
		verify(testCheck).beginTree(mockDetailAST);
	}
	
	@Test
	public void testIsCommentNodeRequired() {
		assertTrue(testCheck.isCommentNodesRequired());
	}
	
	@Test
	public void testFinishTree() {
		// Add Block comments
		doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(mockDetailAST).getType();
		doReturn(5).when(mockDetailAST).getLineNo();
	    testCheck.visitToken(mockDetailAST);
	    doReturn(TokenTypes.BLOCK_COMMENT_END).when(mockDetailAST).getType();
		doReturn(10).when(mockDetailAST).getLineNo();
	    testCheck.visitToken(mockDetailAST);
	    doReturn(TokenTypes.BLOCK_COMMENT_BEGIN).when(mockDetailAST).getType();
		doReturn(15).when(mockDetailAST).getLineNo();
	    testCheck.visitToken(mockDetailAST);
	    doReturn(TokenTypes.BLOCK_COMMENT_END).when(mockDetailAST).getType();
		doReturn(15).when(mockDetailAST).getLineNo();
	    testCheck.visitToken(mockDetailAST);
	    
	    // Add line comment
	    when(mockDetailAST.getType()).thenReturn(TokenTypes.SINGLE_LINE_COMMENT);
	    testCheck.visitToken(mockDetailAST);
	    testCheck.visitToken(mockDetailAST);
		
	    // Finish the tree and log the result
	    when(mockDetailAST.getLineNo()).thenReturn(1);
		doNothing().when(testCheck).log(anyInt(), anyString());
		testCheck.finishTree(mockDetailAST);
		
		// Verify log is called and argument passed in
		verify(testCheck).log(eq(1),contains("9"));
	}
}
