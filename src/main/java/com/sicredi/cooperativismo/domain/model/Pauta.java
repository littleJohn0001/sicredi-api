package com.sicredi.cooperativismo.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_seq")
	@SequenceGenerator(name = "pauta_seq", sequenceName = "pauta_seq", allocationSize = 1)
	private Long id;

}
