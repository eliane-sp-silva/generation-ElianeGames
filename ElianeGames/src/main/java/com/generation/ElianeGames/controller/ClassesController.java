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

import com.generation.ElianeGames.model.Classes;
import com.generation.ElianeGames.repository.ClassesRepository;

@RestController
@RequestMapping("/classes")
@CrossOrigin(origins = "*",allowedHeaders="*")
public class ClassesController {

	@Autowired
	private ClassesRepository classesRepository;
	
	@GetMapping
	public ResponseEntity<List<Classes>> getAll()
	{
		return ResponseEntity.ok(classesRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Classes> getById(@PathVariable Long id)
	{
		return classesRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Classes>> getByDescricao (@PathVariable String descricao)
	{
		return ResponseEntity.ok(classesRepository
				.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Classes> post(@Valid @RequestBody Classes classes)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(classesRepository.save(classes));
	}
	
	@PutMapping
	public ResponseEntity<Classes>put (@Valid @RequestBody Classes classes){
	
		return classesRepository.findById(classes.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(classesRepository.save(classes)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id)
	{
		Optional<Classes> classes = classesRepository.findById(id);
		
		if(classes.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		classesRepository.deleteById(id);
	}
	
	
	
	
}
