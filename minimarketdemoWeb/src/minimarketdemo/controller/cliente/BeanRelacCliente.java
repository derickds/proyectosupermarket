package minimarketdemo.controller.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.cliente.managers.ManagerRelacionCliente;
import minimarketdemo.model.core.entities.CliPersona;
import minimarketdemo.model.core.entities.RelacCliente;
import minimarketdemo.model.core.entities.RelacMedio;

@Named
@SessionScoped
public class BeanRelacCliente implements Serializable {

	@EJB
	private ManagerRelacionCliente mRelacionCliente;
	
	private List<RelacCliente> listaRelacion;
	private RelacCliente nuevaRelacion;
	private RelacCliente edicionRelacion;
	
	private List<RelacMedio> listaMedios;
	private List<CliPersona> listaCedulas;
	private int nuevoIDMedio;
	private int edicionIDMedio;
	private String nuevaCedCli;
	private String edicionCedCli;
	
	public BeanRelacCliente() {
		
	}
	
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanRelacCliente inicializado...");
		nuevaRelacion=new RelacCliente();
	}
	
	public String actionCargarMenuRelacion() {
		listaRelacion=mRelacionCliente.findAllRelacCliente();
		return "relacion";
	}
	
	public String actionMenuNuevaRelacion() {
		nuevaRelacion=new RelacCliente();
		listaMedios=mRelacionCliente.findAllRelacMedio();
		listaCedulas=mRelacionCliente.findAllCedulas();
		return "relacion_nueva";
	}
	
	public void actionListenerInsertarNuevaRelacion() {
		try {
			mRelacionCliente.insertarRelacCliente(nuevaRelacion, nuevaCedCli, nuevoIDMedio);
			listaRelacion=mRelacionCliente.findAllRelacCliente();
			JSFUtil.crearMensajeINFO("Relacion creado");
			nuevaRelacion=new RelacCliente();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionRelacion(RelacCliente relacion) {
		edicionRelacion=relacion;
		listaMedios=mRelacionCliente.findAllRelacMedio();
		listaCedulas=mRelacionCliente.findAllCedulas();
		return "relacion_edicion";
	}
	
	public void actionListenerActualizarEdicionRelacion() {
		try {
			mRelacionCliente.actualizarRelacCliente(edicionRelacion, edicionCedCli, edicionIDMedio);
			listaRelacion=mRelacionCliente.findAllRelacCliente();
			JSFUtil.crearMensajeINFO("Relacion actualizada");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarRelacion(int idRelacCliente) {
		try {
			mRelacionCliente.eliminarRelacCliente(idRelacCliente);
			listaRelacion=mRelacionCliente.findAllRelacCliente();
			JSFUtil.crearMensajeINFO("Relacion eliminada");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int actionListenerContarRelacion() {
		return mRelacionCliente.contarRelacion();
	}

	public List<RelacCliente> getListaRelacion() {
		return listaRelacion;
	}

	public void setListaRelacion(List<RelacCliente> listaRelacion) {
		this.listaRelacion = listaRelacion;
	}

	public RelacCliente getNuevaRelacion() {
		return nuevaRelacion;
	}

	public void setNuevaRelacion(RelacCliente nuevaRelacion) {
		this.nuevaRelacion = nuevaRelacion;
	}

	public RelacCliente getEdicionRelacion() {
		return edicionRelacion;
	}

	public void setEdicionRelacion(RelacCliente edicionRelacion) {
		this.edicionRelacion = edicionRelacion;
	}

	public List<RelacMedio> getListaMedios() {
		return listaMedios;
	}

	public void setListaMedios(List<RelacMedio> listaMedios) {
		this.listaMedios = listaMedios;
	}

	public List<CliPersona> getListaCedulas() {
		return listaCedulas;
	}

	public void setListaCedulas(List<CliPersona> listaCedulas) {
		this.listaCedulas = listaCedulas;
	}

	public int getNuevoIDMedio() {
		return nuevoIDMedio;
	}

	public void setNuevoIDMedio(int nuevoIDMedio) {
		this.nuevoIDMedio = nuevoIDMedio;
	}

	public int getEdicionIDMedio() {
		return edicionIDMedio;
	}

	public void setEdicionIDMedio(int edicionIDMedio) {
		this.edicionIDMedio = edicionIDMedio;
	}

	public String getNuevaCedCli() {
		return nuevaCedCli;
	}

	public void setNuevaCedCli(String nuevaCedCli) {
		this.nuevaCedCli = nuevaCedCli;
	}

	public String getEdicionCedCli() {
		return edicionCedCli;
	}

	public void setEdicionCedCli(String edicionCedCli) {
		this.edicionCedCli = edicionCedCli;
	}
	
	

}
