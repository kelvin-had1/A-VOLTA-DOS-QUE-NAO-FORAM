package br.com.carometro.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
		
		@NotBlank
		String nome) {

}
