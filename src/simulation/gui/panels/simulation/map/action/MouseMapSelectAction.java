package simulation.gui.panels.simulation.map.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

import simulation.ViewState;
import simulation.gui.panels.simulation.mapControl.MapControlPanel;
import simulation.world.World;
import simulation.world.environment.hexagonalMap.UnitHexagonalMap;

/**
 * Mouse select one map
 * 
 * @author SOARES Lucas
 */
public class MouseMapSelectAction extends MouseAction implements MouseListener
{
	/**
	 * The map control panel
	 */
	private MapControlPanel mapControlPanel;

	/**
	 * Construct the mouse select action
	 * 
	 * @param viewState
	 * 		The current viewstate
	 * @param world
	 * 		The current world
	 * @param semaphore
	 * 		The world update semaphore
	 * @param mapControlPanel
	 * 		The map control panel
	 */
	public MouseMapSelectAction( ViewState viewState,
		World world,
		Semaphore semaphore,
		MapControlPanel mapControlPanel )
	{
		// Parent construct
		super( viewState,
			world,
			semaphore );
		
		// Save
		this.mapControlPanel = mapControlPanel;
	}
	
	/**
	 * Mouse CLICKED (which means pressed/released on the
	 * same pixel
	 * 
	 * @param e
	 * 		The mouse event
	 */
	@Override
	public void mouseClicked( MouseEvent e )
	{
		// Get the maps
		UnitHexagonalMap[ ] m = super.getWorld( ).getUnitHexagonalMaps( );
		
		// Look for correct mouse position
		for( UnitHexagonalMap it : m )
			if( it.isContaining( e.getX( ),
				e.getY( ) ) )
			{
				// Change selected map
				super.getViewState( ).setSelectedMap( it.getMap( ) );
				
				// Change selected map
				this.mapControlPanel.setMap( super.getViewState( ).getSelectedMap( ) );
				
				// Quit
				break;
			}
	}

	@Override
	public void mouseEntered( MouseEvent e )
	{
		
	}

	@Override
	public void mouseExited( MouseEvent e )
	{
		
	}

	@Override
	public void mousePressed( MouseEvent e )
	{
		
	}

	@Override
	public void mouseReleased( MouseEvent e )
	{
		
	}
}
