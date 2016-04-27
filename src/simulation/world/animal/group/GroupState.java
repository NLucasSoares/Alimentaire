package simulation.world.animal.group;

import java.util.ArrayList;

import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.animal.group.mortalityRate.MortalityRate;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.environment.plant.PlantGroup;

/**
 * The current state of a group of animals.
 * 
 * @author SOARES Lucas
 */
public class GroupState
{	
	/**
	 * The mortality rate
	 */
	private MortalityRate mortalityRate;
	
	/**
	 * The state of animals
	 */
	private ArrayList<AnimalState> animalState;
	
	/**
	 * Group range diameter
	 */
	private int rangeDiameter;
	
	/**
	 * Group position
	 */
	private Point<Double> position;
	
	/**
	 * The animal definition
	 */
	private AbstractAnimal animal;

	/**
	 * Construct the group state
	 * 
	 * @param maximumFellowNumber
	 * 		The maximum fellow number
	 */
	public GroupState( int initialFellowCount,
		AbstractAnimal animal )
	{
		// Save
		this.animal = animal;
		
		// Init
		this.mortalityRate = new MortalityRate( );
		this.animalState = new ArrayList<AnimalState>( );
		this.position = new Point<Double>( 0.0d,
			0.0d );
		
		// Calculate diameter depending initial fellow count
		this.updateRangeDiameter( initialFellowCount );
		
		// Create initial animals state
		
	}

	/**
	 * Update range diameter, with a specified
	 * fellow count
	 * 
	 * @param fellowCount
	 * 		The fellow count
	 */
	private void updateRangeDiameter( int fellowCount )
	{
		this.rangeDiameter = fellowCount * this.animal.getSize( ) * this.animal.getAgility( );		
	}
	
	/**
	 * Update range diameter
	 */
	private void updateRangeDiameter( )
	{
		this.updateRangeDiameter( this.animalState.size( ) );
	}

	/**
	 * @return the mortality rate
	 */
	public MortalityRate getMortalityRate( )
	{
		return mortalityRate;
	}

	/**
	 * @return the animal state
	 */
	public ArrayList<AnimalState> getAnimalState( )
	{
		return animalState;
	}
	
	/**
	 * Update the group
	 */
	public void update( )
	{
		// Update the diameter according to fellow count
		this.updateRangeDiameter( );
	}
	
	/**
	 * Create group range (Circle)
	 * 
	 * @return the range
	 */
	public Circle createGroupRange( )
	{
		return new Circle( new Point<Double>( this.position.getX( ),
				this.position.getY( ) ),
			(double)this.rangeDiameter / 2.0d );
	}
	
	/**
	 * Is interesect with a plant group?
	 * 
	 * @param plantGroup
	 * 		The plant group
	 * 
	 * @return if intersect exists
	 */
	public boolean isInteresect( PlantGroup plantGroup )
	{
		return this.createGroupRange( ).intersects( plantGroup.createGroupRange( ) );
	}

	/**
	 * Is interesect with an animal group?
	 * 
	 * @param animal
	 * 		The animal group
	 */
	public boolean isInteresect( Group animalGroup )
	{
		return this.createGroupRange( ).intersects( animalGroup.getState( ).createGroupRange( ) );
	}
}
