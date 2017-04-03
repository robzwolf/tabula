import java.util.*;

/**
 * Dice represents a pair of dice as used in tabula.
 *
 * It should use the Die class so that all randomness comes from there
 *
 * Requires a constructor with no parameters. Initially the dice have no value until they are rolled
 **/

public class Dice implements DiceInterface
{
    private Die d1;
    private Die d2;

    public Dice()
    {
        d1 = new Die();
        d2 = new Die();
    }

    public boolean haveRolled()
    {
        return d1.hasRolled() && d2.hasRolled();
    }

    public void roll()
    {
        d1.roll();
        d2.roll();
    }

    public List<Integer> getValues() throws NotRolledYetException
    {

    }
}