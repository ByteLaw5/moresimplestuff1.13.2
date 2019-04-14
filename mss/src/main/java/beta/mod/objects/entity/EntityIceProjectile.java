package beta.mod.objects.entity;

import beta.mod.Main;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EntityIceProjectile extends EntityFireball {
	public EntityIceProjectile(World worldIn) {
		super(Main.RegistryEvents.ICE_PROJ, worldIn, 1.0f, 1.0f);
		this.setSize(1.0f, 1.0f);
	}
	
	public EntityIceProjectile(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(Main.RegistryEvents.ICE_PROJ, x, y, z, accelX, accelY, accelZ, worldIn, 1.0f, 1.0f);
	}
	
	public EntityIceProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
		super(Main.RegistryEvents.ICE_PROJ, shooter, accelX, accelY, accelZ, worldIn, 1.0f, 1.0f);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entity == null) {
			BlockPos pos = result.getBlockPos().up();
			World worldIn = this.world;
			worldIn.setBlockState(pos, Blocks.ICE.getDefaultState());
			worldIn.setBlockState(pos.up(), Blocks.ICE.getDefaultState());
			this.remove();
		} else if(result.entity != null && result.entity instanceof EntityLivingBase) {
			((EntityLivingBase)result.entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 400, 5));
			this.remove();
		}
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
	
	@Override
	protected boolean isFireballFiery() {
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}
	
	@Override
	public float getExplosionResistance(Explosion explosionIn, IBlockReader worldIn, BlockPos pos,
			IBlockState blockStateIn, IFluidState p_180428_5_, float p_180428_6_) {
		return this.isInvulnerable() && blockStateIn.canEntityDestroy(worldIn, pos, this) ? Math.min(0.8f, p_180428_6_) : p_180428_6_;
	}
	
	@Override
	public float getCollisionBorderSize() {
		return 1.0f;
	}
}
