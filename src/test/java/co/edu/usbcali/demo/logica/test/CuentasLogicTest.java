package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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

import co.edu.usbcali.demo.logica.IClienteLogica;
import co.edu.usbcali.demo.logica.ICuentasLogica;
import co.edu.usbcali.demo.modelo.Cuentas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class CuentasLogicTest {
	
	private static final Logger log = LoggerFactory.getLogger(CuentasLogicTest.class);

	@Autowired
	private ICuentasLogica cuentasLogica;
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	private String cuentaNumero = "9999-9999-9999";
	
	private Long idCliente = 101234L;
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void aTest() throws Exception {
		Cuentas cuentas = new Cuentas();
		cuentas.setClientes(clienteLogica.consultarPorId(this.idCliente));
		cuentas.setCueActiva("S");
		cuentas.setCueClave("1234");
		cuentas.setCueNumero(cuentaNumero);
		cuentas.setCueSaldo(new BigDecimal(240000));
		
		cuentasLogica.grabar(cuentas);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		Cuentas cuentas = cuentasLogica.consultarPorId(cuentaNumero);
		assertNotNull("La cuenta no existe", cuentas);
		log.info(cuentas.getCueNumero());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception {
		Cuentas cuentas = cuentasLogica.consultarPorId(cuentaNumero);
		assertNotNull("La cuenta no existe", cuentas);
		cuentas.setCueSaldo(new BigDecimal(450000));
		cuentasLogica.modificar(cuentas);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception {
		Cuentas cuentas = cuentasLogica.consultarPorId(cuentaNumero);
		assertNotNull("La cuenta no existe", cuentas);

		cuentasLogica.borrar(cuentas);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest() throws Exception {
		List<Cuentas> lasCuentas = cuentasLogica.consultarTodos();
		for (Cuentas cuentas : lasCuentas) {
			log.info(cuentas.getCueNumero() + " - " + cuentas.getCueSaldo());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void fTest() throws Exception {
		List<Cuentas> lasCuentas = cuentasLogica.consultarCuentasPorCliente(this.idCliente, "S");
		for (Cuentas cuentas : lasCuentas) {
			log.info(cuentas.getClientes().getCliId() + " - " + cuentas.getClientes().getCliNombre() + " - " + cuentas.getCueNumero() + " - " + cuentas.getCueSaldo());
		}
	}

}
