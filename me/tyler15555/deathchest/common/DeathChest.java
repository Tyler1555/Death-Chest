package me.tyler15555.deathchest.common;

import me.tyler15555.deathchest.item.ItemSavingStone;
import me.tyler15555.deathchest.util.ConfigHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.Level;



@Mod(modid = "DeathChest", name = "Death Chest", version = MinecraftForge.MC_VERSION)
public class DeathChest {
	
	@Instance("DeathChest")
	public static DeathChest instance;
	@SidedProxy(clientSide = "me.tyler15555.deathchest.client.ClientProxy", serverSide = "me.tyler15555.deathchest.common.CommonProxy")
	public static CommonProxy proxy;

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
		
		proxy.registerRenderers();
		
		GameRegistry.addRecipe(new ItemStack(this.savingStone), " s ", "sgs", " s ", 's', Blocks.stone, 'g', Items.gold_ingot);
	}
	
	@EventHandler
	public void finishLoading(FMLPostInitializationEvent event) {
		
	}

}
