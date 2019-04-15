package beta.mod.objects.entity;

import beta.mod.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.model.ModelGhast;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderIceGhast extends RenderLiving<EntityIceGhast> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Main.modid, "textures/entity/ice_ghast.png");
	private static final ResourceLocation SHOOTING_TEXTURES = new ResourceLocation(Main.modid, "textures/entity/ice_ghast_shoot.png");
	
	public RenderIceGhast(RenderManager manager) {
		super(manager, new ModelGhast(), 0.5f);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityIceGhast entity) {
		return entity.isAttacking() ? SHOOTING_TEXTURES : TEXTURES;
	}
	
	@Override
	protected void preRenderCallback(EntityIceGhast entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scalef(4.5f, 4.5f, 4.5f);
		GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
	}
}
