/** Unique exception that will be thrown only when an attempted bid is lower or equal to the previous bid.
* @author tylermsa
* @version 2022.1.11
*/

public class InvalidBidException extends Exception
{
	// Methods for error handling from bid
	private int freqBid;
	private Die valBid;
	
	
	// Constructors
	public InvalidBidException(int num, Die value)
	{
		super("Invalid bid. You must bid higher in frequency or by die value.");
		freqBid = num;
		valBid = value;
	} // public InvalidBidException(int num, Die value)
	
	
	// Generic Getter Methods
	public int getFrequencyOfBid() 
	{ return freqBid; }
	
	public Die getDieValueOfBid() 
	{ return valBid; }
	
} // public class InvalidBidException extends Exception