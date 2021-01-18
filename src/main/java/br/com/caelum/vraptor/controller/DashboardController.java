package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.model.Usuario;

@Path("dashboard")
@Controller
public class DashboardController {
	
	//injetar usuario pelo vraptor
	@Inject EntityManager em; 
	
	//injetar usuario para o dashboard 
	@Inject Result result;
	
	@Get("")
	public void dashboard() {
		
	}
	
	@Post("salvausuario")
	public void salvaUsuario(Usuario usuario) {
		
		//salvar usuario no banco
		em.persist(usuario);
		
		//apos salvar o usuario direcionar para o dashboard
		result.redirectTo(DashboardController.class).dashboard();
				
	}

}
