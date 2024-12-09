package TestFiles;

public class bChecksModels {

	public void main() {

		// ========================================
		// Fault Model 1: Number of Comments
		// ========================================

		// 1.1: Multiple “//” on a same line as multiple comments
		String commentExample1 = "// First comment // Second comment";

		// 1.2: Comment inside a string literal
		String stringWithComment = "This is a string // this is a comment";

		// 1.3: Not counting block comment
		/* This is a block comment */
		// Expected: 1 block comment (fault model should count the block comment
		// correctly)

		// 1.4: Miss comments that are behind a piece of code on a line
		int x = 10; // comment behind code

		// ========================================
		// Fault Model 2: Number of Lines of Comments
		// ========================================

		// 2.1: An empty line between comment blocks may be incorrectly counted as a
		// comment line 1

		/*
		 * Block comment 1
		 * 
		 */

		/*
		 * 
		 * Block comment 2
		 */

		// 2.2: Count multiple block comments in one line as multiple lines
		/* Comment 1 */ /* Comment 2 */ /* Comment 3 */

		// 2.3: Count multiple “//” on one line as multiple lines
		// line 1 // line 2 // line 3 // line 4 // 5 // 6 ...

		// ========================================
		// Fault Model 3: Number of Looping Statements
		// ========================================

		// 3.1: Miss counts nested loop
		for (int i = 0; i < 3; i++) { // Outer loop
			for (int j = 0; j < 3; j++) { // Inner loop
				// Inner loop code
			}
		}

		int m = 0;
		while (m > 0) {
			do {
				int b = 0;
				m--;
			} while (m < 0);
			m++;
		}

		// 3.2: Count keyword "in" in a comment or string literal
		String commentInString = "for (int i in arr)"; // Comment in string literal

		// ========================================
		// Fault Model 4: Number of Operators
		// ========================================

		// 4.1: Compound operators like +=, -=, ++, -- may be counted as individual
		// operators
		int a = 5;
		a += 3; // Compound operator

		// 4.2: Post-Increment and Pre-Increment Operators might be counted as operands
		int b = 10;
		b++; // Post-increment operator

		// 4.3: Count a pair of brackets as 2
		int[] array = new int[5]; // Array initialization with []

		// ========================================
		// Fault Model 5: Number of Operands
		// ========================================

		// 5.1: Constant values are not counted as operands
		int constant = 10;

		// 5.2: Miss count array indices (a[5], a[6])
		int[] arr = new int[10];
		arr[5] = 10; // Array index

		// 5.3: Miss count operands inside an expression
		int sum = a + b; // a and b are operands

		// ========================================
		// Fault Model 6: Number of Expressions
		// ========================================

		// 6.1: Count expression inside comment or string
		String commentExpression = "This is a string with (x + y)"; // Expression in a string literal

		// 6.2: Miss count conditional expression inside control flow statements (if,
		// for, while)
		int y = 5;
		if (x > 5 && y < 10) {
			// Do something
		}
	}
}
