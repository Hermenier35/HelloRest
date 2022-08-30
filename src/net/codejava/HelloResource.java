package net.codejava;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.Modèle.Note;

import javax.ws.rs.PathParam;

@Path("/ataqwa")
public class HelloResource {
	
	@GET
	@Path("/getNote/{ideleve}/{idcours}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("ideleve") int ideleve, @PathParam("idcours") int idcours) {
		Requete req = new Requete("select * from note where ideleve ="+ideleve+" and idcours="+idcours);
		//Note note = new Note(req.getResultSet());
		ResultImplementation<Note> r = new ResultImplementation<>();
		r.setResultSet(req.getResultSet());
		List<Note> notes = r.recupResult("Note");
		req.close();
		return Response.ok(notes, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/getLoginAccess/{mail}/{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("mail") String mail, @PathParam("pass") String pass) {
		Requete reqAdmin = new Requete("select * from admins where email = '"+mail+"' and pass= '"+pass+"'");
		Requete reqParent = new Requete("select * from parent where email = '"+mail+"' and pass= '"+pass+"'");
		Requete reqProf = new Requete("select * from prof where email = '"+mail+"' and pass='"+pass+"'");
		LoginAccess access = new LoginAccess(reqAdmin, reqParent, reqProf);
		access.access();
		reqAdmin.close();
		reqParent.close();
		reqProf.close();
		return Response.ok(access.getAccess(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/getLogin/{mail}/{pass}/{access}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("mail") String mail, @PathParam("pass") String pass, @PathParam("access") String access) {
		Requete reqType = new Requete("select * from "+ access +" where email ='"+mail+"' and pass='"+pass+"'");	
		String loginKey = "error";
		try {
			if(reqType.getResultSet().next())
				loginKey = Cryptage.loginKeys(access);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reqType.close();
		return Response.ok(loginKey, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/getPassword/{mail}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("mail") String mail) {
		Requete reqAdmin = new Requete("select pass from admins where email = '"+mail+"'");
		Requete reqParent = new Requete("select pass from parent where email = '"+mail+"'");
		Requete reqProf = new Requete("select pass from prof where email = '"+mail+"'");
		LoginAccess access = new LoginAccess(reqAdmin, reqParent, reqProf);
		access.recupPassword();
		reqAdmin.close();
		reqParent.close();
		reqProf.close();
		if(access.getPassword().equals("")) {
			return Response.ok("wrong email", MediaType.APPLICATION_JSON).build();
		}else {
			MailBuilder email = new MailBuilder(mail, "Ataqwa, Your password", "Votre mot de passe est : "+ access.getPassword());
			email.sendMail();
			return Response.ok("email sent", MediaType.APPLICATION_JSON).build();
		}
	}
	
}
