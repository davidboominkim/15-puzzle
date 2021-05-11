import java.awt.Color;


/**
 *
 * The implementation of the numbered tiles on the board. 
 * Inspired by Pranay Tiru, https://github.com/patturtestsite/2048
 *
 * @author David B Kim
 */
public class Tile
{
    int value;

    Color tileColor;


    /**
     * The default tile will have a value of zero. This will be the "blank" tile in our game.
     */
    public Tile()
    {
        value = 0;
    }


    /**
     * The other 15 tiles will have a unique number, ranging from 1-15 inclusive. 
     */
    public Tile( int number )
    {
        value = number;
    }


    /**
     * 
     * Gets the tile's value, used in the game to determine where the "blank" tile is
     * The "blank" tile always has a value of 0.
     * 
     * @return value
     */
    public int getValue()
    {
        return value;
    }


    /**
     * Changes the value of the tile.
     * In the game, this method is used to "swap" tiles' values to perform a move.
     */
    public void setValue( int value )
    {
        this.value = value;
    }


    /**
     * Represents the tile as a String
     */
    public String toString()
    {
        return "" + value;
    }


    /**
     * 
     * Sets the tile's color based on its value. I just made 2 different colors so that it's easy on the eyes.
     */
    public void setColor()
    {
        if ( this.getValue() % 2 == 0 )
        {
            tileColor = new Color(102, 178, 255);
        }
        else {
        	tileColor = new Color(255, 204, 153);
        }
    }


    /**
     * 
     * Gets the color based on setColor()
     * 
     * @return tileColor
     */
    public Color getColor()
    {
        this.setColor();
        return tileColor;
    }

}
