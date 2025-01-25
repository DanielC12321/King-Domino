public class Domino 
{
	private int whoOwns;
	private int numBack;
	private Square sL;
	private Square sR;
	
	public Domino(Square x, Square y, int z)
	{
		sL = x;
		sR = y;
		numBack = z;
		whoOwns = -1;
	}
	
	public void setOwner(int x)
	{
		whoOwns = x;
	}
	
	public Square retR()
	{
		return sR;
	}
	
	public Square retL()
	{
		return sL;
	}
	
	public int retNum()
	{
		return numBack;
	}
	
	public int retOwner()
	{
		return whoOwns;
	}
}