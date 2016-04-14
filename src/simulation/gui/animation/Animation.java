package simulation.gui.animation;

import java.awt.image.BufferedImage;

import simulation.math.point.Point;
import simulation.time.NotReadyException;
import simulation.time.Time;

/**
 *	An animation made of frames.
 *
 *	@author
 *		Lucas Soares
 */
public class Animation
{
	/**
	 * The frames of the animation
	 */
	private Frame[ ] frames;
	
	/**
	 * The real number of frames
	 */
	private int realFrameNumber;
	
	/**
	 * The size of one frame
	 */
	private Point<Integer> frameSize;
	
	/**
	 * The delay between the frames (ms)
	 */
	private int delay;
	
	/**
	 * The actual frame
	 */
	private int frame;
	
	/**
	 * Last update time
	 */
	private long lastUpdate;
	
	/**
	 * The position of animation on the screen
	 */
	private Point<Integer> position;
	
	/**
	 *	Construct the animation, which is
	 *	read from left to right, top to bottom
	 * 
	 *	@param tileset
	 *		The picture in which are the frames
	 *	@number
	 *		The horizontal/vertical number of frames
	 *	@realNumber
	 *		The total number of frame to be played
	 *	in the tileset.
	 *	@delay
	 *		The delay between two frames
	 */
	public Animation( BufferedImage tileset,
		Point<Integer> number,
		int realNumber,
		int delay )
	{
		// Save
			// Frame size
				this.frameSize = new Point<Integer>( tileset.getWidth( ) / number.getX( ),
					tileset.getHeight( ) / number.getY( ) );
			// Frame number
				this.realFrameNumber = realNumber;
			// Delay between frames
				this.delay = delay;
		
		// Allocate memory
		this.frames = new Frame[ this.realFrameNumber ];

		// Cut the tileset
		int currentFrame = 0;
		for( int y = 0; y < number.getY( ); y++ )
			for( int x = 0; x < number.getX( ); x++ )
				if( currentFrame < this.realFrameNumber )
					this.frames[ currentFrame++ ] = new Frame(
						tileset.getSubimage( x * this.frameSize.getX( ),
							y * this.frameSize.getY( ),
							this.frameSize.getX( ),
							this.frameSize.getY( ) ) );
		
		// Init
		this.lastUpdate = 0;				
	}
	
	/**
	 * @return the current frame
	 */
	public Frame getCurrentFrame( )
	{
		return this.frames[ this.frame ];
	}
	
	/**
	 * @return the frame size
	 */
	public Point<Integer> getFrameSize( )
	{
		return this.frameSize;
	}
	
	
	/**
	 * @return the position
	 */
	public Point<Integer> getPosition( )
	{
		return position;
	}

	/**
	 * @param position
	 * 		The position to set
	 */
	public void setPosition( Point<Integer> position )
	{
		this.position = position;
	}
	
	/**
	 * @param x
	 * 		The x to set
	 * @param y
	 * 		The y to set
	 */
	public void setPosition( int x,
		int y )
	{
		this.setPosition( new Point<Integer>( x,
			y ) );
	}

	/**
	 *	Update the animation frame
	 * 
	 * 	@throws simulation.time.NotReadyException
	 * 		The delay didn't passed yet
	 */
	public void update( ) throws NotReadyException
	{
		// Check last update
		if( Time.getTicks( ) - this.lastUpdate < (long)this.delay )
			throw new NotReadyException( ( Time.getTicks( ) - this.lastUpdate ) + " < " + this.delay );
		
		// Update the animation
		if( this.frame < this.realFrameNumber - 1 )
			this.frame++;
		else
			this.frame = 0;
		
		// Update last update ticks
		this.lastUpdate = Time.getTicks( );
	}
}


