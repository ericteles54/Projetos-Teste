package modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
	private String username;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	@Convert(converter=CryptoConverter.class)
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
