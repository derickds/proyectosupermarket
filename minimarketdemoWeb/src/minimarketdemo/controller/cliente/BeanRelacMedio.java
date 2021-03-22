package minimarketdemo.controller.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.cliente.managers.ManagerRelacionCliente;
import minimarketdemo.model.core.entities.RelacMedio;

@Named
@SessionScoped
public class BeanRelacMedio implements Serializable {

	@EJB
	private ManagerRelacionCliente mRelacionCliente;
	
	private List<RelacMedio> listaMedios;
	private RelacMedio nuevoMedio;
	private RelacMedio edicionMedio;
	
	public BeanRelacMedio() {

	}
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanRelacMedio inicializado...");
		nuevoMedio=new RelacMedio();
	}
	
	public String actionCargarMenuMedios() {
		listaMedios=mRelacionCliente.findAllRelacMedio();
		return "medios";
	}
	
	public String actionMenuNuevoMedio() {
		nuevoMedio=new RelacMedio();
		return "medios_nuevo";
	}
	
	public void actionListenerInsertarNuevoMedio() {
		try {
			mRelacionCliente.insertarRelacMedio(nuevoMedio);
			listaMedios=mRelacionCliente.findAllRelacMedio();
			JSFUtil.crearMensajeINFO("Medio creado");
			nuevoMedio=new RelacMedio();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionMedio(RelacMedio medio) {
		edicionMedio=medio;
		return "medios_edicion";
	}
	
	public void actionListenerActualizarEdicionMedio() {
		try {
			mRelacionCliente.actualizarRelacMedio(edicionMedio);
			listaMedios=mRelacionCliente.findAllRelacMedio();
			JSFUtil.crearMensajeINFO("Medio editado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarMedio(int idRelacMedio) {
		try {
			mRelacionCliente.eliminarRelacMedio(idRelacMedio);
			listaMedios=mRelacionCliente.findAllRelacMedio();
			JSFUtil.crearMensajeINFO("Medio eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int actionListenerContarMedios() {
			return mRelacionCliente.contarMedios();
		}
	
	public List<RelacMedio> getListaMedios() {
		return listaMedios;
	}
	public void setListaMedios(List<RelacMedio> listaMedios) {
		this.listaMedios = listaMedios;
	}
	public RelacMedio getNuevoMedio() {
		return nuevoMedio;
	}
	public void setNuevoMedio(RelacMedio nuevoMedio) {
		this.nuevoMedio = nuevoMedio;
	}
	public RelacMedio getEdicionMedio() {
		return edicionMedio;
	}
	public void setEdicionMedio(RelacMedio edicionMedio) {
		this.edicionMedio = edicionMedio;
	}
	
	
}
