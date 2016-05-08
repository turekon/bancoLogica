package co.edu.usbcali.demo.delegado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.ITipoDocumentoLogica;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Component("delegadoDeNegocio")
@Scope("singleton")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {

	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
 	
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

}
