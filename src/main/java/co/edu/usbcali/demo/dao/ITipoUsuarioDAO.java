package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;

public interface ITipoUsuarioDAO {
	
	public void grabar(TiposUsuarios tiposUsuarios);
	public void modificar(TiposUsuarios tiposUsuarios);
	public void borrar(TiposUsuarios tiposUsuarios);
	public TiposUsuarios consultarPorId(Long id);
	public List<TiposUsuarios> consultarTodos();
	
}
