package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ing. Marco Arenas Porcel:markituxfor@gmail.com
 * @author modificado por: Ochoa Martinez Sergio
 * Antonio:chechi:gnu.java.sergio@gmail.com
 */
public class Conexion {

    public static final String MYSQL = "mysql";
    public static final String POSTGRES = "postgressql";
    public static final String SQLSERVER = "sqlServer";
    public static final String HSQLDB = "hsqldb";
    public static final String DEFAULT = "";
    //declarando....
    private Connection conn;

    public Conexion() {
    }

    /**
     * Metodo para conectar los parametros con la base de datos
     *
     * @param nombreBD nombre de la base de datos contenida en JDBC:ODBC
     * @param username nomusuario=nombre de usuario
     * @param password pass=password:String
     * @param direccion
     * @param tipo 1:"mysql", 2:"postgresql", 3:"sqlServer"
     * @return devuelve una conexion: connection
     */
    public boolean conectarBD(String nombreBD, String username, String password, String direccion, String tipo) {
        String URL = "";
        if (tipo.compareTo("mysql") == 0) //Para Mysql
        {
            URL = "jdbc:mysql://" + direccion + ":3306/" + nombreBD;//Cargamos el Driver para Mysql
            try {
                Class.forName("com.mysql.jdbc.Driver");

            } catch (ClassNotFoundException ex) {
                System.err.println("No puedo encontrar el driver para la base de datos.");
                System.err.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println("No se puede cargar el puente JDBC-ODBC-MYSQL");
            }
        } else if (tipo.compareTo("postgressql") == 0)//Para PostGreSQL
        {
            URL = "jdbc:postgresql://" + direccion + ":5432/" + nombreBD; //Cargamos el Driver para Postgresql
            try {
                Class.forName("org.postgresql.Driver");

            } catch (ClassNotFoundException ex) {
                System.err.println("No puedo encontrar el driver para la base de datos.");
                System.err.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println("No se puede cargar el puente JDBC-ODBC-POSTGRESQL");
            }
        } else if (tipo.compareTo(Conexion.SQLSERVER) == 0) {

            URL = "jdbc:sqlserver://" + direccion + ":1433;databaseName=" + nombreBD;
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 2005 version
            } catch (ClassNotFoundException ex) {
                System.err.println("No puedo encontrar el driver para la base de datos.");
                System.err.println(ex.getMessage());
            } catch (Exception excepcionSql) { //excepcionSql = puede ponerle otro nombre
                System.out.println("No se puede cargar el puente JDBC-ODBC-POSTGRESQL");
                System.err.println(excepcionSql.getMessage());
            }
        } else if (tipo.compareTo(Conexion.HSQLDB) == 0) {
            URL = "jdbc:hsqldb:file:data/" + nombreBD;
            try {
                Class.forName("org.hsqldb.jdbcDriver");
            } catch (ClassNotFoundException ex) {
                System.err.println("No puedo encontrar el driver para la base de datos.");
                System.err.println(ex.getMessage());
            } catch (Exception excepcionSql) { //excepcionSql = puede ponerle otro nombre
                System.out.println("No se puede cargar el puente JDBC-HSQLDB");
                System.err.println(excepcionSql.getMessage());
            }
        } else//Conectividad por Defecto
        {//Conexion tipo ODBC Generico
            URL = "jdbc:odbc:" + nombreBD;//Cargamos el Driver para ODBC
            try {
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            } catch (ClassNotFoundException ex) {
                System.err.println("No puedo encontrar el driver para la base de datos.");
                System.err.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println("No se puede cargar el puente JDBC-ODBC");
            }
        }
        try {
            conn = DriverManager.getConnection(URL, username, password);//Establecer la Conexion		
        } catch (SQLException E) {
            System.out.println("No se encontraron parametros para la conexion a la BD");
            System.out.println("Excepcion del SQL: " + E.getMessage());
            System.out.println("Estado del SQL: " + E.getSQLState());
            System.out.println("Error del Proveedor: " + E.getErrorCode());
            return false;
        }
        return true;
    }//Fin de Conectar

    /**
     * metodo estatico para cerrar la conexion a la BD.
     */
    public void cerrarBD() {
        try {
            conn.close();
        } catch (SQLException E) {
            System.out.println("Excepcion del SQL: " + E.getMessage());
            System.out.println("Estado del SQL: " + E.getSQLState());
            System.out.println("Error del Proveedor: " + E.getErrorCode());
        }//Fin de catch 	
    }//Fin de cerrarConexion

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}//Fin de la Clase