package aChecks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HalsteadLengthTest {
	private HalsteadLengthCheck testCheck;
    private DetailAST mockDetailAST;

    @BeforeEach
    public void setUp() {
        testCheck = spy(new HalsteadLengthCheck());
        mockDetailAST = mock(DetailAST.class);
        // Initialize the tree
        testCheck.beginTree(mockDetailAST);
    }

    @Test
    public void testGetTokens()
    {

    	assertArrayEquals(HalsteadToken.ALL_TOKENS, testCheck.getAcceptableTokens());
    	assertArrayEquals(new int [0], testCheck.getRequiredTokens());
    	assertArrayEquals(HalsteadToken.ALL_TOKENS, testCheck.getDefaultTokens());
    }
    
    @Test
    public void testVisitToken() {
    	testCheck.visitToken(mockDetailAST);
    	// Visit token
        verify(testCheck).visitToken(mockDetailAST);
    }

    @Test
    public void testBeginTree()
    {
    	//testCheck.beginTree(mockDetailAST);
    	verify(testCheck).beginTree(mockDetailAST);
    }
    
    @Test
    public void testFinishTree() {
    	// Mock the behavior of DetailAST
        when(mockDetailAST.getLineNo()).thenReturn(4);
        
        // Add tokens
        testCheck.visitToken(mockDetailAST);
        testCheck.visitToken(mockDetailAST);
        testCheck.visitToken(mockDetailAST);
        testCheck.visitToken(mockDetailAST);
        testCheck.visitToken(mockDetailAST);
        
        // Finish the tree and log the result
        doNothing().when(testCheck).log(anyInt(),anyString());
        testCheck.finishTree(mockDetailAST);
        
        // Verify log is called and capture arguments passed in log
        verify(testCheck).log(eq(4), contains("5"));
    }
}
