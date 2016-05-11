package co.edu.usbcali.demo.delegado;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.TiposDocumentos;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

public interface IDelegadoDeNegocio {

	public void grabarClientes(Clientes clientes) throws Exception;

	public void modificarClientes(Clientes clientes) throws Exception;

	public void borrarClientes(Clientes clientes) throws Exception;

	public Clientes consultarClientesPorId(long id) throws Exception;

	public List<Clientes> consultarTodosClientes() throws Exception;

	public void grabarTiposDocumentos(TiposDocumentos tiposDocumentos) throws Exception;

	public void modificarTiposDocumentos(TiposDocumentos tiposDocumentos) throws Exception;

	public void borrarTiposDocumentos(TiposDocumentos tiposDocumentos) throws Exception;

	public TiposDocumentos consultarTiposDocumentosPorId(Long id) throws Exception;

	public List<TiposDocumentos> consultarTodosTiposDocumentos() throws Exception;

	public void grabarUsuarios(Usuarios usuarios) throws Exception;

	public void modificarUsuarios(Usuarios usuarios) throws Exception;

	public void borrarUsuarios(Usuarios usuarios) throws Exception;

	public Usuarios consultarUsuariosPorId(long id) throws Exception;

	public List<Usuarios> consultarTodosUsuarios() throws Exception;

	public void grabarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception;

	public void modificarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception;

	public void borrarTiposUsuarios(TiposUsuarios tiposUsuarios) throws Exception;

	public TiposUsuarios consultarTiposUsuariosPorId(Long id) throws Exception;

	public List<TiposUsuarios> consultarTodosTiposUsuarios() throws Exception;

	public void grabarCuentas(Cuentas cuentas) throws Exception;

	public void modificarCuentas(Cuentas cuentas) throws Exception;

	public void borrarCuentas(Cuentas cuentas) throws Exception;

	public Cuentas consultarCuentasPorId(String id) throws Exception;

	public List<Cuentas> consultarTodosCuentas() throws Exception;

	public void grabarRetiros(Retiros retiros) throws Exception;

	public void modificarRetiros(Retiros retiros) throws Exception;

	public void borrarRetiros(Retiros retiros) throws Exception;

	public Retiros consultarRetirosPorId(RetirosId retirosId) throws Exception;

	public List<Retiros> consultarTodosRetiros() throws Exception;

	public void grabarConsignaciones(Consignaciones consignaciones) throws Exception;

	public void modificarConsignaciones(Consignaciones consignaciones) throws Exception;

	public void borrarConsignaciones(Consignaciones consignaciones) throws Exception;

	public Consignaciones consultarConsignacionesPorId(ConsignacionesId consignacionesId) throws Exception;

	public List<Consignaciones> consultarTodosConsignaciones() throws Exception;
	
	public List<Cuentas> consultarCuentasPorCliente(Long idCliente) throws Exception;
	
	public List<Usuarios> consultarUsuariosPorTipoUsuario(Long tusuCodigo) throws Exception;
}
