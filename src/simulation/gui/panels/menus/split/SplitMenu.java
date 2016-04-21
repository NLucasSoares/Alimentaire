package simulation.gui.panels.menus.split;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import simulation.constant.Direction;
import simulation.gui.animation.Animation;
import simulation.gui.panels.Panel;
import simulation.gui.panels.menus.split.action.ValidationAction;
import simulation.math.counter.DelayedPingCyclicCounter;
import simulation.math.point.Point;
import simulation.time.NotReadyException;

/**
 *	The split menu, the one
 *	which start when program
 *	starts.
 *
 *	@author
 *		Lucas Soares
 */
public class SplitMenu extends Panel
{
	/**
	 * Constants
	 */
	// Horizontal space between cow and lion (for the cow) (px)
	private static final int SPACE_COW_LION = 300;
	
	// Horizontal space between cow and lion (for the cow) (px)
	private static final int SPACE_LION_COW = 200;
	
	// Vertical space from the bottom to the grass (px)
	private static final int SPACE_TO_THE_GRASS = 100;
	
	// Title
	private static final String SPLIT_TITLE = "Alimentary Chain";
	
	// Instruction
	private static final String SPLIT_INSTRUCTION = "Press a key...";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -744999504772822928L;
	
	/**
	 *	The background update thread
	 */
	private Thread backgroundUpdateThread;

	/**
	 *	Animations
	 */
	private Animation cow;
	private Animation lion;
	private ScrollingBackground scrollingBackground;
	private DelayedPingCyclicCounter currentAlpha;
	private int cowPositionVariation;
	private int lionPositionVariation;
	
	/**
	 * Play end animation?
	 */
	private boolean isPlayingEndAnimation;
	
	/**
	 * Panel is launched
	 */
	private boolean isContinue;
	
	/**
	 *	Construct the panel
	 * 
	 * 		@throws IOException 
	 */
	public SplitMenu( ScrollingBackground background ) throws java.io.IOException
	{
		// Save
		this.scrollingBackground = background;
		
		// Create animations
			// Cow
				this.cow = new Animation( art.Art.getArtImage( art.ArtList.ART_COW_SPLIT ),
					new Point<Integer>( 8,
							8 ),
					57,
					40 );
			// Lion
				this.lion = new Animation( art.Art.getArtImage( art.ArtList.ART_LION_SPLIT ),
					new Point<Integer>( 3,
							2 ),
					6,
					45 );

		// Init
		this.isContinue = true;
		this.isPlayingEndAnimation = false;
		this.cowPositionVariation = 0;
		this.lionPositionVariation = 0;
				
		// Set to zero
		this.currentAlpha = new DelayedPingCyclicCounter( 0,
			0x7F,
			0xFF,
			Direction.DIRECTION_RIGHT,
			16 );
		
		// Action used for leaving menu
		this.addKeyListener( new ValidationAction( this ) );
		
		// Panel focusable
		super.setFocusable( true );
		super.requestFocusInWindow( );
	}
	
	/**
	 * Calculate cow position
	 * 
	 * @return cow position
	 */
	private Point<Integer> calculateCowPosition( )
	{
		return new Point<Integer>( ( this.getWidth( ) / 2 - this.cow.getFrameSize( ).getX( ) / 2 ) + SPACE_COW_LION,
				this.getHeight( ) - SplitMenu.SPACE_TO_THE_GRASS - this.cow.getFrameSize( ).getY( ) / 3 );
	}
	
	/**
	 * Calculate lion position
	 * 
	 * @return lion position
	 */
	private Point<Integer> calculateLionPosition( )
	{
		return new Point<Integer>( ( this.getWidth( ) / 2 - this.lion.getCurrentFrame( ).getContent( ).getWidth( ) / 2 ) + SPACE_LION_COW,
				this.getHeight( ) - SplitMenu.SPACE_TO_THE_GRASS - this.lion.getFrameSize( ).getY( ) / 4 );
	}

	public void paint( java.awt.Graphics g )
	{
		// Anti Aliasing
		((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON );
		
		// Clean background
			// Set color to draw
				g.setColor( new Color( ScrollingBackground.BACKGROUND_COLOR,
						false ) );
			// Fill with color
				g.fillRect( 0,
					0,
					this.getWidth( ),
					this.getHeight( ) );

		// Blit
			Point<Integer> position;
			// Scrolling background
				this.scrollingBackground.blitBackground( g,
					super.getWidth( ),
					super.getHeight( ),
					this );
			// Cow
				position = this.calculateCowPosition( );
				g.drawImage( this.cow.getCurrentFrame( ).getContent( ),
					position.getX( ) + this.cowPositionVariation,
					position.getY( ),
					this.cow.getFrameSize( ).getX( ) / 3,
					this.cow.getFrameSize( ).getY( ) / 3,
					this );
			// Lion
				position = this.calculateLionPosition( );
				g.drawImage( this.lion.getCurrentFrame( ).getContent( ),
					position.getX( ) + this.lionPositionVariation,
					position.getY( ),
					(int)( (double)this.lion.getFrameSize( ).getX( ) / 3.5 ),
					(int)( (double)this.lion.getFrameSize( ).getY( ) / 3.5 ),
					this );

		// Blit split texts
			// Title
				// Set color
					g.setColor( new java.awt.Color( 0x00FFFFFF | this.currentAlpha.getCount( ) << 24,
						true ) );
				// Change font
					g.setFont( new java.awt.Font( "Trebuchet MS",
						java.awt.Font.BOLD,
						40 ) );
				// Blit
					g.drawString( SPLIT_TITLE,
						super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( SPLIT_TITLE ) / 2,
						60 );
			// Instruction
				// Change font
					g.setFont( new java.awt.Font( "Trebuchet MS",
						java.awt.Font.ITALIC,
						30 ) );
				// Blit
					g.drawString( SPLIT_INSTRUCTION,
						super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( SPLIT_INSTRUCTION ) / 2,
						100 );
	}

	public void update( java.awt.Graphics g )
	{
		paint( g );
	}
	
	public void start( )
	{
		if( this.backgroundUpdateThread == null )
		{
			this.backgroundUpdateThread = new Thread( this );
			this.backgroundUpdateThread.start( );
		}
	}
	
	/**
	 * Play the end animation
	 */
	public void playEndAnimation( )
	{
		this.isPlayingEndAnimation = true;
	}
	
	public void stop( )
	{
		// End animation
		this.playEndAnimation( );
	}
	
	/**
	 * Continue state is on
	 */
	public boolean isContinue( )
	{
		return ( !this.backgroundUpdateThread.isInterrupted( )
			&& this.isContinue );
	}
	
	@Override
	public void run( )
	{		
		// While the thread is running...
		while( this.isContinue( ) )
		{
			try
			{
				// Ask for repaint
				super.repaint( );
				
				// Update animations
					// Lion
						try
						{
							this.lion.update( );
						}
						catch( NotReadyException e )
						{
							
						}
					// Cow
						try
						{
							this.cow.update( );
						}
						catch( NotReadyException e )
						{
							
						}
					// Background
						try
						{
							this.scrollingBackground.update( );
						}
						catch( NotReadyException e )
						{
							
						}
						
				// End animation gesture
				if( this.isPlayingEndAnimation )
				{
					// Play animation
					if( ( this.calculateCowPosition( ).getX( ) + this.cowPositionVariation < super.getWidth( )
							&& this.calculateLionPosition( ).getX( ) + this.lionPositionVariation > -this.lion.getFrameSize( ).getX( ) ) )
					{
						// Change position
						this.cowPositionVariation += 5;
						this.lionPositionVariation -= 5;
					}
					// Quit the panel
					else
					{
						// Stop execution
						this.isContinue = false;
						
						// Kill the thread
						this.backgroundUpdateThread.interrupt( );
					}

					// Delay
					try
					{
						Thread.sleep( 1 );
					}
					catch( InterruptedException e )
					{
						
					}
				}
				
				// Update title alpha
				this.currentAlpha.update( );
				
				// Delay
				Thread.sleep( Panel.DELAY_BETWEEN_FRAME );
			}
			// Stop thread
			catch( InterruptedException e )
			{
				
			}
		}
	}
}
