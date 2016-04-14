package simulation.math.point;

/**
 * Point. Refers to a particular location .
 * 
 * @author Clément Thévin
 * 
 */
public class Point<T> {
	/**
	 * The x and y coordinates
	 */
	private T x;
	private T y;

	/**
	 * Construct the point
	 * 
	 * @param x
	 *            The initial x
	 * @param y
	 *            The initial y
	 */
	public Point(T x, T y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x
	 */
	public T getX() {
		return this.x;
	}

	/**
	 * @param x
	 *            The x to set
	 */
	public void setX(T x) {
		this.x = x;
	}

	/**
	 * @return y
	 */
	public T getY() {
		return this.y;
	}

	/**
	 * @param y
	 *            The y to set
	 */
	public void setY(T y) {
		this.y = y;
	}

	/**
	 * @return String value of object
	 */
	public String toString() {
		return "xy<"
			+ this.x.getClass().getTypeName()
			+ ">("
			+ this.x
			+ ", "
			+ this.y
			+ ")";
	}
}
