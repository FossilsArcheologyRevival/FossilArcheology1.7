package fossilsarcheology.client.model;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ModelUtils {

	public static void rotate(ModelAnimator animator, ModelRenderer box, double x, double y, double z) {
		animator.rotate(box, (float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
	}

	public static void rotateToInit(ModelAnimator animator, AdvancedModelRenderer box) {
		animator.rotate(box, getDefaultRotationX(box), getDefaultRotationY(box), getDefaultRotationZ(box));
	}

	public static void faceTargetMod(AdvancedModelRenderer part, float f3, float f4, float multi) {
		part.rotateAngleY += f3 / (180F / (float) Math.PI) * multi;
		part.rotateAngleX += f4 / (180F / (float) Math.PI) * multi;

	}

	public static void setRotateAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
		AdvancedModelRenderer.rotateAngleX = x;
		AdvancedModelRenderer.rotateAngleY = y;
		AdvancedModelRenderer.rotateAngleZ = z;
	}

	public static void setRotateAngleAlt(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
		AdvancedModelRenderer.rotateAngleX = (float) Math.toRadians(x);
		AdvancedModelRenderer.rotateAngleY = (float) Math.toRadians(y);
		AdvancedModelRenderer.rotateAngleZ = (float) Math.toRadians(z);
	}

	public static float getDefaultRotationX(AdvancedModelRenderer box) {
		return getField(box, "defaultRotationX");
	}

	public static float getDefaultRotationY(AdvancedModelRenderer box) {
		return getField(box, "defaultRotationY");

	}

	public static float getDefaultRotationZ(AdvancedModelRenderer box) {
		return getField(box, "defaultRotationZ");

	}

	public static float getDefaultPositionX(AdvancedModelRenderer box) {
		return getField(box, "defaultPositionX");

	}

	public static float getDefaultPositionY(AdvancedModelRenderer box) {
		return getField(box, "defaultPositionY");
	}

	public static float getDefaultPositionZ(AdvancedModelRenderer box) {
		return getField(box, "defaultPositionZ");
	}

	private static float getField(AdvancedModelRenderer box, String name) {
		float f = 0;
		try {
			f = ReflectionHelper.findField(AdvancedModelRenderer.class, new String[]{name, name}).getFloat(box);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return f;
	}
}
