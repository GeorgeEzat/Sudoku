package SudokuPKG;

import java.util.Scanner;

public class SudokuMain {
    private SudokuPlayer PLAYER;
    private SudokuBoard BOARD;
    private enMenu menuChoice;
    private enDifficulty difficultyChoice;
    // ------------------------------------------

    public SudokuMain() {
        close();
        boolean CONTINUE = true;
        while (CONTINUE) {
            mainMenu();
            setMenuChoice();
            switch (menuChoice) {
                case PLAY:
                    close();
                    setDifficultyChoice();
                    gameRun();
                    break;
                case INSTRUCTIONS:
                    close();
                    instructions();
                    break;
                case EXIT:
                    CONTINUE = false;
                    break;
                default:
                    break;
            }
        }
    }
    // ------------------------------------------

    void mainMenu() {
        System.out.println(AnsiCodes.BOLD + "Sudoku Game" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_GREEN + "1. Play New Game " + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_MAGENTA + "2. Instructions " + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_RED + "0. Exit " + AnsiCodes.RESET);
    }
    // ------------------------------------------

    void difficultyMenu() {
        System.out.println(AnsiCodes.BOLD + "Available Difficulty Levels:" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_GREEN + "1. Easy" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_YELLOW + "2. Medium" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_CYAN + "3. Hard" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_MAGENTA + "4. Expert" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_RED + "5. Master" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.BRIGHT_BLUE + "6. Extreme" + AnsiCodes.RESET);
        System.out.println(AnsiCodes.DIM + "0. Exit" + AnsiCodes.RESET);
    }
    // ------------------------------------------

    void instructions() {
        System.out.println(AnsiCodes.BOLD + "Sudoku Game Instructions" + AnsiCodes.RESET);
        System.out.println();

        System.out.println(AnsiCodes.BRIGHT_YELLOW + "1. Objective:" + AnsiCodes.RESET);
        System.out.println("   Fill the 9x9 grid so that each row, column, and 3x3 subgrid");
        System.out.println("   contains all digits from 1 to 9 without repeating any numbers.");
        System.out.println();

        System.out.println(AnsiCodes.BRIGHT_YELLOW + "2. How to Play:" + AnsiCodes.RESET);
        System.out.println("   - The grid starts with some numbers filled in (clues).");
        System.out.println("   - Use logic to fill in the empty cells.");
        System.out.println("   - Each number (1-9) can only appear once in each row,");
        System.out.println("     column, and 3x3 subgrid.");
        System.out.println();

        System.out.println(AnsiCodes.BRIGHT_YELLOW + "3. Rules:" + AnsiCodes.RESET);
        System.out.println("   - You cannot change the pre-filled numbers (clues).");
        System.out.println("   - The puzzle is solved when all cells are correctly filled.");
        System.out.println();

        System.out.println(AnsiCodes.BRIGHT_YELLOW + "4. Tips:" + AnsiCodes.RESET);
        System.out.println("   - Start with rows, columns, or subgrids that have the most clues.");
        System.out.println("   - Look for numbers that can only fit in one cell.");
        System.out.println();

        System.out.println(AnsiCodes.BRIGHT_YELLOW + "5. Winning:" + AnsiCodes.RESET);
        System.out.println("   - The game is won when the entire grid is filled correctly.");
        home();
    }
    // ------------------------------------------

    void setMenuChoice() {
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            String choice = new String();
            choice = in.nextLine();
            boolean checker = false;
            switch (choice) {
                case "1":
                    menuChoice = enMenu.PLAY;
                    checker = true;
                    break;
                case "2":
                    menuChoice = enMenu.INSTRUCTIONS;
                    checker = true;
                    break;
                case "0":
                    menuChoice = enMenu.EXIT;
                    checker = true;
                    break;
                default:
                    System.out.println(AnsiCodes.RED + "\nINVALID CHOICE!\n" + AnsiCodes.RESET);
                    break;
            }
            if (checker)
                return;
        }
    }
    // ------------------------------------------

    void setDifficultyChoice() {
        difficultyMenu();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your choice: ");
            String choice = in.nextLine();
            boolean checker = false;
            switch (choice) {
                case "1":
                    difficultyChoice = enDifficulty.EASY;
                    checker = true;
                    break;
                case "2":
                    difficultyChoice = enDifficulty.MEDIUM;
                    checker = true;
                    break;
                case "3":
                    difficultyChoice = enDifficulty.HARD;
                    checker = true;
                    break;
                case "4":
                    difficultyChoice = enDifficulty.EXPERT;
                    checker = true;
                    break;
                case "5":
                    difficultyChoice = enDifficulty.MASTER;
                    checker = true;
                    break;
                case "6":
                    difficultyChoice = enDifficulty.EXTREME;
                    checker = true;
                    break;
                case "0":
                    checker = true;
                    break;
                default:
                    System.out.println(AnsiCodes.RED + "\nINVALID CHOICE!\n" + AnsiCodes.RESET);
                    break;
            }
            if (checker)
                return;
        }
    }
    // ------------------------------------------

    void gameRun() {
        close();
        if (difficultyChoice == null)
            return;
        BOARD = new SudokuBoard(difficultyChoice);
        PLAYER = new SudokuPlayer();
        while (!BOARD.gameEnd()) {
            BOARD.viewBoard();
            PLAYER.setMove();
            BOARD.updateBoard(PLAYER);
        }
        results();
    }
    // ------------------------------------------

    void results() {
        System.out.println();
        if (PLAYER.mistakesCount > 0) {
            System.out.println(AnsiCodes.GREEN + "You have won!" + AnsiCodes.RESET);
            System.out.println(AnsiCodes.BRIGHT_YELLOW + "You only have " + PLAYER.mistakesCount + " mistake(s)."
                    + AnsiCodes.RESET);
            System.out.println();
        } else {
            System.out.println(AnsiCodes.GREEN + "You have won!" + AnsiCodes.RESET);
            System.out.println(AnsiCodes.BRIGHT_MAGENTA + "Wonderful you have no mistakes at all!" + AnsiCodes.RESET);
            System.out.println();
        }
        home();
    }
    // ------------------------------------------

    void home() {
        System.out.println();
        System.out.print("Press enter to return to home...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        close();
    }
    // ------------------------------------------

    void close() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // ------------------------------------------
}
