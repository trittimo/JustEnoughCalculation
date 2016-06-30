package pers.towdium.justEnoughCalculation.gui.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import pers.towdium.justEnoughCalculation.JustEnoughCalculation;
import pers.towdium.justEnoughCalculation.gui.JECContainer;
import pers.towdium.justEnoughCalculation.util.PlayerRecordHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Towdium
 * Date:   2016/6/29.
 */
public class GuiRecipeViewer extends GuiRecipeList {

    public GuiRecipeViewer(GuiScreen parent) {
        super(new JECContainer() {
            @Override
            protected void addSlots() {
                addSlotGroup(8, 8, 18, 20, 6, 4);
            }

            @Override
            public EnumSlotType getSlotType(int index) {
                return EnumSlotType.DISABLED;
            }
        }, parent, 6, 7);
    }

    @Nullable
    @Override
    protected String getButtonTooltip(int buttonId) {
        return null;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(JustEnoughCalculation.Reference.MODID,"textures/gui/guiRecipeViewer.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    List<Integer> getRecipeIndex() {
        List<Integer> buffer = new ArrayList<>(PlayerRecordHelper.getSize());
        for(int i = PlayerRecordHelper.getSize()-1; i >= 0; i--){
            buffer.add(i);
        }
        return buffer;
    }
}