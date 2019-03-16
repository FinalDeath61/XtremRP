package fr.gitancraft.xtremrp.Pnj.init;



import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.entity.QuestNpc;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

	public static void registerEntities() {
		registerEntity("NPC", QuestNpc.class, XtremModForge.ENTITY_QUESTNPC, 50, 7825019, 000000);
	}
	
	public static void registerEntity(String name , Class<? extends Entity> entity , int id, int range, int color1, int color2) {
		EntityRegistry.registerModEntity(new ResourceLocation(XtremModForge.MOD_ID + " : " + name), entity, name, id, XtremModForge.instance, range, 1, true, color1, color2);
	}
}
