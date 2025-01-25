import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Board extends JPanel
{
	static final long serialVersionUID = 0;
	BufferedImage kingD;
	public static boolean firsthalf = false;
	public static boolean isValid = false;

	public Board()
	{
		try 
		{
			kingD = ImageIO.read(Board.class.getResource("/images/kingdom.png"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics x)
	{
		int turn = GameState.Whoseturn;
		x.drawImage(kingD, 0, 0, getWidth(), getHeight(), null);
		Graphics2D g2 = (Graphics2D)x ;
		Stroke oldStroke = g2.getStroke();
		int tempQ = 0;
		x.setFont(new Font ("Helvetica", Font.BOLD, 50));
		if(GameState.chooseFrom == 0)
		{
			tempQ = 1;
			System.out.println(tempQ);
		}
		for(int i = 0; i<GameState.choices[tempQ].size(); i++)
		{
			try
			{
				x.drawImage(ImageIO.read(Board.class.getResource("/images/King"+ (GameState.choices[tempQ].get(i).retOwner())+".png")), 400, 125+100*i, 75, 75, null);
			}
			catch(Exception ex){}
			x.drawImage(GameState.choices[tempQ].get(i).retL().retImage(), 490, 125+100*i, 75, 75, null);
			x.drawImage(GameState.choices[tempQ].get(i).retR().retImage(), 565, 125+100*i, 75, 75, null);
		} 
		for(int i = 0; i<GameState.choices[GameState.chooseFrom].size(); i++)
		{
			try 
			{
				x.drawImage(ImageIO.read(Board.class.getResource("/images/King"+ (GameState.choices[GameState.chooseFrom].get(i).retOwner())+".png")), 100, 125+100*i, 75, 75, null);
			}
			catch(Exception ex){}
			x.drawImage(GameState.choices[GameState.chooseFrom].get(i).retL().retImage(), 195, 125+100*i, 75, 75, null);
			x.drawImage(GameState.choices[GameState.chooseFrom].get(i).retR().retImage(), 270, 125+100*i, 75, 75, null);
		}
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(4));
		g2.drawRect(195, 125, 150, 75);
		g2.setStroke(oldStroke);
		x.setColor(new Color(51, 51, 51));
		x.drawString("PLAY", 100, 100);
		x.drawString("QUEUE", 400, 100);
		x.drawString("DOMINOES LEFT: "+GameState.setOfDominoes.size(), 745, 100);
		x.drawString("PLACING", 100 , 600);
		x.drawString("TRAVEL", 400, 600);
		if(firsthalf)
		{
			x.drawImage(GameState.choices[GameState.chooseFrom].get(0).retR().retImage(),100, 625, 175, 175, null );
		}
		else
		{
			x.drawImage(GameState.choices[GameState.chooseFrom].get(0).retL().retImage(), 100, 625, 175, 175, null );
		}
		for(int i =0; i<GameState.boards[turn].length; i++)
		{
			for(int j =0; j<GameState.boards[turn][i].length; j++)
			{
				try 
				{
					x.drawImage(GameState.boards[turn][i][j].retImage(), 751 + 75 * j, 126 + 75*i, 75, 75, null );
				}
				catch(Exception e){}
				x.drawRect(750 + 75 * j, 125 + 75*i, 75, 75);
			}
		}
		int count = 0;
		for(int i = 0; i<2; i++)
		{
			for(int j = 0; j<2; j++)
			{
				try 
				{
					x.drawImage(ImageIO.read(Board.class.getResource("/images/King"+ count+".png")), 400+100*j, 625+100*i, 75, 75, null);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				count++;
			}
		}
		System.out.println(GameState.checkPlayable(GameState.choices[GameState.chooseFrom].get(0), GameState.Whoseturn));
	}
}