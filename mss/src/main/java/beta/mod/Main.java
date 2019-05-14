package beta.mod;

import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beta.mod.init.BlockInit;
import beta.mod.init.ItemInit;
import beta.mod.objects.entity.EntityIceGhast;
import beta.mod.objects.entity.EntityIceGhastProjectile;
import beta.mod.objects.entity.EntityIceProjectile;
import beta.mod.objects.entity.EntityLavaProjectile;
import beta.mod.objects.entity.EntityPoisonProjectile;
import beta.mod.objects.entity.RenderIceGhast;
import beta.mod.objects.entity.RenderIceGhastProjectile;
import beta.mod.objects.entity.RenderIceProjectile;
import beta.mod.objects.entity.RenderLavaProjectile;
import beta.mod.objects.entity.RenderPoisonProjectile;
import beta.mod.tabs.MoreSimpleStuffBlocks;
import beta.mod.tabs.MoreSimpleStuffItems;
import beta.mod.util.GuiHandler;
import beta.mod.world.BiomeSpawns;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("moresimplestuff")
/*
 * @author Arad
 */
public class Main {
	public static Main instance;
	public static final String modid = "moresimplestuff";
	public static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup mssitems = new MoreSimpleStuffItems();
	public static final ItemGroup mssblocks = new MoreSimpleStuffBlocks();
	
	public Main() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::openGui);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private static <T> void registerExtensionPoint(ExtensionPoint<T> point, Supplier<T> extension) {
		ModLoadingContext.get().registerExtensionPoint(point, extension);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		BiomeSpawns.addSpawns();
		logger.info("setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		registerRender(EntityPoisonProjectile.class, RenderPoisonProjectile::new);
		registerRender(EntityIceProjectile.class, RenderIceProjectile::new);
		registerRender(EntityLavaProjectile.class, RenderLavaProjectile::new);
		registerRender(EntityIceGhast.class, RenderIceGhast::new);
		registerRender(EntityIceGhastProjectile.class, RenderIceGhastProjectile::new);
		logger.info("clientRegistries method registered.");
	}
	
	private static <T extends Entity> void registerRender(Class<T> entityClass, IRenderFactory<? super T> renderClass) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderClass);
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		
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
			BlockInit.addSpecialBlocks();
			event.getRegistry().registerAll(
					BlockInit.BLOCKS.toArray(new Block[BlockInit.BLOCKS.size()])
			);
			logger.info("Blocks Registered");
		}
		
//		@SubscribeEvent
//		public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
//			BiomeInit.registerAllBiomes(event);
//		}
		
		@SuppressWarnings("unused")
		private static <T extends Entity> EntityType<T> registerEntity(String name, Class<T> entityClass, Function<? super World, ? extends T> entityClassC) {
			return EntityType.register(modid + ":" + name, EntityType.Builder.create(entityClass, entityClassC));
		}
	}
}