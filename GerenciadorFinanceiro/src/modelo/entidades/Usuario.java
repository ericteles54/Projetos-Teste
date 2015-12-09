package modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;

import conversores.CryptoConverter;

@Entity
@NamedQueries({
	@NamedQuery(name="Usuario.buscaTodos",
			query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.buscaPorUsername",
			query="SELECT u FROM Usuario u WHERE u.username = :username")
})
public class Usuario {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(unique=true, nullable=false)
	@Size(min=6, max=30, message="O campo username precisa ter entre 6 e 30 caracteres")
	private String username;
	
	@Column(nullable=false)
	@Size(min=6, max=30, message="O campo nome precisa ter entre 6 e 30 caracteres")
	private String nome;
	
	@Column(nullable=false)
	@Convert(converter=CryptoConverter.class)
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
