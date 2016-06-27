package co.edu.usbcali.demo.dao.test;

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

import co.edu.usbcali.demo.dao.IConsignacionesDAO;
import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.dao.IUsuarioDAO;
import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;
import co.edu.usbcali.demo.modelo.Cuentas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class ConsignacionesDAOTest {

	private static final Logger log = LoggerFactory.getLogger(ConsignacionesDAOTest.class);
	
	@Autowired
	private IConsignacionesDAO consignacionesDAO;
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	private Long idCon = 99L;
	private String idCuenta = "4008-5305-0010";
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		Consignaciones consignaciones = new Consignaciones();
		consignaciones.setConDescripcion("Descripción consignacion");
		consignaciones.setConFecha(new Date());
		consignaciones.setConValor(new BigDecimal(500000));
		
		Cuentas cuentas = cuentasDAO.consultarPorId(idCuenta);
		assertNotNull("La cuenta no existe para la consignación", cuentas);
		consignaciones.setCuentas(cuentas);
		consignaciones.setUsuarios(usuarioDAO.consultarPorId(10L));

		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		consignaciones.setId(consignacionesId);
		
		consignacionesDAO.grabar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() {
		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		Consignaciones consignaciones = consignacionesDAO.consultarPorId(consignacionesId);
		assertNotNull("La consignacion no existe", consignaciones);
		log.info(consignaciones.getConDescripcion() + " - " + consignaciones.getConValor());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		Consignaciones consignaciones = consignacionesDAO.consultarPorId(consignacionesId);
		assertNotNull("La consignacion no existe", consignaciones);
		consignaciones.setConValor(new BigDecimal(2000000));
		consignacionesDAO.modificar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		ConsignacionesId consignacionesId = new ConsignacionesId(this.idCon, this.idCuenta);
		Consignaciones consignaciones = consignacionesDAO.consultarPorId(consignacionesId);
		assertNotNull("La consignacion no existe", consignaciones);
		consignacionesDAO.borrar(consignaciones);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() {
		List<Consignaciones> lasConsignaciones = consignacionesDAO.consultarTodos();
		for (Consignaciones consignaciones : lasConsignaciones) {
			log.info(consignaciones.getId().getConCodigo() + " - " + consignaciones.getConDescripcion() + " - " + consignaciones.getConDescripcion());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void fTest() {
		log.info("El consecutivo global es: " + consignacionesDAO.consultarMaxConsecutivo().toString());
	}
	
	@Test
	@Transactional(readOnly=true)
	public void gTest() {
		log.info("El consecutivo para la cuenta " + this.idCuenta + " es : " + consignacionesDAO.consultarMaxConsecutivo(this.idCuenta).toString());
		log.info("El consecutivo para la cuenta 4008-5305-0080 es : " + consignacionesDAO.consultarMaxConsecutivo("4008-5305-0080").toString());
	}

}
