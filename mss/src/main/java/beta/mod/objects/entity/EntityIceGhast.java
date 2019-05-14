package beta.mod.objects.entity;

import java.util.Random;

import beta.mod.init.LootTableInit;
import beta.mod.init.ModET;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityIceGhast extends EntityGhast {
	public EntityIceGhast(World worldIn) {
		super(worldIn);
	}
	
	@Override
	public EntityType<?> getType() {
		return ModET.ICE_GHAST;
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(5, new EntityIceGhast.AIRandomFly(this));
		this.tasks.addTask(7, new EntityIceGhast.AILookAround(this));
		this.tasks.addTask(7, new EntityIceGhast.AIIceballAttack(this));
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LootTableInit.ENTITIES_ICE_GHAST;
	}
	
	@Override
	public boolean canSpawn(IWorld worldIn, boolean p_205020_2_) {
		return true;
	}
	
	static class AIIceballAttack extends EntityAIBase {
		private final EntityIceGhast parentEntity;
	      public int attackTimer;

	      public AIIceballAttack(EntityIceGhast ghast) {
	         this.parentEntity = ghast;
	      }

	      public boolean shouldExecute() {
	         return this.parentEntity.getAttackTarget() != null;
	      }

	      public void startExecuting() {
	         this.attackTimer = 0;
	      }

	      public void resetTask() {
	         this.parentEntity.setAttacking(false);
	      }
	      
	      public void tick() {
	         EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
	         if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(entitylivingbase)) {
	            World world = this.parentEntity.world;
	            ++this.attackTimer;
	            if (this.attackTimer == 10) {
	               world.playEvent(null, 1015, new BlockPos(this.parentEntity), 0);
	            }

	            if (this.attackTimer == 20) {
	               Vec3d vec3d = this.parentEntity.getLook(1.0F);
	               double d2 = entitylivingbase.posX - (this.parentEntity.posX + vec3d.x * 4.0D);
	               double d3 = entitylivingbase.getBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F));
	               double d4 = entitylivingbase.posZ - (this.parentEntity.posZ + vec3d.z * 4.0D);
	               world.playEvent(null, 1016, new BlockPos(this.parentEntity), 0);
	               EntityIceGhastProjectile entitylargefireball = new EntityIceGhastProjectile(world, this.parentEntity, d2, d3, d4);
	               entitylargefireball.posX = this.parentEntity.posX + vec3d.x * 4.0D;
	               entitylargefireball.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5D;
	               entitylargefireball.posZ = this.parentEntity.posZ + vec3d.z * 4.0D;
	               world.spawnEntity(entitylargefireball);
	               this.attackTimer = -40;
	            }
	         } else if (this.attackTimer > 0) {
	            --this.attackTimer;
	         }

	         this.parentEntity.setAttacking(this.attackTimer > 10);
	      }
	}
	
   static class AILookAround extends EntityAIBase {
	      private final EntityIceGhast parentEntity;

	      public AILookAround(EntityIceGhast ghast) {
	         this.parentEntity = ghast;
	         this.setMutexBits(2);
	      }

	      /**
	       * Returns whether the EntityAIBase should begin execution.
	       */
	      public boolean shouldExecute() {
	         return true;
	      }

	      /**
	       * Keep ticking a continuous task that has already been started
	       */
	      public void tick() {
	         if (this.parentEntity.getAttackTarget() == null) {
	            this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float)Math.PI);
	            this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
	         } else {
	            EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
	            if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D) {
	               double d1 = entitylivingbase.posX - this.parentEntity.posX;
	               double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
	               this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
	               this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
	            }
	         }

	      }
	   }

	   static class AIRandomFly extends EntityAIBase {
	      private final EntityIceGhast parentEntity;

	      public AIRandomFly(EntityIceGhast ghast) {
	         this.parentEntity = ghast;
	         this.setMutexBits(1);
	      }

	      /**
	       * Returns whether the EntityAIBase should begin execution.
	       */
	      public boolean shouldExecute() {
	         EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();
	         if (!entitymovehelper.isUpdating()) {
	            return true;
	         } else {
	            double d0 = entitymovehelper.getX() - this.parentEntity.posX;
	            double d1 = entitymovehelper.getY() - this.parentEntity.posY;
	            double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
	            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
	            return d3 < 1.0D || d3 > 3600.0D;
	         }
	      }

	      /**
	       * Returns whether an in-progress EntityAIBase should continue executing
	       */
	      public boolean shouldContinueExecuting() {
	         return false;
	      }

	      /**
	       * Execute a one shot task or start executing a continuous task
	       */
	      public void startExecuting() {
	         Random random = this.parentEntity.getRNG();
	         double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
	         double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
	         double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
	         this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
	      }
	   }

	   static class GhastMoveHelper extends EntityMoveHelper {
	      private final EntityGhast parentEntity;
	      private int courseChangeCooldown;

	      public GhastMoveHelper(EntityGhast ghast) {
	         super(ghast);
	         this.parentEntity = ghast;
	      }

	      public void tick() {
	         if (this.action == EntityMoveHelper.Action.MOVE_TO) {
	            double d0 = this.posX - this.parentEntity.posX;
	            double d1 = this.posY - this.parentEntity.posY;
	            double d2 = this.posZ - this.parentEntity.posZ;
	            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
	            if (this.courseChangeCooldown-- <= 0) {
	               this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
	               d3 = (double)MathHelper.sqrt(d3);
	               if (this.isNotColliding(this.posX, this.posY, this.posZ, d3)) {
	                  this.parentEntity.motionX += d0 / d3 * 0.1D;
	                  this.parentEntity.motionY += d1 / d3 * 0.1D;
	                  this.parentEntity.motionZ += d2 / d3 * 0.1D;
	               } else {
	                  this.action = EntityMoveHelper.Action.WAIT;
	               }
	            }

	         }
	      }

	      /**
	       * Checks if entity bounding box is not colliding with terrain
	       */
	      private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
	         double d0 = (x - this.parentEntity.posX) / p_179926_7_;
	         double d1 = (y - this.parentEntity.posY) / p_179926_7_;
	         double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
	         AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

	         for(int i = 1; (double)i < p_179926_7_; ++i) {
	            axisalignedbb = axisalignedbb.offset(d0, d1, d2);
	            if (!this.parentEntity.world.isCollisionBoxesEmpty(this.parentEntity, axisalignedbb)) {
	               return false;
	            }
	         }

	         return true;
	      }
	   }
}
