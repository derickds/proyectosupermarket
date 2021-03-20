package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.FactCabecera;
import minimarketdemo.model.core.entities.FactDetalle;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;

@Named
@SessionScoped
public class BeanFactDetalleFactura implements Serializable {
	@EJB
	private ManagerFacturacion mFacturacion;
	private List<FactDetalle> listaDetalles;
	private List<FactCabecera> listaCabecera;
	private int idFactCabecera;
	public BeanFactDetalleFactura() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		listaDetalles= mFacturacion.findAllDetallesFactura();
	}
	
	public String actionCargarMenuDetalle() {
		listaDetalles = mFacturacion.findAllDetallesFactura();
		listaCabecera =  mFacturacion.findAllCabecerasFactura();
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaDetalles.size());
		return "detalles";
	}

	public void actionListenerConsultarDetalles() {
		listaDetalles = mFacturacion.findDetallesFacturaByIdCabecera(idFactCabecera);
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaDetalles.size());
	}
	
	public List<FactDetalle> getListaDetalles() {
		return listaDetalles;
	}

	public void setListaDetalles(List<FactDetalle> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	public int getIdFactCabecera() {
		return idFactCabecera;
	}

	public void setIdFactCabecera(int idFactCabecera) {
		this.idFactCabecera = idFactCabecera;
	}

	public List<FactCabecera> getListaCabecera() {
		return listaCabecera;
	}

	public void setListaCabecera(List<FactCabecera> listaCabecera) {
		this.listaCabecera = listaCabecera;
	}
	
	

}
