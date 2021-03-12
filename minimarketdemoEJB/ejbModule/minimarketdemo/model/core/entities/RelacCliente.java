package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the relac_cliente database table.
 * 
 */
@Entity
@Table(name="relac_cliente")
@NamedQuery(name="RelacCliente.findAll", query="SELECT r FROM RelacCliente r")
public class RelacCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_relac_cliente", unique=true, nullable=false)
	private Integer idRelacCliente;

	@Column(name="descripcion_cliente", nullable=false, length=300)
	private String descripcionCliente;

	//bi-directional many-to-one association to CliPersona
	@ManyToOne
	@JoinColumn(name="cedula_cli_persona", nullable=false)
	private CliPersona cliPersona;

	//bi-directional many-to-one association to RelacMedio
	@ManyToOne
	@JoinColumn(name="id_relac_medio", nullable=false)
	private RelacMedio relacMedio;

	public RelacCliente() {
	}

	public Integer getIdRelacCliente() {
		return this.idRelacCliente;
	}

	public void setIdRelacCliente(Integer idRelacCliente) {
		this.idRelacCliente = idRelacCliente;
	}

	public String getDescripcionCliente() {
		return this.descripcionCliente;
	}

	public void setDescripcionCliente(String descripcionCliente) {
		this.descripcionCliente = descripcionCliente;
	}

	public CliPersona getCliPersona() {
		return this.cliPersona;
	}

	public void setCliPersona(CliPersona cliPersona) {
		this.cliPersona = cliPersona;
	}

	public RelacMedio getRelacMedio() {
		return this.relacMedio;
	}

	public void setRelacMedio(RelacMedio relacMedio) {
		this.relacMedio = relacMedio;
	}

}