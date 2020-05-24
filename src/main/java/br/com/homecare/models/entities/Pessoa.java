package br.com.homecare.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.homecare.commons.AbstractEntity;
import br.com.homecare.models.dtos.PessoaDTO;
import br.com.homecare.models.enums.GeneroEnum;
import br.com.homecare.models.enums.TipoUsuarioEnum;
import br.com.homecare.utils.messages.ExceptionMessages;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends AbstractEntity<PessoaDTO> {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotEmpty(message = ExceptionMessages.CAMPO_VAZIO)
	@Length(min = 5, max = 80, message = ExceptionMessages.NUM_CARACTER_INVALIDO )
    @Column(name = "nome", nullable = false)
    private String nome;

//	@CPF
	@NotEmpty(message = ExceptionMessages.CAMPO_VAZIO)
	@Length(min = 14, max = 14, message = ExceptionMessages.CPF_INVALIDO )
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "altura", length = 3)
    private Double altura;

    @Column(name = "peso", length = 3)
    private Double peso;

    @Column(name = "idade", length = 3)
    private Integer idade;

    private Integer genero;

//    @Email
	@NotEmpty(message = "Email não preenchido.")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String telefone;

    private String endereco;

    @Column(name = "tipo_usuario", nullable = false)
    private Integer tipoUsuario = TipoUsuarioEnum.VISITANTE.getCode();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
		
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public GeneroEnum getGenero() {
		return GeneroEnum.toEnum(this.genero);
	}

	public void setGenero(GeneroEnum genero) {
		this.genero = genero.getCode();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public TipoUsuarioEnum getTipoUsuario() {
		return TipoUsuarioEnum.toEnum(this.tipoUsuario);
	}

	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario.getCode();
	}
	
	@JsonIgnore
	public Pessoa getPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.getId());
		pessoa.setCpf(this.getCpf());
		pessoa.setNome(this.getNome());
		pessoa.setPeso(this.getPeso());
		pessoa.setIdade(this.getIdade());
		pessoa.setEmail(this.getEmail());
		pessoa.setGenero(this.getGenero());
		pessoa.setAltura(this.getAltura());
		pessoa.setTelefone(this.getTelefone());
		pessoa.setEndereco(this.getEndereco());
		pessoa.setTipoUsuario(this.getTipoUsuario());
		
		return pessoa;
	}


	@JsonIgnore	
	public void setPessoa(Pessoa pessoa) {
		this.setId(pessoa.getId());
		this.setCpf(pessoa.getCpf());
		this.setNome(pessoa.getNome());
		this.setPeso(pessoa.getPeso());
		this.setIdade(pessoa.getIdade());
		this.setEmail(pessoa.getEmail());
		this.setGenero(pessoa.getGenero());
		this.setAltura(pessoa.getAltura());
		this.setTelefone(pessoa.getTelefone());
		this.setEndereco(pessoa.getEndereco());
		this.setTipoUsuario(this.getTipoUsuario());
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj, this.id);
	}

}
