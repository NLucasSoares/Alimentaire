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
