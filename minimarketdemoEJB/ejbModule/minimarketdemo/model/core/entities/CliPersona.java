package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cli_persona database table.
 * 
 */
@Entity
@Table(name="cli_persona")
@NamedQuery(name="CliPersona.findAll", query="SELECT c FROM CliPersona c")
public class CliPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_cli_persona", unique=true, nullable=false, length=13)
	private String cedulaCliPersona;

	@Column(name="apellidos_persona", nullable=false, length=50)
	private String apellidosPersona;

	@Column(name="direccion_persona", nullable=false, length=100)
	private String direccionPersona;

	@Column(name="email_persona", nullable=false, length=100)
	private String emailPersona;

	@Column(name="nombres_persona", nullable=false, length=50)
	private String nombresPersona;

	@Column(name="telefono_persona", nullable=false, length=10)
	private String telefonoPersona;

	//bi-directional many-to-one association to FactCabecera
	@OneToMany(mappedBy="cliPersona")
	private List<FactCabecera> factCabeceras;

	//bi-directional many-to-one association to PedOrden
	@OneToMany(mappedBy="cliPersona")
	private List<PedOrden> pedOrdens;

	//bi-directional many-to-one association to RelacCliente
	@OneToMany(mappedBy="cliPersona")
	private List<RelacCliente> relacClientes;

	public CliPersona() {
	}

	public String getCedulaCliPersona() {
		return this.cedulaCliPersona;
	}

	public void setCedulaCliPersona(String cedulaCliPersona) {
		this.cedulaCliPersona = cedulaCliPersona;
	}

	public String getApellidosPersona() {
		return this.apellidosPersona;
	}

	public void setApellidosPersona(String apellidosPersona) {
		this.apellidosPersona = apellidosPersona;
	}

	public String getDireccionPersona() {
		return this.direccionPersona;
	}

	public void setDireccionPersona(String direccionPersona) {
		this.direccionPersona = direccionPersona;
	}

	public String getEmailPersona() {
		return this.emailPersona;
	}

	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}

	public String getNombresPersona() {
		return this.nombresPersona;
	}

	public void setNombresPersona(String nombresPersona) {
		this.nombresPersona = nombresPersona;
	}

	public String getTelefonoPersona() {
		return this.telefonoPersona;
	}

	public void setTelefonoPersona(String telefonoPersona) {
		this.telefonoPersona = telefonoPersona;
	}

	public List<FactCabecera> getFactCabeceras() {
		return this.factCabeceras;
	}

	public void setFactCabeceras(List<FactCabecera> factCabeceras) {
		this.factCabeceras = factCabeceras;
	}

	public FactCabecera addFactCabecera(FactCabecera factCabecera) {
		getFactCabeceras().add(factCabecera);
		factCabecera.setCliPersona(this);

		return factCabecera;
	}

	public FactCabecera removeFactCabecera(FactCabecera factCabecera) {
		getFactCabeceras().remove(factCabecera);
		factCabecera.setCliPersona(null);

		return factCabecera;
	}

	public List<PedOrden> getPedOrdens() {
		return this.pedOrdens;
	}

	public void setPedOrdens(List<PedOrden> pedOrdens) {
		this.pedOrdens = pedOrdens;
	}

	public PedOrden addPedOrden(PedOrden pedOrden) {
		getPedOrdens().add(pedOrden);
		pedOrden.setCliPersona(this);

		return pedOrden;
	}

	public PedOrden removePedOrden(PedOrden pedOrden) {
		getPedOrdens().remove(pedOrden);
		pedOrden.setCliPersona(null);

		return pedOrden;
	}

	public List<RelacCliente> getRelacClientes() {
		return this.relacClientes;
	}

	public void setRelacClientes(List<RelacCliente> relacClientes) {
		this.relacClientes = relacClientes;
	}

	public RelacCliente addRelacCliente(RelacCliente relacCliente) {
		getRelacClientes().add(relacCliente);
		relacCliente.setCliPersona(this);

		return relacCliente;
	}

	public RelacCliente removeRelacCliente(RelacCliente relacCliente) {
		getRelacClientes().remove(relacCliente);
		relacCliente.setCliPersona(null);

		return relacCliente;
	}

}