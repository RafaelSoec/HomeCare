package br.com.homecare.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.homecare.models.entities.Atendimento;
import br.com.homecare.services.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoResource {

	@Autowired
	private AtendimentoService service;

	@GetMapping("/todos")
	public ResponseEntity<List<Atendimento>> getAll() {
		List<Atendimento> todos = service.getAll();

		return ResponseEntity.ok().body(todos);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		Optional<Atendimento> objeto = service.find(id);

		return ResponseEntity.ok().body(objeto);
	}


	@PostMapping("/salvar")	
	public ResponseEntity<Atendimento> salvar(@RequestBody Atendimento atendimento) {
		Atendimento objeto = service.save(atendimento);

		return ResponseEntity.ok().body(objeto);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Atendimento> update(@RequestBody Atendimento atendimento) {
		Atendimento objeto = service.update(atendimento);

		return ResponseEntity.ok().body(objeto);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<?> Delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		
		return ResponseEntity.ok().body(null);
	}
}
