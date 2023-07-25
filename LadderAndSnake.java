import java.util.Random;
/**
 * This class is a game that contains board in which the players will roll a dice 
 * and move along the board that contain snakes and ladders.
 * 
 * @author Qwitnyn Rice 
 * @version 1.0
 */
public class LadderAndSnake {
    // declaring an array of objects of type Player, used to store the position of players on the board.
    Player[] positionOfPlayer; 
    // declaring a variable players to store the number of players in the game.
    private int players; 
    // declaring a variable to keep track of the number of attempts each player makes.
    private int numAttempts;
    // delcaring two objects of type player
    Player p1;
    Player p2;

    /*
     * making a 2D array that includes the position on the board with the snakes &
     * ladders on the board.
     */
    private final static int[][] specialBoard = {
            { 38, 2, 3, 14, 5, 6, 7, 8, 31, 10 },
            { 11, 12, 13, 14, 15, 6, 17, 18, 19, 20 },
            { 42, 22, 23, 24, 25, 26, 27, 84, 29, 30 },
            { 31, 32, 33, 34, 35, 44, 37, 38, 39, 40 },
            { 41, 42, 43, 44, 45, 46, 47, 30, 49, 50 },
            { 67, 52, 53, 54, 55, 56, 57, 58, 59, 60 },
            { 61, 19, 63, 60, 65, 66, 67, 68, 69, 70 },
            { 91, 72, 73, 74, 75, 76, 77, 78, 79, 80 },
            { 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 },
            { 91, 92, 68, 94, 24, 96, 97, 76, 78, 99, 100 }
    };

    // default constructor
    public LadderAndSnake() {
        this.numAttempts = 0;
        this.players = 0;
    }
    /**
     * This method takes in two parameters, player position and player order, and
     * determines if the player's current position lands on a snake or a ladder. It
     * checks if the player has exceeded the boundary of the array and returns the
     * updated position based on the player's current position on the board. It also
     * checks if both players are at the same position, and if they are, it will reset the
     * last player's position to 0.
     * 
     * @param playerPos   represents the current position of the player.
     * @param playerOrder order of the player.
     * @return it will update the position of the player after checking for ladders,
     *         snakes, and boundaries.
     */
    public int checkPositionOnBoard(int playerPos, int playerOrder) {
        // if player position is equal to 100 then return the player position
        if (playerPos == 100) {
            return 100;
        }
        int row = playerPos / 10; // cal. the row number of the player's position by dividing the player's
                                  // position by 10.
        int col = playerPos % 10 - 1; // subtrating 1 because the board is 0-index, so the first
                                      // column has an index of 0

        // checking if both players are at the same position
        if (positionOfPlayer[0].getCurrentPosition() == positionOfPlayer[1].getCurrentPosition()) {
            return 0;
        }
        // checking if the player's position is past the size of the board & returning
        // the difference
        if (playerPos > 100) {
            return 100 - (playerPos - 100);
        }
        // if the calculated column number is equal to -1, then the column number is set
        // to 0. because the column go from 1-10 and the array index is 0-9.
        if (col == -1) {
            col = 0;
        }
        // if row is equal to 10 and col is not equal to 0, set row to 9
        if (row == 10 && col != 0)
            row = 9;
        // checking whether or not the player's position is larger than the value at the
        // corresponding spot on the board, and if so, they landed on a snake
        if (playerPos > specialBoard[row][col]) {
            System.out.println(positionOfPlayer[playerOrder].getName()
                    + " hit a snake and will now go down to position " + specialBoard[row][col]);
            return specialBoard[row][col];
        }
        // else if player's position is less than the value at the corresponding spot on
        // the board, and if so, they landed on a ladder
        else if (playerPos < specialBoard[row][col]) {
            System.out.println(positionOfPlayer[playerOrder].getName() + " hit a ladder and will now go up to position "
                    + specialBoard[row][col]);
            return specialBoard[row][col];
        }
        // if the position is equal to the spot on the board just return the player
        // position
        return playerPos;

    }   
        /**
         * This is a parameterized method that checks the number of player
         * for the game and initializes the player for the game as long 
         * as it is 2. 
         * @param players represents the number of players in the game 
         */
    public LadderAndSnake(int players) {
        // checking if the number of players is less than 2
        if (players < 2) {
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
            System.exit(0); // termination of program
        }
        // checking if the number of players is greater than 2
        if (players > 2) {
            System.out.println("Warning: The number of players for this game is limited to two.");
            // setting the number of players to 2
            this.players = 2;
        } else {
            // returning is player's position if they didn't hit a ladder or snake
            this.players = players;

        }
        // initializing an array of players
        positionOfPlayer = new Player[players];

        // initializing each player object
        for (int i = 0; i < players; i++) {
            positionOfPlayer[i] = new Player();
        }
    }

    /**
     * Accessor that will get the number of attempts
     * @return keeps track of p1 & p2 dice roll attempts
     */
    public int getNumAttempts() {
        return this.numAttempts;
    }

    /**
     * mutator that will set the number of attempts
     * 
     * @param numAttempts the number of attempts
     */
    public void setNumAttempts(int numAttempts) {
        this.numAttempts = numAttempts;
    }

    /**
     * Method that genrates a random number between 1-6 (Represents the dice)
     * 
     * @return a interger value between 1-6
     */
    public int flipDice() {
        Random r = new Random(); // making an instnace of the class random and initilaizing the random number
                                 // generator
        int ranNum = r.nextInt(6) + 1; // Added + 1 get the right range of 1-6 since nextInt would start
        return ranNum;
    }

    /**
     * This play method creates two player objects and sets their names. It then
     * determines the order of whihc player will start the game by rolling dice.
     * If values of the player's roll ends in tie, the dice is rolled again until 
     * they don't roll the same value. The game begins and continues until a player 
     * reaches the end of the board. This method also checks player's new postion on the board
     * and it will update accordingly depending on if the player landed on a snake
     * or a ladder. Once a player has landed on 100 it will declare
     * the winner and the game will end.
     */
    public void play() {
        // creating two player objects and initializing them using the player class
        // constructor
        this.p1 = new Player();
        this.p1.setName(positionOfPlayer[0].getName());
        this.p2 = new Player();
        this.p2.setName(positionOfPlayer[1].getName());

        /*
         * using a while loop to deterime the order of playing turn for the two players.
         * loop will keep going while p1 and p2 are equal to each other.
         */
        while (p1.getCurrentPosition() == p2.getCurrentPosition()) {
            this.p1.setCurrentPosition(flipDice()); // rolling the dice for player 1 and player 2
            this.p2.setCurrentPosition(flipDice());
            this.numAttempts++; // it will keep track of the number of attempts
            System.out.println(this.p1.getName() + " got a dice value of " + this.p1.getCurrentPosition());
            System.out.println(this.p2.getName() + " got a dice value of " + this.p2.getCurrentPosition() + "\n");

            if (this.p1.getCurrentPosition() == this.p2.getCurrentPosition()) {
                System.out.println("A tie was achieved between Player 1 and Player 2. Attempting to break the tie.");
            }

        }
        // if statements to check which player get a higher value based on the dice role
        // and indicates which player will go first
        if (this.p1.getCurrentPosition() > this.p2.getCurrentPosition()) {
            System.out.println("Reached final decision on order of playing: Player 1 then Player 2. It took "
                    + numAttempts + " attempts before a decision could be made.");
            positionOfPlayer[0] = this.p1; // assigning the position of player to the array depending of the results
                                           // from the if conditions
            positionOfPlayer[1] = this.p2;

        } else {
            System.out.println("Reached final decision on order of playing: Player 2 then Player 1. It took "
                    + numAttempts + " attempts before a decision could be made");
            positionOfPlayer[1] = this.p2;
            positionOfPlayer[0] = this.p1;
        }
        // START OF THE GAME. Dice roll begins

        /*
         * declaring a variable to store the results of each dice roll &
         * setting the intial position of each player to 0.
         */
        int newRoll;
        this.p1.setCurrentPosition(0);
        this.p2.setCurrentPosition(0);
        boolean keepPlaying = true;
        // using a while loop to ensure that the game is will continue as long as a
        // player has not reached 100.
        while (keepPlaying) {
            /*
             * using a for loop it iterate through the special board array to check the
             * player's position, and
             * rolling the dice and storing the result in the newRoll variable. Updating the
             * current position of the player by adding the new roll value
             */
            for (int i = 0; i < positionOfPlayer.length; i++) {
                newRoll = flipDice();
                positionOfPlayer[i].setCurrentPosition(positionOfPlayer[i].getCurrentPosition() + newRoll);
                System.out.println(
                        "\n" + positionOfPlayer[i].getName() + " rolled a " + newRoll + " and is now on square "
                                + positionOfPlayer[i].getCurrentPosition());

                // check if the player's new position is on a special square on the board
                positionOfPlayer[i]
                        .setCurrentPosition(checkPositionOnBoard(positionOfPlayer[i].getCurrentPosition(), i));
                // print out the player's new position
                System.out.println(positionOfPlayer[i].getName() + " is now on square "
                        + positionOfPlayer[i].getCurrentPosition());
                if (positionOfPlayer[i].getCurrentPosition() == 100) {
                    // setting the keepPlaying to false to stop the game loop
                    keepPlaying = false;
                    System.out.println("\n" + positionOfPlayer[i].getName() + " has won the game!");
                    break;
                }
            }

        }
    }
}
