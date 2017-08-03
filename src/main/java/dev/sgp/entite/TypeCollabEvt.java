package dev.sgp.entite;

public enum TypeCollabEvt {
	
	CREATION_COLLAB("Création d'un nouveau collaborateur"), MODIFICATION_COLLAB("Modification d'un collaborateur");
	
	private String msg;
	private TypeCollabEvt(String msg){
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}

}
