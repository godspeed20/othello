# Assumptions:

* Rather than "Invalid move. Please try again." I chose to provide more detail since I had the actual reason available.
* I re-show the current board after every command including invalid moves, unlike the intial example. This may not be the most ideal but given the user typed the wrong thing in maybe they didn't read the board correctly. Avoids having to scroll back up.
* To make it easier to comprehend when reading the code, I've used player A and B. However, as per the requirements, the output uses X and O as requested.
* OthelloGame is properly tested via the full end to end test OthelloAppTest, hence the test file for this class testing one case only.
* Some level of 'AI' is exposed to test end states to simplify the testing. This can be refactored with more time.
* I was, within the suggested time frame, unable to identify sufficient moves to test cases such as player b winning, there being a tie, moves being possible for one but not the other player. With more time these test cases could be identified and added. 
* With my limited Maven experience, I found every time I built anything it seemed to assumed I was using java 1.5 for some reason so I've added the maven compiler plugin. You might not need this, I've added it so it would work cleanly on my side though. Feel free to remove if it's easier for you.

# To run:
From IDE
* Run OthelloApp main method

From Command Line
* mvn clean compile assembly:single
* java -cp target\othello-1.0-jar-with-dependencies.jar com.othello.OthelloApp