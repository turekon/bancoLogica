package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.assertNotNull;

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

import co.edu.usbcali.demo.logica.IConsignacionesLogica;
import co.edu.usbcali.demo.logica.ICuentasLogica;
import co.edu.usbcali.demo.logica.IUsuarioLogica;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ConsignacionesLogicaTest {

	private static final Logger log = LoggerFactory.getLogger(ConsignacionesLogicaTest.class);
	
	@Autowired
	private IConsignacionesLogica consignacionesLogica;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	private Long consignacionID = 99L; 
	private String cuentaNumero = "4008-5305-0020";
	private Long usuarioCedula = 10L;
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception {
		Consignaciones consignaciones = new Consignaciones();
		consignaciones.setConDescripcion("Consignacion de prueba");
		consignaciones.setConFecha(new Date());
		consignaciones.setConValor(new BigDecimal("240000.00"));
		consignaciones.setCuentas(cuentasLogica.consultarPorId(this.cuentaNumero));
		consignaciones.setId(new ConsignacionesId(this.consignacionID, this.cuentaNumero));
		consignaciones.setUsuarios(usuarioLogica.consultarPorId(this.usuarioCedula));
		
		consignacionesLogica.grabar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		Consignaciones consignaciones = consignacionesLogica.consultarPorId(new ConsignacionesId(this.consignacionID, this.cuentaNumero));
		assertNotNull("La consignación no existe", consignaciones);
		log.info(consignaciones.getConDescripcion());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception {
		Consignaciones consignaciones = consignacionesLogica.consultarPorId(new ConsignacionesId(this.consignacionID, this.cuentaNumero));
		assertNotNull("La consignación no existe", consignaciones);
		consignaciones.setConDescripcion("Consignacion modificada JUnit ");
		consignacionesLogica.modificar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception {
		Consignaciones consignaciones = consignacionesLogica.consultarPorId(new ConsignacionesId(this.consignacionID, this.cuentaNumero));
		assertNotNull("La consignación no existe", consignaciones);
		consignacionesLogica.borrar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() throws Exception {
		List<Consignaciones> lasConsignaciones = consignacionesLogica.consultarTodos();
		for (Consignaciones consignaciones : lasConsignaciones) {
			log.info(consignaciones.getCuentas().getCueNumero() + " - " + consignaciones.getConDescripcion() + " - " + consignaciones.getConValor());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void fTest() throws Exception {
		log.info("El consecutivo global es: " + consignacionesLogica.consultarMaxConsecutivo().toString());
	}
	
	@Test
	@Transactional(readOnly=true)
	public void gTest() throws Exception {
		log.info("El consecutivo para la cuenta " + this.cuentaNumero + " es : " + consignacionesLogica.consultarMaxConsecutivo(this.cuentaNumero).toString());
		log.info("El consecutivo para la cuenta 4008-5305-0080 es : " + consignacionesLogica.consultarMaxConsecutivo("4008-5305-0080").toString());
		log.info("El consecutivo para la cuenta 4008-5305-0015 es : " + consignacionesLogica.consultarMaxConsecutivo("4008-5305-0015").toString());
	}

}
