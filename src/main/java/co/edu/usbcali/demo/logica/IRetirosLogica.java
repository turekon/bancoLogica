package co.edu.usbcali.demo.logica;

import java.util.List;

import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;



public interface IRetirosLogica {
	public void grabar(Retiros retiros) throws Exception;
	public void modificar(Retiros retiros) throws Exception;
	public void borrar(Retiros retiros) throws Exception;
	public Retiros consultarPorId(RetirosId retirosId) throws Exception;
	public List<Retiros> consultarTodos() throws Exception;
	public Long consultarMaxConsecutivo() throws Exception;
	public Long consultarMaxConsecutivo(String cueNumero) throws Exception;
}
