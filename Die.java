import java.util.Random;

/**
 * Die represents a single die.
 *
 * Implements DieInterface.
 *
 * Requires a constructor with no parameters. Initially the die has no value until it is rolled.
 *
 * A single static java.util.Random object should be the source of all randomisation.
 *
 **/

public class Die implements DieInterface
{
    private static Random randomiser;
    private int value; // 0 if unrolled

    public Die()
    {
        value = 0;
        randomiser = new Random();
    }

    public boolean hasRolled()
    {
        return value == 0;
    }

    public void roll()
    {
        value = randomiser.nextInt(NUMBER_OF_SIDES_ON_DIE) + 1; // nextInt(int bound) produces a random int between 0 (inc) and bound (exc), so add 1 to get a random int between 1 (inc) and bound (inc)
    }

    public int getValue() throws NotRolledYetException
    {
        if(hasRolled())
        {
            return value;
        }
        else
        {
            throw new NotRolledYetException("Die has not yet been rolled.");
        }
    }

    public void setValue(int value)
    {
        if(0 <= value && value <= NUMBER_OF_SIDES_ON_DIE)
        {
            this.value = value;
        }
        else
        {
            this.value = 0;
        }
    }

    public void clear()
    {
        value = 0;
    }

    public void setSeed(long seed)
    {
        randomiser.setSeed(seed);
    }
}