import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class GameState 
{
	public static ArrayList<Integer> turn = new ArrayList<Integer>(Arrays.asList(0, 1 ,2 ,3)); 
	public static int stage;
	public static boolean first4 = false;
	public static ArrayList<Domino>[] choices;
	public static Stack<Domino> setOfDominoes = new Stack<>();
	public static int chooseFrom = 0;//determines which arraylist of domino choices is being displayed
	public static int Whoseturn = 0; //which domino is chosen
	public static Square[][][] boards = new Square[4][9][9];
	public static int[] territories = {0, 0, 0, 0};
	public static Map<Integer, TreeMap<String, Integer>> difTerrScores = new TreeMap<Integer, TreeMap<String, Integer>>();
	static int[][] egameinf;
	public static Square[][][] boardscopy = new Square[4][9][9];
	@SuppressWarnings("unchecked")
	public GameState() 
	{
		choices = (ArrayList<Domino>[]) new ArrayList[2];
		choices[0]=(new ArrayList<Domino>());
		choices[1]=(new ArrayList<Domino>());
		createDominoes();
		loadChoices(0);
		loadChoices(1);
		Collections.shuffle(turn);
		Whoseturn = turn.get(0);
		turn.remove(0);
		setBoards();
		egameinf = new int[2][4];
	}
	public void setBoards()
	{
		try 
		{
			for (int i = 0; i < 4; i++)
			{
				boards[i][4][4] = new Square("king", 0, ImageIO.read(GameState.class.getResource("/images/KP" + i + ".png")));
			}		
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void changeBoard(Square sq, int king, int row, int col)
	{
		boards[king][row][col] = sq;
	}
	
	public static boolean isValid(Square sq, int king, int row, int col, String Ex)
	{
		String str = sq.retType();
		switch(Ex) 
		{
			case "N":
				try 
				{
					if (boards[king][row+1][col] != null && (boards[king][row+1][col].retType().equals(str) || boards[king][row+1][col].retType().equals("king"))) 
					{
						System.out.println("Bot true");
						return true;		
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds bot");
				}
			
				try 
				{
					if (boards[king][row][col+1] != null && (boards[king][row][col+1].retType().equals(str) || boards[king][row][col+1].retType().equals("king")))
					{
						System.out.println("right true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds right");
				}
			
				try 
				{
					if (boards[king][row][col-1] != null && (boards[king][row][col-1].retType().equals(str) || boards[king][row][col-1].retType().equals("king")))
					{
						System.out.println("left true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds left");	
				}
				break;
				
			case "S":
				try 
				{
					if (boards[king][row-1][col] != null && (boards[king][row-1][col].retType().equals(str) || boards[king][row-1][col].retType().equals("king")))
					{
						System.out.println("Top true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds top");
				}
			
				try
				{
					if (boards[king][row][col+1] != null && (boards[king][row][col+1].retType().equals(str) || boards[king][row][col+1].retType().equals("king")))
					{
						System.out.println("right true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds right");
				}
			
				try
				{
					if (boards[king][row][col-1] != null && (boards[king][row][col-1].retType().equals(str) || boards[king][row][col-1].retType().equals("king")))
					{
						System.out.println("left true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds left");
				}
				break;
			
			case "E":
				try 
				{
					if (boards[king][row+1][col] != null && (boards[king][row+1][col].retType().equals(str) || boards[king][row+1][col].retType().equals("king"))) 
					{
						System.out.println("Bot true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds bot");
				}
						
				try
				{
					if (boards[king][row-1][col] != null && (boards[king][row-1][col].retType().equals(str) || boards[king][row-1][col].retType().equals("king")))
					{
						System.out.println("Top true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds top");
				}
			
				try
				{
					if (boards[king][row][col-1] != null && (boards[king][row][col-1].retType().equals(str) || boards[king][row][col-1].retType().equals("king")))
					{
						System.out.println("left true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds left");
				}
				break;
				
			case "W":
				try 
				{
					if (boards[king][row+1][col] != null && (boards[king][row+1][col].retType().equals(str) || boards[king][row+1][col].retType().equals("king"))) 
					{
						System.out.println("Bot true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds bot");
				}
			
				try
				{
					if (boards[king][row-1][col] != null && (boards[king][row-1][col].retType().equals(str) || boards[king][row-1][col].retType().equals("king")))
					{
						System.out.println("Top true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds top");
				}
			
				try 
				{
					if (boards[king][row][col+1] != null && (boards[king][row][col+1].retType().equals(str) || boards[king][row][col+1].retType().equals("king")))
					{
						System.out.println("right true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds right");
				}
				break;
			
			default:
				try 
				{
					if (boards[king][row+1][col] != null && (boards[king][row+1][col].retType().equals(str) || boards[king][row+1][col].retType().equals("king"))) 
					{
						System.out.println("Bot true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds bot");
				}
			
				try 
				{
					if (boards[king][row-1][col] != null && (boards[king][row-1][col].retType().equals(str) || boards[king][row-1][col].retType().equals("king")))
					{
						System.out.println("Top true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds top");						
				}
			
				try 
				{
					if (boards[king][row][col+1] != null && (boards[king][row][col+1].retType().equals(str) || boards[king][row][col+1].retType().equals("king")))
					{
						System.out.println("right true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds right");
				}
									
				try 
				{
					if (boards[king][row][col-1] != null && (boards[king][row][col-1].retType().equals(str) || boards[king][row][col-1].retType().equals("king")))
					{
						System.out.println("left true");
						return true;
					}
				}
				catch(Exception e)
				{
					System.out.println("out of bounds left");
				}
				break;
		}
		return false;
	}

	@SuppressWarnings("unchecked")

	public static void loadChoices(int x)
	{
		stage = 0;
		if(setOfDominoes.isEmpty())
		{
			System.out.println("Empty");
		}
		else
		for(int i = 0; i<4; i++)
		{
			Domino temp = setOfDominoes.pop();
			if(choices[x].isEmpty()||choices[x].get(choices[x].size()-1).retNum()<temp.retNum())
			{
				choices[x].add(temp);
			}
			else
			{
				for(int j = 0; j<choices[x].size(); j++)
				{
					if(choices[x].get(j).retNum()>temp.retNum()) {
						choices[x].add(j, temp);
						break;
					}
				}
			}
		}
	}
	
	public void createDominoes()
	{
		
		Square[][] tiles = new Square[4][6];
		try
		{
			for (int x = 0; x < 4; x++)
			{
				if (x < 2)
				{
					tiles[x][0] = new Square ("mountain", x, ImageIO.read(GameState.class.getResource("/images/mountains" + x + ".png")));
					tiles[x][1] = new Square ("forest", x, ImageIO.read(GameState.class.getResource("/images/forest" + x + ".png")));
					tiles[x][2] = new Square ("iceberg", x, ImageIO.read(GameState.class.getResource("/images/icebergs" + x + ".png")));
				}
				if (x < 3)
				{
					tiles[x][3] = new Square ("grassland", x, ImageIO.read(GameState.class.getResource("/images/grassland" + x + ".png")));
					tiles[x][4] = new Square ("desert", x, ImageIO.read(GameState.class.getResource("/images/desert" + x + ".png")));
				}
				tiles[x][5] = new Square ("sea", x, ImageIO.read(GameState.class.getResource("/images/sea" + x + ".png")));
			}
		}
		catch (IOException e) {}
		
		for (int x = 1; x < 49; x++)
		{
			switch(x) 
			{
				case 1:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[0][0], x));
					break;
			
				case 2:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[0][0], x));
					break;
			
				case 3:
					setOfDominoes.add(new Domino (tiles[0][1], tiles[0][1], x));	
					break;
			
				case 4:
					setOfDominoes.add(new Domino (tiles[0][1], tiles[0][1], x));
					break;
			
				case 5:
					setOfDominoes.add(new Domino (tiles[0][1], tiles[0][1], x)); 	
					break;
			
				case 6:
					setOfDominoes.add(new Domino (tiles[0][1], tiles[0][1], x)); 
					break;
			
				case 7:
					setOfDominoes.add(new Domino (tiles[0][2], tiles[0][2], x));
					break;
			
				case 8:
					setOfDominoes.add(new Domino (tiles[0][2], tiles[0][2], x));
					break;
			
				case 9:
					setOfDominoes.add(new Domino (tiles[0][2], tiles[0][2], x));
					break;
			
				case 10:
					setOfDominoes.add(new Domino (tiles[0][3], tiles[0][3], x));
					break;
			
				case 11:
					setOfDominoes.add(new Domino (tiles[0][3], tiles[0][3], x));
					break;
			
				case 12:
					setOfDominoes.add(new Domino (tiles[0][4], tiles[0][4], x));
					break;
			
				case 13:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[0][1], x));
					break;
			
				case 14:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[0][2], x));
					break;
			
				case 15:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[0][3], x));
					break;
			
				case 16:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[0][4], x));
					break;
			
				case 17:
					setOfDominoes.add(new Domino (tiles[0][1], tiles[0][2], x));
					break;
			
				case 18:
					setOfDominoes.add(new Domino (tiles[0][1], tiles[0][3], x));
					break;
			
				case 19:
					setOfDominoes.add(new Domino (tiles[1][0], tiles[0][1], x));
					break;
			
				case 20:
					setOfDominoes.add(new Domino (tiles[1][0], tiles[0][2], x));
					break;
			
				case 21:
					setOfDominoes.add(new Domino (tiles[1][0], tiles[0][3], x));
					break;
			
				case 22:
					setOfDominoes.add(new Domino (tiles[1][0], tiles[0][4], x));
					break;
			
				case 23:
					setOfDominoes.add(new Domino (tiles[1][0], tiles[0][5], x));
					break;
			
				case 24:
					setOfDominoes.add(new Domino (tiles[1][1], tiles[0][0], x));
					break;
			
				case 25:
					setOfDominoes.add(new Domino (tiles[1][1], tiles[0][0], x));
					break;
			
				case 26:
					setOfDominoes.add(new Domino (tiles[1][1], tiles[0][0], x));
					break;
			
				case 27:
					setOfDominoes.add(new Domino (tiles[1][1], tiles[0][0], x));
					break;
			
				case 28:
					setOfDominoes.add(new Domino (tiles[1][1], tiles[0][2], x));
					break;
			
				case 29:
					setOfDominoes.add(new Domino (tiles[1][1], tiles[0][3], x));
					break;
			
				case 30:
					setOfDominoes.add(new Domino (tiles[1][2], tiles[0][0], x));
					break;
			
				case 31:
					setOfDominoes.add(new Domino (tiles[1][2], tiles[0][0], x));
					break;
			
				case 32:
					setOfDominoes.add(new Domino (tiles[1][2], tiles[0][1], x));
					break;
			
				case 33:
					setOfDominoes.add(new Domino (tiles[1][2], tiles[0][1], x));
					break;
			
				case 34:
					setOfDominoes.add(new Domino (tiles[1][2], tiles[0][1], x));
					break;
			
				case 35:
					setOfDominoes.add(new Domino (tiles[1][2], tiles[0][1], x));
					break;
			
				case 36:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[1][3], x));
					break;
			
				case 37:
					setOfDominoes.add(new Domino (tiles[0][2], tiles[1][3], x));
					break;
			
				case 38:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[1][4], x));
					break;
			
				case 39:
					setOfDominoes.add(new Domino (tiles[0][3], tiles[1][4], x));
					break;
			
				case 40:
					setOfDominoes.add(new Domino (tiles[1][5], tiles[0][0], x));
					break;
			
				case 41:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[2][3], x));
					break;
			
				case 42:
					setOfDominoes.add(new Domino (tiles[0][2], tiles[2][3], x));
					break;
			
				case 43:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[2][4], x));
					break;
			
				case 44:
					setOfDominoes.add(new Domino (tiles[0][3], tiles[2][4], x));
					break;
			
				case 45:
					setOfDominoes.add(new Domino (tiles[2][5], tiles[0][0], x));
					break;
			
				case 46:
					setOfDominoes.add(new Domino (tiles[0][4], tiles[2][5], x));
					break;
			
				case 47:
					setOfDominoes.add(new Domino (tiles[0][4], tiles[2][5], x));
					break;
			
				case 48:
					setOfDominoes.add(new Domino (tiles[0][0], tiles[3][5], x));
					break;
			}
		}
		Collections.shuffle(setOfDominoes); //uncomment after checking
	}
	//takes in a whole domino and player and check if the domino can be played works with checkBounds to see if it can be played and in bounds
	public static boolean checkPlayable(Domino x, int y)
	{
		Square check;
		for(int k = 0; k<2; k++) {
			if(k==0)
		    check = x.retL();
			else
			check = x.retR();
		for(int i = 0; i<boards[y].length; i++)
			for(int j = 0; j<boards[y][i].length; j++)
			{
				if(boards[y][i][j]==null)
				{
					if(!checkBounds(y,i,j)||!isValid(check, y, i, j, "all"))
						continue;
					try {
					if(checkBounds(y, i+1, j)&&boards[y][i+1][j]==null)
						return true;
					}
					catch(Exception e)
					{
						System.out.println("S check out of bounds");
					}
					try {
					if(checkBounds(y, i-1, j)&&boards[y][i-1][j]==null)
						return true;
					}
					catch(Exception e)
					{
						System.out.println("N check out of bounds");
					}
					try {
					if(checkBounds(y, i, j+1)&&boards[y][i][j+1]==null)
						return true;
					}
					catch(Exception e)
					{
						System.out.println("E check out of bounds");
					}
					try {
					if(checkBounds(y, i, j-1)&&boards[y][i][j-1]==null)
						return true;
					}
					catch(Exception e)
					{
						System.out.println("W check out of bounds");
					}
				}
			}
		}
		
		
		
		return false;
		
	}
	//takes domino x represents row of left side of domino y represents col of left side of domino direction represents direction which the second half is placed N for north E for east etc.
	public static boolean checkBounds(int king, int row, int col)
	{
		int rb = -1;
		int rs = 54;
		int cb = -1;
		int cs = 54;
		boolean loadtiles = false;
		int[][] checker = new int[9][9];
		checker[row][col]= 1;
		for(int k = 0; k<2; k++) {
		for(int i = 0; i <checker.length;i++)
		{
			for(int j =0; j<checker.length; j++)
			{
				if(loadtiles&&checker[i][j]!=0)
				{
					if(i>rb)
						rb = i;
					if(i<rs)
					rs = i;
					if(j>cb)
						cb = j;
					if(j<cs)
						cs = j;
				}
				else
				{
					if(boards[king][i][j]!=null)
					{
						checker[i][j] = 1;
					}
				}
			}
		}
		loadtiles = true;
		}
		
		int height = rb-rs+1;
		int width = cb-cs+1;
		if(height>5||width>5)
		{
		return false;
		}
		else {
			return true;
		}
	}
	static int crowns4Checking = 0;
	public static int getScore (int x)
	{
		int tiles = 0;
		String type = "";
		
		int score = 0;
		
		if (checkTMK(x))
			score += 10;
		if (checkHarmony(x))
			score += 5;
		
		difTerrScores.put(x, new TreeMap<String, Integer>());
		
		while(!isitEmpty(x)) {
		lol:
		for(int j = 0; j<9; j++)
			for(int k = 0; k<9; k++)
			{
				if(boards[x][j][k]!=null)
				{
					type = boards[x][j][k].retType();
					crowns4Checking+=boards[x][j][k].retCrowns();
					tiles++;
					boards[x][j][k]= null;
					tiles +=checkadjacent(x, type,j, k+1);
					tiles +=checkadjacent(x, type,j+1, k);
					break lol;
				}
			}
		
		if (difTerrScores.get(x).containsKey(type))
			difTerrScores.get(x).put(type, difTerrScores.get(x).get(type) + tiles*crowns4Checking);
		else 
			difTerrScores.get(x).put(type, tiles*crowns4Checking);
		
		 score += tiles*crowns4Checking;
		 
		 if (territories[x] < tiles)
			 territories[x] = tiles;
		 
		 crowns4Checking = 0;
		 type = "";
		 tiles = 0;
		}
		
		return score;
		
		}
	public static int checkadjacent (int king,String type, int row, int col)
	{
		try {
		if(boards[king][row][col]==null)
			return 0;
		}
		catch(Exception e) {
			return 0;
		};
		 if (boards[king][row][col].retType().equals(type))
		{
			crowns4Checking+=boards[king][row][col].retCrowns();
			boards[king][row][col]=null;
			return 1 + checkadjacent(king, type, row+1, col)+checkadjacent(king, type, row-1, col)+checkadjacent(king, type, row, col+1)+checkadjacent(king, type, row, col-1);
		}
		else
			return 0;
		
		
		}
	public static boolean isitEmpty (int x)
	{
		for(int i= 0; i<9; i++)
			for(int j= 0; j<9; j++)
				if(boards[x][i][j]!=null)
					return false;
		return true;
		}
	public static void finishGame()
	{for(int i = 0; i <4; i++) {
for(int j = 0; j <9; j++)
for(int k = 0; k <9; k++)
{
	try {
		boardscopy[i][j][k] = (Square) boards[i][j][k].clone();
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
}
egameinf[0][i] = getScore(i);
	}

		
		int count = 1;
		int biggest = 0;
		int index = 0;
		for(int j = 0; j<4; j++) {
		for(int i = 0; i<4; i++)
		{
			if(egameinf[1][i]!=0)
				continue;
			else if(egameinf[0][i] == biggest)
			{
				if (territories[i] > territories[index])
					index = i;
			}
			else if(egameinf[0][i]>biggest)
			{
				biggest = egameinf[0][i];
				index = i;
			}
		}
		egameinf[1][index]= count;
		count++;
		biggest = 0;
		}
	}
	
	
	public static boolean checkTMK (int king)
	{
		int x = 10, y = 10;
		
		for (int i = 0; i < 9; i++)
		{
			for (int q = 0; q < 9; q++)
			{
				if (boards[king][i][q] != null)
				{
					x = i;
					break;
				}
			}
			if (x != 10)
				break;
		}
		
		for (int i = 0; i < 9; i++)
		{
			for (int q = 0; q < 9; q++)
			{
				if (boards[king][q][i] != null)
				{
					y = i;
					break;
				}
			}
			if (y != 10)
				break;
		}
		
		if (x != 10 && y != 10 && boards[king][x+2][y+2].retType().equals("king"))
			return true;
		return false;
	}
	
	public static boolean checkHarmony(int king)
	{
		int x = 10, y = 10;
		for (int i = 0; i < 9; i++)
		{
			for (int q = 0; q < 9; q++)
			{
				if (boards[king][i][q] != null)
				{
					x = i;
					break;
				}
			}
			if (x != 10)
				break;
		}
		
		for (int i = 0; i < 9; i++)
		{
			for (int q = 0; q < 9; q++)
			{
				if (boards[king][q][i] != null)
				{
					y = i;
					break;
				}
			}
			if (y != 10)
				break;
		}
		
		for (int i = 0; i < 5; i++)
		{
			for (int q = 0; q < 5; q++)
			{
				if (boards[king][x+i][y+q] == null)
					return false;
			}
		}
		
		return true;
	}
		
	

}
