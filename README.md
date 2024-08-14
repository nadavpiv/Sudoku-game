# Sudoku Game

## Project Overview

This project involves implementing the classic Sudoku game. The game is played on a 9x9 grid, divided into nine 3x3 subgrids called blocks. At the start of the game, some cells are filled with numbers between 1 and 9. The objective is to fill the entire grid with numbers from 1 to 9 such that each row, column, and block contains all digits from 1 to 9 without repetition.

## Game Implementation

### Features

1. **Board Initialization**:
   - The application presents an empty Sudoku board at the start.
   - The user can input numbers into the empty cells based on a Sudoku puzzle they wish to solve.
   - After entering a number and pressing Enter, the program checks if the number is valid (i.e., it does not violate Sudoku rules for rows, columns, or blocks). If the number is invalid, an error message is displayed, and the number is removed.

2. **Set Board**:
   - After the board is set up, the user clicks the "Set" button.
   - Once set, the initial numbers on the board are locked, and the user cannot change them.
   - The text color for the locked numbers changes to indicate they are fixed.

3. **Puzzle Solving**:
   - The user can now start solving the puzzle by entering numbers into the remaining empty cells.
   - After entering a number and pressing Enter, the program checks its validity. If invalid, an error message is shown, and the number is erased.
   - The user can change the numbers they enter during this phase but cannot modify the initially set numbers.

4. **Clear Board**:
   - Pressing the "Clear" button will reset the entire board, allowing the user to start over with a new puzzle.
