package xyz.robbie.tabula;

import java.util.List;

/**
 * Player represents a player in the game of tabula
 *
 * Up to three different implementations of this interface can be provided: HumanConsolePlayer; ; ComputerPlayer; HumanGUIPlayer
 *
 * Each implementation requires a constructor with no parameters.
**/

public class HumanConsolePlayer implements PlayerInterface
{

    public TurnInterface getTurn(Colour colour, BoardInterface board, List<Integer> diceValues) throws PauseException
    {
        System.out.println(board);
        System.out.println("Player " + Game.strToTitleCase(colour + ", it's your turn."));
        if(diceValues.size() == 4)
        {
            System.out.println("You're lucky - you rolled a double! You dice values " + diceValues.get(0) + ", " + diceValues.get(1) + ", " + diceValues.get(2) + " and " + diceValues.get(3) + ".");
        }
        else
        {
            System.out.println("Your dice values are " + diceValues.get(0) + " and " + diceValues.get(1) + ".");
        }


        return null;
    }
}
