package beta.mod.init;

import beta.mod.Main;
import beta.mod.objects.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Function;

//It's an Event Bus Subscriber because they won't register
@ObjectHolder(Main.modid) @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD,modid=Main.modid)
public class ModET {
    public static final EntityType<EntityIceGhast> ICE_GHAST = register("ice_ghast", EntityIceGhast.class, EntityIceGhast::new);
    public static final EntityType<EntityIceProjectile> ICE_PROJ = register("ice_proj", EntityIceProjectile.class, EntityIceProjectile::new);
    public static final EntityType<EntityLavaProjectile> LAVA_PROJ = register("lava_proj", EntityLavaProjectile.class, EntityLavaProjectile::new);
    public static final EntityType<EntityPoisonProjectile> POISON_PROJ = register("poison_proj", EntityPoisonProjectile.class, EntityPoisonProjectile::new);
    public static final EntityType<EntityIceGhastProjectile> ICE_GHAST_PROJ = register("ice_ghast_proj", EntityIceGhastProjectile.class, EntityIceGhastProjectile::new);

    private static <T extends Entity> EntityType<T> register(String name, Class<T> entityClass, Function<? super World, ? extends T> factory) {
        return EntityType.register(Main.modid + ":" + name, EntityType.Builder.create(entityClass, factory));
    }
}
