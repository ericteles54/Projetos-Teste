package modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Receita.buscaTodas", query="SELECT r FROM Receita r WHERE r.conta.id = :conta_id")
})
public class Receita extends Movimentacao {

}
