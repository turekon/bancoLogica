package co.edu.usbcali.demo.delegado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.ITipoDocumentoLogica;
import co.edu.usbcali.demo.logica.ITipoUsuarioLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.logica.UsuarioLogica;
import co.edu.usbcali.demo.modelo.Clientes;
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

}
