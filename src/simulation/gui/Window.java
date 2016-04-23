package simulation.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.security.InvalidParameterException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import simulation.gui.configuration.Configuration;
import simulation.gui.panels.Panel;
import simulation.gui.panels.PanelDefinition;
import simulation.gui.panels.PanelRepository;

/**
 *	The window used in this project
 *
 *	@author
 *		Lucas Soares
 */
public class Window extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5001563926817063054L;
	
	/**
	 * The different panels
	 */
	private PanelRepository panelRepository;

	/**
	 *	Construct the window
	 * 
	 *		@param configuration
	 *			The configuration of the program
	 */
	public Window( Configuration configuration ) throws java.io.IOException
	{
		// Set resolution
		super.setSize( configuration.getResolution( ).getX( ),
			configuration.getResolution( ).getY( ) );
		super.setPreferredSize( new Dimension( configuration.getResolution( ).getX( ),
				configuration.getResolution( ).getY( ) ) );
				
		/* Panels */
		this.panelRepository = new PanelRepository( );
		
		/* Window */
		// Create window
		super.setTitle( "Alimentary Chain Simulator" );

		// Default action is to quit
		super.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		// Start with split menu
		this.switchPanel( PanelDefinition.PANEL_SPLIT_MENU );
		
		// Show the window
		super.setVisible( true );
	}
	
	/**
	 *	Switch the actual panel shown at screen
	 *
	 *	@param panel
	 *		The panel to put in window
	 *
	 *	@throws java.security.InvalidParameterException
	 *		Parameter error
	 */
	public void switchPanel( PanelDefinition pd ) throws InvalidParameterException
	{
		// Check parameter
		if( pd.ordinal( ) >= PanelDefinition.values( ).length )
			throw new InvalidParameterException( );
		
		// Stop the previous panel
		if( super.getContentPane( ) instanceof Panel )
		{
			// Stop
			((Panel)super.getContentPane( )).stop( );
		
			// Wait for end on the panel
			while( ((Panel)super.getContentPane( )).isContinue( ) )
				try
				{
					Thread.sleep( 1 );
				}
				catch( InterruptedException e )
				{
					
				}
		}
		
		// Change panel
		super.setContentPane( this.panelRepository.getPanel( pd ) );
		
		// Update panel for JFrame component
		super.revalidate( );
		
		// Start the panel
		this.panelRepository.getPanel( pd ).start( );
	}
	
	/**
	 * 	@param panelDefinition
	 * 		The panel to get
	 * 
	 * 	@return the asked panel
	 */
	public Panel getPanel( PanelDefinition panelDefinition )
	{
		return this.panelRepository.getPanel( panelDefinition );
	}
	
	/**
	 * @return the current panel
	 */
	public Panel getCurrentPanel( )
	{
		if( this.getContentPane( ) instanceof Panel )
			return (Panel)this.getContentPane( );
		else
			return null;
	}
	
	/**
	 * Prepare for menu rendering
	 */
	public void prepareForMenuRendering( )
	{
		super.setLayout( new FlowLayout( ) );
	}
	
	/**
	 * Prepare for game rendering
	 * 
	 * @param map
	 * 		The map panel
	 * @param gbcMap
	 * 		The constraints for map panel
	 * @param worldControl
	 * 		The world control panel
	 * @param gbcWorldControl
	 * 		The constrains for world control panel
	 * @param instruments
	 * 		The instruments panel
	 * @param gbcInstruments
	 * 		The constraints for instruments panel
	 */
	public void prepareForGameRendering( JPanel map,
		GridBagConstraints gbcMap,
		JPanel worldControl,
		GridBagConstraints gbcWorldControl,
		JPanel instruments,
		GridBagConstraints gbcInstruments )
	{
		// Black background
		super.setContentPane( new JPanel( ) );
		super.getContentPane( ).setBackground( Color.BLACK );

		// Set the new layout
		super.setLayout( new GridBagLayout( ) );
		
		// Add the panel
			// Map
				super.add( map,
					gbcMap );
			// Biome
				super.add( worldControl,
					gbcWorldControl );
			// Instruments
				super.add( instruments,
					gbcInstruments );
				
		// Pack panels
		super.pack( );
	}
}
