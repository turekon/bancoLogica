package co.edu.usbcali.demo.logica.test;

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

import co.edu.usbcali.demo.logica.ITipoUsuarioLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class UsuarioLogicaTest {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioLogicaTest.class);

	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	private Long idUsuario = 99999L; 
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception {
		Usuarios usuarios = new Usuarios();
		usuarios.setTiposUsuarios(tipoUsuarioLogica.consultarPorId(10L));
		usuarios.setUsuCedula(this.idUsuario);
		usuarios.setUsuClave("12345");
		usuarios.setUsuLogin("UsuarioJUnit");
		usuarios.setUsuNombre("Usuario creado con Junit");
		
		usuarioLogica.grabar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		Usuarios usuarios = usuarioLogica.consultarPorId(this.idUsuario);
		assertNotNull("El usuario no existe", usuarios);
		log.info(usuarios.getUsuNombre());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception {
		Usuarios usuarios = usuarioLogica.consultarPorId(this.idUsuario);
		assertNotNull("El usuario no existe", usuarios);
		usuarios.setUsuNombre("Nombre del usuario modificado");
		usuarioLogica.modificar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception {
		Usuarios usuarios = usuarioLogica.consultarPorId(this.idUsuario);
		assertNotNull("El usuario no existe", usuarios);
		usuarioLogica.borrar(usuarios);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() throws Exception {
		List<Usuarios> losUsuarios = usuarioLogica.consultarTodos();
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getUsuLogin() + " - " + usuarios.getUsuNombre());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void fTest() throws Exception {
		List<Usuarios> losUsuarios = usuarioLogica.consultarUsuariosPorTipoUsuario(10L);
		for (Usuarios usuarios : losUsuarios) {
			log.info(usuarios.getTiposUsuarios().getTusuNombre() + " - " + usuarios.getUsuLogin() + " - " + usuarios.getUsuNombre());
		}
	}

}
