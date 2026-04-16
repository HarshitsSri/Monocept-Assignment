# Tic Tac Toe Game (Java)

A console-based Tic Tac Toe game built using Java with a focus on clean architecture and the **Facade Design Pattern**. The project supports multiple game modes and demonstrates modular design, input validation, and separation of concerns.

---

## 🚀 Features

*  Two Game Modes:
    Human vs Human
    Human vs Computer
*  Clean and modular package structure
*  Implementation of Facade Design Pattern
*  Input validation for safe gameplay
*  Console-based interactive UI
*  Reusable components (board, move handling, winner checking)

---

## 🧠 Design Pattern Used

### Facade Design Pattern

The project uses separate facade classes to simplify interaction with complex game logic:

* `GameFacade` → Handles Human vs Human mode
* `ComputerGameFacade` → Handles Human vs Computer mode

These facade classes:

* Hide internal implementation details
* Provide a single entry point to start the game
* Improve code readability and maintainability

---

## 📦 Project Structure

```
com.monocept
│
├── app        → Entry point (Main class, user interaction)
├── Human      → Human vs Human game logic
├── Computer   → Human vs Computer game logic
```

### 🔹 App Package

* Contains the `Main` class
* Displays menu and takes user input
* Creates objects of respective facade classes
* Does NOT contain game logic

---

### 🔹 Human Package

* Handles Human vs Human gameplay
* Manages player turns, moves, and winner checking
* Input validation is done directly within the game flow
* Uses `GameFacade` to expose functionality

---

### 🔹 Computer Package

* Handles Human vs Computer gameplay
* Includes logic for computer-generated moves
* Uses helper classes for input validation
* Access is controlled through `ComputerGameFacade`

---

## ▶️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/HarshitsSri/Monocept-Assignment.git
   ```

2. Open the project in any Java IDE:

   * IntelliJ IDEA
   * Eclipse

3. Navigate to:

   ```
   com.monocept.app.Main
   ```

4. Run the program

---

## 🎯 Game Flow

1. User selects game mode
2. Corresponding facade is initialized
3. Game board is created
4. Players make moves turn by turn
5. Input is validated
6. Winner is checked after each move
7. Game ends with:

   * Winner declaration
   * OR draw message

---

## ⚡ Key Highlights

* Separation of UI and core logic
* Encapsulation using facade classes
* Demonstrates different validation approaches
* Easy to extend for future features

---

## 🚀 Future Improvements

* 🎨 Add GUI (JavaFX / Swing)
* 🤖 Improve AI (Minimax algorithm)
* 🎯 Add difficulty levels
* 🌐 Multiplayer support

---

## 👨‍💻 Author

**Harshit Srivastava**
B.Tech IT Student | SWE Intern @Monocept

---

