package simulation.world.animal.species.state;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import simulation.ViewState;
import simulation.math.angle.AngleMovement;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.animal.AnimalHelper;
import simulation.world.animal.group.Group;
import simulation.world.animal.move.AimedPosition;
import simulation.world.animal.species.AbstractAnimal;

/**
 * Current state of a given animal.
 * 
 * @author SOARES Lucas 
 */
public abstract class AnimalState {

	/**
	 * Reference to the group this animal belongs to.
	 */
	private Group groupReference;
	
	/**
	 * Current state of the needs of this animal.
	 */
	private HealthState healthState;
	
	/**
	 * Current position of this animal on the map
	 */
	private AngleMovement position;
	
	/**
	 * Currently aimed position
	 */
	private AimedPosition aimedPosition;
	
	/**
	 * Is random move active?
	 */
	private boolean isRandomMoveActive;
	
	/**
	 * The birth date (in turns)
	 */
	private int birthDate;
	
	/**
	 * The painting shape
	 */
	private Shape shape;

	/**
	 * Construct the animal state
	 * 
	 * @param groupReference
	 * 		The callback to the group
	 * @param animal
	 * 		The animal definition
	 * @param currentTurn
	 * 		The current turn of simulation
	 */
	public AnimalState( Group groupReference,
		Point<Double> initialPosition,
		AbstractAnimal animal,
		int currentTurn )
	{
		// Save
		this.groupReference = groupReference;
		
		// Init
		this.healthState = new HealthState( animal.getNeedDefinition( ) );
		this.position = new AngleMovement( AnimalHelper.calculateAnimalSpeed( animal.getAgility( ) ),
			initialPosition );
		this.birthDate = currentTurn;
		this.aimedPosition = new AimedPosition( );
		this.isRandomMoveActive = false;
	}
	
	/**
	 * @return the position
	 */
	public Point<Double> getPosition( )
	{
		return this.position.getPosition( );
	}
	
	/**
	 * @return the painting shape
	 */
	public Shape getShape( )
	{
		return this.shape;
	}
	
	/**
	 * @return the birth date
	 */
	public int getBirthDate( )
	{
		return this.birthDate;
	}
	
	/**
	 * @return the aimed position
	 */
	public Point<Double> getAimedPosition( )
	{
		return this.aimedPosition.getPosition( );
	}
	
	/**
	 * @return the group reference
	 */
	public Group getGroup( )
	{
		return this.groupReference;
	}
	
	/**
	 * @return is alive?
	 */
	public boolean isAlive( )
	{
		return this.healthState.isAlive( );
	}
	
	/**
	 * @return the animal definition
	 */
	public AbstractAnimal getAnimal( )
	{
		return this.groupReference.getAnimal( );
	}
	
	
	/**
	 * Update view
	 * 
	 * @param viewState
	 * 		The current view state
	 * @param groupShape
	 * 		The shape of the group
	 */
	public void updateView( ViewState viewState,
		Shape groupShape )
	{
		// Calculate
		double ellipseSize = (double)viewState.getZoomLevel( );
		double x = this.getPosition( ).getX( ) * viewState.getZoomLevel( ) + ((Ellipse2D.Double)groupShape).x - ( ellipseSize / 2.0d );
		double y = this.getPosition( ).getY( ) * viewState.getZoomLevel( ) + ((Ellipse2D.Double)groupShape).y - ( ellipseSize / 2.0d );
		
		// Create shape
		this.shape = new Ellipse2D.Double( x,
			y,
			ellipseSize,
			ellipseSize );
	}
	
	/**
	 * Aim a position
	 * 
	 * @param position
	 * 		The position to aim
	 */
	public void aimPosition( Point<Double> position )
	{
		// Save position
		this.aimedPosition.setNewAimedPoint( position );
		
		// Aim
		this.position.aimPosition( position );
		
		// Reset random move
		this.isRandomMoveActive = false;
	}
	
	/**
	 * Activate random move
	 */
	public void activateRandomMove( )
	{
		this.isRandomMoveActive = true;
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
	 * Update
	 */
	public void update( )
	{
		// Check if animal doesn't exit his range
		if( !this.groupReference.getGroupRange( ).contains( new Point<Double>( this.groupReference.getGroupRange( ).getPosition( ).getX( ) - this.groupReference.getRangeDiameter( ) / 2.0d + this.getPosition( ).getX( ),
			this.groupReference.getGroupRange( ).getPosition( ).getY( ) - this.groupReference.getRangeDiameter( ) / 2.0d + this.getPosition( ).getY( ) ) ) )
		{
			// Aim the center
			this.aimPosition( new Point<Double>( this.groupReference.getRangeDiameter( ) / 2.0d,
				this.groupReference.getRangeDiameter( ) / 2.0d ) );
		}
			
		// Are we aiming a point
		if( this.aimedPosition.isAimingSomething( ) )
		{
			// Goal?
			if( this.aimedPosition.isReachedPosition( this.getPosition( ) ) )
			{
				// Reset aim position
				this.aimedPosition.removeAim( );
				
				// Stop moving
				this.stopMoving( );
			}
			// Update aim
			else
				this.position.aimPosition( this.aimedPosition.getPosition( ) );
		}
		else if( this.isRandomMoveActive )
		{
			// Range for movement permission
			Circle range = new Circle( new Point<Double>( 0.0d,
					0.0d ),
				this.groupReference.getRangeDiameter( ) );
			
			// Position
			Point<Double> randomAimedPosition;
			
			// Pick a position
			do
			{
				randomAimedPosition = new Point<Double>( simulation.math.probability.Operation.random( range.getPosition( ).getX( ),
						range.getRadius( ) * 2.0d ),
					simulation.math.probability.Operation.random( range.getPosition( ).getY( ),
						range.getRadius( ) * 2.0d ) );					
			} while( !range.contains( randomAimedPosition ) );

			// Aim that
			this.aimPosition( randomAimedPosition );
			
			// Start moving
			this.startMoving( );
		}
		
		// Update needs
		this.healthState.update( );
		
		// Update position
		this.position.update( );
	}
}
