package co.edu.usbcali.demo.logica;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.usbcali.demo.dao.IRetirosDAO;
import co.edu.usbcali.demo.dto.OperacionesDTO;
import co.edu.usbcali.demo.modelo.Clientes;
import co.edu.usbcali.demo.modelo.Cuentas;
import co.edu.usbcali.demo.modelo.Retiros;
import co.edu.usbcali.demo.modelo.RetirosId;
import co.edu.usbcali.demo.modelo.Usuarios;

@Service
@Scope("singleton")
public class RetirosLogica implements IRetirosLogica {

	private static final Logger log = LoggerFactory.getLogger(RetirosLogica.class);
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private IRetirosDAO retirosDAO;
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired 
	private IClienteLogica clientesLogica;
	
	private void validador(Retiros entity) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		
		Set<ConstraintViolation<Retiros>> constraintViolations = validator.validate(entity);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Retiros> constraintViolation : constraintViolations) {
				log.error(constraintViolation.getPropertyPath().toString());
				log.error(constraintViolation.getMessage());
				stringBuilder.append(constraintViolation.getPropertyPath().toString());
				stringBuilder.append("-");
				stringBuilder.append(constraintViolation.getMessage());
				stringBuilder.append(",");
			}
			throw new Exception(stringBuilder.toString()); 
		}
	}
	
	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void grabar(Retiros retiros) throws Exception {
		
		validador(retiros);
		
		if (retiros.getUsuarios() == null) {
			throw new Exception("El usuario del retiro es obligatorio");
		}
		
		Usuarios usuarios = usuarioLogica.consultarPorId(retiros.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("El usuario no existe");
		}
		
		if (retiros.getCuentas() == null) {
			throw new Exception("La cuenta del retiro es obligatorio");
		}
		
		Cuentas cuentas = cuentasLogica.consultarPorId(retiros.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("La cuenta no existe");
		}
		
		if (cuentas.getCueActiva().trim().equals("N")) {
			throw new Exception("La cuenta no se encuentra activa");
		}
		
		if (cuentas.getCueSaldo().compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("La cuenta tiene saldo cero");
		}
		
		if (cuentas.getCueSaldo().compareTo(retiros.getRetValor()) < 0) {
			throw new Exception("El saldo de la cuenta es menor al valor del retiro");
		}
		
		retiros.setCuentas(cuentas);
		retiros.setUsuarios(usuarios);
		
		retirosDAO.grabar(retiros);
		
		Long saldoActual = cuentas.getCueSaldo().longValue();
		saldoActual = saldoActual - retiros.getRetValor().longValue();
		
		cuentas.setCueSaldo(new BigDecimal(saldoActual));
		
		cuentasLogica.modificar(cuentas);
		
		//notificar a la cola de mensajería
		this.reportarRetiro(retiros);
		
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void modificar(Retiros retiros) throws Exception {
		
		validador(retiros);
		
		if (retiros.getCuentas() == null) {
			throw new Exception("La cuenta del retiro es obligatorio");
		}
		
		Cuentas cuentas = cuentasLogica.consultarPorId(retiros.getCuentas().getCueNumero());
		if (cuentas == null) {
			throw new Exception("La cuenta no existe");
		}
		
		if (retiros.getUsuarios() == null) {
			throw new Exception("El usuario del retiro es obligatorio");
		}
		
		Usuarios usuarios = usuarioLogica.consultarPorId(retiros.getUsuarios().getUsuCedula());
		if (usuarios == null) {
			throw new Exception("El usuario no existe");
		}
		
		retiros.setCuentas(cuentas);
		retiros.setUsuarios(usuarios);
		
		retirosDAO.modificar(retiros);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(Retiros retiros) throws Exception {
		Retiros entity = retirosDAO.consultarPorId(retiros.getId());
		
		if (entity == null) {
			throw new Exception("El retiro que desea eliminar no existe");
		}
		
		retirosDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public Retiros consultarPorId(RetirosId retirosId) throws Exception {
		return retirosDAO.consultarPorId(retirosId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Retiros> consultarTodos() throws Exception {
		return retirosDAO.consultarTodos();
	}

	@Override
	@Transactional(readOnly=true)
	public Long consultarMaxConsecutivo() throws Exception {
		return retirosDAO.consultarMaxConsecutivo();
	}

	@Override
	@Transactional(readOnly=true)
	public Long consultarMaxConsecutivo(String cueNumero) throws Exception {
		return retirosDAO.consultarMaxConsecutivo(cueNumero);
	}

	@Override
	public void reportarRetiro(Retiros retiros) throws Exception {
		// armar objeto retirosDTO		
		Clientes clientes = clientesLogica.consultarPorId(retiros.getCuentas().getClientes().getCliId());
		
		OperacionesDTO retirosDTO = new OperacionesDTO();
		retirosDTO.setCajeroIdentificacion(retiros.getUsuarios().getUsuCedula());
		retirosDTO.setCajeroLogin(retiros.getUsuarios().getUsuLogin());
		retirosDTO.setCajeroNombre(retiros.getUsuarios().getUsuNombre());
		retirosDTO.setClienteIdentificacion(clientes.getCliId());
		retirosDTO.setClienteNombre(clientes.getCliNombre());
		retirosDTO.setCodigoOperacion(retiros.getId().getRetCodigo());
		retirosDTO.setCuentaNumero(retiros.getCuentas().getCueNumero());
		retirosDTO.setCuentaSaldo(retiros.getCuentas().getCueSaldo());
		retirosDTO.setDescripcionOperacion(retiros.getRetDescripcion());
		retirosDTO.setFechaOperacion(retiros.getRetFecha());
		retirosDTO.setTipoOperacion("retiro");
		retirosDTO.setValorOperacion(retiros.getRetValor());
		
		//convertir a Json.
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonInString = objectMapper.writeValueAsString(retirosDTO);
		log.info(jsonInString);
		
		//reportar a la cola de mensajería.
		try {
			SqsOperaciones sqsOperaciones = new SqsOperaciones();
			sqsOperaciones.enviarMensajeALaCola(jsonInString);
        } catch (Exception ace) {
            log.info("Error Message: " + ace.getMessage());
        }
	}

}
