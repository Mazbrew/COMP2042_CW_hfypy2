## A. Refactoring changes.

### 1. The unused imports and variables alongside the comments, were removed.
- Removal of the unused imports and variables got rid of the warnings that popped up.
- Removal of comments prevented me from forming assumptions towards the code and ensured that I looked through them thoroughly.

### 2. Slight refactoring of the GameMain.java
- Instead of using an anonymous class of the GameFrame, I created an instance of the gameframe within the GameMain and called a few of its methods.
  
### 3.  Complete overhaul of the HomeMenu.java
- The original HomeMenu.java was an eyesore. A complete overhaul of the HomeMenu.java was done to make it look and work better.
  
- The original class extended a JComponent and everything was drawn using the paint() method but my version of the class extends a JLayeredPane that enables me to add and remove image resources according to their specified layers.
  
- Two extra buttons were also added to the HomeMenu, the HIGHSCORE and INFO button.
> <strong style="color:lightblue">REFERENCE</strong>


### 4. Refactoring changes made to the GameFrame
- The method calls within the GameFrame class's constructor, that changed the properties of the GameFrame, were moved into the initialize method. 

- Methods that enable the GameFrame to swap between views were added:
  - revertGameboard()
  - enableInfo()
  - revertInfopage()
  - enableHighscore()
  - revertHighscorepage()
> <strong style="color:lightblue">REFERENCE</strong>

- The gaming boolean was refactored to by adding a getter method within the GameFrame and getting the value in GameBoard instead of calling the GameBoard.onLostFocus() method.

- The autoLocate() method was removed from the initalize() method to prevent the unnecessary centering of the frame.

- A cute little image of a brick was added on to the title bar. 

- The setDefaultCloseOperation() method was set to DO_NOTHING_ON_CLOSE as the EXIT buttons on the Pause Menu Screen and End Menu Screen are integral to how the user's scores are saved into the highscore list.
> <strong style="color:lightblue">REFERENCE</strong>

### 5. Refactoring changes to the GameBoard
- A lot of refactoring was done to the GameBoard class to ensure that the game runs smoothly.

  ### i. Player movement
  - Player movement controls were heavily refactored to ensure that the player will glide smoothly and wihout interruption so long as at least one of the 4 movement keys were held down. ([LEFT_ARROW],[RIGHT_ARROW],[A],[D])

  - Removed the default case which caused the player to stop moving when any other button is pressed.
  
  ### ii. Pause menu
  - The looks of the pause menu were slightly changed  and the main menu button was added.

  ### iii. End Screen menu
  - An end screen menu was added to the game, will be displayed when the ball count reaches 0.

  - The End Screen menu contains 2 buttons, the Main Menu button and the Exit button.

  ### 
