package br.com.carometro.coordenador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/coordenador")
public class CoordenadorController {

    @Autowired
    private CoordenadorRepository repository;

    // Exibe formulário (criação ou edição)
    @GetMapping("/formulario")
    public String exibirFormulario(Long id, Model model) {
        if (id != null) {
            model.addAttribute("coordenador", repository.findById(id).orElse(new Coordenador()));
        } else {
            model.addAttribute("coordenador", new Coordenador());
        }
        return "coordenador/formulario";
    }

    // Lista todos os coordenadores
    @GetMapping
    public String listarCoordenadores(Model model) {
        model.addAttribute("coordenadores", repository.findAll());
        return "coordenador/listagem";
    }

    // Salva um novo coordenador
    @PostMapping
    @Transactional
    public String salvarCoordenador(@Valid DadosCadastroCoordenador dados) {
        
        Coordenador coordenador = new Coordenador(dados);
        repository.save(coordenador);
        return "redirect:/coordenador";
    }

    // Atualiza um coordenador existente
    @PutMapping
    @Transactional
    public String atualizarCoordenador(
            @RequestParam Long id,
            @RequestParam String nome,
            @RequestParam String login,
            @RequestParam String senha) {
        
        Coordenador coordenador = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado"));
        
        coordenador.setNome(nome);
        coordenador.setLogin(login);
        coordenador.setSenha(senha);
        return "redirect:/coordenador";
    }

    // Remove um coordenador
    @DeleteMapping
    @Transactional
    public String removerCoordenador(@RequestParam Long id) {
        repository.deleteById(id);
        return "redirect:/coordenador";
    }
}