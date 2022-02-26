package entidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public void setID(int iD) {
		ID = iD;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
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
		// TODO Auto-generated constructor stub
	}

	public static Usuario checkUsernameExistence(String usu) {

		Usuario usuario = null;
		try {

			Conexion conn = new Conexion();
			ResultSet rs = conn.preparedSelectStatement(
					"select ID,usuario, contraseña, nickname, correo, verificado,rolID from usuarios  where usuario = ? AND verificado=1  ;",
					new PSParameter(usu, Types.STRING));
			if (rs.next()) {
				usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getBoolean(6), rs.getInt(7));

			}

		} catch (SQLException ex) {
			System.out.print(ex.getMessage() + "error ");
		}
		return usuario;

	}

	public static String checkPasswordExistence(String pass, String nombre) {

		Connection cn = null;
		ResultSet rs = null;
		PreparedStatement s = null;
		String password = null;

		try {
			Conexion conn = new Conexion();
			rs = conn.preparedSelectStatement("select contraseña  from usuarios  where contraseña = ? and usuario =? ;",
					new PSParameter[] { new PSParameter(pass, Types.STRING), new PSParameter(nombre, Types.STRING) });

			while (rs.next()) {
				password = rs.getString(1);
			}
			return password;
		}

		catch (SQLException e) {
			System.out.print(e + "error en getcontrasena");
			return "-1";
		}

		catch (Exception ex) {
			System.out.print(ex + "error en getcontrasena");
			return "-1";
		}

	}

	public static int getUserRol(String contra, String usua) {

		PreparedStatement ps = null;

		String usuarios = null;
		String cont = null;
		int Rol = 0;

		int result;

		try {
			Conexion conn = new Conexion();

			ResultSet rs = conn.preparedSelectStatement(
					"select rolID from usuarios  where contraseña = ? and usuario = ? ;",
					new PSParameter[] { new PSParameter(contra, Types.STRING), new PSParameter(usua, Types.STRING) });

			while (rs.next()) {
				Rol = (int) rs.getInt(1);

				return Rol;
			}
			return Rol;
		}

		catch (SQLException ex) {
			System.out.println(ex + "error en verifica usuario");
			return 0;
		} catch (Exception e) {
			System.out.println(e + "error en verifica usuario");
			return 0;
		}
	}

	
	// TODO wtf???
	public Usuario getUsuarioData(String usU, String contra) {

		ResultSet rs = null;
		Conexion cn = null;
		Usuario usus = null;

		try {

			String query = "select  contraseña ,usuario,nickname,rolID,creadorID, ID ,Mails,Verificado from usuarios  where contraseña = ? and usuario = ?  ;";

			rs = cn.executeSelect(query);

			while (rs.next()) {
				contraseña = rs.getString(1);
				nombre = rs.getString(2);
				nickname = rs.getString(3);
				int rol = rs.getInt(4);
				mail = rs.getString(7);
				verificado = rs.getBoolean(8);

				usus = new Usuario(rs.getInt(6), nombre, contraseña, nickname, mail, verificado, rol);

			}
			return usus;
		}
		    catch(SQLException ex) { System.out.println(ex+"error en usuariodata"); return null;}
			catch(Exception e) {System.out.print("error"+ e); return null;}
		
	
		   
  }public static   LinkedList<Usuario>  GetUsersForTheNight()
	{
    return GetUsersForTheNight(0);
  }
	public static   LinkedList<Usuario>  GetUsersForTheNight(int estado)
	{
		 LinkedList<Usuario> linkusuario = new    LinkedList<Usuario> ();
			ResultSet rs = null;
			PreparedStatement ps= null;
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
			int columns;
			Conexion conn = new Conexion();

			columns = conn.preparedStatement("Update usuarios u set u.contraseña =? , u.usuario =?, u.nickname =?,u.correo=?   where u.ID = ? ",
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
			int columnsafected = con.preparedStatement("INSERT INTO usuarios (usuario,nickname,contraseña,correo,verificado,creadorID,rolID) value(?,?,?,?,?,?,?) ;  ",
					new PSParameter[] { new PSParameter( Usuario, Types.STRING),new PSParameter( nickname , Types.STRING) ,
							new PSParameter( Contrasena , Types.STRING), new PSParameter(email , Types.STRING),
							new PSParameter( verificado, Types.BOOLEAN),new PSParameter( creador, Types.INT) ,new PSParameter( rol, Types.INT)
			});
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
}
