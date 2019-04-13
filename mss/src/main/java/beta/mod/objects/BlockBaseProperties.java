package beta.mod.objects;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;

public class BlockBaseProperties {
	private Block.Properties props;
	
	public BlockBaseProperties(Material mat) {
		props = Block.Properties.create(mat);
	}
	
	public BlockBaseProperties slipperiness(float slip) {
		props.slipperiness(slip);
		return this;
	}
	
	public BlockBaseProperties light(int light) {
		props.lightValue(light);
		return this;
	}
	
	public BlockBaseProperties hardness(float hardness) {
		props.hardnessAndResistance(hardness);
		return this;
	}
	
	public BlockBaseProperties hardnessAndResistance(float hardness, float resistance) {
		props.hardnessAndResistance(hardness, resistance);
		return this;
	}
	
	public BlockBaseProperties sound(SoundType type) {
		props.sound(type);
		return this;
	}
	
	public Block.Properties getProps() {
		return props;
	}
}
