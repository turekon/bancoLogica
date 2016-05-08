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

import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
public class TipoDocumentoDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(TipoDocumentoDAOTest.class);
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	private long idTipoDocumento = 99L;

	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		TiposDocumentos tiposDocumentos = new TiposDocumentos();
		tiposDocumentos.setTdocCodigo(this.idTipoDocumento);
		tiposDocumentos.setTdocNombre("TIPO DOCUMENTO JUNIT");
		
		tipoDocumentoDAO.grabar(tiposDocumentos);
	}
	
	@Test
	@Transactional(readOnly=false)
	public void bTest(){
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(this.idTipoDocumento);
		assertNotNull("El tipo de documento no existe", tiposDocumentos);
		log.info(tiposDocumentos.getTdocCodigo() + " - " + tiposDocumentos.getTdocNombre());
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest(){
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(this.idTipoDocumento);
		assertNotNull("El tipo de usuario no existe", tiposDocumentos);
		tiposDocumentos.setTdocNombre("tipo usuario Junit");
		tipoDocumentoDAO.modificar(tiposDocumentos);
	}
	
	@Test
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest(){
		TiposDocumentos tiposDocumentos = tipoDocumentoDAO.consultarPorId(this.idTipoDocumento);
		assertNotNull("El tipo de usuario no existe", tiposDocumentos);
		
		tipoDocumentoDAO.borrar(tiposDocumentos);
	}
	
	@Test
	@Transactional(readOnly=false)
	public void eTest(){
		List<TiposDocumentos> lostipoDocumento = tipoDocumentoDAO.consultarTodos();
		for (TiposDocumentos tiposDocumentos : lostipoDocumento) {
			log.info(tiposDocumentos.getTdocCodigo() + " - " + tiposDocumentos.getTdocNombre());
		}
	}
	
	

}
