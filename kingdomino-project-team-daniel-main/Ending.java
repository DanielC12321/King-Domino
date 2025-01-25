import java.awt.*; 
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Ending extends JPanel 
{
	static final long serialVersionUID = 0;
	BufferedImage image;
	
	public Ending()
	{
		try 
		{
			image = ImageIO.read(Starting.class.getResource("/images/leaderboard.jfif"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		int counter = 0;
		g.setFont(new Font ("Helvetica", Font.BOLD, 50));
		g.setColor(new Color(255, 215, 0));
		for(int i = 0; i<2; i++)
		{
			for(int j = 0; j<2; j++)
			{
				try 
				{
					g.drawImage(ImageIO.read(Board.class.getResource("/images/King"+ counter+".png")), 400+550*j, getHeight()-600+300*i, 100, 100, null);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				g.drawString(""+GameState.egameinf[0][counter], 400+550*j, getHeight()-610+300*i);
				if(GameState.egameinf[1][counter]==1)
					g.drawString("Winner!", 400+550*j, getHeight()-490+300*i);
				
				counter++;
			}
		}
	}
}