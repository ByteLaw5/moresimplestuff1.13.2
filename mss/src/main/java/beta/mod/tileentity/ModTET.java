package beta.mod.tileentity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import beta.mod.Main;
import beta.mod.tileentity.barrel.TileEntityBarrel;
import beta.mod.tileentity.press.TileEntityPress;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

/**
 * TET stands for Tile Entity Type
 */
@ObjectHolder(Main.modid)
public class ModTET {
	public static final TileEntityType<TileEntityBarrel> BARREL = RegistryHandler.build("barrel", TileEntityType.Builder.create(TileEntityBarrel::new));
	public static final TileEntityType<TileEntityPress> PRESS = RegistryHandler.build("press", TileEntityType.Builder.create(TileEntityPress::new));

	@Mod.EventBusSubscriber(modid=Main.modid,bus= Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryHandler {
		private static final Logger LOGGER = LogManager.getLogger();

		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
			TileEntityType<?>[] tets = new TileEntityType[] {
					BARREL,
					PRESS
			};

			event.getRegistry().registerAll(tets);
		}

		private static <T extends TileEntity> TileEntityType<T> build(final String name, TileEntityType.Builder<T> builder) {
			Type<?> type = null;
			try {
				type = DataFixesManager.getDataFixer().getSchema(DataFixUtils.makeKey(1631)).getChoiceType(TypeReferences.BLOCK_ENTITY, name);
			} catch(IllegalArgumentException e) {
				if(SharedConstants.developmentMode) {
					throw e;
				}

				LOGGER.warn("No data fixer registered for block entity {}", name);
			}

			TileEntityType<T> tet = builder.build(type);
			tet.setRegistryName(new ResourceLocation(Main.modid, name));
			LOGGER.info("Registered Tile Entity Type with name: " + name);
			return tet;
		}
	}
}
