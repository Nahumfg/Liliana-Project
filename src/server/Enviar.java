
package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author NahumFrog
 */
public class Enviar implements Runnable{
    ////////////////////////////////////////////
    private ObjectOutputStream salida;
    private String mensaje;
    private Socket conexion; 
    ///////////////////////////////////////////
    public Enviar(Socket conexion){
        this.conexion=conexion;
    }
    ///////////////////////////////////////////
    public void getMsg(String mensaje){
        this.mensaje = mensaje;
    }
    public String setMsg(){
        return mensaje;
    }
    ///////////////////////////////////////////
    public String enviarMensaje(String msg){
        try{
            salida.writeObject("Mensaje: "+msg);
            salida.flush();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
        return "Mensaje: "+msg;
    }
    public String enviarComando(String comando){
        try{
            salida.writeObject(comando);
            salida.flush();
        }catch(IOException ioe){
            System.out.println(ioe);
        }
        return comando;
    }
    ///////////////////////////////////////////
    public void run(){
        try{
            salida = new ObjectOutputStream(conexion.getOutputStream());
            salida.flush();
        }catch(SocketException se){
            System.err.println(se);
        }catch(IOException ioe){
            System.err.println(ioe);
        }catch(NullPointerException ne){
            System.err.println(ne);
        }
    }
    
}
