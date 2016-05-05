package co.edu.usbcali.demo.logica.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.demo.logica.ITipoDocumentoLogica;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TipoDocumentoLogicaTest {
	
	private static final Logger log = LoggerFactory.getLogger(TipoDocumentoLogicaTest.class);
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	private Long tipoDocumentoId = 10L;

	@Test
	
	public void dTest() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(tipoDocumentoId);
		assertNotNull("El Tipo de documento no existe" , tiposDocumentos);
		log.info(tiposDocumentos.getTdocNombre());
	}

}
