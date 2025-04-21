package br.com.carometro.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.carometro.curso.Curso;
import br.com.carometro.curso.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno")
public class AlunoController {
    
    @Autowired
    private AlunoRepository repository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @GetMapping("/formulario")                  
    public String carregaPaginaFormulario(Long id, Model model) { 
        model.addAttribute("cursos", cursoRepository.findAll());
        if(id != null) {
            var aluno = repository.getReferenceById(id);
            model.addAttribute("aluno", aluno);
        }
        return "aluno/formulario";              
    }     
    
    @GetMapping                                           
    public String carregaPaginaListagem(Model model) {
    	var alunos = repository.findAll(Sort.by("nome").ascending());
        model.addAttribute("lista", alunos);
        return "aluno/listagem";                         
    } 

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroAluno dados, BindingResult result) {
        
    	if (result.hasErrors()) {
	        return "aluno/formulario";
    	}
    	var aluno = new Aluno(dados);
    	var curso = cursoRepository.findById(dados.cursoId()).get();
    	aluno.setCurso(curso);
	    repository.save(aluno);
	    return "redirect:/aluno";  
    }
    
    @PutMapping
    @Transactional
    public String atualizar(DadosAtualizacaoAluno dados,
                          @RequestParam(name = "redesSociais", required = false) List<String> redesSociais,
                          @RequestParam(name = "cursoId", required = false) Long cursoId) {
        
        var aluno = repository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);
        
        // Atualiza redes sociais
        aluno.setRedesSociais(redesSociais);
        
        // Atualiza curso
        if(cursoId != null) {
            Curso curso = cursoRepository.findById(cursoId).get();
            aluno.setCurso(curso);
        } else {
            aluno.setCurso(null);
        }
        
        return "redirect:/aluno";  
    }
    
    @DeleteMapping
    @Transactional
    public String removeAluno(Long id) {
        repository.deleteById(id);
        return "redirect:/aluno";  
    }
    
    // Endpoint para o coordenador aprovar/reprovar alunos
    @PostMapping("/aprovar")
    @Transactional
    public String aprovarAluno(Long id, boolean aprovado) {
        var aluno = repository.getReferenceById(id);
        aluno.setAprovado(aprovado);
        return "redirect:/aluno";
    }
}