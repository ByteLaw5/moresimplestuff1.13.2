package beta.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beta.mod.init.BlockInit;
import beta.mod.init.ItemInit;
import beta.mod.tabs.MoreSimpleStuffBlocks;
import beta.mod.tabs.MoreSimpleStuffItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
		logger.info("clientRegistries method registered.");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			ItemInit.ITEMS.add(ItemInit.STAFF);
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
	}
}
