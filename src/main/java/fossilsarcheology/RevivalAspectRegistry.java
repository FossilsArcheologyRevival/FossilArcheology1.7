package fossilsarcheology;

import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * Created by Joseph on 4/29/2018.
 */
public class RevivalAspectRegistry {

	public static final Aspect TIME = new Aspect("chronos", 0Xb5a642, new Aspect[]{Aspect.EXCHANGE, Aspect.MOTION}, new ResourceLocation("fossil:textures/thaumcraft/hourglass.png"), 1);

	public static void register() {
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.BIOFOSSIL), new AspectList().add(Aspect.EARTH, 5).add(Aspect.BEAST, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.AMBER), new AspectList().add(Aspect.EARTH, 3).add(Aspect.CRYSTAL, 3).add(RevivalAspectRegistry.TIME, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_CLOCK), new AspectList().add(Aspect.METAL, 15).add(RevivalAspectRegistry.TIME, 15));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_HELMET), new AspectList().add(Aspect.PROTECT, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_JAVELIN), new AspectList().add(Aspect.AVERSION, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_KEY), new AspectList().add(Aspect.DESIRE, 10).add(Aspect.METAL, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.ANCIENT_SWORD), new AspectList().add(Aspect.AVERSION, 10).add(Aspect.METAL, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.AQUATIC_SCARAB_GEM), new AspectList().add(Aspect.WATER, 5).add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 5).add(RevivalAspectRegistry.TIME, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.DOMINICAN_AMBER), new AspectList().add(Aspect.WATER, 3).add(Aspect.EARTH, 3).add(Aspect.CRYSTAL, 3).add(RevivalAspectRegistry.TIME, 3));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.BROKEN_HELMET), new AspectList().add(Aspect.ENTROPY, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.BROKEN_SWORD), new AspectList().add(Aspect.ENTROPY, 10).add(RevivalAspectRegistry.TIME, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.CHICKEN_ESSENCE), new AspectList().add(Aspect.CRAFT, 5).add(Aspect.BEAST, 5));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.COOKED_CHICKEN_SOUP), new AspectList().add(Aspect.CRAFT, 10).add(Aspect.BEAST, 10).add(Aspect.WATER, 10));
		ThaumcraftApi.registerObjectTag(new ItemStack(FAItemRegistry.COOKED_EGG), new AspectList().add(Aspect.DEATH, 5).add(Aspect.BEAST, 5).add(Aspect.CRAFT, 5));
	}

}
