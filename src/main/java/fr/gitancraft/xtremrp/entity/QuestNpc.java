package fr.gitancraft.xtremrp.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.gitancraft.xtremrp.Pnj.gui.GuiMarchandPnj;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class QuestNpc extends EntityVillager {

	private UUID tokenID = UUID.randomUUID();

	private List<UUID> doneQuest = new ArrayList();

	public boolean isDone(UUID token) {
		return doneQuest.contains(token);
	}

	public QuestNpc(World worldIn) {
		super(worldIn);
	}

	@Override
	public EntityVillager createChild(EntityAgeable ageable) {
		return new EntityVillager(world);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		// TODO Auto-generated method stub
		return super.getAmbientSound();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		// TODO Auto-generated method stub
		return super.getHurtSound(damageSourceIn);
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO Auto-generated method stub
		return super.getDeathSound();
	}

	public UUID getTokenID() {
		return tokenID;
	}

	public void setTokenID(UUID tokenID) {
		this.tokenID = tokenID;
	}
	
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		
		Minecraft.getMinecraft().displayGuiScreen(new GuiMarchandPnj());
		return true;
	}
	
	
}
