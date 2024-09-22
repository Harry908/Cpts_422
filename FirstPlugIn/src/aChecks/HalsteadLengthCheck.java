package aChecks;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {

	 private int count = 0;

	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] {
	    		// Operators
	    		TokenTypes.DO_WHILE, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, 
	    		TokenTypes.BNOT, TokenTypes.BOR_ASSIGN, TokenTypes.BOR, TokenTypes.BSR, TokenTypes.BSR_ASSIGN, 
	    		TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.CASE_GROUP, TokenTypes.COLON, TokenTypes.COMMA,
	    		TokenTypes.CTOR_CALL, TokenTypes.DEC, TokenTypes.DIV, TokenTypes.DIV_ASSIGN, TokenTypes.DOT, 
	    		TokenTypes.DOUBLE_COLON, TokenTypes.EQUAL, TokenTypes.FOR_EACH_CLAUSE, TokenTypes.GE, TokenTypes.GT,
	    		TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAMBDA, TokenTypes.LAND, TokenTypes.LCURLY,
	    		TokenTypes.LE, TokenTypes.LITERAL_ASSERT, TokenTypes. LITERAL_BREAK, TokenTypes.LITERAL_CASE, 
	    		TokenTypes.LITERAL_CATCH, TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_DO, 
	    		TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_FINALLY, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF, 
	    		TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_THROW, TokenTypes.LITERAL_THROWS,
	    		TokenTypes.LITERAL_TRY, TokenTypes.LITERAL_WHILE, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LPAREN, 
	    		TokenTypes.LT, TokenTypes.METHOD_CALL, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN, TokenTypes.MOD, 
	    		TokenTypes.MOD_ASSIGN, TokenTypes.NOT_EQUAL, 
	    		TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION, 
	    		TokenTypes.RCURLY, TokenTypes.SEMI,TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, 
	    		TokenTypes.SR_ASSIGN, TokenTypes.TYPECAST,
	    		// Operands
	    		TokenTypes.LITERAL_THIS, TokenTypes.STRING_LITERAL, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_INT, 
	    		TokenTypes.NUM_FLOAT, TokenTypes.NUM_LONG, TokenTypes.IDENT, TokenTypes.CHAR_LITERAL
	    };
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
	  public void beginTree(DetailAST ast)
	  {
		  count = 0;
	  }
	 
	  @Override
	  public void finishTree(DetailAST ast)
	  {  
		  log(ast.getLineNo(), "Halstead Length: "+ count + " -HK");
	  }

}
