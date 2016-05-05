package co.edu.usbcali.demo.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.IClienteDAO;
import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.logica.test.TipoDocumentoLogicaTest;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class ClienteLogica implements IClienteLogica {

	private static final Logger log = LoggerFactory.getLogger(TipoDocumentoLogicaTest.class);
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Autowired
	private Validator validator;
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void grabar(Clientes clientes) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Clientes>> constraintViolations = validator.validate(clientes);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Clientes> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
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
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Clientes>> constraintViolations = validator.validate(clientes);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Clientes> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
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
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Clientes>> constraintViolations = validator.validate(clientes);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Clientes> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
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
