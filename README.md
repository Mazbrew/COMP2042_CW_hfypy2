- Javadocs : build/target/apidocs/*
- Jar file : build/target/BrickDestroyer-1.1.jar
- Junit tests : build/src/test/java/com/build
- git hub : https://github.com/Mazbrew/COMP2042_CW_hfypy2

---

## A. Refactoring changes.
### 1. General
- Removed the unused imports and variables.

- Getter and setter methods were added to improve upon encapsulation.

- all the classes were separated into different classes.

- packages and separated according to MVC.

### 2.GameMain
- Instead of using an anonymous class of the GameFrame, I created an instance of the gameframe within the GameMain and called a few of its methods.
  
### 3. HomeMenu
- A complete overhaul of the HomeMenu.java was done to make it look and work better.

### 4.GameFrame
- The method calls within the GameFrame class's constructor, that changed the properties of the GameFrame, were moved into the initialize method. 

- Methods that enable the GameFrame to swap between views were added.

- The gaming boolean was refactored to by adding a getter method within the GameFrame and getting the value in GameBoard instead of calling the GameBoard.onLostFocus() method.

- The autoLocate() method was removed from the initalize() method to prevent the unnecessary centering of the frame.

- The setDefaultCloseOperation() method was set to DO_NOTHING_ON_CLOSE as the EXIT buttons on the Pause Menu Screen and End Menu Screen are integral to how the user's scores are saved into the highscore list.


### 5.GameBoard
- Player movement controls were heavily refactored to ensure that the player will glide smoothly and wihout interruption.

- Removed the default case which caused the player to stop moving when any other button is pressed.
  
- The looks of the pause menu were slightly changed  and the main menu button was added.
  
- To better fit the MVC convention, the GameTimer was moved to GameContoller.java.

- When the pause menu is closed, the game continues running.

- debug panel is displayed when [F1] is pressed.

### 6. GameController i.e. Wall
- All of the game logic was moved into GameContoller.java.

- makeSingleTypeLevel() method was fixed to enable different bricks to be made, not just clay.

- added checks to prevent the ball from disappearing or sticking to the paddle.

- added a check to prevent a null exception when "skip level" is pressed on the debug panel

### 7. Brick and child classes
- Crack class is now it's own standalone class, Crack.java.

- makeBrickFace() and getBrickFace overrides in the child classes are were removed.

### 8. Ball
- min and max speed of the ball was increased.

### 9. debug panel
- the slider was changed to accomodate the new max speed of the ball.

- upon closure after skipping the level, the ball and player is reset to the start point.

---

## B. Additions.

### New levels and bricks
- 5 new levels were added 

- 3 new bricks were added
  - slime
    - randomizes either the X or Y axis speed of the ball, based on direction of impact.
  - gravity
    - randomizes the location of the GameFrame on the screen upon impact.
  - speed
    - increases the X and Y axis speed of the ball by 1.

- 2 new views
  - HighscorePage
  - InfoPage

- The highscore.java model 

- end game screen is displayed when the ball count reaches 0 or when the user wins the game. Contains the Exit button and Main Menu buttons.

- pause menu now has the Main Menu button.


