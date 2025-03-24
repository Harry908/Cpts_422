# Cpts422-Project

## CheckStyle Plug-In

An Eclipse plug-in designed to detect structural software metrics for Java code, using static analysis techniques.

---

### ✅ Implemented Features

#### 🔹 **Category A* – Halstead Metrics**

- **Halstead Length**: Total number of operators and operands.
- **Halstead Vocabulary**: Count of unique operators and unique operands.
- **Halstead Volume**: Represents the size of the implementation.  
  - Formula: `Volume = N × log₂(n)`
- **Halstead Difficulty**: Measures the difficulty of understanding the program.  
  - Formula: `Difficulty = (n₁ / 2) × (N₂ / n₂)`
- **Halstead Effort**: Represents the effort required to implement or review the code.  
  - Formula: `Effort = Difficulty × Volume`

> **Note:**  
> - **Operators**: Include arithmetic, relational, logical, bitwise operators, keywords, and function calls.  
> - **Operands**: Include variables, constants, literals, and identifiers.  
> - Java-specific considerations: class names, method calls, and packages may be counted as operands.  
> - Refer to: [GeeksforGeeks: Halstead's Software Metrics](https://www.geeksforgeeks.org/software-engineering-halsteads-software-metrics/)

---

#### 🔹 **Category B* – Code Structure Metrics**

- **Comments Count**
- **Number of Lines of Comments**
- **Number of Looping Statements**
- **Number of Operators**
- **Number of Operands**
- **Number of Expressions**

> **Note:** Supports both single-line `//` and multi-line `/* */` comment formats.

---

### 🧪 Testing

- **JUnit Testing**  
  Unit tests are written using **JUnit** to verify metric calculations and structural checks.  
  Tests cover both individual components and integrated plugin functionality.

- **White Box Testing**  
  - Achieves **100% branch coverage**  
  - Detailed results in `Del2/TestReport.pdf`

- **Black Box Testing**  
  - Validated against predefined fault models  
  - Details in `Del3/report.pdf`

- **Mutation Testing (Pit-clipse)**  
  - Achieves **100% mutation coverage**  
  - See `Del3/report.pdf` for results

---
