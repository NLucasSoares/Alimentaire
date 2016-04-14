package simulation.gui.panels.simulation.action;

import java.util.concurrent.Semaphore;

import simulation.ViewState;
import simulation.world.World;

public class MouseAction
{
	/**
	 * View state
	 */
	private ViewState viewState;
	
	/**
	 * The world
	 */
	private World world;
	
	/**
	 * Semaphore
	 */
	private Semaphore semaphore;
	
	/**
	 * Construct mouse drag map action
	 * 
	 * @param viewState
	 * 		The view state
	 * @param world
	 * 		The world
	 */
	public MouseAction( ViewState viewState,
		World world,
		Semaphore semaphore )
	{
		// Save
		this.viewState = viewState;
		this.world = world;
		this.semaphore = semaphore;
	}

	/**
	 * @return the view state
	 */
	public ViewState getViewState( )
	{
		return viewState;
	}

	/**
	 * @return the world
	 */
	public World getWorld( )
	{
		return world;
	}
	
	/**
	 * @return the semaphore
	 */
	public Semaphore getSemaphore( )
	{
		return semaphore;
	}
}
