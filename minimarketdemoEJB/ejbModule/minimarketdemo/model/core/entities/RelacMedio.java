package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the relac_medio database table.
 * 
 */
@Entity
@Table(name="relac_medio")
@NamedQuery(name="RelacMedio.findAll", query="SELECT r FROM RelacMedio r")
public class RelacMedio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_relac_medio", unique=true, nullable=false)
	private Integer idRelacMedio;

	@Column(name="nombre_medio", nullable=false, length=100)
	private String nombreMedio;

	//bi-directional many-to-one association to RelacCliente
	@OneToMany(mappedBy="relacMedio")
	private List<RelacCliente> relacClientes;

	public RelacMedio() {
	}

	public Integer getIdRelacMedio() {
		return this.idRelacMedio;
	}

	public void setIdRelacMedio(Integer idRelacMedio) {
		this.idRelacMedio = idRelacMedio;
	}

	public String getNombreMedio() {
		return this.nombreMedio;
	}

	public void setNombreMedio(String nombreMedio) {
		this.nombreMedio = nombreMedio;
	}

	public List<RelacCliente> getRelacClientes() {
		return this.relacClientes;
	}

	public void setRelacClientes(List<RelacCliente> relacClientes) {
		this.relacClientes = relacClientes;
	}

	public RelacCliente addRelacCliente(RelacCliente relacCliente) {
		getRelacClientes().add(relacCliente);
		relacCliente.setRelacMedio(this);

		return relacCliente;
	}

	public RelacCliente removeRelacCliente(RelacCliente relacCliente) {
		getRelacClientes().remove(relacCliente);
		relacCliente.setRelacMedio(null);

		return relacCliente;
	}

}