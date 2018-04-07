package fossilsarcheology.server.entity.prehistoric;

public enum Diet {
	CARNIVORE(3),
	HERBIVORE(0),
	OMNIVORE(1),
	PISCIVORE(1),
	CARNIVORE_EGG(2),
	INSECTIVORE(0),
	PISCCARNIVORE(3),
	NONE(0);

	private int fearIndex;

	Diet(int fearIndex) {
		this.fearIndex = fearIndex;
	}

	public int getFearIndex() {
		return this.fearIndex;
	}
}
