package datos;

import java.sql.Time;
import java.util.Date;

public class PSParameter {
	
	public enum Types{
		INT
		,STRING
		,DATE
		,BOOLEAN, TIME
		
	}
	private Types tipo;
	private Object parametro;

	public PSParameter(Object parametro,Types tipo) {
		this.parametro=parametro;
		this.tipo=tipo;
	}
	
	public PSParameter(String parametro) {
		this.parametro=parametro;
		this.tipo=Types.STRING;
	}
	
	public PSParameter(int parametro) {
		this.parametro=parametro;
		this.tipo=Types.INT;
	}
	
	public PSParameter(Date parametro) {
		
		this.parametro=new java.sql.Date(parametro.getTime());
		this.tipo=Types.DATE;
	}

	public PSParameter(Time parametro) {
		
		this.parametro=parametro;
		this.tipo=Types.TIME;
	}
	
	public PSParameter(boolean parametro) {
		this.parametro=parametro;
		this.tipo=Types.BOOLEAN;
	}
	
	
	public Types getTipo() {
		return tipo;
	}
	
	public Object getParametro() {
		return parametro;
	}
}