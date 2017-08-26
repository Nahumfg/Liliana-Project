/*
 * En esta clase leeré y escribiré archivos y será mandada llamar por la parte gráfica
 */
package nahum;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author Nahum Flores Gómez
 */
public class WriteRead {
    //Declaro mis atributos
    ////////////////////////////////////////////////////////////////////////////
    private JFileChooser buscador = new JFileChooser();
    private FileReader lector=null;
    private BufferedReader bufer=null;
    private File archivo;
    private File archivo1;
    private static String cadRecibe;
    private FileWriter escritor=null;
    private BufferedWriter buferEscrib;
    private String cadGeneric, lect;
    private Ejecutar ejec;
    ////////////////////////////////////////////////////////////////////////////
    //comienzo con los comportamientos
    public String leerReg(String lect){//Este método sirve para leer un archivo de texto
        try{
//            buscador.showOpenDialog(buscador);
//            cadRecibe = buscador.getSelectedFile().getAbsolutePath();
            lect="";
            archivo = new File("Registro.rec");
            try{
                lector = new FileReader(archivo);
                bufer=new BufferedReader (lector);
                String aux="";
                
                while(aux!=null){
                    aux=bufer.readLine();
                    if (aux!=null) {
                        lect = lect + aux +"\n";
                    }
                    
                    
                }
                return lect;
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error 1 "+e+"\nDebe abrir el archivo primero");
                ejec.writeInLog(""+e);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 2 "+e+"\nDebe abrir el archivo primero");
        }finally{
            try{
                if (null!=lector) {
                    lector.close();
                }
 
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error "+e+"\nDebe abrir el archivo primero");
            }
        }
        return lect;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String escribeReg(String escrib){//Éste método agrega texto a mi archivo en una nueva línea
        try{
            archivo = new File("Config/Registro.rec");
            escritor = new FileWriter(archivo, true);
            escritor.write(escrib+"\n");
            archivo = null;
            escritor.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error 1 "+"\n"+e);
        }
        return escrib;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String writeGeneric(String urlGeneric, String cadGeneric){//Éste método agrega texto a mi archivo en una nueva línea
        try{
            
            archivo = new File(urlGeneric);
            escritor = new FileWriter(archivo, true);
            escritor.write(cadGeneric+"\n");
            escritor.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error 1 "+"\n"+e);
        }
        return cadGeneric;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String writeError(String cadGeneric){//Éste método agrega texto a mi archivo en una nueva línea
        try{
            
            archivo = new File("Config/ErrorLog.log");
            escritor = new FileWriter(archivo, true);
            escritor.write(cadGeneric+"\n");
            escritor.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error 1 "+"\n"+e);
        }
        return cadGeneric;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String reescribe(String reesc){
        try{
            buferEscrib = new BufferedWriter(new FileWriter(archivo));
            buferEscrib.write(reesc);
            buferEscrib.close();
            return reesc;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error 1 "+"\n"+e);
        }
        
        return reesc;
    }
    ////////////////////////////////////////////////////////////////////////////
    public String verificar(String lect){//Este método sirve para ver el texto ya editado luego de guardar
        
        try{
            lect="";
            archivo = new File(cadRecibe);
            try{
                lector = new FileReader(archivo);
                bufer=new BufferedReader (lector);
                String aux="";
                
                while(aux!=null){
                    aux=bufer.readLine();
                    if (aux!=null) {
                        lect = lect + aux +"\n";
                    }
                    
                }
                return lect;
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error "+e+"\nDebe abrir el archivo primero");
            }
        }catch(java.awt.HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error "+e+"\nDebe abrir el archivo primero");
        }finally{
            try{
                if (null!=lector) {
                    lector.close();
                }
 
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error "+e+"\nDebe abrir el archivo primero");
            }
        }
        return lect;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String leerGeneric(String lect, String cadGeneric){//Este método sirve para leer un archivo de texto
        try{
            
            this.cadGeneric=cadGeneric;
            
            archivo = new File(cadGeneric);
            try{
                lector = new FileReader(archivo);
                bufer=new BufferedReader (lector);
                String aux="";
                
                while(aux!=null){
                    aux=bufer.readLine();
                    if (aux!=null) {
                        lect = lect + aux +"\n";
                        
                    }else{
                        aux = null;
                    }
                    
                    
                }
                this.lect=lect;
                return lect;
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error 1 "+e+"\nDebe abrir el archivo primero");
                writeError("----------------------------------\n"+e+"\n------------------------------------------");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 2 "+e+"\nDebe abrir el archivo primero");
        }finally{
            try{
                if (null!=lector) {
                    lector.close();
                }
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Error "+e+"\nDebe abrir el archivo primero");
            }
        }
        return this.lect;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    public void verif2(){
//        try{
//            Runtime.getRuntime().exec("gedit "+cadRecibe);
//        }catch(IOException e){
//            System.err.println(e);
//        }
//    }
    ////////////////////////////////////////////////////////////////////////////
    
    
}
