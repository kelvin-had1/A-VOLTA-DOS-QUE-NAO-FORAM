package br.com.carometro.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    // Exibe formulário (criação ou edição)
    @GetMapping("/formulario")
    public String exibirFormulario(Long id, Model model) {
        if (id != null) {
            model.addAttribute("curso", repository.findById(id).orElse(new Curso()));
        } else {
            model.addAttribute("curso", new Curso());
        }
        return "curso/formulario";
    }

    // Lista todos os cursos
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", repository.findAll());
        return "curso/listagem";
    }

    @PostMapping
    public String salvarCurso(@RequestParam String nome) {
        Curso curso = new Curso();
        curso.setNome(nome);
        repository.save(curso);
        return "redirect:/curso";
    }
    
    @PutMapping
    @Transactional
    public String atualizarCurso(
        @RequestParam Long id,
        @RequestParam String nome) {
        
        Curso curso = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
            
        curso.setNome(nome);
        return "redirect:/curso";
    }


    // Remove curso
    @DeleteMapping
    public String removerCurso(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/curso";
    }
}