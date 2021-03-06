package simulation.world.animal.group;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;

import simulation.ViewState;
import simulation.constant.SimulationConstant;
import simulation.gui.animation.Annotation;
import simulation.gui.object.Hexagon;
import simulation.math.angle.AngleMovement;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.aim.AimedObject;
import simulation.world.animal.AnimalHelper;
import simulation.world.animal.group.mortalityRate.MortalityRate;
import simulation.world.animal.species.AbstractAnimal;
import simulation.world.animal.species.Carnivorous;
import simulation.world.animal.species.Herbivorous;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.animal.species.state.CarnivorousState;
import simulation.world.animal.species.state.HerbivorousState;
import simulation.world.environment.Map;
import simulation.world.environment.MapState;
import simulation.world.environment.plant.PlantGroup;

/**
 * A group of animals, as described by animal, which is a species, and
 * groupState, which is the current state of this group.
 * 
 * @author SOARES Lucas
 */
public abstract class Group implements AimedObject
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
	 * Map reference
	 */
	private Map map;
	
	/**
	 * Group range diameter
	 */
	private int rangeDiameter;
	
	/**
	 * Group range circle
	 */
	private Circle groupRange;
	
	/**
	 * Annotation
	 */
	private Annotation annotation;
	
	/**
	 * Group position
	 */
	private AngleMovement position;
	
	/**
	 * Currently aimed entitie
	 */
	private AimedObject aimedObject;
	
	/**
	 * Painting shape
	 */
	private Shape shape;
	
	/**
	 * Next turn group update
	 */
	private NextTurnGroupUpdate nextTurnGroupUpdate;

	/**
	 * Construct the group
	 * 
	 * @param animal
	 * 		The animal which group is composed of
	 * @param initialFellowCount
	 * 		The initial count of ground fellows
	 */
	public Group( AbstractAnimal animal,
		int initialFellowCount,
		Map map )
	{
		// Construct base
		this( animal,
			initialFellowCount,
			null,
			map );

		// The position
		Point<Double> initialPosition;
		
		// Map hexagon sample
		Hexagon h = new Hexagon( Map.SIZE_PIXEL_BY_SIZE_UNIT );
		
		// Look for correct initial position
		do
		{
			initialPosition = new Point<Double>( simulation.math.probability.Operation.random( 0.0d,
					Map.SIZE_PIXEL_BY_SIZE_UNIT ),
				simulation.math.probability.Operation.random( 0.0d,
					Map.SIZE_PIXEL_BY_SIZE_UNIT ) );
		} while( !h.isContaining( initialPosition.getX( ).intValue( ),
				initialPosition.getY( ).intValue( ) ) );
		
		// Init position
		this.position = new AngleMovement( this.calculateGroupSpeed( ),
			initialPosition );
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
		AngleMovement initialPosition,
		Map map )
	{
		// Save
		this.animalDefinition = animal;
		this.map = map;
		
		// Init
		this.mortalityRate = new MortalityRate( );
		this.animals = new ArrayList<AnimalState>( );
		this.position = initialPosition;
		this.aimedObject = null;
		this.annotation = new Annotation( 1,
			0xFFFFFFFF );
		this.nextTurnGroupUpdate = new NextTurnGroupUpdate( );
		
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
				position = new Point<Double>( simulation.math.probability.Operation.random( 0.0d,
						this.rangeDiameter ),
					simulation.math.probability.Operation.random( 0.0d,
						this.rangeDiameter ) );
			} while( !shape.contains( position.getX( ),
				position.getY( ) ) );
			
			// Add
				// Declaration
					AnimalState animalState = null;
				// Create
					if( this.animalDefinition instanceof Carnivorous )
						animalState = new CarnivorousState( this,
							position,
							this.animalDefinition,
							0 );
					else if( this.animalDefinition instanceof Herbivorous )
						animalState = new HerbivorousState( this,
							position,
							this.animalDefinition,
							0 );
				// Add
					this.animals.add( animalState );
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
	 * @return the animal count
	 */
	public int getAnimalCount( )
	{
		return this.animals.size( );
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
		// Range
		int range = fellowCount * ( this.animalDefinition.getSize( ) * this.animalDefinition.getAgility( ) ) / SimulationConstant.GROUP_RANGE_BY_FACTOR;
		
		// Check minimum
		range = range < SimulationConstant.MINIMUM_RANGE_ANIMAL_GROUP ? SimulationConstant.MINIMUM_RANGE_ANIMAL_GROUP : range;
		
		// Set range
		this.rangeDiameter = ( range >= SimulationConstant.MAXIMUM_RANGE_ANIMAL_GROUP ) ?
				SimulationConstant.MAXIMUM_RANGE_ANIMAL_GROUP
				: range;
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
	 * @return the next
	 */
	public NextTurnGroupUpdate getNextTurnGroupUpdate( )
	{
		return this.nextTurnGroupUpdate;
	}
	
	/**
	 * @return the painting shape
	 */
	public Shape getShape( )
	{
		return this.shape;
	}
	
	/**
	 * @return the group range
	 */
	public Circle getGroupRange( )
	{
		return this.groupRange;
	}
	
	/**
	 * @return the map callback
	 */
	public Map getMap( )
	{
		return this.map;
	}
	
	/**
	 * @return the annotation
	 */
	public Annotation getAnnotation( )
	{
		return this.annotation;
	}
	
	/**
	 * @return if is moving
	 */
	public boolean isMoving( )
	{
		return this.position.isMoving( );
	}

	/**
	 * Update the group
	 * 
	 * @param state
	 * 		The current map state
	 */
	public void update( MapState state )
	{
		// Update the diameter according to fellow count
		this.updateRangeDiameter( );
		
		// Create group range
		this.createGroupRange( );
		
		// Aim
		if( this.aimedObject != null )
		{
			// Check if we've reached goal
				// Create goal circle
					Circle goalCircle = new Circle( this.aimedObject.getPosition( ),
						SimulationConstant.POSITION_AIMING_PRECISION );
				// Check goal
					if( goalCircle.contains( this.getPosition( ) ) )
					{
						// If plant group it won't move so remove
						if( this.aimedObject instanceof PlantGroup )
							this.aimedObject = null;
					}
				// Aim object
					else
						this.aimObject( );
		}

		// Update position
		this.position.update( );
		
		// To remove fellows list
		ArrayList<AnimalState> removeList = new ArrayList<AnimalState>( );
		
		// Update fellows
		for( Iterator<AnimalState> it = this.animals.iterator( ); it.hasNext( ); )
		{
			// Get animal
			AnimalState animal = it.next( );

			// Update the animal
			animal.update( );

			// Check if alive
			if( !animal.isAlive( )
				|| state.getWorldState( ).getRound( ) - animal.getBirthDate( ) >= animal.getLifeTime( ) )
			{
				// Add a field resource
				state.addFieldResource( animal );

				// Add annotation
				this.annotation.addMessage( "- 1" );
				
				// Remove of the group
				removeList.add( animal );
				
				// Continue
				continue;
			}
			
			// Activate reproduce look for
			if( this.animals.size( ) < this.animalDefinition.getMaximumDensity( ) )
				// Check for good animal condition
				if( ( animal.getHealthState( ).getProtein( ) / animal.getAnimal( ).getNeedDefinition( ).getProtein( ) ) * 100.0d >= (double)SimulationConstant.FLOOR_FOR_PROTEIN_STOCK_REPRODUCING
					&& this.getMap( ).getState( ).getWorldState( ).getRound( ) - animal.getLastRoundGivenBirth( ) >= animal.getAnimal( ).getReproduceTime( ) )
					animal.activateReproduceResearch( );
		}

		// Check if group is full
		if( this.animals.size( ) >= this.animalDefinition.getMaximumDensity( ) )
		{
			if( this.getMap( ).getState( ).getGroupCount( ) < SimulationConstant.MAXIMUM_GROUP_BY_MAP
				&& ( this instanceof HerbivorousGroup ?
						this.getMap( ).getState( ).getHerbivorousGroupCount( ) < ( SimulationConstant.MAXIMUM_GROUP_BY_MAP / 2 )
						: true )
				&& ( this instanceof CarnivorousGroup ?
						this.getMap( ).getState( ).getCarnivorousGroupCount( ) < ( SimulationConstant.MAXIMUM_GROUP_BY_MAP / 10 )
						: true ) )
			{
				// New group size
				int newGroupSize = this.animals.size( ) / 2;
				
				// Create new group
				Group group = ( this instanceof HerbivorousGroup ) ?
					new HerbivorousGroup( this.getAnimal( ),
							newGroupSize,
						new AngleMovement( AnimalHelper.calculateAnimalSpeed( this.animalDefinition.getAgility( ) ),
							this.animals.get( 0 ).getPosition( ) ),
						this.map )
					: new CarnivorousGroup( this.getAnimal( ),
							newGroupSize,
						new AngleMovement( AnimalHelper.calculateAnimalSpeed( this.animalDefinition.getAgility( ) ),
							this.animals.get( 0 ).getPosition( ) ),
						this.map );
					
				// Set position
				group.position.setPosition( this.position.getPosition( ) );
				
				// Remove fellows from current group
				for( int i = 0; i < newGroupSize; i++ )
					this.animals.remove( 0 );

				// Group to add
				this.nextTurnGroupUpdate.addGroup( group );
			}
		}
		
		// Remove deads fellows
		for( Iterator<AnimalState> it = removeList.iterator( ); it.hasNext( ); )
			this.animals.remove( it.next( ) );
		
		// Add the animals requested
		for( Iterator<AnimalState> it = this.nextTurnGroupUpdate.getAnimalToAdd( ).iterator( ); it.hasNext( ); )
			this.animals.add( it.next( ) );
		
		// Empty adding list
		this.nextTurnGroupUpdate.emptyAddingList( );
	}
	
	/**
	 * Update desynchronized
	 */
	public void updateDesynchronized( )
	{
		// Update state
		this.annotation.update( );
	}
	
	/**
	 * Add animal due to reproducing result
	 * 
	 * @param parent
	 * 		One of the parent
	 */
	public void addReproduceResult( AnimalState parent )
	{
		// If limit not reached
		if( this.animals.size( ) < this.animalDefinition.getMaximumDensity( ) )
		{
			// Create animal
			AnimalState animal = ( this.animalDefinition instanceof Herbivorous ) ?
				new HerbivorousState( this,
					parent.getPosition( ),
					this.animalDefinition,
					this.getMap( ).getState( ).getWorldState( ).getRound( ) )
				: new CarnivorousState( this,
						parent.getPosition( ),
					this.animalDefinition,
					this.getMap( ).getState( ).getWorldState( ).getRound( ) );
	
			// Add animal
			this.nextTurnGroupUpdate.addAnimal( animal );
			
			// Add annotation
			this.annotation.addMessage( "+ 1" );
		}
	}
	
	/**
	 * Create group range (Circle)
	 */
	private void createGroupRange( )
	{
		this.groupRange = new Circle( new Point<Double>( this.position.getPosition( ).getX( ),
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
		return 0.05d;
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
		return this.groupRange.intersects( plantGroup.getGroupRange( ) );
	}

	/**
	 * Is interesect with an animal group?
	 * 
	 * @param animal
	 * 		The animal group
	 */
	public boolean isInteresect( Group animalGroup )
	{
		return this.groupRange.intersects( animalGroup.getGroupRange( ) );
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
		
		// Update annotation position
		this.annotation.setPosition( new Point<Integer>( (int)x + (int)( ellipseSize / 2.0d ),
			(int)y + (int)( ellipseSize / 2.0d ) ) );
	}
	
	/**
	 * Start moving
	 */
	public void startMoving( )
	{
		this.position.startMoving( );
	}
	
	/**
	 * Stop moving
	 */
	public void stopMoving( )
	{
		this.position.stopMoving( );
	}
	
	/**
	 * Aim a position
	 * 
	 * @param position
	 * 		The position to aim
	 */
	public void aimPosition( Point<Double> position )
	{
		// Aim
		this.position.aimPosition( position );
	}
	
	/**
	 * Aim a plant group
	 * 
	 * @param p
	 * 		The plant group to aim
	 */
	public void aimPlantGroup( PlantGroup p )
	{
		// Save what we aim
		this.aimedObject = p;
		
		// Aim
		this.aimObject( );
	}
	
	/**
	 * Aim an animal group
	 */
	public void aimAnimalGroup( Group g )
	{
		// Save what we aim
		this.aimedObject = g;
		
		// Aim
		this.aimObject( );
	}
	
	/**
	 * Aim currently aimed object
	 */
	private void aimObject( )
	{
		this.position.aimPosition( new Point<Double>( (double)this.aimedObject.getPosition( ).getX( ),
			(double)this.aimedObject.getPosition( ).getY( ) ) );
	}
	
	/**
	 * Remove aiming object
	 */
	public void removeAimOnObject( )
	{
		this.aimedObject = null;
	}
}
