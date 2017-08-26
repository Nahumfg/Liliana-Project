
package nahum;


/**
 * Importamos las librerías necesarias de la clase principal.
 * Nótese que sólo se importan las que se utilizarán para ahorrar memoria
 */
import java.net.InetAddress;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/******************************
 *                            *
 * @author Nahum Flores Gómez *
 *     Versión: beta 0.2.3    *
 ******************************/

/**
 * Última Revisión: 26/Agosto/2017. (Nahum)
 * 
 * Escribir modificaciones en el archivo léame.
 */
/**
 * 
 * @author NahumFrog
 * Inicia la clase principal
 */
public class Principal {
    
    public static void iniciar(){
        /**
         * Método que sirve para abrir un puerto local
         * el cual servirá para revisar si el programa 
         * se está ejecutando, de este modo no se podrá 
         * ejecutar más de una vez.
         */
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ServerSocket soc = new ServerSocket(9999, 10, InetAddress.getLocalHost());//abre un socket
            new VentanaMain();//Create main window
            
        }catch(java.net.BindException  b){//captura la excepción
            //Lanza una ventana de advertencia si el socket ya esta abierto
            JOptionPane.showMessageDialog(null, "Liliana ya está en ejecución","Atención!",JOptionPane.ERROR_MESSAGE);
        }catch(Exception e){
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Ocurrió un problema al tratar de ejecutar","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    /**
     * Main Method
     * @param args 
     */
    public static void main(String[] args) {
        
        iniciar();//Inicia el programa con el método iniciar
        
    }

}
