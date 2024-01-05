package br.com.ecomunidade.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome é Obrigatório!")
	@Size(max = 100, message = "O atributo nome deve conter no mínimo 10 e no máximo 100 caracteres")
	@Column(length = 100)
	private String nome;

	@Schema(example = "email@email.com.br")
	@NotBlank(message = "O atributo email é Obrigatório!")
	@Size(max = 100, message = "O atributo email deve conter no mínimo 10 e no máximo 100 caracteres")
	@Email(message = "Informe um endereço de e-mail válido") // perguntar yuri
	private String email;
	
	@NotBlank(message = "O atributo senha é Obrigatório!")
	@Size(max = 100, message = "A senha deve conter no mínimo 10 e no máximo 100 caracteres")
	@Column(length = 100)
	private String senha;
	
	@Size(max = 1000, message = "A url deve ter no maximo 1000 caracteres")
	@Column(length = 1000)
	private String foto;
	
	@Size(max = 1000, message = "A bio deve conter no máximo 1000 caracteres")
	@Column(length = 1000)
	private String bio;
	
	@Size(max = 1000, message = "As informações de contato devem conter no máximo 1000 caracteres")
	@Column(length = 1000)
	private String contato;
	
	@Past(message = "A data de nascimento deve estar no passado")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate data_nascimento;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Post> post;
	
	

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		//this.post = post;
	}
	
	
	
	

}
