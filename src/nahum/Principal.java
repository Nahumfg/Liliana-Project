
/*
 * Copyright (c) 2016, Nahum Flores Gómez
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
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
 *     Versión: beta 0.2.2    *
 ******************************/

/** 
<<<<<<< HEAD
 * Última Revisión: 28/Julio/2017. (Nahum)
=======
 * Última Revisión: 7/Mayo/2015. (Nahum)
>>>>>>> origin/master
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
