package simulation.gui.panels;

import java.io.IOException;
import java.security.InvalidParameterException;

import simulation.gui.panels.menus.main.MainMenu;
import simulation.gui.panels.menus.option.OptionMenu;
import simulation.gui.panels.menus.split.ScrollingBackground;
import simulation.gui.panels.menus.split.SplitMenu;

/**
 *	The panels
 *
 *	@author
 *		Lucas Soares
 */
public class PanelRepository
{
	/**
	 *	The different panels
	 */
	private Panel[ ] panels;
	
	/**
	 * Scrolling background
	 */
	private ScrollingBackground scrollingBackground;
	
	/**
	 * Construct panels
	 */
	public PanelRepository( ) throws IOException
	{
		// Allocate memory
		this.panels = new Panel[ PanelDefinition.values( ).length ];
		
		// Create the scrolling background
		this.scrollingBackground = new ScrollingBackground( art.Art.getArtImage( art.ArtList.ART_BACKGROUND_SPLIT ),
			8,
			1.5 );
		
		// Create the panels
		for( PanelDefinition pd : PanelDefinition.values( ) )
			switch( pd )
			{
				case PANEL_SPLIT_MENU:
					this.panels[ pd.ordinal( ) ] = new SplitMenu( this.scrollingBackground );
					break;
				case PANEL_MAIN_MENU:
					this.panels[ pd.ordinal( ) ] = new MainMenu( this.scrollingBackground );
					break;
				case PANEL_OPTIONS_MENU:
					this.panels[ pd.ordinal( ) ] = new OptionMenu( this.scrollingBackground );
					break;
					
				default:
					break;
			}
	}
	
	/**
	 *	@param panel
	 *		The requested panel
	 *
	 *	@return the wanted panel
	 *
	 *	@throws
	 *		java.security.InvalidParameterException
	 */
	public Panel getPanel( PanelDefinition pd ) throws InvalidParameterException
	{
		// Check panel
		if( pd.ordinal( ) >= PanelDefinition.values( ).length )
			throw new InvalidParameterException( );
		
		// OK
		return this.panels[ pd.ordinal( ) ];
	}
}
