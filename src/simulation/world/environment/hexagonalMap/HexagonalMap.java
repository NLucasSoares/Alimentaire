package simulation.world.environment.hexagonalMap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.security.InvalidParameterException;
import java.util.Iterator;

import javax.swing.JPanel;

import simulation.Database;
import simulation.ViewState;
import simulation.constant.SimulationConstant;
import simulation.gui.panels.simulation.AlphaEvolution;
import simulation.math.hexagon.HexagonEdge;
import simulation.math.point.Point;
import simulation.math.point.PointDouble;
import simulation.math.rectangle.Rectangle;
import simulation.world.Configuration;
import simulation.world.environment.Map;
import simulation.world.environment.biome.Biome;
import simulation.world.environment.nameGenerator.MapNameGenerator;
import simulation.world.environment.plant.PlantGroup;

/**
 * A hexagonal group of map, using axial
 * coordinates system.
 * 
 * The system is based on the explications
 * of http://www.redblobgames.com/grids/hexagons/#map-storage
 * 
 * @author Lucas SOARES
 */
public class HexagonalMap
{
	/**
	 * The existing maps in the world
	 */
	private Map[ ][ ] maps;
	
	/**
	 * The hexagons representing the
	 * maps
	 */
	private UnitHexagonalMap[ ] hexagons;
	
	/**
	 * The hexagons count
	 */
	private int hexagonsCount = 0;
	
	/**
	 * The center map, the root of
	 * the procedural generation
	 */
	private Map centerMap;
	
	/**
	 * Size
	 */
	private int size;

	/**
	 * Construct the hexagonal map
	 * 
	 * Axial coordinates:
	 * 		x = X + size
	 * 		y = Y + size
	 */
	public HexagonalMap( Configuration configuration,
		Database database,
		int width,
		int height )
	{
		// Save the size
		this.size = configuration.getSize( );
		
		// Allocate memory
			// 1D
				this.maps = new Map[ ( this.size * 2 ) + 1 ][ ];
			// 2D
				for( int i = 0; i < ( this.size * 2 ) + 1; i++ )
					this.maps[ i ] = new Map[ ( this.size * 2 ) + 1 ];
				
		// Generate the maps
			// Create the map at the center
				this.centerMap = new Map( MapNameGenerator.generate( ),
					new Point<Integer>( 0, 0 ),
					configuration.getCentralBiome( ) );
			// Copy the center map
				Point<Integer> centerCoordinates = this.convertToCartesianCoordinates( new Point<Integer>( 0,
					0 ) );
				this.maps[ centerCoordinates.getX( ) ][ centerCoordinates.getY( ) ] = this.centerMap;
				
		// Create the visitor
		HexagonalMapVisitor hmv = new HexagonalMapVisitor( this );

		// Construct the map
		while( hmv.isNextMap( ) )
		{
			// Go to the next map
			hmv.nextMap( );

			// Pick-up a biome
			Biome biome = hmv.getPreviousMap( ).getBiome( ).getNearAllowedBiomes( ).pickRandomBiome( database );

			// Set the map
			hmv.setCurrentMap( new Map( MapNameGenerator.generate( ),
				hmv.getCurrentAxialCoordinates( ),
				biome ) );
		}
		
		// Create the hexagons
		this.createHexagons( width,
			height,
			new ViewState( this.centerMap ) );
	}
	
	/**
	 * @return the size
	 */
	public int getSize( )
	{
		return this.size;
	}
	
	/**
	 * Convert to axial coordinates, according to the
	 * actual system
	 * 
	 * @param cartesianCoordinates
	 * 		Coordinates to convert
	 * 
	 * @return the converted coordinates
	 */
	public simulation.math.point.Point<Integer> convertToAxialCoordinates( Point<Integer> cartesianCoordinates )
	{
		return new simulation.math.point.Point<Integer>( cartesianCoordinates.getX( ) - this.size,
			cartesianCoordinates.getY( ) - this.size );
	}
	
	/**
	 * Convert to cartesian coordinates, according to the
	 * actual system
	 * 
	 * @param axialCoordinates
	 * 		Coordinates to convert
	 * 
	 * @return the converted coordinates
	 */
	public simulation.math.point.Point<Integer> convertToCartesianCoordinates( Point<Integer> axialCoordinates )
	{
		return new simulation.math.point.Point<Integer>( axialCoordinates.getX( ) + this.size,
			axialCoordinates.getY( ) + this.size );
	}
	
	/**
	 * Check if coordinates is in the array
	 * of maps
	 * 
	 * @param cartesianCoordinates
	 * 		The coordinates to check
	 */
	public boolean isCartesianCoordinatesCorrect( Point<Integer> cartesianCoordinates )
	{
		// Out of bound
		return ( cartesianCoordinates.getX( ) < this.maps.length
			&& cartesianCoordinates.getX( ) >= 0
			&& cartesianCoordinates.getY( ) < this.maps[ cartesianCoordinates.getX( ) ].length
			&& cartesianCoordinates.getY( ) >= 0 );
	}
	
	/**
	 * Set the map in axial coordinates
	 * 
	 * @param axialCoordinates
	 * 		The coordinates of the map
	 * @param map
	 * 		The map to set
	 * 
	 * @throws java.security.InvalidParameterException
	 */
	public void setMapFromAxialCoordinates( Point<Integer> axialCoordinates,
		Map map ) throws InvalidParameterException
	{
		// Convert
		Point<Integer> cartesianCoordinates = this.convertToCartesianCoordinates( axialCoordinates );
		
		// Check coordinates
		if( !this.isCartesianCoordinatesCorrect( cartesianCoordinates ) )
			throw new InvalidParameterException( );
		
		// Set the map
		this.maps[ cartesianCoordinates.getX( ) ][ cartesianCoordinates.getY( ) ] = map;
	}
	
	/**
	 * Get the center map
	 * 
	 * @return the center map
	 */
	public Map getCenterMap( )
	{
		return this.centerMap;
	}
	
	/**
	 * Get the map in axial coordinates
	 * 
	 * @param axialCoordinates
	 * 		The axial system coordinates
	 * 
	 * @return the asked map
	 */
	public Map getMapFromAxialCoordinates( Point<Integer> axialCoordinates ) throws InvalidParameterException
	{
		// Get the axial coordinates equivalent
		Point<Integer> coordinates = this.convertToCartesianCoordinates( axialCoordinates );
		
		// OK
		return this.getMapFromCartesianCoordinates( coordinates.getX( ),
			coordinates.getY( ) );
	}
	
	/**
	 * Get the map in axial coordinates
	 * 
	 * @param x
	 * 		The x axial system coordinate
	 * @param y
	 * 		The y axial system coordinate
	 * 
	 * @return the asked map
	 */
	public Map getMapFromAxialCoordinates( int x,
		int y ) throws InvalidParameterException
	{
		return this.getMapFromAxialCoordinates( new Point<Integer>( x,
			y ) );
	}
	
	/**
	 * Get the map from cartesian coordinates
	 * 
	 * @param x
	 * 		The x index
	 * @param y
	 * 		The y index
	 * 
	 * @return the asked map
	 */
	public Map getMapFromCartesianCoordinates( int x,
		int y ) throws InvalidParameterException
	{
		return this.getMapFromCartesianCoordinates( new Point<Integer>( x,
			y ) );
	}
	
	/**
	 * Get the map from cartesina coordinates
	 * 
	 * @param coordinates
	 * 		The requested map coordinates
	 * 
	 * @return the requested map
	 */
	public Map getMapFromCartesianCoordinates( Point<Integer> coordinates ) throws InvalidParameterException
	{
		// Check coordinates validity
		if( !this.isCartesianCoordinatesCorrect( coordinates ) )
			throw new InvalidParameterException( );
		
		// OK
		return this.maps[ coordinates.getX( ) ][ coordinates.getY( ) ];
	}
	
	/**
	 * Get the neighbourhood map with axial coordinates
	 * 
	 * @param currentMap
	 * 		The current map we want the neighbourhood
	 * @param edge
	 * 		Hexagon edge
	 * 
	 * @return the asked map
	 */
	public Map getNeighbourhoodMapAxialCoordinates( Point<Integer> currentMap,
		HexagonEdge edge ) throws InvalidParameterException
	{
		// Convert coordinates to axial
		Point<Integer> cartesianCoordinates = this.convertToCartesianCoordinates( currentMap );
		
		// Execute
		return this.getNeighbourhoodMapAxialCoordinates( cartesianCoordinates,
			edge );
	}
	
	/**
	 * Get the neighbourhood map with cartesian coordinates
	 * 
	 * @param currentMap
	 * 		The current map we want the neighbourhood
	 * @param edge
	 * 		Hexagon edge
	 * 
	 * @return the asked map
	 */
	public Map getNeighbourhoodMapCartesianCoordinates( Point<Integer> currentMap,
		HexagonEdge edge ) throws InvalidParameterException
	{
		// Add edge direction value
		currentMap.setX( currentMap.getX( ) + HexagonEdge.EDGE_DIRECTION_COORDINATE[ edge.ordinal( ) ][ 0 ] );
		currentMap.setY( currentMap.getY( ) + HexagonEdge.EDGE_DIRECTION_COORDINATE[ edge.ordinal( ) ][ 1 ] );
		
		// Check validity
		if( currentMap.getX( ) >= this.maps.length
			|| currentMap.getY( ) >= this.maps[ currentMap.getX( ) ].length )
			throw new InvalidParameterException( );
		
		// OK
		return this.maps[ currentMap.getX( ) ][ currentMap.getY( ) ];
	}
	
	/**
	 * @return unit hexagonal maps
	 */
	public UnitHexagonalMap[ ] getUnitHexagonalMaps( )
	{
		return this.hexagons;
	}	
	
	/**
	 * Calculate hexagons count
	 * 
	 * @return the hexagonal count
	 */
	private int calculateHexagonsCount( )
	{
		// Output
		int output = 1;
		
		// Add ring
		for( int i = 0; i < this.size; i++ )
			output += HexagonEdge.values( ).length * ( i + 1 );
		
		// OK
		return output;
	}
	
	/**
	 * Create the hexagons
	 * 
	 * @param width
	 * 		Screen width
	 * @param height
	 * 		Screen height
	 * @param viewState
	 * 		The current view state
	 */
	public void createHexagons( int width,
		int height,
		ViewState viewState )
	{
		// Calculate hexagons count
		this.hexagonsCount = this.calculateHexagonsCount( );
		
		// Allocate
		this.hexagons = new UnitHexagonalMap[ this.hexagonsCount ];
		
		// Create the visitor
		HexagonalMapVisitor hmv = new HexagonalMapVisitor( this );
		
		// First hexagon
			// Create
				this.hexagons[ 0 ] = new UnitHexagonalMap( (double)viewState.getZoomLevel( ) * Map.SIZE_PIXEL_BY_SIZE_UNIT,
					hmv.getCurrentMap( ) );
			// Set position
				this.hexagons[ 0 ].setPosition( -( (int)this.hexagons[ 0 ].getVerticalDiameter( ) ) / 2 + width / 2 + viewState.getScrollPosition( ).getX( ),
						-( (int)this.hexagons[ 0 ].getVerticalDiameter( ) ) / 2 + height / 2 + viewState.getScrollPosition( ).getY( ) );
			// Set color
				this.hexagons[ 0 ].setBorderColor( new Color( 0x00FFFFFF,
					true ) );
				
		// The last map coordinates
		Point<Integer> lastCoordinates;
		
		// Draw maps
		int currentMap = 1;
		while( hmv.isNextMap( ) )
		{
			// Get the last map coordinates
			lastCoordinates = new Point<Integer>( hmv.getCurrentAxialCoordinates( ).getX( ),
				hmv.getCurrentAxialCoordinates( ).getY( ) );

			// Next map
			hmv.nextMap( );

			// Translate to next hexagon
			try
			{
				// Get the transfer vector
				PointDouble transfer = this.hexagons[ currentMap - 1 ].getNearHexagonPositionDifference( HexagonEdge.calculateLastEdge( hmv.getCurrentAxialCoordinates( ),
					lastCoordinates ) );
				
				// Create
				this.hexagons[ currentMap ] = new UnitHexagonalMap( (double)viewState.getZoomLevel( ) * Map.SIZE_PIXEL_BY_SIZE_UNIT,
					hmv.getCurrentMap( ) );
				
				// Set color
				this.hexagons[ currentMap ].setBorderColor( new Color( 0x00FFFFFF,
					true ) );
				
				// Set position
				this.hexagons[ currentMap ].setPosition( this.hexagons[ currentMap - 1 ].getPosition( ).getX( ) + transfer.getX( ),
					this.hexagons[ currentMap - 1 ].getPosition( ).getY( ) + transfer.getY( ) );
			}
			catch( InvalidParameterException ipe )
			{
				
			}
			
			// Increase map
			currentMap++;
		}
	}
	
	/**
	 * Blit the hexagonal map according to
	 * the ViewState
	 * 
	 * @param g
	 * 		Graphics
	 * @param viewState
	 * 		The actual view state
	 * @param width
	 *		The screen width
	 * @param height
	 * 		The screen height
	 * @param mapContainter
	 * 		The panel used to show the map
	 */
	public void drawMap( Graphics g,
		ViewState viewState,
		int width,
		int height,
		JPanel mapContainer,
		AlphaEvolution selectedMapAlpha )
	{
		// Anti Aliasing
		((Graphics2D)g).setRenderingHint( RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON );
				
		// View point
		Rectangle viewPoint = new Rectangle( width + (int)this.hexagons[ 0 ].getWidthConstant( ),
			height + (int)this.hexagons[ 0 ].getHeightConstant( ),
			0,
			0 );

		// Draw map biomes
		for( int i = 0; i < this.hexagonsCount; i++ )
		{
			// Check for position
			if( this.hexagons[ i ].getPosition( ).getX( ) >= viewPoint.getPosition( ).getX( ) - this.hexagons[ i ].getWidthConstant( )
				&& this.hexagons[ i ].getPosition( ).getY( ) >= viewPoint.getPosition( ).getY( ) - this.hexagons[ i ].getHeightConstant( )
				&& this.hexagons[ i ].getPosition( ).getX( ) + this.hexagons[ i ].getWidthConstant( ) <= viewPoint.getPosition( ).getX( )  + viewPoint.getSize( ).getX( ) 
				&& this.hexagons[ i ].getPosition( ).getY( ) + this.hexagons[ i ].getHeightConstant( ) <= viewPoint.getPosition( ).getY( ) + viewPoint.getSize( ).getY( ) )
			{
				// Paint borders
				this.hexagons[ i ].paint( g );

				// If current map selected, change alpha
				if( this.hexagons[ i ].getMap( ) == viewState.getSelectedMap( ) )
					( (Graphics2D)g ).setComposite( java.awt.AlphaComposite.getInstance( java.awt.AlphaComposite.SRC_OVER,
							(float)selectedMapAlpha.getCurrentAlpha( ) ) );
				
				// Paint biome tileset
				g.drawImage( this.hexagons[ i ].getMap( ).getBiome( ).getTileset( ),
					this.hexagons[ i ].getPosition( ).getX( ).intValue( ),
					this.hexagons[ i ].getPosition( ).getY( ).intValue( ),
					(int)this.hexagons[ i ].getWidthConstant( ),
					(int)this.hexagons[ i ].getHeightConstant( ),
					mapContainer );
				
				// Restore alpha
				( (Graphics2D)g ).setComposite( java.awt.AlphaComposite.getInstance( java.awt.AlphaComposite.SRC_OVER,
						1.0f ) );
			}
		}
		
		// Draw content
		for( int i = 0; i < this.hexagonsCount; i++ )
		{
			// Check for position
			if( this.hexagons[ i ].getPosition( ).getX( ) >= viewPoint.getPosition( ).getX( ) - this.hexagons[ i ].getWidthConstant( )
				&& this.hexagons[ i ].getPosition( ).getY( ) >= viewPoint.getPosition( ).getY( ) - this.hexagons[ i ].getHeightConstant( )
				&& this.hexagons[ i ].getPosition( ).getX( ) + this.hexagons[ i ].getWidthConstant( ) <= viewPoint.getPosition( ).getX( )  + viewPoint.getSize( ).getX( ) 
				&& this.hexagons[ i ].getPosition( ).getY( ) + this.hexagons[ i ].getHeightConstant( ) <= viewPoint.getPosition( ).getY( ) + viewPoint.getSize( ).getY( ) )
			{				
				// Plant group
				for( Iterator<PlantGroup> it = this.hexagons[ i ].getMap( ).getState( ).getPlantGroup( ); it.hasNext( ); )
				{
					// Get plant group
					PlantGroup pg = it.next( );
					
					// Construct circle
					if( pg.getDiameter( ) > 0 )
					{
						// Calculate
							double ellipseSize = ( ( (double)pg.getDiameter( ) / 2.0d ) * (double)viewState.getZoomLevel( ) );
							double x = ( ( (double)pg.getPosition( ).getX( ) * viewState.getZoomLevel( ) ) + this.hexagons[ i ].getPosition( ).getX( ) ) - ( ellipseSize / 2.0d );
							double y = ( ( (double)pg.getPosition( ).getY( ) * viewState.getZoomLevel( ) ) + this.hexagons[ i ].getPosition( ).getY( ) ) - ( ellipseSize / 2.0d );
						// Create
							Shape ellipse = new Ellipse2D.Double( x,
								y,
								ellipseSize,
								ellipseSize );
						// Fill
							g.setColor( new Color( 0x00,
								0xFF,
								0x00,
								0x2F ) );
							((Graphics2D)g).fill( ellipse );							
						// Draw border
							g.setColor( Color.WHITE );
							((Graphics2D)g).draw( ellipse );
					}
				}
			}
		}
	}
	
	/**
	 * Give life, spawn random animal groups on each map
	 */
	public void giveLife( Database database )
	{
		for( UnitHexagonalMap uhm : this.hexagons )
		{
			// Determine how many group to spawn
			int groupCount = (int)( SimulationConstant.MINIMUM_GROUP_COUNT_MAP_START + ( Math.random( ) * (double)( SimulationConstant.MAXIMUM_GROUP_COUNT_MAP_START - SimulationConstant.MINIMUM_GROUP_COUNT_MAP_START ) ) );
			
			for( int i = 0; i < groupCount; i++ )
			{
				// Create properties for this random group
				//database
			//uhm.getMap( ).getState( ).addGroup(  );
			}
		}
	}
	
	/**
	 * Update the maps
	 */
	public void update( )
	{
		// Update map state
		for( UnitHexagonalMap uhm : this.hexagons )
			uhm.getMap( ).getState( ).update( );

		// Update weathers
		
	}
}

