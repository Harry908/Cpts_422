# Cpts422-Project

## CheckStyle Plug-In
An Eclipse plug-in to detect structural metrics.

### Implemented Features

#### Category A* Checks

- **Halstead Length**: The sum of the total number of operators and operands.
- **Halstead Vocabulary**: The sum of unique operators and unique operands.
- **Halstead Volume**: The program length (N) times the log2 of the program vocabulary (n).
  - Volume = N log2 n
- **Halstead Difficulty**: Half of the unique operators multiplied by the total number of operands, divided by the number of unique operands.
- **Halstead Effort**: The difficulty multiplied by the volume.
  - Effort = D * V
  - Effort was intended to suggest how long code review might take.
----
  ***Note**: Halstead metrics assumptions
  - **Operators**: arithmetic, relational, logical, and bitwise operators, as well as keywords and function calls.
  - **Operands**: variables, constants, and literals.

  - Please refer to [GeeksforGeeks: Halstead's Software Metrics](https://www.geeksforgeeks.org/software-engineering-halsteads-software-metrics/). While the link uses C language as an example, some extra Java concepts such as class names and packages are counted as operands. 

#### Category B* Checks
- **Comments Count**: Total number of comments.
- **Number of Comments**: Total number of comments
- **Number of Lines of Comments**: Total number of lines within comments.
- **Number of Looping Statements**: Total number of looping statements.
- **Number of Operators**: Total number of operators.
- **Number of Operands**: Total number of operands.
- **Number of Expressions**: Total number of expressions.
---
  ***Note:** Content of single line comment `//<content>` and block comment `/*<content>*/`

### Tests
+ WhiteBox: 100% branch coverage (details in Del2/TestReport.pdf)
+ BlackBox: test with fault models specify in Del3/report.pdf
+ Pit-clipse mutation test: 100% mutation coverage (details in Del3/report.pdf)
