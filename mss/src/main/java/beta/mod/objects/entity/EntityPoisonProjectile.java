package beta.mod.objects.entity;

import beta.mod.init.ModET;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EntityPoisonProjectile extends EntityFireball {
	public EntityPoisonProjectile(World worldIn) {
		super(ModET.POISON_PROJ, worldIn, 1.0f, 1.0f);
		this.setSize(1.0f, 1.0f);
	}
	
	public EntityPoisonProjectile(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(ModET.POISON_PROJ, x, y, z, accelX, accelY, accelZ, worldIn, 1.0f, 1.0f);
	}
	
	public EntityPoisonProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
		super(ModET.POISON_PROJ, shooter, accelX, accelY, accelZ, worldIn, 1.0f, 1.0f);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entity instanceof EntityLivingBase) {
			if(result.entity instanceof EntityZombie || result.entity instanceof EntitySkeleton) {
				((EntityLivingBase)result.entity).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 1));
				this.remove();
			} else {
				((EntityLivingBase)result.entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 300, 1));
				this.remove();
			}
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
