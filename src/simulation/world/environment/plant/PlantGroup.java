package simulation.world.environment.plant;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import simulation.ViewState;
import simulation.constant.SimulationConstant;
import simulation.gui.animation.Annotation;
import simulation.gui.object.Hexagon;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.world.aim.AimedObject;
import simulation.world.environment.biome.resource.NoMoreResourceException;
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
	 * Annotation
	 */
	private Annotation annotation;

	/**
	 * Construct the plant group
	 */
	public PlantGroup( Point<Double> position,
		Need needsDefinition )
	{
		// Init
		this.leaves = 1;
		this.needsState = new NeedState( );
		this.annotation = new Annotation( 1,
			0xFFFFFFFF );
		
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
		int result = ( ( this.leaves / SimulationConstant.LEAVES_BY_STAGE ) / 2 ) + 1;
		
		// Return
		return ( result >= SimulationConstant.MAXIMUM_PLANT_GROUP_DIAMETER ) ? SimulationConstant.MAXIMUM_PLANT_GROUP_DIAMETER : result;
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
	 * @return the annotation
	 */
	public Annotation getAnnotation( )
	{
		return this.annotation;
	}
	
	/**
	 * Be eaten
	 * 
	 * @param leafEaten
	 * 		The count of leaves eaten by herbivorous
	 */
	public void beEaten( int leavesEaten )
	{
		// Decrease leaf
		this.leaves = ( this.leaves < leavesEaten ? 0 : this.leaves - leavesEaten );
		
		// Annotate
		this.annotation.addMessage( "- "
			+ leavesEaten
			+ "("
			+ this.leaves
			+ ")" );
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
		double nitrogenToBeConsume = this.needsDefinition.getNitrogen( ) / ( ( (double)( SimulationConstant.MAXIMUM_STAGES * SimulationConstant.LEAVES_BY_STAGE ) - (double)this.leaves ) + 1.0d );

		// Consume
		try
		{
			// Consume
			resourceState.consumeNitrogen( nitrogenToBeConsume );

			// More leaves
				// Pick a number
					int addedLeaves = (int)simulation.math.probability.Operation.random( (double)SimulationConstant.MINIMUM_LEAF_GROWING_ONE_TURN,
						(double)SimulationConstant.MAXIMUM_LEAF_GROWING_ONE_TURN );
				// Add
					this.leaves += addedLeaves;
				// Add annotation
					this.annotation.addMessage( "+ "
						+ addedLeaves
						+ "("
						+ this.leaves
						+ ")" );
		}
		catch( NoMoreResourceException e )
		{
			// Reduce
			this.leaves--;
			
			// Add annotation
			this.annotation.addMessage( "- 1 ("
				+ this.leaves
				+ ")" );
		}
		
		// Calculate diameter
		this.diameter = this.calculateDiameter( );
		
		// Create group range
		this.createGroupRange( );
	}
	
	/**
	 * Update elements that aren't dependant of round
	 * gesture
	 */
	public void updateDesynchronized( )
	{
		// Update annotation
		this.annotation.update( );
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
		
		// Update annotation position
		this.annotation.setPosition( new Point<Integer>( (int)x + (int)( ellipseSize / 2.0d ),
			(int)y + (int)( ellipseSize / 2.0d ) ) );
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