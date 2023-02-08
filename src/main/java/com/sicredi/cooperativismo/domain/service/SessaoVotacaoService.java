package com.sicredi.cooperativismo.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.cooperativismo.api.model.PautaResponseDTO;
import com.sicredi.cooperativismo.api.model.SessaoVotacaoRequestDTO;
import com.sicredi.cooperativismo.api.model.SessaoVotacaoResponseDTO;
import com.sicredi.cooperativismo.domain.exception.SessaoVotacaoPrazoPrecedeDataAtualException;
import com.sicredi.cooperativismo.domain.exception.SessaoVotacaoNaoEncontradaException;
import com.sicredi.cooperativismo.domain.model.Pauta;
import com.sicredi.cooperativismo.domain.model.SessaoVotacao;
import com.sicredi.cooperativismo.infrastructure.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {
	
	@Autowired
	private SessaoVotacaoRepository repository;
	
	@Autowired
	private PautaService pautaService;
	
	//variavel na nuvem?
	private static final Integer PRAZO = 60;
	
	//alterar a parte de criacao do response para o controller? 
	public SessaoVotacaoResponseDTO abrir(Long pautaId, SessaoVotacaoRequestDTO request)
			throws SessaoVotacaoPrazoPrecedeDataAtualException {
		LocalDateTime prazo = request.getPrazo();

		if (prazo != null) {
			validarPrazo(request.getPrazo());
		} else {
			prazo = LocalDateTime.now().plusSeconds(PRAZO);
		}

		var pauta = pautaService.get(pautaId);

		var sessaoVotacao = repository.save(SessaoVotacao.builder().pauta(pauta).prazo(prazo).build());

		var pautaResponse = PautaResponseDTO.builder().id(pauta.getId()).build();

		return SessaoVotacaoResponseDTO.builder().id(sessaoVotacao.getId()).prazo(sessaoVotacao.getPrazo())
				.pauta(pautaResponse).build();
	}
	
	public SessaoVotacao getByPauta(Pauta pauta) {
		return repository.findByPauta(pauta).orElseThrow(() -> new SessaoVotacaoNaoEncontradaException(pauta));
	}

	public SessaoVotacao get(Long sessaoVotacaoId) {
		return repository.findById(sessaoVotacaoId)
				.orElseThrow(() -> new SessaoVotacaoNaoEncontradaException(sessaoVotacaoId));
	}
	
	//TODO data com server
	private void validarPrazo(LocalDateTime prazo) throws SessaoVotacaoPrazoPrecedeDataAtualException {
		if (prazo.isBefore(LocalDateTime.now())) {
			throw new SessaoVotacaoPrazoPrecedeDataAtualException(prazo);
		}
	}

	

}
