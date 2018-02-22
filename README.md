# Assumptions/Notes:

* It will provide more details in the event of an invalid move as the reason is available.
* On finishing a game the app quits. 
* TODO: With my limited Maven experience, I found every time I built anything it seemed to assumed I was using java 1.5 for some reason so I've added the maven compiler plugin to get around this. It may not be necessary for you to build, feel free to remove as per your situation.

# To run:
From IDE
* Run OthelloApp main method

From Command Line
* mvn clean compile assembly:single
* java -cp target\othello-1.0-jar-with-dependencies.jar com.othello.OthelloApp