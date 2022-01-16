/** Generic Build of a Player for Liar's Dice. Contains array of dice, and can either bid or challenge last bid.
* @author tylermsa
* @version 2022.1.15
*/

public class Player
{
	// Declared Variables
	private int[] dice;
	private String playerName;
	
	public Player(int[] dice)
	{
		this.dice = dice;
		playerName = "Player";
		
	} // public Player(int[] dice)
	
	public Player(int[] dice, String name)
	{
		this(dice);
		playerName = name;
		
	} // public Player(int[] dice)
	
	
	public String bid(int num, Die val) // throws InvalidBidException
	{
		if(val == Die.ONES)
			return num + " ones";
		else if(val == Die.TWOS)
			return num + " twos";
		else if(val == Die.THREES)
			return num + " threes";
		else if(val == Die.FOURS)
			return num + " fours";
		else if(val == Die.FIVES)
			return num + " fives";
		else
			return num + " sixes";
		
	} // public String bid(int num, Die val)
	
	public boolean challenge(int num, Die val, Player challenged)
	{
		final int HIGHEST_DIE;
		int frequency = 0;
		
		/** Find highest die value
		*/
		if(val == Die.ONES)
			HIGHEST_DIE = 1;
		else if(val == Die.TWOS)
			HIGHEST_DIE = 2;
		else if(val == Die.THREES)
			HIGHEST_DIE = 3;
		else if(val == Die.FOURS)
			HIGHEST_DIE = 4;
		else if(val == Die.FIVES)
			HIGHEST_DIE = 5;
		else
			HIGHEST_DIE = 6;
		
		/** Count frequnecy of die value
		*/
		
		for(int p = 0; p < dice.length; p++)
		{
			if(dice[p] == HIGHEST_DIE)
				frequency ++;
		}
		
		int[] opDice = challenged.getDice();
		
		for(int o = 0; o < opDice.length; o++)
		{
			if(opDice[o] == HIGHEST_DIE)
				frequency ++;
		}
		
		/** Determine outcome
		*/
		System.out.println("Actual: " + frequency + ", Bid: " + num);
		
		if(num > frequency)
			return true;
		else
			return false;
		
	} // public boolean challenge(int num, Die val, Player challenged)
	
	
	public int[] getDice()
	{ return dice; }
	
	
	public String toString()
	{ 
		String disp = playerName + "'s Dice: \n";
		
		for(int i = 0; i < dice.length; i++)
		{
			disp = disp + dice[i] + " ";
		}
		
		return disp;
	
	} // public String toString()
	
} // public class Player