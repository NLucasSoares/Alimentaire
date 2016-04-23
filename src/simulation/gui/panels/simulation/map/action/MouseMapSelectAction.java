package simulation.gui.panels.simulation.map.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

import simulation.ViewState;
import simulation.world.World;
import simulation.world.environment.hexagonalMap.UnitHexagonalMap;

public class MouseMapSelectAction extends MouseAction implements MouseListener
{
	public MouseMapSelectAction( ViewState viewState,
		World world,
		Semaphore semaphore )
	{
		// Parent construct
		super( viewState,
			world,
			semaphore );
	}
	
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
