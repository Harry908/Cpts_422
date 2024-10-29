package aChecksTests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import aChecks.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVocabularyTest {
	
	private HalsteadVocabularyCheck testCheck;
    private DetailAST mockDetailAST;

    @BeforeEach
    public void setUp() {
        testCheck = spy(new HalsteadVocabularyCheck());
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
        // Mock the behavior of DetailAST
        when(mockDetailAST.getType()).thenReturn(TokenTypes.PLUS);
        // Visit token
        testCheck.visitToken(mockDetailAST);
        
        // Recall on plus
        when(mockDetailAST.getType()).thenReturn(TokenTypes.PLUS);
        testCheck.visitToken(mockDetailAST);
        
        // Add another token
        when(mockDetailAST.getType()).thenReturn(TokenTypes.MINUS);
        testCheck.visitToken(mockDetailAST);


        // Verify if the token type was added to the set
        Set<Integer> visitedTokens = testCheck.getVisitedTokens();
        assertTrue(visitedTokens.contains(TokenTypes.PLUS));
        // Verify token count
        assertEquals(2,visitedTokens.size());
    }

    @Test
    public void testBeginTree()
    {
    	testCheck.beginTree(mockDetailAST);
    	assertEquals(0, testCheck.getVisitedTokens().size());
    }
    
    @Test
    public void testFinishTree() {
    	// Capture the arguments passed to the log method
        ArgumentCaptor<Integer> lineNum = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> logMsg = ArgumentCaptor.forClass(String.class);
        
        // Mock the behavior of DetailAST
        when(mockDetailAST.getLineNo()).thenReturn(1);

        // Add 1 token
        when(mockDetailAST.getType()).thenReturn(TokenTypes.MINUS);
        testCheck.visitToken(mockDetailAST); // Add multiple to ensure uniqueness

        // Finish the tree and log the result
        doNothing().when(testCheck).log(anyInt(), anyString());
        testCheck.finishTree(mockDetailAST);
        
        // Verify the log method was called
        //verify(testCheck).log(anyInt(), anyString());
        verify(testCheck).log(lineNum.capture(), logMsg.capture());

        // Assert the log message
        String expectedMessage = "Halstead Vocabulary: 1 -HK";
        assertEquals(1, (int) lineNum.getValue());
        assertEquals(expectedMessage, logMsg.getValue());
    }				

	
		
		
	

}
