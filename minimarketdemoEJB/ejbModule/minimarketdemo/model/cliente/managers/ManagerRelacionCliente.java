package minimarketdemo.model.cliente.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.CliPersona;
import minimarketdemo.model.core.entities.RelacCliente;
import minimarketdemo.model.core.entities.RelacMedio;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerRelacionCliente
 */
@Stateless
@LocalBean
public class ManagerRelacionCliente {

	@EJB
	private ManagerDAO mDAO;
	
    public ManagerRelacionCliente() {
        // TODO Auto-generated constructor stub
    }
    
    //MEDIO DE RELACION
    public List<RelacMedio> findAllRelacMedio(){
    	return mDAO.findAll(RelacMedio.class);
    }
    
    public RelacMedio findByIdRelacMedio(int idRelacMedio) throws Exception{
    	return (RelacMedio) mDAO.findById(RelacMedio.class, idRelacMedio);
    }
    
    public void insertarRelacMedio(RelacMedio nuevoMedio) throws Exception{
    	mDAO.insertar(nuevoMedio);
    }
    
    public void eliminarRelacMedio(int idRelacMedio) throws Exception {
    	RelacMedio medio=(RelacMedio)mDAO.findById(RelacMedio.class, idRelacMedio);
    	if(medio.getRelacClientes().size()>0)
    		throw new Exception("No se puede eliminar el Medio de Relacion porque existen Relaciones con este Medio");
    	mDAO.eliminar(RelacMedio.class, medio.getIdRelacMedio());
    }
    
    public void actualizarRelacMedio(RelacMedio edicionMedio) throws Exception {
    	RelacMedio medio=(RelacMedio) mDAO.findById(RelacMedio.class, edicionMedio.getIdRelacMedio());
    	medio.setNombreMedio(edicionMedio.getNombreMedio());
    	mDAO.actualizar(medio);
    }

    //RELACION CON CLIENTES
    
    public List<CliPersona> findAllCedulas(){
    	return mDAO.findAll(CliPersona.class);
    }
    
    public List<RelacCliente> findAllRelacCliente(){
    	return mDAO.findAll(RelacCliente.class);
    }
    
    public RelacCliente findByIdRelacCliente(int idRelacCliente) throws Exception{
    	return (RelacCliente) mDAO.findById(RelacCliente.class, idRelacCliente);
    }
    
    public void insertarRelacCliente(RelacCliente nuevaRelacion,String cedulaCli, int idRelacMedio) throws Exception {
    	CliPersona c=(CliPersona) mDAO.findById(CliPersona.class, cedulaCli);
    	RelacMedio r=(RelacMedio) mDAO.findById(RelacMedio.class, idRelacMedio);
    	nuevaRelacion.setCliPersona(c);
    	nuevaRelacion.setRelacMedio(r);
    	mDAO.insertar(nuevaRelacion);
    }
    
    public void eliminarRelacCliente(int idRelacCliente) throws Exception {
    	RelacCliente relacion=(RelacCliente)mDAO.findById(RelacCliente.class, idRelacCliente);
    	mDAO.eliminar(RelacCliente.class, relacion.getIdRelacCliente());
    }
    
    public void actualizarRelacCliente(RelacCliente edicionRelacion,String cedulaCli, int idRelacMedio) throws Exception {
    	RelacCliente relacion=(RelacCliente) mDAO.findById(RelacCliente.class, edicionRelacion.getIdRelacCliente());
    	CliPersona c=(CliPersona) mDAO.findById(CliPersona.class, cedulaCli);
    	RelacMedio r=(RelacMedio) mDAO.findById(RelacMedio.class, idRelacMedio);
    	relacion.setCliPersona(c);
    	relacion.setRelacMedio(r);
    	relacion.setDescripcionCliente(edicionRelacion.getDescripcionCliente());
    	mDAO.actualizar(relacion);
    }
    
    public int contarMedios() {
		int numeroMedios=0;
		List<RelacMedio> medio=mDAO.findAll(RelacMedio.class);
		for(RelacMedio med:medio) {
			numeroMedios=numeroMedios+1;
		}
		return numeroMedios;
	}

    public int contarRelacion() {
		int numeroRelacion=0;
		List<RelacCliente> relacion=mDAO.findAll(RelacCliente.class);
		for(RelacCliente relac:relacion) {
			numeroRelacion=numeroRelacion+1;
		}
		return numeroRelacion;
	}
}
