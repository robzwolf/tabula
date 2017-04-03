/**
 * Move represents the use of a single Die to move a single piece.
 *
 * Requires a constructor with no parameters.
 *
 **/

public class Move implements MoveInterface
{
    private int sourceLocationNumNum;

    public Move()
    {
        sourceLocation = -1;
    }

    public void setSourceLocation(int locationNumber) throws NoSuchLocationException
    {
        if(locationNumber < 0 || locationNumber > 24)
        {
            throw new NoSuchLocationException("That is not a valid location. Locations ")
        }
        sourceLocationNum = locationNumber;
    }




}
