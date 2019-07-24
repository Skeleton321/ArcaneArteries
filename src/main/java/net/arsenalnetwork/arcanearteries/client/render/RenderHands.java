/*
 * This document is Copyright ©(2018) of MaxIsHot (hereafter referred to as "The Owner") and is the intellectual property of The Owner.
 * It may be not be reproduced under any circumstances except for personal, private use as long as it remains in its unaltered, unedited form.
 * Use of this mod on any other website or as a part of any public display is strictly prohibited, and a violation of copyright.
 */

package net.arsenalnetwork.arcanearteries.client.render;

import net.arsenalnetwork.arcanearteries.common.init.ModItems;
import net.arsenalnetwork.arcanearteries.utilities.ModReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = ModReference.MOD_ID, value = Side.CLIENT)
public class RenderHands
{
    /**
     * This is to render the hands for items.
     */
    @SubscribeEvent
    public static void renderHandEvent(RenderSpecificHandEvent e) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        ItemStack handItem = player.getHeldItem(e.getHand());

        if (handItem.getItem() == ModItems.BOTANIASACRIFICE || handItem.getItem() == ModItems.THAUMIC_KNIFE)
        {
            if (!player.isSwingInProgress)
            {
                GlStateManager.disableCull();
                GlStateManager.pushMatrix();
                GlStateManager.rotate(90F, 1.0F, 0F, 0.0F);
                GlStateManager.rotate(40F, 0F, 0F, 1.0F);
                // X = Move to right or left | Y = Move In Or Out | Z = Move Up Or Down
                GlStateManager.translate(0.65F, -0.4F, 0.6F);
                float scale = 1.2F;
                GlStateManager.scale(scale, scale, scale);
                renderArm(EnumHandSide.LEFT);
                GlStateManager.popMatrix();
                GlStateManager.enableCull();
            }
        }
    }

    public static void renderArm(EnumHandSide enumHandSide)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().player.getLocationSkin());
        Render<AbstractClientPlayer> render = Minecraft.getMinecraft().getRenderManager().<AbstractClientPlayer>getEntityRenderObject(Minecraft.getMinecraft().player);

        RenderPlayer renderplayer = (RenderPlayer)render;

        GlStateManager.pushMatrix();
        GlStateManager.rotate(160.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(5.0F, 0.0F, 1.0F, 0.0F);

        if (enumHandSide == EnumHandSide.RIGHT)
        {
            renderplayer.renderRightArm(Minecraft.getMinecraft().player);
        }
        else
        {
            renderplayer.renderLeftArm(Minecraft.getMinecraft().player);
        }

        GlStateManager.popMatrix();
    }
}
