package beta.mod.objects.entity;

import beta.mod.Main;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.fluid.IFluidState;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EntityPoisonProjectile extends EntityFireball {
	private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.createKey(EntityPoisonProjectile.class, DataSerializers.BOOLEAN);
	
	public EntityPoisonProjectile(World worldIn) {
		super(Main.RegistryEvents.POISON_PROJ, worldIn, 1.1f, 1.1f);
		this.setSize(1.1f, 1.1f);
	}
	
	@OnlyIn(Dist.CLIENT)
	public EntityPoisonProjectile(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(Main.RegistryEvents.POISON_PROJ, x, y, z, accelX, accelY, accelZ, worldIn, 1.1f, 1.1f);
	}
	
	public EntityPoisonProjectile(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
		super(Main.RegistryEvents.POISON_PROJ, shooter, accelX, accelY, accelZ, worldIn, 1.1f, 1.1f);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entity != null && result.entity instanceof EntityLivingBase) {
			((EntityLivingBase)result.entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 300));
			this.remove();
		}
	}
	
	@Override
	public boolean isBurning() {
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	protected boolean isFireballFiery() {
		return false;
	}
	
	@Override
	public boolean isInvulnerable() {
		return this.dataManager.get(INVULNERABLE);
	}
	
	@Override
	public void setInvulnerable(boolean isInvulnerable) {
		this.dataManager.set(INVULNERABLE, isInvulnerable);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}
	
	@Override
	protected void registerData() {
		this.dataManager.register(INVULNERABLE, false);
	}
	
	@Override
	public float getExplosionResistance(Explosion explosionIn, IBlockReader worldIn, BlockPos pos,
			IBlockState blockStateIn, IFluidState p_180428_5_, float p_180428_6_) {
		return this.isInvulnerable() && blockStateIn.canEntityDestroy(worldIn, pos, this) ? Math.min(0.8f, p_180428_6_) : p_180428_6_;
	}
	
	@Override
	protected float getMotionFactor() {
		return this.isInvulnerable() ? 0.73f : super.getMotionFactor();
	}
}
