package fr.gitancraft.xtremrp.entity.render;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.player.PlayerCapabilities;

public class Player  extends PlayerCapabilities{
	List<UUID> doneQuest = new ArrayList<>();

	/**
	 * M�thode appel�e lors de la validation d'une qu�te. Permet de r�cuperer l'UUID de la qu�te et de l'ajouter aux qu�tes valid�es.
	 */
	public void validationQuete(UUID uuidDoneQuest) {
		doneQuest.add(uuidDoneQuest);
	}
	/**
	 * Supprime TOUTES les qu�tes valid�es par le joueur
	 */
	public void purgeQuest() {
		doneQuest.clear();
	}
	
	public List<UUID> getDoneQuest() {
		return doneQuest;
	}

	
	
}
