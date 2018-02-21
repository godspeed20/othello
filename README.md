# Assumptions:

* Rather than "Invalid move. Please try again." I chose to provide more detail since I had the actual reason available.
* I re-show the current board after every command including invalid moves, unlike the intial example. This may not be the most ideal but given the user typed the wrong thing in maybe they didn't read the board correctly. Avoids having to scroll back up.
* To make it easier to comprehend when reading the code, I've used player A and B. However, as per the requirements, the output uses X and O as requested.
* OthelloGame is properly tested via the full end to end test OthelloAppTest.
* some level of 'AI' is exposed to test end states to simplify the testing. This can be refactored with more time.

