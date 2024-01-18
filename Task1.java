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
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("NUMBER GUESSING GAME");
        System.out.println("Total Number Of Rounds : " + MAX_ROUNDS);
        System.out.println("Attempts To Guess Number In Each Round : " + MAX_ATTEMPTS);

        for (int round = 1; round <= MAX_ROUNDS; round++) {
            int targetNumber = random.nextInt(MAX_RANGE) + MIN_RANGE;
            int attempts = 0;

            System.out.printf("\nRound %d: Guess the number between %d and %d in %d attempts.\n",
                    round, MIN_RANGE, MAX_RANGE, MAX_ATTEMPTS);

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    int roundScore = MAX_ATTEMPTS - attempts + 1; // Higher score for fewer attempts
                    totalScore += roundScore;
                    System.out.printf("Hurray! Number Guessed Successfully. Attempts = %d. Round Score = %d\n",
                            attempts, roundScore);
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("The number is greater than " + userGuess + ". Attempts Left: " + (MAX_ATTEMPTS - attempts));
                } else {
                    System.out.println("The number is less than " + userGuess + ". Attempts Left: " + (MAX_ATTEMPTS - attempts));
                }
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
}
