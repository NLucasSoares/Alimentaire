package simulation.gui.panels.simulation.worldControl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import simulation.ViewState;
import simulation.gui.panels.simulation.worldControl.action.SliderFrameDelayAction;
import simulation.world.World;

public class WorldControlPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1612989753663371255L;

	/**
	 * World reference
	 */
	private World world;
	
	/**
	 * Round count
	 */
	private JLabel roundCountLabel;
	
	/**
	 * World state label
	 */
	private JLabel groupCountLabel,
		plantCountLabel,
		animalCountLabel;

	/**
	 * Construct
	 */
	public WorldControlPanel( World world )
	{
		// Save
		this.world = world;
		
		// Delay between frames control
			// Label
				JLabel label = new JLabel( "Simulation speed" );
				label.setForeground( Color.WHITE );
			// Add label
				super.add( label );
			// Create slider
				JSlider js = new JSlider( JSlider.HORIZONTAL,
					ViewState.MINIMUM_DELAY_BETWEEN_FRAMES,
					ViewState.MAXIMUM_DELAY_BETWEEN_FRAMES,
					ViewState.MINIMUM_DELAY_BETWEEN_FRAMES + ViewState.MAXIMUM_DELAY_BETWEEN_FRAMES - world.getState( ).getViewState( ).getDelayBetweenFrames( ) );
			// No background
				js.setBackground( new Color( 0x00000000,
					true ) );
			// No graduation
				js.setPaintTicks( false );
			// The track must be painted
				js.setPaintTrack( true );
			// Labels details
				// We want label to be painted
					js.setPaintLabels( true );
				// Create the labels
					// Table
						Hashtable<Integer, JLabel> ht = new Hashtable<Integer, JLabel>( );
					// Left side
						label = new JLabel( "Slow" );
						label.setForeground( Color.WHITE );
						ht.put( new Integer( ViewState.MINIMUM_DELAY_BETWEEN_FRAMES ),
							label );
					// Right side
						label = new JLabel( "Fast" );
						label.setForeground( Color.WHITE );
						ht.put( new Integer( ViewState.MAXIMUM_DELAY_BETWEEN_FRAMES ),
							label );
					// Set this table
						js.setLabelTable( ht );
			// Add event gesture
				js.addChangeListener( new SliderFrameDelayAction( world.getState( ).getViewState( ) ) );
			// Add to world control panel
				super.add( js );
				
		// Round count
			// Create label
				this.roundCountLabel = new JLabel( "Days count:" );
			// Set color
				this.roundCountLabel.setForeground( Color.WHITE );
			// Add
				this.add( this.roundCountLabel );
				
		// World state
			// Create
				this.groupCountLabel = new JLabel( "Group count: " );
				this.plantCountLabel = new JLabel( "Plant group count: " );
				this.animalCountLabel = new JLabel( "Animal count: " );
			// Set color
				this.groupCountLabel.setForeground( Color.WHITE );
				this.plantCountLabel.setForeground( Color.WHITE );
				this.animalCountLabel.setForeground( Color.WHITE );
			// Add
				this.add( this.groupCountLabel );
				this.add( this.plantCountLabel );
				this.add( this.animalCountLabel );
				
		// Notify changes
		super.revalidate( );
	}
	
	/**
	 * Update
	 */
	@Override
	public void update( Graphics g )
	{
		this.paint( g );
	}
	
	/**
	 * Paint
	 */
	@Override
	public void paint( Graphics g )
	{
		// Update labels
			// Round count
				this.roundCountLabel.setText( "Round count (days): "
					+ this.world.getState( ).getRound( ) );
			// World state
				this.groupCountLabel.setText( "Group count: "
					+ this.world.getGroupCount( ) );
				this.plantCountLabel.setText( "Plant group count: "
					+ this.world.getPlantGroupCount( ) );
				this.animalCountLabel.setText( "Animal count: "
					+ this.world.getAnimalCount( ) );

		// Paint content
		super.paint( g );
		
		// Paint border
			// Set color
				g.setColor( Color.WHITE );
			// Paint
				g.drawRect( 0,
					0,
					super.getWidth( ) - 1,
					super.getHeight( ) - 1 );
	}
}
