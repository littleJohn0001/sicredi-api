package com.sicredi.cooperativismo.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Associado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "associado_seq")
	@SequenceGenerator(name = "associado_seq", sequenceName = "associado_seq", allocationSize = 1)
	private Long id;
	
	@Column(unique = true)
	private String cpf;

}
