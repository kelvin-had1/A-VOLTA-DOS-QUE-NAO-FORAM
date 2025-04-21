package br.com.carometro.coordenador;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCoordenador(
		
		@NotBlank
		String nome,
		String login,
		String senha) {

}
