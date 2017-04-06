import java.util.List;
import java.util.Set;
import java.util.ArrayList;

/**
 * Board represents the board state in the game of tabula (not including dice and players).
 *
 * Requires a constructor with no parameters which creates and initialises all of the locations for the start of the game.
 *
 **/

public class Board implements BoardInterface
{

    private String name;

    // List of all the locations in game
    // Location 0 ("Start") is the start location, off the board
    // Location 1 ("Newcastle") is the first location on the board
    // Location 24 ("Durham") is the last location on the board
    // Location 25 ("Finish") is the finish location, off the board
    // Location 26 ("Stockton") is the 'knocked' location
    // Hence locations list should look like:
    //     START (0, OFF), 1 (ON), 2 (ON), ..., NUMBER_OF_LOCATIONS-1 (23, ON), NUMBER_OF_LOCATIONS (24, ON), END (25, OFF), KNOCKED (26, OFF)
    private List<LocationInterface> locations;

    private static final String[] locationNames = {
        "Start",            //  0
        "Newcastle",        //  1
        "Gateshead",        //  2
        "Sunderland",       //  3
        "Peterlee",         //  4
        "Hartlepool",       //  5
        "Redcar",           //  6
        "Saltburn",         //  7
        "Staithes",         //  8
        "Guisborough",      //  9
        "Middlesbrough",    // 10
        "Thornaby",         // 11
        "Darlington",       // 12
        "Barnard Castle",   // 13
        "Middleton",        // 14
        "Stanhope",         // 15
        "Alston",           // 16
        "Haltwhistle",      // 17
        "Haydon Bridge",    // 18
        "Hexham",           // 19
        "Consett",          // 20
        "Bishop Auckland",  // 21
        "Newton Aycliffe",  // 22
        "Spennymoor",       // 23
        "Durham",           // 24
        "Finish",           // 25
        "Stockton"          // 26 (Holding Area)
    };

    public Board()
    {
        locations = new ArrayList<LocationInterface>();

        // Create the list of Locations
        // Location 0 ("Start") is the start location, off the board
        // Location 1 ("Newcastle-upon-Tyne") is the first location on the board
        // Location 24 ("Durham") is the last location on the board
        // Location 25 ("Finish") is the finish location, off the board
        // Location 26 ("Stockton-on-Tees") is the 'knocked' location
        // Hence locations list should look like:
        //     START (0, OFF), 1 (ON), 2 (ON), ..., NUMBER_OF_LOCATIONS-1 (23, ON), NUMBER_OF_LOCATIONS (24, ON), END (25, OFF), KNOCKED (26, OFF)
        for(int i=0; i<NUMBER_OF_LOCATIONS+3; i++)
        {
            String locName = i>=locationNames.length ? "Town "+i : locationNames[i];
            System.out.println("Location #" + i + ": " + locName);

            Location l = new Location(locName);

            if(i == 0 || i == 25 || i == 26) // if start, end or 'knocked' location (all off the board), make location mixed
            {
                l.setMixed(true);
            }

            locations.add(l);

        }

        // Set the board name
        setName("North-East Board");
    }

    public void setName(String name)
    {
        this.name = (name != null) ? name : "";
    }

    public LocationInterface getStartLocation()
    {
        return locations.get(0);
    }

    public LocationInterface getEndLocation()
    {
        return locations.get(NUMBER_OF_LOCATIONS + 1);
    }

    public LocationInterface getKnockedLocation()
    {
        return locations.get(NUMBER_OF_LOCATIONS + 2);
    }

    public LocationInterface getBoardLocation(int locationNumber) throws NoSuchLocationException
    {
        if(locationNumber < 1 || locationNumber > 24)
        {
            throw new NoSuchLocationException("Requested location number was out of the given range (1 to " + NUMBER_OF_LOCATIONS + ").");
        }
        else
        {
            return locations.get(locationNumber);
        }
    }


    public boolean canMakeMove(Colour colour, MoveInterface move)
    {
        return false;
    }

    public void makeMove(Colour colour, MoveInterface move) throws IllegalMoveException
    {
        if(canMakeMove(colour, move))
        {
            LocationInterface sourceLocation = locations.get(move.getSourceLocation());
            if(sourceLocation.canRemovePiece(colour)){
                try
                {
                    sourceLocation.removePiece(colour);
                }
                catch(IllegalMoveException e)
                {
                    throw new IllegalMoveException("Cannot remove a " + colour + " piece from location " + sourceLocation.getName());
                }
            }
            else
            {
                throw new IllegalMoveException("Cannot remove a " + colour + " piece from location " + sourceLocation.getName());
            }
        }
        else
        {
            // try-catch on moveThing() instead?
            //throw IllegalMoveException
        }
    }

    public void takeTurn(Colour colour, TurnInterface turn, List<Integer> diceValues) throws IllegalTurnException
    {
        int index = 0;
        for(MoveInterface move : turn.getMoves())
        {
            if(move.getDiceValue() != diceValues.get(index))
            {
                throw new IllegalTurnException("Die value (" + move.getDiceValue() + ") of move #" + (index+1) + " does not match the given dice value (" + diceValues.get(index) + ")");
            }
            else
            {

            }
            index++;
        }
    }

    public boolean isWinner(Colour colour)
    {
        return false;
    }

    public Colour winner()
    {
        return null;
    }

    public boolean isValid()
    {
        return false;
    }

    public Set<MoveInterface> possibleMoves(Colour colour, List<Integer> diceValues)
    {
        return null;
    }

    public BoardInterface clone()
    {

        //     make new Board()
        //         (this will create new locations and set them mixed as required)
        //     transfer number of each colour counters to each location in the new board

        BoardInterface cloneBoard = new Board();

        for(int i=0; i<this.locations.size(); i++)
        {
            LocationInterface tl = null;
            LocationInterface cl = null;
            try{
                cl = cloneBoard.getBoardLocation(i); // cl = Clone Location
                tl = this.getBoardLocation(i); // This Board's Location
            }
            catch(NoSuchLocationException e)
            {
                // Will never happen as the number of locations in this and in cloneBoard are defined by BoardInterface.NUMBER_OF_LOCATIONS
                System.out.println("Error getting location " + i);
                if(cl instanceof LocationInterface)
                {
                    System.out.println("cl instanceof LocationInterface TRUE");
                }
                if(tl instanceof LocationInterface)
                {
                    System.out.println("tl instanceof LocationInterface TRUE");
                }
                continue;
            }

            // Transfer over the Location name
            cl.setName(tl.getName());

            // Transfer over whether Location is mixed (probably not necessary by default, unless this property has been manually changed for any Location)
            cl.setMixed(tl.isMixed());

            // Transfer number of pieces of each colour
            for(Colour c : Colour.values())
            {
                // Add the piece c the correct number of times
                for(int j=1; j<=tl.numberOfPieces(c); j++)
                {
                    try
                    {
                        cl.addPieceGetKnocked(c);
                    }
                    catch(IllegalMoveException e)
                    {
                        // Should never happen as tl will be valid
                        System.out.println("Error adding " + c + " on j-iteration #" + j + " to location #" + i);
                    }
                }
            }
        }
        return cloneBoard;
    }

    public String toString()
    {
        List<String> lines = new ArrayList<String>();

        /*

        [ 1] [ 2] [ 3] [ 4] [ 5] Ascending  (0)
                            [ 6] Right only (1)
        [11] [10] [ 9] [ 8] [ 7] Descending (2)
        [12]                     Left only  (3)
        [13] [14] [15] [16] [17] Ascending  (0)
                            [18] Right only (1)
        [23] [22] [21] [20] [19] Descending (2)
        [24]                     Left only  (3)

        */

        try
        {
            double rowsToDraw = Math.floor(NUMBER_OF_LOCATIONS / (HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW+1.0)) + Math.ceil(NUMBER_OF_LOCATIONS / (HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW+1.0));
            System.out.println(rowsToDraw + " rows to draw");

            // Generate entire dashed line
            String dashLine = "";
            for(int i=1; i<=HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW; i++)
            {
                dashLine += " ";
                for(int j=2; j<=HumanConsolePlayer.CONSOLE_OUTPUT_WIDTH_OF_BOX-1; j++)
                {
                    dashLine += "-";
                }
                dashLine += " ";
                if(i != HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW){ // i.e. not the last item on the row
                    dashLine += " "; // a single space
                }
            }
            dashLine += " ";

            for(int h=1; h<=rowsToDraw; h++)
            {
                // System.out.println("h: " + h);

                System.out.println("CASE (h-1)%4: " + (h-1)%4 + ", h: " + h);// + ", offset: " + offset);

                switch((h-1)%4)
                {
                    case 0: // case 0 Ascending
                    {
                        int offset = (int) (1 + (HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW + 1) * 2 * (0.25 * h - 0.25)) - 1; // Generates 1, 13, 25, ...
                        System.out.println("offset: " + offset);
                        lines.add(dashLine);
                        // System.out.println(dashLine);

                        // Draw location name line
                        String locationNameLine = "";
                        for(int i=1; i<=HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW; i++)
                        {
                            locationNameLine += "|" + this.locations.get(offset+i).getName();
                            for(int j=1; j<=HumanConsolePlayer.CONSOLE_OUTPUT_WIDTH_OF_BOX-this.locations.get(offset+i).getName().length()-2; j++)
                            {
                                locationNameLine += " ";
                            }
                            locationNameLine += "|";
                            if(i != HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW){
                                locationNameLine += " ";
                            }
                        }
                        lines.add(locationNameLine);
                        // System.out.println(locationNameLine);


                        // Loop through each colour and print the right number of them
                        for(Colour c : Colour.values())
                        {
                            String colourLine = "";
                            for(int i=1; i<=HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW; i++)
                            {
                                colourLine += "|";
                                colourLine += this.locations.get(offset+i).numberOfPieces(c);
                                colourLine += " ";
                                colourLine += c;
                                for(int j=1; j<=HumanConsolePlayer.CONSOLE_OUTPUT_WIDTH_OF_BOX-c.toString().length()-3-(""+this.locations.get(offset+i).numberOfPieces(c)).length(); j++){
                                    colourLine += " ";
                                }
                                colourLine += "|";
                                if(i != HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW){
                                    colourLine += " ";
                                }
                            }
                            lines.add(colourLine);
                            // System.out.println(colourLine);
                        }

                        // Re-add the full line of dashes
                        lines.add(dashLine);
                        // System.out.println(dashLine);

                        // Draw a blank line
                        lines.add("");

                        break;


                    } // case 0 ascending

                    case 1: // case 1 Right only
                    {

                        int offset = (int) ((HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW+1) * (h / 2.0) - 1); // Generates 6, 18, 30, ...
                        System.out.println("offset: " + offset);

                        // Draw the dashed line
                        lines.add(dashLine);


                        // Draw location name line
                        String locationNameLine = "";

                        // Draw the right number of white spaces first
                        for(int i=1; i<=(HumanConsolePlayer.CONSOLE_OUTPUT_WIDTH_OF_BOX+1)*(HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW-1); i++)
                        {
                            locationNameLine += " ";
                        }

                        // Print | and the location name
                        locationNameLine += "|" + this.locations.get(offset+1).getName() + "|";
                        lines.add(locationNameLine);
                        // System.out.println(locationNameLine);


                        // Loop through each colour and print the right number of them
                        for(Colour c : Colour.values())
                        {
                            String colourLine = "";
                            for(int i=1; i<=HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW; i++)
                            {
                                colourLine += "|";
                                colourLine += this.locations.get(offset+i).numberOfPieces(c);
                                colourLine += " ";
                                colourLine += c;
                                for(int j=1; j<=HumanConsolePlayer.CONSOLE_OUTPUT_WIDTH_OF_BOX-c.toString().length()-3-(""+this.locations.get(offset+i).numberOfPieces(c)).length(); j++){
                                    colourLine += " ";
                                }
                                colourLine += "|";
                                if(i != HumanConsolePlayer.CONSOLE_OUTPUT_NUMBER_OF_BOXES_ON_ROW){
                                    colourLine += " ";
                                }
                            }
                            lines.add(colourLine);
                            // System.out.println(colourLine);
                        }

                        // Re-add the full line of dashes
                        lines.add(dashLine);
                        // System.out.println(dashLine);

                        // Draw a blank line
                        lines.add("");


                        break;

                    } // case 1 right only
                } // switch
            } // for (int h)

            String output = "";
            for(String line : lines)
            {
                output += line + "\n";
            }
            return output;

        }
        catch(Exception e)
        {
            return e.toString();
        }

    }
}
