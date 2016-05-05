package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.TiposDocumentos;

@Repository
@Scope("singleton")
public class TipoDocumentoHibernateDAO implements ITipoDocumentoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().save(tiposDocumentos);
	}

	@Override
	public void modificar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().update(tiposDocumentos);
	}

	@Override
	public void borrar(TiposDocumentos tiposDocumentos) {
		sessionFactory.getCurrentSession().delete(tiposDocumentos);
	}

	@Override
	public TiposDocumentos consultarPorId(Long id) {
		return sessionFactory.getCurrentSession().get(TiposDocumentos.class, id);
	}

	@Override
	public List<TiposDocumentos> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(TiposDocumentos.class).list();
	}

}
