package aChecks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

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
    	when(mockDetailAST.getText()).thenReturn("+");
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
    	// Capture the arguments passed to the log method
        ArgumentCaptor<Integer> lineNum = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> logMsg = ArgumentCaptor.forClass(String.class);
        
        // Mock the behavior of DetailAST
        when(mockDetailAST.getLineNo()).thenReturn(1);

        // Add tokens
        when(mockDetailAST.getText()).thenReturn("+");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("+");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("-");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("sort");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("a");
        testCheck.visitToken(mockDetailAST);
        
        // Finish the tree and log the result
        doNothing().when(testCheck).log(anyInt(), anyString());
        testCheck.finishTree(mockDetailAST);
        
        // Verify log is called and capture arguments passed in log
        verify(testCheck).log(lineNum.capture(), logMsg.capture());

        // Assert the log message
        String expectedMessage = "Halstead Vocabulary: 4 -HK";
        assertEquals(1, (int) lineNum.getValue());
        assertEquals(expectedMessage, logMsg.getValue());
    }				
}
