package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fact_cabecera database table.
 * 
 */
@Entity
@Table(name="fact_cabecera")
@NamedQuery(name="FactCabecera.findAll", query="SELECT f FROM FactCabecera f")
public class FactCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fact_cabecera", unique=true, nullable=false)
	private Integer idFactCabecera;

	@Column(name="direccion_local_cabecera", nullable=false, length=100)
	private String direccionLocalCabecera;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cabecera", nullable=false)
	private Date fechaCabecera;

	@Column(name="id_fact", nullable=false, length=10)
	private String idFact;

	@Column(name="iva_cabecera", nullable=false, precision=7, scale=2)
	private BigDecimal ivaCabecera;

	@Column(name="subtotal_cabecera", nullable=false, precision=7, scale=2)
	private BigDecimal subtotalCabecera;

	@Column(name="total_cabecera", nullable=false, precision=7, scale=2)
	private BigDecimal totalCabecera;

	//bi-directional many-to-one association to CliPersona
	@ManyToOne
	@JoinColumn(name="cedula_cli_persona", nullable=false)
	private CliPersona cliPersona;

	//bi-directional many-to-one association to FactDescuento
	@ManyToOne
	@JoinColumn(name="id_fact_descuento")
	private FactDescuento factDescuento;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario")
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to FactDetalle
	@OneToMany(mappedBy="factCabecera")
	private List<FactDetalle> factDetalles;

	public FactCabecera() {
	}

	public Integer getIdFactCabecera() {
		return this.idFactCabecera;
	}

	public void setIdFactCabecera(Integer idFactCabecera) {
		this.idFactCabecera = idFactCabecera;
	}

	public String getDireccionLocalCabecera() {
		return this.direccionLocalCabecera;
	}

	public void setDireccionLocalCabecera(String direccionLocalCabecera) {
		this.direccionLocalCabecera = direccionLocalCabecera;
	}

	public Date getFechaCabecera() {
		return this.fechaCabecera;
	}

	public void setFechaCabecera(Date fechaCabecera) {
		this.fechaCabecera = fechaCabecera;
	}

	public String getIdFact() {
		return this.idFact;
	}

	public void setIdFact(String idFact) {
		this.idFact = idFact;
	}

	public BigDecimal getIvaCabecera() {
		return this.ivaCabecera;
	}

	public void setIvaCabecera(BigDecimal ivaCabecera) {
		this.ivaCabecera = ivaCabecera;
	}

	public BigDecimal getSubtotalCabecera() {
		return this.subtotalCabecera;
	}

	public void setSubtotalCabecera(BigDecimal subtotalCabecera) {
		this.subtotalCabecera = subtotalCabecera;
	}

	public BigDecimal getTotalCabecera() {
		return this.totalCabecera;
	}

	public void setTotalCabecera(BigDecimal totalCabecera) {
		this.totalCabecera = totalCabecera;
	}

	public CliPersona getCliPersona() {
		return this.cliPersona;
	}

	public void setCliPersona(CliPersona cliPersona) {
		this.cliPersona = cliPersona;
	}

	public FactDescuento getFactDescuento() {
		return this.factDescuento;
	}

	public void setFactDescuento(FactDescuento factDescuento) {
		this.factDescuento = factDescuento;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public List<FactDetalle> getFactDetalles() {
		return this.factDetalles;
	}

	public void setFactDetalles(List<FactDetalle> factDetalles) {
		this.factDetalles = factDetalles;
	}

	public FactDetalle addFactDetalle(FactDetalle factDetalle) {
		getFactDetalles().add(factDetalle);
		factDetalle.setFactCabecera(this);

		return factDetalle;
	}

	public FactDetalle removeFactDetalle(FactDetalle factDetalle) {
		getFactDetalles().remove(factDetalle);
		factDetalle.setFactCabecera(null);

		return factDetalle;
	}

}