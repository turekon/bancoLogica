package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
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

import co.edu.usbcali.demo.dao.IConsignacionesDAO;
import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class ConsignacionesLogica implements IConsignacionesLogica {

	private static final Logger log = LoggerFactory.getLogger(ConsignacionesLogica.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private IConsignacionesDAO consignacionesDAO;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	
	private void validador(Consignaciones entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Consignaciones>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Consignaciones> constraintViolation : constraintViolations) {
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
	public void grabar(Consignaciones consignaciones) throws Exception {
		validador(consignaciones);
		
		if (consignaciones.getUsuarios() == null) {
			throw new Exception("El usuario de la consignacion es obligatorio");
		}
		
		if (consignaciones.getCuentas() == null) {
			throw new Exception("La cuenta de la consignacion es requerida");
		}
		
		Usuarios usuarios = usuarioLogica.consultarPorId(consignaciones.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("El usuario no existe");
		}
		
		Cuentas cuentas = cuentasLogica.consultarPorId(consignaciones.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("La cuenta no existe");
		}
		
		if (cuentas.getCueActiva().trim().equals("N")) {
			throw new Exception("La cuenta se encuentra inactiva");
		}
		
		consignaciones.setUsuarios(usuarios);
		consignaciones.setCuentas(cuentas);
		
		consignacionesDAO.grabar(consignaciones);
		
		Long saldoActual = cuentas.getCueSaldo().longValue();
		saldoActual = saldoActual + consignaciones.getConValor().longValue();
		
		cuentas.setCueSaldo(new BigDecimal(saldoActual));
		
		cuentasLogica.modificar(cuentas);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Consignaciones consignaciones) throws Exception {
		validador(consignaciones);
		
		if (consignaciones.getUsuarios() == null) {
			throw new Exception("El usuario de la consignacion es obligatorio");
		}
		
		if (consignaciones.getCuentas() == null) {
			throw new Exception("La cuenta de la consignacion es requerida");
		}
		
		Usuarios usuarios = usuarioLogica.consultarPorId(consignaciones.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("El usuario no existe");
		}
		
		Cuentas cuentas = cuentasLogica.consultarPorId(consignaciones.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("La cuenta no existe");
		}
		
		consignaciones.setUsuarios(usuarios);
		consignaciones.setCuentas(cuentas);
		
		consignacionesDAO.modificar(consignaciones);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Consignaciones consignaciones) throws Exception {
		Consignaciones entity = consignacionesDAO.consultarPorId(consignaciones.getId());
		
		if (entity == null) 
		{
			throw new Exception("La consignación que desea eliminar no existe.");
		}
		
		consignacionesDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Consignaciones consultarPorId(ConsignacionesId consignacionesId) throws Exception {
		return consignacionesDAO.consultarPorId(consignacionesId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Consignaciones> consultarTodos() throws Exception {
		return consignacionesDAO.consultarTodos();
	}

	@Override
	@Transactional(readOnly=true)
	public Long consultarMaxConsecutivo() throws Exception {
		return consignacionesDAO.consultarMaxConsecutivo();
	}

	@Override
	@Transactional(readOnly=true)
	public Long consultarMaxConsecutivo(String cueNumero) throws Exception {
		return consignacionesDAO.consultarMaxConsecutivo(cueNumero);
	}

}
