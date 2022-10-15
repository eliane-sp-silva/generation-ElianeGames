package com.generation.ElianeGames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
