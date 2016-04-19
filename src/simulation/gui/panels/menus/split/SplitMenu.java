package simulation.gui.panels.menus.split;

import java.awt.Color;

import simulation.constant.Direction;
import simulation.gui.animation.Animation;
import simulation.gui.panels.Panel;
import simulation.gui.panels.menus.split.action.ValidationAction;
import simulation.math.counter.DelayedPingCyclicCounter;
import simulation.math.point.Point;

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
	
	/**
	 * Panel is launched
	 */
	private boolean isContinue;
	
	/**
	 *	Construct the panel
	 * 
	 * 		@throws IOException 
	 */
	public SplitMenu( ) throws java.io.IOException
	{
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
			// Background
				this.scrollingBackground = new ScrollingBackground( art.Art.getArtImage( art.ArtList.ART_BACKGROUND_SPLIT ),
					8,
					1.5 );

		// Init
		this.isContinue = true;
				
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

	public void paint( java.awt.Graphics g )
	{
		// Clean background
			// Set color to draw
				g.setColor( new Color( 0x5BA4DB,
						false ) );
			// Fill with color
				g.fillRect( 0,
					0,
					this.getWidth( ),
					this.getHeight( ) );

		// Blit
			// Scrolling background
				this.scrollingBackground.blitBackground( g,
					super.getWidth( ),
					super.getHeight( ),
					this );
			// Cow
				g.drawImage( this.cow.getCurrentFrame( ).getContent( ),
					( this.getWidth( ) / 2 - this.cow.getFrameSize( ).getX( ) / 2 ) + SPACE_COW_LION,
					this.getHeight( ) - SplitMenu.SPACE_TO_THE_GRASS - this.cow.getFrameSize( ).getY( ) / 3,
					this.cow.getFrameSize( ).getX( ) / 3,
					this.cow.getFrameSize( ).getY( ) / 3,
					this );
			// Lion
				g.drawImage( this.lion.getCurrentFrame( ).getContent( ),
					( this.getWidth( ) / 2 - this.lion.getCurrentFrame( ).getContent( ).getWidth( ) / 2 ) + SPACE_LION_COW,
					this.getHeight( ) - SplitMenu.SPACE_TO_THE_GRASS - this.lion.getFrameSize( ).getY( ) / 4,
					(int)((double)this.lion.getFrameSize( ).getX( ) / 3.5),
					(int)((double)this.lion.getFrameSize( ).getY( ) / 3.5),
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
	
	public void stop( )
	{
		// Stop execution
		this.isContinue = false;
		
		// Kill the thread
		this.backgroundUpdateThread.interrupt( );
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
						catch( simulation.time.NotReadyException e )
						{
							
						}
					// Cow
						try
						{
							this.cow.update( );
						}
						catch( simulation.time.NotReadyException e )
						{
							
						}
					// Background
						try
						{
							this.scrollingBackground.update( );
						}
						catch( simulation.time.NotReadyException e )
						{
							
						}
				
				// Update title alpha
				this.currentAlpha.update( );
				
				// Delay
				Thread.sleep( 1 );
			}
			// Stop thread
			catch( InterruptedException e )
			{
				
			}
		}
	}
}
