package fr.gitancraft.xtremrp.entity.render;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.PlayerCapabilities;

public class Player  extends PlayerCapabilities{
	List<UUID> doneQuest = new ArrayList<>();

	/**
	 * Méthode appelée lors de la validation d'une quête. Permet de récuperer l'UUID de la quête et de l'ajouter aux quêtes validées.
	 */
	public void validationQuete(UUID uuidDoneQuest) {
		doneQuest.add(uuidDoneQuest);
	}
	/**
	 * Supprime TOUTES les quêtes validées par le joueur
	 */
	public void purgeQuest() {
		doneQuest.clear();
	}
	
	public List<UUID> getDoneQuest() {
		return doneQuest;
	}

	
	
}
