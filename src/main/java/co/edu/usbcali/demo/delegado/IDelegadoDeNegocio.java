package co.edu.usbcali.demo.delegado;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
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
}
