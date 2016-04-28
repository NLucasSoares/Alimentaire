package simulation.world.animal.group;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

import simulation.ViewState;
import simulation.constant.SimulationConstant;
import simulation.gui.object.Hexagon;
import simulation.math.angle.AngleMovement;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.animal.group.mortalityRate.MortalityRate;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.environment.Map;
import simulation.world.environment.plant.PlantGroup;

/**
 * A group of animals, as described by animal, which is a species, and
 * groupState, which is the current state of this group.
 * 
 * @author SOARES Lucas
 */
public class Group
{
	/**
	 * The description of animals in the group
	 */
	private AbstractAnimal animalDefinition;
	
	/**
	 * The mortality rate
	 */
	private MortalityRate mortalityRate;
	
	/**
	 * The state of animals
	 */
	private ArrayList<AnimalState> animals;
	
	/**
	 * Group range diameter
	 */
	private int rangeDiameter;
	
	/**
	 * Group position
	 */
	private AngleMovement position;
	
	/**
	 * Painting shape
	 */
	private Shape shape;

	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of ground fellows
	 */
	public Group( AbstractAnimal animal,
		int initialFellowCount )
	{
		// Construct base
		this( animal,
			initialFellowCount,
			null );

		// The position
		Point<Double> initialPosition;
		
		// Map hexagon sample
		Hexagon h = new Hexagon( Map.SIZE_PIXEL_BY_SIZE_UNIT );
		
		// Look for correct initial position
		do
		{
			initialPosition = new Point<Double>( simulation.math.Operation.random( 0.0d,
					Map.SIZE_PIXEL_BY_SIZE_UNIT ),
				simulation.math.Operation.random( 0.0d,
					Map.SIZE_PIXEL_BY_SIZE_UNIT ) );
		} while( !h.isContaining( initialPosition.getX( ).intValue( ),
				initialPosition.getY( ).intValue( ) ) );
		
		// Init position
		this.position = new AngleMovement( this.calculateGroupSpeed( ),
			initialPosition,
			false );
	}
	
	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of group fellows
	 * @param initialPosition
	 * 		The initial position
	 */
	public Group( AbstractAnimal animal,
		int initialFellowCount,
		AngleMovement initialPosition )
	{
		// Save
		this.animalDefinition = animal;
		
		// Init
		this.mortalityRate = new MortalityRate( );
		this.animals = new ArrayList<AnimalState>( );
		this.position = initialPosition;
		
		// Calculate diameter depending initial fellow count
		this.updateRangeDiameter( initialFellowCount );
		
		// Create animals properties
		this.initialRandomPositionning( initialFellowCount );
	}
	
	/**
	 * Initial random positionning of the animals
	 * 
	 * @param initialFellowCount
	 * 		The number of animal to create
	 */
	private void initialRandomPositionning( int initialFellowCount )
	{
		// Create an ellipse sample
		Shape shape = new Ellipse2D.Double( 0,
			0,
			this.rangeDiameter,
			this.rangeDiameter );
				
		// Add fellows
		for( int i = 0; i < initialFellowCount; i++ )
		{
			// The animal position
			Point<Double> position;
			
			// Find position
			do
			{
				// Create position
				position = new Point<Double>( simulation.math.Operation.random( 0.0d,
						this.rangeDiameter ),
					simulation.math.Operation.random( 0.0d,
						this.rangeDiameter ) );
			} while( !shape.contains( position.getX( ),
				position.getY( ) ) );
			
			// Add
			this.animals.add( new AnimalState( this,
				position,
				this.animalDefinition,
				0 ) );
		}
	}
	
	/**
	 * @return the animal for this group
	 */
	public AbstractAnimal getAnimal( )
	{
		return this.animalDefinition;
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
		this.rangeDiameter = fellowCount * ( this.animalDefinition.getSize( ) * this.animalDefinition.getAgility( ) ) / SimulationConstant.GROUP_RANGE_BY_FACTOR;
	}
	
	/**
	 * Update range diameter
	 */
	private void updateRangeDiameter( )
	{
		this.updateRangeDiameter( this.animals.size( ) );
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
		return animals;
	}
	
	/**
	 * @return the position
	 */
	public Point<Double> getPosition( )
	{
		return this.position.getPosition( );
	}
	
	/**
	 * @return the range
	 */
	public int getRangeDiameter( )
	{
		return this.rangeDiameter;
	}
	
	/**
	 * @return the painting shape
	 */
	public Shape getShape( )
	{
		return this.shape;
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
		return new Circle( new Point<Double>( this.position.getPosition( ).getX( ),
				this.position.getPosition( ).getY( ) ),
			(double)this.rangeDiameter / 2.0d );
	}
	
	/**
	 * Calculte the speed of the group
	 * 
	 * @return the group speed
	 */
	public double calculateGroupSpeed( )
	{
		return 0.01d;
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
		return this.createGroupRange( ).intersects( animalGroup.createGroupRange( ) );
	}
	
	/**
	 * Update shape
	 * 
	 * @param viewState
	 * 		The current view state
	 * @param hexagon
	 * 		The hexagon representing the map where the
	 * group is onto
	 */
	public void updateView( ViewState viewState,
		Hexagon hexagon )
	{
		// For the group range
			// Calculate
				double ellipseSize = ( ( (double)this.rangeDiameter ) * (double)viewState.getZoomLevel( ) );
				double x = ( ( (double)this.getPosition( ).getX( ) * viewState.getZoomLevel( ) ) + hexagon.getPosition( ).getX( ) ) - ( ellipseSize / 2.0d );
				double y = ( ( (double)this.getPosition( ).getY( ) * viewState.getZoomLevel( ) ) + hexagon.getPosition( ).getY( ) ) - ( ellipseSize / 2.0d );
			// Create the shape
				this.shape = new Ellipse2D.Double( x,
					y,
					ellipseSize,
					ellipseSize );
			
		// For animals
		for( Iterator<AnimalState> it = this.animals.iterator( ); it.hasNext( ); )
			it.next( ).updateView( viewState,
				this.shape );
	}
	
}
