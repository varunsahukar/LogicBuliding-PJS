import java.util.Random;
import java.util.Scanner;

public class wordScrambler {
    // List of predefined words categorized by difficulty
    private static final String[] EASY_WORDS = {"java", "king"};
    private static final String[] MEDIUM_WORDS = {"developer", "database"};
    private static final String[] HARD_WORDS = {"microservices", "kickboxing"};

    // words based on difficulty
    public static String getRandomWord(String[] words, int index) {
        return words[index];  // Pick word based on index
    }

    //  (Fisher-Yates algorithm)
    public static String scrambleWord(String word) {
        char[] letters = word.toCharArray();
        Random random = new Random();

        for (int i = letters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }

        return new String(letters);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;
        final int maxAttempts = 3;

        System.out.println("🎲 Welcome to the Word Scrambler Game!");
        System.out.println("Choose Difficulty: Easy | Medium | Hard");
        System.out.print("Enter difficulty: ");
        String difficulty = sc.next().toLowerCase();

        String[] selectedWords;
        switch (difficulty) {
            case "easy":
                selectedWords = EASY_WORDS;
                break;
            case "medium":
                selectedWords = MEDIUM_WORDS;
                break;
            case "hard":
                selectedWords = HARD_WORDS;
                break;
            default:
                System.out.println("Invalid choice! Defaulting to Easy.");
                selectedWords = EASY_WORDS;
        }

        System.out.println("\nTry to guess the original word. Type 'exit' to quit.");
        System.out.println("Need a hint? Type '@H' to reveal it.\n");

        for (int i = 0; i < 2; i++) { // Only 2 words per game session
            String originalWord = getRandomWord(selectedWords, i);
            String scrambledWord = scrambleWord(originalWord);
            String hint = originalWord.charAt(0) + "..." + originalWord.charAt(originalWord.length() - 1);
            int attemptsLeft = maxAttempts;

            System.out.println("\nScrambled Word: " + scrambledWord);

            while (attemptsLeft > 0) {
                System.out.print("Your guess (or '@H' for hint): ");
                String userGuess = sc.next().toLowerCase();

                if (userGuess.equalsIgnoreCase("exit")) {
                    System.out.println("\nThanks for playing! Your final score: " + score);
                    sc.close();
                    return;
                }

                if (userGuess.equalsIgnoreCase("@H")) {
                    System.out.println("🔍 Hint: " + hint);
                    continue;
                }

                if (userGuess.equals(originalWord)) {
                    System.out.println("🎉 Correct! You earned 1 point.");
                    score++;
                    break;
                } else {
                    attemptsLeft--;
                    if (attemptsLeft > 0) {
                        System.out.println(" Wrong! Attempts left: " + attemptsLeft);
                    } else {
                        System.out.println(" No attempts left! The correct word was: '" + originalWord + "'.");
                    }
                }
            }

            System.out.println("Current Score: " + score);
        }

        System.out.println("\nGame Over! Your final score: " + score);
        sc.close();
    }
}
