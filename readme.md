# Minesweeper Project
- This project will create and display a Minesweeper game in the command line

- To run the file:
    java -jar target/*.jar

- To set up the database:
    sudo docker run -d --name minesweeper -p 5432:5432 --rm -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password postgres
    sudo docker exec -it minesweeper psql -U user

## Design
- com.github.garrisonadams.minesweeper
    - Minesweeper.java: main program that starts up the program
    - Tile.java: The class that describes the Tiles used in Minesweeper.java
- com.github.garrisonadams.database
    - Database.java: class that contains the methods that interact with the postgres database
    - DatabaseConnector.java: class that connects to the postgres database
- com.github.garrisonadams.io
    - InputHandler.java: class that 
- src\main\resources
    - contains UserNotAuthenticate.csv and UserWin.csv, which contain the input to the program

## Explanation
- main() creates a Minesweeper object, and passes String[] args to  its startup() method
    - First, startup() creates a BufferedReader object 
    - The username and password in the input file is read and passed to the authenticateUser() method in Database.java

    - authenticateUser() method sends a SQL query to the postgres database and returns a boolean value.
        - First it checks to see if the username already exists.
            - If the username does exist, a SQL query is sent to the database to check if it is the correct password
                    - If it is the correct password, then authenticateUser() returns true
                    - If it is not the correct password, then authenticateUser() returns false and the program closes
            - If the username does not already exist, newUser() is called and the database tables are updated and authenticateUser() returns true

    - If authenticateUser() returns false, then the program ends

    - If authenticateUser() returns true, then the program calls userInterface()

    - userInterface() is what the users interact with
        - First, there is a while loop that runs as long as the boolean variable isPlaying is true
            - Next, the display() method is called. This prints the current state of the Minesweeper grid to the console
            - The user is told how to enter commands: exit, select, flag, unflag
            - The command the user enters is sent to the executeCommand() method
            - After the command is executed, the program checks to see if the user has won or lost.
            - If the user has NOT won or lost, then the while loop executes again
            - If the user HAS won or lost, the corresponding win() or lose() is called
                - Then an incrementWin() or incrementLoss() is called. These methods retrieve the user's number of wins/losses from the database, increments it, and updates the database
            -  Lastly, isPlaying becomes false
        - After the while loop, the printUsernameStats() method is called. It retrieves the user's wins and losses.
        - Next, the user is asked if they want to play another game
            - If yes, then the restart() method is called, which creates another Minesweeper object and then goes straight to its userInterface() method, bypassing userAuthenticate()
        
        