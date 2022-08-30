package net.Modèle;

public class Note {
	
	private int idEleve;
	private int idCours;
	private int note;
	private String commentaire;
	
	public Note(int idEleve, int idCours, int note, String commentaire) {
		this.idEleve = idEleve;
		this.idCours = idCours;
		this.note = note;
		this.commentaire = commentaire;
	}
	

	public int getIdEleve() {
		return idEleve;
	}

	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}

	public int getNote() {
		return note;
	}

	public void setNotes(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
