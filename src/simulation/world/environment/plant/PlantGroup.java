package simulation.world.environment.plant;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import simulation.ViewState;
import simulation.gui.object.Hexagon;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.aim.AimedObject;
import simulation.world.environment.biome.resource.state.ResourceState;
import simulation.world.plant.need.Need;
import simulation.world.plant.need.state.NeedState;

/**
 * Plant Group
 * 
 * @author CAMPS Aurèle
 */
public class PlantGroup implements AimedObject
{
	/**
	 * The number of leaves for one stage in plant group
	 */
	private static final int LEAVES_BY_STAGE = 5;
	
	/**
	 * Maximum stage for one plant group
	 */
	private static final int MAXIMUM_STAGES = 30;

	/**
	 * The amount of leaves of a plant group
	 */
	private int leaves;

	/**
	 * The needs state of a plant group
	 */
	private NeedState needsState;

	/**
	 * The needs definition of a plant group
	 */
	private Need needsDefinition;

	/**
	 * The diameter of the plant group
	 */
	private int diameter;
	
	/**
	 * Position
	 */
	private Point<Double> position;
	
	/**
	 * Group range
	 */
	private Circle groupRange;
	
	/**
	 * Painting shape
	 */
	private Shape shape;

	/**
	 * Construct the plant group
	 */
	public PlantGroup( Point<Double> position,
		Need needsDefinition )
	{
		// Init
		this.leaves = 1;
		this.needsState = new NeedState( );
		
		// Save
		this.position = position;
		this.needsDefinition = needsDefinition;
		
		// Calculate
			// Diameter
				this.diameter = this.calculateDiameter( );
				
		// Create group range
		this.createGroupRange( );
	}
	
	/**
	 * Calculate the diameter
	 * 
	 * @return the diameter
	 */
	private int calculateDiameter( )
	{
		// Result
		int result = ( ( this.leaves / LEAVES_BY_STAGE ) / 2 ) + 1;
		
		// Return
		return ( result >= MAXIMUM_STAGES ) ? MAXIMUM_STAGES : result;
	}

	/**
	 * @return the leaves
	 */
	public int getLeaves( )
	{
		return leaves;
	}

	/**
	 * @return the needsState
	 */
	public NeedState getNeedsState( )
	{
		return needsState;
	}

	/**
	 * @return the diameter
	 */
	public int getDiameter( )
	{
		return diameter;
	}
	
	/**
	 * @return the position
	 */
	public Point<Double> getPosition( )
	{
		return position;
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
	 * Update the plant group
	 * 
	 * @param resourceState
	 * 		The resources state
	 */
	public void update( ResourceState resourceState )
	{
		// What to be eaten (nitrogen)
		double nitrogenToBeConsume = this.needsDefinition.getNitrogen( ) / ( ( (double)( MAXIMUM_STAGES * LEAVES_BY_STAGE ) - (double)this.leaves ) + 1.0d );
		
		// TODO
		// Consume
		//try
		//{
			// Consume
			//resourceState.consumeNitrogen( nitrogenToBeConsume );

			// One more leaf
			if( this.leaves < MAXIMUM_STAGES * LEAVES_BY_STAGE )
				this.leaves++;
		//}
		//catch( NoMoreResourceException e )
		//{
		//	this.leaves--;
		//}
		
		// Calculate diameter
		this.diameter = this.calculateDiameter( );
		
		// Create group range
		this.createGroupRange( );
	}
	
	/**
	 * Update view point
	 * 
	 * @param viewState
	 * 		The current viewstate
	 * @param hexagon
	 * 		The hexagon representing the map where
	 * the group is currently on
	 */
	public void updateView( ViewState viewState,
		Hexagon hexagon )
	{
		// Calculate
		double ellipseSize = ( ( (double)this.diameter ) * (double)viewState.getZoomLevel( ) );
		double x = ( ( (double)this.getPosition( ).getX( ) * viewState.getZoomLevel( ) ) + hexagon.getPosition( ).getX( ) ) - ( ellipseSize / 2.0d );
		double y = ( ( (double)this.getPosition( ).getY( ) * viewState.getZoomLevel( ) ) + hexagon.getPosition( ).getY( ) ) - ( ellipseSize / 2.0d );
	
		// Create the shape
		this.shape = new Ellipse2D.Double( x,
			y,
			ellipseSize,
			ellipseSize );
	}
	
	/**
	 * Create group range (circle)
	 * 
	 * @return the range
	 */
	private void createGroupRange( )
	{
		this.groupRange = new Circle( new Point<Double>( (double)this.position.getX( ),
				(double)this.position.getY( ) ),
			(double)this.diameter / 2.0d );
	}
}