package beta.mod.tabs;

import beta.mod.init.BlockInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoreSimpleStuffBlocks extends ItemGroup {
	public MoreSimpleStuffBlocks() {
		super("mssblocks");
	}
	
	@Override @OnlyIn(Dist.CLIENT)
	public ItemStack createIcon() {
		return new ItemStack(BlockInit.mixed_bricks);
	}
}
