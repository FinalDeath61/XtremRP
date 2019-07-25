package fr.gitancraft.xtremrp.refs;

public enum ButtonNPCDialogID {
	ADMIN_PANEL(0), NEXT_PAGE(1), PREVIOUS_PAGE(2);

	private int idButton;

	ButtonNPCDialogID(int idButton) {
		this.setIdButton(idButton);
	}

	public int getIdButton() {
		return idButton;
	}

	public void setIdButton(int idButton) {
		this.idButton = idButton;
	}
}
