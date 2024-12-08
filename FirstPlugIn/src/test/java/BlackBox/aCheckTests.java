package BlackBox;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;

import aChecks.HalsteadDifficultyCheck;
import aChecks.HalsteadEffortCheck;
import aChecks.HalsteadLengthCheck;
import aChecks.HalsteadVocabularyCheck;
import aChecks.HalsteadVolumeCheck;

public class aCheckTests {

	private CheckEngine walker;
	private String relativePath = "src/test/java/TestFiles/";

	@Test
	public void testHalsteadLength() {

		HalsteadLengthCheck testCheck = spy(new HalsteadLengthCheck());
		walker = new CheckEngine(testCheck);

		check("aChecksTestcase");

		verify(testCheck).log(anyInt(), contains(" 97"));
	}

	@Test
	public void testHalsteadVocabulary() {

		HalsteadVocabularyCheck testCheck = spy(new HalsteadVocabularyCheck());
		walker = new CheckEngine(testCheck);

		check("aChecksTestcase");

		verify(testCheck).log(anyInt(), contains(" 34"));
	}

	@Test
	public void testHalsteadVolume() {

		HalsteadVolumeCheck testCheck = spy(new HalsteadVolumeCheck());
		walker = new CheckEngine(testCheck);

		check("aChecksTestcase");

		verify(testCheck).log(anyInt(), contains(" 493.48"));
	}

	@Test
	public void testHalsteadDifficulty() {

		HalsteadDifficultyCheck testCheck = spy(new HalsteadDifficultyCheck());
		walker = new CheckEngine(testCheck);

		check("aChecksTestcase");

		verify(testCheck).log(anyInt(), contains(" 8.33"));
	}

	@Test
	public void testHalsteadEffort() {

		HalsteadEffortCheck testCheck = spy(new HalsteadEffortCheck());
		walker = new CheckEngine(testCheck);

		check("aChecksTestcase");

		verify(testCheck).log(anyInt(), contains(" 4112.37"));
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
