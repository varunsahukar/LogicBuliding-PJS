# 🎮 Multiplayer Number Guessing Game

## 📌 About the Game
The **Multiplayer Number Guessing Game** is a fun console-based game where two players take turns guessing a randomly chosen number within a selected difficulty range. The game features a scoring system, hints, a leaderboard, and the ability to save high scores.

## 🛠️ Features
- 🎭 **Multiplayer Mode** (Two players take turns guessing)
- 🔢 **Difficulty Levels** (Easy, Medium, Hard)
- 🎯 **Leaderboard** (Stores the top 5 scores)
- 💡 **Hint System** (Hints cost 2 attempts)
- 💾 **High Score Saving** (Persists across sessions)

---

## 🚀 How to Run
### 1️⃣ Setup Project Structure
Ensure your files are organized as follows:
```
NumberGuessingGameProject/
│── src/
│   └── com/
│       └── numberGuessingGame/
│           └── numberGG.java
│── leaderboard.txt
│── README.md
```

### 2️⃣ Compile and Run
1. **Open VS Code and Terminal (`Ctrl + ~`)**
2. **Compile the program:**
   ```sh
   javac -d bin src/com/numberGuessingGame/numberGG.java
   ```
3. **Run the game:**
   ```sh
   java -cp bin com.numberGuessingGame.numberGG
   ```

If you removed the `package com.numberGuessingGame;` line, simply run:
```sh
javac numberGG.java
java numberGG
```

---

## 🎮 How to Play
1. The game asks for **two player names**.
2. Select a **difficulty level**:
   - 🟢 Easy (1-50) - 10 attempts
   - 🔵 Medium (1-100) - 7 attempts
   - 🔴 Hard (1-500) - 5 attempts
3. Players take turns guessing the **random number**.
4. If needed, type `hint` to get a clue (costs 2 attempts).
5. The player who guesses correctly **wins the round** and gets a score.
6. **High scores and leaderboards are saved**.
7. Players can choose to **play again or exit**.

---

## 🏆 Leaderboard System
- Stores the **top 5 scores** in `leaderboard.txt`.
- **Scores are calculated as:** `100 - (attempts * 10)`.
- The leaderboard updates **automatically** after each game.

---

## 📌 Contributions & Improvements
Feel free to fork, modify, and improve the game! Possible enhancements:
- Adding a **single-player mode**
- Implementing a **GUI version** using Java Swing/JavaFX
- Enhancing the **AI to provide smarter hints**

🚀 Enjoy the game and happy coding!

