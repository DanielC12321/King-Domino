import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.swing.*;
import java.io.*;

public class DominoChoices extends JPanel 
{
	static final long serialVersionUID = 0;
	int tempC = 0;
	BufferedImage title;
	
	public DominoChoices()
	{
		try 
		{
			title = ImageIO.read(DominoChoices.class.getResource("/images/gameHub.jfif"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(title, 0, 0, getWidth(), getHeight(), null);
		System.out.println(GameState.Whoseturn);
		try 
		{
			g.drawImage(ImageIO.read(DominoChoices.class.getResource("/images/King"+ (GameState.Whoseturn)+".png")), 55, 150, 100, 100, null);
		}
		catch(Exception ex)
		{
			System.out.println("Not exist");
		}
		for(int i = 0; i<GameState.choices[GameState.chooseFrom].size(); i++)
		{
			try 
			{
				g.drawImage(ImageIO.read(DominoChoices.class.getResource("/images/King"+ (GameState.choices[GameState.chooseFrom].get(i).retOwner())+".png")), 900, 50+200*i, 150, 150, null);
			}
			catch(Exception ex){}
			g.drawImage(GameState.choices[GameState.chooseFrom].get(i).retL().retImage(), 1100, 50+200*i, 150, 150, null);
			g.drawImage(GameState.choices[GameState.chooseFrom].get(i).retR().retImage(), 1250, 50+200*i, 150, 150, null);
		} 
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(4));
		for(int i = 0; i<4; i++)
		{
			if(i+1==tempC)
			{
				g.drawRect(1100, 50+200*i, 300, 150);
			}
		}
		int count = 0;
		for(int i = 0; i<2; i++)
		{
			for(int j = 0; j<2; j++)
			{
				try 
				{
					g.drawImage(ImageIO.read(DominoChoices.class.getResource("/images/King"+ count+".png")), 55+130*j, getHeight()-525+125*i, 100, 100, null);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				count++;
			}
		}
	}
	
	public void setC(int x)
	{
		tempC = x;
	}
	
	public int retC()
	{
		return tempC;
	}
}
