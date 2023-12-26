## This is an open-source Java-Selenium library to perform actions on https://www.gamesforthebrain.com/game/checkers/.

# Documentation

# Design/Implementation details for Checkers Game UI

## Board Class:
Board class is responsible for representing the game board and the actions that can be performed on it. This includes simple moves and jumps. 
The class contains methods like select (to choose a piece based on its name) and move (to execute both simple moves and jumps).
Additionally, I've laid the groundwork for capturing piece statuses, although this functionality is yet to be fully implemented.

## StepDirection Enum:
To simplify move definitions, I've introduced an enum that encompasses all possible moves within the game, including diagonal directions and jump actions.
Users can easily specify move actions by selecting the appropriate enum constant.
 
## Board Navigation Class:
I've created a class named ‘BoardNav’ responsible for managing the movement of individual pieces based on their attributes, primarily their names. 
This class provides implementations for various possible moves, such as:
moveDiagonallyFwdLeft
moveDiagonallyFwdRight
jumpOverFwdLeft
jumpOverFwdRight
Note that backward moves and consecutive jump overs haven't been implemented due to time constraints. Also above methods were implemented just for illustrative purpose.

## Checkers Game Class:
For the actual game itself, I've designed a Checkers class that adheres to the Page Object Design pattern to create an instance of the board.

## BaseTest Class:
I've implemented a BaseTest class for setting up the ChromeDriver and launching the game website. This separation of test logic from the business logic ensures easier maintenance and abstraction.

## Game Test Class ('GameTest'):
In the 'GameTest' class, I've created an instance of the game (including the board) and executed the required actions. This class contains the following test cases:

1. verifyCheckersPageIsLoadedAndVisible: A test case to ensure that the Checkers page is successfully loaded.
2. testToMakeFIVEOrangeMovesIncludingTWOJumps: A test case that performs five moves, including both simple moves and jumps.
3. testToRestartTheGame: A test case to verify if the game is correctly reloaded after a restart or refresh.

Despite the inherent complexity of Checkers game rules and functions, and considering time constraints, my focus has been primarily to have a simple POC. 
However, I believe that the classes I've constructed provide a clear insight into my design and implementation approach.


 

