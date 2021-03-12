package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the inv_producto database table.
 * 
 */
@Entity
@Table(name="inv_producto")
@NamedQuery(name="InvProducto.findAll", query="SELECT i FROM InvProducto i")
public class InvProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inv_producto", unique=true, nullable=false)
	private Integer idInvProducto;

	@Column(name="nombre_producto", nullable=false, length=50)
	private String nombreProducto;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal precio;

	//bi-directional many-to-one association to FactDetalle
	@OneToMany(mappedBy="invProducto")
	private List<FactDetalle> factDetalles;

	//bi-directional many-to-one association to InvProveedor
	@ManyToOne
	@JoinColumn(name="id_inv_proveedor")
	private InvProveedor invProveedor;

	//bi-directional many-to-one association to InvStock
	@OneToMany(mappedBy="invProducto")
	private List<InvStock> invStocks;

	//bi-directional many-to-one association to PedOrdenDetalle
	@OneToMany(mappedBy="invProducto")
	private List<PedOrdenDetalle> pedOrdenDetalles;

	public InvProducto() {
	}

	public Integer getIdInvProducto() {
		return this.idInvProducto;
	}

	public void setIdInvProducto(Integer idInvProducto) {
		this.idInvProducto = idInvProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public List<FactDetalle> getFactDetalles() {
		return this.factDetalles;
	}

	public void setFactDetalles(List<FactDetalle> factDetalles) {
		this.factDetalles = factDetalles;
	}

	public FactDetalle addFactDetalle(FactDetalle factDetalle) {
		getFactDetalles().add(factDetalle);
		factDetalle.setInvProducto(this);

		return factDetalle;
	}

	public FactDetalle removeFactDetalle(FactDetalle factDetalle) {
		getFactDetalles().remove(factDetalle);
		factDetalle.setInvProducto(null);

		return factDetalle;
	}

	public InvProveedor getInvProveedor() {
		return this.invProveedor;
	}

	public void setInvProveedor(InvProveedor invProveedor) {
		this.invProveedor = invProveedor;
	}

	public List<InvStock> getInvStocks() {
		return this.invStocks;
	}

	public void setInvStocks(List<InvStock> invStocks) {
		this.invStocks = invStocks;
	}

	public InvStock addInvStock(InvStock invStock) {
		getInvStocks().add(invStock);
		invStock.setInvProducto(this);

		return invStock;
	}

	public InvStock removeInvStock(InvStock invStock) {
		getInvStocks().remove(invStock);
		invStock.setInvProducto(null);

		return invStock;
	}

	public List<PedOrdenDetalle> getPedOrdenDetalles() {
		return this.pedOrdenDetalles;
	}

	public void setPedOrdenDetalles(List<PedOrdenDetalle> pedOrdenDetalles) {
		this.pedOrdenDetalles = pedOrdenDetalles;
	}

	public PedOrdenDetalle addPedOrdenDetalle(PedOrdenDetalle pedOrdenDetalle) {
		getPedOrdenDetalles().add(pedOrdenDetalle);
		pedOrdenDetalle.setInvProducto(this);

		return pedOrdenDetalle;
	}

	public PedOrdenDetalle removePedOrdenDetalle(PedOrdenDetalle pedOrdenDetalle) {
		getPedOrdenDetalles().remove(pedOrdenDetalle);
		pedOrdenDetalle.setInvProducto(null);

		return pedOrdenDetalle;
	}

}