

package iconific;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nahum.WriteRead;
import nahum.Ejecutar;
/**
 *
 * @author nahumfg
 */
public class VentanasEsc {
    private JFrame miframe;
    Ejecutar EJEC = new Ejecutar();
    private PopupMenu popup = new PopupMenu();
    private Image image =new ImageIcon(getClass().getResource("/iconific/circle-icon-linux-48px.png")).getImage() ;
    private final TrayIcon trayIcon = new TrayIcon(image, "Proyecto Liliana Versión "+EJEC.getProjectVersion(), popup);
    private boolean band;
    
    public VentanasEsc( JFrame frame){
    this.miframe = frame;
    System.gc();
    //comprueba si SystemTray es soportado en el sistema
    if (SystemTray.isSupported())
    {
      //obtiene instancia SystemTray
      SystemTray systemtray = SystemTray.getSystemTray();
      //acciones del raton sobre el icono en la barra de tareas
      MouseListener mouseListener = new MouseListener() {

        public void mouseClicked(MouseEvent evt) {            
            //Si se presiono el boton izquierdo y la aplicacion esta minimizada
            if( evt.getButton() == MouseEvent.BUTTON1 && miframe.getExtendedState()==JFrame.ICONIFIED )
                MensajeTrayIcon("Liliana se sigue ejecutando... \nPresione el botón derecho del mouse para restaurar.", MessageType.WARNING);
        }

        public void mouseEntered(MouseEvent evt) {
            
        }

        public void mouseExited(MouseEvent evt) {}

        public void mousePressed(MouseEvent evt) {}

        public void mouseReleased(MouseEvent evt) {}
    };
        
    //ACCIONES DEL MENU POPUP
    //Salir de aplicacion
    ActionListener exitListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) { 
            String os = System.getProperty("os.name");
            Date fecha = new Date();
            WriteRead leeEsc = new WriteRead();
            if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
                leeEsc.escribeReg("Liliana terminó de ejecutarsepor orden del usuario. \n"+fecha);
            }
            System.exit(0);
        }
    };
    //Restaurar aplicacion
    ActionListener RestaurarListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {            
            miframe.setVisible(true);
            miframe.setExtendedState( JFrame.NORMAL );
            miframe.repaint();
            System.gc();
            band = true;
            String os = System.getProperty("os.name");
            Date fecha = new Date();
            String fech = ""+fecha.getDate();
            WriteRead leeEsc = new WriteRead();
            if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
                leeEsc.escribeReg("Boton de restaurar presionado \nKassandra está visible de nuevo \n"+fecha);
            }
            
        }
    };
    //Se crean los Items del menu PopUp y se añaden
    MenuItem SalirItem = new MenuItem("Salir");
    SalirItem.addActionListener(exitListener);
    popup.add(SalirItem);

    MenuItem ItemRestaurar = new MenuItem("Restaurar");
    ItemRestaurar.addActionListener(RestaurarListener);
    popup.add(ItemRestaurar);
    trayIcon.setImageAutoSize(true);
    trayIcon.addMouseListener(mouseListener);
    
    //Añade el TrayIcon al SystemTray
    try {
        systemtray.add(trayIcon);
    } catch (AWTException e) {
        System.err.println( "Error:" + e.getMessage() );
    }
  } else {
     JOptionPane.showMessageDialog(null, "System Tray is not Supported","Error",JOptionPane.ERROR_MESSAGE);
  }

    //Cuando se minimiza JFrame, se oculta para que no aparesca en la barra de tareas
     miframe.addWindowListener(new WindowAdapter(){
        @Override
        public void windowIconified(WindowEvent e){
           miframe.setVisible(false);//Se oculta JFrame
           //Se inicia una tarea cuando se minimiza
           band = false;
                      
           
        }
    });

    }

    //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo)
    {
        trayIcon.displayMessage("Liliana dice:", texto, tipo);
    }

    
    
}
