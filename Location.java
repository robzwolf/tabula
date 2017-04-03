import java.util.*;

/**
 * Location represents a single location on the board, but not its position
 * Locations on the main part of the board may only contain a single colour of piece.
 *
 * Off-board locations, i.e. the starting location, the finishing location and the knocked-off location
 * can contain pieces of both colours and are referred to as mixed.
 *
 * Requires a constructor with one parameter (the name of the location), which creates a non-mixed location with no pieces.
 *
 */

public class Location implements LocationInterface
{

    private String name;
    private boolean mixed;
    private HashMap<Colour,Integer> pieces;

    public Location(String name)
    {
        if(name == null)
        {
            this.name = "";
        }
        else
        {
            this.name = name;
        }

        mixed = false;
        pieces = new HashMap<Colour,Integer>();

        // Populate the pieces HashMap
        for(Colour colour : Colour.getEnumConstants())
        {
            pieces.put(colour,0);
        }
    }

    public boolean isMixed()
    {
        return mixed;
    }

    public void setMixed(boolean isMixed)
    {
        mixed = isMixed;
    }

    public boolean isEmpty()
    {
        for(Integer count : pieces.values())
        {
            if(count != 0)
            {
                return false;
            }
        }

        return true;
    }

    public int numberOfPieces(Colour colour)
    {
        return pieces.get(colour);
    }

    public boolean canAddPiece(Colour colour)
    {
        /* A piece can be added if:
           - The space is empty
           - The space has counters of the same colour
           - The space has one counter of the opposite colour
               - If a counter moves to a space with one counter of the
                 opposite colour, the opposite colour, the opposition
                 counter is knocked off and is placed in a holding area
        */

        // If the space is empty
        if(this.isEmpty())
        {
            return true;
        }

        // If the space has counters of the same colour
        if(!this.isMixed() && pieces.get(colour) != 0)
        {
            return true;
        }

        // If the space has one counter of the opposite colour
        if(!this.isMixed()) && pieces.get(colour) == 0)
    }
}
