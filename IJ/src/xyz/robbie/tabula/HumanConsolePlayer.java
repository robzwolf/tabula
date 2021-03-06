package xyz.robbie.tabula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Player represents a player in the game of tabula
 *
 * Up to three different implementations of this interface can be provided: HumanConsolePlayer; ; ComputerPlayer; HumanGUIPlayer
 *
 * Each implementation requires a constructor with no parameters.
**/

public class HumanConsolePlayer implements PlayerInterface
{
    private static final String[] ordinalNumbers = { "first", "second", "third", "fourth" };

    private Scanner scanner;
    private String input;

    public HumanConsolePlayer()
    {
        scanner = new Scanner(System.in);
        input = "";
    }

    private String getPrettyNumbersList(List<Integer> diceValues)
    {
        String output = "";
        for (int i=0; i<diceValues.size()-2; i++)
        {
            output += diceValues.get(i) + ", ";
        }
        if(diceValues.size() >= 2)
        {
            output += diceValues.get(diceValues.size()-2) + " and " + diceValues.get(diceValues.size()-1);
        }

        return output;
//        return Arrays.toString(diceValues.toArray());
    }

    public TurnInterface getTurn(Colour colour, BoardInterface board, List<Integer> diceValues) throws PauseException
    {
        System.out.println(board);
        System.out.println("Player " + Game.strToTitleCase(colour + ", it's your turn."));
        if(diceValues.size() == 4)
        {
            System.out.println("You're lucky - you rolled a double! Your dice values are " + diceValues.get(0) + ", " + diceValues.get(1) + ", " + diceValues.get(2) + " and " + diceValues.get(3) + "!");
        }
        else
        {
            System.out.println("Your dice values are " + diceValues.get(0) + " and " + diceValues.get(1) + ".");
        }

        List<MoveInterface> moves = new ArrayList<MoveInterface>();
        for(int dieValue : diceValues)
        {
            // Ask user for their preferred dice value
            System.out.println("The dice values available to you are: " + getPrettyNumbersList(diceValues));
            System.out.println("Enter which die value you wish to use " + ordinalNumbers[moves.size()] + ":"); // when moves is empty, get ordinalNumbers[0] and so on
            int chosenDie = askUserForNum(diceValues, "%s is not one of the values you rolled. Try again:");

            // Ask user for move source location
            Integer sourceLocation = null;
            System.out.println("Enter from which location you wish to move a counter " + chosenDie + " space" + (chosenDie == 1 ? "" : "s") + ":");
            List<Integer> locationNums = new ArrayList<Integer>();
            for(int i=1; i<BoardInterface.NUMBER_OF_LOCATIONS; i++){
                locationNums.add(i);
            }
            int chosenSourceLocation = askUserForNum(locationNums, "%s is not a valid location. Try again:");

            MoveInterface calculatedMove = new Move();
            try
            {
                calculatedMove.setDiceValue(chosenDie);
                calculatedMove.setSourceLocation(chosenSourceLocation);
            }
            catch (IllegalMoveException | NoSuchLocationException e)
            {
                System.out.println("e = " + e);
            }

            moves.add(calculatedMove);
        }

        return null;
    }

    /**
     * Repeatedly ask the user for a specific number out of a list until they give a valid response.
     * @param allowableValues List of values from which the user should choose
     * @param errMessage Message to print if the user does not enter an acceptable value. Use %s to refer to user's input.
     * @return The (valid) number which was chosen by the user.
     */
    private int askUserForNum(List<Integer> allowableValues, String errMessage)
    {
        Integer chosenNum = null;
        do
        {

            input = scanner.nextLine().toLowerCase();

            // Check if the user entered a number or something else
            try
            {
                chosenNum = Integer.parseInt(input);
                // Check user entered a value that is contained in allowableValue
                if(!allowableValues.contains(Integer.parseInt(input))) // user hasn't entered a valid value
                {
                    System.out.println(String.format(errMessage,input));
                    chosenNum = null;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("'" + input + "' is not a valid number. Try again:");
            }
        } while(chosenNum == null);

        return chosenNum;
    }

}
