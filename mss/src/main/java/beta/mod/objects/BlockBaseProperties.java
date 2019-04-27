package beta.mod.objects;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;

public class BlockBaseProperties {
	private Block.Properties props;
	private BlockBaseType type;
	
	public BlockBaseProperties(Material mat, BlockBaseType type) {
		props = Block.Properties.create(mat);
		this.type = type;
	}
	
	public BlockBaseProperties(Material mat) {
		this(mat, BlockBaseType.block);
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
	
	public BlockBaseType getType() {
		return type;
	}
}
