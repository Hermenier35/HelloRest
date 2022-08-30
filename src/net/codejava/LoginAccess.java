package net.codejava;
import java.util.ArrayList;
import java.util.List;

public class LoginAccess {
	private Requete admin;
	private Requete parent;
	private Requete prof;
	private List<String> access;
	private String password;
	
	public LoginAccess(Requete admin, Requete parent, Requete prof) {
		this.admin = admin;
		this.parent = parent;
		this.prof = prof;
		this.access = new ArrayList<String>();
		this.password = "";
	}
	
	public void access() {
		try {
			if(admin.getResultSet().next())
				this.access.add("admins");
			if(parent.getResultSet().next())
				this.access.add("parent");
			if(prof.getResultSet().next())
				this.access.add("prof");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void recupPassword() {
		try {
			if(admin.getResultSet().next())
				this.password = admin.getResultSet().getString("pass").trim();
			else if(parent.getResultSet().next())
				this.password = parent.getResultSet().getString("pass").trim();
			else if(prof.getResultSet().next())
				this.password = prof.getResultSet().getString("pass").trim();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public List<String> getAccess() {
		return access;
	}

	public String getPassword() {
		return password;
	}
	
}
