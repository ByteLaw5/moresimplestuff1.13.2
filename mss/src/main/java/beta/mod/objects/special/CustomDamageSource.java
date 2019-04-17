package beta.mod.objects.special;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class CustomDamageSource extends DamageSource {
	private String death_name;
	
	public CustomDamageSource(String damageType) {
		super(damageType);
	}
	
	public CustomDamageSource(String damageType, String death_name) {
		super(damageType);
		this.death_name = death_name;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation(death_name != null ? death_name : "no_name", entityLivingBaseIn.getDisplayName());
	}
}