

package database;

/**
 *
 * @author NahumFrog
 */
public class Alumno {
    //Atributos
    private int code;
    private String nombre, apellidos;
    
    //metodos
    public Alumno(String nombre, String apellidos){//constructor
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getCode() {//para obtener el código del alumno
        return code;
    }

    public void setCode(int code) {//para poner el código de alumno
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
}
