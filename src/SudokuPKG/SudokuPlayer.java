package SudokuPKG;

import java.util.Scanner;

public class SudokuPlayer {
    short mistakesCount = 0;
    SudokuPlayerMove move;
    // ------------------------------------------

    void setMove() {
        move = new SudokuPlayerMove(0, 0, 0);
        boolean checker = false;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your attempt separated by spaces (row, column, number): ");
            String tempString = in.nextLine();
            String numbers = "0123456789";
            if (tempString.length() != 5) {
                System.out.println(AnsiCodes.RED + "\nINVALID INPUT!\n" + AnsiCodes.RESET);
                continue;
            }
            for (String c : tempString.split(" ")) {
                if (!numbers.contains(c)) {
                    System.out.println(AnsiCodes.RED + "\nINVALID INPUT!\n" + AnsiCodes.RESET);
                    checker = false;
                    break;
                }
                checker = true;
            }
            if (checker) {
                move = new SudokuPlayerMove(Integer.valueOf(tempString.charAt(0)) - 48,
                        Integer.valueOf(tempString.charAt(2)) - 48,
                        Integer.valueOf(tempString.charAt(4)) - 48);
                break;
            }
        }
    }
    // ------------------------------------------
}
