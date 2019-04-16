package beta.mod.objects.special;

import beta.mod.Main;
import beta.mod.objects.BlockBaseProperties;
import net.minecraft.block.BlockPane;
import net.minecraft.util.ResourceLocation;

public class BlockBars extends BlockPane {
	public BlockBars(String name, BlockBaseProperties builder) {
		super(builder.getProps());
		
		setRegistryName(new ResourceLocation(Main.modid, name));
	}
}
