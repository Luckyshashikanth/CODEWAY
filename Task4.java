import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOption;

    public QuizQuestion(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private static final int QUESTION_TIME_LIMIT = 10; // in seconds
    private static int score = 0;

    public static void main(String[] args) {
        // Sample questions
        QuizQuestion[] questions = {
                new QuizQuestion("What is the capital of France?", new String[]{"A. Berlin", "B. Paris", "C. Madrid", "D. Rome"}, 1),
                new QuizQuestion("Which planet is known as the Red Planet?", new String[]{"A. Mars", "B. Venus", "C. Jupiter", "D. Saturn"}, 0),
                new QuizQuestion("Who wrote Romeo and Juliet?", new String[]{"A. Charles Dickens", "B. William Shakespeare", "C. Jane Austen", "D. Mark Twain"}, 1)
        };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz!");
        System.out.println("You have " + QUESTION_TIME_LIMIT + " seconds to answer each question.");

        for (QuizQuestion question : questions) {
            displayQuestion(question);
            waitForAnswer(question);
        }

        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is: " + score + " out of " + questions.length);

        scanner.close();
    }

    private static void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
    }

    private static void waitForAnswer(QuizQuestion question) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! You didn't submit an answer for the current question.");
                System.exit(0);
            }
        }, QUESTION_TIME_LIMIT * 1000);

        System.out.print("\nEnter your answer (A, B, C, or D): ");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine().toUpperCase();

        timer.cancel(); // Cancel the timer as the user has submitted an answer

        if (userAnswer.equals("A") || userAnswer.equals("B") || userAnswer.equals("C") || userAnswer.equals("D")) {
            checkAnswer(question, userAnswer);
        } else {
            System.out.println("Invalid input. Please enter A, B, C, or D.");
        }
    }

    private static void checkAnswer(QuizQuestion question, String userAnswer) {
        int userOption = userAnswer.charAt(0) - 'A';

        if (userOption == question.getCorrectOption()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer was: " + (char) ('A' + question.getCorrectOption()));
        }
    }
}
