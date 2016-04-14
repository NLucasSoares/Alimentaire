package simulation.world.environment.hexagonalMap;

import simulation.gui.object.Hexagon;
import simulation.world.environment.Map;

/**
 * One map configure for event gesture,
 * and paint on the map
 */
public class UnitHexagonalMap extends Hexagon
{
	/**
	 * The radius unit for a hexagon
	 */
	public static final double HEXAGON_UNIT_PAINT_RADIUS = 15.0;
	
	/**
	 * Construct the unit hexagonal map
	 * 
	 * @param radius
	 * 		The hexagon size
	 * @param map
	 * 		The map
	 */
	public UnitHexagonalMap( double radius,
		Map map )
	{
		// Parent constructor
		super( radius );

		// Save
		this.map = map;
	}

	/**
	 * Map
	 */
	private Map map;

	/**
	 * @return the map
	 */
	public Map getMap( )
	{
		return map;
	}
	
}
