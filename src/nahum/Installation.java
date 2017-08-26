/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nahum;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author NahumFrog
 */
public class Installation {
    private final WriteRead LEE_ESC = new WriteRead();
    public void moveFiles(File origin, File destiny){
        try{
            if (origin.exists()) {
                if (destiny.exists()) {
                    //JOptionPane.showMessageDialog(null, "El archivo ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(Exception e){
            
        }
    }
    public void installReg(){//Este método escribira un registro de la installacion
        
        LEE_ESC.writeGeneric("","Install.reg");//La primer String es lo que se escribirá en el archivo, la segunda es el nombre del Archivo
    }
}
