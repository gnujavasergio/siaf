package jdbc;

import java.sql.*;

/**
 *
 * @author Ing. Marco Arenas Porcel:markituxfor@gmail.com
 * @author modificado por: Ochoa Martinez Sergio
 * Antonio:chechi:gnu.java.sergio@gmail.com
 */
public class ConsultaBD {

    private Statement stmUpdate, stmNave, consulta; //Objetos Para la consulta

    /**
     * Constructor por Defecto
     */
    public ConsultaBD(Conexion conexion) {

        if (conexion.getConn() != null) {
            try {//establece un puente, un flujo con la BD.
                /**
                 * createStatement(tipo, concurrencia) tipos= TYPE_FORWARD_ONLY,
                 * TYPE_SCROLL_INSENSITIVE, TYPE_SROLL_SENSITIVE concurrencias=
                 * CONCUR_READ_ONLY, CONCUR_UPDATABLE
                 */
                stmNave = conexion.getConn().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);//Navegable
                stmUpdate = conexion.getConn().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);//Actualizable
                consulta = conexion.getConn().createStatement();
            } catch (SQLException E) {
                System.out.println("Excepciï¿½n del SQL: " + E.getMessage());
                System.out.println("Estado del SQL: " + E.getSQLState());
                System.out.println("Error del Proveedor: " + E.getErrorCode());
                return;
            }
        } else {
            System.out.println("No se establecio la Conexion!");
        }
        //Hasta Aqui se Establecio un flujo de comunicacion
    }//Fin del Constructor

    /**
     * Metodo de Consulta Generica
     *
     * @param String sql:sentencia
     * @return Hoja de Resultado
     */
    public ResultSet consultar(String sql) {
        try {
            return stmNave.executeQuery(sql);
        } catch (SQLException sqlE) {
            System.out.println("Excepcion del SQL: " + sqlE.getMessage());
            System.out.println("Estado del SQL: " + sqlE.getSQLState());
            System.out.println("Error del Proveedor: " + sqlE.getErrorCode());
            return null;
        }
    }//fin de consultar

    /**
     * Metodo para Actualizar: -Insertar, Actualizar y Eliminar
     *
     * @param String sqlUpdate
     * @return int, para ver si se hizo la funcion (0: no hizo nada, 1:se
     * realizo)
     */
    public int actualizar(String sqlUpdate) {
        try {
            return stmUpdate.executeUpdate(sqlUpdate);
        } catch (SQLException sqlE) {
            System.out.println("No se establecieron los parametros correctos para la Actualizacion");
            System.out.println("Excepcion del SQL: " + sqlE.getMessage() + "-" + sqlUpdate);
            System.out.println("Estado del SQL: " + sqlE.getSQLState() + "-" + sqlUpdate);
            System.out.println("Error del Proveedor: " + sqlE.getErrorCode() + "-" + sqlUpdate);
            return 0;
        }
    }//fin actualizar

    /**
     * Metodo para Utilizar Funciones desde la BD
     *
     * @param String sql
     * @return boolean
     */
    public boolean ejecutar(String sql) {
        try {
            consulta.execute(sql);
            return true;
        } catch (SQLException sqlE) {
            System.out.println("No se establecieron los parametros correctos para la Actualizacion");
            System.out.println("Excepcion del SQL: " + sqlE.getMessage() + "-" + sql);
            System.out.println("Estado del SQL: " + sqlE.getSQLState() + "-" + sql);
            System.out.println("Error del Proveedor: " + sqlE.getErrorCode() + "-" + sql);
            return false;
        }
    }//fin de la ejecutar   

    /**
     * Cerra la Consulta
     */
    public void cerrarConsulta() {
        try {
            stmNave.close();
            stmUpdate.close();
        } catch (SQLException E) {
            System.out.println("Excepciï¿½n del SQL: " + E.getMessage());
            System.out.println("Estado del SQL: " + E.getSQLState());
            System.out.println("Error del Proveedor: " + E.getErrorCode());
        }//Fin de catch
    }//Fin de cerrarStatement
}//fin de la clase ConsultaBD
