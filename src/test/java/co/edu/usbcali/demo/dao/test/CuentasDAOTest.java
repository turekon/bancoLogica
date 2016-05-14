package co.edu.usbcali.demo.dao.test;

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

import co.edu.usbcali.demo.dao.IClienteDAO;
import co.edu.usbcali.demo.dao.ICuentasDAO;
import co.edu.usbcali.demo.modelo.Cuentas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class CuentasDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(CuentasDAOTest.class);
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	private String idCuenta = "9999-9999-9999";
	
	private Long idCliente = 101234L;

	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		Cuentas cuentas = new Cuentas();
		cuentas.setClientes(clienteDAO.consultarPorId(this.idCliente));
		cuentas.setCueActiva("S");
		cuentas.setCueClave("9999");
		cuentas.setCueNumero(this.idCuenta);
		cuentas.setCueSaldo(new BigDecimal("1000000"));
		
		cuentasDAO.grabar(cuentas);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest(){
		Cuentas cuentas = cuentasDAO.consultarPorId(this.idCuenta);
		assertNotNull("La cuenta no existe", cuentas);
		log.info(cuentas.getCueNumero() + " - " + cuentas.getCueSaldo());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest(){
		Cuentas cuentas = cuentasDAO.consultarPorId(this.idCuenta);
		assertNotNull("La cuenta no existe", cuentas);
		cuentas.setCueSaldo(new BigDecimal("199999"));
		cuentasDAO.modificar(cuentas);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest(){
		Cuentas cuentas = cuentasDAO.consultarPorId(this.idCuenta);
		assertNotNull("La cuenta no existe", cuentas);
		cuentasDAO.borrar(cuentas);
	}
	
	@Test
	@Transactional(readOnly=true)
	public void eTest(){
		List<Cuentas> lasCuentas = cuentasDAO.consultarTodos();
		for (Cuentas cuentas : lasCuentas) {
			log.info(cuentas.getCueNumero() + " - " + cuentas.getCueSaldo());
		}
	}
	
	@Test
	@Transactional(readOnly=true)
	public void fTest(){
		List<Cuentas> lasCuentas = cuentasDAO.consultarCuentasPorCliente(this.idCliente, "S");
		for (Cuentas cuentas : lasCuentas) {
			log.info(cuentas.getClientes().getCliId() + " - " + cuentas.getClientes().getCliNombre() + " - " + cuentas.getCueNumero() + " - " + cuentas.getCueSaldo());
		}
	}
	
	

}
