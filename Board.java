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
        for(int i=0; i<NUMBER_OF_LOCATIONS+3; i++){

            Location l = new Location(i>locationNames.length ? "Town "+i : locationNames[i]);

            if(i == 0 || i == 25 || i == 26) // if start, end or 'knocked' location (all off the board), make location mixed
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
        this.name = (name != null) ? name : "";
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
        if(canMakeMove(colour, move))
        {
            Location sourceLocation = locations[move.getSourceLocation()];
            if(sourceLocation.canRemovePiece(colour)){
                try
                {
                    sourceLocation.removePiece(colour);
                }
                catch(IllegalMoveException e)
                {
                    throw new IllegalMoveException("Cannot remove a " + colour + " piece from location " + sourceLocation.getName());
                }
            }
            else
            {
                throw new IllegalMoveException("Cannot remove a " + colour + " piece from location " + sourceLocation.getName());
            }
        }
        else
        {
            // try-catch on moveThing() instead?
            //throw IllegalMoveException
        }
    }

    public void takeTurn(Colour colour, TurnInterface turn, List<Integer> diceValues) throws IllegalTurnException
    {
        int index = 0;
        for(Move move : turn.getMoves())
        {
            if(move.getDiceValue() != diceValues.get(index))
            {
                throw new IllegalTurnException("Die value (" + move.getDiceValue() + ") of move #" + (index+1) + " does not match the given dice value (" + diceValues.get(index) + ")");
            }
            else
            {
                
            }
            index++;
        }
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

    /**
     * @param colour The colour we know, find the other one
     * @return The other colour in the game (assuming there are only two colours), null if something went wrong
     */
    public static Colour getOtherColour(Colour colour)
    {
        // getEnumConsants() usage taken from https://www.tutorialspoint.com/java/lang/class_getenumconstants.htm, retrieved 03/04/2017
        for(Colour c : Colour.class.getEnumConstants())
        {
            if(c != colour)
            {
                return c;
            }
        }
        return null;
    }
}
