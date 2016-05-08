package simulation.world.environment.biome.resource.field;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import simulation.ViewState;
import simulation.constant.SimulationConstant;
import simulation.gui.object.Hexagon;
import simulation.math.circle.Circle;
import simulation.math.point.Point;
import simulation.math.rectangle.Rectangle;
import simulation.world.aim.AimedObject;
import simulation.world.environment.biome.resource.ResourceBasis;

/**
 * The field resources are determined by water, protein, nitrogen,
 * 	carbon and more..
 * 
 * @author CAMPS Aurèle
 */

public class FieldResource extends ResourceBasis implements AimedObject
{
	/**
	 * The name of the resource
	 */
	private String name;
	
	/**
	 * The position
	 */
	private Point<Double> position;
	
	/**
	 * The size
	 */
	private int size;
	
	/**
	 * The shape to be painted
	 */
	private Shape shape;
	
	/**
	 * The range of the resource
	 */
	private Rectangle range;

	/**
	 * Construct the field resource
	 * 
	 * @param name
	 * 		The name of the source field resource
	 * @param protein
	 * 		The protein quantity in this resource
	 * @param nitrogen
	 * 		The nitrogen quantity in this resource
	 */
	public FieldResource( String name,
		int proteinQuantity,
		Point<Double> position )
	{
		// Parent constructor
		super( 0,
			proteinQuantity );

		// Save
		this.name = name;
		this.position = position;
		
		// Calcultate size
		this.size = this.calculateSize( );
		this.createRangeDiameter( );
		
		// Init
		this.shape = null;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}
	
	/**
	 * @return the shape
	 */
	public Shape getShape( )
	{
		return this.shape;
	}
	
	/**
	 * @return the range
	 */
	public Rectangle getRange( )
	{
		return this.range;
	}
	
	/**
	 * Calculate the size according to protein quantity
	 * 
	 * @return the size
	 */
	public int calculateSize( )
	{
		return (int)( SimulationConstant.FIELD_RESOURCE_SIZE );
	}
	
	/**
	 * Update view
	 * 
	 * @param viewState
	 * 		The current view state
	 * @param hexagon
	 * 		The hexagon representing the current map
	 */
	public void updateView( ViewState viewState,
		Hexagon hexagon )
	{
		// Calculate
		double size = ( (double)this.size * (double)viewState.getZoomLevel( ) );
		double x = ( ( (double)this.position.getX( ) * viewState.getZoomLevel( ) ) + hexagon.getPosition( ).getX( ) ) - ( size / 2.0d );
		double y = ( ( (double)this.position.getY( ) * viewState.getZoomLevel( ) ) + hexagon.getPosition( ).getY( ) ) - ( size / 2.0d );
		
		// Create shape
		this.shape = new Rectangle2D.Double( x,
			y,
			size,
			size );
	}
	
	/**
	 * Create the range diameter
	 */
	public void createRangeDiameter( )
	{
		// Create the range
		this.range = new Rectangle( new Point<Integer>( SimulationConstant.FIELD_RESOURCE_SIZE,
				SimulationConstant.FIELD_RESOURCE_SIZE ),
			new Point<Integer>( this.position.getX( ).intValue( ),
				this.position.getY( ).intValue( ) ) );
	}
	
	/**
	 * Update
	 */
	public void update( )
	{
		// Calculate size
		this.size = this.calculateSize( );
		
		// Create the range diameter
		this.createRangeDiameter( );
	}

	/**
	 * @return the position
	 */
	@Override
	public Point<Double> getPosition( )
	{
		return this.position;
	}
}
