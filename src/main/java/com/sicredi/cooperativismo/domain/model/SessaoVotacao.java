package com.sicredi.cooperativismo.domain.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SessaoVotacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_votacao_seq")
	@SequenceGenerator(name = "sessao_votacao_seq", sequenceName = "sessao_votacao_seq", allocationSize = 1)
	private Long id;
	
	@OneToOne
	private Pauta pauta;
	
	private LocalDateTime prazo;
	
	//1 sessao tem muitos votos
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sessaoVotacao", cascade = CascadeType.ALL)
	private Set<Voto> votos;

}
