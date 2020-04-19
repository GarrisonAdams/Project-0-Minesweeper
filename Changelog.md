# Changelog

## Version 0.3.4
### Added
- Remembered that they are mines, not bombs. 
- Program can now calculate how many adjacent mines each tile has
- Included a lose() method that is called when a tile with a mine is selected
- Included a win() that determines if the winning conditions have been met and ends the program

## Version 0.2.4
### Added
- It is now possible to mark tiles that you think are bombs
- Changed the access modifiers of the instance variables of Tile.java from default to private and created setters and getters for them
- Tiles can be marked and unmarked
- Minesweeper.java modified so the main() method only starts up the game and is not responsible for anything else
- SRP hopefully followed

## Version 0.1.4
### Added
- Selected tiles now propogate throughout the grid as expected, for a few test cases

## Version 0.1.3
### Added
- The user can now uncover any tile

## Version 0.1.2

### Added
- Project has been successfully Mavenized

## Version 0.1.1
### Added

#### Minesweeper.java
- The constructor initializes the row, column, isCovered, and tileDisplayValue of all tiles correctly
- The display method displays the Minesweeper grid
- The while loop in main(String[] args) displays the user interface that will be used


#### Tile.java
- All tiles have a row, column, hasBomb, isCovered, tileDisplayValue, and adjacentBombs values
- Setters and Getters for the tileDisplayValue

- .gitignore added




## Version 0.0.1
### Added 
- Changelog.md first created
- readme.md first created