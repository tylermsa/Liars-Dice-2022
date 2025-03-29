# Liars-Dice (2022 Java Version)
A game of liar's dice where you play a game, of Liar's Dice, with a CPU. This was made back in 2022 to be familiar with Java and to prepare for some of my harder classes in the Spring Semester of my Freshmen Year.

I will try to fix the bugs from the `2022.1.16` version before moving on to making a version of this in Python.

## Specs
**LDGame.java** - Main Engine of the Game

**Die.java** - Enumeration of a single 6-sided die

**Player.java** - Object that retruns bids and challenges. toString() returns the player's name and dice.

**InvalidBidException.java *`(TODO)`*** - Custom exception that is thrown only when a player attempts to make a bid lower than the highest current die value and/or frequency of dice.
