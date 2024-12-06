package BlackBox;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import bChecks.CommentsCountCheck;

public class CommentsCountTest {

	private CommentsCountCheck testCheck = spy(new CommentsCountCheck());

	private CheckEngine walker = new CheckEngine(testCheck);

	@Test
	public void test1() {
		check("src/test/java/TestFiles/CS422Halstead.java");
		verify(testCheck).log(anyInt(), contains("2"));
	}

	public void check(String filePath) {
		try {
			walker.Check(filePath);
		} catch (IOException e) { // TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (CheckstyleException e) { // TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}