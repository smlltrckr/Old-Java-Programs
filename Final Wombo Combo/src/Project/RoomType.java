package Project;

import java.io.Serializable;

/**
 * Enum type describing the possible room types
 * @author Team Wombo Combo
 *
 */
public enum RoomType implements Serializable {
	Economy(100), Luxury(200);
	private int cost;

	/**
	 * Constructs the Room Type with its associated cost.
	 * @param cost the cost
	 */
	private RoomType(int cost) {
		this.cost = cost;
	}

	/**
	 * Gets the cost of the Room Type
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}
}
