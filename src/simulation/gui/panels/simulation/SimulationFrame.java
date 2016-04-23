package simulation.gui.panels.simulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Hashtable;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import simulation.ViewState;
import simulation.gui.Window;
import simulation.gui.panels.simulation.map.action.MouseDragMapAction;
import simulation.gui.panels.simulation.map.action.MouseMapSelectAction;
import simulation.gui.panels.simulation.map.action.MouseViewResetAction;
import simulation.gui.panels.simulation.map.action.MouseWheelAction;
import simulation.gui.panels.simulation.worldControl.WorldControlPanel;
import simulation.gui.panels.simulation.worldControl.action.SliderFrameDelayAction;
import simulation.gui.panels.simulation.map.MapPanel;
import simulation.world.World;

public class SimulationFrame
{
	/**
	 * The biome display height
	 */
	private static final int BIOME_DISPLAY_HEIGHT = 200;
	
	/**
	 * The right width of the panels
	 */
	private static final int RIGHT_DISPLAY_WIDTH = 200;
	
	/**
	 * The map panel
	 */
	private JPanel mapContainer;

	/**
	 * The world control panel
	 */
	private JPanel worldControlContainer;

	/**
	 * The instruments panel
	 */
	private JPanel instrumentsContainer;
	
	/**
	 * Mutex
	 */
	private java.util.concurrent.Semaphore semaphore;
	
	/**
	 * Alpha gesture of selected map
	 */
	private AlphaEvolution alphaEvolutionSelectedMap;
	
	/**
	 * Continue simulation?
	 */
	private boolean isContinue;
	
	/**
	 * Create the simulation frame
	 * 
	 * @param window
	 * 		The window
	 * @param world
	 * 		The world
	 */
	public SimulationFrame( Window window,
		World world )
	{
		// Create the alpha gesture
		this.alphaEvolutionSelectedMap = new AlphaEvolution( 50,
			255,
			1 );
				
		// Create the grid bag constraints
		GridBagConstraints gbcMap = new GridBagConstraints( );
		GridBagConstraints gbcWorldControl = new GridBagConstraints( );
		GridBagConstraints gbcInstruments = new GridBagConstraints( );

		// Create the semaphore
		this.semaphore = new Semaphore( 1 );
		
		// Transmit semaphore
		world.setSemaphore( this.semaphore );

		// Create panels
			// Map container
				// Panel
					this.mapContainer = new MapPanel( world,
						this.alphaEvolutionSelectedMap );
					this.mapContainer.setBackground( Color.BLACK );
				// Configure the grid bag constraints
					gbcMap.gridx = 0;
					gbcMap.gridy = 0;
					gbcMap.gridwidth = GridBagConstraints.RELATIVE;
					gbcMap.gridheight = GridBagConstraints.REMAINDER;
					gbcMap.anchor = GridBagConstraints.LINE_START;
					gbcMap.fill = GridBagConstraints.BOTH;
					gbcMap.insets = new Insets( 5,
						5,
						5,
						5 );
					gbcMap.weightx = 1.0;
					gbcMap.weighty = 1.0;
			// World control
				// Panel
					this.worldControlContainer = new WorldControlPanel( world );
					this.worldControlContainer.setBackground( Color.BLACK );
					
					this.worldControlContainer.setPreferredSize( new Dimension( SimulationFrame.RIGHT_DISPLAY_WIDTH,
						SimulationFrame.BIOME_DISPLAY_HEIGHT ) );
					this.worldControlContainer.setMinimumSize( new Dimension( SimulationFrame.RIGHT_DISPLAY_WIDTH,
						SimulationFrame.BIOME_DISPLAY_HEIGHT ) );
				// Configure the grid bag constraints
					gbcWorldControl.insets = new Insets( 5,
						0,
						5,
						5 );
			// Instruments container
				// Panel
					this.instrumentsContainer = new JPanel( );
					this.instrumentsContainer.setBackground( Color.BLUE );
					this.instrumentsContainer.setPreferredSize( new Dimension( SimulationFrame.RIGHT_DISPLAY_WIDTH,
						window.getHeight( ) - SimulationFrame.BIOME_DISPLAY_HEIGHT ) );
					this.instrumentsContainer.setMinimumSize( new Dimension( SimulationFrame.RIGHT_DISPLAY_WIDTH,
							window.getHeight( ) - SimulationFrame.BIOME_DISPLAY_HEIGHT ) );
				// Configure the grid bag constraints
					gbcInstruments.insets = new Insets( 0,
						0,
						5,
						5 );
					gbcInstruments.weighty = 1.0;
					gbcInstruments.gridheight = GridBagConstraints.RELATIVE;
					gbcInstruments.fill = GridBagConstraints.VERTICAL;

		// Set layout
		window.prepareForGameRendering( mapContainer,
			gbcMap,
			worldControlContainer,
			gbcWorldControl,
			instrumentsContainer,
			gbcInstruments );

		// Add actions listener
			// Map controller
				// Wheel for map zoom
					window.addMouseWheelListener( new MouseWheelAction( world.getState( ).getViewState( ),
						world,
						this.semaphore ) );
				// Mouse control for map
					// Create
						MouseDragMapAction mdma = new MouseDragMapAction( world.getState( ).getViewState( ),
							world,
							this.semaphore );
					// Add listeners
						this.mapContainer.addMouseListener( mdma );
						this.mapContainer.addMouseMotionListener( mdma );
				// Mouse middle click to reset camera
					this.mapContainer.addMouseListener( new MouseViewResetAction( world.getState( ).getViewState( ),
						world,
						this.semaphore ) );
				// Mouse left click to select map
					this.mapContainer.addMouseListener( new MouseMapSelectAction( world.getState( ).getViewState( ),
						world,
						this.semaphore ) );
				

		// Start simulation
		this.isContinue = true;
	}
	
	/**
	 * @return the mapContainer
	 */
	public JPanel getMapContainer( )
	{
		return mapContainer;
	}

	/**
	 * @return the biomeDisplayContainer
	 */
	public JPanel getWorldControlContainer( )
	{
		return worldControlContainer;
	}

	/**
	 * @return the instrumentsContainer
	 */
	public JPanel getInstrumentsContainer( )
	{
		return instrumentsContainer;
	}
	
	/**
	 * Activate the painting
	 */
	public void activatePainting( )
	{
		((MapPanel)this.mapContainer).setPaintReady( true );
	}
	
	/**
	 * Simulate a world
	 */
	public void simulate( World world )
	{
		
		do
		{
			// Update the world
			world.update( );
			
			// Update alpha for selected map
			this.alphaEvolutionSelectedMap.update( );
			
			// Paint
			this.worldControlContainer.repaint( );
			this.instrumentsContainer.repaint( );
			this.mapContainer.repaint( );

			// Delay
			try
			{
				Thread.sleep( 16 );
			}
			catch( InterruptedException e )
			{
			}
		} while( this.isContinue );
	}
}
