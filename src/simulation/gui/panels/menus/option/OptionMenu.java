package simulation.gui.panels.menus.option;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.swing.JSlider;

import simulation.gui.panels.menus.main.ReturnCode;
import simulation.gui.panels.menus.main.action.MouseAction;

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
	
	private static final ImageObserver observer = null;
	
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
	 * Construct the panel
	 * @throws  java.io.IOException 
	 */
	public OptionMenu( )
	{
		// Init
		this.isContinue = true;
		
		
		// Create mouse listener
		this.mouseAction = new MouseAction( this );
		
		
		
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
		// Panel Background
		try
		{
			g.drawImage( art.Art.getArtImage( art.ArtList.ART_BACKGROUND_SPLIT ), 
					0, 
					0, 
					super.getWidth( ), 
					super.getHeight( ), 
					observer );	 
		}
		catch (IOException e)
		{
			System.out.println("Image doesn't exist");
		}
		
		// String color
		g.setColor(Color.WHITE);


	//Option title
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
		
		JSlider mapSlide = new JSlider ();
		
		mapSlide.setMaximum(100);
		mapSlide.setMinimum(10);
		mapSlide.setValue(20);
		mapSlide.setPaintTicks(true);
		mapSlide.setPaintLabels(true);
		mapSlide.setMinorTickSpacing(10);
		mapSlide.setMajorTickSpacing(20);
		
		mapSlide.setVisible(true);
		
		
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
			this.backgroundUpdateThread = new Thread( );
			this.backgroundUpdateThread.start( );
		}
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
