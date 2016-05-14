package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;

public interface ICuentasLogica {
	public void grabar(Cuentas cuentas) throws Exception;
	public void modificar(Cuentas cuentas) throws Exception;
	public void borrar(Cuentas cuentas) throws Exception;
	public Cuentas consultarPorId(String id) throws Exception;
	public List<Cuentas> consultarTodos() throws Exception;
	public List<Cuentas> consultarCuentasPorCliente(Long idCliente, String cueActiva) throws Exception;
}
