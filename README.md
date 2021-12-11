## File directories
- Javadocs : build/target/apidocs/*
- Jar file : bild/target/BrickDestroyer-1.1.jar

---

## A. Refactoring changes.
### 1. General
- Removed the unused imports and variables.

- Getter and setter methods were added to improve upon encapsulation.

- all the classes were separated into different classes

- packages were named according to MVC 

### 2.GameMain
- Instead of using an anonymous class of the GameFrame, I created an instance of the gameframe within the GameMain and called a few of its methods.
  
### 3. HomeMenu
- A complete overhaul of the HomeMenu.java was done to make it look and work better.
  
- Two extra buttons were also added to the HomeMenu, the HIGHSCORE and INFO button.

### 4.GameFrame
- The method calls within the GameFrame class's constructor, that changed the properties of the GameFrame, were moved into the initialize method. 

- Methods that enable the GameFrame to swap between views were added

- The gaming boolean was refactored to by adding a getter method within the GameFrame and getting the value in GameBoard instead of calling the GameBoard.onLostFocus() method.

- The autoLocate() method was removed from the initalize() method to prevent the unnecessary centering of the frame.

- The setDefaultCloseOperation() method was set to DO_NOTHING_ON_CLOSE as the EXIT buttons on the Pause Menu Screen and End Menu Screen are integral to how the user's scores are saved into the highscore list.


### 5.GameBoard
  - Player movement controls were heavily refactored to ensure that the player will glide smoothly and wihout interruption.

  - Removed the default case which caused the player to stop moving when any other button is pressed.
  
  - The looks of the pause menu were slightly changed  and the main menu button was added.

  - An end screen menu was added to the game, will be displayed when the ball count reaches 0.

  - The End Screen menu contains 2 buttons, the Main Menu button and the Exit button. 
  - 
  - To better fit the MVC convention, the GameTimer was moved to GameContoller.java.

### 6. GameController i.e. Wall
- All of the game logic was moved into GameContoller.java.

- makeSingleTypeLevel() method was fixed to enable different bricks to be made, not just clay.

- added checks to prevent the ball from disappearing or sticking to the paddle.

### 7. Brick and child classes
- Crack class is now it's own standalone class, Crack.java.

- makeBrickFace() and getBrickFace overrides in the child classes are were removed

### packages according to MVC

---

## B. Additions.

### New levels and bricks
- 5 new levels were added 

- 3 new bricks were added
  - slime
  - gravity
  - speed

- 2 new views
  - HighscorePage
  - InfoPage

- The highscore.java model was created


