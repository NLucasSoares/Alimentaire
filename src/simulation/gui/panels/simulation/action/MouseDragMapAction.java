package simulation.gui.panels.simulation.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.Semaphore;

import javax.swing.SwingUtilities;

import simulation.ViewState;
import simulation.math.point.Point;
import simulation.world.World;

public class MouseDragMapAction extends MouseAction implements MouseListener, MouseMotionListener
{	
	/**
	 * Start position at click
	 */
	private Point<Integer> startPosition;
	
	/**
	 * Drag value
	 */
	private Point<Integer> dragPosition;
	
	/**
	 * Initial drag
	 */
	private Point<Integer> initialDragValue;
	
	/**
	 * Construct mouse drag map action
	 * 
	 * @param viewState
	 * 		The view state
	 * @param world
	 * 		The world
	 */
	public MouseDragMapAction( ViewState viewState,
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
	}

	@Override
	public void mouseReleased( MouseEvent e )
	{
		// delete position
		this.startPosition = null;
	}

	@Override
	public void mouseDragged( MouseEvent e )
	{
		// Check for left button
		if( SwingUtilities.isLeftMouseButton( e ) )
		{
			// Save the start position
			if( this.startPosition == null )
			{
				// Mouse position at start
				this.startPosition = new Point<Integer>( e.getX( ),
					e.getY( ) );
				
				// Save drag
				this.initialDragValue = super.getViewState( ).getScrollPosition( );
			}
			// Calculate drag value
			else
			{
				// Position modification
				this.dragPosition = new Point<Integer>( this.startPosition.getX( ) - e.getX( ),
					this.startPosition.getY( ) - e.getY( ) );
				
				// Set position
				super.getViewState( ).setScrollPosition( new Point<Integer>( this.initialDragValue.getX( ) - this.dragPosition.getX( ),
					this.initialDragValue.getY( ) - this.dragPosition.getY( ) ) );
				
				try
				{
					// Lock the semaphore
					super.getSemaphore( ).acquire( );
					
					// Update
					super.getWorld( ).constructWorldMap( );
				}
				catch( InterruptedException e1 )
				{

				}
				finally
				{
					// Unlock the semaphore
					super.getSemaphore( ).release( );
				}
			}
		}
	}

	@Override
	public void mouseMoved( MouseEvent e )
	{
		
	}
}
