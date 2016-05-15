package simulation.world.environment;

import java.util.ArrayList;
import java.util.Iterator;

import simulation.constant.SimulationConstant;
import simulation.math.hexagon.Hexagon;
import simulation.math.point.Point;
import simulation.math.probability.Experience;
import simulation.world.WorldState;
import simulation.world.animal.group.CarnivorousGroup;
import simulation.world.animal.group.Group;
import simulation.world.animal.group.HerbivorousGroup;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.environment.biome.resource.field.FieldResource;
import simulation.world.environment.biome.resource.state.ResourceState;
import simulation.world.environment.decomposer.Decomposer;
import simulation.world.environment.plant.PlantGroup;
import simulation.world.plant.need.Need;

/**
 *	Current state of the map.
 *
 *	@author SOARES Lucas
 */
public class MapState
{
	/**
	 * Resource state
	 */
	private ResourceState resourceState;
	
	/**
	 * Decomposer
	 */
	private Decomposer decomposer;
	
	/**
	 * Field resources
	 */
	private ArrayList<FieldResource> fieldResource;
	
	/**
	 * Groups of animals
	 */
	private ArrayList<Group> animalGroup;
	
	/**
	 * Plant groups
	 */
	private ArrayList<PlantGroup> plantGroup;
	
	/**
	 * World state reference
	 */
	private WorldState worldState;
	
	/**
	 * Construct the map state from map
	 * callback
	 * 
	 * @param map
	 * 		Reference to current map
	 */
	public MapState( )
	{
		// Init
		this.resourceState = new ResourceState( 10000,
			10000 );
		this.decomposer = new Decomposer( );
		this.animalGroup = new ArrayList<Group>( );
		this.fieldResource = new ArrayList<FieldResource>( );
		this.plantGroup = new ArrayList<PlantGroup>( );
		this.worldState = null;
	}
	
	/**
	 * @return the world state reference
	 */
	public WorldState getWorldState( )
	{
		return this.worldState;
	}
	
	/**
	 * @return an iterator on the plant group
	 */
	public Iterator<PlantGroup> getPlantGroup( )
	{
		return this.plantGroup.iterator( );
	}
	
	/**
	 * @return an iterator on the animal group
	 */
	public Iterator<Group> getAnimalGroup( )
	{
		return this.animalGroup.iterator( );
	}
	
	/**
	 * @return an iterator on the field resource
	 */
	public Iterator<FieldResource> getFieldResource( )
	{
		return this.fieldResource.iterator( );
	}
	
	/**
	 * @return the total animal count
	 */
	public long getAnimalCount( )
	{
		// Output
		long output = 0;
		
		// Calculate
		for( Iterator<Group> it = this.animalGroup.iterator( ); it.hasNext( ); )
			output += it.next( ).getAnimalCount( );
		
		// OK
		return output;
	}
	
	/**
	 * @return the plant group count
	 */
	public long getPlantGroupCount( )
	{
		return this.plantGroup.size( );
	}
	
	/**
	 * Update the state
	 */
	public void update( )
	{
		// The decomposer do their job
		this.resourceState.addNitrogen( this.decomposer.work( this ) );
		
		// The plant group update themselves
		for( Iterator<PlantGroup> it = this.plantGroup.iterator( ); it.hasNext( ); )
		{
			// Get the plant group
			PlantGroup pg = it.next( );
			
			// Update
			pg.update( this.resourceState,
				this.decomposer );
			
			// Check leaves count
			if( pg.getLeaves( ) <= 0 )
				it.remove( );
		}
		
		// Plant group spawn
		if( this.plantGroup.size( ) < SimulationConstant.MAXIMUM_PLANT_GROUP_COUNT_MAP )
		{
			// Create the experience
				Experience e = new Experience( SimulationConstant.PLANT_SPAWN_PROBABILITY );
			// Do the experience
				e.doExperience( );
			// Act according to result
				if( e.getEventID( ) == 0 )
				{
					// Get random position
					Point<Double> position = Hexagon.getRandomPosition( (double)Map.SIZE_PIXEL_BY_SIZE_UNIT );
					
					// Plant
					this.plantGroup.add( new PlantGroup( position,
						new Need( 1,
							1,
							1 ) ) );
				}
		}
		
		// Group to add
		ArrayList<Group> groupToAdd = new ArrayList<Group>( );
		
		// Group to remove
		ArrayList<Group> groupToRemove = new ArrayList<Group>( );
		
		// Update animal groups
		for( Iterator<Group> iterator = this.animalGroup.iterator( ); iterator.hasNext( ); )
		{
			// Group
			Group g = iterator.next( );
			
			// Update
			g.update( this );
			
			// Add group to add if requested
			if( g.getNextTurnGroupUpdate( ).getGroupToAdd( ) != null )
			{
				// Add
				groupToAdd.add( g.getNextTurnGroupUpdate( ).getGroupToAdd( ) );
				
				// Remove
				g.getNextTurnGroupUpdate( ).removeGroupToAdd( );
			}
			
			// Check group empty
			if( g.getAnimalCount( ) <= 0 )
				groupToRemove.add( g );
		}
		
		// Remove empty groups
		for( Iterator<Group> it = groupToRemove.iterator( ); it.hasNext( ); )
			this.animalGroup.remove( it.next( ) );
		
		// Add the group if requested
		for( Iterator<Group> it = groupToAdd.iterator( ); it.hasNext( ); )
			this.animalGroup.add( it.next( ) );
		
		// Update field resources
		for( Iterator<FieldResource> iterator = this.fieldResource.iterator( ); iterator.hasNext( ); )
		{
			// Get field resource
			FieldResource fr = iterator.next( );
			
			// Update
			fr.update( );
			
			// Check protein quantity
			if( fr.getProteinQuantity( ) <= 0 )
				iterator.remove( );
		}
	}
	
	/**
	 * @return group count
	 */
	public int getGroupCount( )
	{
		return this.animalGroup.size( );
	}
	
	/**
	 * @return the herbivorous group count
	 */
	public int getHerbivorousGroupCount( )
	{
		// Result
		int output = 0;
		
		// Count
		for( Iterator<Group> it = this.animalGroup.iterator( ); it.hasNext( ); )
			if( it.next( ) instanceof HerbivorousGroup )
				output++;
		
		// Return result
		return output;
	}
	
	/**
	 * @return the carnivorous group count
	 */
	public int getCarnivorousGroupCount( )
	{
		// Result
		int output = 0;
		
		// Count
		for( Iterator<Group> it = this.animalGroup.iterator( ); it.hasNext( ); )
			if( it.next( ) instanceof CarnivorousGroup )
				output++;
		
		// Return result
		return output;
	}
	
	/**
	 * Update elements that aren't dependant of
	 * round gesture
	 */
	public void updateDesynchronized( )
	{
		// Plant group
		for( Iterator<PlantGroup> it = this.getPlantGroup( ); it.hasNext( ); )
			// Update
			it.next( ).updateDesynchronized( );
		
		// Animal group
		for( Iterator<Group> it = this.getAnimalGroup( ); it.hasNext( ); )
			// Update
			it.next( ).updateDesynchronized( );
	}
	
	/**
	 * Add a group to the map
	 * 
	 * @param group
	 * 		The group to add
	 */
	public void addGroup( Group group )
	{
		// Add the group
		this.animalGroup.add( group );
	}
	
	/**
	 * Add a plant group
	 * 
	 * @param group
	 * 		The plant group to add
	 */
	public void addPlantGroup( PlantGroup group )
	{
		// Add the group
		this.plantGroup.add( group );
	}
	
	/**
	 * Add an animal field resource
	 *
	 * @param animal
	 * 		The animal to add
	 */
	public void addFieldResource( AnimalState a )
	{
		// Add field resource
		this.fieldResource.add( new FieldResource( a.getAnimal( ).getName( ),
			a.getAnimal( ).getSize( ) * a.getAnimal( ).getWeight( ),
			new Point<Double>( a.getPosition( ).getX( ) + a.getGroup( ).getPosition( ).getX( ),
				a.getPosition( ).getY( ) + a.getGroup( ).getPosition( ).getY( ) ) ) );
	}

	/**
	 * Set the world state
	 * 
	 * @param world state
	 * 		The world state to set
	 */
	public void setWorldState( WorldState worldState )
	{
		this.worldState = worldState;
	}
}
