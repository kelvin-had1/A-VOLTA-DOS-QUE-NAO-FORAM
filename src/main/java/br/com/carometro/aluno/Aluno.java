package br.com.carometro.aluno;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import br.com.carometro.curso.Curso;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Aluno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private LocalDate dataNasc;
	private String foto;
	private String historico;
	private boolean aprovado;
	private List<String> redesSociais;
	@ManyToOne(fetch = FetchType.LAZY)  // Muitos alunos para UM curso
    @JoinColumn(name = "curso_id")      // Coluna FK na tabela aluno
    private Curso curso;
	
	public Aluno(DadosCadastroAluno dados) {
		this.nome = dados.nome();
		this.dataNasc = dados.dataNasc();
		this.foto = dados.foto();
		this.historico = dados.historico();
		this.aprovado = false;
		this.redesSociais = dados.redesSociais();			
	}
	
	public void atualizarInformacoes(DadosAtualizacaoAluno dados) {
		if (dados.nome() != null)
			this.nome = dados.nome();
		
		if (dados.dataNasc() != null)
			this.dataNasc = dados.dataNasc();
		
		if (dados.foto() != null)
			this.foto = dados.foto();
		
		if (dados.historico() != null)
			this.historico = dados.historico();
		
		if (dados.aprovado())
			this.aprovado = dados.aprovado();
		
		if (dados.redesSociais() != null)
			this.redesSociais = dados.redesSociais();
		
	}
	
}
