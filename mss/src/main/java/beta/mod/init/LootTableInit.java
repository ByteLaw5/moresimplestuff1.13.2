package beta.mod.init;

import beta.mod.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableInit {
	public static final ResourceLocation ENTITIES_ICE_GHAST = register("entities/ice_ghast");
	
	private static ResourceLocation register(String id) {
		return LootTableList.register(new ResourceLocation(Main.modid, id));
	}
}
