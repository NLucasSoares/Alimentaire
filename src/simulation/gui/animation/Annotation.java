package simulation.gui.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import simulation.math.point.Point;
import simulation.time.Time;

/**
 * A moving annotation which display short message
 * during given time
 * (text animation, going up with alpha fading
 *
 * @author SOARES Lucas
 */
public class Annotation
{
	/**
	 * The vertical difference being applied at the end of
	 * annotation animation (px)
	 */
	private static final int FINAL_VERTICAL_DIFFERENCE = 10;
	
	/**
	 * Overload limit
	 */
	private static final int OVERLOAD_LIMIT = 5;
	
	/**
	 * The initial alpha for a message
	 */
	private static final int INITIAL_ALPHA_STATE = 0xFF;
	
	/**
	 * Alpha decreasing speed
	 */
	private static final int ALPHA_DECREASING_SPEED = 10;
	
	/**
	 * The color
	 */
	private int color;
	
	/**
	 * The messages queue
	 */
	private ArrayList<String> messageQueue;
	
	/**
	 * The position of the annotation
	 */
	private Point<Integer> position;
	
	/**
	 * Alpha reduction delay
	 */
	private int alphaReductionDelay;
	
	/**
	 * Current alpha state
	 */
	private int alphaState;
	
	/**
	 * Current vertical position modification state
	 */
	private double verticalPositionState;
	
	/**
	 * Last update
	 */
	private long lastUpdate;
	
	/**
	 * Construct the annotation
	 * 
	 * @param messageTime
	 * 		Time for single message to be displayed
	 */
	public Annotation( int alphaReductionDelay,
		int color )
	{
		// Save
			// Alpha reduction delay
				this.alphaReductionDelay = alphaReductionDelay;
			// Color
				this.color = color;
		
		// Init
		this.position = new Point<Integer>( 0,
			0 );
		this.messageQueue = new ArrayList<String>( );
		
		// Reset state
		this.resetState( );
	}
	
	/**
	 * Reset the annotation state
	 */
	public void resetState( )
	{
		this.alphaState = Annotation.INITIAL_ALPHA_STATE;
		this.verticalPositionState = 0;
		this.lastUpdate = 0;
	}
	
	/**
	 * Add a message to queue
	 * 
	 * @param message
	 *		The message to add
	 */
	public void addMessage( String message )
	{
		// Add
		this.messageQueue.add( message );
	}
	
	/**
	 * Set the color
	 * 
	 * @param color
	 * 		The color to set
	 */
	public void setColor( int color )
	{
		this.color = color;
	}
	
	/**
	 * @return if message in queue
	 */
	public boolean isMessageQueued( )
	{
		return this.messageQueue.size( ) > 0;
	}
	
	/**
	 * @return the position
	 */
	public Point<Integer> getPosition( )
	{
		return this.position;
	}
	
	/**
	 * Set the position
	 * 
	 * @param position
	 * 		The position to set
	 */
	public void setPosition( Point<Integer> position )
	{
		this.position = position;
	}
	
	/**
	 * Update
	 */
	public void update( )
	{
		// Check for message in queue
		if( this.isMessageQueued( ) )
		{
			// Correct overloading state
			while( this.messageQueue.size( ) >= Annotation.OVERLOAD_LIMIT )
			{
				// Remove first
				this.messageQueue.remove( 0 );		
				
				// Reset state
				this.resetState( );
			}
				
			
			// Check if delay passed
			if( Time.getTicks( ) - this.lastUpdate >= this.alphaReductionDelay )
			{
				// Check for current state
				if( this.alphaState > Annotation.ALPHA_DECREASING_SPEED )
				{
					this.alphaState -= Annotation.ALPHA_DECREASING_SPEED;
					this.verticalPositionState -= Annotation.FINAL_VERTICAL_DIFFERENCE / ( (double)Annotation.INITIAL_ALPHA_STATE / (double)Annotation.ALPHA_DECREASING_SPEED );
				}
				else
				{
					// Remove this annotation
					this.messageQueue.remove( 0 );
					
					// Reset state
					this.resetState( );
				}
				
				// Save last update time
				this.lastUpdate = Time.getTicks( );
			}
		}
	}
	
	/**
	 * Draw
	 * 
	 * @param graphics
	 * 		The graphics where to draw
	 */
	public void draw( Graphics g )
	{
		// Check if message in queue
		if( !this.isMessageQueued( ) )
			return;
		
		// Set the color
		g.setColor( new Color( color,
			false ) );
		
		// Set alpha
		( (Graphics2D)g ).setComposite( java.awt.AlphaComposite.getInstance( java.awt.AlphaComposite.SRC_OVER,
				(float)this.alphaState / 255.0f ) );
		
		// Draw
		g.drawString( this.messageQueue.get( 0 ),
			this.position.getX( ) - g.getFontMetrics( ).stringWidth( this.messageQueue.get( 0 ) ) / 2,
			this.position.getY( ) + (int)this.verticalPositionState );
		
		// Restore alpha
		( (Graphics2D)g ).setComposite( java.awt.AlphaComposite.getInstance( java.awt.AlphaComposite.SRC_OVER,
				1.0f ) );
	}
}
