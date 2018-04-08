package fossilsarcheology.server.entity.prehistoric;


import net.minecraft.util.text.TextFormatting;

public enum PrehistoricMoodType {
	ANGRY(0, -71, TextFormatting.DARK_RED), SAD(48, -36, TextFormatting.GOLD), CALM(95, 0, TextFormatting.YELLOW), CONTENT(142, 36, TextFormatting.GREEN), HAPPY(190, 71, TextFormatting.DARK_GREEN);
	public int uv;
	public int value;
	public TextFormatting color;


	PrehistoricMoodType(int uv, int value, TextFormatting color) {
		this.uv = uv;
		this.value = value;
		this.color = color;
	}
}
