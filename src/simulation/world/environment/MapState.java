package simulation.world.environment;

import java.util.ArrayList;
import java.util.Iterator;

import simulation.constant.SimulationConstant;
import simulation.math.Operation;
import simulation.math.hexagon.Hexagon;
import simulation.math.point.Point;
import simulation.math.probability.Experience;
import simulation.world.animal.group.Group;
import simulation.world.environment.biome.resource.field.FieldResource;
import simulation.world.environment.biome.resource.state.ResourceState;
import simulation.world.environment.decomposer.Decomposer;
import simulation.world.plant.PlantGroup;
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
	 * Reference to map
	 */
	private Map map;
	
	/**
	 * Construct the map state from map
	 * callback
	 * 
	 * @param map
	 * 		Reference to current map
	 */
	public MapState( Map map )
	{
		// Save
		this.map = map;
		
		// Init
		this.resourceState = new ResourceState( 1000,
			1000 );
		this.decomposer = new Decomposer( );
		this.animalGroup = new ArrayList<Group>( );
		this.fieldResource = new ArrayList<FieldResource>( );
		this.plantGroup = new ArrayList<PlantGroup>( );
	}
	
	/**
	 * @return the plant group
	 */
	public Iterator<PlantGroup> getPlantGroup( )
	{
		return this.plantGroup.iterator( );
	}
	/**
	 * Update the state
	 */
	public void update( )
	{
		// The decomposer do their job
		this.resourceState.addNitrogen( this.decomposer.work( this.fieldResource ) );
		
		// The plant update themselves
		for( Iterator<PlantGroup> it = this.plantGroup.iterator( ); it.hasNext( ); )
		{
			// Get the plant group
			PlantGroup pg = it.next( );
			
			// Update
			pg.update( this.resourceState );
			
			// Check leaves count
			if( pg.getLeaves( ) <= 0 )
				it.remove( );
		}
		
		// Plant group spawn
			// Create the experience
				Experience e = new Experience( SimulationConstant.PLANT_SPAWN_PROBABILITY );
			// Do the experience
				e.doExperience( );
			// Act according to result
				if( e.getEventID( ) == 0 )
				{
					// Calculate size
					double wPlant = Hexagon.calculateWidth( Map.SIZE_PIXEL_BY_SIZE_UNIT );
					double hPlant = Hexagon.calculateHeight( Map.SIZE_PIXEL_BY_SIZE_UNIT );
					
					// Create hexagon copy
					simulation.gui.object.Hexagon hexagonCopy = new simulation.gui.object.Hexagon( Map.SIZE_PIXEL_BY_SIZE_UNIT );
					
					// Determine where allowed to plant
					Point<Integer> position;
					do
					{
						position = new Point<Integer>( (int)( Operation.random( 1,
								wPlant ) ),
							(int)( Operation.random( 1,
								hPlant ) ) );
					} while( !hexagonCopy.isContaining( position ) );
					
					// Plant
					this.plantGroup.add( new PlantGroup( position,
						new Need( 10,
							10,
							10 ) ) ); // HAVE TO SEE FOR NEED DETAILS
				}
	}
	
	
	
}