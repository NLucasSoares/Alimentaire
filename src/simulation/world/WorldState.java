package simulation.world;

import simulation.ViewState;
import simulation.time.Time;

public class WorldState
{
	/**
	 * Current round
	 */
	private long round;
	
	/**
	 * The actual viewstate
	 */
	private ViewState viewState;
	
	/**
	 * Callback to world
	 */
	private World world;
	
	/**
	 * Last update time
	 */
	private long lastUpdateTime;
	
	/**
	 * Construct the world
	 * 
	 * @param world
	 * 		The world which this state is for
	 */
	public WorldState( World world )
	{
		// Save
		this.world = world;
		
		// Init
		this.round = 0;
		this.lastUpdateTime = 0;
		this.viewState = new ViewState( this.world.getCenterMap( ) );
	}

	/**
	 * @return the round
	 */
	public long getRound( )
	{
		return round;
	}

	/**
	 * @return the viewState
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
	 * Is update ready?
	 * 
	 * @return if update ready
	 */
	public boolean isUpdateReady( )
	{
		return ( ( Time.getTicks( ) - this.lastUpdateTime ) >= this.viewState.getDelayBetweenFrames( ) );
	}
	
	/**
	 * The update has been done
	 */
	public void updateDone( )
	{
		this.lastUpdateTime = Time.getTicks( );
	}
}
