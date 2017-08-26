

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.lang.ClassNotFoundException;

/**
 *
 * @author NahumFrog
 */
public class SqliteBase {
    
    private Connection conect;
    private final String URL_DATA="Config/database/users.db";
    private String nombre, apellidos;
    private Alumno alumno= new Alumno(nombre, apellidos);
    private DefaultTableModel modeloAlumno, modeloProfesor, modeloAdmin;
    public void getAlumno(String nombre, String apellidos){
        this.nombre=nombre;
        this.apellidos=apellidos;
    }
    ///////////////////////////////////////////////////////////////////////////
    public void crearConectar(){
        /*Este metodo crea una conexion a la base de datos SQLite
          si la base de datos no existe, crea el archivo*/
        try{
            Class.forName("org.sqlite.JDBC");
            conect = DriverManager.getConnection("jdbc:sqlite:"+URL_DATA);
            
            if (conect!=null) {
                System.out.println("Conexión exitosa");
            }
            
        }catch(SQLException | ClassNotFoundException e){
            System.err.println("Falla en la conexión");
            System.out.println("\n"+e);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    public void cerrarConect(){//se supone que esto cierra la conexion
        try{
            conect.close();
            System.out.println("Se cerró la conexion");
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    public void saveAlumno(){
        try{
            PreparedStatement st = conect.prepareStatement("insert into alumnos (nombre, apellidos) values (?,?)");
            st.setString(1, alumno.getNombre());
            st.setString(2, alumno.getApellidos());
            st.execute();
        }catch(SQLException e){
            System.err.println(e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    public DefaultTableModel llenarModeloAlumno(){
        modeloAlumno = new DefaultTableModel();
        modeloAlumno.addColumn("Código");
        modeloAlumno.addColumn("Nombre (s)");
        modeloAlumno.addColumn("Apellido Paterno");
        modeloAlumno.addColumn("Apellido Materno");
        modeloAlumno.addColumn("e-mai");
        modeloAlumno.addColumn("NRC/Clase");
        return modeloAlumno;
    }
    public DefaultTableModel llenarModeloProf(){//No se utilizará de momento
        modeloProfesor = new DefaultTableModel();
        modeloProfesor.addColumn("Código");
        modeloProfesor.addColumn("Nombre (s)");
        modeloProfesor.addColumn("Apellido Paterno");
        modeloProfesor.addColumn("Apellido Materno");
        modeloProfesor.addColumn("e-mai");
        modeloProfesor.addColumn("Estatus");
        return modeloProfesor;
    }
    public DefaultTableModel llenarModeloAdmin(){//No se utilizará de momento
        modeloAdmin = new DefaultTableModel();
        modeloAdmin.addColumn("Nombre (s)");
        modeloAdmin.addColumn("Apellido Paterno");
        modeloAdmin.addColumn("Apellido Materno");
        modeloAdmin.addColumn("e-mai");
        modeloAdmin.addColumn("Estatus");
        return modeloAdmin;
    }
    ////////////////////////////////////////////////////////////////////////////
}
