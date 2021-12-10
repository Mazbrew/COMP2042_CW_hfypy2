## A. Refactoring changes.

### 1. Removal of unsused imports , variables and the original commenting. 

- In the original source code, there were various  unused imports and variables. The first thing that was done to the code was to remove them all. Aside for that, the comments that were left by the original developer, caused some confusion and therefore they were removed as well.
  
- The removal of the unused imports and variables got rid of the warnings that were displayed in the vscode IDE


### 2. Slight refactoring of the GameMain.java

- The original GameMain.java contained a lambda expression that I didn't quite understand.
  
- In order to make it more understandable, I changed a few lines of code within this file.
  
- Instead of using an anonymous class of the GameFrame, I created an instance of the gameframe within the GameMain and called a few of its methods.
  
- The resulting code is a bit more readable and understandable.


### 3.  Complete overhaul of the HomeMenu.java

- The original HomeMenu.java was an eyesore. A complete overhaul of the HomeMenu.java was done to make it look and work better.
  
- The original class extended a JComponent and everything was drawn using the paint() method but my version of the class extends a JLayeredPane that enables me to add and remove image resources according to their specified layers.
  
- Instead of hardcoding how the HomeMenu looks like, I can swap out images, at any point, to completely change its looks just by editing the file name.
  
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

- The gaming boolean was refactored to by adding a getter method within the GameFrame. This getter method is called within the GameBoard every game tick to check if the window has lost focus. If so, the game will pause and a message telling users to press [SPACE] to continue will appear.

- The autoLocate() method was removed from the initalize() method as I didn't want it to be called everytime the view was changed which would cause the GameFrame to position to the center of the screen each time. This enables users to move the GameFrame without the annoyance of it recentering in the screen. 

- A cute little image of a brick was added on to the title bar. 

- The setDefaultCloseOperation() method was set to DO_NOTHING_ON_CLOSE as the EXIT buttons on the Pause Menu Screen and End Menu Screen are integral to how the user's scores are saved into the highscore list.

