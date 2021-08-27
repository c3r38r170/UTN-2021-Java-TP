package entidades;

public class Usuario {
	private String nombre;
	private String contrase�a;
	private String nickname;
	
	public Usuario(String nombre, String contrase�a, String nickname) {
		super();
		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.nickname = nickname;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Usuario getCreador() {
		// Conectar a la base de datos y traer el creador, si hay uno.
		return null;
	}
}
