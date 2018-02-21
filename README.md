# Assumptions/Notes:

* It will provide more details on an invalid move as the reason is available.
* After every command (including invalid moves) it will re-show the current game board. This may not be the most ideal but it simplifies the code a little and avoids having to scroll back up.
* To make it easier to comprehend when reading the code, Players are called A and B internally. However, the output uses X and O as required.
* OthelloGame is properly tested via the full end to end test OthelloAppTest, hence the test file for this class testing one case only.
* Some test cases are missing due time frames. This includes player b winning, there being a tie (assuming this is possible), moves being possible for one but not the other player, etc. With more time these test cases can be identified and added. 
* With my limited Maven experience, I found every time I built anything it seemed to assumed I was using java 1.5 for some reason so I've added the maven compiler plugin to get around this. It may not be necessary for you to build, feel free to remove as per your situation.

# To run:
From IDE
* Run OthelloApp main method

From Command Line
* mvn clean compile assembly:single
* java -cp target\othello-1.0-jar-with-dependencies.jar com.othello.OthelloApp