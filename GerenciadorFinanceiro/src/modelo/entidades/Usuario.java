package modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import modelo.conversores.CryptoConverterPassword;

@Entity
@NamedQueries({
	@NamedQuery(name="Usuario.buscaTodos",
			query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.buscaPorIdSemPassword",
			query="SELECT u.id,u.nome,u.username FROM Usuario u WHERE u.id = :id"),
	@NamedQuery(name="Usuario.buscaPorUsername",
			query="SELECT u FROM Usuario u WHERE u.username = :username")
})
public class Usuario {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(unique=true)
	@NotNull(message="O username não pode ser nulo")
	@Size(min=6, max=30, message="O campo username precisa ter entre 6 e 30 caracteres")
	private String username;
	
	@NotNull(message="O nome não pode ser nulo")
	@Size(min=6, max=30, message="O campo nome precisa ter entre 6 e 30 caracteres")
	private String nome;
	
	@NotNull(message="O password não pode ser nulo")
	@Convert(converter=CryptoConverterPassword.class)
	@Size(min=6, max=30, message="O campo password precisa ter entre 6 e 30 caracteres")
	private String password;
	
	
	
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
