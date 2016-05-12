package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Consignaciones;
import co.edu.usbcali.demo.modelo.ConsignacionesId;

@Repository
@Scope("singleton")
public class ConsignacionesHibernateDAO implements IConsignacionesDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().save(consignaciones);
	}

	@Override
	public void modificar(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().update(consignaciones);
	}

	@Override
	public void borrar(Consignaciones consignaciones) {
		sessionFactory.getCurrentSession().delete(consignaciones);
	}

	@Override
	public Consignaciones consultarPorId(ConsignacionesId consignacionesId) {
		return sessionFactory.getCurrentSession().get(Consignaciones.class, consignacionesId);
	}

	@Override
	public List<Consignaciones> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Consignaciones.class).list();
	}

	@Override
	public Long consultarMaxConsecutivo() {
		return (Long) sessionFactory.getCurrentSession().createCriteria(Consignaciones.class).setProjection(Projections.max("id.conCodigo")).uniqueResult();
	}

	@Override
	public Long consultarMaxConsecutivo(String cueNumero) {
		return (Long) sessionFactory.getCurrentSession().createCriteria(Consignaciones.class).setProjection(Projections.max("id.conCodigo")).add(Restrictions.eq("id.cueNumero", cueNumero)).uniqueResult();
	}
	
	

}
