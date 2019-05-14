package beta.mod.util;

import beta.mod.Main;
import beta.mod.tileentity.barrel.GuiBarrel;
import beta.mod.tileentity.barrel.TileEntityBarrel;
import beta.mod.tileentity.press.GuiPress;
import beta.mod.tileentity.press.TileEntityPress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GuiHandler {
	public static GuiScreen openGui(FMLPlayMessages.OpenContainer openContainer) {
		BlockPos pos = openContainer.getAdditionalData().readBlockPos();
		EntityPlayerSP player = Minecraft.getInstance().player;
		
		//Barrel
		if(openContainer.getId().equals(new ResourceLocation(Main.modid, "barrel"))) {
			return new GuiBarrel(player.inventory, (TileEntityBarrel)Minecraft.getInstance().world.getTileEntity(pos), player);
		}
		//Press
		if(openContainer.getId().equals(new ResourceLocation(Main.modid, "press"))) {
			return new GuiPress(player.inventory, (TileEntityPress)Minecraft.getInstance().world.getTileEntity(pos));
		}
		
		return null;
	}
	
	public static enum GUI {
		BARREL("moresimplestuff:barrel", new ResourceLocation(Main.modid, "textures/gui/barrel.png")),
		PRESS("moresimplestuff:press", new ResourceLocation(Main.modid, "textures/gui/press.png"));
    	
    	private ResourceLocation texture;
    	private String guiId;
    	
    	GUI(String guiId, ResourceLocation texture) {
    		this.guiId = guiId;
    		this.texture = texture;
    	}
    	
    	public String getGuiID() {
    		return guiId;
    	}
    	
    	public ResourceLocation getTexture() {
    		return texture;
    	}
	}
}
