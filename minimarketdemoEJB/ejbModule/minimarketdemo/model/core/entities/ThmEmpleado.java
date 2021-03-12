package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the thm_empleado database table.
 * 
 */
@Entity
@Table(name="thm_empleado")
@NamedQuery(name="ThmEmpleado.findAll", query="SELECT t FROM ThmEmpleado t")
public class ThmEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_thm_empleado", unique=true, nullable=false)
	private Integer idThmEmpleado;

	@Column(name="horas_trabajadas", nullable=false)
	private Integer horasTrabajadas;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario", nullable=false)
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to ThmCargo
	@ManyToOne
	@JoinColumn(name="id_thm_cargo", nullable=false)
	private ThmCargo thmCargo;

	//bi-directional many-to-one association to ThmRolCabecera
	@OneToMany(mappedBy="thmEmpleado")
	private List<ThmRolCabecera> thmRolCabeceras;

	//bi-directional many-to-one association to VentRegistro
	@OneToMany(mappedBy="thmEmpleado")
	private List<VentRegistro> ventRegistros;

	public ThmEmpleado() {
	}

	public Integer getIdThmEmpleado() {
		return this.idThmEmpleado;
	}

	public void setIdThmEmpleado(Integer idThmEmpleado) {
		this.idThmEmpleado = idThmEmpleado;
	}

	public Integer getHorasTrabajadas() {
		return this.horasTrabajadas;
	}

	public void setHorasTrabajadas(Integer horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public ThmCargo getThmCargo() {
		return this.thmCargo;
	}

	public void setThmCargo(ThmCargo thmCargo) {
		this.thmCargo = thmCargo;
	}

	public List<ThmRolCabecera> getThmRolCabeceras() {
		return this.thmRolCabeceras;
	}

	public void setThmRolCabeceras(List<ThmRolCabecera> thmRolCabeceras) {
		this.thmRolCabeceras = thmRolCabeceras;
	}

	public ThmRolCabecera addThmRolCabecera(ThmRolCabecera thmRolCabecera) {
		getThmRolCabeceras().add(thmRolCabecera);
		thmRolCabecera.setThmEmpleado(this);

		return thmRolCabecera;
	}

	public ThmRolCabecera removeThmRolCabecera(ThmRolCabecera thmRolCabecera) {
		getThmRolCabeceras().remove(thmRolCabecera);
		thmRolCabecera.setThmEmpleado(null);

		return thmRolCabecera;
	}

	public List<VentRegistro> getVentRegistros() {
		return this.ventRegistros;
	}

	public void setVentRegistros(List<VentRegistro> ventRegistros) {
		this.ventRegistros = ventRegistros;
	}

	public VentRegistro addVentRegistro(VentRegistro ventRegistro) {
		getVentRegistros().add(ventRegistro);
		ventRegistro.setThmEmpleado(this);

		return ventRegistro;
	}

	public VentRegistro removeVentRegistro(VentRegistro ventRegistro) {
		getVentRegistros().remove(ventRegistro);
		ventRegistro.setThmEmpleado(null);

		return ventRegistro;
	}

}