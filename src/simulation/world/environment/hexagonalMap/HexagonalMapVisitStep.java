package simulation.world.environment.hexagonalMap;

import simulation.math.hexagon.HexagonEdge;
import simulation.math.point.Point;

/**
 * The different step of a visit in a
 * hexagonal map field.
 * 
 * @author Lucas SOARES
 */
public enum HexagonalMapVisitStep
{
	// To the right, only one time
	HEXAGONAL_MAP_VISIT_STEP_RIGHT_START,
	
	// To the bottom-right, actual ring time(s)
	HEXAGONAL_MAP_VISIT_STEP_BOTTOM_RIGHT,
	
	// To the bottom-left, actual ring + 1 time(s)
	HEXAGONAL_MAP_VISIT_STEP_BOTTOM_LEFT,
	
	// To the left, actual ring + 1 time(s)
	HEXAGONAL_MAP_VISIT_STEP_LEFT,
	
	// To the top-left, actual ring + 1 time(s)
	HEXAGONAL_MAP_VISIT_STEP_TOP_LEFT,
	
	// To the top-right, actual ring + 1 time(s)
	HEXAGONAL_MAP_VISIT_STEP_TOP_RIGHT,
	
	// To the right, actual ring + 1 time(s)
	HEXAGONAL_MAP_VISIT_STEP_RIGHT_END;
	
	/**
	 * Convert exagonalMapVisitStep to HexagoneEdge
	 * 
	 * @param the hexagonal map visit step to convert
	 * 
	 * @return the converted edge
	 */
	public static HexagonEdge convertHexagonalMapVisitStepToHexagonalEdge( HexagonalMapVisitStep hmvs )
	{
		switch( hmvs )
		{				
			case HEXAGONAL_MAP_VISIT_STEP_BOTTOM_LEFT:
				return HexagonEdge.HEXAGONE_EDGE_DOWN_LEFT;
				
			case HEXAGONAL_MAP_VISIT_STEP_BOTTOM_RIGHT:
				return HexagonEdge.HEXAGONE_EDGE_DOWN_RIGHT;
				
			case HEXAGONAL_MAP_VISIT_STEP_LEFT:
				return HexagonEdge.HEXAGONE_EDGE_LEFT;
				
			default:
			case HEXAGONAL_MAP_VISIT_STEP_RIGHT_END:
			case HEXAGONAL_MAP_VISIT_STEP_RIGHT_START:
				return HexagonEdge.HEXAGONE_EDGE_RIGHT;
				
			case HEXAGONAL_MAP_VISIT_STEP_TOP_LEFT:
				return HexagonEdge.HEXAGONE_EDGE_UP_LEFT;
				
			case HEXAGONAL_MAP_VISIT_STEP_TOP_RIGHT:
				return HexagonEdge.HEXAGONE_EDGE_UP_RIGHT;
		}
	}
}
