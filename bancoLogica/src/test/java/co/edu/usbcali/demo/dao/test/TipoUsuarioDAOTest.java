package co.edu.usbcali.demo.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoUsuarioDAO;
import co.edu.usbcali.demo.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TipoUsuarioDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(TipoDocumentoDAOTest.class);
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	private long idTipoUsuario = 99L;

	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		TiposUsuarios tiposUsuarios = new TiposUsuarios();
		tiposUsuarios.setTusuCodigo(this.idTipoUsuario);
		tiposUsuarios.setTusuNombre("TIPO USUARIO JUNIT");
		
		tipoUsuarioDAO.grabar(tiposUsuarios);
	}
	
	@Test
	@Transactional(readOnly=false)
	public void bTest(){
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(this.idTipoUsuario);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		log.info(tiposUsuarios.getTusuCodigo() + " - " + tiposUsuarios.getTusuNombre());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest(){
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(this.idTipoUsuario);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		tiposUsuarios.setTusuNombre("tipo usuario Junit");
		tipoUsuarioDAO.modificar(tiposUsuarios);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest(){
		TiposUsuarios tiposUsuarios = tipoUsuarioDAO.consultarPorId(this.idTipoUsuario);
		assertNotNull("El tipo de usuario no existe", tiposUsuarios);
		
		tipoUsuarioDAO.borrar(tiposUsuarios);
	}
	
	@Test
	@Transactional(readOnly=false)
	public void eTest(){
		List<TiposUsuarios> losTipoUsuario = tipoUsuarioDAO.consultarTodos();
		for (TiposUsuarios tiposUsuarios : losTipoUsuario) {
			log.info(tiposUsuarios.getTusuCodigo() + " - " + tiposUsuarios.getTusuNombre());
		}
	}
	
	

}
