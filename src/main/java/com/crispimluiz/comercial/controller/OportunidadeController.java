package com.crispimluiz.comercial.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.crispimluiz.comercial.model.Oportunidade;
import com.crispimluiz.comercial.repository.OportunidadeRepository;

@CrossOrigin
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

	@Autowired
	private OportunidadeRepository oportunidades;

	// Busca a lista inteira
	@GetMapping
	public List<Oportunidade> listar() {

		return oportunidades.findAll();

		// Utilizado para teste, antes do repository
		/*
		 * Oportunidade oportunidade = new Oportunidade(); oportunidade.setId(1L);
		 * oportunidade.setDescricao("Teste");
		 * oportunidade.setNomeProspecto("Grupo teste"); oportunidade.setValor(new
		 * BigDecimal(45000)); return Arrays.asList(oportunidade );
		 */
	}

	// Busca por Id
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidades.findById(id);

		if (oportunidade.isPresent()) {
			return ResponseEntity.ok(oportunidade.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		
//Busca do OportunidadeRepository um filtro que impede descrição e nomeProspecto sejão os mesmo em outro projeto
		Optional<Oportunidade> oportunidadeExistente = oportunidades
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
		
		if(oportunidadeExistente.isPresent()) {
			throw new 	ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"Já exite uma oportunidade com o Nome e Descrição");
		}
		
		return oportunidades.save(oportunidade);
	}

}
