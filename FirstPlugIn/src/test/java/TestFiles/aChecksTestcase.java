package TestFiles;

public class aChecksTestcase {
	public void main() {

		// Fault Model 1: HalsteadLengthCheck
		// 1a. Count pairing operators as two, such as (), {}, []
		int[] numbers = new int[5]; // Pairing operators []

		// 1b. Count operand/operator in a comment or a string literal
		// This is a comment with an operator: +
		String example = "This string contains an operator: +";

		// Fault Model 2: HalsteadVocabularyCheck
		// 2a. Miss-counting unique operands/operators (overcount or undercount)
		int a = 1;
		int b = 2;
		int c = a + b; // Operators: =, + | Operands: a, b, c

		// 2b. Counting compound operator such as “+=” as 2 separate operators
		int x = 5;
		x += 3; // Compound operator +=

		// Fault Model 3: HalsteadVolumeCheck
		// 3a. Incorrect program length or vocabulary
		int d = 1; // Length: 3 (int, d, 1) | Vocabulary: 3 (int, d, 1)
		int e = 2; // Length: 3 (int, e, 2) | Vocabulary: 3 (int, e, 2)
		int f = d + e; // Length: 5 (int, f, d, +, e) | Vocabulary: 5 (int, f, d, +, e)

		// Fault Model 4: HalsteadDifficultyCheck
		// 4a. Incorrect count of operators or operands
		int g = 1; // Operators: = | Operands: int, g, 1
		int h = 2; // Operators: = | Operands: int, h, 2
		int i = g + h; // Operators: =, + | Operands: int, i, g, h

		// Fault Model 5: HalsteadEffortCheck
		// 5a. Incorrect volume or difficulty
		int j = 1; // Operators: = | Operands: int, j, 1
		int k = 2; // Operators: = | Operands: int, k, 2
		int l = j - k; // Operators: =, + | Operands: int, l, j, k
	}
}
