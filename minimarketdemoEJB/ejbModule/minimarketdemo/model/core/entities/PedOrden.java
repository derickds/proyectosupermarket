package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ped_orden database table.
 * 
 */
@Entity
@Table(name="ped_orden")
@NamedQuery(name="PedOrden.findAll", query="SELECT p FROM PedOrden p")
public class PedOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ped_orden", unique=true, nullable=false)
	private Integer pedOrden;

	//bi-directional many-to-one association to CliPersona
	@ManyToOne
	@JoinColumn(name="cedula_cli_persona")
	private CliPersona cliPersona;

	//bi-directional many-to-one association to PedOrdenDetalle
	@OneToMany(mappedBy="pedOrdenBean")
	private List<PedOrdenDetalle> pedOrdenDetalles;

	public PedOrden() {
	}

	public Integer getPedOrden() {
		return this.pedOrden;
	}

	public void setPedOrden(Integer pedOrden) {
		this.pedOrden = pedOrden;
	}

	public CliPersona getCliPersona() {
		return this.cliPersona;
	}

	public void setCliPersona(CliPersona cliPersona) {
		this.cliPersona = cliPersona;
	}

	public List<PedOrdenDetalle> getPedOrdenDetalles() {
		return this.pedOrdenDetalles;
	}

	public void setPedOrdenDetalles(List<PedOrdenDetalle> pedOrdenDetalles) {
		this.pedOrdenDetalles = pedOrdenDetalles;
	}

	public PedOrdenDetalle addPedOrdenDetalle(PedOrdenDetalle pedOrdenDetalle) {
		getPedOrdenDetalles().add(pedOrdenDetalle);
		pedOrdenDetalle.setPedOrdenBean(this);

		return pedOrdenDetalle;
	}

	public PedOrdenDetalle removePedOrdenDetalle(PedOrdenDetalle pedOrdenDetalle) {
		getPedOrdenDetalles().remove(pedOrdenDetalle);
		pedOrdenDetalle.setPedOrdenBean(null);

		return pedOrdenDetalle;
	}

}