package br.com.carometro.aluno;

import java.time.LocalDate;
import java.util.List;

import br.com.carometro.curso.Curso;

public record DadosListagemAluno(
    Long id,
    String nome,
    LocalDate dataNasc,
    String foto,
    String historico,
    boolean aprovado,
    List<String> redesSociais,
    Curso curso) {

    public DadosListagemAluno(Aluno aluno) {
        this(
            aluno.getId(),
            aluno.getNome(),
            aluno.getDataNasc(),
            aluno.getFoto(),
            aluno.getHistorico(),
            aluno.isAprovado(),
            aluno.getRedesSociais(),
            aluno.getCurso()
        );
    }
    
}