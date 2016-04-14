package simulation.gui.panels.menus.split;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import simulation.time.NotReadyException;
import simulation.time.Time;

/**
 *	The background behind the split menu
 * 
 * 	@author Lucas SOARES
 */
public class ScrollingBackground
{
	/**
	 * The background image
	 */
	private BufferedImage background;
	
	/**
	 * Actual state
	 */
	private double state;
	
	/**
	 * Last update
	 */
	private long lastUpdate;
	
	/**
	 * Delay between background update
	 */
	private long delayBetweenUpdate;
	
	/**
	 * Speed of movement
	 */
	private double movementSpeed;
	
	/**
	 * Construct the background
	 * 
	 * @param background
	 * 		The background image
	 * @param delayBetweenUpdate
	 * 		The delay between two update
	 * of the background state
	 * @param movementSpeed
	 * 		The position increase at each step of
	 * update
	 */
	public ScrollingBackground( BufferedImage background,
		long delayBetweenUpdate,
		double movementSpeed )
	{
		// Save
		this.background = background;
		this.delayBetweenUpdate = delayBetweenUpdate;
		this.movementSpeed = movementSpeed;
		
		// Init
		this.state = 0.0;
		this.lastUpdate = Time.getTicks( );
	}
	
	/**
	 * Update
	 */
	public void update( ) throws NotReadyException
	{
		// Check time
		if( simulation.time.Time.getTicks( ) - this.lastUpdate < this.delayBetweenUpdate )
			throw new NotReadyException( "Not ready" );
		
		// Update
		if( this.state <= -(double)this.background.getWidth( ) )
			this.state += ((double)this.background.getWidth( ) - this.movementSpeed);
		else
			this.state -= this.movementSpeed;
		
		// Save last time updated
		this.lastUpdate = Time.getTicks( );
	}
	
	/**
	 * Print background
	 * 
	 * @param g
	 * 		The graphics to blit on
	 * @param width
	 * 		The width of the window
	 * @param height
	 * 		The height of the window
	 */
	public void blitBackground( Graphics g,
		int width,
		int height,
		ImageObserver imageObserver )
	{
		// Current position
		double x = this.state;

		// Blit background
		do
		{
			// Blit
			g.drawImage( this.background,
				(int)x,
				height - this.background.getHeight( ),
				imageObserver );

			// Increase x position
			x += (double)this.background.getWidth( );
		} while( (int)x <= width );
	}
			
}

