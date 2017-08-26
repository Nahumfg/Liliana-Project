

package nahum;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author Nahum Flores Gómez
 */
public class Ejecutar {
    private String os,proper,arch, linea="",pass,fecha;
    private final String ERROR_URL="Config/errorLog.log";
    private final String VERSION = "Versión: 0.2.1";
    //private ValidarUsr getPass = new ValidarUsr();
    private Process p;
    private File rec;
    private short dia,mes,anio,horaDelDia,hora,minuto;
    private Calendar calendario = new GregorianCalendar();
    private WriteRead leeEsc = new WriteRead();
    
    //Nota intentaré mejorar el código teniendo sólo una lista de comantos y un sólo método para ejecutarlos
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    private void getDateAndTime(){
        dia = (short)calendario.get(Calendar.DAY_OF_MONTH);
        mes = (short)calendario.get(Calendar.MONTH);
        anio = (short)calendario.get(Calendar.YEAR);
        horaDelDia = (short)calendario.get(Calendar.HOUR_OF_DAY);
        minuto = (short)calendario.get(Calendar.MINUTE);
        hora = (short)calendario.get(Calendar.HOUR);
        fecha = ""+dia+"/"+(mes+1)+"/"+anio;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void writeInLog(String eventReg){
        getDateAndTime();
        leeEsc.writeError("Registro fecha: "+fecha+"\n"+"------------------------------------------------\n"+eventReg+" a las: "+horaDelDia+":"+minuto+"\n------------------------------------------------\n");
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void setPass(String pass){
        this.pass=pass;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getProjectVersion(){
        return VERSION;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void shutDownLinux(){//método de prueba
        //veamos si funciona en linux
       
        String line="";
        
        try{
            //Process pL = Runtime.getRuntime().exec(new String [] {"bash","-c","sudo shutdown -h now","echo "+pass});
            Process pL = Runtime.getRuntime().exec(new String [] {"bash","-c","echo "+pass+"|sudo shutdown -h now"});
            BufferedReader input = new BufferedReader(new InputStreamReader(pL.getInputStream()));
            String aux= "";
            while(aux!=null){
                aux=input.readLine();
                if (aux!=null) {
                    line = line+aux+"\n";
                }
            }
            input.close();
            pL.destroy();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n");
            JOptionPane.showMessageDialog(null, line);
            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando apagar linux"+"\n"+line);
        }
            
    }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void restartLinux(){
            String line="";
        
            try{
                Process pL = Runtime.getRuntime().exec(new String [] {"bash","-c","sudo reboot","echo "+pass});

            BufferedReader input = new BufferedReader(new InputStreamReader(pL.getInputStream()));
            String aux= "";
            while(aux!=null){
                aux=input.readLine();
                if (aux!=null) {
                    line = line+aux+"\n";
                }
            }
            input.close();
            pL.destroy();
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n");
            JOptionPane.showMessageDialog(null, line);
            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando apagar linux"+"\n"+line);
        }
            
    }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void restartLinux(String pass){
            String line="";
        
            try{
                Process pL = Runtime.getRuntime().exec(new String [] {"bash","-c","sudo reboot",pass});
                BufferedReader input = new BufferedReader(new InputStreamReader(pL.getInputStream()));
                String aux= "";
                while(aux!=null){
                    aux=input.readLine();
                    if (aux!=null) {
                        line = line+aux+"\n";
                    }
                }
                input.close();
                pL.destroy();
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n");
                JOptionPane.showMessageDialog(null, line);
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando reiniciar Linux");
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void loggOffLinux(String pass){
            
        }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void knowVersion(){
        String dir = "https://mega.nz/#F!isZWxJjL!XFbn8tgOL4L_mk9XZFztsg";
        try{
            JOptionPane.showMessageDialog(null, "Su versión actual del programa es: "+VERSION,"Version del programa",JOptionPane.INFORMATION_MESSAGE);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(dir));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    public void linkOpenner(String url){
        try{
            
            if (url.length()>=60) {
                JOptionPane.showMessageDialog(null, "Abriendo: "+url.substring(0, 60)+"\nen su navegador","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Abriendo: "+url+"\nen su navegador","Mensaje",JOptionPane.INFORMATION_MESSAGE);
            }
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            }
            
        }catch(Exception e){
            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando abrir el URL");
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    public void reproSound(String dir){
        try{
            
            File arch = new File(dir);
            
            Clip sonido = AudioSystem.getClip();
            
            sonido.open(AudioSystem.getAudioInputStream(arch));
            
            sonido.start();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando abrir el Sonido");
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    public String listarProcesos(){
        obtenerSistema();
        
        if (os.equalsIgnoreCase("Linux")) {
            try{
                Process pL = Runtime.getRuntime().exec(new String [] {"bash","-c","ps -e"});
                BufferedReader input = new BufferedReader(new InputStreamReader(pL.getInputStream()));
                String aux= "";
            
            while(aux!=null){
                aux=input.readLine();
                if (aux!=null) {
                    linea = linea+aux+"\n";
                }
            }
            
            input.close();
            pL.destroy();
            linea = linea + "\n------------------------------\n";
            return linea;
            
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nAl intentar el comando ps -e > Linux");
            }
            
        }
        if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
            try{
                Process pL = Runtime.getRuntime().exec(new String []{"tasklist"});
                BufferedReader input = new BufferedReader(new InputStreamReader(pL.getInputStream()));
                String aux= "";
            
            while(aux!=null){
                aux=input.readLine();
                if (aux!=null) {
                    linea = linea+aux+"\n";
                }
            }
            input.close();
            pL.destroy();
            linea = linea + "\n------------------------------\n";
            return linea;
            
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar el comando tasklist > windows");
            }
            
        }
        
        return "";
        
    }
    public void obtenerSistema(){
        os=System.getProperty("os.name");//nombre
        proper=System.getProperty("os.version");//version
        arch=System.getProperty("os.arch");//arquitectura
        //JOptionPane.showMessageDialog(null, "Sistema operativo detectado: "+os+"\nKernel Version: "+proper+"\nArquitectura: "+arch+"\nIntentando Ejecutar comando");
     }
     public void depende(){//Según el sistema operativo abre cualquiera de los dos mensajes
        os=System.getProperty("os.name");
         if (os.equalsIgnoreCase("Linux")) {
             JOptionPane.showMessageDialog(null, "Éste programa utiliza comandos de terminal\nde programas ya instalados en su distribución de\nLinux por lo cual no es seguro que funcione en todas\nlas distribuciones... Trabajo en solucionar ese problema.\nGracias por comprender"
             ,"Bienvenido",JOptionPane.INFORMATION_MESSAGE);
         }
         if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
             JOptionPane.showMessageDialog(null, "Éste programa utiliza comandos y rutas de programas\nya instalados en Windows, si su pc no los tiene instalados\n es normal que el programa no se ejecute y muestre un error de comando"
             ,"Bienvenido",JOptionPane.INFORMATION_MESSAGE);
         }
         if (os.equalsIgnoreCase("Mac OS X")) {
             JOptionPane.showMessageDialog(null, "Actualmente este programa está siendo sometido a pruebas en Mac\nSi usted ve este mensaje significa que la prueba está teniendo éxito.","Info",JOptionPane.INFORMATION_MESSAGE);
             writeInLog("Mensaje presentado al ejecutar la aplicación en Mac");
         }
     }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public void leerPDF(){//revisar este metodo0
        obtenerSistema();
        if (os.equalsIgnoreCase("Linux")) {
            try{
                    Runtime.getRuntime().exec("evince");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nSe intentará otro comando");
                    try{
                        Runtime.getRuntime().exec("okular");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nNo hay mas comandos en la lista");
                    }
                }
        }
        if (os.equalsIgnoreCase("Windows 7")) {
            try{

                Runtime.getRuntime().exec("cmd /c start acrobat.exe");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nAl intentar el comando acrobat.exe");
            }
        }
        if (os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
            try{
                Runtime.getRuntime().exec("cmd /c start acrord32.exe");
            }catch(Exception e){
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar acrord32.exe");
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
        public void instanMessage(){//ejecuta el programa para mensajería instantánea
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    Runtime.getRuntime().exec("empathy");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nSe intentará otro comando");
                    try{
                        Runtime.getRuntime().exec("kopete");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nNo hay mas comandos en la lista");
                        try{
                            Runtime.getRuntime().exec("skype");
                        }catch(IOException e2){
                            JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nNo hay mas comandos en la lista");
                            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar mensajero, ninguno de los comandos funciono");
                        }
                    }
                }
            }
            if (os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                    JOptionPane.showMessageDialog(null, "Ésta característica no funciona para Windows 8 o 10 \nGracias por su comprensión","Advertencia",JOptionPane.ERROR_MESSAGE);
                }
            if (os.equalsIgnoreCase("Windows 7")) {
                
                try{
                    //Nota esto es en windows 64 Bits
                    if(arch.equalsIgnoreCase("amd64")){
                        Runtime.getRuntime().exec("C:\\Program Files (x86)\\Skype\\Phone\\Skype.exe");
                    }else{
                        Runtime.getRuntime().exec("C:\\Program Files\\Skype\\Phone\\Skype.exe");
                    }
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada \n Probablemente no tiene instalado el programa");
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar skype.exe");
                }
            }
            if (os.equalsIgnoreCase("Mac OS X")) {
                try{
                    Runtime.getRuntime().exec("ichat");
                }catch(IOException e){
                    JOptionPane.showMessageDialog(null, "Error de comando");
                }
            }
        }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    //Métodos para Ejecutar programas de office///////////////////////////////////////////////////////////
    public void calculo(){//abre office
        obtenerSistema();
        if (os.equalsIgnoreCase("Linux")) {
            try{
                Runtime.getRuntime().exec("soffice");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nSe intentará otro comando");
                try{
                    Runtime.getRuntime().exec("koffice");
                }catch(IOException e1){
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nNo hay mas comandos en la lista");
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar office en linux");
                }
            }
        }
        if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
            try{

                Runtime.getRuntime().exec("cmd /c start excel.exe");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar excel.exe");
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public void escritor(){//abre office
        obtenerSistema();
        if (os.equalsIgnoreCase("Linux")) {
            try{
                Runtime.getRuntime().exec("soffice");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n Se intentará otro comando");
                try{
                    Runtime.getRuntime().exec("koffice");
                }catch(IOException e1){
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nNo hay mas comandos en la lista");
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar office");
                }
            }
        }
        if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
            try{

                Runtime.getRuntime().exec("cmd /c start winword.exe");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar winword.exe");

            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public void presentador(){//abre ocffice
        obtenerSistema();
        if (os.equalsIgnoreCase("Linux")) {
            try{

                Runtime.getRuntime().exec("soffice");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nSe intentará otro comando");
                try{
                    Runtime.getRuntime().exec("koffice");
                }catch(IOException e1){
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\nNo hay mas comandos en la lista");
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar office");
                }
            }
        }
        if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
            try{

                Runtime.getRuntime().exec("cmd /c start powerpnt.exe");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar powerpnt.exe");
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     //////////////////////////////////////////////////////////////////////////////////////////////////////


     
     //////////////////////////////////////////////////////////////////////////////////////////////////////
    public void terminal(){//Método para abrir la terminal del sistema
        obtenerSistema();
        if (os.equals("Linux")) {
            try{
                Runtime.getRuntime().exec("gnome-terminal");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error ter01\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                    try{
                        Runtime.getRuntime().exec("konsole");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error ter02\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                        try{
                            Runtime.getRuntime().exec("xterm");
                        }catch(IOException e2){
                            JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error ter02\nUps, se terminó la lista de comandos", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar terminal");
                            
                        }
                    }
            }
        }
        if(os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")){
            try{
                Runtime.getRuntime().exec("cmd /c start cmd");
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar cmd.exe");
            }
        }
    }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        public void processAdmin(){//Abre el administrador de tareas
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    JOptionPane.showMessageDialog(null, "Supervisor de procesos");
                    Runtime.getRuntime().exec("gnome-system-monitor");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error tsk01\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                    try{
                        Runtime.getRuntime().exec("ksysguard");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error tsk02\nNo se encuentran más comandos", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                        writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar task manager");
                    }
                }
            }
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{//abre el gestor de procesos den windows
                    Runtime.getRuntime().exec("cmd /c taskmgr");//prueba agregando start
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar taskmgr");
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        public void folder(){//Abre el gestor de archivos
            obtenerSistema();
            
            if(os.equals("Linux")){
                try{
                    Runtime.getRuntime().exec("nemo"); 
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error fd01\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                    try{
                        Runtime.getRuntime().exec("nautilus");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error fd02\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                        try{
                            Runtime.getRuntime().exec("dolphin");
                        }catch(IOException e2){
                            JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error fd03\nNo se encuentran más comandos", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                            writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar gestor de archivos");
                        }
                    }
                }
            }
            if (os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                
                try{
                    Runtime.getRuntime().exec("explorer.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar explorer.exe");
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        public void text(){//abre el editor de texto    
            obtenerSistema();
            if(os.equals("Linux")){
                try{
                    Runtime.getRuntime().exec("gedit");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error tx01\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                    try{
                        Runtime.getRuntime().exec("kwrite");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error tx02\nSe intentará otro comando", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                        writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar editor de texto");
                    }
                }
            }
            if (os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("cmd /c start notepad.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                    JOptionPane.showMessageDialog(null, "Posible error: La dirección del archivo .exe fue modificada");
                    try{
                        Runtime.getRuntime().exec("cmd /c start wordpad.exe");
                    }catch(IOException e2){
                        writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar notepad.exe");
                    }
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        public void firefox(){
            obtenerSistema();
            if(os.equals("Linux")){
                try{
                    Runtime.getRuntime().exec("firefox");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar firefox");
                }
            }
            if (os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("cmd /c start firefox.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Posible error: Programa no instalado", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    writeInLog("Se produjo el siguiente error: "+"\n"+e+"\nIntentando ejecutar firefox.exe");
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        public void chrome(){
            obtenerSistema();
            if(os.equals("Linux")){
                try{
                    Runtime.getRuntime().exec("chromium-browser");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Posible error: Programa no instalado", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("cmd /c start chrome.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Posible error: Programa no instalado", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////

        //este metodo es solo para windows/////////////////////////////////////////////////////////////
        public void winIExplore(){//problemas con windows 10, veremos cómo solucionarlo
            try{
                    Runtime.getRuntime().exec("cmd /c start iexplore.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////

        
        /////////////////////////////////////////////////////////////////////////////////
        public void mediaPlayer(){
            obtenerSistema();
            //Este método ejecuta el reproductor de audio ó multimedia
            if(os.equals("Linux")){
                try{
                    Runtime.getRuntime().exec("banshee");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "Código de error: mP01\nSe intentará otro comando");
                    try{
                        Runtime.getRuntime().exec("rhythmbox");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error mP02\nSe intentará otro comando"
                        , "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                        try{
                            Runtime.getRuntime().exec("amarok");
                        }catch(IOException e2){
                            JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error mP03\nNo quedan comandos en la lista"
                            , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            if (os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("cmd /c start wmplayer.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (os.equalsIgnoreCase("Mac OS X")) {
                try{
                    Runtime.getRuntime().exec("itunes");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////

        //Método en desarrollo para abrir Synaptic/////////////////////////////////////////////////////
        public void instalador(){//Aún hay problemas con los permisos de superusuario
            obtenerSistema();
            if (os.equals("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{//abre panel de control

                    Runtime.getRuntime().exec("cmd /c start control");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    
                }
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        public void movie(){//Éste método ejecuta el reproductor de videos
            obtenerSistema();
            if(os.equals("Linux")){
                try{
                    Runtime.getRuntime().exec("totem");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n"
                            + "código de error mv01\nSe intentará otro comando"
                    , "Tenemos Problemas!!",JOptionPane.WARNING_MESSAGE);
                    try{
                        Runtime.getRuntime().exec("vlc");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error mv02\nParece que no se encontró ninguno de los comandos"
                        , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("cmd /c start wmplayer.exe");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... El programa Windows Media no se encuentra instalado"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //////////////////////////////////////////////////////////////////////////////////////////////////
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        public void pintura(){//éste método ejecuta el programa para editar imágenes
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    Runtime.getRuntime().exec("gimp");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \nEl programa gimp no se encuentra instalado"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            //working
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    p = Runtime.getRuntime().exec("mspaint.exe");
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        public void calculadora(){
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    Runtime.getRuntime().exec("gcalctool");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n"
                            + "código de error cal01\nSe intentará otro comando", 
                            "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    try{
                        Runtime.getRuntime().exec("kcalc");
                    }catch(IOException e1){
                        JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada\n"
                            + "código de error cal02\nParece que no se encontró ninguno de los comandos", 
                                "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            //Working
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    p = Runtime.getRuntime().exec("calc");
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada");
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando");
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        //ejecutador manual
        public void exeGeneric(String comando){
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {//working
                try{
                    Runtime.getRuntime().exec(comando);
                    
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n"
                            + "acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            //testing
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {//working
                try{
                    p = Runtime.getRuntime().exec("cmd /c start "+comando);
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando");
            }
            }
            
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        public void salir(){
            os=System.getProperty("os.name");
            JOptionPane.showMessageDialog(null, "\nGracias por utilizar Proyecto Liliana...");
            System.exit(0);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void shutdownSistem(){
            obtenerSistema();
//            if (os.equalsIgnoreCase("Linux")) {
//                try{
//                    JOptionPane.showMessageDialog(null, "esta característica aún no disponible para Linux");
//                    Runtime.getRuntime().exec("");
//                }catch (IOException e) {
//                    JOptionPane.showMessageDialog(null, "Error de comando... \n", 
//                            "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
//                }
//            }
            //if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {//working
                try{
                    p = Runtime.getRuntime().exec("shutdown -s -t 5");
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando");
                }
            //}
            
        }
        public void timedShutDown(int time){
            try{
                JOptionPane.showMessageDialog(null, "El equipo se apagará en "+time+" segundos");
                    p = Runtime.getRuntime().exec("shutdown -s -t "+time);
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando");
                }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void restartSistem(){
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    JOptionPane.showMessageDialog(null, "esta característica aún no disponible para Linux");
                    Runtime.getRuntime().exec("");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n", 
                            "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {//working
                try{
                    p = Runtime.getRuntime().exec("shutdown -r -t 5");
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando");
                }
            }
            
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void logOff(){
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    
                    JOptionPane.showMessageDialog(null, "esta característica aún no disponible para Linux");
                    Runtime.getRuntime().exec("");
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n", 
                            "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {//working
                try{
                    p = Runtime.getRuntime().exec("shutdown -l");
                    p.waitFor();
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... Acción no ejecutada", "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }catch (InterruptedException ie){
                    JOptionPane.showMessageDialog(null, ie+"\nError en el comando");
                }
            }
            
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void bluetoothTool(){
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    p = Runtime.getRuntime().exec("");
                    System.out.println(p.getInputStream());
                    System.out.println(p.getOutputStream());
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n", 
                            "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("cmd /c start fsquirt.exe");//probando
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \nPosible error: Bluetooth apagado o no disponible"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        public void getSysInfo(){
            rec = new File("Registro.rec");
            obtenerSistema();
            JOptionPane.showMessageDialog(null, "Sistema Operativo: "+os+"\nVersion: "+proper+"\nArquitectura: "+arch
            +"\nEl registro de actividades está en:\n"+rec.getAbsolutePath()+"\nVersión actual de Liliana: "+VERSION,"Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        public void libInstall(){//método que utilizaré para instalar las librerías
            obtenerSistema();
            if (os.equalsIgnoreCase("Linux")) {
                try{
                    
                    Runtime.getRuntime().exec("");
                    
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n", 
                            "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
            if (os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")) {
                try{
                    Runtime.getRuntime().exec("");//probando
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error de comando... \n"
                    , "Tenemos Problemas!!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////
}
