package minimarketdemo.controller.venta;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.core.entities.FactDetalle;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.entities.VentRegistro;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;
import minimarketdemo.model.venta.managers.ManagerVenta;

@Named
@SessionScoped
public class BeanVenta implements Serializable {
	@EJB
	private ManagerVenta managerVenta;
	@EJB
	private ManagerSeguridades mSeguridades;
	private VentRegistro nuevoVenta;
	private List<VentRegistro> listaVenta;
	private List<ThmEmpleado> listaEmpleados;
	private List<SegUsuario> listaUsuarios;
	private List<FactDetalle> listafactdetalle;
	private int idSegUsuario;
	private int idFactDetalle;

	public BeanVenta() {

	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanVentas inicializado...");
		nuevoVenta = new VentRegistro();
	}
	/// factdetalle utilizado:..../////
	public String actionCargarMenuVentas() {
		listaUsuarios=mSeguridades.findAllUsuarios();
		listafactdetalle =managerVenta.findAllFactDetalle();
		listaVenta = managerVenta.findAllVenta();
		return "ventas?faces-redirect=true";
	}

	public VentRegistro getNuevoVenta() {
		return nuevoVenta;
	}

	public void setNuevoVenta(VentRegistro nuevoVenta) {
		this.nuevoVenta = nuevoVenta;
	}

	public List<VentRegistro> getListaVenta() {
		return listaVenta;
	}

	public void setListaVenta(List<VentRegistro> listaVenta) {
		this.listaVenta = listaVenta;
	}

	public List<ThmEmpleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<ThmEmpleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public List<SegUsuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<SegUsuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public int getIdSegUsuario() {
		return idSegUsuario;
	}

	public void setIdSegUsuario(int idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}

	public int getIdFactDetalle() {
		return idFactDetalle;
	}

	public void setIdFactDetalle(int idFactDetalle) {
		this.idFactDetalle = idFactDetalle;
	}

	public List<FactDetalle> getListafactdetalle() {
		return listafactdetalle;
	}

	public void setListafactdetalle(List<FactDetalle> listafactdetalle) {
		this.listafactdetalle = listafactdetalle;
	}

}
