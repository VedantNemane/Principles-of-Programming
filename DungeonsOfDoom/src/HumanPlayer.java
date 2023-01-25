import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Random;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 */
public class HumanPlayer
{
	/**
	 * currGold declared, with getter-setters.
	 */
	private int currGold;
	/**
	 * @return : currGold
	 */
	public int getcurrGold()
	{
		return currGold;
	}
	public void setcurrGold(int gold) 
	{
		currGold = gold;
	}
	
	
	private int intXPlayerPosition, intYPlayerPosition;
	/**
	 * @return : intXPlayerPosition
	 */
	public int getIntXPlayerPosition()
	{
		return intXPlayerPosition;
	}
	public void setIntXPlayerPosition(int position)
	{
		this.intXPlayerPosition = position;
	}
	/**
	 * @return : intYPlayerPosition
	 */
	public int getIntYPlayerPosition()
	{
		return intYPlayerPosition;
	}
	public void setIntYPlayerPosition(int position)
	{
		this.intYPlayerPosition = position;
	}

    /**
     * Reads player's input from the console.
     * <p>
     * @return : A string containing the input the player entered.
     */
    protected String getInputFromConsole() 
    {
    	String strInput = "";
    	BufferedReader reader;
    	try
    	{
    		reader = new BufferedReader(new InputStreamReader(System.in));
    		strInput = reader.readLine();
    		
    	}
    	catch (Exception ex)
    	{
    		System.out.println("**Unable to read input!**");
    	}
    	strInput = strInput.toUpperCase();
        return strInput;
    }

    /**
     * Processes the command. It should return a reply in form of a String, as the protocol dictates.
     * Otherwise it should return the string "INVALID".
     *
     * @return : Processed output or INVALID if the @param command is wrong.
     */
    protected String getNextAction(String strInput) 
    {
    	if (strInput.contains("HELLO"))
    	{
    		return "HELLO";
    	}
    	else if (strInput.contains("GOLD"))
    	{
    		return "GOLD";
    	}
    	else if (strInput.contains("MOVE"))
    	{
    		try
    		{
    			String temp = String.valueOf(strInput.charAt(5));
        		return temp;
    		}
    		catch (Exception ex)
    		{
    			return null;
    		}
    		
    	}
    	else if (strInput.contains("PICKUP"))
    	{
    		return "PICKUP";
    	}
    	else if (strInput.contains("LOOK"))
    	{
    		return "LOOK";
    	}
    	else if (strInput.contains("QUIT"))
    	{
    		return "QUIT";
    	}
    	else
    	{
    		return "INVALID";
    	}
    }




}