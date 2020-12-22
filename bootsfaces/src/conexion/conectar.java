package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectar {
	
	static String bd = "documentos";
    static String login = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost:3306/"+bd;
    Connection connection = null;
    
    public conectar(){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url,login,password);
        if (connection!=null){
            System.out.println("Conexión a base de datos "+bd+" OK\n");
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void desconectar(){
        connection = null;
    }
}
