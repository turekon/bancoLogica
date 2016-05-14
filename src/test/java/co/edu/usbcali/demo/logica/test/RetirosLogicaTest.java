package co.edu.usbcali.demo.logica.test;

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

import co.edu.usbcali.demo.logica.ICuentasLogica;
import co.edu.usbcali.demo.logica.IRetirosLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class RetirosLogicaTest {

	private static final Logger log = LoggerFactory.getLogger(RetirosLogicaTest.class);
	
	@Autowired
	private IRetirosLogica retirosLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	private Long retirosID = 99L;
	private String cuentaNumero = "4008-5305-0020";
	private Long usuarioCedula = 10L;
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception {
		Retiros retiros = new Retiros();
		retiros.setCuentas(cuentasLogica.consultarPorId(this.cuentaNumero));
		retiros.setId(new RetirosId(this.retirosID, this.cuentaNumero));
		retiros.setRetDescripcion("Retiro de prueba JUnit");
		retiros.setRetFecha(new Date());
		retiros.setRetValor(new BigDecimal(240000));
		retiros.setUsuarios(usuarioLogica.consultarPorId(this.usuarioCedula));
		
		retirosLogica.grabar(retiros);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		Retiros retiros = retirosLogica.consultarPorId(new RetirosId(this.retirosID,  this.cuentaNumero));
		assertNotNull("El retiro no existe", retiros);
		log.info(retiros.getRetDescripcion());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception {
		Retiros retiros = retirosLogica.consultarPorId(new RetirosId(this.retirosID,  this.cuentaNumero));
		assertNotNull("El retiro no existe", retiros);
		retiros.setRetDescripcion("Modificado con JUnit");
		retirosLogica.modificar(retiros);
	}

	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception {
		Retiros retiros = retirosLogica.consultarPorId(new RetirosId(this.retirosID,  this.cuentaNumero));
		assertNotNull("El retiro no existe", retiros);
		retirosLogica.borrar(retiros);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() throws Exception {
		List<Retiros> losRetiros = retirosLogica.consultarTodos();
		for (Retiros retiros : losRetiros) {
			log.info(retiros.getCuentas().getCueNumero() + " - " + retiros.getRetDescripcion() + " - " + retiros.getRetValor());
		}
	}

}
