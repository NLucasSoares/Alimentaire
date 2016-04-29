package simulation.foodies;

import java.io.IOException;

import simulation.Database;
import simulation.gui.Window;
import simulation.gui.configuration.Configuration;
import simulation.gui.panels.Panel;
import simulation.gui.panels.PanelDefinition;
import simulation.world.World;

/**
 * 	The main function is here
 * 
 * 	@author
 * 		Lucas Soares
 */
public class Foodies
{
	/**
	 * The GUI configuration
	 */
	private Configuration configuration;
	
	/**
	 * The main window
	 */
	private Window window;
	
	/**
	 * The database
	 */
	private Database database;
	
	/**
	 * The current world
	 */
	private World world;
	
	/**
	 * User didn't ask to quit yet...
	 */
	private boolean isContinue = true;
	
	/**
	 * Create the instance
	 */
	public Foodies( ) throws IOException
	{
		/* Ini file */
		// Check if ini file exists
		if( !simulation.file.operation.Operation.isFileExist( simulation.gui.configuration.Configuration.CONFIGURATION_FILE ) )
			// Create the ini file
			Configuration.createINI( );
		
		// Load configuration
		this.configuration = new Configuration( );
		
		// Load data
		this.database = new Database( );
		
		/* Window */
		this.window = new Window( this.configuration );
	}
	
	/**
	 * Main
	 */
	public static void main( String[ ] args )
	{
		// Try to create instance of Foodies
		try
		{
			// Create the instance
			simulation.foodies.Foodies foodies = new Foodies( );
			
			// Wait for split panel ending
			waitForPanel( foodies.window.getCurrentPanel( ) );
			
			// When split is done, start main menu
			do
			{				
				// Prepare menu
				foodies.window.prepareForMenuRendering( );
				
				// Start main menu
				foodies.window.switchPanel( PanelDefinition.PANEL_MAIN_MENU );
				
				// Wait for main panel ending
				waitForPanel( foodies.window.getCurrentPanel( ) );
				
				// Analyse result code of execution
				switch( ((simulation.gui.panels.menus.main.MainMenu)foodies.window.getPanel( PanelDefinition.PANEL_MAIN_MENU )).getReturnCode( ) )
				{
					case RETURN_CODE_NEW:
						//foodies.window.switchPanel( PanelDefinition.PANEL_NEW_MENU );
						foodies.world = new World( "Test",
								new simulation.world.Configuration( 20,
									true,
									100,
									foodies.database.getBiomeFromName( "jungle" ) ),
								foodies.database,
								foodies.window );
						
						foodies.world.getSimulationFrame( ).simulate( foodies.world );
						break;
					case RETURN_CODE_LOAD:
						foodies.window.switchPanel( PanelDefinition.PANEL_LOAD_MENU );
						break;
					case RETURN_CODE_OPTIONS:
						foodies.window.switchPanel(PanelDefinition.PANEL_OPTIONS_MENU);
						break;
					case RETURN_CODE_QUIT:
						foodies.isContinue = false;
						break;
						
					default:
						break;
				}
				
				// Wait for subpanel end
				waitForPanel( foodies.window.getCurrentPanel( ) );
			} while( foodies.isContinue );
		}
		catch( IOException e )
		{
			System.err.println( "Unable to init program." );
			System.exit( 1 );
		}
	
	}
	
	/**
	 * Wait for panel...
	 */
	public static void waitForPanel( Panel panel )
	{
		// Wait for panel ending
		while( panel.isContinue( ) )
		{
			try
			{
				Thread.sleep( 1 );
			}
			catch ( InterruptedException e )
			{
			}
		}
	}
}
