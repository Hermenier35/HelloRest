package net.codejava;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.Modèle.Note;

public class ResultImplementation<T> implements ResultInterface<T> {
	private ResultSet result;
	@SuppressWarnings("unchecked")
	@Override
	public List<T> recupResult(String modele) {
		if(modele.equals("Note"))
			return (List<T>) recupNote(result);
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResultSet(ResultSet resultSet) {
		// TODO Auto-generated method stub
		this.result = resultSet;
	}
	
	private List<Note> recupNote(ResultSet resultSet){
		int idEleve = 0;
		int idCours = 0;
	    int note = 0;
		String commentaire = "";
		List<Note> mesNotes= new ArrayList<Note>();
		
		 try {
				while (resultSet.next()) {
					 idEleve = resultSet.getInt("ideleve");
					 idCours = resultSet.getInt("idcours");
				     note = resultSet.getInt("notes");
				     commentaire = resultSet.getString("commentaire").trim();
				     mesNotes.add(new Note(idEleve, idCours, note, commentaire));
				     System.out.println(mesNotes.toString());
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return mesNotes;
	}

}
