import java.util.concurrent.ThreadLocalRandom;
/**
 * Inspired by Pranay Tiru, https://github.com/patturtestsite/2048
 * 
 * @author David B Kim
 */
public class Board
{
    public Tile[][] board;

    int grids = 4;
    
    int moves = 0;


    /**
     * The board is a 2D square matrix of size 4 to accommodate for 15 numbered tiles and 1 blank tile.
     */
    public Board()
    {
        board = new Tile[4][4];
        int count = 1;
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                board[i][j] = new Tile(count);
                count++;
                if (count >= 16) {
                	count = 0;
                }
            }
        }
    }

    /**
     * 
     * Getter method that returns the board
     * 
     * @return board
     */
    public Tile[][] getBoard()
    {
        return board;
    }


    /**
     * 
     * Getter method that returns the number of moves made so far.
     * 
     * @return moves
     */
    public int getMoves()
    {
        return moves;
    }


    /**
     * 
     * Prints out the board on the console - for testing purposes
     * Method entirely from Pranay Tiru
     */
    public void print()
    {
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                String s = board[i][j].toString() + " ";
                System.out.print( s );
            }
            System.out.println( "" );
        }
        
    }

    /**
     * Mixes up the tiles to start the game.
     */
    public void shuffle() {
    	int ran = ThreadLocalRandom.current().nextInt(61,201);
    	String prev = "";
    	for (int i = 0; i < ran; i++) {
    		int ran2 = ThreadLocalRandom.current().nextInt(1,5);
    		if (ran2 == 1) {
    			if (prev.equals("down")) {
    				this.left();
    				
    			}
    			this.up();
    			prev = "up";
    		}
    		else if (ran2 == 2) {
    			if (prev.equals("up")) {
    				this.right();
    				
    			}
    			this.down();
    			prev = "down";
    		}
    		else if (ran2 == 3) {
    			if (prev.equals("right")) {
    				this.up();
    			}
    			this.left();
    			prev = "left";
    		}
    		else {
    			if (prev.equals("left")) {
    				this.down();
    			}
    			this.right();
    			prev = "right";
    		}
    	}
    	moves = 0;
    }
    
    /**
     * Returns the board as a String - used in the GUI
     */
    public String toString()
    {
        String s = "";
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                s += board[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * We know the game is over when all the tiles are 
     * organized in ascending order, from left to right and top to bottom.
     */
    public boolean gameOver()
    {
    	int intended = 0;
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board.length; j++) {
        		intended ++;
        		if (board[i][j].getValue() != intended) {
        			return false;
        		}
        		if (intended == 15) {
        			return true;
        		}
        	}
        }
        return true;
    }


    /**
     * 
     * This method is called when a 'w' or up arrow is pressed - goes through
     * the entire board and finds the blank tile. If the blank tile has a valid tile below it,
     * their values will be swapped.
     */
    public void up()
    {
        for ( int i = 0; i < grids-1; i++ )
        {
            for ( int j = 0; j < grids; j++ )
            {
                if ( board[i][j].getValue() == 0 )
                {
                	int val = board[i+1][j].getValue();
                	board[i][j].setValue(val);
                	board[i+1][j].setValue(0);
                	moves++;
                	return;
                }
            }
        }
    }


    /**
     * 
     * This method is called when a 's' or down arrow is pressed - goes through
     * the entire board and finds the blank tile. If the blank tile has a valid tile above it,
     * their values will be swapped.
     */
    public void down()
    {
        for ( int i = 1; i < grids; i++ )
        {
            for ( int j = 0; j <grids; j++ )
            {
                if ( board[i][j].getValue() == 0 )
                {
                    int val = board[i-1][j].getValue();
                    board[i][j].setValue(val);
                    board[i-1][j].setValue(0);
                    moves++;
                    return;
                }
            }
        }
    }

    /**
     * 
     * This method is called when an 'a' or left arrow is pressed - goes through
     * the entire board and finds the blank tile. If the blank tile has a valid tile to its right,
     * their values will be swapped.
     */
    public void left()
    {
        for ( int i = 0; i < grids; i++ )
        {
            for ( int j = 0; j < grids-1; j++ )
            {
                if ( board[i][j].getValue() == 0 )
                {
                	int val = board[i][j+1].getValue();
                    board[i][j].setValue(val);
                    board[i][j+1].setValue(0);
                    moves++;
                    return;
                }
            }
        }
    }


    /**
     * 
     * This method is called when a 'd' or right arrow is pressed - goes through
     * the entire board and finds the blank tile. If the blank tile has a valid tile to its left,
     * their values will be swapped.
     */
    public void right()
    {
        for ( int i = 0; i < grids; i++)
        {
            for ( int j = 1; j <grids; j++)
            {
                if ( board[i][j].getValue() == 0 )
                {
                	int val = board[i][j-1].getValue();
                    board[i][j].setValue(val);
                    board[i][j-1].setValue(0);
                    moves++;
                    return;
                }
            }
        }
    }



}
