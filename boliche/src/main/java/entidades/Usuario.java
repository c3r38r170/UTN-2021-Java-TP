package entidades;

public class Usuario {
	private String nombre;
	private String contraseña;
	private String nickname;
	
	public Usuario(String nombre, String contraseña, String nickname) {
		super();
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.nickname = nickname;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
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
