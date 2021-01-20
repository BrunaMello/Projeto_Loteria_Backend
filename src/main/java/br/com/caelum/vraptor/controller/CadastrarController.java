package br.com.caelum.vraptor.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.UsuarioDAO;
import br.com.caelum.vraptor.model.Usuario;
import br.com.caelum.vraptor.validator.Validator;

@Path("cadastrar")
@Controller
public class CadastrarController {
	
		//injetar usuario pelo vraptor
		@Inject EntityManager em; 
		
		//injetar usuario para o dashboard 
		@Inject Result result;
		
		//injetar usuario DAO
		@Inject UsuarioDAO usuarioDao;
		
		//validador para verificar se o usuario esta valido
		@Inject Validator validator;
	
	@Get("")
	public void cadastrar() {
		
	}
	
	@Post("salvausuario")
	public void salvaUsuario(@Valid Usuario usuario) { //@valid faz a validacao 
		
		//em caso de erro redirecionar
		validator.onErrorRedirectTo(this).cadastrar();
		
		//mensagem de erro 
		
		
		//salvar usuario no banco
		usuarioDao.insertOrUpdate(usuario);
		
				
		//apos salvar o usuario direcionar para o dashboard
		result.redirectTo(DashboardController.class).dashboard();
		
		
				
	}


}
