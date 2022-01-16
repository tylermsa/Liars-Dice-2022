/** Creates the main engine to run Liar's Dice. Takes turns between player and CPU for making bets or challenging.
* @author tylermsa
* @version 2022.1.16
*/

import java.util.Scanner;
import java.util.Random;

public class LDGame
{
	public static int getNumOfBid(String bid)
	{
		int space = bid.indexOf(" ");
		return Integer.parseInt(bid.substring(0, space));
	} // getNumOfBid
	
	public static Die getValueOfBid(String bid, Die cd)
	{
		int space = bid.indexOf(" ");
		String valDie = bid.substring(space+1);
		valDie.toLowerCase();
		
		/* System.out.println("@-" + valDie); */
		
		switch(valDie)
		{
			case "ones":
				return Die.ONES;
			case "twos":
				return Die.TWOS;
			case "threes":
				return Die.THREES;
			case "fours":
				return Die.FOURS;
			case "fives":
				return Die.FIVES;
			case "sixes":
				return Die.SIXES;
			default:
				return cd;
			
		} // switch(valDie)
		
	} // getValueOfBid
	
	public static Die nextDieVal(Die prevDie, Die cd)
	{
		switch(prevDie)
		{
			case ONES:
				return Die.TWOS;
			case TWOS:
				return Die.THREES;
			case THREES:
				return Die.FOURS;
			case FOURS:
				return Die.FIVES;
			case FIVES:
				return Die.SIXES;
			default:
				return cd;
			
		} // switch(valDie)
		
	}
	
	
	public static void main(String[] args)
	{
		// Game-state variables
		boolean gameOver = false;
		boolean winner = false;
		boolean isChallenging = false;
		
		// Game Setup
		final int STARTING_DICE = 6;
		final int NUM_PLAYERS = 2;
		int[][] playersDice = new int[NUM_PLAYERS][];
		final String CPU_DIFFICULTY = "normal";
		final int CPU_CHALLENGING_MAGIC_NUM = 2; // possible logic error type shit???
		int round = 0;
		
		// For two players
		int playerDiceRemaining = STARTING_DICE;
		int cpuDiceRemaining = STARTING_DICE;
		boolean yourTurn = true;
		
		// Utilities
		Scanner kb = new Scanner(System.in);
		String userResponse = "";
		String prevResponse = "";
		Random rand = new Random();
		
		
		/** Introduce software
		*/
		System.out.println("Liar's Dice GitHub v2022.1.16");
		System.out.println("powered by -Tylermsa");
		System.out.println("-----------------------------");
		
		while(!gameOver)
		{
			/** FOR TWO PLAYER GAME YA
			*/
			
			/** Assign new int[] in the array
			*/
			playersDice[0] = new int[playerDiceRemaining]; // P1
			playersDice[1] = new int[cpuDiceRemaining];    // CPU
			round ++;
			isChallenging = false;
			
			/* System.out.println("@-" + playersDice[0].length);
			System.out.println("@-" + playersDice[1].length); */
			
			Die currentDie = Die.ONES;
			
			
			/** Roll Dice into Array and show player's dice
			*/
			for(int i = 0; i < playersDice.length; i++)
			{
				for(int j = 0; j < playersDice[i].length; j++)
				{
					playersDice[i][j] = rand.nextInt(6) +1;
					
				} // for(int j = 0; j < playersDice[i].length; j++)
				
			} // for(int i = 0; i < playersDice.length; i++)
				
			Player p1 = new Player(playersDice[0], "Player");
			Player cpu = new Player(playersDice[1], "CPU");
				
			System.out.println("Your dice for Round " + round + ":");
			
			for(int i = 0; i < playersDice[0].length; i++)
				System.out.print(playersDice[0][i] + " ");
			
			System.out.println();
			System.out.println();
			
			/** Take Turns, determine if raises bid or challenge
			*/
			while(!isChallenging)
			{
				if(yourTurn)
				{
					System.out.print("Make the next highest bid, or challenge: "); // EX 3 fours
					userResponse = kb.nextLine();
					System.out.println();
					
					/** Check response
					*/
					if(userResponse.equals("challenge") || userResponse.equals("Challenge"))
					{
						isChallenging = true; 
						break;
					}
					else if(Character.isDigit(userResponse.charAt(0)))
					{
						int numBid = getNumOfBid(userResponse);
						Die valDie = getValueOfBid(userResponse, currentDie);
						currentDie = valDie;
						userResponse = p1.bid(numBid, valDie);
						System.out.println("- You bid " + userResponse);
						prevResponse = userResponse;
						
						yourTurn = false;
						
					}
					else
					{ System.out.println("You can only enter your bid as \"int DieValue\" or \"challenge\"."); }
					
					
					
				}
				else
				{
					/** CPU's TURN
					* ctrl+c and ctrl+v code from CPU_Difficulties into here depending on your difficulty
					*/
					
					/** CPU Rules:
					- Reads from userResponse
					- Can only bid one higher than previous bid
					- Will challenge only if bid exceeds total dice - da magic number
					*/
					
					int numBid = getNumOfBid(userResponse);
					Die valDie = getValueOfBid(userResponse, currentDie);
					currentDie = valDie;
					
					if(numBid > (cpuDiceRemaining + playerDiceRemaining) - CPU_CHALLENGING_MAGIC_NUM)
						userResponse = cpu.bid(2, nextDieVal(valDie, currentDie));
					else
						userResponse = cpu.bid(numBid+1, valDie);
					System.out.println("- The CPU bids " + userResponse);
					prevResponse = userResponse;
					
					
					/** Switch turn
					*/
					yourTurn = true;
					
				}
				
				
			} // while(!isChallenging)
			
			/** When challenge occurs, commence challenge accepted, update dem dice counts
			*/
			int numBid = getNumOfBid(prevResponse);
			Die valDie = getValueOfBid(prevResponse, currentDie);
			
			if(yourTurn)
			{
				System.out.println("- You challenged the CPU's last bid.");
				System.out.println(cpu);
				System.out.println(p1);
				
				if(p1.challenge(numBid, valDie, cpu))
				{
					System.out.println("- CHALLENGE SUCCESSFUL -");
					cpuDiceRemaining --;
					
					if(cpuDiceRemaining > 0)
					{ System.out.println("- The CPU is down to " + cpuDiceRemaining + " dice."); }
					else
					{
						gameOver = true;
						winner = true;
						break;
						
					}
					
				}
				else
				{
					System.out.println("- CHALLENGE FAILED -");
					playerDiceRemaining --;
					
					if(playerDiceRemaining > 0)
					{ System.out.println("- You are down to " + playerDiceRemaining + " dice."); }
					else
					{
						gameOver = true;
						winner = false;
						break;
						
					}
					
				}
				
			}
			else
			{
				System.out.println("- The CPU challenged your last bid.");
				System.out.println(cpu);
				System.out.println(p1);
				
				if(cpu.challenge(numBid, valDie, p1))
				{
					System.out.println("- CHALLENGE SUCCESSFUL -");
					playerDiceRemaining --;
					
					if(playerDiceRemaining > 0)
					{ System.out.println("- You are down to " + playerDiceRemaining + " dice."); }
					else
					{
						gameOver = true;
						winner = false;
						break;
						
					}
					
				}
				else
				{
					System.out.println("- CHALLENGE FAILED -");
					cpuDiceRemaining --;
					
					if(cpuDiceRemaining > 0)
					{ System.out.println("- The CPU is down to " + cpuDiceRemaining + " dice."); }
					else
					{
						gameOver = true;
						winner = true;
						break;
						
					}
					
				}
				
			}
			
			System.out.println();
			
		} // while(!gameOver)
			
		
		/** Close program with winning statement
		*/
		System.out.println();
		
		if(winner)
			System.out.println("- CPU has run out of dice: You win!");
		else
			System.out.println("- You have run out of dice: CPU wins!");
		
		System.out.println();
		
	} // main
	
} // public class LDGame