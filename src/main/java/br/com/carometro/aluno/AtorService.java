package br.com.carometro.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AtorService {
	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> getAllAtor() {
		return repository.findAll(Sort.by("nome").ascending());
	}

	public Aluno getAtorById (Long id) {
		return repository.getReferenceById(id);
	}
}
