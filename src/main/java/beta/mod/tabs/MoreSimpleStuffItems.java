package beta.mod.tabs;

import beta.mod.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoreSimpleStuffItems extends ItemGroup {

	public MoreSimpleStuffItems() {
		super("mssitems");
	}

	@Override @OnlyIn(Dist.CLIENT)
	public ItemStack createIcon() {
		return new ItemStack(ItemInit.CORN);
	}
}
