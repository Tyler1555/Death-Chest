package me.tyler15555.deathchest.common;

import me.tyler15555.deathchest.item.ItemSavingStone;
import me.tyler15555.deathchest.util.ConfigHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "DeathChest", name = "Death Chest", version = MinecraftForge.MC_VERSION)
public class DeathChest {

	public static ItemSavingStone savingStone = new ItemSavingStone();
	
	public DeathChest() {
		
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		event.getModLog().log(Level.INFO, "Starting to load Death Chest!");
		
		GameRegistry.registerItem(savingStone, "savingStone");
		
		ConfigHelper.setupConfig(new Configuration(event.getSuggestedConfigurationFile()), event.getModLog());
	}
	
	@EventHandler
	public void loadMod(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new DCEventHandler());
		
		GameRegistry.addRecipe(new ItemStack(this.savingStone), " s ", "sgs", " s ", 's', Blocks.stone, 'g', Items.gold_ingot);
	}
	
	@EventHandler
	public void finishLoading(FMLPostInitializationEvent event) {
		
	}

}
