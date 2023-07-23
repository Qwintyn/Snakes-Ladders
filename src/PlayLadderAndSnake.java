import java.util.Scanner;
/**
 * This is a PlayLadderAndSnake class in the main class that will allow the user to play the snakes & ladder game.
 * 
 */
public class PlayLadderAndSnake {
    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // initializing scanner
        Scanner in = new Scanner(System.in);

        // prompting the user to enter the number of players to play the game 2
        System.out.print("Enter the number of players that will be need for the game: ");
        System.out.println(); 
        int numPlayers = in.nextInt();
        // if the value of numPlayer does not equal to it will display an error message
        // and exit the method
        if (numPlayers != 2) {
            System.out.println("Wrong number of players. This game is limited to 2 players to play the game.");
            System.exit(0);
        }

        // an array that will store the names of the players (i+1 because the index
        // starts at 0)
        String[] pNames = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name of player " + (i + 1) + ": ");
            pNames[i] = in.next();
        }
        /*
         * making an instance of the games which will contain two players
         * using a for loop to iterate through each player then setting their names.
         */
        LadderAndSnake game = new LadderAndSnake(2);
        for (int i = 0; i < numPlayers; i++) {
            game.positionOfPlayer[i].setName(pNames[i]);
        }
        // play game
        game.play();

    }
}
