package simulation.world.animal.species.state;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import simulation.ViewState;
import simulation.math.angle.AngleMovement;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.animal.AnimalHelper;
import simulation.world.animal.group.Group;
import simulation.world.animal.need.state.NeedState;
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
	private NeedState needState;
	
	/**
	 * Current position of this animal on the map
	 */
	private AngleMovement position;
	
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
		this.needState = new NeedState( animal.getNeedDefinition( ) );
		this.position = new AngleMovement( AnimalHelper.calculateAnimalSpeed( animal.getAgility( ) ),
			initialPosition );
		this.birthDate = currentTurn;
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
	 * Update view
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
		this.position.aimPosition( position );
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
		// Get future position
		Point<Double> futurePosition = this.position.calculateFuturePosition( );
		
		// Check position
		if( !this.groupReference.getGroupRange( ).contains( new Point<Double>( this.groupReference.getGroupRange( ).getPosition( ).getX( ) - this.groupReference.getRangeDiameter( ) / 2.0d + futurePosition.getX( ),
			this.groupReference.getGroupRange( ).getPosition( ).getY( ) - this.groupReference.getRangeDiameter( ) / 2.0d + futurePosition.getY( ) ) ) )
			this.position.stopMoving( );
				
		// Update position
		this.position.update( );
	}
}
