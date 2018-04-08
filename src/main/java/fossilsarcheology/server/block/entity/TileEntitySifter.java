package fossilsarcheology.server.block.entity;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.SifterBlock;
import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntitySifter extends TileEntity implements IInventory, ISidedInventory, ITickable {

    private static final int[] slots_bottom = new int[]{1, 2, 3, 4, 5}; // output
    private static final int[] slots_top = new int[]{0};// fuel
    public int sifterBurnTime = 0;
    public int currentItemBurnTime = 0;
    public int sifterCookTime = 0;
    private String customName;
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
    private int RawIndex = -1;
    private int SpaceIndex = -1;

    private static int getItemBurnTime(ItemStack var1) {
        return 100;
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    @Override
    public int getSizeInventory() {
        return this.stacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.stacks) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return this.stacks.get(var1);
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        if (this.stacks.get(var1) != ItemStack.EMPTY) {
            ItemStack var3;

            if (this.stacks.get(var1).getCount() <= var2) {
                var3 = this.stacks.get(var1);
                this.stacks.set(var1, ItemStack.EMPTY);
                return var3;
            } else {
                var3 = this.stacks.get(var1).splitStack(var2);

                if (this.stacks.get(var1).getCount() == 0) {
                    this.stacks.set(var1, ItemStack.EMPTY);
                }

                return var3;
            }
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.stacks.set(var1, var2);

        if (var2 != null && var2.getCount() > this.getInventoryStackLimit()) {
            var2.setCount(this.getInventoryStackLimit());
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound var1) {
        super.readFromNBT(var1);
        this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(var1, this.stacks);

        this.sifterBurnTime = var1.getShort("BurnTime");
        this.sifterCookTime = var1.getShort("CookTime");
        this.currentItemBurnTime = 100;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound var1) {
        super.writeToNBT(var1);
        var1.setShort("BurnTime", (short) this.sifterBurnTime);
        var1.setShort("CookTime", (short) this.sifterCookTime);
        ItemStackHelper.saveAllItems(var1, this.stacks);
        return var1;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    public int getSiftProgressScaled(int var1) {
        return this.sifterCookTime * var1 / 200;
    }

    public int getSiftTimeRemainingScaled(int var1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 100;
        }

        return this.sifterBurnTime * var1 / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return this.sifterBurnTime > 0;
    }

    @Override
    public void update() {
        boolean var1 = this.sifterBurnTime > 0;
        boolean var2 = false;
        if (this.sifterBurnTime > 0) {
            --this.sifterBurnTime;
        }

        if (!this.world.isRemote) {
            if (this.sifterBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.sifterBurnTime = 100;

                if (this.sifterBurnTime > 0) {
                    var2 = true;
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.sifterCookTime;

                if (this.sifterCookTime == 200) {
                    this.sifterCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            } else {
                this.sifterCookTime = 0;
            }

            if (var1 != this.sifterBurnTime > 0) {
                var2 = true;
                SifterBlock.setState(this.sifterBurnTime > 0, this.world, this.pos);
            }
        }

        if (var2) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        this.SpaceIndex = -1;
        this.RawIndex = -1;
        int var1;
        for (var1 = 0; var1 < 1; ++var1) {
            if (this.stacks.get(var1) != null) {
                Item input = this.stacks.get(var1).getItem();
                ItemStack itemstack = this.stacks.get(var1);
                if ((input == Item.getItemFromBlock(Blocks.SAND)) || (input == Item.getItemFromBlock(Blocks.DIRT)) || (input == Item.getItemFromBlock(Blocks.GRAVEL)) || (input == Item.getItemFromBlock(Blocks.CLAY)) || (input == Item.getItemFromBlock(FABlockRegistry.VOLCANIC_ASH))) {
                    this.RawIndex = var1;
                    break;
                }
            }
        }
        if (this.RawIndex == -1) {
            return false;
        } else {
            for (var1 = 5; var1 > 0; --var1) {
                if (this.stacks.get(var1) == null) {
                    this.SpaceIndex = var1;
                    break;
                }
            }
            return this.SpaceIndex != -1 && this.RawIndex != -1;
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack result = null;
            int randomloot = (new Random()).nextInt(100);
            double random = (new Random()).nextInt(100);
            int var3;

            if (this.stacks.get(this.RawIndex).getItem() == Item.getItemFromBlock(Blocks.SAND) || this.stacks.get(this.RawIndex).getItem() == Item.getItemFromBlock(Blocks.DIRT) || this.stacks.get(this.RawIndex).getItem() == Item.getItemFromBlock(Blocks.GRAVEL) || this.stacks.get(this.RawIndex).getItem() == Item.getItemFromBlock(Blocks.CLAY) || this.stacks.get(this.RawIndex).getItem() == Item.getItemFromBlock(FABlockRegistry.VOLCANIC_ASH)) {
                if (randomloot < 80) {
                    if (random < 75) {
                        result = null;
                    } else {
                        result = this.stacks.get(this.SpaceIndex);
                    }
                } else {
                    if (random < 0.4) {
                        result = new ItemStack(FAItemRegistry.DOMINICAN_AMBER, 1);
                    } else if (random < 15) {
                        result = new ItemStack(FAItemRegistry.PALAE_SAPLING_FOSSIL, 1);
                    } else if (random < 30) {
                        result = new ItemStack(Items.POTATO, 1);
                    } else if (random < 40) {
                        result = new ItemStack(Items.CARROT, 1);
                    } else if (random < 60) {
                        result = new ItemStack(Items.DYE, 1, 15);
                    } else if (random < 80) {
                        result = new ItemStack(Blocks.SAND, 1);
                    } else if (random < 90) {
                        result = new ItemStack(FAItemRegistry.FERN_SEED, 2);
                    } else if (random < 95) {
                        result = new ItemStack(FAItemRegistry.POTTERY_SHARD, 3);
                    } else if (random <= 100) {
                        int i = (new Random()).nextInt(15);
                        Item i0;
                        if (i == 0) {
                            i0 = FAItemRegistry.PALAE_SAPLING_FOSSIL;
                        } else {
                            i0 = FAItemRegistry.BIOFOSSIL;
                        }

                        result = new ItemStack(i0, 1);
                    }
                }
            }
            if (result != null) {
                for (int slots = 1; slots < 5; slots++) {
                    ItemStack stackInSlot = this.stacks.get(slots);
                    if (stackInSlot != null) {
                        if (stackInSlot.isItemEqual(result) && stackInSlot.getCount() + result.getCount() < 64) {
                            stackInSlot.grow(result.getCount());
                            if (this.stacks.get(this.RawIndex).getCount() > 1) {
                                this.stacks.get(this.RawIndex).shrink(1);
                            } else {
                                this.stacks.set(this.RawIndex, ItemStack.EMPTY);
                            }
                            break;
                        }
                    } else if (stackInSlot == null) {
                        this.stacks.set(slots, result);
                        this.stacks.get(this.RawIndex).shrink(1);
                        break;
                    }
                }
            }
        }
    }

    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 <= 8 && (par1 >= 8 || isItemFuel(par2ItemStack));
    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 0 && isItemFuel(par2ItemStack);
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return this.sifterCookTime;
            case 1:
                return this.sifterBurnTime;
            case 2:
                return this.currentItemBurnTime;
            default:
                return 0;
        }    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.sifterCookTime = value;
                break;
            case 1:
                this.sifterBurnTime = value;
                break;
            case 2:
                this.currentItemBurnTime = value;
                break;
        }
    }

    @Override
    public int getFieldCount() {
        return 3;
    }

    @Override
    public void clear() {
        this.stacks.clear();
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.UP ? slots_top : slots_bottom;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction != EnumFacing.UP || index != 1 || stack.getItem() == Items.BUCKET;
    }

    @Override
    public String getName() {
        return "tile.sifter.name";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
