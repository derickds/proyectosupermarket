package minimarketdemo.controller.cliente;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.cliente.managers.ManagerCliente;
import minimarketdemo.model.core.entities.CliPersona;

@Named
@SessionScoped
public class BeanCliPersona implements Serializable {

	@EJB
	private ManagerCliente mCliente;
	
	private List<CliPersona> listaPersonas;
	private CliPersona nuevaPersona;
	private CliPersona edicionPersona;
	
	public BeanCliPersona() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanCliPersona inicializado...");
		nuevaPersona = new CliPersona();
	}
	
	public String actionCargarMenuCliPersona() {
		listaPersonas = mCliente.findAllPersonas();
		return "clientes";
	}
	
	public String actionMenuNuevaPersona() {
		nuevaPersona = new CliPersona();
		return "clientes_nuevo";
	}
	
	public void actionListenerInsertarNuevaPersona() {
		try {
			mCliente.insertarPersona(nuevaPersona);
			listaPersonas = mCliente.findAllPersonas();
			JSFUtil.crearMensajeINFO("Cliente registrado");
			nuevaPersona = new CliPersona();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionPersona(CliPersona persona) {
		edicionPersona=persona;
		return "clientes_edicion";
	}
	
	public void actionListenerActualizarEdicionPersona() {
		try {
			mCliente.actualizarPersona(edicionPersona);
			listaPersonas = mCliente.findAllPersonas();
			JSFUtil.crearMensajeINFO("Cliente editado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarPersona(String cedulaCliPersona) {
		try {
			mCliente.eliminarPersona(cedulaCliPersona);
			listaPersonas = mCliente.findAllPersonas();
			JSFUtil.crearMensajeINFO("Cliente eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<CliPersona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<CliPersona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public CliPersona getNuevaPersona() {
		return nuevaPersona;
	}

	public void setNuevaPersona(CliPersona nuevaPersona) {
		this.nuevaPersona = nuevaPersona;
	}

	public CliPersona getEdicionPersona() {
		return edicionPersona;
	}

	public void setEdicionPersona(CliPersona edicionPersona) {
		this.edicionPersona = edicionPersona;
	}
	
	

}
