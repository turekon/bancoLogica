package co.edu.usbcali.demo.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
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

import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.dao.IRetirosDAO;
import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class RetirosDAOTest {

	private static final Logger log = LoggerFactory.getLogger(RetirosDAOTest.class);
	
	@Autowired
	private IRetirosDAO retirosDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	private Long retCodigo = 99L;
	private String cueNumero = "4008-5305-0010"; 
			
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		Retiros retiros = new Retiros();
		retiros.setCuentas(cuentasDAO.consultarPorId(this.cueNumero));
		retiros.setId(new RetirosId(this.retCodigo, this.cueNumero));
		retiros.setRetDescripcion("Un retiro JUnit");
		retiros.setRetFecha(new Date());
		retiros.setRetValor(new BigDecimal(250000));
		retiros.setUsuarios(usuarioDAO.consultarPorId(10L));
		
		retirosDAO.grabar(retiros);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest(){
		RetirosId retirosId = new RetirosId(this.retCodigo, this.cueNumero);
		Retiros retiros = retirosDAO.consultarPorId(retirosId);
		assertNotNull("El retiro no existe", retiros);
		log.info(retiros.getId().getRetCodigo() + " - " + retiros.getRetDescripcion() + " - " + retiros.getRetValor());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest(){
		RetirosId retirosId = new RetirosId(this.retCodigo, this.cueNumero);
		Retiros retiros = retirosDAO.consultarPorId(retirosId);
		assertNotNull("El retiro no existe", retiros);
		retiros.setRetDescripcion("Descripcion modificada");
		retirosDAO.modificar(retiros);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest(){
		RetirosId retirosId = new RetirosId(this.retCodigo, this.cueNumero);
		Retiros retiros = retirosDAO.consultarPorId(retirosId);
		assertNotNull("El retiro no existe", retiros);
		retirosDAO.borrar(retiros);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest(){
		List<Retiros> losRetiros = retirosDAO.consultarTodos();
		for (Retiros retiros : losRetiros) {
			log.info(retiros.getId().getRetCodigo() + " - " + retiros.getRetDescripcion() + " - " + retiros.getRetValor());
		}
	}
	
	

}
