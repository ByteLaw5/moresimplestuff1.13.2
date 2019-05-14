package beta.mod.tileentity.barrel;

import beta.mod.util.GuiHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiBarrel extends GuiContainer {
	private final InventoryPlayer plrInv;
	private final TileEntityBarrel te;
	
	public GuiBarrel(InventoryPlayer plrInv, TileEntityBarrel barrel, EntityPlayer player) {
		super(new ContainerBarrel(plrInv, barrel, player));
		this.plrInv = plrInv;
		this.te = barrel;
		
		this.xSize = 178;
		this.ySize = 129;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color3f(1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(GuiHandler.GUI.BARREL.getTexture());
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedComponentText(), 8, 6, 0);
		this.fontRenderer.drawString(this.plrInv.getDisplayName().getUnformattedComponentText(), 8, this.ySize - 92, 0);
	}
}
