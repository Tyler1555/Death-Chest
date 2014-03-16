package me.tyler15555.deathchest.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameData;
import me.tyler15555.deathchest.util.ConfigHelper;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public class DCEventHandler {

	public DCEventHandler() {
		
	}
	
	@SubscribeEvent
	public void onPlayerDrops(PlayerDropsEvent event) {
		boolean saveItems = false;
		if(!ConfigHelper.requireSavingStone) {
			saveItems = true;
		}
		
		for(int i = 0; i < event.drops.size(); i++) {
			
			ItemStack droppedStack = event.drops.get(i).getEntityItem();
			
			if(droppedStack.getItem() == DeathChest.savingStone) {
				event.drops.remove(i);
				saveItems = true;
			}
		}
		
		if(saveItems) {
			int posX = MathHelper.floor_double(event.entityPlayer.posX);
			int posY = MathHelper.floor_double(event.entityPlayer.posY);
			int posZ = MathHelper.floor_double(event.entityPlayer.posZ);
			
			World world = event.entityPlayer.worldObj;
			
			world.setBlock(posX, posY, posZ, Blocks.chest, 0, 2);
			TileEntityChest chest = (TileEntityChest) world.getTileEntity(posX, posY, posZ);
			for(int j = 0; j < event.drops.size(); j++) {
				if(j > chest.getSizeInventory()) {
					return;
				} else {
					chest.setInventorySlotContents(j, event.drops.get(j).getEntityItem());
					event.drops.get(j).setDead();
				}
			}
		}
	}

}
