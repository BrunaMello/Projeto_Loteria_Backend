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
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.model.Usuario;
import br.com.caelum.vraptor.validator.SimpleMessage;
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
	
	@IncludeParameters //inclui todos os parametros que escreveu no metodo
	@Post("salvausuario")
	public void salvaUsuario(@Valid Usuario usuario, String confirmaSenha) { //@valid faz a validacao 
		
		
		//nao perder os dados da tela
		//result.include("usuario", usuario);
		//result.include(confirmaSenha, confirmaSenha);
		
		//validando a confirmacao de senha 
		boolean asSenhasSaoIguais = confirmaSenha.equals(usuario.getSenha());
		
		validator.addIf(!asSenhasSaoIguais, new SimpleMessage("confirmaSenha", "A confirmação esta diferente da senha"));
		
		//em caso de erro redirecionar
		validator.onErrorRedirectTo(this).cadastrar();
		
		//mensagem de erro 
		
		
		//salvar usuario no banco
		usuarioDao.insertOrUpdate(usuario);
		
				
		//apos salvar o usuario direcionar para o dashboard
		result.redirectTo(DashboardController.class).dashboard();
		
		
				
	}


}
