package beta.mod.objects.entity;

import beta.mod.init.ModET;
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

public class EntityLavaProjectile extends EntityFireball {
	public EntityLavaProjectile(World worldIn) {
		super(ModET.LAVA_PROJ, worldIn, 1.0f, 1.0f);
		this.setSize(1.0f, 1.0f);
	}
	
	public EntityLavaProjectile(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModET.LAVA_PROJ, x, y, z, accelX, accelY, accelZ, worldIn, 1.0f, 1.0f);
	}
	
	public EntityLavaProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
		super(ModET.LAVA_PROJ, shooter, accelX, accelY, accelZ, worldIn, 1.0f, 1.0f);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entity == null) {
			BlockPos pos = result.getBlockPos();
			World worldIn = this.world;
			worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.east(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.west(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.north(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.south(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.south().west(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.south().east(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.north().west(), Blocks.LAVA.getDefaultState());
			worldIn.setBlockState(pos.north().east(), Blocks.LAVA.getDefaultState());
			this.remove();
		} else if(result.entity instanceof EntityLivingBase) {
			((EntityLivingBase)result.entity).setFire(8);
			((EntityLivingBase)result.entity).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 360));
			this.remove();
		}
	}
	
	@Override
	public boolean isBurning() {
		return true;
	}
	
	@Override
	protected boolean isFireballFiery() {
		return true;
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
