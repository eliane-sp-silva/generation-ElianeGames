package com.generation.ElianeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.ElianeGames.model.Personagens;

@Repository
public interface PersonagensRepository extends JpaRepository<Personagens, Long>{
	
	public List <Personagens> findAllByNomeContainingIgnoreCase(@Param("nome")
	String titulo);

	//No MySQL -> SELECT * FROM tb_postagens where titulo like "%nome%";
}
