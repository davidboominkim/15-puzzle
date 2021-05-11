import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * 15 Puzzle Game. Run this file to open the GUI interface and play.
 *
 ** Inspired by Pranay Tiru, https://github.com/patturtestsite/2048
 *
 * @author David B Kim
 */
public class Game extends JPanel implements KeyListener
{
    Board game = new Board();

    static Game newGame = new Game();

    static JFrame frame = new JFrame( "15 Puzzle" );

    static Color green;

    String gameBoard = game.toString();
    
    boolean start = false;

    public static void setUpGUI()
    {
        frame.addKeyListener( newGame );
        frame.getContentPane().add( newGame );
        frame.setSize( 600, 400 );
        frame.setVisible( true );
        frame.setResizable( false );
    }


    /**
     * If a valid button is pressed, make the subsequent move on the 15 puzzle board.
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
    	// slide a tile up into the empty space.
        if ( e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP )
        {
            game.up();
            start = true;
            gameBoard = game.toString();
            frame.repaint();
        }
        // slide a tile down into the empty space
        else if ( e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN )
        {
            game.down();
            start = true;
            gameBoard = game.toString();
            frame.repaint();
        }
        // slide a tile to the left into the empty space
        else if ( e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT )
        {
            game.left();
            start = true;
            gameBoard = game.toString();
            frame.repaint();
        }
        // slide a tile to the right into the empty space
        else if ( e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            game.right();
            start = true;
            gameBoard = game.toString();
            frame.repaint();
        }
        // pressing enter will restart the game
        else if ( e.getKeyCode() == KeyEvent.VK_ENTER )
        {
            game = new Board();
            start = true;
            game.shuffle();
            frame.repaint();
        }
    }

    /**
     * This creates the instructions, title, and calls the method to create all the tiles.
     */
    public void paint( Graphics g )
    {
        super.paint( g );
        Graphics2D g2 = (Graphics2D)g;
        g2.drawString( "15 Puzzle", 250, 20 );
        g2.drawString( "Number of Moves Made: " + game.getMoves(),
            210 - 5 * String.valueOf( game.getMoves() ).length(),
            40 );
        g2.drawString( "Press 'Enter' to Start A New Game", 180, 315 );
        g2.drawString( "Use WASD or the Arrow Keys to Move Tiles into the Blank Space", 115, 335 );
        g2.setColor( Color.gray );
        g2.fillRect( 140, 50, 250, 250 );
        for ( int i = 0; i < 4; i++ )
        {
            for ( int j = 0; j < 4; j++ )
            {
                drawTiles( g, game.board[i][j], j * 60 + 150, i * 60 + 60 );
            }
        }
        if ( game.gameOver() && start )
        {
        	g2.drawString("You Did It!", 400, 150);
        	for ( int i = 0; i < 4; i++ )
            {
                for ( int j = 0; j < 4; j++ )
                {
                    drawFinishedTiles( g, game.board[i][j], j * 60 + 150, i * 60 + 60 );
                }
            }
        }
    }


    /**
     * Given a location, a tile will be drawn at x and y.
     */
    public void drawTiles( Graphics g, Tile tile, int x, int y )
    {
        int tileValue = tile.getValue();
        int length = String.valueOf( tileValue ).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor( Color.lightGray );
        g2.fillRoundRect( x, y, 50, 50, 5, 5 );
        g2.setColor( Color.black );
        if ( tileValue > 0 )
        {
            g2.setColor( tile.getColor() );
            g2.fillRoundRect( x, y, 50, 50, 5, 5 );
            g2.setColor( Color.black );
            g.drawString( "" + tileValue, x + 25 - 3 * length, y + 25 );
        }
        
    }

    /**
     * When the game is finished, the tile will light up green!
     */
    public void drawFinishedTiles( Graphics g, Tile tile, int x, int y )
    {
        int tileValue = tile.getValue();
        int length = String.valueOf( tileValue ).length();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor( Color.lightGray );
        g2.fillRoundRect( x, y, 50, 50, 5, 5 );
        g2.setColor( Color.black );
        if ( tileValue > 0 )
        {
            g2.setColor( Color.green );
            g2.fillRoundRect( x, y, 50, 50, 5, 5 );
            g2.setColor( Color.black );
            g.drawString( "" + tileValue, x + 25 - 3 * length, y + 25 );
        }
        
    }

    /**
     * Sets up the entire game.
     */
    public static void main( String[] args )
    {
        setUpGUI();
    }

}