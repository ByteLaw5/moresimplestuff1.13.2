package beta.mod;

import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beta.mod.init.BlockInit;
import beta.mod.init.ItemInit;
import beta.mod.objects.entity.EntityIceProjectile;
import beta.mod.objects.entity.EntityLavaProjectile;
import beta.mod.objects.entity.EntityPoisonProjectile;
import beta.mod.objects.entity.RenderIceProjectile;
import beta.mod.objects.entity.RenderLavaProjectile;
import beta.mod.objects.entity.RenderPoisonProjectile;
import beta.mod.tabs.MoreSimpleStuffBlocks;
import beta.mod.tabs.MoreSimpleStuffItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

@Mod("moresimplestuff")
/**
 * @author Arad
 */
public class Main {
	public static Main instance;
	public static final String modid = "moresimplestuff";
	private static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup mssitems = new MoreSimpleStuffItems();
	public static final ItemGroup mssblocks = new MoreSimpleStuffBlocks();
	
	public Main() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		logger.info("setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		registerRender(EntityPoisonProjectile.class, RenderPoisonProjectile::new);
		registerRender(EntityIceProjectile.class, RenderIceProjectile::new);
		registerRender(EntityLavaProjectile.class, RenderLavaProjectile::new);
		logger.info("clientRegistries method registered.");
	}
	
	private static <T extends Entity> void registerRender(Class<T> entityClass, IRenderFactory<? super T> renderClass) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderClass);
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@ObjectHolder(modid + ":poison_proj")
		public static final EntityType<EntityPoisonProjectile> POISON_PROJ = registerEntity("poison_proj", EntityPoisonProjectile.class, EntityPoisonProjectile::new);
		@ObjectHolder(modid + ":ice_proj")
		public static final EntityType<EntityIceProjectile> ICE_PROJ = registerEntity("ice_proj", EntityIceProjectile.class, EntityIceProjectile::new);
		@ObjectHolder(modid + ":lava_proj")
		public static final EntityType<EntityLavaProjectile> LAVA_PROJ = registerEntity("lava_proj", EntityLavaProjectile.class, EntityLavaProjectile::new);
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			ItemInit.addSpecialItems();
			event.getRegistry().registerAll(
					ItemInit.ITEMS.toArray(new Item[ItemInit.ITEMS.size()])
			);
			logger.info("Items Registered");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			BlockInit.BLOCKS.add(BlockInit.QUICK_SAND);
			event.getRegistry().registerAll(
					BlockInit.BLOCKS.toArray(new Block[BlockInit.BLOCKS.size()])
			);
			logger.info("Blocks Registered");
		}
		
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
			event.getRegistry().registerAll(
					POISON_PROJ,
					ICE_PROJ,
					LAVA_PROJ
			);
		}
		
		private static <T extends Entity> EntityType<T> registerEntity(String name, Class<T> entityClass, Function<? super World, ? extends T> entityClassC) {
			return EntityType.register(modid + ":" + name, EntityType.Builder.create(entityClass, entityClassC));
		}
	}
}