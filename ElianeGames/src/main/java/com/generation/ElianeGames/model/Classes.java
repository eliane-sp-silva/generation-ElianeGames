package com.generation.ElianeGames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_classes")
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo Tipo é obrigatório")
	private String tipo;
	
	@NotBlank(message = "O atributo Descrição é obrigatório")
	private String descricao;
	
	//Relacionamento entre tabelas
	
	@OneToMany(mappedBy = "classes", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("classes")
	private List<Personagens> personagens;
	
	//getters e setters
	
	public Long getId() {
		return id;
	}

	public List<Personagens> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagens> personagens) {
		this.personagens = personagens;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	
	
	
}
