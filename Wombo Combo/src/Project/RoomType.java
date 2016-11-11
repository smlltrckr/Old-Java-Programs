package Project;

public enum RoomType {
	Economy(100), Luxury(200);
	private int cost;

	private RoomType(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
}
