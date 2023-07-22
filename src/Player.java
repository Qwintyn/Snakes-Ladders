
/**
 * This is a Player class represents a player in a game, with a name and a
 * current position.
 */
public class Player {
    // attribute to store the player's current position & name
    private int currentPosition;
    private String name;

    // default constructor, sets the player's position to 0 & name empty string
    public Player() {
        this.currentPosition = 0;
        this.name = "";
        
    }

    /**
     * it will get the current position of the player.
     * @return the current position of the player
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * setting the current position of the player.
     * @param currentPosition the new current position of the player
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * it will get the name of the player.
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * setting the name of the player.
     * @param name of the new player.
     */
    public void setName(String name) {
        this.name = name;
    }
}
