package br.com.homecare.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.homecare.models.entities.Usuario;
import br.com.homecare.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginResource {

	@Autowired
	private LoginService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public Usuario login() {
		Usuario user = new Usuario("teste", "oi");

		return user;
	}


	@RequestMapping(value="/buscar/(id)", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Usuario> usuario = this.service.buscar(id);
		
		return ResponseEntity.ok().body(usuario);
	}
}