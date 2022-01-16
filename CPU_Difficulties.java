/** Copy-and-paste document with code for CPU Difficulty. 
* DO NOT COMPILE THIS FILE DURING COMPILE-TIME OR YOU WILL GET NUMEROUS, EXPECTED SYNTAX ERRORS. THIS SHOULD BE USED TO REFER AND CTRL+C/ CTRL+V INTO THE CPU TURN ALGORITHM.
* @author tylermsa
* @version 2022.1.16
*/

public class CPU_Difficulties
{
	void original_diff()
	{
		int numBid = getNumOfBid(userResponse);
		Die valDie = getValueOfBid(userResponse, currentDie);
		currentDie = valDie;
		
		userResponse = cpu.bid(numBid+1, valDie);
		System.out.println("- The CPU bids " + userResponse);
		prevResponse = userResponse;
					
		
	} // void original_diff()
	
	void easy_diff()
	{
		
		
	} // void easy_diff()
	
	void normal_diff()
	{
		
		
	} // normal_diff()
	
} // public class CPU_Difficulties