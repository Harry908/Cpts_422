package BlackBox;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import bChecks.CommentsCountCheck;
import bChecks.ExpressionCountCheck;
import bChecks.LineOfCommentCountCheck;
import bChecks.LoopStatementCountCheck;
import bChecks.OperandsCountCheck;
import bChecks.OperatorsCountCheck;

public class bCheckTests {
	private CheckEngine walker;
	private String relativePath = "src/test/java/TestFiles/";

	@Test
	public void testCommentCounts() {

		CommentsCountCheck testCheck = spy(new CommentsCountCheck());
		walker = new CheckEngine(testCheck);

		check("bChecksModels");

		verify(testCheck).log(anyInt(), contains(" 59"));
	}

	@Test
	public void testLineOfCommentCount() {

		LineOfCommentCountCheck testCheck = spy(new LineOfCommentCountCheck());
		walker = new CheckEngine(testCheck);

		check("bChecksModels");

		verify(testCheck).log(anyInt(), contains(" 65"));
	}

	@Test
	public void testLoopStatementCount() {

		LoopStatementCountCheck testCheck = spy(new LoopStatementCountCheck());
		walker = new CheckEngine(testCheck);

		check("bChecksModels");

		verify(testCheck).log(anyInt(), contains(" 4"));
	}

	@Test
	public void testExpressionCount() {

		ExpressionCountCheck testCheck = spy(new ExpressionCountCheck());
		walker = new CheckEngine(testCheck);

		check("bChecksModels");

		verify(testCheck).log(anyInt(), contains(" 31"));
	}

	@Test
	public void testOperandsCount() {

		OperandsCountCheck testCheck = spy(new OperandsCountCheck());
		walker = new CheckEngine(testCheck);

		check("bChecksModels");

		verify(testCheck).log(anyInt(), contains(" 62"));
	}

	@Test
	public void testOperatorsCount() {

		OperatorsCountCheck testCheck = spy(new OperatorsCountCheck());
		walker = new CheckEngine(testCheck);

		check("bChecksModels");

		verify(testCheck).log(anyInt(), contains(" 89"));
	}

	private void check(String fileName) {
		try {
			walker.Check(relativePath + fileName + ".java");
		} catch (IOException e) { // TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CheckstyleException e) { // TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
