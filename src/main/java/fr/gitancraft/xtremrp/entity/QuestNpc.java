package fr.gitancraft.xtremrp.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.gitancraft.xtremrp.XtremModForge;
import fr.gitancraft.xtremrp.Pnj.gui.GuiMarchandPnj;
import fr.gitancraft.xtremrp.gui.GuiPNJMaster;
import fr.gitancraft.xtremrp.gui.pnj.GuiBasicDialog;
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
	protected boolean isMovementBlocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	protected boolean canDespawn() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean attackable() {
		// TODO Auto-generated method stub
		return false;
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
		player.openGui(XtremModForge.instance, 2, world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		return true;
	}
	
	
}
