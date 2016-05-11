package co.edu.usbcali.demo.delegado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.IConsignacionesLogica;
import co.edu.usbcali.demo.logica.ICuentasLogica;
import co.edu.usbcali.demo.logica.IRetirosLogica;
import co.edu.usbcali.demo.logica.ITipoDocumentoLogica;
import co.edu.usbcali.demo.logica.ITipoUsuarioLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.logica.UsuarioLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@Component("delegadoDeNegocio")
@Scope("singleton")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {

	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	@Autowired
	private IRetirosLogica retirosLogica;
	
	@Autowired
	private IConsignacionesLogica consignacionesLogica;
 	
	@Override
	public void grabarClientes(Clientes clientes) throws Exception {
		clienteLogica.grabar(clientes);
	}

	@Override
	public void modificarClientes(Clientes clientes) throws Exception {
		clienteLogica.modificar(clientes);
	}

	@Override
	public void borrarClientes(Clientes clientes) throws Exception {
		clienteLogica.borrar(clientes);
	}

	@Override
	public Clientes consultarClientesPorId(long id) throws Exception {
		return clienteLogica.consultarPorId(id);
	}

	@Override
	public List<Clientes> consultarTodosClientes() throws Exception {
		return clienteLogica.consultarTodos();
	}

	@Override
	public void grabarTiposDocumentos(TiposDocumentos tiposDocumentos) throws Exception {
		tipoDocumentoLogica.grabar(tiposDocumentos);
	}

	@Override
	public void modificarTiposDocumentos(TiposDocumentos tiposDocumentos) throws Exception {
		tipoDocumentoLogica.modificar(tiposDocumentos);
	}

	@Override
	public void borrarTiposDocumentos(TiposDocumentos tiposDocumentos) throws Exception {
		tipoDocumentoLogica.borrar(tiposDocumentos);
	}

	@Override
	public TiposDocumentos consultarTiposDocumentosPorId(Long id) throws Exception {
		return tipoDocumentoLogica.consultarPorId(id);
	}

	@Override
	public List<TiposDocumentos> consultarTodosTiposDocumentos() throws Exception {
		return tipoDocumentoLogica.consultarTodos();
	}

	@Override
	public void grabarUsuarios(Usuarios usuarios) throws Exception {
		usuarioLogica.grabar(usuarios);		
	}

	@Override
	public void modificarUsuarios(Usuarios usuarios) throws Exception {
		usuarioLogica.modificar(usuarios);
	}

	@Override
	public void borrarUsuarios(Usuarios usuarios) throws Exception {
		usuarioLogica.borrar(usuarios);
	}

	@Override
	public Usuarios consultarUsuariosPorId(long id) throws Exception {
		return usuarioLogica.consultarPorId(id);
	}

	@Override
	public List<Usuarios> consultarTodosUsuarios() throws Exception {
		return usuarioLogica.consultarTodos();
	}

	@Override
	public void grabarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tipoUsuarioLogica.grabar(tiposUsuarios);
	}

	@Override
	public void modificarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tipoUsuarioLogica.modificar(tiposUsuarios);
	}

	@Override
	public void borrarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception {
		tipoUsuarioLogica.borrar(tiposUsuarios);
	}

	@Override
	public TiposUsuarios consultarTiposUsuariosPorId(Long id) throws Exception {
		return tipoUsuarioLogica.consultarPorId(id);
	}

	@Override
	public List<TiposUsuarios> consultarTodosTiposUsuarios() throws Exception {
		return tipoUsuarioLogica.consultarTodos();
	}

	@Override
	public void grabarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.grabar(cuentas);
	}

	@Override
	public void modificarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.modificar(cuentas);
	}

	@Override
	public void borrarCuentas(Cuentas cuentas) throws Exception {
		cuentasLogica.borrar(cuentas);
	}

	@Override
	public Cuentas consultarCuentasPorId(String id) throws Exception {
		return cuentasLogica.consultarPorId(id);
	}

	@Override
	public List<Cuentas> consultarTodosCuentas() throws Exception {
		return cuentasLogica.consultarTodos();
	}

	@Override
	public void grabarRetiros(Retiros retiros) throws Exception {
		retirosLogica.grabar(retiros);
	}

	@Override
	public void modificarRetiros(Retiros retiros) throws Exception {
		retirosLogica.modificar(retiros);
	}

	@Override
	public void borrarRetiros(Retiros retiros) throws Exception {
		retirosLogica.borrar(retiros);
	}

	@Override
	public Retiros consultarRetirosPorId(RetirosId retirosId) throws Exception {
		return retirosLogica.consultarPorId(retirosId);
	}

	@Override
	public List<Retiros> consultarTodosRetiros() throws Exception {
		return retirosLogica.consultarTodos();
	}

	@Override
	public void grabarConsignaciones(Consignaciones consignaciones) throws Exception {
		consignacionesLogica.grabar(consignaciones);
	}

	@Override
	public void modificarConsignaciones(Consignaciones consignaciones) throws Exception {
		consignacionesLogica.modificar(consignaciones);
	}

	@Override
	public void borrarConsignaciones(Consignaciones consignaciones) throws Exception {
		consignacionesLogica.borrar(consignaciones);
	}

	@Override
	public Consignaciones consultarConsignacionesPorId(ConsignacionesId consignacionesId) throws Exception {
		return consignacionesLogica.consultarPorId(consignacionesId);
	}

	@Override
	public List<Consignaciones> consultarTodosConsignaciones() throws Exception {
		return consignacionesLogica.consultarTodos();
	}

	@Override
	public List<Cuentas> consultarCuentasPorCliente(Long idCliente) throws Exception {
		return cuentasLogica.consultarCuentasPorCliente(idCliente);
	}

	@Override
	public List<Usuarios> consultarUsuariosPorTipoUsuario(Long tusuCodigo) throws Exception {
		return usuarioLogica.consultarUsuariosPorTipoUsuario(tusuCodigo);
	}

}
