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

import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;

@Service
@Scope("singleton")
public class CuentasLogica implements ICuentasLogica {

	private static final Logger log = LoggerFactory.getLogger(CuentasLogica.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	private void validador(Cuentas entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Cuentas>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Cuentas> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
		}
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void grabar(Cuentas cuentas) throws Exception {
		
		validador(cuentas);
		
		if (cuentas.getClientes() == null) {
			throw new Exception("El cliente de la cuenta es obligatorio");
		}
		
		Clientes clientes = clienteLogica.consultarPorId(cuentas.getClientes().getCliId());
		if (clientes == null) {
			throw new Exception("El cliente no existe");
		}
		
		cuentas.setClientes(clientes);
		
		cuentasDAO.grabar(cuentas);
		
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Cuentas cuentas) throws Exception {
		
		validador(cuentas);
		
		if (cuentas.getClientes() == null) {
			throw new Exception("El cliente de la cuenta es obligatorio");
		}
		
		Clientes clientes = clienteLogica.consultarPorId(cuentas.getClientes().getCliId());
		if (clientes == null) {
			throw new Exception("El cliente no existe");
		}
		
		cuentas.setClientes(clientes);
		
		cuentasDAO.modificar(cuentas);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Cuentas cuentas) throws Exception {
		Cuentas entity = cuentasDAO.consultarPorId(cuentas.getCueNumero());
		
		if (entity == null) {
			throw new Exception("La cuenta que desea eliminar no existe");
		}
		
		cuentasDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Cuentas consultarPorId(String id) throws Exception {
		return cuentasDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cuentas> consultarTodos() throws Exception {
		return cuentasDAO.consultarTodos();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Cuentas> consultarCuentasPorCliente(Long idCliente, String cueActiva) throws Exception {
		return cuentasDAO.consultarCuentasPorCliente(idCliente, cueActiva);
	}

}
