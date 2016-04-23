package simulation;

import simulation.math.point.Point;
import simulation.world.environment.Map;

/**
 * The actual view state of the world
 * 
 * @author SOARES Lucas
 */
public class ViewState
{
	/**
	 * Default zoom level
	 */
	public static final int DEFAULT_VIEW_STATE_ZOOM = 5;
	
	/**
	 * Default delay between frames
	 */
	public static final int DEFAULT_DELAY_BETWEEN_FRAMES = 100;
	
	/**
	 * Minimum delay between frames
	 * (10ms)
	 */
	public static final int MINIMUM_DELAY_BETWEEN_FRAMES = 5;
	
	/**
	 * Maximum delay between frames
	 * (2s)
	 */
	public static final int MAXIMUM_DELAY_BETWEEN_FRAMES = 1000;
	
	/**
	 * The actual selected map
	 */
	private Map selectedMap;
	
	/**
	 * The zoom level
	 */
	private int zoomLevel;
	
	/**
	 * The actual position of the
	 * camera in the world (where
	 * [0;0] is the center of the
	 * world.)
	 */
	private Point<Integer> scrollPosition;

	/**
	 * Delay between two frames of the
	 * simulation
	 */
	private int delayBetweenFrames;
	
	/**
	 * Construct the viewState
	 * 
	 * @param centerMap
	 * 		The map at the center of world
	 */
	public ViewState( Map centerMap )
	{
		// Save
		this.selectedMap = centerMap;

		// Init
		this.delayBetweenFrames = ViewState.DEFAULT_DELAY_BETWEEN_FRAMES;
		this.resetCamera( );
	}
	
	/**
	 * Reset the viewstate
	 */
	public void resetCamera( )
	{
		this.zoomLevel = ViewState.DEFAULT_VIEW_STATE_ZOOM;
		this.scrollPosition = new Point<Integer>( 0,
			0 );
	}
	
	/**
	 * @return the selectedMap
	 */
	public Map getSelectedMap( )
	{
		return selectedMap;
	}

	/**
	 * @param selectedMap
	 * 		the selected map to set
	 */
	public void setSelectedMap( Map selectedMap )
	{
		this.selectedMap = selectedMap;
	}

	/**
	 * @return the zoomLevel
	 */
	public int getZoomLevel( )
	{
		return zoomLevel;
	}

	/**
	 * @param zoomLevel
	 * 		the zoom level to set
	 */
	public void setZoomLevel( int zoomLevel )
	{
		this.zoomLevel = zoomLevel;
	}

	/**
	 * @return the position
	 */
	public Point<Integer> getScrollPosition( )
	{
		return this.scrollPosition;
	}

	/**
	 * @param position
	 * 		the position to set
	 */
	public void setScrollPosition( Point<Integer> scrollPosition )
	{
		this.scrollPosition = scrollPosition;
	}

	/**
	 * @return the delay between frames
	 */
	public int getDelayBetweenFrames( )
	{
		return delayBetweenFrames;
	}

	/**
	 * @param delayBetweenFrames
	 * 		the delay between frames to set
	 */
	public void setDelayBetweenFrames( int delayBetweenFrames )
	{
		this.delayBetweenFrames = delayBetweenFrames;
	}
	
	
}
