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

import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.logica.test.TipoDocumentoLogicaTest;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class TipoDocumentoLogica implements ITipoDocumentoLogica {

	private static final Logger log = LoggerFactory.getLogger(TipoDocumentoLogicaTest.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	private void validador(TiposDocumentos entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<TiposDocumentos>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<TiposDocumentos> constraintViolation : constraintViolations) {
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
	public void grabar(TiposDocumentos tiposDocumentos) throws Exception {
		validador(tiposDocumentos);
		
		tipoDocumentoDAO.grabar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(TiposDocumentos tiposDocumentos) throws Exception {
		validador(tiposDocumentos);
		
		tipoDocumentoDAO.modificar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(TiposDocumentos tiposDocumentos) throws Exception {
		validador(tiposDocumentos);
		
		tipoDocumentoDAO.borrar(tiposDocumentos);
	}

	@Override
	@Transactional(readOnly=true)
	public TiposDocumentos consultarPorId(Long id) throws Exception {		
		return tipoDocumentoDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposDocumentos> consultarTodos() throws Exception {
		return tipoDocumentoDAO.consultarTodos();
	}

}
