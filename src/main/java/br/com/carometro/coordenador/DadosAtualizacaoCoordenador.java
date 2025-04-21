package br.com.carometro.coordenador;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoCoordenador(
		
		@NotBlank
		String nome,
		String login,
		String senha) {

}
