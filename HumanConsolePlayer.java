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
    public static final int CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW = 5;

    public static final int CONSOLE_OUTPUT_WIDTH_OF_BOX = 17;

    public TurnInterface getTurn(Colour colour, BoardInterface board, List<Integer> diceValues) throws PauseException
    {
        return null;
    }
}
