package co.edu.usbcali.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.demo.modelo.Clientes;

@Repository
@Scope("singleton")
public class ClienteHibernateDAO implements IClienteDAO {

	@Autowired	
	private SessionFactory sessionFactory;
	
	@Override
	public void grabar(Clientes clientes) {
		sessionFactory.getCurrentSession().save(clientes);
	}

	@Override
	public void modificar(Clientes clientes) {
		sessionFactory.getCurrentSession().update(clientes);
	}

	@Override
	public void borrar(Clientes clientes) {
		sessionFactory.getCurrentSession().delete(clientes);
	}

	@Override
	public Clientes consultarPorId(long id) {
		return sessionFactory.getCurrentSession().get(Clientes.class, id);
	}

	@Override
	public List<Clientes> consultarTodos() {
		return sessionFactory.getCurrentSession().createCriteria(Clientes.class).list();
	}

}
