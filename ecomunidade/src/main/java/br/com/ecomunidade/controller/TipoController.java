package br.com.ecomunidade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecomunidade.model.Tipo;
import br.com.ecomunidade.repository.TipoRepository;

@RestController
@RequestMapping("/tipos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoController {
	
	@Autowired
	private TipoRepository tipoRepository;
	
	@GetMapping
	public ResponseEntity<List<Tipo>> getAll() {
		return ResponseEntity.ok(tipoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tipo> getById(@PathVariable Long id) {
		return tipoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}
