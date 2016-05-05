package co.edu.usbcali.demo.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.dao.ITipoDocumentoDAO;
import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Service
@Scope("singleton")
public class TipoDocumentoLogica implements ITipoDocumentoLogica {

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Override
	public void grabar(TiposDocumentos tiposDocumentos) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificar(TiposDocumentos tiposDocumentos) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrar(TiposDocumentos tiposDocumentos) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly=true)
	public TiposDocumentos consultarPorId(Long id) throws Exception {		
		return tipoDocumentoDAO.consultarPorId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TiposDocumentos> consultarTodos() throws Exception {
		return tipoDocumentoDAO.consultarTodos();
	}

}
