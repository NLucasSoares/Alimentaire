package simulation.world.environment;

import simulation.math.hexagon.HexagonEdge;
import simulation.math.point.Point;
import simulation.world.environment.biome.Biome;

/**
 * A hexagonal piece of the world
 * 
 * @author SOARES Lucas
 */
public class Map
{
	/**
	 * Size in pixel by size unit
	 * (real size for one map case,
	 * instead of HEXAGON_UNIT_PAINT_RADIUS
	 * which is only for painting)
	 */
	public static final int SIZE_PIXEL_BY_SIZE_UNIT = 100;
	
	/**
	 * Map's name
	 */
	private String name;
	
	/**
	 * The 6 edges maps
	 */
	private Map[ ] edgeMap = new Map[ HexagonEdge.values().length ];
	
	/**
	 * The map's biome
	 */
	private Biome biome;
	
	/**
	 * State
	 */
	private MapState state;
	
	/**
	 * Coordinates in the world
	 * 
	 * Using here the axial coordinates
	 * system.
	 */
	private simulation.math.point.Point<Integer> axialCoordinates;
	
	/**
	 * Construct the map
	 */
	public Map( String name,
		simulation.math.point.Point<Integer> axialCoordinates,
		Biome biome )
	{
		// Init
		for( HexagonEdge he : HexagonEdge.values( ) )
			this.edgeMap[ he.ordinal( ) ] = null;
		
		// Create map state
		this.state = new MapState( this );

		// Save
		this.name = name;
		this.biome = biome;
		this.axialCoordinates = axialCoordinates;
	}
	
	/**
	 * Set an edge map
	 * 
	 * @param he
	 * 		The hexagonal edge map to set
	 * @param map
	 * 		The map to set
	 */
	public void setEdgeMap( HexagonEdge he,
		Map m )
	{
		this.edgeMap[ he.ordinal( ) ] = m;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}

	/**
	 * @return the edgeMap
	 */
	public Map[ ] getEdgeMap( )
	{
		return edgeMap;
	}

	/**
	 * @return the biome
	 */
	public Biome getBiome( )
	{
		return biome;
	}

	/**
	 * @return the state
	 */
	public MapState getState( )
	{
		return state;
	}

	/**
	 * @return the axialCoordinates
	 */
	public Point<Integer> getAxialCoordinates( )
	{
		return axialCoordinates;
	}
	
	
}
