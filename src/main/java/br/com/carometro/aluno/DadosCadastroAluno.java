package br.com.carometro.aluno;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAluno(
		
		@NotBlank
		String nome,		
		@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataNasc,
		String foto,
		String historico,
		boolean aprovado,
		List<String> redesSociais,
		Long cursoId) {

}
