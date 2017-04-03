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
        setName(name);
        setMixed(false);
        pieces = new HashMap<Colour,Integer>();

        // Populate the pieces HashMap
        // getEnumConsants() usage taken from https://www.tutorialspoint.com/java/lang/class_getenumconstants.htm, retrieved 03/04/2017
        for(Colour colour : Colour.class.getEnumConstants())
        {
            pieces.put(colour,0);
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? "" : name;
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
        // From http://community.dur.ac.uk/s.p.bradley/teaching/IP/lecture_tabula/, retrieved 03/04/2017
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
        if(!this.isMixed())
        {
            boolean onlyThisColour = true;
            // If any other colours have a non-zero number of pieces on this location
            for(Colour c : pieces.keySet())
            {
                if(pieces.get(c) != 0 & c != colour)
                {
                    onlyThisColour = false;
                }
            }
            if(onlyThisColour){
                return true;
            }
        }

        return false;
    }

    public Colour addPieceGetKnocked(Colour colour) throws IllegalMoveException
    {
        return null;
    }

    public boolean canRemovePiece(Colour colour)
    {
        return false;
    }

    public void removePiece(Colour colour) throws IllegalMoveException
    {
        if(pieces.get(colour) == 0)
        {
            throw new IllegalMoveException("No pieces of that colour are on that location.");
        }
    }

    public boolean isValid()
    {
        return false;
    }
}
