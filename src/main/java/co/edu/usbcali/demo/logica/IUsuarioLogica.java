package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Usuarios;

public interface IUsuarioLogica {
	public void grabar(Usuarios usuarios) throws Exception;
	public void modificar(Usuarios usuarios) throws Exception;
	public void borrar(Usuarios usuarios) throws Exception;
	public Usuarios consultarPorId(long id) throws Exception;
	public List<Usuarios> consultarTodos() throws Exception;
	public List<Usuarios> consultarUsuariosPorTipoUsuario(Long tusuCodigo) throws Exception;
}
