package me.tyler15555.deathchest.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSavingStone extends Item {

	public ItemSavingStone() {
		setUnlocalizedName("savingStone");
		setTextureName("deathchest:saving_stone");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		list.add("When you die, this dumps everything");
		list.add("it can into a chest");
	}

}
