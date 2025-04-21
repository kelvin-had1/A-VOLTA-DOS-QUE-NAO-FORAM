package br.com.carometro.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoCurso(
		
		@NotBlank
		String nome) {

}
