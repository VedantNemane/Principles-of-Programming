import java.io.BufferedReader; 
import java.io.FileReader;

/**
 * Reads and contains in memory the map of the game.
 * 
 */
public class Map {
	/**
	 * Declares private variable currPlayerTile and declare getter-setters.
	 */
	private char currPlayerTile;
	/**
	 * @return : currPlayerTile
	 */
	public char getcurrPlayerTile() 
	{
		return currPlayerTile;
	}
	public void setcurrPlayerTile(char playerTile) 
	{
		currPlayerTile = playerTile;
	}
	
	
	public int noOfLines, maxStringLength;
	/** Representation of the map 
	 */
	private char[][] map;
	/**
	 * @return : map
	 */
	public char[][] getMap()
	{
		return map;
	}
	
	/** 
	 * Map name 
	 */
	private String mapName;
	/**
	 * @return : mapName
	 */
	public String getName()
	{
		return mapName;
	}
	
	/** 
	 * Gold required for the human player to win, value never needs to change so only getter implemented. 
	 */
	private int goldRequired;
	/**
	 * @return : goldRequired
	 */
	public int getgoldRequired()
	{
		return goldRequired;
	}
	
	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() 
	{
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][]
		{
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
		};
	}
	
	/**
	 * Constructor that accepts a map to read in from.
	 * 
	 * @param : The filename of the map file.
	 */
	public Map(String fileName) 
	{
		readMap(fileName);	
	}

    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired(String fileName) 
    {
    	BufferedReader fileReader = null;
    	try 
    	{
    		fileReader = new BufferedReader(new FileReader(fileName));
    		fileReader.readLine();
    		String strSecondLine = fileReader.readLine();
    		goldRequired = Integer.parseInt(strSecondLine.substring(4, strSecondLine.length()));
    	}
    	catch (Exception ex)
    	{
    		System.out.println("**Could not read gold required. Default gold required value used.**");
    	}
        return goldRequired;
    }

    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap(String fileName) 
    {
    	BufferedReader fileReader = null;
    	
    	noOfLines = 0;
    	maxStringLength = 0;
    	String line;
    	/**
    	 * Checks the dimensions of inserted map.
    	 */
    	try 
    	{
    		fileReader = new BufferedReader(new FileReader(fileName));
    		fileReader.readLine();
    		fileReader.readLine();
    		while ((line = fileReader.readLine()) != null)
    		{
    			noOfLines = noOfLines + 1;
    			maxStringLength = (maxStringLength < line.length()) ? line.length(): maxStringLength;
    		}
    	} 
    	catch (Exception ex) {
    		/**
    		 * Sets values to default values if incorrect filename inserted.
    		 */
    		noOfLines = 9;
    		maxStringLength = 20;
    	}
    	
    	try 
    	{
    		/**
    		 * Reads map provided.
    		 */
    		fileReader = new BufferedReader(new FileReader(fileName));
    		fileReader.readLine();
    		fileReader.readLine();
    		String strCurrentLine;
    		int yCounter = 0;
    		map = new char[noOfLines][maxStringLength];
    		while((strCurrentLine = fileReader.readLine()) != null)
    		{
    			char[] charArrayCurrentLine = strCurrentLine.toCharArray(); 
    			for (int i = 0; i < strCurrentLine.length(); i++)
    			{
    				map[yCounter][i] = charArrayCurrentLine[i];
    			}
    			yCounter++;
    		}
    		
    	}
    	catch (Exception ex)
    	{
    		System.out.println("**Could not read map. Default map used.**");
    	}
        return map;
    }


    /**
     * @return : The name of the current map.
     */
    protected String getMapName(String fileName) 
    {
    	BufferedReader fileReader = null;
    	try 
    	{
    		fileReader = new BufferedReader(new FileReader(fileName));
    		String strFirstLine = fileReader.readLine();
    		mapName = strFirstLine.substring(5, strFirstLine.length());
    	} 
    	catch(Exception ex) 
    	{
    		System.out.println("**Could not read map name. Default name used.**");
    	}
        return mapName;
    }


    /**
     * Reads the map from file.
     *
     * @param : Name of the map's file.
     */
    protected void readMap(String fileName) 
    {
    	try 
    	{
    		this.goldRequired = getGoldRequired(fileName);
    		this.mapName = getMapName(fileName);
    		this.map = getMap(fileName);
    		
    	} catch (Exception ex) 
    	{
    		System.out.println("**Something went wrong.**");
    		ex.printStackTrace();
    	}
    }


}
