package simulation.gui.panels.simulation.action;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.concurrent.Semaphore;

import simulation.ViewState;
import simulation.world.World;

public class MouseWheelAction extends MouseAction implements MouseWheelListener
{
	/**
	 * Construct the mouse wheel action
	 * 
	 * @param viewState
	 * 		The actual view state
	 * @param world
	 * 		The world
	 */
	public MouseWheelAction( ViewState viewState,
		World world,
		Semaphore semaphore )
	{
		// Parent constructor
		super( viewState,
			world,
			semaphore);
	}
	
	/**
	 * Movement of the wheel
	 * 
	 * @param e
	 * 		The mouse wheel event
	 */
	@Override
	public void mouseWheelMoved( MouseWheelEvent e )
	{
		// Modify zoom level
		if( super.getViewState( ).getZoomLevel( ) - e.getWheelRotation( ) >= 1 )
			super.getViewState( ).setZoomLevel( super.getViewState( ).getZoomLevel( ) - e.getWheelRotation( ) );
		else
			super.getViewState( ).setZoomLevel( 1 );
		
		
		try
		{
			// Lock semaphore
			super.getSemaphore( ).acquire( );
			
			// Update view
			super.getWorld( ).constructWorldMap( );
		}
		catch( InterruptedException e1 )
		{
			
		}
		finally
		{
			// Unlock semaphore
			super.getSemaphore( ).release( );
		}
	}

}
