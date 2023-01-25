import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Contains the main logic part of the game, as it processes.
 * @author Vedant Nemane (vn295)
 */
public class GameLogic 
{
	/*
	 * Creates a new player object from class HumanPlayer.
	 */
	private HumanPlayer humanPlayer = new HumanPlayer();
	/*
	 * Creates a new random number object, within the given bounds.
	 */
	public static int generateRandomInt(int min, int max) 
	{
	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	/**
	 * Declares an object of the Map class with a null value.
	 */
	private Map map;
	/**
	 * Stores the file name which the user enters.
	 */
	private String fileName;
	
	/**
	 * Default constructor. Contains the code which calls functions.
	 */
	public GameLogic() 
	{
		// By declaring reader to be null, passing values to reader inside a try loop automatically resets the address for later use.
		BufferedReader reader = null;
		map = new Map();
		System.out.println("Enter a file name (the full address, including '.txt'): ");
		try 
		{
			reader = new BufferedReader(new InputStreamReader(System.in));
			fileName = reader.readLine();
			map.readMap(fileName);
			// To prevent an empty map from being read in.
			if ((map.noOfLines > 0) && (map.maxStringLength > 0))
			{
				// Notifies user of valid file address input and gives player a position in the map.
				System.out.println("**Valid map address entered- map was loaded in successfully.**");
				this.spawnPlayer();
				// Infinite loop to run while the user specifies not to exit the program.
				while (this.gameRunning())
				{
					// Strings receive input from method inside HumanPlayer class.
					String strInput = humanPlayer.getInputFromConsole();
					String instruction = humanPlayer.getNextAction(strInput);
					// Instead of an IF statement, switch case used to check user input.
					switch(instruction)
					{
						case "HELLO":
							System.out.println(this.hello());
							break;
						case "GOLD":
							System.out.println(this.gold());
							break;
						case "N":
							System.out.println(this.move(instruction.charAt(0)));
							break;
						case "E":
							System.out.println(this.move(instruction.charAt(0)));
							break;
						case "W":
							System.out.println(this.move(instruction.charAt(0)));
							break;
						case "S":
							System.out.println(this.move(instruction.charAt(0)));
							break;
						case "PICKUP":
							System.out.println(this.pickup());
							break;
						case "LOOK":
							this.look();
							break;
						case "QUIT":
							this.quitGame();
							break;
						default: 
							System.out.println("**Invalid.**");
							break;
					}
				}
			}
			else
			{
				System.out.println("**Received an empty map- you sure this is right?**");
			}
		}
		catch (IOException ex)
		{
		}
	}
	/**
	 * Gives a random position to player object which is not a gold tile or a wall.
	 */
	protected void spawnPlayer()
	{	
		/**
		 * check current tile of player code and use random number generation to determine player placement.
		 */
		boolean isValidPosition = false;
		int x = 0, y = 0;
		while (isValidPosition == false)
		{
			x = generateRandomInt(0, map.maxStringLength - 1);
			y = generateRandomInt(0, map.noOfLines - 1);
			if ((map.getMap()[y][x] != '#') && (map.getMap()[y][x] != 'G'))
			{
				isValidPosition = true;
			}
		}
		// Assigns generated values to humanPlayer object.
		humanPlayer.setIntXPlayerPosition(x);
		humanPlayer.setIntYPlayerPosition(y);
		// Assigns the character underneath player position to map attribute.
		map.setcurrPlayerTile(map.getMap()[y][x]);
	}
	

    /**
	 * Checks if the game is running.
	 *
     * @return if the game is running.
     */
    protected boolean gameRunning() 
    {
        return true;
    }

    /**
	 * Returns the gold required to win.
	 *
     * @return : Gold required to win.
     */
    protected String hello() 
    {
    	String goldRequired = ("Gold to win: " + map.getgoldRequired());
    	return goldRequired;
    }
	
	/**
	 * Returns the gold currently owned by the player.
	 *
     * @return : Gold currently owned.
     */
    protected String gold() 
    {
    	String goldOwned = ("Gold owned: " + humanPlayer.getcurrGold());
        return goldOwned;
    }

    /**
     * Checks if movement is legal and updates player's location on the map.
     *
     * @param direction : The direction of the movement.
     * @return : Protocol if success or not.
     */
    protected String move(char direction) 
    {
    	// Converts input to upper case.
    	direction = Character.toUpperCase(direction);
    	boolean movePossible = false;
    	
    	if (direction == 'N')
    	{
    		try
    		{
    			// If next position is not a wall then movePossible becomes true and player position set as such.
    			if (map.getMap()[humanPlayer.getIntYPlayerPosition() - 1][humanPlayer.getIntXPlayerPosition()] != '#')
    			{
    				movePossible = true;
    				humanPlayer.setIntYPlayerPosition(humanPlayer.getIntYPlayerPosition() - 1);
    				map.setcurrPlayerTile(map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition()]);
    			}
    			// If not, add a sarcastic remark and keep value of movePossible as false.
    			else
    			{
    				movePossible = false;
    				System.out.println("You faceplant into a wall.");
    			}
    		}
    		// If player tries to move outside of map (if map is not surrounded by walls), stop it from generating an error.
    		catch (IndexOutOfBoundsException e)
    		{
    			movePossible = false;
    			System.out.println("**Trying to move outside of the map is NOT a valid strategy!**");
    		}
    	}
    	else if (direction == 'E')
    	{
    		try
    		{
    			// If next position is not a wall then movePossible becomes true and player position set as such.
    			if (map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition() + 1] != '#')
    			{
    				movePossible = true;
    				humanPlayer.setIntXPlayerPosition(humanPlayer.getIntXPlayerPosition() + 1);
    				map.setcurrPlayerTile(map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition()]);
    			}
    			// If not, add a sarcastic remark and keep value of movePossible as false.
    			else
    			{
    				movePossible = false;
    				System.out.println("You faceplant into a wall.");
    			}
    		}
    		// If player tries to move outside of map (if map is not surrounded by walls), stop it from generating an error.
    		catch (IndexOutOfBoundsException e)
    		{
    			movePossible = false;
    			System.out.println("**Trying to move outside of the map is NOT a valid strategy!**");
    		}
    	}
    	else if (direction == 'S')
    	{
    		try
    		{
    			// If next position is not a wall then movePossible becomes true and player position set as such.
    			if (map.getMap()[humanPlayer.getIntYPlayerPosition() + 1][humanPlayer.getIntXPlayerPosition()] != '#')
    			{
    				movePossible = true;
    				humanPlayer.setIntYPlayerPosition(humanPlayer.getIntYPlayerPosition() + 1);
    				map.setcurrPlayerTile(map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition()]);
    			}
    			// If not, add a sarcastic remark and keep value of movePossible as false.
    			else
    			{
    				movePossible = false;
    				System.out.println("You faceplant into a wall.");
    			}
    		}
    		// If player tries to move outside of map (if map is not surrounded by walls), stop it from generating an error.
    		catch (IndexOutOfBoundsException e)
    		{
    			movePossible = false;
    			System.out.println("**Trying to move outside of the map is NOT a valid strategy!**");
    		}
    	}
    	else if (direction == 'W')
    	{
    		try
    		{
    			// If next position is not a wall then movePossible becomes true and player position set as such.
    			if (map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition() - 1] != '#')
    			{
    				movePossible = true;
    				humanPlayer.setIntXPlayerPosition(humanPlayer.getIntXPlayerPosition() - 1);
    				map.setcurrPlayerTile(map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition()]);
    			}
    			// If not, add a sarcastic remark and keep value of movePossible as false.
    			else
    			{
    				movePossible = false;
    				System.out.println("You faceplant into a wall.");
    			}
    		}
    		// If player tries to move outside of map (if map is not surrounded by walls), stop it from generating an error.
    		catch (IndexOutOfBoundsException e)
    		{
    			movePossible = false;
    			System.out.println("**Trying to move outside of the map is NOT a valid strategy!**");
    		}
    	}
    	else
    	{
    		System.out.println("**Invalid direction.**");
    	}
    	/**
    	 * @return : if move command was successful or not.
    	 * */
    	String output = (movePossible) ? "SUCCESS" : "FAIL";
        return output;
    }
    /**
     * Outputs 5x5 grid with player in the centre.
     */
    protected void look() 
    {
    	// Two independent counters to keep track of location within 5x5 grid.
    	// copyMapCounter1 stores Y position; copyMapCounter2 stores X position.
    	int copyMapCounter1 = 0, copyMapCounter2 = 0;
    	// Create a new 5x5 array to keep original map preserved.
        char[][] copyMap = new char[5][5];
        for (int counter1 = humanPlayer.getIntYPlayerPosition() - 2; counter1 < humanPlayer.getIntYPlayerPosition() + 3; counter1++)
        {
        	// Each iteration has to reset copy counter 2 since this is not reset automatically.
        	copyMapCounter2 = 0;
        	for (int counter2 = humanPlayer.getIntXPlayerPosition() - 2; counter2 < humanPlayer.getIntXPlayerPosition() + 3; counter2++)
        	{
        		// If the value being considered is out of the map, then output a '#' instead of throwing exception.
        		if ((counter1 < 0) || (counter2 < 0) || (counter2 >= map.maxStringLength) || (counter1 >= map.noOfLines))
        		{
        			copyMap[copyMapCounter1][copyMapCounter2] = '#';
        		}
        		else
        		{
        			copyMap[copyMapCounter1][copyMapCounter2] = map.getMap()[counter1][counter2];
        		}
        		copyMapCounter2++;
        	}
        	copyMapCounter1++;
        }
        // Make player the centre of the grid.
        copyMap[2][2] = 'P';
        //Output copyMap 5x5 grid.
        for (int i = 0; i < 5; i++)
        {
        	System.out.println("");
        	for (int j = 0; j < 5; j++)
        	{
        		System.out.print(copyMap[i][j]);
        	}
        }
        System.out.println("");
    }

    /**
     * Processes the player's pickup command, updating the map and the player's gold amount.
     *
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() 
    {
    	// String output initialised with blank string to remove erroneous/null values from returning when trying to access.
    	String output = "";
    	boolean pickupPossible = false;
    	if (map.getcurrPlayerTile() == '.')
    	{
    		//Insert sarcastic remark for trying to pick up nothing.
    		System.out.println("You picked up the air. Well done.");
    	}
    	else if (map.getcurrPlayerTile() == 'G')
    	{
    		pickupPossible = true;
    		System.out.println("You picked up gold.");
    		map.getMap()[humanPlayer.getIntYPlayerPosition()][humanPlayer.getIntXPlayerPosition()] = '.';
    		humanPlayer.setcurrGold(humanPlayer.getcurrGold() + 1);
    	}
    	else
    	{
    		// No other tile can be walked on, so E is the only tile left- impossible to pick up so insert reply.
    		System.out.println("Imagine you could just pick up an exit tile: that would be handy.");
    		System.out.println("Unfortunately you can't. Well done, you picked up air.");
    	}
    	// Output according to given format.
    	output = (pickupPossible) ? "SUCCESS. Gold owned: " + humanPlayer.getcurrGold() : "FAIL";
        return output;
    }

    /**
     * Quits the game, shutting down the application.
     */
    protected void quitGame() 
    {
    	if ((map.getgoldRequired() <= humanPlayer.getcurrGold()) && (map.getcurrPlayerTile() == 'E'))
    	{
    		System.out.println("WIN");
    		// This is the additional remark given for winning the game.
    		System.out.println("Now go do something else.");
    	}
    	else
    	{
    		System.out.println("LOSE");
    	}
    	System.exit(0);
    }
	/**
	 * Create a new GameLogic object which goes to default constructor to start program.
	 */
	public static void main(String[] args) 
	{
		GameLogic logic = new GameLogic();
    }
}