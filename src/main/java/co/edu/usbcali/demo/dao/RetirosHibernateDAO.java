package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;

@Repository
@Scope("singleton")
public class RetirosHibernateDAO implements IRetirosDAO {

	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public void grabar(Retiros retiros) {
		sessionFactory.getCurrentSession().save(retiros);
	}

	@Override
	public void modificar(Retiros retiros) {
		sessionFactory.getCurrentSession().update(retiros);
	}

	@Override
	public void borrar(Retiros retiros) {
		sessionFactory.getCurrentSession().delete(retiros);
	}

	@Override
	public Retiros consultarPorId(RetirosId retirosId) {
		return sessionFactory.getCurrentSession().get(Retiros.class, retirosId);
	}

	@Override
	public List<Retiros> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Retiros.class).list();
	}

	@Override
	public Long consultarMaxConsecutivo() {
		return (Long) sessionFactory.getCurrentSession().createCriteria(Retiros.class).setProjection(Projections.max("id.retCodigo")).uniqueResult();
	}

	@Override
	public Long consultarMaxConsecutivo(String cueNumero) {
		return (Long) sessionFactory.getCurrentSession().createCriteria(Retiros.class).setProjection(Projections.max("id.retCodigo")).add(Restrictions.eq("id.cueNumero", cueNumero)).uniqueResult();
	}

}
