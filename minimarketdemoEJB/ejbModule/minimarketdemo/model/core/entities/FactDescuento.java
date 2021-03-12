package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fact_descuento database table.
 * 
 */
@Entity
@Table(name="fact_descuento")
@NamedQuery(name="FactDescuento.findAll", query="SELECT f FROM FactDescuento f")
public class FactDescuento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fact_descuento", unique=true, nullable=false)
	private Integer idFactDescuento;

	@Column(name="nombre_descuento", nullable=false, length=100)
	private String nombreDescuento;

	@Column(name="porcentaje_descuento", nullable=false)
	private Integer porcentajeDescuento;

	//bi-directional many-to-one association to FactCabecera
	@OneToMany(mappedBy="factDescuento")
	private List<FactCabecera> factCabeceras;

	public FactDescuento() {
	}

	public Integer getIdFactDescuento() {
		return this.idFactDescuento;
	}

	public void setIdFactDescuento(Integer idFactDescuento) {
		this.idFactDescuento = idFactDescuento;
	}

	public String getNombreDescuento() {
		return this.nombreDescuento;
	}

	public void setNombreDescuento(String nombreDescuento) {
		this.nombreDescuento = nombreDescuento;
	}

	public Integer getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}

	public void setPorcentajeDescuento(Integer porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public List<FactCabecera> getFactCabeceras() {
		return this.factCabeceras;
	}

	public void setFactCabeceras(List<FactCabecera> factCabeceras) {
		this.factCabeceras = factCabeceras;
	}

	public FactCabecera addFactCabecera(FactCabecera factCabecera) {
		getFactCabeceras().add(factCabecera);
		factCabecera.setFactDescuento(this);

		return factCabecera;
	}

	public FactCabecera removeFactCabecera(FactCabecera factCabecera) {
		getFactCabeceras().remove(factCabecera);
		factCabecera.setFactDescuento(null);

		return factCabecera;
	}

}