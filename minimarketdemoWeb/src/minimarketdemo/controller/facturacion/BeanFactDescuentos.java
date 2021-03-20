package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.FactDescuento;
import minimarketdemo.model.facturacion.managers.ManagerFacturacion;

@Named
@SessionScoped
public class BeanFactDescuentos implements Serializable{

	@EJB
	private ManagerFacturacion mFacturacion;
	
	private List<FactDescuento> listaDescuentos;
	private FactDescuento nuevoDescuento;
	private FactDescuento editarDescuento;
	
	public BeanFactDescuentos() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanFactDescuentos inicializado...");
		nuevoDescuento=new FactDescuento();
	}
	
	public String actionCargarMenuDescuentos() {
		try {
			listaDescuentos=mFacturacion.findAllDescuentos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "descuentos";
	}
	
	
	public void actionListenerInsertarNuevoDescuento() {
		try {
			mFacturacion.insertardescuento(nuevoDescuento);
			listaDescuentos=mFacturacion.findAllDescuentos();
			JSFUtil.crearMensajeINFO("Descuento creado");
			nuevoDescuento=new FactDescuento();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerCargarDescuento(FactDescuento descuento) {
		editarDescuento=descuento;
	}
	
	public void actionListenerGuardarEdicionDescuento() {
		try {
			mFacturacion.actualizarDescuento(editarDescuento);
			JSFUtil.crearMensajeINFO("Descuento editado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarDescuento(int idFactDescuento) {
		try {
			mFacturacion.eliminarDescuento(idFactDescuento);
			listaDescuentos=mFacturacion.findAllDescuentos();
			JSFUtil.crearMensajeINFO("Descuento eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<FactDescuento> getListaDescuentos() {
		return listaDescuentos;
	}

	public void setListaDescuentos(List<FactDescuento> listaDescuentos) {
		this.listaDescuentos = listaDescuentos;
	}

	public FactDescuento getNuevoDescuento() {
		return nuevoDescuento;
	}

	public void setNuevoDescuento(FactDescuento nuevoDescuento) {
		this.nuevoDescuento = nuevoDescuento;
	}

	public FactDescuento getEditarDescuento() {
		return editarDescuento;
	}

	public void setEditarDescuento(FactDescuento editarDescuento) {
		this.editarDescuento = editarDescuento;
	}
	
}
