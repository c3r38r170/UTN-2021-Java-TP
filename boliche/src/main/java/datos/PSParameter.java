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
	
	public PSParameter(String parametro) {
		this.parametro=parametro;
		this.tipo=Types.STRING;
	}
	
	public PSParameter(int parametro) {
		this.parametro=parametro;
		this.tipo=Types.INT;
	}
	
	public Types getTipo() {
		return tipo;
	}
	
	public Object getParametro() {
		return parametro;
	}
}