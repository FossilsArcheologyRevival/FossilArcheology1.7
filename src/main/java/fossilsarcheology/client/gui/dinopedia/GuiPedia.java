package fossilsarcheology.client.gui.dinopedia;


import com.mojang.realmsclient.gui.ChatFormatting;
import fossilsarcheology.Revival;
import fossilsarcheology.server.entity.EntityDinosaurEgg;
import fossilsarcheology.server.entity.EntityFishBase;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.EntityQuagga;
import fossilsarcheology.server.entity.utility.FossilsMammalProperties;
import fossilsarcheology.server.util.FoodMappings;
import net.ilexiconn.llibrary.LLibrary;
import net.ilexiconn.llibrary.client.util.ClientUtils;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@SideOnly(Side.CLIENT)
public class GuiPedia extends GuiScreen {
    private static final ResourceLocation background_image = new ResourceLocation("fossil:textures/gui/dinopedia.png");
    private static final ResourceLocation moods = new ResourceLocation("fossil:textures/gui/dinopedia_mood.png");
    public int xGui = 390;
    public int yGui = 320;
    public ButtonDinopediaPage buttonNextPage;
    public ButtonDinopediaPage buttonPreviousPage;
    public int bookPages;
    public int bookPagesTotal = 1;
    public ButtonDinopedia buttonIcon;
    int left;// counter for text added on the left side
    int right;// same for the right side
    int items;// counter for the minipics down
    private RenderItem itemRender;
    private float mouseX;
    private float mouseY;
    private FoodSorter sorter;
    protected int xSize = 176;
    protected int ySize = 166;
    protected int guiLeft;
    protected int guiTop;

    public GuiPedia() {
        super();
        left = 0;
        right = 0;
        items = 0;
        xSize = 390;
        ySize = 245;
        sorter = new FoodSorter();
    }

    @Override
    public void initGui() {
        buttonList.clear();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int centerX = (this.width - this.xGui) / 2;
        int centerY = (this.height - this.yGui) / 2;
        this.buttonList.add(this.buttonNextPage = new ButtonDinopediaPage(0, centerX + 350, centerY + 240, true, bookPages));
        this.buttonList.add(this.buttonPreviousPage = new ButtonDinopediaPage(1, centerX + 7, centerY + 240, false, bookPages));
        this.itemRender = Minecraft.getMinecraft().getRenderItem();
        addButtonByPage(bookPages);
        super.initGui();
    }

    public void updateScreen() {
        super.updateScreen();
        if (!this.mc.player.isEntityAlive() || this.mc.player.isDead) {
            this.mc.player.closeScreen();
        }
    }

    public void reset() {
        this.left = 0;
        this.right = 0;
        this.items = 0;
    }

    public void addStringLR(String string, boolean left0) {
        this.fontRenderer.drawString(string, 30 + (left0 ? 0 : 121), 12 * ((left0 ? this.left++ : this.right++) + 1), 4210752);
    }

    public void addStringLR(String string, int marginOffset, boolean left0) {
        this.fontRenderer.drawString(string, 30 + (left0 ? marginOffset : 121 + marginOffset), 12 * ((left0 ? this.left++ : this.right++) + 1), 0X9D7E67);
    }

    public void addStringLR(String string, boolean left0, int r, int g, int b) {
        int col = (r << 16) | (g << 8) | b;
        this.fontRenderer.drawString(string, 30 + (left0 ? 0 : 121), 12 * ((left0 ? this.left++ : this.right++) + 1), col);
    }

    public void printStringXY(String str0, int x0, int y0, int r, int g, int b) {
        int col = (r << 16) | (g << 8) | b;
        this.fontRenderer.drawString(str0, x0, y0, col);
    }

    public boolean printItemXY(ItemStack item, int x, int y, int zoom) {
        if (item.getItem() instanceof ItemBlock) {

        } else {
            ScaledResolution scaledResolution = new ScaledResolution(mc);
            final int width = scaledResolution.getScaledWidth();
            final int height = scaledResolution.getScaledHeight();
            final int guiLeft = (width - this.xSize) / 2;
            final int guiTop = (height - this.ySize) / 2;
            final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
            final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;

            int drawSize = zoom * 16;

            if (drawSize < 0) {
                drawSize = 4;
            }

            if (drawSize == 0) {
                drawSize = 8;
            }

            if (drawSize > 160) {
                drawSize = 160;
            }

            GL11.glDisable(GL11.GL_LIGHTING);
            if (item != null && item.getItem() != null) {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                net.minecraft.client.gui.FontRenderer font = null;
                this.itemRender.renderItemAndEffectIntoGUI(item, x, y);
                this.itemRender.renderItemOverlayIntoGUI(font, item, x, y, null);
                GL11.glEnable(GL11.GL_LIGHTING);
                if (mouseX > x && mouseX < x + drawSize) {
                    if (mouseY > y && mouseY < y + drawSize) {
                        List<String> text = new ArrayList<String>();
                        String s1 = item.getDisplayName();
                        text.add(s1);
                        this.drawHoveringText(text, (-this.fontRenderer.getStringWidth(s1) / 2) + 280, 222, fontRenderer);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void addMiniItem(ItemStack item) {
        if (this.printItemXY(item, 230 + 16 * (items % 8), 70 + 16 * (items / 8), 1)) {
            items++;
        }
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        this.drawDefaultBackground();
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) k, (float) l, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        short short1 = 240;
        short short2 = 240;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) short1 / 1.0F, (float) short2 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiContainerForegroundLayer(p_73863_1_, p_73863_2_);
        GL11.glEnable(GL11.GL_LIGHTING);
        InventoryPlayer inventoryplayer = this.mc.player.inventory;
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        if (Revival.PEDIA_OBJECT instanceof EntityPrehistoric || Revival.PEDIA_OBJECT instanceof EntityFishBase || Revival.PEDIA_OBJECT instanceof EntityQuagga) {
            this.buttonNextPage.enabled = true;
        }
        if (bookPages == 0) {
            if(Revival.PEDIA_OBJECT instanceof EntityHorse || Revival.PEDIA_OBJECT instanceof EntityCow || Revival.PEDIA_OBJECT instanceof EntityPig || Revival.PEDIA_OBJECT instanceof EntitySheep || Revival.PEDIA_OBJECT instanceof EntityRabbit){
                FossilsMammalProperties properties = EntityPropertiesHandler.INSTANCE.getProperties((EntityAnimal)Revival.PEDIA_OBJECT, FossilsMammalProperties.class);
                if(properties.embryoProgress > 9999){
                    Minecraft.getMinecraft().displayGuiScreen(null);
                    return;
                }
                EntityAnimal entity = (EntityAnimal)Revival.PEDIA_OBJECT;
                String s1 = I18n.format(entity.getName());
                String s2 = I18n.format("prehistoric.pregnant");
                int quot = (int) Math.floor(((float) properties.embryoProgress / (float) properties.embryo.growTime * 100.0F));
                String s3 = I18n.format("prehistoric.pregnantTime") + String.valueOf(quot) + "%";
                printStringXY(s3, (-this.fontRenderer.getStringWidth(s3) / 2) + 100, 110, 157, 126, 103);
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                printStringXY(s2 + I18n.format(entity.getName()), (-this.fontRenderer.getStringWidth(s2 + s1) / 2) + 65, 60, 66, 48, 36);
            }
            if (Revival.PEDIA_OBJECT instanceof EntityLivingBase) {
                renderFirstPage((EntityLivingBase) Revival.PEDIA_OBJECT);
            } else if (Revival.PEDIA_OBJECT instanceof EntityDinosaurEgg) {
                renderFirstPage((EntityDinosaurEgg) Revival.PEDIA_OBJECT);
            } else if (Revival.PEDIA_OBJECT instanceof EntityFishBase) {
                renderFirstPage((EntityFishBase) Revival.PEDIA_OBJECT);
            }
        }
        /*
         * if (Revival.PEDIA_OBJECT instanceof EntityDinoEgg) { ((EntityDinoEgg)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityPregnantCow) { ((EntityPregnantCow)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityPregnantPig) { ((EntityPregnantPig)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityPregnantHorse) { ((EntityPregnantHorse)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityPregnantSheep) { ((EntityPregnantSheep)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityNewPrehistoric) { ((EntityNewPrehistoric)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityFishBase) { ((EntityFishBase)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityQuagga) { ((EntityQuagga)
		 * Revival.PEDIA_OBJECT).showPedia(this); } else if (Revival.PEDIA_OBJECT
		 * instanceof EntityTerrorBird) { ((EntityTerrorBird)
		 * Revival.PEDIA_OBJECT).showPedia(this); }
		 */
        else {
            if (Revival.PEDIA_OBJECT instanceof EntityPrehistoric) {
                showPrehistoricBio(((EntityPrehistoric) Revival.PEDIA_OBJECT).type.toString());
            } else if (Revival.PEDIA_OBJECT instanceof EntityFishBase) {
                showPrehistoricBio(((EntityFishBase) Revival.PEDIA_OBJECT).selfType.toString());
            } else if (Revival.PEDIA_OBJECT instanceof EntityQuagga) {
                showPrehistoricBio("Quagga");
            }
        }
    }

    public void showPrehistoricBio(String mobName) {
        this.reset();
        this.addStringLR("", 150, false);
        String translatePath = "assets/fossil/dinopedia/" + Minecraft.getMinecraft().gameSettings.language + "/";
        String bioFile = String.valueOf(mobName) + ".txt";
        if (getClass().getClassLoader().getResourceAsStream(translatePath) == null) {
            translatePath = "assets/fossil/dinopedia/" + "en_us" + "/";
        }

        if (getClass().getClassLoader().getResourceAsStream(translatePath + bioFile) != null) {
            InputStream fileReader = getClass().getClassLoader().getResourceAsStream(translatePath + bioFile);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileReader));
                StringBuilder stringBuffer = new StringBuilder();
                String line;
                int linenumber = 0;
                this.addStringLR("", 250, true);
                while ((line = bufferedReader.readLine()) != null) {
                    GL11.glPushMatrix();
                    GL11.glScalef(0.75F, 0.75F, 0.75F);
                    if (linenumber <= 20) {
                        this.addStringLR(line, -125, false);
                    } else {
                        this.addStringLR(line, 250, true);
                    }
                    linenumber++;
                    GL11.glPopMatrix();
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.addStringLR("File not found.", false);
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.addStringLR(translatePath + bioFile, 150, false);
            GL11.glPopMatrix();
        }
    }

    public void renderFirstPage(Entity entity) {
        reset();
        int wordLength = 90;
        if (entity instanceof EntityPrehistoric) {
            EntityPrehistoric dino = (EntityPrehistoric) entity;
            renderFirstPageRight(dino);
            GL11.glPushMatrix();
            String s = I18n.format(entity.getName());
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            printStringXY(I18n.format(entity.getName()), (-this.fontRenderer.getStringWidth(s) / 2) + 65, 60, 66, 48, 36);
            GL11.glPopMatrix();
            {
                String s1 = I18n.format("pedia.age") + " " + dino.getAgeInDays();
                printStringXY(s1, wordLength / 2, 110, 157, 126, 103);
            }
            {
                String s1 = I18n.format("pedia.health") + " " + Math.min(dino.getHealth(), dino.getMaxHealth()) + "/" + dino.getMaxHealth();
                printStringXY(s1, wordLength / 2, 120, 157, 126, 103);
            }
            {
                String s1 = I18n.format("pedia.hunger") + " " + dino.getHunger() + "/" + dino.getMaxHunger();
                printStringXY(s1, wordLength / 2, 130, 157, 126, 103);
            }
            {
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                final int width = scaledResolution.getScaledWidth();
                final int height = scaledResolution.getScaledHeight();
                final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                String s1 = I18n.format("pedia.diet") + " " + I18n.format("pedia.diet." + dino.type.diet.toString().toLowerCase());
                int x = wordLength / 2;
                int y = 140;
                printStringXY(s1, x, y, 157, 126, 103);
                if (mouseX > x && mouseX < x + this.fontRenderer.getStringWidth(s1)) {
                    if (mouseY > y && mouseY < y + 10) {
                        List<String> text = new ArrayList<String>();
                        text.add(I18n.format("pedia.diet." + dino.type.diet.toString().toLowerCase() + ".desc"));
                        GL11.glPushMatrix();
                        this.drawHoveringText(text, mouseX, mouseY, fontRenderer);
                        GL11.glPopMatrix();
                    }
                }
            }
            {
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                final int width = scaledResolution.getScaledWidth();
                final int height = scaledResolution.getScaledHeight();
                final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                String s1 = I18n.format("pedia.temperament") + " " + I18n.format(dino.getTempermentString());
                int x = wordLength / 2;
                int y = 150;
                printStringXY(s1, x, y, 157, 126, 103);
                if (mouseX > x && mouseX < x + this.fontRenderer.getStringWidth(s1)) {
                    if (mouseY > y && mouseY < y + 10) {
                        List<String> text = new ArrayList<String>();
                        text.add(I18n.format(dino.getTempermentString() + ".desc"));
                        GL11.glPushMatrix();
                        this.drawHoveringText(text, mouseX, mouseY, fontRenderer);
                        GL11.glPopMatrix();
                    }
                }
            }
            {
                String s1 = I18n.format("pedia.gender") + " " + (dino.getGender() == 0 ? I18n.format("pedia.gender.female") : I18n.format("pedia.gender.male"));
                printStringXY(s1, wordLength / 2, 160, 157, 126, 103);
            }
            {
                String name = dino.getOwner() == null ? "" : dino.getOwner().getName();
                String s1 = I18n.format("pedia.untame");
                String s2 = I18n.format("pedia.owner") + " " + name;
                printStringXY(!name.equals("") ? s2 : s1, wordLength / 2, 170, 157, 126, 103);
            }
            {
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                final int width = scaledResolution.getScaledWidth();
                final int height = scaledResolution.getScaledHeight();
                final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                String s1 = I18n.format("pedia.order") + " " + I18n.format("pedia.order." + dino.currentOrder.toString().toLowerCase());
                int x = wordLength / 2;
                int y = 180;
                printStringXY(s1, x, y, 157, 126, 103);
                if (mouseX > x && mouseX < x + this.fontRenderer.getStringWidth(s1)) {
                    if (mouseY > y && mouseY < y + 10) {
                        List<String> text = new ArrayList<String>();
                        text.add(I18n.format(I18n.format("pedia.order." + dino.currentOrder.toString().toLowerCase() + ".desc")));
                        GL11.glPushMatrix();
                        this.drawHoveringText(text, mouseX, mouseY, fontRenderer);
                        GL11.glPopMatrix();
                    }
                }
            }
            {
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                final int width = scaledResolution.getScaledWidth();
                final int height = scaledResolution.getScaledHeight();
                final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                String s1 = I18n.format("pedia.order.item") + ": " + new ItemStack(dino.getOrderItem()).getDisplayName();
                int x = wordLength / 2;
                int y = 190;
                printStringXY(s1, x, y, 157, 126, 103);
            }
            {
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                final int width = scaledResolution.getScaledWidth();
                final int height = scaledResolution.getScaledHeight();
                final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                String s1 = I18n.format("pedia.activity") + " " + I18n.format("pedia.activity." + dino.aiActivityType().toString().toLowerCase());
                int x = wordLength / 2;
                int y = 200;
                printStringXY(s1, x, y, 157, 126, 103);
                if (mouseX > x && mouseX < x + this.fontRenderer.getStringWidth(s1)) {
                    if (mouseY > y && mouseY < y + 10) {
                        List<String> text = new ArrayList<String>();
                        text.add(I18n.format(I18n.format("pedia.activity." + dino.aiActivityType().toString().toLowerCase() + ".desc")));
                        GL11.glPushMatrix();
                        this.drawHoveringText(text, mouseX, mouseY, fontRenderer);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
        if (entity instanceof EntityDinosaurEgg) {
            EntityDinosaurEgg egg = (EntityDinosaurEgg) entity;
            GL11.glPushMatrix();
            String s = I18n.format(egg.selfType.toString() + " " + I18n.format("pedia.egg"));
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            printStringXY(s, (-this.fontRenderer.getStringWidth(s) / 2) + 65, 60, 66, 48, 36);
            GL11.glPopMatrix();
            {
                int time = (int) Math.floor(((float) egg.getBirthTick() / (float) egg.totalHatchTime * 100.0F));
                String s1 = I18n.format("pedia.egg.time") + " " + time + "%";
                printStringXY(s1, wordLength / 2, 120, 157, 126, 103);
            }
            {
                String s1;
                if (egg.isInWater()) {
                    s1 = I18n.format("pedia.egg.status" + " " + ChatFormatting.AQUA + I18n.format("pedia.egg.status.wet"));
                } else {
                    if ((egg.getBirthTick() >= 0 && egg.getBirthTick() > EntityDinosaurEgg.lastBirthTick) || egg.getBirthTick() >= 100) {
                        s1 = I18n.format("pedia.egg.status") + " " + ChatFormatting.GOLD + I18n.format("pedia.egg.status.warm");
                    } else {
                        s1 = I18n.format("pedia.egg.status") + " " + ChatFormatting.BLUE + I18n.format("pedia.egg.status.cold");
                    }
                }
                printStringXY(s1, wordLength / 2, 140, 157, 126, 103);
            }
        }
        if (entity instanceof EntityFishBase) {
            String s1 = I18n.format(entity.getName());
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            printStringXY(I18n.format(entity.getName()), (-this.fontRenderer.getStringWidth(s1) / 2) + 65, 60, 66, 48, 36);
        }
        if (entity instanceof EntityQuagga) {
            String s1 = I18n.format(entity.getName());
            GL11.glScalef(1.5F, 1.5F, 1.5F);
            printStringXY(I18n.format(entity.getName()), (-this.fontRenderer.getStringWidth(s1) / 2) + 65, 60, 66, 48, 36);
        }
    }

    private void renderFirstPageRight(EntityLivingBase entity) {
        if (entity instanceof EntityPrehistoric) {
            EntityPrehistoric dino = (EntityPrehistoric) entity;
            {

                float scale = 1.75F;
                GL11.glPushMatrix();
                GL11.glTranslatef(1F, 0, 0);
                GL11.glScalef(scale, scale, scale);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.renderEngine.bindTexture(moods);
                this.drawTexturedModalRect(160, 7, dino.getMoodFace().uv, 10, 16, 15);
                GL11.glPopMatrix();

            }
            {
                float scale = 0.75F;
                GL11.glPushMatrix();
                GL11.glScalef(scale, scale, scale);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.renderEngine.bindTexture(moods);
                this.drawTexturedModalRect(290, 60, 0, 0, 206, 9);
                GL11.glPopMatrix();
            }
            {
                GL11.glPushMatrix();
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.renderEngine.bindTexture(moods);
                this.drawTexturedModalRect(293 - dino.getScaledMood(), 43, 0, 26, 4, 10);
                GL11.glPopMatrix();
            }

            {
                {
                    ScaledResolution scaledResolution = new ScaledResolution(mc);
                    final int width = scaledResolution.getScaledWidth();
                    final int height = scaledResolution.getScaledHeight();
                    final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                    final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                    int x = 218;
                    int y = 40;
                    if (mouseX > x && mouseX < x + 154) {
                        if (mouseY > y && mouseY < y + 13) {
                            List<String> text = new ArrayList<String>();
                            text.add(I18n.format("pedia.moodstatus") + dino.getMoodFace().color + dino.getMood());
                            GL11.glPushMatrix();
                            this.drawHoveringText(text, mouseX, mouseY, fontRenderer);
                            GL11.glPopMatrix();
                        }
                    }
                }
                {
                    ScaledResolution scaledResolution = new ScaledResolution(mc);
                    final int width = scaledResolution.getScaledWidth();
                    final int height = scaledResolution.getScaledHeight();
                    final int mouseX = (Mouse.getX() * width / mc.displayWidth) - guiLeft;
                    final int mouseY = (height - Mouse.getY() * height / mc.displayHeight - 1) - guiTop;
                    int x = 280;
                    int y = 10;
                    if (mouseX > x && mouseX < x + 28) {
                        if (mouseY > y && mouseY < y + 28) {
                            List<String> text = new ArrayList<String>();
                            text.add(dino.getMoodFace().color + I18n.format(I18n.format("pedia.") + dino.getMoodFace().toString().toLowerCase()));
                            text.add(ChatFormatting.GRAY + I18n.format(I18n.format("pedia.") + dino.getMoodFace().toString().toLowerCase() + I18n.format(".desc")));
                            GL11.glPushMatrix();
                            this.drawHoveringText(text, mouseX, mouseY, fontRenderer);
                            GL11.glPopMatrix();
                        }
                    }
                }
            }
            Map<ItemStack, Integer> foodMap = FoodMappings.INSTANCE.getFoodRenderList(dino.type.diet);
            List<ItemStack> keys = Collections.list(Collections.enumeration(foodMap.keySet()));
            Collections.sort(keys, this.sorter);
            ItemStack[] keyArray = keys.toArray(new ItemStack[0]);
            for (ItemStack item : keyArray) {
                if (items < 64) {
                    addMiniItem(item);
                }
            }

        } else {

        }
    }

    public void renderSecondPage(EntityLivingBase entity) {

    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background_image);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        drawModalRectWithCustomSizedTexture(k, l, 0, 0, this.xSize, this.ySize, (390.625F), (390.625F));
        if (bookPages == 0) {
            GL11.glPushMatrix();
            if (Revival.PEDIA_OBJECT instanceof EntityDinosaurEgg) {
                renderEgg(k + 100, l + 80, 150, 0, 0, (EntityDinosaurEgg) Revival.PEDIA_OBJECT);
            } else if (Revival.PEDIA_OBJECT instanceof EntityLivingBase) {
                if (Revival.PEDIA_OBJECT instanceof EntityPrehistoric) {
                    renderDinosaur(k + 100, l + 80, Math.round(2 * ((EntityPrehistoric) Revival.PEDIA_OBJECT).pediaScale), 0, 0, (EntityLivingBase) Revival.PEDIA_OBJECT);
                } else {
                    if (Revival.PEDIA_OBJECT instanceof EntityQuagga) {
                        renderDinosaur(k + 100, l + 80, 50, 0, 0, (EntityLivingBase) Revival.PEDIA_OBJECT);
                    } else {
                        renderDinosaur(k + 100, l + 80, 80, 0, 0, (EntityLivingBase) Revival.PEDIA_OBJECT);
                    }
                }
            }

            GL11.glPopMatrix();
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    public void addTextByPage(int page) {
        int var1 = MathHelper.floor((this.width - this.xGui) / 2.2F);
        int var2 = MathHelper.floor((this.height - this.yGui) / 2.2F);
        int var3 = MathHelper.floor((this.width - this.xGui) / 2.0F + 10);
        int var4 = MathHelper.floor((this.height - this.yGui) / 1.2F);
        GL11.glPushMatrix();
        GL11.glScalef(1.10F, 1.10F, 1.10F);
        GL11.glPopMatrix();

        if (page == 1) {
        }

        var1 = MathHelper.floor((this.width - this.xGui) / 2.4F);
        var2 = MathHelper.floor((this.height - this.yGui) / 2.4F);
        var3 = MathHelper.floor((this.width - this.xGui) / 1.2F);
        var4 = MathHelper.floor((this.height - this.yGui) / 1.2F);
        GL11.glPushMatrix();
        GL11.glScalef(1.20F, 1.20F, 1.20F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(0.6F, 0.6F, 0.6F);
        GL11.glPopMatrix();
    }

    public void addButtonByPage(int page) {
        int centerX = (this.width - this.xSize) / 2;
        int centerY = (this.height - this.ySize) / 2;

        if (page == 0) {
            this.buttonList.add(this.buttonIcon = new ButtonDinopedia(2, centerX + 35, centerY + 55, 0));
            this.buttonList.add(this.buttonIcon = new ButtonDinopedia(3, centerX + 75, centerY + 55, 2));
            this.buttonList.add(this.buttonIcon = new ButtonDinopedia(4, centerX + 115, centerY + 55, 4));
            this.buttonList.add(this.buttonIcon = new ButtonDinopedia(5, centerX + 55, centerY + 95, 6));
            this.buttonList.add(this.buttonIcon = new ButtonDinopedia(6, centerX + 95, centerY + 95, 8));
        }
    }

    public void triggerButtons(GuiButton button) {
        switch (button.id) {
            case 2:
                this.bookPages = 1;
                break;

            case 3:
                this.bookPages = 2;
                break;
        }
    }

    public static void renderDinosaur(int posX, int posY, int scaleValue, float renderYaw, float renderPitch, EntityLivingBase mob) {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glTranslatef((float) posX, (float) posY, 50.0F);
        scaleValue -= scaleValue * 0.25F;
        if (mob instanceof EntityPrehistoric) {
            GL11.glScalef((float) -scaleValue, -(float) scaleValue, (float) scaleValue);
        } else {
            GL11.glScalef((float) scaleValue, (float) scaleValue, (float) scaleValue);
        }
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 0.0F, 0.0F);
        GL11.glRotatef(-((float) Math.atan((double) (renderPitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(0.0F, (float)mob.getYOffset(), 0.0F);
        Minecraft.getMinecraft().getRenderManager().playerViewY = 180.0F;
        if (mob instanceof EntityPrehistoric) {
            EntityPrehistoric prehistoric = (EntityPrehistoric) mob;
            GL11.glScalef(1 / prehistoric.getAgeScale(), -1 / prehistoric.getAgeScale(), -1 / prehistoric.getAgeScale());
        }
        GL11.glRotatef(-45.0F, 0.0F, 1.0F, -0.1F);
        float partialTicks = LLibrary.PROXY.getPartialTicks();
        GL11.glRotatef(ClientUtils.interpolate(mob.prevRenderYawOffset, mob.renderYawOffset, partialTicks), 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().getRenderManager().renderEntity(mob, 0.0D, 0.0D, 0, 0.0F, partialTicks, false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

    }

    public static void renderEgg(int posX, int posY, int scaleValue, float renderYaw, float renderPitch, Entity mob) {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glTranslatef((float) posX, (float) posY, 50.0F);
        GL11.glScalef((float) (scaleValue), (float) scaleValue, (float) scaleValue);
        float f2 = 0;
        float f3 = 0;
        float f4 = 0;
        float f5 = 0;
        float f6 = 0;
        GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 0.0F, 0.0F);
        GL11.glRotatef(-((float) Math.atan((double) (renderPitch / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        mob.rotationYaw = (float) Math.atan((double) (renderYaw / 40.0F)) * 40.0F;
        mob.rotationPitch = -((float) Math.atan((double) (renderPitch / 40.0F))) * 20.0F;
        GL11.glTranslatef(0.0F, (float)mob.getYOffset(), 0.0F);
        GL11.glRotatef(mob.ticksExisted, 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().getRenderManager().playerViewY = 180.0F;
        Minecraft.getMinecraft().getRenderManager().renderEntity(mob, 0.0D, 0.0D, 0.0D, 0.0F, 0F, false);
        mob.rotationYaw = f3;
        mob.rotationPitch = f4;
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0 && bookPages < bookPagesTotal) {
            bookPages += 1;
        } else if (button.id == 1 && bookPages > 0) {
            bookPages -= 1;
        }

        this.triggerButtons(button);
        this.initGui();
        this.updateScreen();
    }

    public class FoodSorter implements Comparator {

        public FoodSorter() {
        }

        public int compareFoods(ItemStack var1, ItemStack var2) {
            double var3 = Item.getIdFromItem(var1.getItem());
            double var5 = Item.getIdFromItem(var2.getItem());
            if(var3 == var5 && var1.getItem() != null && var2.getItem() != null){
                double var6 = var1.getItemDamage();
                double var7 = var2.getItemDamage();
                return var6 < var7 ? -1 : (var6 > var7 ? 1 : 0);
            }
            return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
        }

        @Override
        public int compare(Object var1, Object var2) {
            return this.compareFoods((ItemStack) var1, (ItemStack) var2);
        }
    }
}
