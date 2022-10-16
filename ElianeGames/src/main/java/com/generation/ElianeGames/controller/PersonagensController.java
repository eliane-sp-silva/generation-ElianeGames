package com.generation.ElianeGames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.generation.ElianeGames.model.Personagens;
import com.generation.ElianeGames.repository.PersonagensRepository;

@RestController
@RequestMapping("/personagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonagensController {
	
	@Autowired
	private PersonagensRepository personagensRepository;
	
	@GetMapping //http://localhost:8080/personagens
	public ResponseEntity<List<Personagens>> getAll()
	{
		return ResponseEntity.ok(personagensRepository.findAll());
	}
	
	@GetMapping("/{id}") //http://localhost:8080/personagens/id
	public ResponseEntity<Personagens> getById(@PathVariable Long id)
	{
		return personagensRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/nome/{nome}") //http://localhost:8080/personagens/nome/{nome}
	public ResponseEntity<List<Personagens>> getByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(personagensRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping   //http://localhost:8080/personagens
	public ResponseEntity<Personagens> post(@Valid @RequestBody Personagens personagens) //No MySQL >> INSERT INTO TB_PERSONAGENS(NOME,FORCA,ATAQUE,DEFESA,INTELIGENCIA)VALUES("SAMYMASTER",10,700,2000,9)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(personagensRepository.save(personagens));
	}
	
	@PutMapping  //http://localhost:8080/personagens
	public ResponseEntity<Personagens> put(@Valid @RequestBody Personagens personagens) 
	//o MySQL, seria o equivalente a instrução: UPDATE tb_postagens SET titulo = "titulo", texto = "texto",	data = CURRENT_TIMESTAMP() WHERE id = id; 
	{ //pesquisar por Id que estará no corpo da requisição
		return personagensRepository.findById(personagens.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(personagensRepository.save(personagens)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());			
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) 
	// o MySQL, seria o equivalente a instrução: DELETE FROM tb_postagens WHERE id = id;
	{
		Optional<Personagens> personagens = personagensRepository.findById(id);
		
		if(personagens.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		personagensRepository.deleteById(id);
	}

}
