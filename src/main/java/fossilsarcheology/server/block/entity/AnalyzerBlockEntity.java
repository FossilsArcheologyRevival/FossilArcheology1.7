package fossilsarcheology.server.block.entity;

import fossilsarcheology.Revival;
import fossilsarcheology.server.achievement.FossilAchievements;
import fossilsarcheology.server.block.AnalyzerBlock;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityType;
import fossilsarcheology.server.entity.prehistoric.TimePeriod;
import fossilsarcheology.server.item.DinosaurBoneItem;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.item.variant.DinosaurBoneType;
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
import net.minecraft.util.text.translation.I18n;

import java.util.Random;

public class AnalyzerBlockEntity extends TileEntity implements IInventory, ISidedInventory, ITickable {
    private static final int[] SLOTS_TOP = new int[] {};
    private static final int[] SLOTS_BOTTOM = new int[] { 10, 11, 12 };
    private static final int[] SLOTS_SIDES = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

    public int analyzeFuelTime = 0;
    public int currentFuelTime = 100;
    public int analyzeTime = 0;
    private String customName;
    private NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(13, ItemStack.EMPTY);
    private int rawIndex = -1;
    private int spaceIndex = -1;


    private static int getFuelTime(ItemStack stack) {
        return 100;
    }

    public static boolean isFuel(ItemStack stack) {
        return getFuelTime(stack) > 0;
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
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.stacks.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        return ItemStackHelper.getAndSplit(this.stacks, slot, amount);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.stacks, index);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.stacks.set(slot, stack);
        if (stack != null && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }
    }

    public void setCustomName(String name) {
        this.customName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.stacks);
        this.analyzeFuelTime = compound.getShort("FuelTime");
        this.analyzeTime = compound.getShort("AnalyzeTime");
        this.currentFuelTime = 100;
        if (compound.hasKey("CustomName")) {
            this.customName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);
        compound.setShort("FuelTime", (short) this.analyzeFuelTime);
        compound.setShort("AnalyzeTime", (short) this.analyzeTime);
        ItemStackHelper.saveAllItems(compound, this.stacks);
        if (this.hasCustomName()) {
            compound.setString("CustomName", this.customName);
        }
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public int getAnalyzeProgressScaled(int scale) {
        return this.analyzeTime * scale / 200;
    }

    public boolean isAnalyzing() {
        return this.analyzeFuelTime > 0;
    }

    @Override
    public void update() {
        for (EntityPlayer player : this.world.playerEntities) {
            if (this.getDistanceSq(player.posX, player.posY, player.posZ) < 40) {
                for (int slot = 12; slot > 8; --slot) {
                    ItemStack stack = this.stacks.get(slot);
                    if (stack != null) {
                        if (stack.getItem() == FAItemRegistry.STONE_TABLET) {
                           // player.addStat(FossilAchievements.TABLET, 1);
                        }
                    }
                }
            }
        }
        boolean fueled = this.analyzeFuelTime > 0;
        boolean dirty = false;
        if (this.analyzeFuelTime > 0) {
            --this.analyzeFuelTime;
        }
        if (!this.world.isRemote) {
            if (this.analyzeFuelTime == 0 && this.canAnalyze()) {
                this.currentFuelTime = this.analyzeFuelTime = 100;
                dirty = true;
            }
            if (this.isAnalyzing() && this.canAnalyze()) {
                ++this.analyzeTime;
                if (this.analyzeTime == 200) {
                    this.analyzeTime = 0;
                    this.analyzeItem();
                    dirty = true;
                }
            } else {
                this.analyzeTime = 0;
            }
            if (fueled != this.analyzeFuelTime > 0) {
                dirty = true;
                AnalyzerBlock.setState(this.analyzeFuelTime > 0, this.world, this.pos);
            }
        }
        if (dirty) {
            this.markDirty();
        }
    }

    private boolean canAnalyze() {
        this.spaceIndex = -1;
        this.rawIndex = -1;
        for (int slot = 0; slot < 9; ++slot) {
            if (this.stacks.get(slot) != null) {
                Item item = this.stacks.get(slot).getItem();
                if (PrehistoricEntityType.isFoodItem(this.stacks.get(slot).getItem()) || (item instanceof DinosaurBoneItem) || (item == FAItemRegistry.BIOFOSSIL) || (item == FAItemRegistry.TAR_FOSSIL) || /*(item == FAItemRegistry.TAR_DROP) || (item == FAItemRegistry.FAILURESAURUS_FLESH) || */ (item == FAItemRegistry.RELIC_SCRAP) || (item == Items.PORKCHOP) || (item == Items.BEEF) || (item == Items.EGG) || (item == Items.CHICKEN) || (item == Item.getItemFromBlock(Blocks.WOOL)) || /*(item == FAItemRegistry.ICED_MEAT) || */ (item == Items.LEATHER) || (item == FAItemRegistry.PLANT_FOSSIL)) {
                    this.rawIndex = slot;
                    break;
                }
            }
        }
        if (this.rawIndex == -1) {
            return false;
        } else {
            for (int slot = 12; slot > 8; --slot) {
                if (this.stacks.get(slot) == null) {
                    this.spaceIndex = slot;
                    break;
                }
            }
            return this.spaceIndex != -1 && this.rawIndex != -1;
        }
    }

    public void analyzeItem() {
        if (this.canAnalyze()) {
            ItemStack output = null;
            Random random = this.world.rand;
            int rand = random.nextInt(100);
            Item rawItem = this.stacks.get(rawIndex).getItem();
            if (rawItem instanceof DinosaurBoneItem) {
                if (!Revival.RELEASE_TYPE.enableDebugging()) {
                    if (rand > -1 && rand <= 30) {
                        output = new ItemStack(Items.DYE, 3, 15);
                    }
                    if (rand > 30 && rand <= 65) {
                        output = new ItemStack(Items.BONE, 3);
                    }
                    if (rand > 65) {
                        output = new ItemStack(DinosaurBoneType.getEntity(DinosaurBoneType.values()[this.stacks.get(rawIndex).getItemDamage()]).dnaItem, 1);
                    }
                } else {
                    output = new ItemStack(PrehistoricEntityType.getRandomTimePeriod(random, TimePeriod.MESOZOIC).dnaItem, 1);
                }
            } else if (rawItem == FAItemRegistry.BIOFOSSIL) {
                if (!Revival.RELEASE_TYPE.enableDebugging()) {
                    if (rand > -1 && rand <= 50) {
                        output = new ItemStack(Items.DYE, 3, 15);
                    }
                    if (rand > 50 && rand <= 85) {
                        output = new ItemStack(Blocks.SAND, 1 + random.nextInt(2));
                    }
                    if (rand > 85) {
                        output = new ItemStack(PrehistoricEntityType.getRandomTimePeriod(random, TimePeriod.MESOZOIC).dnaItem, 1);
                    }
                } else {
                    output = new ItemStack(PrehistoricEntityType.getRandomTimePeriod(random, TimePeriod.MESOZOIC).dnaItem, 1);
                }
            }/* else if (rawItem == FAItemRegistry.TAR_FOSSIL) {
                if (rand > -1 && rand <= 50) {
                    output = new ItemStack(Items.DYE, 3, 15);
                }
                if (rand > 50 && rand <= 80) {
                    output = new ItemStack(FABlockRegistry.VOLCANIC_ROCK, 1);
                }
                if (rand > 80 && rand <= 75) {
                    output = new ItemStack(Blocks.OBSIDIAN, 1);
                }
                if (rand > 75) {
                    output = new ItemStack(PrehistoricEntityType.getRandomTimePeriod(random, TimePeriod.CENOZOIC).dnaItem, 1);
                }
            } else if (rawItem == FAItemRegistry.TARDROP) {
                if (rand >= 0 && rand <= 40) {
                    output = new ItemStack(Items.COAL, random.nextInt(2) + 1, random.nextInt(1));
                }
                if (rand > 40 && rand <= 85) {
                    output = new ItemStack(FAItemRegistry.TAR_FOSSIL, 1);
                }
                if (rand > 85) {
                    output = new ItemStack(FABlockRegistry.VOLCANIC_ROCK, 1);
                }
            } else if (rawItem == FAItemRegistry.PLANT_FOSSIL) {
                if (rand > 0) {
                    output = new ItemStack(Blocks.SAND, 1 + random.nextInt(1), 0);
                }
                if (rand > 35 && rand <= 65) {
                    output = new ItemStack(Items.COAL, 1, 0);
                }
                if (rand > 65 && rand <= 75) {
                    output = new ItemStack(FAItemRegistry.PALAE_SAPLING_FOSSIL, 1, 0);
                }
                if (rand > 75 && rand <= 85) {
                    output = new ItemStack(Items.DYE, 1, 2);
                }
                if (rand > 85) {
                    output = new ItemStack(FAItemRegistry.FOSSIL_SEED, 1, random.nextInt(14));
                }
            } else if (rawItem == Item.getItemFromBlock(Blocks.WOOL)) {
                if ((random).nextInt(50) <= 30) {
                    output = new ItemStack(Items.STRING, 4);
                } else {
                    output = new ItemStack(PrehistoricEntityType.SHEEP.dnaItem, 1);
                }
            } else if (PrehistoricEntityType.getDNA(rawItem) != null) {
                output = new ItemStack(PrehistoricEntityType.getDNA(rawItem), 1);
            } else if (rawItem == Items.PORKCHOP) {
                output = new ItemStack(PrehistoricEntityType.PIG.dnaItem, 2);
            } else if (rawItem == Items.BEEF) {
                output = new ItemStack(PrehistoricEntityType.COW.dnaItem, 2);
            } else if (rawItem == FAItemRegistry.FAILURESAURUS_FLESH) {
                int randChoice = random.nextInt(3);
                if (randChoice == 0) {
                    output = new ItemStack(Items.ROTTEN_FLESH, 1);
                } else {
                    output = new ItemStack(PrehistoricEntityType.getRandom().dnaItem, 1);
                }
            } else if (rawItem == Items.LEATHER) {
                if (random.nextInt(10) > 3) {
                    output = new ItemStack(PrehistoricEntityType.COW.dnaItem, 1);
                } else {
                    output = new ItemStack(PrehistoricEntityType.HORSE.dnaItem, 1);
                }
            } else if (rawItem == Items.EGG) {
                output = new ItemStack(PrehistoricEntityType.CHICKEN.dnaItem, 1);
            } else if (rawItem == Items.CHICKEN) {
                output = new ItemStack(PrehistoricEntityType.CHICKEN.dnaItem, 1);
            } else if (rawItem == FAItemRegistry.ICED_MEAT) {
                if (rand >= 15) {
                    output = new ItemStack(Items.CHICKEN, 1);
                }
                if (rand >= 15 && rand < 30) {
                    output = new ItemStack(Items.CHICKEN, 1);
                }
                if (rand >= 30 && rand < 45) {
                    output = new ItemStack(Items.PORKCHOP, 1);
                }
                if (rand >= 45 && rand < 65) {
                    output = new ItemStack(PrehistoricEntityType.getRandomTimePeriod(random, TimePeriod.CENOZOIC).dnaItem);
                }
                if (rand >= 65 && rand < 85) {
                    output = new ItemStack(FAItemRegistry.TAR_FOSSIL);
                }
                if (output == null) {
                    output = new ItemStack(Items.BEEF);
                }
            } else if (rawItem == FAItemRegistry.RELIC_SCRAP) {
                if (rand <= 40) {
                    output = new ItemStack(Blocks.GRAVEL, 1 + random.nextInt(2));
                }
                if (rand > 40 && rand <= 70) {
                    output = new ItemStack(FAItemRegistry.STONE_TABLET, 1);
                }
                if (rand > 70 && rand <= 88) {
                    output = new ItemStack(Items.FLINT, 1 + random.nextInt(1));
                }
                if (rand > 88 && rand <= 92) {
                    output = new ItemStack(FAItemRegistry.POTTERY_SHARD, 1);
                }
                if (rand > 92 && rand <= 96) {
                    if (random.nextFloat() < 0.7) {
                        output = new ItemStack(FABlockRegistry.FIGURINE, 1, random.nextInt(5) + 10);
                    } else {
                        output = new ItemStack(FABlockRegistry.FIGURINE, 1, random.nextInt(5) + 5);
                    }
                }
                if (rand > 96) {
                    output = new ItemStack(FAItemRegistry.BROKEN_SWORD, 1);
                }
            }*/
            if (output != null) {
                for (int slot = 9; slot < 13; slot++) {
                    ItemStack stack = this.stacks.get(slot);
                    if (stack != null) {
                        if (stack.isItemEqual(output) && stack.getCount() + output.getCount() < 64) {
                            stack.setCount(stack.getCount() + output.getCount());
                            if (this.stacks.get(this.rawIndex).getCount() > 1) {
                                this.stacks.get(this.rawIndex).shrink(1);
                            } else {
                                this.stacks.set(this.rawIndex, ItemStack.EMPTY);
                            }
                            break;
                        }
                    } else {
                        this.stacks.set(slot, output);
                        if (this.stacks.get(this.rawIndex).getCount() > 1) {
                            this.stacks.get(this.rawIndex).shrink(1);
                        } else {
                            this.stacks.set(this.rawIndex, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }
        }
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return slot != 2 && (slot != 1 || isFuel(stack));
    }

    @Override
    public int getField(int id) {
        switch (id) {
            case 0:
                return this.analyzeFuelTime;
            case 1:
                return this.currentFuelTime;
            case 2:
                return this.analyzeTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.analyzeFuelTime = value;
                break;
            case 1:
                this.currentFuelTime = value;
                break;
            case 2:
                this.analyzeTime = value;
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
    public void openInventory(EntityPlayer player) {
        for (int slots = 12; slots > 8; --slots) {
            if (this.stacks.get(slots)!= ItemStack.EMPTY) {
                if (this.stacks.get(slots).getItem() == FAItemRegistry.STONE_TABLET) {
                    //player.addStat(FossilAchievements.TABLET, 1);
                }
            }
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
        return this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction != EnumFacing.DOWN || index != 1;
    }

    @Override
    public String getName() {
        return this.hasCustomName() ? this.customName : I18n.translateToLocal("tile.analyzer.name");
    }

    @Override
    public boolean hasCustomName() {
        return this.customName != null && this.customName.length() > 0;
    }
}
