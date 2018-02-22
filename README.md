# Assumptions/Notes:

* It will provide more details in the event of an invalid move as the reason is available.
* On finishing a game the app quits. You can start a new game during an existing game, however.
* I couldn't find an example of how to end a game with a tie break so this case is not supported.
* I've added the maven assembly plugin so the program can one be one-jar'd and run off the command line easily. If you run it from IntelliJ you won't need this plugin at all.

# To run:
From IDE
* Run OthelloApp main method

From Command Line
* mvn clean compile assembly:single
* java -cp target\othello-1.0-jar-with-dependencies.jar com.othello.OthelloApp