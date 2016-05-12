package co.edu.usbcali.demo.dao;

import java.util.List;

import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;



public interface IRetirosDAO {
	public void grabar(Retiros retiros);
	public void modificar(Retiros retiros);
	public void borrar(Retiros retiros);
	public Retiros consultarPorId(RetirosId retirosId);
	public List<Retiros> consultarTodos();
	public Long consultarMaxConsecutivo();
	public Long consultarMaxConsecutivo(String cueNumero);
}
