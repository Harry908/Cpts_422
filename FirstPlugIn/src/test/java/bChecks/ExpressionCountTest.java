package bChecks;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class ExpressionCountTest {
	ExpressionCountCheck testCheck;
	DetailAST mockDetailAST;
	@BeforeEach
	public void setUp() {
		testCheck = spy(new ExpressionCountCheck());
        mockDetailAST = mock(DetailAST.class);
        // Initialize the tree
        testCheck.beginTree(mockDetailAST);
    }

	@Test
	public void testGetTokens() {
		assertArrayEquals(new int [] {TokenTypes.EXPR}, testCheck.getAcceptableTokens());
		assertArrayEquals(new int [0], testCheck.getRequiredTokens());
		assertArrayEquals(new int [0], testCheck.getDefaultTokens());
	}
	
	// Just verify function call. result can only be check in finishTree (without a getter).
	@Test
	public void testVisitToken() {
	    testCheck.visitToken(mockDetailAST);
	    testCheck.visitToken(mockDetailAST);
	    testCheck.visitToken(mockDetailAST);
	    verify(testCheck, times(3)).visitToken(mockDetailAST);
	}
	
	@Test
	public void testBeginTree() {
		verify(testCheck).beginTree(mockDetailAST);
	}
	
	@Test
	public void testFinishTree() {
		testCheck.visitToken(mockDetailAST);
		testCheck.visitToken(mockDetailAST);
		testCheck.visitToken(mockDetailAST);
		testCheck.visitToken(mockDetailAST);
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getLineNo()).thenReturn(1);
		// Finish the tree and log the result
		doNothing().when(testCheck).log(anyInt(), anyString());
		testCheck.finishTree(mockDetailAST);
		
		// Verify log is called and argument passed in
		verify(testCheck).log(eq(1),contains("5"));
	}
}
