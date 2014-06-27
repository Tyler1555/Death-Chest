package me.tyler15555.deathchest.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameData;
import me.tyler15555.deathchest.util.ConfigHelper;
import net.minecraft.entity.item.EntityItem;
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
		int counter = 0;
		if(!ConfigHelper.requireSavingStone) {
			saveItems = true;
		}
		
		for(EntityItem droppedStack : event.drops) {
			if(droppedStack.getEntityItem().getItem() == DeathChest.savingStone) {
				event.drops.remove(droppedStack);
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
			for(EntityItem droppedItemEntity : event.drops) {
				counter++;
				ItemStack droppedItem = droppedItemEntity.getEntityItem();
				
				if(counter > chest.getSizeInventory()) {
					return;
				} else {
					chest.setInventorySlotContents(counter - 1, droppedItem);
					droppedItemEntity.setDead();
				}
			}
		}
	}

}
