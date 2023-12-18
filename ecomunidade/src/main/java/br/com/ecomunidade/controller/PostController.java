package br.com.ecomunidade.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.ecomunidade.model.Post;
import br.com.ecomunidade.repository.PostRepository;
import br.com.ecomunidade.repository.TipoRepository;
import jakarta.validation.Valid;

@RequestMapping("/postagens")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private TipoRepository tipoRepository;

	@GetMapping
	public ResponseEntity<List<Post>> getAll() {
		return ResponseEntity.ok(postRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getById(@PathVariable Long id) {
		return postRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/conteudo/{conteudo}")
	public ResponseEntity<List<Post>> getByConteudo(@PathVariable String conteudo) {
		return ResponseEntity.ok(postRepository.findAllbyConteudoContainingIgnoreCase(conteudo));
	}

	@PostMapping
	public ResponseEntity<Post> post(@Valid @RequestBody Post post) {
		if (tipoRepository.existsById(post.getTipo().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(post));

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo não existe!", null);
	}

	@PutMapping
	public ResponseEntity<Post> put(@Valid @RequestBody Post post) {
		if (postRepository.existsById(post.getId())) {

			if (tipoRepository.existsById(post.getTipo().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(post));

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo não existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Post> post = postRepository.findById(id);

		if (post.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		postRepository.deleteById(id);
	}
}
