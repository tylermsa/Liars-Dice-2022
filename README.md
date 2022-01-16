# Liars-Dice
A game of liar's dice where you play a game, of Liar's Dice, with a CPU. This will constantly be updated as new and improved features/ code are added.

# Specs

LDGame - Main Engine of the Game

Die - Enumeration of a single 6-sided die

Player - Object that retruns bids and challenges. toString() returns the player's name and dice.

InvalidBidException (WILL BE IMPLEMENTED LATER) - Custom exception that is thrown only when a player attempts to make a bid lower than the highest current die value and/or frequency of dice.
