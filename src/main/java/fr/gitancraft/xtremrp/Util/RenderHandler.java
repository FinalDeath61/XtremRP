package fr.gitancraft.xtremrp.Util;

import fr.gitancraft.xtremrp.entity.QuestNpc;
import fr.gitancraft.xtremrp.entity.render.renderQuestNOS;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	public static void registerEntityRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(QuestNpc.class, new IRenderFactory<QuestNpc>() {
			@Override
			public Render<? super QuestNpc> createRenderFor(RenderManager manager) {
				return new renderQuestNOS(manager);
			}
		});
	}
}
