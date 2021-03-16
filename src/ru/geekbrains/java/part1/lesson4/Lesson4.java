package ru.geekbrains.java.part1.lesson4;

import java.util.Random;
import java.util.Scanner;

/**
 * 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку.
 * <p>
 * 2. Переделать проверку победы, чтобы она не была реализована просто набором условий,
 * например, с использованием циклов.
 * <p>
 * 3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
 * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
 * <p>
 * 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */

public class Lesson4 {

    static char emptyCellSign = '-';
    static char player1Sign = 'X';
    static char player2Sign = 'O';

    static int fieldSize = 5;
    static int winSize = 4;

    public static void main(String[] args) {

        playGame();

    }

    private static void playGame() {

        char[][] field = createEmptyField();
        char currentPlayerSign = emptyCellSign;

        System.out.println("TIC TAK TOE\n");
        drawField(field);
        do {
            currentPlayerSign = getAnotherPlayerSign(currentPlayerSign);
            doNextMove(field, currentPlayerSign);
        } while (isNextTurnAvailable(field, currentPlayerSign));

    }

    private static char getAnotherPlayerSign(char currentPlayerSign) {
        if (currentPlayerSign == player1Sign)
            return player2Sign;
        return player1Sign;
    }

    private static void drawField(char[][] field) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(field[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void doNextMove(char[][] field, char currentPlayerSign) {

        System.out.printf("'%s' player turn!%n", currentPlayerSign);

        boolean isHumanPlayer = true;

        if (currentPlayerSign == player2Sign)
            isHumanPlayer = false;

        int[] coordinates;
        if (isHumanPlayer) {
            do {
                coordinates = getHumanPlayerCoordinates();
            } while (!isCellEmpty(field, coordinates, false));
        } else {
            coordinates = getAIPlayerCoordinates(field, currentPlayerSign);
        }

        fillCell(field, coordinates, currentPlayerSign);
        drawField(field);

    }

    private static int[] getAIPlayerCoordinates(char[][] field, char currentPlayerSign) {
        int[] coordinates = new int[2];
        boolean isSmartMove = findWinLoseCondition(field, coordinates, currentPlayerSign);
        if (!isSmartMove)
            coordinates = getRandomFreeCoordinates(field);
        return coordinates;
    }

    private static boolean findWinLoseCondition(char[][] field, int[] coordinates, char currentPlayerSign) {

        boolean isLosePossible = false;
        int[] loseCoordinates = new int[2];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                int[] checkedCoordinates = new int[2];
                checkedCoordinates[0] = i;
                checkedCoordinates[1] = j;
                boolean isWinPossibleThisMove = checkPotentialMoveWinCondition(field, checkedCoordinates, currentPlayerSign);
                if (isWinPossibleThisMove) {
                    coordinates[0] = checkedCoordinates[0];
                    coordinates[1] = checkedCoordinates[1];
                    return true;
                }
                boolean isLosePossibleThisMove = checkPotentialMoveWinCondition(field, checkedCoordinates, getAnotherPlayerSign(currentPlayerSign));
                if (isLosePossibleThisMove) {
                    isLosePossible = true;
                    loseCoordinates[0] = checkedCoordinates[0];
                    loseCoordinates[1] = checkedCoordinates[1];
                }
            }
        }
        if (isLosePossible) {
            coordinates[0] = loseCoordinates[0];
            coordinates[1] = loseCoordinates[1];
            return true;
        }
        return false;
    }

    private static boolean checkPotentialMoveWinCondition(char[][] field, int[] checkedCoordinates, char checkedSign) {
        boolean isWinPossible = false;
        if (isCellEmpty(field, checkedCoordinates, true)) {
            fillCell(field, checkedCoordinates, checkedSign);
            isWinPossible = checkWinCondition(field, checkedSign, true);
            fillCell(field, checkedCoordinates, emptyCellSign);
        }
        return isWinPossible;
    }

    private static int[] getRandomFreeCoordinates(char[][] field) {
        int[] coordinates = new int[2];
        Random random = new Random();
        do {
            coordinates[0] = random.nextInt(fieldSize);
            coordinates[1] = random.nextInt(fieldSize);
        } while (!isCellEmpty(field, coordinates, false));
        return coordinates;
    }

    private static void fillCell(char[][] field, int[] coordinates, char currentPlayerSign) {
        field[coordinates[0]][coordinates[1]] = currentPlayerSign;
    }

    private static int[] getHumanPlayerCoordinates() {
        int[] coordinates = new int[2];
        coordinates[0] = getCoordinate("horizontal");
        coordinates[1] = getCoordinate("vertical");
        System.out.println();
        return coordinates;
    }

    private static int getCoordinate(String coordinateLineName) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Please input %s line coordinate in range [1..%d]...", coordinateLineName, fieldSize);
            String userInput = scanner.next();
            try {
                int userNumber = Integer.parseInt(userInput);
                if (userNumber >= 1 && userNumber <= fieldSize) {
                    return userNumber - 1;
                }
            } catch (NumberFormatException ignored) {
            }
        }

    }

    private static boolean isCellEmpty(char[][] field, int[] coordinates, boolean silentMode) {
        boolean isCellEmpty = field[coordinates[0]][coordinates[1]] == emptyCellSign;
        if (!isCellEmpty && !silentMode) {
            System.out.println("Cell is not empty! Please, try again.\n");
        }
        return isCellEmpty;
    }

    private static boolean isNextTurnAvailable(char[][] field, char currentPlayerSign) {
        boolean isGameOver;
        isGameOver = checkWinCondition(field, currentPlayerSign, false);
        if (!isGameOver) {
            isGameOver = checkDrawCondition(field);
        }
        return !isGameOver;
    }

    private static boolean checkWinCondition(char[][] field, char currentPlayerSign, boolean silentMode) {

        for (int i = 0; i < fieldSize; i++) {
            int currentLengthI = 0;
            int currentLengthJ = 0;
            for (int j = 0; j < fieldSize; j++) {

                if (field[i][j] == currentPlayerSign)
                    currentLengthI++;
                else
                    currentLengthI = 0;

                if (field[j][i] == currentPlayerSign)
                    currentLengthJ++;
                else
                    currentLengthJ = 0;

                if (currentLengthI == winSize || currentLengthJ == winSize) {
                    if (!silentMode) System.out.printf("'%s' player win!%n%n", currentPlayerSign);
                    return true;

                }
            }
        }

        for (int displacement = 0; displacement < fieldSize - winSize + 1; displacement++) {
            int currentLengthMainDiagonalUp = 0;
            int currentLengthMainDiagonalDown = 0;
            int currentLengthSideDiagonalUp = 0;
            int currentLengthSideDiagonalDown = 0;
            for (int i = 0; (i < fieldSize) && (i + displacement < fieldSize); i++) {

                if (field[i][i + displacement] == currentPlayerSign)
                    currentLengthMainDiagonalUp++;
                else
                    currentLengthMainDiagonalUp = 0;

                if (field[i + displacement][i] == currentPlayerSign)
                    currentLengthMainDiagonalDown++;
                else
                    currentLengthMainDiagonalDown = 0;

                if (field[i][fieldSize - 1 - (i + displacement)] == currentPlayerSign)
                    currentLengthSideDiagonalUp++;
                else
                    currentLengthSideDiagonalUp = 0;

                if (field[i + displacement][fieldSize - i - 1] == currentPlayerSign)
                    currentLengthSideDiagonalDown++;
                else
                    currentLengthSideDiagonalDown = 0;

                if (currentLengthMainDiagonalUp == winSize || currentLengthMainDiagonalDown == winSize
                        || currentLengthSideDiagonalUp == winSize || currentLengthSideDiagonalDown == winSize) {
                    if (!silentMode) System.out.printf("'%s' player win!%n%n", currentPlayerSign);
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkDrawCondition(char[][] field) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == emptyCellSign) return false;
            }
        }
        System.out.println("Draw!");
        return true;
    }

    private static char[][] createEmptyField() {
        char[][] field = new char[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = emptyCellSign;
            }
        }
        return field;
    }
}
