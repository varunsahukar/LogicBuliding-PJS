package com.numberguessinggame;

import java.io.*;
import java.util.*;

public class numberGG{
    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;
        int highScore = loadHighScore(); // Load previous high score from file

        while (playAgain) {
            System.out.println("\n🎮 Welcome to Multiplayer Number Guessing Game!");
            System.out.print("Enter Player 1 Name: ");
            String player1 = scanner.nextLine();
            System.out.print("Enter Player 2 Name: ");
            String player2 = scanner.nextLine();

            System.out.println("\nChoose Difficulty Level:");
            System.out.println("1. Easy (1-50) - 10 attempts");
            System.out.println("2. Medium (1-100) - 7 attempts");
            System.out.println("3. Hard (1-500) - 5 attempts");
            System.out.print("Enter your choice (1/2/3): ");

            int choice = scanner.nextInt();
            int maxRange = 100, maxAttempts = 7;

            if (choice == 1) { maxRange = 50; maxAttempts = 10; }
            else if (choice == 2) { maxRange = 100; maxAttempts = 7; }
            else if (choice == 3) { maxRange = 500; maxAttempts = 5; }
            else { System.out.println("Invalid choice! Defaulting to Medium (1-100)."); }

            int numberToGuess = random.nextInt(maxRange) + 1;
            int attempts = 0;
            int currentPlayerIndex = 0;
            String[] players = {player1, player2};

            System.out.println("\n🔢 A number has been chosen between 1 and " + maxRange + ". Players take turns guessing!");
            scanner.nextLine(); // Consume newline

            while (attempts < maxAttempts) {
                String currentPlayer = players[currentPlayerIndex];
                System.out.print(currentPlayer + "'s turn! Enter your guess (or type 'hint'): ");
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("hint")) {
                    if (maxAttempts - attempts <= 2) {
                        System.out.println("❌ Not enough attempts left for a hint!");
                    } else {
                        attempts += 2; // Hint costs 2 attempts
                        giveHint(numberToGuess, maxRange);
                    }
                    continue;
                }

                int userGuess;
                try {
                    userGuess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input! Enter a number or 'hint'.");
                    continue;
                }

                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("📉 Too Low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("📈 Too High!");
                } else {
                    int score = 100 - (attempts * 10);
                    System.out.println("🎉 " + currentPlayer + " guessed the number in " + attempts + " attempts!");
                    System.out.println("🏆 " + currentPlayer + "'s Score: " + score);

                    if (score > highScore) {
                        highScore = score;
                        saveHighScore(highScore);
                        System.out.println("🔥 New High Score! " + highScore);
                    } else {
                        System.out.println("🎯 Best Score So Far: " + highScore);
                    }

                    updateLeaderboard(currentPlayer, score);
                    break;
                }

                // Switch turn
                currentPlayerIndex = (currentPlayerIndex + 1) % 2;

                if (attempts == maxAttempts) {
                    System.out.println("\n❌ Game Over! The correct number was: " + numberToGuess);
                }
            }

            // Show leaderboard after each game
            showLeaderboard();

            System.out.print("\nDo you want to play again? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("Thanks for playing! Your best score was: " + highScore);
        scanner.close();
    }

    // ✅ Hint System
    public static void giveHint(int number, int maxRange) {
        if (number % 2 == 0) {
            System.out.println("🔍 Hint: The number is EVEN.");
        } else {
            System.out.println("🔍 Hint: The number is ODD.");
        }

        int lowerBound = Math.max(1, number - 10);
        int upperBound = Math.min(maxRange, number + 10);
        System.out.println("📍 Hint: The number is between " + lowerBound + " and " + upperBound);
        System.out.println("⚠️ Using a hint costs 2 attempts!");
    }

    // ✅ Load High Score
    public static int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    // ✅ Save High Score
    public static void saveHighScore(int highScore) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            System.out.println("❌ Error saving high score.");
        }
    }

    // ✅ Update Leaderboard (Stores Top 5 Players)
    public static void updateLeaderboard(String player, int score) {
        try {
            List<String> scores = new ArrayList<>();
            File file = new File(LEADERBOARD_FILE);

            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    scores.add(line);
                }
                reader.close();
            }

            scores.add(player + " - " + score);
            scores.sort((a, b) -> Integer.parseInt(b.split(" - ")[1]) - Integer.parseInt(a.split(" - ")[1]));

            if (scores.size() > 5) scores = scores.subList(0, 5); // Keep only top 5

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String s : scores) {
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("❌ Error updating leaderboard.");
        }
    }

    // ✅ Show Leaderboard
    public static void showLeaderboard() {
        System.out.println("\n🏆 LEADERBOARD 🏆");
        try (BufferedReader reader = new BufferedReader(new FileReader(LEADERBOARD_FILE))) {
            String line;
            int rank = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(rank + ". " + line);
                rank++;
            }
        } catch (IOException e) {
            System.out.println("No leaderboard data found.");
        }
    }
}