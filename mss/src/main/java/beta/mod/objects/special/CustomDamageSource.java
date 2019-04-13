package beta.mod.objects.special;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class CustomDamageSource extends DamageSource {
	public CustomDamageSource(String damageType) {
		super(damageType);
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation("death.attack.quick_sand", entityLivingBaseIn.getDisplayName());
	}
}