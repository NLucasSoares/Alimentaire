package simulation.gui.panels.menus.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import simulation.gui.object.ButtonState;
import simulation.gui.object.RectangleButton;
import simulation.gui.panels.Panel;
import simulation.gui.panels.menus.main.action.MouseAction;
import simulation.gui.panels.menus.main.properties.ButtonProperty;
import simulation.math.point.Point;

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
	
	/**
	 * Mouse action listener
	 */
	private MouseAction mouseAction;
	
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
	public boolean isContinue;
	
	/**
	 * Construct the panel
	 */
	public MainMenu( )
	{
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
	
	public void calculateButtons( )
	{
		int changedHeight = 150;
		for(int index = 0;index<4;index++)
		{
			this.buttons[ index ].setPosition( new Point<Integer>( super.getWidth()/2 - (WIDTH_BUTTON/2),
					changedHeight ) );
		
			changedHeight += this.buttons[index].getSize().getY() + 10;
		}
	}
	
	public void update( Graphics g )
	{
		paint( g );
	}
	
	public void paint( Graphics g )
	{
		g.setColor( Color.BLACK );
		g.fillRect( 0,
			0,
			super.getWidth( ),
			super.getHeight( ) );
		g.setColor(Color.WHITE);

		g.setFont( new java.awt.Font( "Trebuchet MS",
				java.awt.Font.BOLD,
				30 ) );
		g.drawString( MAIN_TITLE,
				super.getWidth( ) / 2 - g.getFontMetrics( ).stringWidth( MAIN_TITLE ) / 2,
				80 );
		for(int index=0; index < 4; index++)
		{
			buttons[index].blit( g );
			g.setColor(Color.RED);
			g.drawString(ButtonProperty.BUTTON_NAME[index],
					super.getWidth( ) / 2 - g.getFontMetrics().stringWidth(ButtonProperty.BUTTON_NAME[index]) / 2,
					buttons[ index ].getPosition().getY( ) + ( buttons[ index ].getSize( ).getY( ) / 2 ) + ( g.getFontMetrics().getDescent() / 2 ) );
		}
	} 
	
	/**
	 * Update button state
	 */
	public void updateButton( MouseEvent e )
	{
		for(int index=0; index < 4; index++)
		{
			buttons[index].update( e );
		
			switch( buttons[index].getButtonState( ) )
			{
				case BUTTON_OVERFLOWN:
					this.buttons[index].setFilled( true );
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
				
				// Treat buttons
				for( ButtonProperty bp : ButtonProperty.values( ) )
					if( this.buttons[ bp.ordinal( ) ].getButtonState( ) == ButtonState.BUTTON_PRESSED )
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
								int quitConfirm = JOptionPane.showConfirmDialog(null,"Do you really want to quit ?","Quit Confirmation",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if( quitConfirm == JOptionPane.YES_OPTION)
								{
								System.exit(0);
								}
								
								this.buttons[ bp.ordinal( ) ].resetButtonState(); 
								
								break;
								
							default:
								break;
						}
					
				// Repaint window
				super.repaint( );
				
				// Delay
				Thread.sleep( 16 );
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
