package simulation.world.environment.hexagonalMap;

import simulation.math.hexagon.HexagonEdge;
import simulation.math.point.Point;
import simulation.world.environment.Map;

/**
 * This class allow user to go through
 * a hexagonal map, by the simple call
 * of the nextMap function. This way,
 * it become very easy to iterate maps
 * in an hexagonal disposition.
 * 
 * @author Lucas SOARES
 */
public class HexagonalMapVisitor
{
	/**
	 * Current counter
	 */
	private int counter;
	
	/**
	 * Current ring
	 */
	private int currentRing;
	
	/**
	 * Axial coordinates of the current map
	 */
	private Point<Integer> axialCoordinates;
	
	/**
	 * Previous coordinates to be calculate
	 */
	private Point<Integer> previousAxialCoordinates;
	
	/**
	 * Last coordinates when updating map
	 * (used for the end-determination of
	 * the next-map-going process)
	 */
	private Point<Integer> lastAxialCoordinates;
	
	/**
	 * Current step
	 */
	private HexagonalMapVisitStep step;
	
	/**
	 * Hexagonal map
	 */
	private HexagonalMap hexagonalMap;
	
	/**
	 * Construct the hexagonal map
	 * visitor
	 * 
	 * @param hexagonalMap
	 * 		The hexagonal map to visit
	 */
	public HexagonalMapVisitor( HexagonalMap hexagonalMap )
	{
		// Save
		this.hexagonalMap = hexagonalMap;
		
		// Reset the visitor
		this.reset( );
	}
	
	/**
	 * Reset the visitor
	 */
	public void reset( )
	{
		this.counter = 0;
		this.axialCoordinates = new Point<Integer>( 0,
			0 );
		this.previousAxialCoordinates = new Point<Integer>( 0,
			0 );
		this.currentRing = 0;
		this.step = HexagonalMapVisitStep.HEXAGONAL_MAP_VISIT_STEP_RIGHT_START;
	}
	
	/**
	 * @return the current map
	 */
	public Map getCurrentMap( )
	{
		return this.hexagonalMap.getMapFromAxialCoordinates( this.axialCoordinates );
	}
	
	/**
	 * @return the previous map
	 */
	public Map getPreviousMap( )
	{
		return this.hexagonalMap.getMapFromAxialCoordinates( this.previousAxialCoordinates );
	}
	
	/**
	 * Set the current visited map
	 * to a new map given
	 * 
	 * @param map
	 * 		The map we'd like to
	 * put at this place
	 */
	public void setCurrentMap( Map map )
	{
		this.hexagonalMap.setMapFromAxialCoordinates( this.axialCoordinates,
			map );
	}
	
	/**
	 * Go to the next map
	 * As the update can take multiple times to be
	 * correctly done, we use this function from the
	 * outside to be sure update is done
	 */
	public void nextMap( )
	{
		do
		{
			this.updateMap( );
		} while( this.axialCoordinates.getX( ) == this.lastAxialCoordinates.getX( )
			&& this.axialCoordinates.getY( ) == this.lastAxialCoordinates.getY( ) );
	}
	
	/**
	 * Update the current map by increase step/counter
	 * Be aware this function won't necessarily go to
	 * the next map, it'll only increase the current
	 * step.
	 * Use nextMap( ) for visitor to go to the next
	 * map.
	 */
	public void updateMap( )
	{
		// Check for next map
		if( !this.isNextMap( ) )
			return;
		
		// Save last coordinates
		this.lastAxialCoordinates = new Point<Integer>( this.axialCoordinates.getX( ),
			this.axialCoordinates.getY( ) );
		
		// Go to next map
		switch( this.step )
		{
			default:
			case HEXAGONAL_MAP_VISIT_STEP_RIGHT_START:
				// Update coordinates
				this.updateCoordinates( );
				
				// Next step
				this.increaseStep( );
				
				// Next ring
				this.currentRing++;
				break;
				
			case HEXAGONAL_MAP_VISIT_STEP_BOTTOM_RIGHT:
				if( this.counter >= this.currentRing - 1 )
					this.increaseStep( );
				else
				{
					this.updateCoordinates( );
					this.counter++;
				}
				break;
				
			case HEXAGONAL_MAP_VISIT_STEP_BOTTOM_LEFT:
			case HEXAGONAL_MAP_VISIT_STEP_LEFT:
			case HEXAGONAL_MAP_VISIT_STEP_TOP_LEFT:
			case HEXAGONAL_MAP_VISIT_STEP_TOP_RIGHT:
			case HEXAGONAL_MAP_VISIT_STEP_RIGHT_END:
				if( this.counter >= this.currentRing )
					this.increaseStep( );
				else
				{
					this.updateCoordinates( );
					this.counter++;
				}
				break;
		}
	}
	
	/**
	 * @return if there's another map after
	 * the current one
	 */
	public boolean isNextMap( )
	{
		switch( this.step )
		{
			case HEXAGONAL_MAP_VISIT_STEP_RIGHT_END:
				if( this.currentRing >= this.hexagonalMap.getSize( )
					&& this.counter >= this.currentRing )
					return false;

			default:
				return true;
		}
	}
	
	/**
	 * @return the current axial coordinates
	 */
	public Point<Integer> getCurrentAxialCoordinates( )
	{
		return this.axialCoordinates;
	}
	
	/**
	 * @return the previous axial coordinates
	 */
	public Point<Integer> getPreviousAxialCoordinates( )
	{
		return this.previousAxialCoordinates;
	}
	
	/**
	 * Increase the current step
	 * to the next one according
	 * to the object state.
	 */
	private void increaseStep( )
	{
		// Check current step
		if( this.step.ordinal( ) + 1 >= HexagonalMapVisitStep.values( ).length )
			// Go back to start step
			this.step = HexagonalMapVisitStep.values( )[ 0 ];
		// Increase step
		else
			this.step = HexagonalMapVisitStep.values( )[ this.step.ordinal( ) + 1 ];
		
		// Counter goes back to 0
		this.counter = 0;
	}
	
	/**
	 * Get the current step of the
	 * visit
	 * 
	 * @return the step
	 */
	public HexagonalMapVisitStep getHexagonalMapVisitStep( )
	{
		return this.step;
	}
	
	/**
	 * Update the axial coordinates
	 * of the current map
	 */
	private void updateCoordinates( )
	{
		// Save previous coordinates
		this.previousAxialCoordinates = new Point<Integer>( this.axialCoordinates.getX( ),
			this.axialCoordinates.getY( ) );
		
		// Update coordinates
		this.axialCoordinates.setX( this.axialCoordinates.getX( )
				+ HexagonEdge.EDGE_DIRECTION_COORDINATE[ HexagonalMapVisitStep.convertHexagonalMapVisitStepToHexagonalEdge( this.step ).ordinal( ) ][ 0 ] );
		this.axialCoordinates.setY( this.axialCoordinates.getY( )
				+ HexagonEdge.EDGE_DIRECTION_COORDINATE[ HexagonalMapVisitStep.convertHexagonalMapVisitStepToHexagonalEdge( this.step ).ordinal( ) ][ 1 ] );
	
		// log
		//System.out.println( "Ring = " + this.currentRing+ ", Counter = "+ this.counter+ ", axial("+ axialCoordinates.getX( )+ ", "+ axialCoordinates.getY( )+ ") -> " + this.step.name( ) );
	}
}
