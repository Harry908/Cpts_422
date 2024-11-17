package aChecks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class HalsteadVolumeTest {
	private HalsteadVolumeCheck testCheck;
    private DetailAST mockDetailAST;

    @BeforeEach
    public void setUp() {
        testCheck = spy(new HalsteadVolumeCheck());
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
    	verify(testCheck).beginTree(mockDetailAST);
    }
    
    @Test
    public void testFinishTree() {        
        // Mock the behavior of DetailAST
        when(mockDetailAST.getLineNo()).thenReturn(1);

        // Add Operators
        when(mockDetailAST.getText()).thenReturn("+");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("+");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("-");
        testCheck.visitToken(mockDetailAST);
        
        // Add Operands
        when(mockDetailAST.getText()).thenReturn("sort");
        testCheck.visitToken(mockDetailAST);
        when(mockDetailAST.getText()).thenReturn("b");
        testCheck.visitToken(mockDetailAST);
        
        // Calculate volume
        int N = 5;
        int n = 4;
        double volume = N * (Math.log(n) / Math.log(2));
		String formattedVolume = String.format("%.2f", volume);
        
        // Finish the tree and log the result
        doNothing().when(testCheck).log(anyInt(), anyString());
        testCheck.finishTree(mockDetailAST);
        
        // Verify log is called and argument passed in
        verify(testCheck).log(eq(1),contains(formattedVolume));
    }
}
