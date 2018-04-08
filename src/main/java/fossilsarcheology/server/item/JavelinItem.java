package fossilsarcheology.server.item;

import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.entity.projectile.AncientJavelinEntity;
import fossilsarcheology.server.entity.projectile.JavelinEntity;
import fossilsarcheology.server.tab.FATabRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class JavelinItem extends Item implements DefaultRenderedItem {
	public ToolMaterial material;
	private boolean ancient;

	public JavelinItem(ToolMaterial material, String name, boolean ancient) {
		super();
		this.maxStackSize = 16;
		this.setMaxDamage(30);
		this.hasSubtypes = false;
		this.material = material;
		this.bFull3D = true;
		this.ancient = ancient;
		this.setUnlocalizedName(name);
		this.setCreativeTab(FATabRegistry.ITEMS);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			boolean infinite = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			int time = this.getMaxItemUseDuration(stack) - timeLeft;
			float speed = (float) time / 20.0F;
			speed = (speed * speed + speed * 2.0F) / 3.0F;
			if (speed < 0.1) {
				return;
			}
			if (speed > 1.0F) {
				speed = 1.0F;
			}
			int damage = stack.getMaxDamage() - (stack.getItemDamage() + (player.capabilities.isCreativeMode ? 0 : 1));
			JavelinEntity javelin;
			if (!this.ancient) {
				javelin = new JavelinEntity(world, this.material, damage, player);
			} else {
				javelin = new AncientJavelinEntity(world, damage, player);
			}
			javelin.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, speed * 2.0F, 1.0F);
			if (speed >= 1.0F) {
				javelin.setIsCritical(true);
			}
			int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
			if (power > 0) {
				javelin.setDamage(javelin.getDamage() + power * 0.5 + 0.5D);
			}
			int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
			if (punch > 0) {
				javelin.setKnockbackStrength(punch);
			}
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
				javelin.setFire(100);
			}
			world.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + speed * 0.5F, false);
			if (!infinite) {
				stack.shrink(1);
			} else {
				javelin.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
			}
			if (!world.isRemote) {
				world.spawnEntity(javelin);
			}
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 720000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (player.capabilities.isCreativeMode || player.inventory.hasItemStack(stack)) {
			player.setActiveHand(hand);
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	@Override
	public int getItemEnchantability() {
		return 1;
	}
}