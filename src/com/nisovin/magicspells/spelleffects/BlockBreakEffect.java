package com.nisovin.magicspells.spelleffects;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.magicspells.DebugHandler;
import com.nisovin.magicspells.util.ConfigData;

public class BlockBreakEffect extends SpellEffect {

	@ConfigData(field="id", dataType="int", defaultValue="1")
	int id = 1;
	
	@Override
	public void loadFromString(String string) {
		if (string != null && !string.isEmpty()) {
			try {
				id = Integer.parseInt(string);
			} catch (NumberFormatException e) {		
				DebugHandler.debugNumberFormat(e);
			}
		}
	}

	@Override
	public void loadFromConfig(ConfigurationSection config) {
		id = config.getInt("id", id);
	}
	
	@Override
	public Runnable playEffectLocation(Location location) {
		location.getWorld().playEffect(location, Effect.STEP_SOUND, id);
		return null;
	}
}