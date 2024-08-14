import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.util.HashSet;
import static java.lang.Character.isDigit;

public class SudokuGameController {
    private TextField[][] squares;
    private final int SIZE = 9;
    private Alert illegal;

    @FXML
    private GridPane gameBoard;

    public void initialize() {
        illegal = new Alert(Alert.AlertType.NONE);
        squares = new TextField[SIZE][SIZE];
        initializeBoard();
    }

    // This method clear the board game after the user press on the clear button.
    @FXML
    void pressedClear(ActionEvent event) {
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                squares[i][j].clear();
            }
        }
    }

    // This method disable all the full text fields and change their color after the user press set.
    @FXML
    void pressedSet(ActionEvent event) {
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!squares[i][j].getText().equals("")) {
                    squares[i][j].setEditable(false);
                    squares[i][j].setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                }
            }
        }
    }

    // This method initialize the board game according to the user choice.
    public void initializeBoard() {
        // Initialize every square with null input.
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                squares[i][j] = new TextField();
                squares[i][j].setPrefSize(40,40);
                squares[i][j].setBackground(null);
                gameBoard.add(squares[i][j], i , j ); // Add the square to the game board.
                int finalI = i, finalJ = j;

                // Check if the user press on the keyboard.
                squares[i][j].setOnKeyReleased(event -> {
                    handleTextBoxAction(event, finalI, finalJ);
                });
            }
        }
    }

    // This method check if the user press enter after he enters a number.
    // If it's true we will check if the number is legal, if the number illegal we pop up alert.
    public void handleTextBoxAction(KeyEvent event, int row, int column) {

        if (event.getCode() == KeyCode.ENTER) {
            // Find the square that the user enters the number.
            TextField lastEnter = (TextField) event.getSource();
            String strLastEnter = lastEnter.getText();

            // Check if the number is valid, valid according to the rules of sudoku game.
            if (!validNumber(strLastEnter) || !isValidSudoku(squares)) {
                illegal.setAlertType(Alert.AlertType.ERROR);
                illegal.setContentText("\"Illegal input, try again please\"");
                illegal.showAndWait();
                squares[row][column].clear();
            }

            // If the input is valid we center the number.
            else
                squares[row][column].setAlignment(Pos.CENTER);
        }
    }

    // This method check if the sudoku is valid according to the rules of sudoku game.
    public boolean isValidSudoku(TextField[][] game){

        // For simple calculations we build 2D char array from the game board.
        char[][] boardGameChar = buildArrayFromTextFields(game);

        // Check rows and columns.
        HashSet<Character> rowSet = new HashSet<>();
        HashSet<Character> colSet = new HashSet<>();
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {

                // Check if row[i] is valid.
                char rowChar = boardGameChar[i][j];
                if(rowSet.contains(rowChar)) return false;
                if(rowChar != '.') rowSet.add(rowChar);

                // Check col[j] is valid.
                char colChar = boardGameChar[j][i];
                if(colSet.contains(colChar)) return false;
                if(colChar != '.') colSet.add(colChar);
            }
            rowSet.clear();
            colSet.clear();
        }

        // Checking 3X3 squares.
        int minRow = 0;
        int maxRow = 2;
        int minCol = 0;
        int maxCol = 2;
        final int SQUARE_SIZE = 3;
        for(int i = 0; i < SQUARE_SIZE; i++) {
            for(int j = 0; j < SQUARE_SIZE; j++) {
                for(int row = minRow; row <= maxRow; row++) {
                    for(int col = minCol; col <= maxCol; col++) {
                        char currNum = boardGameChar[row][col];
                        if(rowSet.contains(currNum)) return false;
                        if(currNum != '.') rowSet.add(currNum);
                    }
                }
                rowSet.clear();
                minCol += 3;
                maxCol += 3;
            }
            minRow += 3;
            maxRow += 3;
            minCol = 0;
            maxCol = 2;
        }
        return true;
    }

    // This method build 2D char array for simple calculations.
    public char[][] buildArrayFromTextFields(TextField[][] game){
        char[][] board = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                String currNum = game[i][j].getText();

                // If the string is empty we add '.' to know at the validation check that this square is empty.
                if (currNum.equals(""))
                    board[i][j] = '.';
                else
                    board[i][j] = currNum.charAt(0);
            }
        }
        return board;
    }

    // This method check if the input that the user enters is legal sudoku number.
    public boolean validNumber(String num) {
        if(num.length() > 1 || (num.length() == 1 &&!isDigit(num.charAt(0))))
            return false;

        else if(num.length() == 0 || (num.length() == 1 && num.charAt(0) == '0'))
            return false;

        return true;
    }
}






