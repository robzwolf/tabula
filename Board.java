import java.util.List;
import java.util.Set;

/**
 * Board represents the board state in the game of tabula (not including dice and players).
 *
 * Requires a constructor with no parameters which creates and initialises all of the locations for the start of the game.
 *
 **/

public class Board //implements BoardInterface
{
    private String name;

    // List of all the locations in game
    // Location 0 ("Newcastle-upon-Tyne") is the start location, off the board
    // Location 24 ("Durham") is the last location
    // Location 25 ("Stockton-on-Tees") is the 'knocked' location
    private List<LocationInterface> locations;

    private static final String[] locationNames = {
        "Newcastle-upon-Tyne",
        "Gateshead",
        "Sunderland",
        "Peterlee"
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
        "Newton Aycliffe",
        "Spennymoor",
        "Durham",
        "Stockton-on-Tees"
    };

    public Board()
    {
        // Create the list of Locations
        // Location 0 is start location, off the board
        // Location 24 is last location on the board
        // Location 25 is the 'knocked' location
        for(i=0; i<25; i++){
            Location l = new Location(locationNames[i]);
            if(i == 0 || i == 25) // if start (off) or 'knocked' location, make location mixed
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
}
