# Cpts422-Project

## CheckStyle Plug-In
An Eclipse plug-in to detect structural metrics.
### Implemented features
#### Category A Checks

- **Halstead Length**: The sum of the total number of operators and operands*.

  ---
  ***Note**: Halstead metrics assumptions
  - **Operators**: arithmetic, relational, logical, and bitwise operators, as well as keywords and function calls.
  - **Operands**: variables, constants, and literals.

#### Category B Checks
- **Comments Count**: Total number of comments*.

  ---
   ***Note**: Content of single line comment `//<content>` and block comment `/*<content>*/`
