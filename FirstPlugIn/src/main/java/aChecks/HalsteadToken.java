package aChecks;
import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public final class HalsteadToken {
	public static final int[] OPERATORS = {
	        //Arithmetic
			TokenTypes.MINUS,TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, TokenTypes.MOD_ASSIGN, 
			TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC,
			TokenTypes.ASSIGN,TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.INC,
			TokenTypes.STAR, TokenTypes.STAR_ASSIGN, 
			
			// Bitwise
			TokenTypes.BAND, TokenTypes.BAND_ASSIGN,
			TokenTypes.BNOT, TokenTypes.BOR_ASSIGN, 
			TokenTypes.BOR, TokenTypes.BSR, TokenTypes.BSR_ASSIGN,
	        TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN,
			
			// Conditions
	        TokenTypes.GE, TokenTypes.GT,TokenTypes.NOT_EQUAL,
	        TokenTypes.EQUAL,TokenTypes.LAND,TokenTypes.LOR,
	        TokenTypes.LE,TokenTypes.LT,TokenTypes.LNOT,	        
	        
	        //Punctuation
	        TokenTypes.COMMA,TokenTypes.DOT,TokenTypes.LCURLY,
	        TokenTypes.LPAREN,TokenTypes.COLON,TokenTypes.QUESTION,
	        TokenTypes.INDEX_OP, TokenTypes.SEMI,TokenTypes.LAMBDA,
	        
	        //Keywords
			TokenTypes.DO_WHILE,TokenTypes.LITERAL_DO,
			TokenTypes.LITERAL_IF,TokenTypes.LITERAL_ELSE,
			TokenTypes.FOR_EACH_CLAUSE,TokenTypes.LITERAL_FOR,
			TokenTypes.LITERAL_WHILE,TokenTypes.LITERAL_CONTINUE,
			TokenTypes.LITERAL_SWITCH, TokenTypes.CASE_GROUP, 
			TokenTypes.LITERAL_CASE,TokenTypes.LITERAL_BREAK, TokenTypes.LITERAL_DEFAULT,
	        TokenTypes.LITERAL_FINALLY, 
	        TokenTypes.LITERAL_RETURN,  
	        TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
	        TokenTypes.LITERAL_TRY,TokenTypes.LITERAL_CATCH, 
	        TokenTypes.CTOR_CALL, TokenTypes.METHOD_CALL,
	        TokenTypes.LITERAL_ASSERT,  
	        TokenTypes.TYPECAST, TokenTypes.LITERAL_INT, TokenTypes.LITERAL_BOOLEAN,
	        TokenTypes.LITERAL_LONG, TokenTypes.LITERAL_DOUBLE,
	        TokenTypes.LITERAL_BYTE,TokenTypes.LITERAL_FLOAT,TokenTypes.LITERAL_CHAR,
	    };

	    public static final int[] OPERANDS = {
	        TokenTypes.LITERAL_THIS, TokenTypes.STRING_LITERAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_INT,
	        TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, TokenTypes.CHAR_LITERAL, 
	        TokenTypes.LITERAL_NULL, TokenTypes.LITERAL_FALSE, TokenTypes.LITERAL_TRUE, 
	        TokenTypes.IDENT
	    };
	    
	    public static boolean isOperand(int token)
	    {
	    	for (int i : OPERANDS)
    		{
    			if(i == token)
    			{
    				return true;
    			}
    		}
    		return false;
	    }

	    public static final int[] ALL_TOKENS;

	    static {
	        ALL_TOKENS = Arrays.copyOf(OPERATORS, OPERATORS.length + OPERANDS.length);
	        System.arraycopy(OPERANDS, 0, ALL_TOKENS, OPERATORS.length, OPERANDS.length);
	    }
	    
	    
}
