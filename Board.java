import java.util.List;
import java.util.Set;

/**
 * Board represents the board state in the game of tabula (not including dice and players).
 *
 * Requires a constructor with no parameters which creates and initialises all of the locations for the start of the game.
 *
 **/

public class Board implements BoardInterface
{
    private String name;

    // List of all the locations in game
    // Location 0 ("Newcastle-upon-Tyne") is the start location, off the board
    // Location 25 ("Durham") is the end location, off the board
    // Location 26 ("Stockton-on-Tees") is the 'knocked' location
    // Hence locations list should look like:
    //     START (0, OFF), 1 (ON), 2 (ON), ..., NUMBER_OF_LOCATIONS-1 (23, ON), NUMBER_OF_LOCATIONS (24, ON), END (25, OFF), KNOCKED (26, OFF)
    private List<LocationInterface> locations;

    private static final String[] locationNames = {
        "Newcastle-upon-Tyne",
        "Gateshead",
        "Sunderland",
        "Peterlee",
        "Hartlepool",
        "Redcar",
        "Saltburn-by-the-Sea",
        "Staithes",
        "Whitby",
        "Guisborough",
        "Middlesbrough",
        "Thornaby",
        "Darlington",
        "Barnard Castle",
        "Middleton-in-Teesdale",
        "Stanhope",
        "Alston",
        "Haltwhistle",
        "Haydon Bridge",
        "Hexham",
        "Consett",
        "Bishop Auckland",
        "Shildon",
        "Newton Aycliffe",
        "Spennymoor",
        "Durham",
        "Stockton-on-Tees"
    };

    public Board()
    {
        // Create the list of Locations
        // Location 0 is start location, off the board
        // Location 25 is end location, off the board
        // Location 26 is the 'knocked' location, off the board
        // Hence locations list should look like:
        //     START (0, OFF), 1 (ON), 2 (ON), ..., NUMBER_OF_LOCATIONS-1 (23, ON), NUMBER_OF_LOCATIONS (24, ON), END (25, OFF), KNOCKED (26, OFF)
        for(int i=0; i<NUMBER_OF_LOCATIONS + 3; i++){

            // String locName;
            // if(i > locationNames.length){
            //     locName = "Town " + i;
            // }
            // else
            // {
            //     locName = locationsNames[i];
            // }

            Location l = new Location(i>locationNames.length ? "Town "+i : locationNames[i]);

            if(i == 0 || i == 25 || i == 26) // if start, end or 'knocked' location (all off the baord), make location mixed
            {
                l.setMixed(true);
            }

            locations.add(l);

        }

        // Set the board name
        setName(name);
    }

    public void setName(String name)
    {
        this.name = name == null ? "" : name;
    }

    public LocationInterface getStartLocation()
    {
        return locations.get(0);
    }

    public LocationInterface getEndLocation()
    {
        return locations.get(NUMBER_OF_LOCATIONS + 1);
    }

    public LocationInterface getKnockedLocation()
    {
        return locations.get(NUMBER_OF_LOCATIONS + 2);
    }

    public LocationInterface getBoardLocation(int locationNumber) throws NoSuchLocationException
    {
        if(locationNumber < 1 || locationNumber > 24)
        {
            throw new NoSuchLocationException("Requested location number was out of the given range (1 to " + NUMBER_OF_LOCATIONS + ").");
        }
        else
        {
            return locations.get(locationNumber);
        }
    }


    public boolean canMakeMove(Colour colour, MoveInterface move)
    {
        return false;
    }

    public void makeMove(Colour colour, MoveInterface move) throws IllegalMoveException
    {

    }

    public void takeTurn(Colour colour, TurnInterface turn, List<Integer> diceValues) throws IllegalTurnException
    {

    }

    public boolean isWinner(Colour colour)
    {
        return false;
    }

    public Colour winner()
    {
        return null;
    }

    public boolean isValid()
    {
        return false;
    }

    public Set<MoveInterface> possibleMoves(Colour colour, List<Integer> diceValues)
    {
        return null;
    }

    public BoardInterface clone()
    {
        return null;
    }

    public String toString()
    {
        return null;
    }
}
