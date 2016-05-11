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
import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class UsuarioDAOTest {

	private static final Logger log = LoggerFactory.getLogger(UsuarioDAOTest.class);
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	private Long idUsuario = 99999L;
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		Usuarios usuarios = new Usuarios();
		usuarios.setTiposUsuarios(tipoUsuarioDAO.consultarPorId(10L));
		usuarios.setUsuCedula(this.idUsuario);
		usuarios.setUsuClave("12345");
		usuarios.setUsuLogin("UsuarioJUnit");
		usuarios.setUsuNombre("Usuario creado con Junit");
		
		usuarioDAO.grabar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() {
		Usuarios usuarios = usuarioDAO.consultarPorId(this.idUsuario);
		assertNotNull("El usuario no existe", usuarios);
		log.info(usuarios.getUsuNombre());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		Usuarios usuarios = usuarioDAO.consultarPorId(this.idUsuario);
		assertNotNull("El usuario no existe", usuarios);
		usuarios.setUsuNombre("Nombre del usuario modificado");
		usuarioDAO.modificar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		Usuarios usuarios = usuarioDAO.consultarPorId(this.idUsuario);
		assertNotNull("El usuario no existe", usuarios);
		usuarioDAO.borrar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() {
		List<Usuarios> losUsuarios = usuarioDAO.consultarTodos();
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getUsuLogin() + " - " + usuarios.getUsuNombre());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void fTest() {
		List<Usuarios> losUsuarios = usuarioDAO.consultarUsuariosPorTipoUsuario(10L);
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getTiposUsuarios().getTusuNombre() + " - " + usuarios.getUsuLogin() + " - " + usuarios.getUsuNombre());
		}
	}
}
