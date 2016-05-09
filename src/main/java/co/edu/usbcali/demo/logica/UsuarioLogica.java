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

import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class UsuarioLogica implements IUsuarioLogica {

	private static final Logger log = LoggerFactory.getLogger(UsuarioLogica.class);
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	@Autowired
	private Validator validator;
	
	private void validador(Usuarios entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Usuarios>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Usuarios> constraintViolation : constraintViolations) {
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
	public void grabar(Usuarios usuarios) throws Exception {
		
		validador(usuarios);
		
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(usuarios.getTiposUsuarios().getTusuCodigo());
		if (tiposUsuarios == null) {
			throw new Exception("El tipo de usuario no existe");
		}
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuarioDAO.grabar(usuarios);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Usuarios usuarios) throws Exception {
		
		validador(usuarios);
		
		if (usuarios.getTiposUsuarios() == null) {
			throw new Exception("El tipo de usuario es obligatorio");			
		}
		
		TiposUsuarios tiposUsuarios = tipoUsuarioLogica.consultarPorId(usuarios.getTiposUsuarios().getTusuCodigo());
		if (tiposUsuarios == null ){
			throw new Exception("El tipo de usuario es obligatorio");
		}
		
		usuarios.setTiposUsuarios(tiposUsuarios);
		
		usuarioDAO.modificar(usuarios);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Usuarios usuarios) throws Exception {
		
		Usuarios entity = usuarioDAO.consultarPorId(usuarios.getUsuCedula());
		
		if (entity == null) {
			throw new Exception("El Usuario que desea eliminar no existe");
		}
		
		usuarioDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuarios consultarPorId(long id) throws Exception {
		return usuarioDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuarios> consultarTodos() throws Exception {
		return usuarioDAO.consultarTodos();
	}

}
