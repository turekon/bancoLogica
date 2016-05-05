package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IClienteDAO;
import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class ClienteLogica implements IClienteLogica {

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void grabar(Clientes clientes) throws Exception {
		if (clientes == null) {
			throw new Exception("El cliente es null");
		}
		
		if (clientes.getCliDireccion() == null || clientes.getCliDireccion().trim().equals("")) {
			throw new Exception("La dirección es obligatoria");
		}
		
		if (clientes.getCliId() == 0) {
			throw new Exception("El id es obligatorio");
		}
		
		if (clientes.getCliMail() == null || clientes.getCliMail().trim().equals("")) {
			throw new Exception("El email es obligatorio");
		}
		
		if (clientes.getCliNombre() == null || clientes.getCliNombre().trim().equals("")) {
			throw new Exception("El nombre es obligatorio");
		}
		
		if (clientes.getCliTelefono() == null || clientes.getCliTelefono().trim().equals("")) {
			throw new Exception("El telefono es obligatorio");
		}
		
		if (clientes.getTiposDocumentos() == null) {
			throw new Exception("El tipo de documento es obligatorio");
		}
		
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(clientes.getTiposDocumentos().getTdocCodigo());
		if (tiposDocumentos == null) {
			throw new Exception("El tipo de documento no existe");
		}
		
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clienteDAO.grabar(clientes);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Clientes clientes) throws Exception {
		if (clientes == null) {
			throw new Exception("El cliente es null");
		}
		
		Clientes entity = clienteDAO.consultarPorId(clientes.getCliId());
		
		if (entity == null) {
			throw new Exception("El cliente que desea modificar no existe");
		}
		
		if (clientes.getCliDireccion() == null || clientes.getCliDireccion().trim().equals("")) {
			throw new Exception("La dirección es obligatoria");
		}
		
		if (clientes.getCliId() == 0) {
			throw new Exception("El id es obligatorio");
		}
		
		if (clientes.getCliMail() == null || clientes.getCliMail().trim().equals("")) {
			throw new Exception("El email es obligatorio");
		}
		
		if (clientes.getCliNombre() == null || clientes.getCliNombre().trim().equals("")) {
			throw new Exception("El nombre es obligatorio");
		}
		
		if (clientes.getCliTelefono() == null || clientes.getCliTelefono().trim().equals("")) {
			throw new Exception("El telefono es obligatorio");
		}
		
		if (clientes.getTiposDocumentos() == null) {
			throw new Exception("El tipo de documento es obligatorio");
		}
		
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(clientes.getTiposDocumentos().getTdocCodigo());
		if (tiposDocumentos == null) {
			throw new Exception("El tipo de documento no existe");
		}
		
		clientes.setTiposDocumentos(tiposDocumentos);
		
		clienteDAO.modificar(clientes);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Clientes clientes) throws Exception {
		if (clientes == null) {
			throw new Exception("El cliente es null");
		}
		
		Clientes entity = clienteDAO.consultarPorId(clientes.getCliId());
		
		if (entity == null) {
			throw new Exception("El cliente que desea eliminar no existe");
		}
		
		clienteDAO.borrar(clientes);
	}

	@Override
	@Transactional(readOnly=true)
	public Clientes consultarPorId(long id) throws Exception {
		return clienteDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Clientes> consultarTodos() throws Exception {
		return clienteDAO.consultarTodos();
	}

}
