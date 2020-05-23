package br.com.homecare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.homecare.core.exceptions.custom.RequestErrorException;
import br.com.homecare.models.entities.Atendimento;
import br.com.homecare.models.entities.Pessoa;
import br.com.homecare.models.entities.PessoaAtendimento;
import br.com.homecare.repositories.AtendimentoRepository;
import br.com.homecare.repositories.PessoaAtendimentoRepository;
import br.com.homecare.repositories.PessoaRepository;
import br.com.homecare.utils.messages.ExceptionMessages;

@Service
@Transactional(rollbackFor = RequestErrorException.class)
public class PessoaService {

	@Autowired
	private PessoaRepository repo;

	@Autowired
	private AtendimentoRepository atendimentoRepo;
	
	@Autowired
	private PessoaAtendimentoRepository pessoaAtendRepo;

	public Optional<Pessoa> find(Long id) {
		return this.repo.findById(id);
	}

	public List<Pessoa> saveAll(List<Pessoa> entity) {
		for (Pessoa pessoa : entity) {
			this.save(pessoa);
		}

		return entity;
	}

	public List<Pessoa> getAll() {
		return this.repo.findAll();
	}

	public Pessoa save(Pessoa entity) {
		try {
			entity = this.repo.save(entity);
//			List<Atendimento> atendimentos = entity.getAtendimentos();
//			atendimentos = this.atendimentoRepo.saveAll(atendimentos);
			for(Atendimento atend: entity.getAtendimentos()) {
				PessoaAtendimento pessoaAtend = new PessoaAtendimento();
				pessoaAtend.setAtendimentoId(atend.getId());
				pessoaAtend.setPessoaId(entity.getId());
				this.pessoaAtendRepo.save(pessoaAtend);
			}
			
		} catch (Exception e) {
			throw new RequestErrorException(e.getMessage());
		}
		
		return entity;
	}

	public Pessoa update(Pessoa entity) {
		if (entity.getId() == null) {
			throw new RequestErrorException(ExceptionMessages.campoNulo("id"));
		}

		Optional<Pessoa> objeto = this.find(entity.getId());
		if (objeto.isPresent()) {
			return this.repo.save(entity);
		} else {
			throw new RequestErrorException(ExceptionMessages.objetoNaoEncontrado("Pessoa"));
		}
	}

	public void delete(final Long id) {
		if (id == null) {
			throw new RequestErrorException(ExceptionMessages.campoNulo("id"));
		}

		Optional<Pessoa> objeto = this.repo.findById(id);
		if (objeto.isPresent()) {
			this.repo.delete(objeto.get());
		} else {
			throw new RequestErrorException(ExceptionMessages.objetoNaoEncontrado("Pessoa"));
		}
	}
}
