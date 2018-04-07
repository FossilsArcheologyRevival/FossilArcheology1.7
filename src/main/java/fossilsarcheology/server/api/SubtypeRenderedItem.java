package fossilsarcheology.server.api;

public interface SubtypeRenderedItem {
	int[] getUsedSubtypes();

	String getResource(String name, int metadata);
}
