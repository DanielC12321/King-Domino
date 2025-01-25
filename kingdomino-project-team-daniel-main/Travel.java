import java.awt.Font;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Travel extends JPanel 
{
	public static int player = 0;
	
	public void paintComponent(Graphics g)
	{
		try
		{
			g.drawImage(ImageIO.read(Travel.class.getResource("/images/travel.jfif")), 0, 0, getWidth(), getHeight(), null);
			
			for(int i =0; i<GameState.boards[player].length; i++)
			{
				for(int j =0; j<GameState.boards[player][i].length; j++)
				{
					try 
					{if(GameState.boards[player][i][j]==null)
						g.drawImage(GameState.boardscopy[player][i][j].retImage(), 751 + 75 * j, 126 + 75*i, 75, 75, null );
					else
						g.drawImage(GameState.boards[player][i][j].retImage(), 751 + 75 * j, 126 + 75*i, 75, 75, null );
					}
					catch(Exception e) {
						
					}
					
					g.drawRect(750 + 75 * j, 125 + 75*i, 75, 75);
				}
			}
			
			
		}
		catch(Exception e) {}
		g.setFont(new Font ("Helvetica", Font.BOLD, 25));
		try {
		int count = 0;
		for(String x : GameState.difTerrScores.get(player).keySet()) {
			if(GameState.difTerrScores.get(player).get(x).equals("king"))
				;
			else {
			g.drawString(x+ ": "+GameState.difTerrScores.get(player).get(x), 50, 200+40*count);
			count++;
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
