package simulation.gui.panels.menus.option;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JSlider;

import simulation.gui.panels.Panel;
import simulation.gui.panels.menus.main.ReturnCode;
import simulation.gui.panels.menus.option.action.MouseAction;
import simulation.gui.panels.menus.split.ScrollingBackground;
import simulation.time.NotReadyException;

/**
 * The option panel
 * 
 * 		@author 
 * 			Aurèle Camps
 */

public class OptionMenu extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8736762147948680561L;
	
	private static final String OPTION_TITLE = "Alimentary Chain";
	private static final String OPTION_SUBTITLE = "Option";

	
	/**
	 * The returned code by the panel at the
	 * end of the execution
	 */
	private ReturnCode returnCode;
	
	
	/**
	 * Mouse action listener
	 */
	private MouseAction mouseAction;
	
	/**
	 * The updating thread
	 */
	private Thread backgroundUpdateThread;
	
	/**
	 * Is launched?
	 */
	public boolean isContinue;
	
	/**
	 * Background
	 */
	private ScrollingBackground background;
	
	/**
	 * Construct the panel
	 * @param background 
	 * @throws  java.io.IOException 
	 */
	public OptionMenu( ScrollingBackground background )
	{
		// Save
		this.background = background;
		
		// Init
		this.isContinue = true;
		
		// Add listener
		//super.addMouseListener( this.mouseAction );
		//super.addMouseMotionListener( this.mouseAction );
		
		// Panel focusable
		super.setFocusable( true );
		super.requestFocusInWindow( );
	}
	
	/**
	 * @return the code at the end of the
	 * panel execution
	 */
	public ReturnCode getReturnCode( )
	{
		return this.returnCode;
	}

	public void update( Graphics g )
	{
		paint( g );
	}
	
	public void paint( Graphics g )
	{
		// Anti Aliasing
		((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON );
		
		// Clear background
			// Set color
				g.setColor( new Color( ScrollingBackground.BACKGROUND_COLOR,
					false ) );
			// Fill
				g.fillRect( 0,
					0,
					super.getWidth( ),
					super.getHeight( ) );
		
		// Panel Background
		this.background.blitBackground( g,
			super.getWidth( ),
			super.getHeight( ),
			this );

		// String color
		g.setColor( Color.WHITE );

		// Option title
			// Change font
				g.setFont( new java.awt.Font( "Trebuchet MS",
					java.awt.Font.BOLD,
					40 ) );
			// Blit title
				g.drawString( OPTION_TITLE,
						super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( OPTION_TITLE ) / 2,
						60 );

		// Subtitle
			// Change font
				g.setFont( new java.awt.Font( "Trebuchet MS",
					java.awt.Font.ITALIC,
					30 ) );
			// Blit subtitle
				g.drawString( OPTION_SUBTITLE,
					super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( OPTION_SUBTITLE ) / 2,
					100 );

		/*JSlider mapSlide = new JSlider( );
		   
	    mapSlide.setMaximum( 100 );
	    mapSlide.setMinimum( 0 );
	    mapSlide.setValue( 50 );
	    mapSlide.setPaintTicks( true );
	    mapSlide.setPaintLabels( true );
	    mapSlide.setMinorTickSpacing( 5 );
	    mapSlide.setMajorTickSpacing( 10 );*/
	}
	
	/**
	 * Quit menu and save return code
	 * 
	 * @param returnCode
	 * 		The menu return code
	 */
	public void quitMenu( ReturnCode rc )
	{
		// Save the code
		this.returnCode = rc;
		
		// Stop the menu
		this.stop( );
	}
	
	public void start( )
	{
		if( this.backgroundUpdateThread == null )
		{
			this.backgroundUpdateThread = new Thread( this );
			this.backgroundUpdateThread.start( );
		}
	}
	
	public void run( )
	{
		do
		{
			// Repaint
			super.repaint( );

			// FAIS CE QUE TU AS A FAIRE ICI
			
			// Update background
			try
			{
				this.background.update( );
			}
			catch( NotReadyException e1 )
			{

			}
			
			// Delay
			try
			{
				Thread.sleep( Panel.DELAY_BETWEEN_FRAME );
			}
			catch( InterruptedException e )
			{

			}
		} while( this.isContinue( ) );
	}

	public void stop( )
	{
		// Stop the panel
		this.isContinue = false;
		
		// Interrupt the thread
		this.backgroundUpdateThread.interrupt( );
	}

	public boolean isContinue( )
	{
		return ( !this.backgroundUpdateThread.isInterrupted( )
			&& this.isContinue );
	}
}
