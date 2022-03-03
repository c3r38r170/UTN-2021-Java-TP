package entidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import datos.Conexion;
import datos.PSParameter;
import datos.PSParameter.Types;

public class Usuario {
	private String nombre;
	private String contraseña;
	private String nickname;
	private String mail;
	private Rol rol;
	private boolean verificado;
	private int ID;

	public int getID() {
		return ID;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getNickname() {
		return nickname;
	}

	public String getMail() {
		return mail;
	}

	public Rol getRol() {
		return rol;
	}

	public boolean isVerificado() { 
		return verificado;
	}

	public Usuario(int ID) throws SQLException {
		this((new Conexion()).primerFila("SELECT ID,usuario, contraseña, nickname, correo, verificado,rolID FROM usuarios WHERE ID="+ID));
	}

	public Usuario(ResultSet rs) throws SQLException {
		this(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getBoolean(6), rs.getInt(7));
	}
	
	public Usuario(int ID, String nombre, String contraseña, String nickname, String mail, boolean verificado,
			int rol) {
		super();
		this.nombre = nombre;
		this.rol = Usuario.intToRol(rol);
		this.contraseña = contraseña;
		this.nickname = nickname;
		this.mail = mail;
		this.verificado = verificado;
		this.ID = ID;
	}

	public Usuario() {
	}

	public Usuario(String usu) throws SQLException {
		this((new Conexion()).primerFila("select ID,usuario, contraseña, nickname, correo, verificado,rolID from usuarios  where usuario = ? AND verificado=1",new PSParameter(usu)));
	}
		   
  public static   LinkedList<Usuario>  GetUsersForTheNight()
	{
    return GetUsersForTheNight(0);
  }
	public static   LinkedList<Usuario>  GetUsersForTheNight(int estado)
	{
		 LinkedList<Usuario> linkusuario = new    LinkedList<Usuario> ();
			ResultSet rs = null;
			try 
			{
				Conexion cn = new Conexion();
				String query="select u.ID,u.usuario,u.nickname,u.correo"
						+ " from usuarios u"
						+ " join acceso ac on ac.clienteID = u.ID"
						+ " join noche nox on nox.ID = ac.nocheID"
						+ " where  nox.fecha = CURDATE()";
        if(estado!=0)
						query+=" AND ac.estadoID="+estado;
        query+=" ORDER BY ac.hora ASC";
        
				rs=cn.executeSelect(query);
				
			 		while(rs.next())
			 		{
			 			Usuario u = new Usuario(rs.getInt(1),rs.getString(2),null,rs.getString(3),rs.getString(4),false,0);
			 			linkusuario.add(u);
			 		}
			}
			catch(Exception e){System.out.println(e.getMessage());}
		 
		      return linkusuario;
	}

	public static Rol intToRol(int num) {
		switch(num) {
		case 1:
			return Rol.Administrador;
		case 2:
			return Rol.Seguridad;
		case 3:
			return Rol.Cliente;
		default:
			return Rol.Desconocido;
		}
	}
	
	public static void ActualizarUsuario(int id , String Usuario,String nickname,String Contrasena,String email) 
	{
		
		try {
			Conexion conn = new Conexion();

			conn.preparedStatement("Update usuarios u set u.contraseña =? , u.usuario =?, u.nickname =?,u.correo=?   where u.ID = ? ",
					new PSParameter[] { new PSParameter(Contrasena , Types.STRING), new PSParameter(Usuario, Types.STRING),
							new PSParameter(nickname, Types.STRING), new PSParameter( email, Types.STRING) ,new PSParameter(id , Types.INT)

					});
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		
	}
	
	
	
	public static  void agregar( String Usuario,String nickname,String Contrasena,String email, boolean verificado, int creador,int rol) 
	{
		var con = new Conexion();

		try {
			con.preparedStatement("INSERT INTO usuarios (usuario,nickname,contraseña,correo,verificado,creadorID,rolID) value(?,?,?,?,?,?,?) ;  ",
					new PSParameter[] { new PSParameter( Usuario, Types.STRING),new PSParameter( nickname , Types.STRING) ,
							new PSParameter( Contrasena , Types.STRING), new PSParameter(email , Types.STRING),
							new PSParameter( verificado, Types.BOOLEAN),new PSParameter( creador, Types.INT) ,new PSParameter( rol, Types.INT)
			});
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	
	
	
	public static int  TraerIdEmpleado(String nombre, int rol) 
	{
		
		
		try {
			Conexion conn = new Conexion();

			ResultSet rs = conn.preparedSelectStatement(
					"select u.ID from usuarios u where u.usuario =? and u.rolID =? ;",
					new PSParameter[] { new PSParameter(nombre, Types.STRING), new PSParameter(rol, Types.INT) });
			
			
			return rs.getInt(1);
			
		}
		
		catch(Exception ex) {System.out.println(ex.getMessage());}
		return 0;
	}

		
	
	public static void  EliminarEmpleado (int Id) 
	{
		
		try {
			Conexion conn = new Conexion();

			conn.preparedStatement("Update usuarios u set u.verificado = 0 where u.ID = ? ",
					new PSParameter[] { new PSParameter(Id , Types.INT)});
							

					
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		
		
	}
	
	
	
	} 
	 
	
	
	


