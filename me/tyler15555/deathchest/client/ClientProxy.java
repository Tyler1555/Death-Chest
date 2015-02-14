package me.tyler15555.deathchest.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import me.tyler15555.deathchest.common.CommonProxy;
import me.tyler15555.deathchest.common.DeathChest;
import me.tyler15555.deathchest.item.ItemSavingStone;

public class ClientProxy extends CommonProxy {

	public ClientProxy() {
		
	}
	
	@Override
	public void registerRenderers() {
		RenderItem render = Minecraft.getMinecraft().getRenderItem();
		
		render.getItemModelMesher().register(DeathChest.savingStone, 0, new ModelResourceLocation("deathchest:savingStone", "inventory"));
	}

}
