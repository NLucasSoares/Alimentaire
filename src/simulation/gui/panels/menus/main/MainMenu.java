package simulation.gui.panels.menus.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import simulation.gui.object.ButtonState;
import simulation.gui.object.RectangleButton;
import simulation.gui.panels.Panel;
import simulation.gui.panels.menus.main.action.MouseAction;
import simulation.gui.panels.menus.main.properties.ButtonProperty;
import simulation.gui.panels.menus.split.ScrollingBackground;
import simulation.math.point.Point;
import simulation.time.NotReadyException;

/**
 *	The main menu
 *
 *		@author
 *			Lucas Soares
 */
public class MainMenu extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5667077112861324655L;
	
	/**
	 * The width and height of menus' buttons
	 */
	private static final int WIDTH_BUTTON = 300;
	private static final int HEIGHT_BUTTON = 50;
	private static final String MAIN_TITLE = "Alimentary Chain";
	private static final String MAIN_SUBTITLE = "Ecosystem Simulation";
	
	
	/**
	 * Mouse action listener
	 */
	private MouseAction mouseAction;
	
	/**
	 * Buttons in menu
	 */
	private RectangleButton[] buttons;
	
	/**
	 * The returned code by the panel at the
	 * end of the execution
	 */
	private ReturnCode returnCode;
	
	/**
	 * The updating thread
	 */
	private Thread backgroundUpdateThread;
	
	/**
	 * Is launched?
	 */
	private boolean isContinue;
	
	/**
	 * Background
	 */
	private ScrollingBackground background;
	
	/**
	 * Construct the panel
	 * 
	 * @throws  java.io.IOException 
	 */
	public MainMenu( ScrollingBackground background )
	{
		// Save
		this.background = background;
		
		// Init
		this.isContinue = true;
		
		// Create mouse listener
		this.mouseAction = new MouseAction( this );
		
		// Calculate buttons
		this.buttons = new RectangleButton[ ButtonProperty.values( ).length ];
		for( int i = 0; i < ButtonProperty.values( ).length; i++ )
		{
			this.buttons[ i ] = new RectangleButton( );
			
			this.buttons[ i ].setSize( new Point<Integer>( WIDTH_BUTTON,
					 HEIGHT_BUTTON ) );
		}
		
		// Add listener
		super.addMouseListener( this.mouseAction );
		super.addMouseMotionListener( this.mouseAction );
		
		// Focusable panel
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
	
	public void calculateButtons( )
	{
		// The current vertical position
		int currentVerticalPosition = 150;
		
		// Calculate position
		for( int index = 0; index < this.buttons.length; index++ )
		{
			// Set position
			this.buttons[ index ].setPosition( new Point<Integer>( super.getWidth( ) / 2 - ( WIDTH_BUTTON / 2 ),
				currentVerticalPosition ) );
		
			// Next button
			currentVerticalPosition += this.buttons[ index ].getSize().getY( ) + 10;
		}
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


		// Title
			// Change font
				g.setFont( new java.awt.Font( "Trebuchet MS",
					java.awt.Font.BOLD,
					40 ) );
			// Blit title
				g.drawString( MAIN_TITLE,
						super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( MAIN_TITLE ) / 2,
						60 );
			
		// Subtitle
			// Change font
				g.setFont( new java.awt.Font( "Trebuchet MS",
					java.awt.Font.ITALIC,
					30 ) );
			// Blit subtitle
				g.drawString( MAIN_SUBTITLE,
					super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( MAIN_SUBTITLE ) / 2,
					100 );
			
		// Buttons
		
		// Change font
			g.setFont( new java.awt.Font( "Time New Roman",
					java.awt.Font.PLAIN,
					25 ) );
		// Buttons
		for( int index = 0; index < buttons.length; index++ )
		{
			// Blit shape
			buttons[ index ].blit( g );
			
			// Change color
			g.setColor( Color.BLACK );
			
			// Print text
			g.drawString( ButtonProperty.BUTTON_NAME[ index ],
					super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( ButtonProperty.BUTTON_NAME[ index ] ) / 2,
					buttons[ index ].getPosition( ).getY( ) + ( buttons[ index ].getSize( ).getY( ) / 2 ) + ( g.getFontMetrics( ).getDescent( ) / 2 ) );
		}
	} 
	
	/**
	 * Update button state
	 * 
	 * @param e
	 * 		The mouse event
	 */
	public void updateButton( MouseEvent e )
	{
		// Update buttons
		for( int index = 0; index < buttons.length; index++ )
		{
			// Get current state
			buttons[ index ].update( e );
		
			// Parse result
			switch( buttons[ index ].getButtonState( ) )
			{
				case BUTTON_OVERFLOWN:
					this.buttons[ index ].setFilled( true );
					break;
					
				case BUTTON_RELEASED:
					this.buttons[ index ].setFilled( false );
					break;
					
					
					
				default:
					break;
			}
		}
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

	
	@Override
	public void run( )
	{
		// Menu and background thread are running
		while( this.isContinue( ) )
		{			
			try
			{
				// Recalculate buttons
				calculateButtons( );
				
				// Update background
				try
				{
					this.background.update( );
				}
				catch( NotReadyException e )
				{
					
				}
				
				// Treat buttons
				for( ButtonProperty bp : ButtonProperty.values( ) )
					// If clicked
					if( this.buttons[ bp.ordinal( ) ].getButtonState( ) == ButtonState.BUTTON_PRESSED )
					{
						// Parse button action
						switch( bp )
						{
							case BUTTON_NEW_WORLD:
								this.quitMenu( ReturnCode.RETURN_CODE_NEW );
								break;
							case BUTTON_LOAD_WORLD:
								this.quitMenu( ReturnCode.RETURN_CODE_LOAD );
								break;
							case BUTTON_OPTION:
								this.quitMenu( ReturnCode.RETURN_CODE_OPTIONS );
								break;
							case BUTTON_QUIT:
								// Open confirm dialog
								int quitConfirm = JOptionPane.showConfirmDialog( null,
										"Do you really want to quit ?",
										"Quit Confirmation",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE );
								
								// Check if yes selected
								if( quitConfirm == JOptionPane.YES_OPTION )
									System.exit( 0 ); 
								break;
								
							default:
								break;
						}
						
						// Reset button state
						this.buttons[ bp.ordinal( ) ].resetButtonState( );
					}
					
				// Repaint window
				super.repaint( );
				
				// Delay
				Thread.sleep( Panel.DELAY_BETWEEN_FRAME );
			}
			catch( InterruptedException e )
			{
			}
		}
	}

	@Override
	public void start( )
	{
		if( this.backgroundUpdateThread == null )
		{
			this.backgroundUpdateThread = new Thread( this );
			this.backgroundUpdateThread.start( );
		}
	}

	@Override
	public void stop( )
	{
		// Stop the panel
		this.isContinue = false;
		
		// Interrupt the thread
		this.backgroundUpdateThread.interrupt( );
	}

	@Override
	public boolean isContinue( )
	{
		return ( !this.backgroundUpdateThread.isInterrupted( )
			&& this.isContinue );
	}
}
