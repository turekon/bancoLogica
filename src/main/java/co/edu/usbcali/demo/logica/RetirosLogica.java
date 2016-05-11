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

import co.edu.usbcali.demo.dao.IRetirosDAO;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class RetirosLogica implements IRetirosLogica {

	private static final Logger log = LoggerFactory.getLogger(RetirosLogica.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private IRetirosDAO retirosDAO;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	private void validador(Retiros entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Retiros>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Retiros> constraintViolation : constraintViolations) {
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
	public void grabar(Retiros retiros) throws Exception {
		
		validador(retiros);
		
		if (retiros.getCuentas() == null) {
			throw new Exception("La cuenta del retiro es obligatorio");
		}
		
		Cuentas cuentas = cuentasLogica.consultarPorId(retiros.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("La cuenta no existe");
		}
		
		if (retiros.getUsuarios() == null) {
			throw new Exception("El usuario del retiro es obligatorio");
		}
		
		Usuarios usuarios = usuarioLogica.consultarPorId(retiros.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("El usuario no existe");
		}
		
		retiros.setCuentas(cuentas);
		retiros.setUsuarios(usuarios);
		
		retirosDAO.grabar(retiros);
		
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Retiros retiros) throws Exception {
		
		validador(retiros);
		
		if (retiros.getCuentas() == null) {
			throw new Exception("La cuenta del retiro es obligatorio");
		}
		
		Cuentas cuentas = cuentasLogica.consultarPorId(retiros.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("La cuenta no existe");
		}
		
		if (retiros.getUsuarios() == null) {
			throw new Exception("El usuario del retiro es obligatorio");
		}
		
		Usuarios usuarios = usuarioLogica.consultarPorId(retiros.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("El usuario no existe");
		}
		
		retiros.setCuentas(cuentas);
		retiros.setUsuarios(usuarios);
		
		retirosDAO.modificar(retiros);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Retiros retiros) throws Exception {
		Retiros entity = retirosDAO.consultarPorId(retiros.getId());
		
		if (entity == null) {
			throw new Exception("El retiro que desea eliminar no existe");
		}
		
		retirosDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Retiros consultarPorId(RetirosId retirosId) throws Exception {
		return retirosDAO.consultarPorId(retirosId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Retiros> consultarTodos() throws Exception {
		return retirosDAO.consultarTodos();
	}

}
