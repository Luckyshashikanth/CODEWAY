import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_ROUNDS = 3;

    public static void main(String[] args) {
        playNumberGuessingGame();
    }

    private static void playNumberGuessingGame() {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("NUMBER GUESSING GAME");

        for (int round = 1; round <= MAX_ROUNDS; round++) {
            int targetNumber = generateRandomNumber(MIN_RANGE, MAX_RANGE);
            int attempts = 0;

            System.out.printf("\nRound %d: Guess the number between %d and %d in %d attempts.\n",
                    round, MIN_RANGE, MAX_RANGE, MAX_ATTEMPTS);

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int userGuess = getUserInput(scanner);

                if (userGuess == targetNumber) {
                    int roundScore = MAX_ATTEMPTS - attempts + 1; // Higher score for fewer attempts
                    totalScore += roundScore;
                    System.out.printf("Hurray! Number Guessed Successfully. Attempts = %d. Round Score = %d\n",
                            attempts + 1, roundScore);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("The number is greater than " + userGuess + ". Attempts Left: " + (MAX_ATTEMPTS - attempts - 1));
                } else {
                    System.out.println("The number is less than " + userGuess + ". Attempts Left: " + (MAX_ATTEMPTS - attempts - 1));
                }

                attempts++;
            }

            if (attempts == MAX_ATTEMPTS) {
                System.out.printf("\nRound %d: No more attempts left. The correct number was: %d\n", round, targetNumber);
            }
        }

        System.out.printf("\nGame Over. Total Score = %d\n", totalScore);

        // Ask the user if they want to play again
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();

        if (playAgain.equals("yes")) {
            playNumberGuessingGame(); // Recursively start a new game
        } else {
            System.out.println("Thanks for playing! Goodbye.");
        }

        scanner.close();
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private static int getUserInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine(); // Consume the invalid input
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.print("Enter your guess: ");
            }
        }
    }
}
