package com.nisovin.magicspells.spells.targeted;

import com.nisovin.magicspells.spells.TargetedEntitySpell;
import com.nisovin.magicspells.spells.TargetedSpell;
import com.nisovin.magicspells.util.MagicConfig;
import com.nisovin.magicspells.util.TargetBooleanState;
import com.nisovin.magicspells.util.TargetInfo;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CustomNameVisibilitySpell extends TargetedSpell implements TargetedEntitySpell {
	
	private TargetBooleanState targetBooleanState;
	
	public CustomNameVisibilitySpell(MagicConfig config, String spellName) {
		super(config, spellName);
		
		this.targetBooleanState = TargetBooleanState.getFromName(getConfigString("target-state", "toggle"));
	}
	
	@Override
	public PostCastAction castSpell(Player player, SpellCastState state, float power, String[] args) {
		if (state == SpellCastState.NORMAL) {
			TargetInfo<LivingEntity> targetInfo = getTargetedEntity(player, power);
			if (targetInfo == null) return noTarget(player);
			LivingEntity target = targetInfo.getTarget();
			if (target == null) return noTarget(player);
			target.setCustomNameVisible(targetBooleanState.getBooleanState(target.isCustomNameVisible()));
		}
		return PostCastAction.HANDLE_NORMALLY;
	}
	
	@Override
	public boolean castAtEntity(Player caster, LivingEntity target, float power) {
		target.setCustomNameVisible(targetBooleanState.getBooleanState(target.isCustomNameVisible()));
		return true;
	}
	
	@Override
	public boolean castAtEntity(LivingEntity target, float power) {
		target.setCustomNameVisible(targetBooleanState.getBooleanState(target.isCustomNameVisible()));
		return true;
	}
	
}
