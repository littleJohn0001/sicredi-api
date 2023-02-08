package com.sicredi.cooperativismo.domain.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicredi.cooperativismo.api.model.AssociadoResponseDTO;
import com.sicredi.cooperativismo.api.model.PautaResponseDTO;
import com.sicredi.cooperativismo.api.model.SessaoVotacaoResponseDTO;
import com.sicredi.cooperativismo.api.model.VotoRequestDTO;
import com.sicredi.cooperativismo.api.model.VotoResponseDTO;
import com.sicredi.cooperativismo.domain.exception.SessaoVotacaoPrazoExpiradoException;
import com.sicredi.cooperativismo.domain.model.Voto;
import com.sicredi.cooperativismo.infrastructure.repository.VotoRepository;

@Service
public class VotoService {
	
	@Autowired
	private VotoRepository repository;
	
	@Autowired
	private AssociadoService associadoService;
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@Transactional
	public VotoResponseDTO votar(Long pautaId, VotoRequestDTO request) {
		var associado = associadoService.get(request.getAssociadoId());
		var pauta = pautaService.get(pautaId);
		var sessaoVotacao = sessaoVotacaoService.getByPauta(pauta);
		
		if (LocalDateTime.now().isAfter(sessaoVotacao.getPrazo())) {
			throw new SessaoVotacaoPrazoExpiradoException(pauta);
		}

		var voto = repository.save(Voto.builder().associado(associado).sessaoVotacao(sessaoVotacao).decisaoVoto(request.getDecisaoVoto()).build());
		
		var associadoResponse = AssociadoResponseDTO.builder().id(associado.getId()).cpf(associado.getCpf()).build();
		
		var pautaResponse = PautaResponseDTO.builder().id(sessaoVotacao.getPauta().getId()).build();

		var sessaoVotacaoResponse = SessaoVotacaoResponseDTO.builder().id(sessaoVotacao.getId()).pauta(pautaResponse).build();
		
		return VotoResponseDTO.
				builder().
				id(voto.getId()).
				decisaoVoto(voto.getDecisaoVoto()).
				associado(associadoResponse).
				sessaoVotacao(sessaoVotacaoResponse).
				build();
	}
      
//       voto.setSessaoVotacao(sessaoVotacao);
//       voto.setDataHora(LocalDateTime.now());
//
//       if(votoRepository.existsBySessaoVotacaoAndCpfEleitor(sessaoVotacao, voto.getCpfEleitor())) {
//           throw new RegraDeNegocioException(TipoMensagemRegraDeNegocioException.VOTO_JA_REGISTRADO, HttpStatus.BAD_REQUEST);
//       }
//
//       votoRepository.save(voto);

}
