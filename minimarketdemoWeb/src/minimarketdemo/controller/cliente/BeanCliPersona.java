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
	private String cedulaClienteFact;
	private CliPersona clienteFact;
	private CliPersona ultimoCliente;
	
	
	public BeanCliPersona() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanCliPersona inicializado...");
		listaPersonas = mCliente.findAllPersonas();
		nuevaPersona = new CliPersona();
		ultimoCliente=mCliente.clienteMasReciente();
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
	
	public void actionFindByIdCliente() {
		try {
			clienteFact = new CliPersona();
			clienteFact =  mCliente.findByIdPersona(cedulaClienteFact);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public void actionListenerRegistrarClienteFactura() {
		try {
			if(mCliente.verificarClienteFactura(clienteFact)==false) {
				mCliente.insertarPersona(clienteFact);
				listaPersonas = mCliente.findAllPersonas();
				JSFUtil.crearMensajeINFO("Cliente registrado");
			}
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int actionListenerContarClientes() {
		return mCliente.contarClientes();
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

	public String getCedulaClienteFact() {
		return cedulaClienteFact;
	}

	public void setCedulaClienteFact(String cedulaClienteFact) {
		this.cedulaClienteFact = cedulaClienteFact;
	}

	public CliPersona getClienteFact() {
		return clienteFact;
	}

	public void setClienteFact(CliPersona clienteFact) {
		this.clienteFact = clienteFact;
	}

	public CliPersona getUltimoCliente() {
		return ultimoCliente;
	}

	public void setUltimoCliente(CliPersona ultimoCliente) {
		this.ultimoCliente = ultimoCliente;
	}
	
	

}
