package simulation.gui.panels.simulation.map.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Semaphore;

import javax.swing.SwingUtilities;

import simulation.ViewState;
import simulation.world.World;

public class MouseViewResetAction extends MouseAction implements MouseListener
{
	/**
	 * Construct the action
	 * 
	 * @param viewState
	 * 		The actual view state
	 * @param world
	 * 		The world
	 */
	public MouseViewResetAction( ViewState viewState,
		World world,
		Semaphore semaphore )
	{
		// Parent constructor
		super( viewState,
			world,
			semaphore );
	}
	
	@Override
	public void mouseClicked( MouseEvent e )
	{

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
		// If middle click
		if( SwingUtilities.isMiddleMouseButton( e ) )
		{
			// Reset view state
			super.getViewState( ).resetCamera( );
			
			try
			{
				// Lock semaphore
				super.getSemaphore( ).acquire( );
				
				// Update view
				super.getWorld( ).updateWorldMap( );
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

	@Override
	public void mouseReleased( MouseEvent e )
	{

	}

}
