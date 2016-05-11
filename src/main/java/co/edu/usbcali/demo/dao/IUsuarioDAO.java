package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.TiposUsuarios;
import co.edu.usbcali.demo.modelo.Usuarios;

public interface IUsuarioDAO {
	public void grabar(Usuarios usuarios);
	public void modificar(Usuarios usuarios);
	public void borrar(Usuarios usuarios);
	public Usuarios consultarPorId(long id);
	public List<Usuarios> consultarTodos();
	public List<Usuarios> consultarUsuariosPorTipoUsuario(Long tusuCodigo);
}
