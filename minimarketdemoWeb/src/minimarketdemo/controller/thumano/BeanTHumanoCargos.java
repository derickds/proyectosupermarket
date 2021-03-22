package minimarketdemo.controller.thumano;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.entities.ThmRolCabecera;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;
import minimarketdemo.model.thumano.managers.ManagerTHumano;

@Named
@SessionScoped
public class BeanTHumanoCargos implements Serializable {
	@EJB
	private ManagerTHumano mTHumano;
	@EJB
	private ManagerSeguridades mSeguridades;
	private List<String> listaPeriodosRol;
	private List<ThmCargo> listaRolCargo;
	private String periodoRolSeleccionado;
	private ThmCargo nuevoCargo;
	private ThmCargo seleccionCargo;
	
	

	public BeanTHumanoCargos() {
		
	}
	public void actionSeleccionarCargo(int idCargo) {
		try {
			seleccionCargo=mTHumano.findByIdThmCargo(idCargo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String actionCargarMenuCargos() {
		listaRolCargo=mTHumano.findAllThmCargo();
		nuevoCargo=new ThmCargo();
		return "cargos?faces-redirect=true";
	}
	
	public void actionListenerInsertarCargo() {
		try {
			mTHumano.insertarThmCargo(nuevoCargo);
			listaRolCargo=mTHumano.findAllThmCargo();
			JSFUtil.crearMensajeINFO("Cargo registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerActualizarCargo() {
		try {
			mTHumano.editarThmCargo(seleccionCargo);
			listaRolCargo=mTHumano.findAllThmCargo();
			JSFUtil.crearMensajeINFO("Cargo actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarCargo(int idCargo) {
		try {
			mTHumano.eliminarCargo(idCargo);
			listaRolCargo=mTHumano.findAllThmCargo();
			JSFUtil.crearMensajeINFO("Cargo eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	public List<String> getListaPeriodosRol() {
		return listaPeriodosRol;
	}

	public void setListaPeriodosRol(List<String> listaPeriodosRol) {
		this.listaPeriodosRol = listaPeriodosRol;
	}

	

	public List<ThmCargo> getListaRolCargo() {
		return listaRolCargo;
	}

	public void setListaRolCargo(List<ThmCargo> listaRolCargo) {
		this.listaRolCargo = listaRolCargo;
	}

	public String getPeriodoRolSeleccionado() {
		return periodoRolSeleccionado;
	}

	public void setPeriodoRolSeleccionado(String periodoRolSeleccionado) {
		this.periodoRolSeleccionado = periodoRolSeleccionado;
	}
	public ThmCargo getNuevoCargo() {
		return nuevoCargo;
	}
	public void setNuevoCargo(ThmCargo nuevoCargo) {
		this.nuevoCargo = nuevoCargo;
	}
	public ThmCargo getSeleccionCargo() {
		return seleccionCargo;
	}
	public void setSeleccionCargo(ThmCargo seleccionCargo) {
		this.seleccionCargo = seleccionCargo;
	}
	
	

}
