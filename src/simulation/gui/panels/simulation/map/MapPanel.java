package simulation.gui.panels.simulation.map;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import simulation.gui.panels.simulation.AlphaEvolution;
import simulation.world.World;

public class MapPanel extends JPanel
{
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = -8697727959100052026L;
	
	/**
	 * Is paint ready reference
	 */
	private boolean isPaintReady = false;
	
	/**
	 * Alpha evolution for selected map
	 */
	private AlphaEvolution selectedMapAlpha;
	
	/**
	 * World
	 */
	private World world;
	
	/**
	 * Construct the MapPanel
	 * 
	 * @param world
	 * 		The current world
	 */
	public MapPanel( World world,
		AlphaEvolution selectedMapAlpha )
	{
		// Parent constructor
		super( );
		
		// Save
		this.world = world;
		this.selectedMapAlpha = selectedMapAlpha;
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
		
		// Clear
		g.setColor( Color.BLACK );
		g.fillRect( 0,
			0,
			super.getWidth( ),
			super.getHeight( ) );
		
		// Draw content
		this.world.drawWorld( g,
			this,
			selectedMapAlpha );
		
		// Draw border
		g.setColor( Color.WHITE );
		g.drawRect( 0,
			0,
			super.getWidth( ) - 1,
			super.getHeight( ) - 1 );
	}
}
