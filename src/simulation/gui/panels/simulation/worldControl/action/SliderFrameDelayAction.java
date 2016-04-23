package simulation.gui.panels.simulation.worldControl.action;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simulation.ViewState;

public class SliderFrameDelayAction implements ChangeListener
{
	/**
	 * The view state for the current world
	 */
	private ViewState viewStateReference;
	
	/**
	 * Construct slider frame delay action
	 * 
	 * @param viewState
	 * 		A reference to the world view state
	 */
	public SliderFrameDelayAction( ViewState viewState )
	{
		// Save
		this.viewStateReference = viewState;
	}
	
	/**
	 * The current state change
	 * 
	 * @param e
	 * 		The details for the change
	 */
	@Override
	public void stateChanged( ChangeEvent e )
	{
		this.viewStateReference.setDelayBetweenFrames( ViewState.MINIMUM_DELAY_BETWEEN_FRAMES + ViewState.MAXIMUM_DELAY_BETWEEN_FRAMES - ( (JSlider)e.getSource( ) ).getValue( ) );
	}

}
