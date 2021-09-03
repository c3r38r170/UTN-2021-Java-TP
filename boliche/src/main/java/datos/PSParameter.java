package datos;

public class PSParameter {
	
	public enum Types{
		INT
		,STRING
	}
	private Types tipo;
	private Object parametro;
	
	public PSParameter(Object parametro,Types tipo) {
		this.parametro=parametro;
		this.tipo=tipo;
	}
	
	public Types getTipo() {
		return tipo;
	}
	
	public Object getParametro() {
		return parametro;
	}
}