package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ped_orden_detalle database table.
 * 
 */
@Entity
@Table(name="ped_orden_detalle")
@NamedQuery(name="PedOrdenDetalle.findAll", query="SELECT p FROM PedOrdenDetalle p")
public class PedOrdenDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ped_orden_detalle", unique=true, nullable=false)
	private Integer idPedOrdenDetalle;

	@Column(name="cantidad_orden", nullable=false)
	private Integer cantidadOrden;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="id_inv_producto", nullable=false)
	private InvProducto invProducto;

	//bi-directional many-to-one association to PedOrden
	@ManyToOne
	@JoinColumn(name="ped_orden", nullable=false)
	private PedOrden pedOrdenBean;

	public PedOrdenDetalle() {
	}

	public Integer getIdPedOrdenDetalle() {
		return this.idPedOrdenDetalle;
	}

	public void setIdPedOrdenDetalle(Integer idPedOrdenDetalle) {
		this.idPedOrdenDetalle = idPedOrdenDetalle;
	}

	public Integer getCantidadOrden() {
		return this.cantidadOrden;
	}

	public void setCantidadOrden(Integer cantidadOrden) {
		this.cantidadOrden = cantidadOrden;
	}

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

	public PedOrden getPedOrdenBean() {
		return this.pedOrdenBean;
	}

	public void setPedOrdenBean(PedOrden pedOrdenBean) {
		this.pedOrdenBean = pedOrdenBean;
	}

}