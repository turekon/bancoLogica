package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Cuentas;

@Repository
@Scope("singleton")
public class CuentasHibernateDAO implements ICuentasDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void grabar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().save(cuentas);
	}

	@Override
	public void modificar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().update(cuentas);
	}

	@Override
	public void borrar(Cuentas cuentas) {
		sessionFactory.getCurrentSession().delete(cuentas);
	}

	@Override
	public Cuentas consultarPorId(String id) {
		return sessionFactory.getCurrentSession().get(Cuentas.class, id);
	}

	@Override
	public List<Cuentas> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Cuentas.class).list();
	}
	
	@Override
	public List<Cuentas> consultarCuentasPorCliente(Long idCliente, String cueActiva) {
		return sessionFactory.getCurrentSession().createCriteria(Cuentas.class)
				.add(Restrictions.eq("clientes.cliId", idCliente))
				.add(Restrictions.eq("cueActiva", cueActiva))
				.list();
	}

}
