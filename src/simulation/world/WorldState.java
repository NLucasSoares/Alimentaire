package simulation.world;

import java.util.ArrayList;

import simulation.ViewState;
import simulation.world.animal.group.Group;

public class WorldState
{
	/**
	 * Current round
	 */
	private long round;

	/**
	 * Groups of animals
	 */
	private ArrayList<Group> animalGroup;
	
	/**
	 * The actual viewstate
	 */
	private ViewState viewState;
	
	/**
	 * Callback to world
	 */
	private World world;
	
	/**
	 * Construct the world
	 */
	public WorldState( World world )
	{
		// Save
		this.world = world;
		
		// Init
		this.round = 0;
		this.animalGroup = new ArrayList<Group>( );
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
	 * @return the animalGroup
	 */
	public ArrayList<Group> getAnimalGroup( )
	{
		return animalGroup;
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
}
