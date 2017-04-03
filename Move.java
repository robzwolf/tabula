/**
 * Move represents the use of a single Die to move a single piece.
 *
 * Requires a constructor with no parameters.
 *
 **/

public class Move implements MoveInterface
{
    private Location sourceLocation;
    private int diceValue;

    public Move()
    {
        sourceLocation = null;
        diceValue = 0;
    }

    // ???
    public void setSourceLocation(int locationNumber) throws NoSuchLocationException
    {
        if(locationNumber < 0 || locationNumber > BoardInterface.NUMBER_OF_LOCATIONS)
        {
            throw new NoSuchLocationException("That is not a valid location. Locations must lie in the range 0 to " + BoardInterface.NUMBER_OF_LOCATIONS);
        }
        else
        {
            //sourceLocation = ;
        }

    }

    // ??? why int?
    public int getSourceLocation()
    {
        //return sourceLocation;
        return -1;
    }

    // ??? why 0-6 and not 1-6?
    public void setDiceValue(int diceValue) throws IllegalMoveException
    {
        if(0 <= diceValue && diceValue <= 6)
        {
            this.diceValue = diceValue;
        }
        else
        {
            // ???
            throw new IllegalMoveException("Dice value must be in the range 0-6");
        }
    }

    public int getDiceValue()
    {
        return diceValue;
    }



}
