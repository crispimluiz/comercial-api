package com.crispimluiz.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crispimluiz.comercial.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{
	
//esse filtro para para o OportunidadeController para ser executado
	Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
}
