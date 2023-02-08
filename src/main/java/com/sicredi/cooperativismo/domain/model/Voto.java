package com.sicredi.cooperativismo.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_seq")
	@SequenceGenerator(name = "voto_seq", sequenceName = "voto_seq", allocationSize = 1)
	private Long id;
	
	@OneToOne
	private Associado associado;
	
	@Enumerated(EnumType.STRING)
	private DecisaoVoto decisaoVoto;
	
	//Muitos votos tem apenas 1 sessao
	@ManyToOne
    private SessaoVotacao sessaoVotacao;

}
