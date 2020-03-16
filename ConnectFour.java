import java.util.Arrays;
import java.util.Scanner;
public class ConnectFour {
    public static void main(String args[]) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("What would you like the height of the board to be? ");
        int height = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        int length = scnr.nextInt();
        System.out.println("");
        //sets up array with designated and length
        char[][] array = new char[height][length];
        initializeBoard(array);
        printBoard(array);
        System.out.println("Player 1: x\nPlayer 2: o");
        boolean win = false;
        boolean tie = false;
        //beginning of loop for actual gameplay
        while (win == false) {
            System.out.print("\nPlayer 1: Which column would you like to choose? ");
            char chipType = 'x';
            int col = scnr.nextInt();
            int row = (array.length-insertChip(array,col,chipType));
            printBoard(array);
            win = checkIfWinner(array,col,row,chipType);
            if (win==true) {
                System.out.println("Player 1 won the game!");
                break; }
            //checks tie conditions
            for (int i=0; i < array[0].length; i++) {
                tie = true;
                if (array[array.length-1][i] == '-') {
                    tie = false;
                    break;
                }
            }
            if (tie==true) {
                System.out.print("Draw. Nobody wins.");
                break;
            }
            //repeats first part of the while loop for player 2
            System.out.print("\nPlayer 2: Which column would you like to choose? ");
            chipType = 'o';
            col = scnr.nextInt();
            row = (array.length-insertChip(array,col,chipType));
            printBoard(array);
            win = checkIfWinner(array,col,row,chipType);
            if (win==true)
                System.out.println("Player 2 won the game!");
            for (int i=0; i < array[0].length; i++) {
                    tie = true;
                    if (array[array.length-1][i] == '-') {
                        tie = false;
                        break; }

            }
            if (tie==true) {
                System.out.print("Draw. Nobody wins.");
                break;
            }
        }


    }
//prints board with the rows in reverse order so row 0 is on the bottom
    public static void printBoard(char[][] array) {
        for (int i = array.length-1; i >= 0; i--) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
                System.out.println();
            }
        }

//fills all entries with the "-" character
    public static void initializeBoard(char[][] array) {
        for (char[] row : array) {
            Arrays.fill(row, '-');
        }

    }
//checks for blank entries in reverse order to account for row 0 being the bottom row
    public static int insertChip(char[][] array, int col, char chipType) {
        int row = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                row = array.length-i;
                break;
            }
        }
        return row;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int coltally = 0;
        int rowtally = 0;
        //checks across the row for a chain of the same chip type
        for (int i = 0; i < array[0].length; i++) {
            if (array[row][i] == chipType) {
                rowtally += 1;
            if (rowtally>=4)
                break; }
            else
                rowtally = 0;
        }
        //checks down columns for a chain of the same chip type
        for (int j = 0;j<array.length; j++) {
            if (array[j][col] == chipType) {
                coltally +=1;
            if (coltally>=4)
                break; }
            else
                coltally = 0;
        }
        if (rowtally >= 4 || coltally>=4)
            return true;
        else
            return false;
    }
}