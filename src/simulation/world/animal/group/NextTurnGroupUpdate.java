package simulation.world.animal.group;

import java.util.ArrayList;

import simulation.world.animal.species.state.AnimalState;

/**
 * Things to do at the end of the update
 * for the group
 * 
 * @author SOARES Lucas
 */
public class NextTurnGroupUpdate
{
	/**
	 * Group to add
	 */
	private Group groupToAdd;
	
	/**
	 * Animal to add at the update ending
	 */
	private ArrayList<AnimalState> animalsToAdd;
	
	/**
	 * Construct
	 */
	public NextTurnGroupUpdate( )
	{
		// Init
		this.animalsToAdd = new ArrayList<AnimalState>( );
		this.groupToAdd = null;
	}
	
	/**
	 * Empty adding list
	 */
	public void emptyAddingList( )
	{
		// Empty
		while( this.animalsToAdd.size( ) > 0 )
			this.animalsToAdd.remove( 0 );
	}
	
	/**
	 * Remove group to add
	 */
	public void removeGroupToAdd( )
	{
		this.groupToAdd = null;
	}
	
	/**
	 * Add animal on next turn
	 * 
	 * @param animal
	 * 		The animal to add
	 */
	public void addAnimal( AnimalState animal )
	{
		this.animalsToAdd.add( animal );
	}
	
	/**
	 * Add group
	 * 
	 * @param group
	 * 		The group to add
	 */
	public void addGroup( Group group )
	{
		this.groupToAdd = group;
	}
	
	/**
	 * @return the animals to add
	 */
	public ArrayList<AnimalState> getAnimalToAdd( )
	{
		return this.animalsToAdd;
	}
	
	/**
	 * @return the group
	 */
	public Group getGroupToAdd( )
	{
		return this.groupToAdd;
	}
}
