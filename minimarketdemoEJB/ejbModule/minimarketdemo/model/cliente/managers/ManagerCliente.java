package minimarketdemo.model.cliente.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.CliPersona;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerCliente
 * @author derick
 *
 */
@Stateless
@LocalBean
public class ManagerCliente {
	@EJB
	private ManagerDAO mDAO;
	
	public ManagerCliente() {
		
	}
	
	//Persona
	public List<CliPersona> findAllPersonas(){
		return mDAO.findAll(CliPersona.class);
	}
	
	public CliPersona findByIdPersona(String cedulaCliPersona) throws Exception {
		CliPersona cliente = (CliPersona) mDAO.findById(CliPersona.class, cedulaCliPersona);
		if(cliente==null) {
			cliente = new CliPersona();
		}
		return cliente;
	}
	
	public boolean verificarClienteFactura(CliPersona clienteFact) {
		for(CliPersona c : findAllPersonas()) {
			if(c.getCedulaCliPersona().equals(clienteFact.getCedulaCliPersona())) {
				return true;
			}
		}
		return false;	
	}
	
	public void insertarPersona(CliPersona nuevoCliente) throws Exception{
		mDAO.insertar(nuevoCliente);
	}
	
	public void eliminarPersona(String cedulaCliPersona) throws Exception{
		CliPersona cliente = (CliPersona) mDAO.findById(CliPersona.class, cedulaCliPersona);
		if(cliente.getRelacClientes().size()>0 || cliente.getFactCabeceras().size()>0 || cliente.getPedOrdens().size()>0) {
			throw new Exception("No se puede eliminar porque existen relaciones con este cliente");
		}else {
			mDAO.eliminar(CliPersona.class, cedulaCliPersona);
		}
	}
	
	public void actualizarPersona(CliPersona edicioncliente) throws Exception {
		CliPersona cliente = (CliPersona) mDAO.findById(CliPersona.class, edicioncliente.getCedulaCliPersona());
		cliente.setApellidosPersona(edicioncliente.getApellidosPersona());
		cliente.setNombresPersona(edicioncliente.getNombresPersona());
		cliente.setEmailPersona(edicioncliente.getEmailPersona());
		cliente.setTelefonoPersona(edicioncliente.getTelefonoPersona());
		cliente.setDireccionPersona(edicioncliente.getDireccionPersona());
		mDAO.actualizar(cliente);
	}
	

}
