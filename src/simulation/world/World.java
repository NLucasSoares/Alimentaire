package simulation.world;

import java.awt.Graphics;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;

import simulation.Database;
import simulation.gui.Window;
import simulation.gui.panels.simulation.AlphaEvolution;
import simulation.gui.panels.simulation.SimulationFrame;
import simulation.world.environment.Map;
import simulation.world.environment.hexagonalMap.HexagonalMap;
import simulation.world.environment.hexagonalMap.HexagonalMapVisitor;
import simulation.world.environment.hexagonalMap.UnitHexagonalMap;

/**
 * The world
 * 
 * @author Lucas SOARES
 */
public class World
{
	/**
	 * Name
	 */
	private String name;

	/**
	 * The hexagonal map
	 */
	private HexagonalMap hexagonalMap;
	
	/**
	 * The world state
	 */
	private WorldState state;
	
	/**
	 * The configuration
	 */
	private Configuration configuration;
	
	/**
	 * Database reference
	 */
	private Database database;
	
	/**
	 * Simulation frame
	 */
	private SimulationFrame simulationFrame;
	
	/**
	 * Semaphore
	 */
	private Semaphore semaphore;
	
	/**
	 * Construct a world from configuration
	 * 
	 * @param name
	 * 		The name of the world
	 * @param configuration
	 * 		The configuration of generation
	 */
	public World( String name,
		Configuration configuration,
		Database database,
		Window window )
	{
		// Save the configuration
		this.configuration = configuration;
		this.name = name;
		this.database = database;
		
		// Generate the world
		this.generateWorld( window.getWidth( ),
			window.getHeight( ) );
		
		// Init
		this.diffuseWorldState( this.state = new WorldState( this ) );
				
		// Create the simulation frame
		this.simulationFrame = new SimulationFrame( window,
			this );
		
		// Update view
		this.updateWorldMap( );
		
		// Activate painting
		this.simulationFrame.activatePainting( );
	}
	
	/**
	 * Set semaphore
	 * 
	 * @param semaphore
	 * 		The created semaphore
	 */
	public void setSemaphore( Semaphore semaphore )
	{
		this.semaphore = semaphore;
	}
	
	/**
	 * Generate the world
	 * 
	 * @param width
	 * 		The screen width
	 * @param height
	 * 		The screen height
	 */
	private void generateWorld( int width,
		int height )
	{
		// Construct the hexagonal map
		this.hexagonalMap = new HexagonalMap( this.configuration,
			this.database,
			width,
			height,
			this );
	}
	
	/**
	 * Diffuse world state in map details
	 * 
	 * @param worldState
	 * 		The world state to diffuse
	 */
	private void diffuseWorldState( WorldState worldState )
	{
		this.hexagonalMap.diffuseWorldState( worldState );
	}
	
	/**
	 * Update the world
	 */
	public void update( )
	{
		// Update
		try
		{
			// Lock the semaphore
			this.semaphore.acquire( );
			
			// Check for update ready
			if( this.state.isUpdateReady( ) )
			{
				// Update the maps
				this.hexagonalMap.update( );
				
				// Update view of the alive entities
				this.hexagonalMap.updateEntitiesView( this.getState( ).getViewState( ) );
				
				// Update world state
				this.state.update( );

				// Update done
				this.state.updateDone( );
			}
			
			// Update round-independant components
			this.hexagonalMap.updateDesynchronized( );
		}
		catch( InterruptedException e )
		{
			
		}
		finally
		{		
			// Unlock the semaphore
			this.semaphore.release( );
		}
	}
	
	/**
	 * Construct the world map
	 */
	public void updateWorldMap( )
	{
		// Recreate hexagons
		this.hexagonalMap.createHexagons( this.simulationFrame.getMapContainer( ).getWidth( ),
			this.simulationFrame.getMapContainer( ).getHeight( ),
			this.state.getViewState( ) );
		
		// Update view state
		this.hexagonalMap.updateEntitiesView( this.state.getViewState( ) );
	}
	
	/**
	 * Draw the map
	 * 
	 * @param g
	 * 		The graphics place to draw
	 * @param panel
	 * 		The panel where to draw
	 * @param selectedMapAlpha
	 * 		The current state of selected
	 * map alpha
	 */
	public void drawWorld( Graphics g,
		JPanel panel,
		AlphaEvolution selectedMapAlpha )
	{
		try
		{
			// Lock the semaphore
			this.semaphore.acquire( );
			
			// Draw
			this.hexagonalMap.drawMap( g,
				this.state.getViewState( ),
				this.simulationFrame.getMapContainer( ).getWidth( ),
				this.simulationFrame.getMapContainer( ).getHeight( ),
				panel,
				selectedMapAlpha );
		}
		catch( InterruptedException e )
		{
			
		}
		finally
		{
			// Unlock the semaphore
			this.semaphore.release( );
		}
	}

	
	/**
	 * @return the simulationFrame
	 */
	public SimulationFrame getSimulationFrame( )
	{
		return simulationFrame;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}

	/**
	 * @return the state
	 */
	public WorldState getState( )
	{
		return state;
	}

	/**
	 * @return the center map
	 */
	public Map getCenterMap( )
	{
		return this.hexagonalMap.getCenterMap( );
	}
	
	/**
	 * @return the configuration
	 */
	public Configuration getConfiguration( )
	{
		return configuration;
	}

	/**
	 * @return the database
	 */
	public Database getDatabase( )
	{
		return database;
	}
	
	/**
	 * @return the total animal count
	 */
	public long getAnimalCount( )
	{
		return this.hexagonalMap.getAnimalCount( );
	}
	
	/**
	 * @return the group count
	 */
	public long getGroupCount( )
	{
		return this.hexagonalMap.getGroupCount( );
	}
	
	/**
	 * @return the plant group count
	 */
	public long getPlantGroupCount( )
	{
		return this.hexagonalMap.getPlantGroupCount( );
	}
	
	/**
	 * @return the herbivorous group count
	 */
	public long getHerbivorousGroupCount( )
	{
		return this.hexagonalMap.getHerbivorousGroupCount( );
	}
	
	/**
	 * @return this carnivorous group count
	 */
	public long getCarnivorousGroupCount( )
	{
		return this.hexagonalMap.getCarnivorousGroupCount( );
	}
	
	/**
	 * @return a hexagonal map visitor
	 * for this world
	 */
	public HexagonalMapVisitor getHexagonalMapVisitor( )
	{
		return new HexagonalMapVisitor( this.hexagonalMap );
	}
	
	/**
	 * @return the array of unit hexagonal map
	 */
	public UnitHexagonalMap[ ] getUnitHexagonalMaps( )
	{
		return this.hexagonalMap.getUnitHexagonalMaps( );
	}
}
