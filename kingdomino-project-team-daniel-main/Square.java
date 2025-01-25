import java.awt.image.BufferedImage;

public class Square implements Cloneable
{
	private BufferedImage tile;
	private String type;
	private int crowns;
	public Square (String x, int y, BufferedImage z)
	{
		tile = z;
		type = x;
		crowns = y;
	}
	
	public String retType()
	{
		return type;
	}
	
	public BufferedImage retImage()
	{
		return tile;
	}
	
	public int retCrowns()
	{
		return crowns;
	}
	
	public Object clone()throws CloneNotSupportedException
	{  
		return super.clone();  
	}
}