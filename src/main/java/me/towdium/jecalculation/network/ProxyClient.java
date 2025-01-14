package me.towdium.jecalculation.network;

import me.towdium.jecalculation.command.JecCommand;
import me.towdium.jecalculation.data.Controller;
import me.towdium.jecalculation.data.label.ILabel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static org.lwjgl.input.Keyboard.KEY_NONE;

/**
 * Author: towdium
 * Date:   8/10/17.
 */
@SideOnly(Side.CLIENT)
public class ProxyClient extends ProxyCommon {
    public static final KeyBinding keyOpenGuiCraft = new KeyBinding("jecalculation.key.gui_craft", KEY_NONE, "jecalculation.key.category");
    public static final KeyBinding keyOpenGuiMath = new KeyBinding("jecalculation.key.gui_math", KEY_NONE, "jecalculation.key.category");

    @Override
    public void initPost() {
        super.initPost();
        ClientCommandHandler.instance.registerCommand(new JecCommand());
        ILabel.initClient();
        Controller.loadFromLocal();
    }

    @Override
    public void init() {
        super.init();
        ClientRegistry.registerKeyBinding(keyOpenGuiCraft);
        ClientRegistry.registerKeyBinding(keyOpenGuiMath);
    }

    @Override
    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().player;
    }
}
