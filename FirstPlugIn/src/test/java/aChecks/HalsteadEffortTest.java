package aChecks;

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

public class HalsteadEffortTest {
	private HalsteadEffortCheck testCheck;
	private DetailAST mockDetailAST;

	@BeforeEach
	public void setUp() {
		testCheck = spy(new HalsteadEffortCheck());
		mockDetailAST = mock(DetailAST.class);
		// Initialize the tree
		testCheck.beginTree(mockDetailAST);
	}

	@Test
	public void testGetTokens() {
		assertArrayEquals(HalsteadToken.ALL_TOKENS, testCheck.getAcceptableTokens());
		assertArrayEquals(new int[0], testCheck.getRequiredTokens());
		assertArrayEquals(HalsteadToken.ALL_TOKENS, testCheck.getDefaultTokens());
	}

	// Just verify function call. result can only be check in finishTree (without a
	// getter).
	@Test
	public void testVisitToken() {
		// Mock operator
		when(mockDetailAST.getType()).thenReturn(TokenTypes.PLUS);
		when(mockDetailAST.getText()).thenReturn("+");
		testCheck.visitToken(mockDetailAST);
		// Visit token
		verify(testCheck).visitToken(mockDetailAST);

		// Mock operator
		when(mockDetailAST.getType()).thenReturn(TokenTypes.IDENT);
		when(mockDetailAST.getText()).thenReturn("max");
		testCheck.visitToken(mockDetailAST);
		// Visit token
		verify(testCheck, times(2)).visitToken(mockDetailAST);
	}

	@Test
	public void testBeginTree() {
		verify(testCheck).beginTree(mockDetailAST);
	}

	@Test
	public void testFinishTree() {
		// Mock the behavior of DetailAST
		when(mockDetailAST.getLineNo()).thenReturn(1);

		// Add Operators
		when(mockDetailAST.getType()).thenReturn(TokenTypes.PLUS);
		when(mockDetailAST.getText()).thenReturn("+");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.PLUS);
		when(mockDetailAST.getText()).thenReturn("+");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.MINUS);
		when(mockDetailAST.getText()).thenReturn("-");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.MINUS);
		when(mockDetailAST.getText()).thenReturn("-");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.LPAREN);
		when(mockDetailAST.getText()).thenReturn("(");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.LPAREN);
		when(mockDetailAST.getText()).thenReturn("(");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.LITERAL_INT);
		when(mockDetailAST.getText()).thenReturn("int");
		testCheck.visitToken(mockDetailAST);

		// Add Operands
		when(mockDetailAST.getType()).thenReturn(TokenTypes.IDENT);
		when(mockDetailAST.getText()).thenReturn("sort");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.IDENT);
		when(mockDetailAST.getText()).thenReturn("sort");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.NUM_INT);
		when(mockDetailAST.getText()).thenReturn("5");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.IDENT);
		when(mockDetailAST.getText()).thenReturn("a");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.NUM_INT);
		when(mockDetailAST.getText()).thenReturn("a");
		testCheck.visitToken(mockDetailAST);
		when(mockDetailAST.getType()).thenReturn(TokenTypes.NUM_INT);
		when(mockDetailAST.getText()).thenReturn("5");
		testCheck.visitToken(mockDetailAST);

		// Calculate Difficulty
		// D = (n1 / 2) * (N2 / n2)
		int n1 = 4; // unique operators
		double n2 = 3; // unique operands
		double N2 = 6; // total operands
		double D = (n1 / 2.0) * (N2 / n2);
		// Calculate volume
		int N = 13;
		int n = 7;
		double volume = N * (Math.log(n) / Math.log(2));

		double E = D * volume;
		String formattedE = " " + String.format("%.2f", E);

		// Finish the tree and log the result
		doNothing().when(testCheck).log(anyInt(), anyString());
		testCheck.finishTree(mockDetailAST);

		// Verify log is called and argument passed in
		verify(testCheck).log(eq(1), contains(formattedE));
	}
}
