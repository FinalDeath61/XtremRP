package fr.gitancraft.xtremrp.entity.render;

import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.entity.QuestNpc;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class renderQuestNOS extends RenderLiving<QuestNpc> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(XtremModForge.MOD_ID + " cheminTexture");

	public renderQuestNOS(RenderManager manager) {
		super(manager, new ModelVillager(0), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(QuestNpc entity) {
		return TEXTURE;
	}

	@Override
	protected void applyRotations(QuestNpc entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
