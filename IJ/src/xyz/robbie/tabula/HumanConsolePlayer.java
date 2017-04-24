package xyz.robbie.tabula;

import java.text.NumberFormat;
import java.util.ArrayList;
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

    private Scanner scanner;
    private String input;

    public HumanConsolePlayer()
    {
        scanner = new Scanner(System.in);
        input = "";
    }

    public TurnInterface getTurn(Colour colour, BoardInterface board, List<Integer> diceValues) throws PauseException
    {
//        Scanner scanner = new Scanner(System.in);
//        String input = "";

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


        // ArrayList<Integer> dice = new ArrayList<Integer>();

        System.out.println("Enter which die value you wish to use first:");
        // Ask user for their preferred dice value
        int firstDie = askUserForNum(diceValues, "%s is not one of the values you rolled. Try again:");
        Integer sourceLocation = null;

        // Ask user which location they want to move a piece from
        System.out.println("Enter which location do you want to move a counter " + firstDie + " space" + (firstDie == 1 ? "" : "s") + " from:");
        List<Integer> locationNums = new ArrayList<Integer>();
        for(int i=1; i<BoardInterface.NUMBER_OF_LOCATIONS; i++){
            locationNums.add(i);
        }
        int firstLocation = askUserForNum(locationNums, "%s is not a valid location. Try again:");

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
