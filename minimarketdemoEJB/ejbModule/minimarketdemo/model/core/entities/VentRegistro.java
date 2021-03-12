package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vent_registro database table.
 * 
 */
@Entity
@Table(name="vent_registro")
@NamedQuery(name="VentRegistro.findAll", query="SELECT v FROM VentRegistro v")
public class VentRegistro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vent_registro", unique=true, nullable=false)
	private Integer idVentRegistro;

	@Column(name="mensaje_registro", nullable=false, length=200)
	private String mensajeRegistro;

	//bi-directional many-to-one association to FactDetalle
	@ManyToOne
	@JoinColumn(name="id_fact_detalle")
	private FactDetalle factDetalle;

	//bi-directional many-to-one association to ThmEmpleado
	@ManyToOne
	@JoinColumn(name="id_thm_empleado")
	private ThmEmpleado thmEmpleado;

	public VentRegistro() {
	}

	public Integer getIdVentRegistro() {
		return this.idVentRegistro;
	}

	public void setIdVentRegistro(Integer idVentRegistro) {
		this.idVentRegistro = idVentRegistro;
	}

	public String getMensajeRegistro() {
		return this.mensajeRegistro;
	}

	public void setMensajeRegistro(String mensajeRegistro) {
		this.mensajeRegistro = mensajeRegistro;
	}

	public FactDetalle getFactDetalle() {
		return this.factDetalle;
	}

	public void setFactDetalle(FactDetalle factDetalle) {
		this.factDetalle = factDetalle;
	}

	public ThmEmpleado getThmEmpleado() {
		return this.thmEmpleado;
	}

	public void setThmEmpleado(ThmEmpleado thmEmpleado) {
		this.thmEmpleado = thmEmpleado;
	}

}