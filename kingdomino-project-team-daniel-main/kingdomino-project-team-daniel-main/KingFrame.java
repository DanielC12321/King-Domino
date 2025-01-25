import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KingFrame extends JFrame implements KeyListener, MouseListener{
Starting startScreen;
DominoChoices gameHub;
GameState state;
Board gB = new Board();
Ending ES;
Travel travel;
int x = -1;
int y = -1;
String direction ="";
boolean travelmode = false;
boolean last4 = false;
public KingFrame()
{

	super("KingDomino");
	ES = new Ending();
	state = new GameState();
	startScreen = new Starting();
	gameHub = new DominoChoices();
	travel = new Travel();
	setSize(1500,1000);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//add(panel);
	add(startScreen);
	addKeyListener(this);
	addMouseListener(this);
	setVisible(true);
	setResizable(false);
}

@Override
public void keyTyped(KeyEvent e) {
	switch(GameState.stage) {
	case 0:
		if(e.getKeyChar()== ' ') 
		{
			GameState.stage++;
		remove(startScreen);
		repaint();
		add(gameHub);
		revalidate();
		//start screen -> gameHub
	}
		break;
	case 1:
		
		
		//confirm domino selection
		if(e.getKeyChar()== ' ') {
		if(gameHub.retC()!=0)
		{
			// will not allow player to select a domino that alr has an owner
			if(GameState.choices[GameState.chooseFrom].get(gameHub.retC()-1).retOwner()!=-1)
				break;
			
			GameState.choices[GameState.chooseFrom].get(gameHub.retC()-1).setOwner(GameState.Whoseturn);
			gameHub.setC(0);
			//sets domino owner
			if(GameState.first4 == false && !GameState.turn.isEmpty())
			{
				
				GameState.Whoseturn = GameState.turn.get(0);
				GameState.turn.remove(0);
				//if the first set of turn is empty
			}
			else if (GameState.first4 == false && GameState.turn.isEmpty()&&GameState.stage==1)
			{
				GameState.stage++;
				GameState.Whoseturn= GameState.choices[0].get(0).retOwner();
				GameState.first4 = true;
				remove(gameHub);
				repaint();
				add(gB);
				revalidate();
				break;
				//move onto placing dominoes after the first 4 turns
			}
			else if(GameState.first4)
			{
				if(GameState.chooseFrom!=0&&GameState.choices[0].isEmpty())
				{
					GameState.loadChoices(0);
					GameState.stage=2;
					GameState.Whoseturn = GameState.choices[GameState.chooseFrom].get(0).retOwner();
					remove(gameHub);
					repaint();
					add(gB);
					revalidate();
					break;
					
					
				}
				else if(GameState.chooseFrom!=0&&!GameState.choices[0].isEmpty())
				{
					
					GameState.stage=2;
					GameState.chooseFrom= 0;
					GameState.Whoseturn = GameState.choices[0].get(0).retOwner();
					remove(gameHub);
					repaint();
					add(gB);
					revalidate();
					break;
					
				}
				else if(GameState.chooseFrom==0&&GameState.choices[1].isEmpty())
				{
					GameState.loadChoices(1);
					GameState.stage=2;
					GameState.Whoseturn = GameState.choices[GameState.chooseFrom].get(0).retOwner();
					remove(gameHub);
					repaint();
					add(gB);
					revalidate();
					break;
					
				}
				else if(GameState.chooseFrom==0&&!GameState.choices[1].isEmpty())
				{
					
					GameState.stage=2;
					GameState.chooseFrom=1;
					GameState.Whoseturn = GameState.choices[1].get(0).retOwner();
					remove(gameHub);
					repaint();
					add(gB);
					revalidate();
					break;
					
				}
			}
		}
		remove(gameHub);
		repaint();
		add(gameHub);
		revalidate();
		break;
		}
		break;
	case 2:
		//if(e.getKeyChar()== 'x')
			//System.out.println(GameState.getScore(GameState.Whoseturn));
	if(travelmode)
		break;
		if(Board.firsthalf==true)
		{
			if(e.getKeyChar()=='w')
			{
				if(direction.equals("") ) {
				x--;
				try {
					Square temp = GameState.boards[GameState.Whoseturn][x][y];
				}
				catch (Exception b)
				{
					System.out.println("out of bounds north");
					Board.firsthalf=false;
					x++;
					direction = "";
					GameState.boards[GameState.Whoseturn][x][y]= null;
					x =-1;
					y = -1;
					remove(gB);
					repaint();
					add(gB);
					revalidate();
					break;
				}
				
				if(GameState.boards[GameState.Whoseturn][x][y]!=null)
				{
					Board.firsthalf=false;
					x++;
					direction = "";
					GameState.boards[GameState.Whoseturn][x][y]= null;
					x =-1;
					y = -1;
					remove(gB);
					repaint();
					add(gB);
					revalidate();
					break;
				}
				else if(direction.equals(""))
				{
					direction = "n";
					GameState.boards[GameState.Whoseturn][x][y]=GameState.choices[GameState.chooseFrom].get(0).retR();
					remove(gB);
					repaint();
					add(gB);
					revalidate();
					break;
				}
				}
				
				
			}
			if(e.getKeyChar()=='s')
			{
				if(direction.equals("") ) {
					x++;
					try {
					Square temp = GameState.boards[GameState.Whoseturn][x][y];
					}
					catch (Exception b)
					{
						
						System.out.println("out of bounds north");
						Board.firsthalf=false;
						x--;
						direction = "";
						GameState.boards[GameState.Whoseturn][x][y]= null;
						x =-1;
						y = -1;
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					
					if(GameState.boards[GameState.Whoseturn][x][y]!=null)
					{
						Board.firsthalf=false;
						x--;
						direction = "";
						GameState.boards[GameState.Whoseturn][x][y]= null;
						x =-1;
						y = -1;
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					else if(direction.equals(""))
					{
						direction = "s";
						GameState.boards[GameState.Whoseturn][x][y]=GameState.choices[GameState.chooseFrom].get(0).retR();
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					}
			}
			if(e.getKeyChar()=='a')
			{
				if(direction.equals("") ) {
					y--;
					try {
					Square temp = GameState.boards[GameState.Whoseturn][x][y];
					}
					catch (Exception b)
					{
						System.out.println("out of bounds north");
						Board.firsthalf=false;
						y++;
						direction = "";
						GameState.boards[GameState.Whoseturn][x][y]= null;
						x =-1;
						y = -1;
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					
					if(GameState.boards[GameState.Whoseturn][x][y]!=null)
					{
						Board.firsthalf=false;
						y++;
						direction = "";
						GameState.boards[GameState.Whoseturn][x][y]= null;
						x =-1;
						y = -1;
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					else if(direction.equals(""))
					{
						direction = "w";
						GameState.boards[GameState.Whoseturn][x][y]=GameState.choices[GameState.chooseFrom].get(0).retR();
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					}
				
			}
			if(e.getKeyChar()=='d')
			{
				if(direction.equals("") ) {
					y++;
					try {
					Square temp = GameState.boards[GameState.Whoseturn][x][y];
					}
					catch (Exception b)
					{
						System.out.println("out of bounds north");
						Board.firsthalf=false;
						y--;
						direction = "";
						GameState.boards[GameState.Whoseturn][x][y]= null;
						x =-1;
						y = -1;
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					
					if(GameState.boards[GameState.Whoseturn][x][y]!=null)
					{
						Board.firsthalf=false;
						y--;
						direction = "";
						GameState.boards[GameState.Whoseturn][x][y]= null;
						x =-1;
						y = -1;
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					else if(direction.equals(""))
					{
						direction = "e";
						GameState.boards[GameState.Whoseturn][x][y]=GameState.choices[GameState.chooseFrom].get(0).retR();
						remove(gB);
						repaint();
						add(gB);
						revalidate();
						break;
					}
					}
				
			}
		}
		break;
	}
	

}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent e) 
{
	switch(GameState.stage) 
	{
	//click the domino
	
	case 0:
		if (Starting.state== 0 && e.getX()>=0 && e.getY()>=0)
		{
			System.out.println("click");
			Starting.state = 1;
			remove(startScreen);
			repaint();
			add(startScreen);
			revalidate();
			break;
		}
		else if (Starting.state== 1&& e.getX()>=0 && e.getX()<=250 && e.getY()>=0 && e.getY()<=150)
		{
System.out.println("click");
			
			Starting.state = 0;
			remove(startScreen);
			repaint();
			add(startScreen);
			revalidate();
			break;
		}
		
		
		
		
		break;
		case 1:
			if(travelmode&&e.getX()>=0&&e.getX()<=250&&e.getY()>=0&&e.getY()<=150)
			{
				System.out.println("click");
				Travel.player = 0;
				travelmode = false;
				remove(travel);
				repaint();
				add(gameHub);
				revalidate();
				break;
			}
			
			else if(travelmode)
				break;
			if(!travelmode)
		{
			int count = 0;
			for(int i = 0; i<2; i++)
				for(int j = 0; j<2; j++)
				{
					if(e.getX()>=75+100*j && e.getX()<=175+100*j&&e.getY()>=getHeight()-550+100*i&&e.getY()<=getHeight()-450+100*i)
					{
						System.out.println("click");
						Travel.player = count;
						travelmode = true;
						remove(gameHub);
						repaint();
						add(travel);
						revalidate();
						break;
					}
						
					
					count++;
				}
			
		}
			
			if(e.getX() >= 1100 && e.getX() <= 1400 && e.getY() >= 50 && e.getY() <= 200)
			{
				gameHub.setC(1);
			}
			
			if(e.getX()>=1100&&e.getX()<=1400&&e.getY()>=250&&e.getY()<=400)
			{
				gameHub.setC(2);
			}
			
			if(e.getX()>=1100&&e.getX()<=1400&&e.getY()>=450&&e.getY()<=600)
			{
				gameHub.setC(3);
			}
			if(e.getX()>=1100&&e.getX()<=1400&&e.getY()>=650&&e.getY()<=800) 
			{
				gameHub.setC(4);
			}
			
		//	if(e.getX() >= 55 && e.getX() <= 145 && e.getY() >= 350 && e.getY() <= 440)
		//	{
		//		travelmode = true;
		//		//remove
		//		//static varibale in travel class
		//	}
			
			remove(startScreen);
			repaint();
			add(gameHub);
			revalidate();
			break;
			
	case 2:
		if(travelmode&&e.getX()>=0&&e.getX()<=250&&e.getY()>=0&&e.getY()<=150)
		{
			System.out.println("click");
			Travel.player = 0;
			travelmode = false;
			remove(travel);
			repaint();
			add(gB);
			revalidate();
			break;
		}
		else if(travelmode)
			break;
		if(!travelmode)
		{
			int count = 0;
			for(int i = 0; i<2; i++)
			{
				for(int j = 0; j<2; j++)
				{
					if(e.getX()>=400+100*j && e.getX()<=490+100*j && e.getY()>=650+100*i && e.getY()<=725+100*i)
					{
						System.out.println("click");
						Travel.player = count;
						travelmode = true;
						remove(gB);
						repaint();
						add(travel);
						revalidate();
						break;
					}
						
					//75+100*j, getHeight()-550+100*i, 100, 100
					count++;
				}
			}
			
		}
		if(!GameState.checkPlayable(GameState.choices[GameState.chooseFrom].get(0), GameState.Whoseturn)&&x == -1&& y==-1&& e.getX()>=870 &&e.getX()<= 950 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50)
		{if(GameState.choices[0].isEmpty()&&!GameState.choices[1].isEmpty()&&GameState.setOfDominoes.isEmpty()) {
			System.out.println("end bc 0 empty");
			last4 = true;
			Board.firsthalf=false;
			direction = "";
			GameState.choices[GameState.chooseFrom].remove(0);
			x= -1;
			y=-1;
			if(!GameState.choices[1].isEmpty())
				GameState.Whoseturn= GameState.choices[1].get(0).retOwner();
				remove(gB);
				repaint();
				if(!GameState.choices[1].isEmpty())
				add(gB);
				else if (GameState.choices[1].isEmpty()) {
					GameState.stage++;
					GameState.finishGame();
					add(ES);
				}
			revalidate();
		break;
		}
		else {
			Board.firsthalf=false;
			GameState.stage--;
			direction = "";
			GameState.choices[GameState.chooseFrom].remove(0);
			x= -1;
			y=-1;
			remove(gB);
			repaint();
			if(GameState.chooseFrom==0)
			GameState.chooseFrom++;
			else
				GameState.chooseFrom--;
			add(gameHub);
			revalidate();
			
			 break;
		}
		}
		if(!Board.firsthalf) {
			lol:
		for (int i = 0; i < 9; i++)
		{
			for (int q = 0; q < 9; q++)
			{
				if(e.getX() >= 760+75*i && e.getX() <= 835+75*i && e.getY() >= 160+75*q && e.getY() <= 235+75*q) 
				{
					if(GameState.boards[GameState.Whoseturn][q][i]==null&&GameState.checkBounds(GameState.Whoseturn, q, i)) {
					GameState.changeBoard(GameState.choices[GameState.chooseFrom].get(0).retL(), GameState.Whoseturn, q, i);
					Board.isValid = GameState.isValid(GameState.choices[GameState.chooseFrom].get(0).retL(), GameState.Whoseturn, q, i, "all");
					x = q;
					y = i;
					System.out.println(Board.isValid);
					System.out.println("Pressed");
					Board.firsthalf = true;
					remove(gB);
					repaint();
					add(gB);
					revalidate();
					
					break lol;
					}
					else
					{
						System.out.println("piece already on the tile or out of 5x5");
						break lol;
					}
				}
			}
		}
		break;
		}
		else if(Board.firsthalf)
			
		{ 
			if(direction.equals("")&&e.getX()>= 750&&e.getX()<= 825 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50 &&Board.firsthalf==true)
			{
				
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			else if(direction.equals("n")&&e.getX()>= 750&&e.getX()<= 825 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50)
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x= x +1;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			else if(direction.equals("s")&&e.getX()>= 750&&e.getX()<= 825 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50)
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =x-1;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			else if(direction.equals("w")&&e.getX()>= 750&&e.getX()<= 825 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50)
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				y =y+1;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			else if(direction.equals("e")&&e.getX()>= 750&&e.getX()<= 825 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50)
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				y =y-1;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			
			
			else if(direction.equals("n")&&e.getX()>= 975&&e.getX()<= 1075 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50) {
			System.out.print("Clicked");
			
				
				System.out.println(x+ " "+ y);
			if(!Board.isValid)
			Board.isValid = GameState.isValid(GameState.choices[GameState.chooseFrom].get(0).retR(),GameState.Whoseturn, x, y, "S");
			System.out.println(Board.isValid);
			if(Board.isValid&&GameState.checkBounds(GameState.Whoseturn, x, y))
			{if(GameState.choices[0].isEmpty()&&!GameState.choices[1].isEmpty()&&GameState.setOfDominoes.isEmpty()) {
				System.out.println("end bc 0 empty");
				last4 = true;
				Board.firsthalf=false;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x= -1;
				y=-1;
				if(!GameState.choices[1].isEmpty())
				GameState.Whoseturn= GameState.choices[1].get(0).retOwner();
				remove(gB);
				repaint();
				if(!GameState.choices[1].isEmpty())
				add(gB);
				else if (GameState.choices[1].isEmpty()) {
					GameState.stage++;
					GameState.finishGame();
					add(ES);
				}
				revalidate();
			break;
				
			}
			else {
				Board.firsthalf=false;
				GameState.stage--;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				if(GameState.chooseFrom==0)
				GameState.chooseFrom++;
				else
					GameState.chooseFrom--;
				add(gameHub);
				revalidate();
				break;
			}
				
			}
			else
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x = x+1;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			
			
		}
		//
			else if(direction.equals("s")&&e.getX()>= 975&&e.getX()<= 1075 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50) {
			System.out.print("Clicked");
			
				
				System.out.println(x+ " "+ y);
			if(!Board.isValid)
			Board.isValid = GameState.isValid(GameState.choices[GameState.chooseFrom].get(0).retR(),GameState.Whoseturn, x, y, "N");
			System.out.println(Board.isValid);
			if(Board.isValid&&GameState.checkBounds(GameState.Whoseturn, x, y))
			{if(GameState.choices[0].isEmpty()&&!GameState.choices[1].isEmpty()&&GameState.setOfDominoes.isEmpty()) {
				System.out.println("end bc 0 empty");
				last4 = true;
				Board.firsthalf=false;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x= -1;
				y=-1;
				if(!GameState.choices[1].isEmpty())
					GameState.Whoseturn= GameState.choices[1].get(0).retOwner();
					remove(gB);
					repaint();
					if(!GameState.choices[1].isEmpty())
					add(gB);
					else if (GameState.choices[1].isEmpty()) {
						GameState.stage++;
						GameState.finishGame();
						add(ES);
					}
				revalidate();
			break;
			}
			else {
				Board.firsthalf=false;
				GameState.stage--;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				if(GameState.chooseFrom==0)
				GameState.chooseFrom++;
				else
					GameState.chooseFrom--;
				add(gameHub);
				revalidate();
				break;
			}
			}
			else
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x--;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			
			
		}
			else if(direction.equals("w")&&e.getX()>= 975&&e.getX()<= 1075 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50) {
			System.out.print("Clicked");
			
				
				System.out.println(x+ " "+ y);
			if(!Board.isValid)
			Board.isValid = GameState.isValid(GameState.choices[GameState.chooseFrom].get(0).retR(),GameState.Whoseturn, x, y, "E");
			System.out.println(Board.isValid);
			if(Board.isValid&&GameState.checkBounds(GameState.Whoseturn, x, y))
			{if(GameState.choices[0].isEmpty()&&!GameState.choices[1].isEmpty()&&GameState.setOfDominoes.isEmpty()) {
				System.out.println("end bc 0 empty");
				last4 = true;
				Board.firsthalf=false;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x= -1;
				y=-1;
				if(!GameState.choices[1].isEmpty())
					GameState.Whoseturn= GameState.choices[1].get(0).retOwner();
					remove(gB);
					repaint();
					if(!GameState.choices[1].isEmpty())
					add(gB);
					else if (GameState.choices[1].isEmpty()) {
						GameState.stage++;
						GameState.finishGame();
						add(ES);
					}
				revalidate();
			break;
			}
			else {
				Board.firsthalf=false;
				GameState.stage--;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				if(GameState.chooseFrom==0)
				GameState.chooseFrom++;
				else
					GameState.chooseFrom--;
				add(gameHub);
				revalidate();
			}
			}
			else
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				y++;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			
			
		}
			else if(direction.equals("e")&&e.getX()>= 975&&e.getX()<= 1075 && e.getY()>=getHeight()-125 &&e.getY()<=getHeight()-50) {
			System.out.print("Clicked");
			
				
				System.out.println(x+ " "+ y);
			if(!Board.isValid)
			Board.isValid = GameState.isValid(GameState.choices[GameState.chooseFrom].get(0).retR(),GameState.Whoseturn, x, y, "W");
			System.out.println(Board.isValid);
			if(Board.isValid&&GameState.checkBounds(GameState.Whoseturn, x, y))
			{if(GameState.choices[0].isEmpty()&&!GameState.choices[1].isEmpty()&&GameState.setOfDominoes.isEmpty()) {
				System.out.println("end bc 0 empty");
				last4 = true;
				Board.firsthalf=false;
				direction = "";
				GameState.choices[GameState.chooseFrom].remove(0);
				x= -1;
				y=-1;
				if(!GameState.choices[1].isEmpty())
					GameState.Whoseturn= GameState.choices[1].get(0).retOwner();
					remove(gB);
					repaint();
					if(!GameState.choices[1].isEmpty())
					add(gB);
					else if (GameState.choices[1].isEmpty()) {
						GameState.stage++;
						GameState.finishGame();
						add(ES);
					}
				revalidate();
			break;
			}
			else {
			Board.firsthalf=false;
			GameState.stage--;
			direction = "";
			GameState.choices[GameState.chooseFrom].remove(0);
			x =-1;
			y = -1;
			remove(gB);
			repaint();
			if(GameState.chooseFrom==0)
			GameState.chooseFrom++;
			else
				GameState.chooseFrom--;
			add(gameHub);
			revalidate();
			}
			}
			else
			{
				direction = "";
				Board.firsthalf=false;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				y--;
				GameState.boards[GameState.Whoseturn][x][y]= null;
				x =-1;
				y = -1;
				remove(gB);
				repaint();
				add(gB);
				revalidate();
				break;
			}
			
			
		}
		}
		break;
	case 3:
		if(travelmode&&e.getX()>=0&&e.getX()<=250&&e.getY()>=0&&e.getY()<=150)
		{
			System.out.println("click");
			Travel.player = 0;
			travelmode = false;
			remove(travel);
			repaint();
			add(ES);
			revalidate();
			break;
		}
		else if(travelmode)
			break;
		if(!travelmode)
		{
			int count = 0;
			for(int i = 0; i<2; i++)
				for(int j = 0; j<2; j++)
				{
					if(e.getX()>=400+550*j && e.getX()<=500+550*j&&e.getY()>=getHeight()-600+300*i&&e.getY()<=getHeight()-500+300*i)
					{
						System.out.println("click");
						Travel.player = count;
						travelmode = true;
						remove(ES);
						repaint();
						add(travel);
						revalidate();
						break;
					}
						
					
					count++;
				}
		}
			
	}
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
public void addNotify()
{
	super.addNotify();
	requestFocus();
}
public void paintComponent()
{
	
}
}
