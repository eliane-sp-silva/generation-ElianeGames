package com.generation.ElianeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.ElianeGames.model.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

	public List<Classes> findAllByDescricaoContainingIgnoreCase (String descricao);
	//Query Method
	
	//No MySQL instrução equivale a SELECT * FROM tb_temas WHERE descricao LIKE "%descricao%";
}
