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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

//	@Test
//	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
//	public void aTest() throws Exception {
//		
//		
//		TiposDocumentos tiposDocumentos = new TiposDocumentos();
//		tiposDocumentos.setTdocCodigo(this.tipoDocumentoId);
//		tiposDocumentos.setTdocNombre("JUnit tipo");
//		
//		tipoDocumentoLogica.grabar(tiposDocumentos);
//	}
	
	@Test
	@Transactional(readOnly=true)
	public void bTest() throws Exception {
		TiposDocumentos tiposDocumentos = tipoDocumentoLogica.consultarPorId(tipoDocumentoId);
		assertNotNull("El Tipo de documento no existe" , tiposDocumentos);
		log.info(tiposDocumentos.getTdocNombre());
	}

}
