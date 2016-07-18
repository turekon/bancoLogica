package co.edu.usbcali.demo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OperacionesDTO {

	/*
	 * retiro o consignación
	 */
	private String tipoOperacion;
	private long CodigoOperacion;
	private BigDecimal ValorOperacion;
	private Date FechaOperacion;
	private String CajeroLogin;
	private long CajeroIdentificacion;
	private String CajeroNombre;
	private String DescripcionOperacion;
	private long clienteIdentificacion;
	private String clienteNombre;
	private String cuentaNumero;
	private BigDecimal cuentaSaldo;
	
	public OperacionesDTO() {
	
	}
	
	public OperacionesDTO(String tipoOperacion, long codigoOperacion, BigDecimal valorOperacion, Date fechaOperacion,
			String descripcionOperacion, long clienteIdentificacion, String clienteNombre, String cuentaNumero,
			BigDecimal cuentaSaldo) {
		super();
		this.tipoOperacion = tipoOperacion;
		CodigoOperacion = codigoOperacion;
		ValorOperacion = valorOperacion;
		FechaOperacion = fechaOperacion;
		DescripcionOperacion = descripcionOperacion;
		this.clienteIdentificacion = clienteIdentificacion;
		this.clienteNombre = clienteNombre;
		this.cuentaNumero = cuentaNumero;
		this.cuentaSaldo = cuentaSaldo;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public long getCodigoOperacion() {
		return CodigoOperacion;
	}

	public void setCodigoOperacion(long codigoOperacion) {
		CodigoOperacion = codigoOperacion;
	}

	public BigDecimal getValorOperacion() {
		return ValorOperacion;
	}

	public void setValorOperacion(BigDecimal valorOperacion) {
		ValorOperacion = valorOperacion;
	}

	public Date getFechaOperacion() {
		return FechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		FechaOperacion = fechaOperacion;
	}

	public String getCajeroLogin() {
		return CajeroLogin;
	}

	public void setCajeroLogin(String cajeroLogin) {
		CajeroLogin = cajeroLogin;
	}

	public long getCajeroIdentificacion() {
		return CajeroIdentificacion;
	}

	public void setCajeroIdentificacion(long cajeroIdentificacion) {
		CajeroIdentificacion = cajeroIdentificacion;
	}

	public String getCajeroNombre() {
		return CajeroNombre;
	}

	public void setCajeroNombre(String cajeroNombre) {
		CajeroNombre = cajeroNombre;
	}

	public String getDescripcionOperacion() {
		return DescripcionOperacion;
	}

	public void setDescripcionOperacion(String descripcionOperacion) {
		DescripcionOperacion = descripcionOperacion;
	}

	public long getClienteIdentificacion() {
		return clienteIdentificacion;
	}

	public void setClienteIdentificacion(long clienteIdentificacion) {
		this.clienteIdentificacion = clienteIdentificacion;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getCuentaNumero() {
		return cuentaNumero;
	}

	public void setCuentaNumero(String cuentaNumero) {
		this.cuentaNumero = cuentaNumero;
	}

	public BigDecimal getCuentaSaldo() {
		return cuentaSaldo;
	}

	public void setCuentaSaldo(BigDecimal cuentaSaldo) {
		this.cuentaSaldo = cuentaSaldo;
	}
	
}
