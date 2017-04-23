package xyz.robbie.tabula;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Game represents the game state including the board, the dice and the players
 *
 * Requires a constructor with no parameters.
 *
 * Also requires a main method which allows the user to choose the player types and start the game.
 * The main method menu should allow users to:
 *      set the players (human or computer);
 *      load a game;
 *      continue a game;
 *      save the game;
 *      start a new game;
 *      exit the program.
 *
 * If providing a GUI then the same options need to be available through the GUI.
**/

public class Game implements GameInterface
{

    private HashMap<Colour, PlayerInterface> players;
    private Colour currentColour;

    public Game()
    {
        this.players = new HashMap<Colour, PlayerInterface>();
    }

    public void setPlayer(Colour colour, PlayerInterface player)
    {
        players.put(colour, player);
    }

    public Colour getCurrentPlayer()
    {
        return currentColour;
    }

    public Colour play() throws PlayerNotDefinedException
    {
        if(getCurrentPlayer() == null){
            throw new PlayerNotDefinedException("No player has been defined!");
        }


        return null; // Should return the colour of the winner if there is one, or null if not (the game has been paused by a player)
    }

    public void saveGame(String filename) throws IOException
    {

    }

    public void loadGame(String filename) throws IOException
    {

    }

    /**
    * The main method menu should allow users to:
    *      set the players (human or computer);
    *      load a game;
    *      continue a game;
    *      save the game;
    *      start a new game;
    *      exit the program.
    * Run with -c for a command line game or -g for a GUI game ??? (look into how GUI is created)
    */
    public static void main(String[] args)
    {
        System.out.println("Welcome to Tabula North-East.");
		
        String mode = "";
        boolean preferNotV =false;
		
        if(args.length > 0){
            if(args[0].equals("-c"))
            {
                mode = "c";
                System.out.println("You have elected to start in command line mode.");
            }
            else if(args[0].equals("-g"))
            {
                mode = "g";
                System.out.println("You have elected to start in GUI mode.");
            }
			else if (args[0].equals("-dev"))
			{
				mode = "dev";
			}
            else
            {
                System.out.println("Your command line parameter was not recognised. Use -c to start in command line mode or -g to start in GUI mode. Defaulting to command line mode.");
            }

            if(args.length > 1)
            {
                if(args[1].equals("-notv"))
                {
                    preferNotV = true;
                }
            }
        }
        else
        {
            mode = "c";
            System.out.println("You did not specify whether to start in command line or GUI mode.\nDefaulting to command line mode.");
        }
        Scanner scanner = new Scanner(System.in);
        String input = "";
		
		Game g = new Game();

		if(mode.equals("g"))
		{
			// GUI play
		}
		else if(mode.equals("dev"))
		{	
				Board b = new Board();
				if(preferNotV == true)
                {
                    b.setVerticalToString(false);
                }
				
				PlayerInterface humanConsolePlayerOne = new HumanConsolePlayer();
				PlayerInterface humanConsolePlayerTwo = new HumanConsolePlayer();
				
				g.setPlayer(Colour.GREEN, humanConsolePlayerOne);
				g.setPlayer(Colour.BLUE, humanConsolePlayerTwo);
				
				
				do {
					System.out.println("Choose from the following options:");
					System.out.println("(e)xit, print all (l)ocations, print (b)oard");
					input = scanner.nextLine().toLowerCase();
					
					if(input.equals("l"))
					{
						System.out.println(b.getStartLocation());
						for(int i=1;i<BoardInterface.NUMBER_OF_LOCATIONS;i++)
						{
							try
							{
							System.out.println(b.getBoardLocation(i));
							}
							catch (NoSuchLocationException e)
							{
								// Won't ever happen
							}
						}
						System.out.println(b.getEndLocation());
						System.out.println(b.getKnockedLocation());
					}
					else if(input.equals("b"))
					{
						System.out.println(b);
					}
					
				} while (!input.equals("e"));
		}
		else // must have mode == c
		{
			// command line play
		}
		
		
    }
}
