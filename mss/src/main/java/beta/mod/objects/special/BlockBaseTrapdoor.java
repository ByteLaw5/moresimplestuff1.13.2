package beta.mod.objects.special;

import beta.mod.objects.BlockBaseProperties;

import net.minecraft.block.BlockTrapDoor;

public class BlockBaseTrapdoor extends BlockTrapDoor {
	public BlockBaseTrapdoor(BlockBaseProperties builder) {
		super(builder.getProps());
	}
}
