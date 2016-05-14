package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;

public interface ICuentasDAO {
	public void grabar(Cuentas cuentas);
	public void modificar(Cuentas cuentas);
	public void borrar(Cuentas cuentas);
	public Cuentas consultarPorId(String id);
	public List<Cuentas> consultarTodos();
	public List<Cuentas> consultarCuentasPorCliente(Long idCliente, String cueActiva);
}
