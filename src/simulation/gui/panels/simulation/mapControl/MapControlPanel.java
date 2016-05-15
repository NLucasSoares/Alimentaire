package simulation.gui.panels.simulation.mapControl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import simulation.constant.SimulationConstant;
import simulation.world.animal.group.Group;
import simulation.world.animal.species.state.AnimalState;
import simulation.world.environment.Map;

public class MapControlPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2191301922292426939L;

	/**
	 * Is paint ready reference
	 */
	private boolean isPaintReady = false;
	
	/**
	 * Map
	 */
	private Map map;
	
	/**
	 * Label
	 */
	private JLabel label;
	
	/**
	 * Construct the MapPanel
	 * 
	 * @param world
	 * 		The current world
	 */
	public MapControlPanel( Map map )
	{
		// Parent constructor
		super( );
		
		// Save
		this.map = map;
		
		// Init
			// Construct
				this.label = new JLabel( );
			// Set color
				this.label.setForeground( Color.WHITE );
		
		// Add JLabel
		this.add( this.label );
		
		super.revalidate( );
		
	}
	
	/**
	 * Set the map
	 * 
	 * @param map
	 * 		The map to set
	 */
	public void setMap( Map map )
	{
		this.map = map;
	}

	/**
	 * Give paint ready reference
	 * 
	 * @param isPaintReady
	 * 		The reference to paint ready state
	 */
	public void setPaintReady( boolean isPaintReady )
	{
		this.isPaintReady = isPaintReady;
	}
	
	/**
	 * Compose animal state text
	 * 
	 * @return animal state html code
	 */
	private String composeStateText( )
	{
		// Output
		String output = "<html>";
		
		// Map name
		output += "<h1 style=\"text-align: center;\">"
			+ this.map.getName( )
			+ "</h1>";
		
		// Animal state
		output += "<p style=\"text-align: center\">Group state ("
			+ this.map.getState( ).getGroupCount( )
			+ "):</p>";
		
		// Group list
		for( Iterator<Group> it = this.map.getState( ).getAnimalGroup( ); it.hasNext( ); )
		{
			// Get group
			Group g = it.next( );
			
			// Add
			output += "<p style=\"text-align: center\">"
				+ g.getAnimal( ).getName( )
				+ " group:"
				+ "</p>";
			
			// Group details
			output += "<ul style=\"margin-left: 0; list-style: none;\">";
			for( Iterator<AnimalState> it2 = g.getAnimalState( ).iterator( ); it2.hasNext( ); )
			{
				// Get animal
				AnimalState animal = it2.next( );
				
				// Details
				output += "<li>Born on round "
					+ animal.getBirthDate( )
					+ ", "
					+ animal.getHealthState( ).getHealthPoint( )
					+ "/"
					+ SimulationConstant.MAXIMUM_ANIMAL_HEALTH_POINT
					+ "</li>";
			}
			output += "</ul>";
		}
		
		// Return
		return output + "</html>";
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
		// Check if ready
		if( !this.isPaintReady )
			return;
		
		// Set label text
		this.label.setText( this.composeStateText( ) );

		// Paint parent
		super.paint( g );
		
		// Draw border
		g.setColor( Color.WHITE );
		g.drawRect( 0,
			0,
			super.getWidth( ) - 1,
			super.getHeight( ) - 1 );
	}
}
