package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;

public interface IClienteDAO {

	public void grabar(Clientes clientes);
	public void modificar(Clientes clientes);
	public void borrar(Clientes clientes);
	public Clientes consultarPorId(long id);
	public List<Clientes> consultarTodos();
	
}
