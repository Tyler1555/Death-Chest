package me.tyler15555.deathchest.util;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.Level;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHelper {

	public ConfigHelper() {
		
	}
	
	public static boolean requireSavingStone;
	
	private static Property requireSavingStoneProp;
	
	public static void setupConfig(Configuration cfg, Logger logger) {
		try {
			requireSavingStoneProp = cfg.get("General", "requireSavingStone", false);
			requireSavingStoneProp.comment = "Whether or not the Saving Stone is required to get a Death Chest";
			requireSavingStone = requireSavingStoneProp.getBoolean(false);
		} catch(Exception e) {
			logger.log(Level.ERROR, "An error occured loading Death Chests config!");
			e.printStackTrace();
		} finally {
			if(cfg.hasChanged()) {
				cfg.save();
			}
		}
	}

}
