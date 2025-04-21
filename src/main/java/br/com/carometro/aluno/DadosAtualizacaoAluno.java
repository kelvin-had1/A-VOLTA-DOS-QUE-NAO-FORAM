package br.com.carometro.aluno;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAluno(
	@NotNull
	Long id,
	String nome,
	@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataNasc,
	String foto,
	String historico,
	boolean aprovado,
	List<String> redesSociais) {
}
