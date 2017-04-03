import java.io.IOException;
import java.util.Scanner;

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

    private PlayerInterface currentPlayer;
    private Colour currentColour;

    public Game()
    {

    }

    public void setPlayer(Colour colour, PlayerInterface player)
    {
        this.currentColour = colour;
        this.currentPlayer = player;
    }

    public Colour getCurrentPlayer()
    {
        return null;
    }

    public Colour play() throws PlayerNotDefinedException
    {
        return null;
    }

    public void saveGame(String filename) throws IOException
    {

    }

    public void loadGame(String filename) throws IOException
    {

    }

    public static void main(String[] args)
    {
        System.out.println("Welcome to Tabula Durham.");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            System.out.println("Press E to exit.");
            input = scanner.nextLine();
        } while (!input.toLowerCase().equals("e"));
    }
}
