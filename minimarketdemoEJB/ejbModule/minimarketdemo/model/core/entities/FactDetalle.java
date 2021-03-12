package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the fact_detalle database table.
 * 
 */
@Entity
@Table(name="fact_detalle")
@NamedQuery(name="FactDetalle.findAll", query="SELECT f FROM FactDetalle f")
public class FactDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fact_detalle", unique=true, nullable=false)
	private Integer idFactDetalle;

	@Column(name="cantidad_detalle", nullable=false)
	private Integer cantidadDetalle;

	@Column(name="costo_unitario_detalle", nullable=false, precision=7, scale=2)
	private BigDecimal costoUnitarioDetalle;

	@Column(name="sub_total_detalle", nullable=false, precision=7, scale=2)
	private BigDecimal subTotalDetalle;

	//bi-directional many-to-one association to FactCabecera
	@ManyToOne
	@JoinColumn(name="id_fact_cabecera", nullable=false)
	private FactCabecera factCabecera;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="id_inv_producto", nullable=false)
	private InvProducto invProducto;

	//bi-directional many-to-one association to VentRegistro
	@OneToMany(mappedBy="factDetalle")
	private List<VentRegistro> ventRegistros;

	public FactDetalle() {
	}

	public Integer getIdFactDetalle() {
		return this.idFactDetalle;
	}

	public void setIdFactDetalle(Integer idFactDetalle) {
		this.idFactDetalle = idFactDetalle;
	}

	public Integer getCantidadDetalle() {
		return this.cantidadDetalle;
	}

	public void setCantidadDetalle(Integer cantidadDetalle) {
		this.cantidadDetalle = cantidadDetalle;
	}

	public BigDecimal getCostoUnitarioDetalle() {
		return this.costoUnitarioDetalle;
	}

	public void setCostoUnitarioDetalle(BigDecimal costoUnitarioDetalle) {
		this.costoUnitarioDetalle = costoUnitarioDetalle;
	}

	public BigDecimal getSubTotalDetalle() {
		return this.subTotalDetalle;
	}

	public void setSubTotalDetalle(BigDecimal subTotalDetalle) {
		this.subTotalDetalle = subTotalDetalle;
	}

	public FactCabecera getFactCabecera() {
		return this.factCabecera;
	}

	public void setFactCabecera(FactCabecera factCabecera) {
		this.factCabecera = factCabecera;
	}

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

	public List<VentRegistro> getVentRegistros() {
		return this.ventRegistros;
	}

	public void setVentRegistros(List<VentRegistro> ventRegistros) {
		this.ventRegistros = ventRegistros;
	}

	public VentRegistro addVentRegistro(VentRegistro ventRegistro) {
		getVentRegistros().add(ventRegistro);
		ventRegistro.setFactDetalle(this);

		return ventRegistro;
	}

	public VentRegistro removeVentRegistro(VentRegistro ventRegistro) {
		getVentRegistros().remove(ventRegistro);
		ventRegistro.setFactDetalle(null);

		return ventRegistro;
	}

}