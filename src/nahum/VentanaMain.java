



package nahum;


import com.sun.awt.AWTUtilities;
import com.sun.glass.events.KeyEvent;
import iconific.VentanasEsc;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
/**
 *
 * @author Nahum Flores Gómez
 */
public class VentanaMain extends javax.swing.JFrame implements Runnable{
    private File rec;
    private String os;
    private final Ejecutar ejec = new Ejecutar();
    private final WriteRead leeEsc = new WriteRead();
    private Date fecha = new Date();
    private Calendar calendario;
    private String hora, minutos, segundos,ampm;
    private Thread h1;
    private VentanasEsc escond;
    private boolean band=false;
    /////////////////////////////////////////////////////////////////////////////////////////////
    public VentanaMain() {
        leeEsc.escribeReg("\nIniciado Liliana... \n"+fecha+"\n--------------------------------------");
        initComponents();
        ejec.reproSound("Config/system-starting-up.wav");
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        Shape forma = new RoundRectangle2D.Double(0,0,this.getBounds().width, this.getBounds().height, 30,30);
        
        AWTUtilities.setWindowShape(this, forma);
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        getSystem();
        tamanoPantalla();
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        h1 = new Thread(this);
        h1.start();
        
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/emblem-linux.png")));
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        if(os.equalsIgnoreCase("Windows 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")||os.equalsIgnoreCase("Windows 10")){
            fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Liliana1.png")));
            fondo2.setVisible(false);
            try{
                
                Class clase = Class.forName("com.sun.awt.AWTUtilities");
                Method metodo = clase.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);
                metodo.invoke(clase,this , false);
            }catch(IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e){
                System.err.println(e);
            }
            
        }else{
            fondo2.setVisible(true);
        }
        //this.setLocationRelativeTo(null);
        
        ejec.depende();
        lMedia.setVisible(false);
        lHelp.setVisible(false);
        lTools.setVisible(false);
        lOffice.setVisible(false);
        lWeb.setVisible(false);
        lSoft.setVisible(false);
        lUpdate.setVisible(false);
        labelCMD.setVisible(false);
        getRecPath();
        System.out.println(this.getSize());
        exeWeb();
    }
    ////////////////////////////////////////////////////////////////////////////
    private void exeWeb(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WebShotrCuts().setVisible(true);
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////
    private void tamanoPantalla(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int alto = pantalla.height;
        int ancho = pantalla.width;
        System.out.println("Tamaño: "+ancho+" x "+alto);
        this.setLocation(200, 0);
    }
    ////////////////////////////////////////////////////////////////////////////
    private void eventRegister(){
    }
    ////////////////////////////////////////////////////////////////////////////
    private void getRecPath(){//Obtener la ruta de mi archivo de registro
        String registro="";
        if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
            registro = registro + "Registro Inicio fecha: "+fecha+"\n";
            registro = registro + "--------------------------------------\n";
            registro = registro + "Liliana inició su ejecución a las: "+hora+":"+minutos+":"+segundos+" "+ampm+"\n";
            registro = registro + "--------------------------------------\n";
            leeEsc.escribeReg(registro);
        }
        rec = new File("Registro.rec");
        
    }
    //////////////////////////////////////////////////////////////////////////////
    @Override
    public void run() {//crea un hilo para cambiar la hora del dia
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            labelTime.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        
    }
    /////////////////////////////////////////////////////////////////////////////
    
    /////////////////////////////////////////////////////////////////////////////
    public void calcula() {//obtiene la hora del dia
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();


        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR);
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR) > 9 ? "" + calendario.get(Calendar.HOUR) : "0" + calendario.get(Calendar.HOUR);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    ///////////////////////////////////////////////////////////////////////////////
    /** Creates new form VentanaMain with a Thread*/
    
    
    private void getSystem(){
        
        os=System.getProperty("os.name");
     }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bMultimedia = new javax.swing.JButton();
        Boffice = new javax.swing.JButton();
        bUtil = new javax.swing.JButton();
        lMedia = new javax.swing.JLabel();
        lWeb = new javax.swing.JLabel();
        lTools = new javax.swing.JLabel();
        lOffice = new javax.swing.JLabel();
        bHelp = new javax.swing.JButton();
        lHelp = new javax.swing.JLabel();
        bExec = new javax.swing.JButton();
        labelTime = new javax.swing.JLabel();
        labelCMD = new javax.swing.JLabel();
        bMinimize = new javax.swing.JButton();
        bSoft = new javax.swing.JButton();
        lSoft = new javax.swing.JLabel();
        bWeb = new javax.swing.JButton();
        bUpdate = new javax.swing.JButton();
        bShutdown = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lUpdate = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();
        fondo2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Liliana");
        setFont(new java.awt.Font("1942 report", 1, 10)); // NOI18N
        setMaximumSize(new java.awt.Dimension(800, 130));
        setMinimumSize(new java.awt.Dimension(800, 130));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(800, 130));
        setMaximumSize(new java.awt.Dimension(1210, 130));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bMultimedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Media-Player.png"))); // NOI18N
        bMultimedia.setContentAreaFilled(false);
        bMultimedia.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bMultimediaMouseMoved(evt);
            }
        });
        bMultimedia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bMultimediaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bMultimediaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bMultimediaMouseReleased(evt);
            }
        });
        bMultimedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMultimediaActionPerformed(evt);
            }
        });
        getContentPane().add(bMultimedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 17, 60, -1));

        Boffice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/oficina.png"))); // NOI18N
        Boffice.setContentAreaFilled(false);
        Boffice.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                BofficeMouseMoved(evt);
            }
        });
        Boffice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BofficeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BofficeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                BofficeMouseReleased(evt);
            }
        });
        Boffice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BofficeActionPerformed(evt);
            }
        });
        getContentPane().add(Boffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 50, 60));

        bUtil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/herramientas.png"))); // NOI18N
        bUtil.setContentAreaFilled(false);
        bUtil.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bUtilMouseMoved(evt);
            }
        });
        bUtil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bUtilMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bUtilMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bUtilMouseReleased(evt);
            }
        });
        bUtil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUtilActionPerformed(evt);
            }
        });
        getContentPane().add(bUtil, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 60, 60));

        lMedia.setForeground(new java.awt.Color(0, 255, 255));
        lMedia.setText("Media");
        lMedia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lMedia, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        lWeb.setForeground(new java.awt.Color(51, 51, 255));
        lWeb.setText("Internet");
        lWeb.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lWeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, -1));

        lTools.setForeground(new java.awt.Color(254, 254, 254));
        lTools.setText("Tools");
        lTools.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lTools, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, -1));

        lOffice.setForeground(new java.awt.Color(255, 0, 76));
        lOffice.setText("Oficina");
        lOffice.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lOffice, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        bHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png"))); // NOI18N
        bHelp.setContentAreaFilled(false);
        bHelp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bHelpMouseMoved(evt);
            }
        });
        bHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bHelpMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bHelpMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bHelpMouseReleased(evt);
            }
        });
        bHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHelpActionPerformed(evt);
            }
        });
        getContentPane().add(bHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 60, 60));

        lHelp.setForeground(new java.awt.Color(0, 255, 57));
        lHelp.setText("Ayuda");
        lHelp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, -1, -1));

        bExec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Oil-icono-terminal.png"))); // NOI18N
        bExec.setContentAreaFilled(false);
        bExec.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bExecMouseMoved(evt);
            }
        });
        bExec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bExecMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bExecMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bExecMouseReleased(evt);
            }
        });
        bExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExecActionPerformed(evt);
            }
        });
        getContentPane().add(bExec, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 50, 50));

        labelTime.setForeground(new java.awt.Color(0, 255, 255));
        getContentPane().add(labelTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 130, 30));

        labelCMD.setForeground(new java.awt.Color(255, 255, 255));
        labelCMD.setText("Insert comand");
        labelCMD.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(labelCMD, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        bMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimizar.png"))); // NOI18N
        bMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bMinimizeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bMinimizeMouseReleased(evt);
            }
        });
        bMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMinimizeActionPerformed(evt);
            }
        });
        getContentPane().add(bMinimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 30, 20));

        bSoft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cube.png"))); // NOI18N
        bSoft.setContentAreaFilled(false);
        bSoft.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bSoftMouseMoved(evt);
            }
        });
        bSoft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bSoftMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bSoftMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bSoftMouseReleased(evt);
            }
        });
        bSoft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSoftActionPerformed(evt);
            }
        });
        getContentPane().add(bSoft, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 60, 60));

        lSoft.setForeground(new java.awt.Color(255, 255, 0));
        lSoft.setText("Software");
        lSoft.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lSoft, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, -1));

        bWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newsr.png"))); // NOI18N
        bWeb.setContentAreaFilled(false);
        bWeb.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bWebMouseMoved(evt);
            }
        });
        bWeb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bWebMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bWebMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bWebMouseReleased(evt);
            }
        });
        bWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bWebActionPerformed(evt);
            }
        });
        getContentPane().add(bWeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 60, 60));

        bUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        bUpdate.setContentAreaFilled(false);
        bUpdate.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bUpdateMouseMoved(evt);
            }
        });
        bUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bUpdateMouseExited(evt);
            }
        });
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(bUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 40, 60));

        bShutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicio.png"))); // NOI18N
        bShutdown.setBorderPainted(false);
        bShutdown.setContentAreaFilled(false);
        bShutdown.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bShutdownMouseMoved(evt);
            }
        });
        bShutdown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bShutdownMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bShutdownMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bShutdownMouseReleased(evt);
            }
        });
        bShutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bShutdownActionPerformed(evt);
            }
        });
        getContentPane().add(bShutdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 50, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Apagar PC");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        lUpdate.setForeground(new java.awt.Color(0, 153, 153));
        lUpdate.setText("Actualizar Liliana");
        lUpdate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lUpdate.setFocusable(false);
        getContentPane().add(lUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, -1, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Liliana1.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 90));

        fondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconofondo.jpg"))); // NOI18N
        getContentPane().add(fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 130));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bMultimediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMultimediaActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Multimedia dialog = new Multimedia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    
                });
                dialog.setVisible(true);
            }
        });
        System.gc();
    }//GEN-LAST:event_bMultimediaActionPerformed

    private void BofficeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BofficeActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Office dialog = new Office(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {

                    }
                });
                dialog.setVisible(true);
            }
        });
        System.gc();
    }//GEN-LAST:event_BofficeActionPerformed

    private void bUtilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUtilActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Utils dialog = new Utils(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {

                    }
                });
                dialog.setVisible(true);
            }
        });
        
        System.gc();
    }//GEN-LAST:event_bUtilActionPerformed

    private void bHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHelpActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Info dialog = new Info(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
        String registro="";
        if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
            registro = registro + "Registro fecha: "+fecha+"\n";
            registro = registro + "--------------------------------------\n";
            registro = registro + "Botón de ayuda presionado \nse abrió el mensaje de ayuda a las:"+hora+":"+minutos+":"+segundos+" "+ampm+"\n";
            registro = registro + "--------------------------------------\n";
            leeEsc.escribeReg(registro);
        }
    }//GEN-LAST:event_bHelpActionPerformed

    private void bMultimediaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMultimediaMousePressed
        bMultimedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Media-PlayerP.png")));
    }//GEN-LAST:event_bMultimediaMousePressed

    private void bMultimediaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMultimediaMouseReleased
        bMultimedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Media-Player.png")));
    }//GEN-LAST:event_bMultimediaMouseReleased

    private void bMultimediaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMultimediaMouseMoved
        lMedia.setVisible(true);
        bMultimedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Media-PlayerL.png")));
    }//GEN-LAST:event_bMultimediaMouseMoved

    private void bMultimediaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMultimediaMouseExited
        lMedia.setVisible(false);
        bMultimedia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Media-Player.png")));
    }//GEN-LAST:event_bMultimediaMouseExited

    private void bUtilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUtilMouseExited
        bUtil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/herramientas.png")));
        lTools.setVisible(false);
    }//GEN-LAST:event_bUtilMouseExited

    private void bUtilMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUtilMouseMoved
        bUtil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/herramientasL.png")));
        lTools.setVisible(true);
    }//GEN-LAST:event_bUtilMouseMoved

    private void BofficeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BofficeMouseMoved
        Boffice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/oficinaL.png")));
        lOffice.setVisible(true);
    }//GEN-LAST:event_BofficeMouseMoved

    private void BofficeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BofficeMouseExited
        Boffice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/oficina.png")));
        lOffice.setVisible(false);
    }//GEN-LAST:event_BofficeMouseExited

    private void bHelpMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHelpMouseMoved
        
        lHelp.setVisible(true);
    }//GEN-LAST:event_bHelpMouseMoved

    private void bHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHelpMouseExited
        
        lHelp.setVisible(false);
    }//GEN-LAST:event_bHelpMouseExited

    private void bHelpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHelpMousePressed
        
    }//GEN-LAST:event_bHelpMousePressed

    private void bHelpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHelpMouseReleased
        
    }//GEN-LAST:event_bHelpMouseReleased

    private void bExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExecActionPerformed
        String comando;        
        comando = JOptionPane.showInputDialog(this, "Ingrese comando manualmente","Ejecutar",JOptionPane.OK_CANCEL_OPTION);
        
        if(!comando.equals("")){
            if (comando.equalsIgnoreCase("salir")) {
                ///////////////////////////////////////////////////////////////////////
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        Confirm dialog = new Confirm(new javax.swing.JFrame(), true);
                        
                        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent e) {
                        
                            }
                        });
                        dialog.setVisible(true);
                    }
                });
                
                ///////////////////////////////////////////////////////////////////////
                
                
            }else{
                String registro="";
                if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
                    registro = registro + "Registro fecha: "+fecha+"\n";
                    registro = registro + "--------------------------------------\n";
                    registro = registro + "Botón de Insert Command presionado \nSe ejecutó el comando: "+comando+"\nHora "+hora+":"+minutos+":"+segundos+" "+ampm+"\n";
                    registro = registro + "--------------------------------------\n";
                    leeEsc.escribeReg(registro);
                }
                ejec.exeGeneric(comando);
            }
        }
        System.gc();
    }//GEN-LAST:event_bExecActionPerformed

    private void bMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMinimizeActionPerformed
        this.setExtendedState(ICONIFIED);
        if (band == false) {
            escond = new VentanasEsc(this);
            band = true;
        }
        if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
            String registro="";
            registro = registro + "Registro fecha: "+fecha+"\n";
            registro = registro + "--------------------------------------\n";
            registro = registro + "Botón Minimizar presionado \nse minimizó el programa a las:"+hora+":"+minutos+":"+segundos+" "+ampm+"\n";
            registro = registro + "--------------------------------------\n";
            leeEsc.escribeReg(registro);
        }
        System.gc();
    }//GEN-LAST:event_bMinimizeActionPerformed

    private void bUtilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUtilMousePressed
        bUtil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/herramientasP.png")));
    }//GEN-LAST:event_bUtilMousePressed

    private void bUtilMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUtilMouseReleased
        bUtil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/herramientas.png")));
    }//GEN-LAST:event_bUtilMouseReleased

    private void BofficeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BofficeMousePressed
        Boffice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/oficinaP.png")));
    }//GEN-LAST:event_BofficeMousePressed

    private void BofficeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BofficeMouseReleased
        Boffice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/oficina.png")));
    }//GEN-LAST:event_BofficeMouseReleased

    private void bMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMinimizeMousePressed
        bMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimizarL.png")));
    }//GEN-LAST:event_bMinimizeMousePressed

    private void bMinimizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMinimizeMouseReleased
        bMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimizar.png")));
    }//GEN-LAST:event_bMinimizeMouseReleased

    private void bSoftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSoftActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Software dialog = new Software(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
        leeEsc.escribeReg("Registro fecha: "+fecha+"\n"+
                          "--------------------------------------\n"+
                          "Botón Software presionado, abriendo ventana de software"+
                          "--------------------------------------\n");
    }//GEN-LAST:event_bSoftActionPerformed

    private void bSoftMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSoftMouseMoved
        bSoft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cubeL.png")));
        lSoft.setVisible(true);
    }//GEN-LAST:event_bSoftMouseMoved

    private void bSoftMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSoftMouseExited
        bSoft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cube.png")));
        lSoft.setVisible(false);
    }//GEN-LAST:event_bSoftMouseExited

    private void bSoftMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSoftMousePressed
        bSoft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cubeP.png")));
    }//GEN-LAST:event_bSoftMousePressed

    private void bSoftMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSoftMouseReleased
        bSoft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cube.png")));
    }//GEN-LAST:event_bSoftMouseReleased

    private void bExecMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExecMouseMoved
        bExec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Oil-icono-terminalL.png")));
        labelCMD.setVisible(true);
    }//GEN-LAST:event_bExecMouseMoved

    private void bExecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExecMouseExited
        bExec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Oil-icono-terminal.png")));
        labelCMD.setVisible(false);
    }//GEN-LAST:event_bExecMouseExited

    private void bExecMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExecMousePressed
        bExec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Oil-icono-terminalP.png")));
    }//GEN-LAST:event_bExecMousePressed

    private void bExecMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExecMouseReleased
        bExec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Oil-icono-terminal.png")));
    }//GEN-LAST:event_bExecMouseReleased

    private void bWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bWebActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Web dialog = new Web(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_bWebActionPerformed

    private void bWebMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWebMouseMoved
        bWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newsrL.png")));
        lWeb.setVisible(true);
    }//GEN-LAST:event_bWebMouseMoved

    private void bWebMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWebMouseExited
        bWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newsr.png")));
        lWeb.setVisible(false);
    }//GEN-LAST:event_bWebMouseExited

    private void bWebMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWebMousePressed
        bWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newsrP.png")));
    }//GEN-LAST:event_bWebMousePressed

    private void bWebMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWebMouseReleased
        bWeb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newsr.png")));
    }//GEN-LAST:event_bWebMouseReleased

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        String registro="";
        if (os.equalsIgnoreCase("Windos 7")||os.equalsIgnoreCase("Windows 8")||os.equalsIgnoreCase("Windows 8.1")) {
            registro = registro + "Registro fecha: "+fecha+"\n";
            registro = registro + "--------------------------------------\n";
            registro = registro + "Botón Update presionado \nSe abrió el enlace a las:"+hora+":"+minutos+":"+segundos+" "+ampm+"\n";
            registro = registro + "--------------------------------------\n";
            leeEsc.escribeReg(registro);
        }
        ejec.knowVersion();
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bShutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bShutdownActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShutdownSistem dialog = new ShutdownSistem(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_bShutdownActionPerformed

    private void bShutdownMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bShutdownMouseMoved
        bShutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioL.png")));
    }//GEN-LAST:event_bShutdownMouseMoved

    private void bShutdownMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bShutdownMousePressed
        bShutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicioP.png")));
    }//GEN-LAST:event_bShutdownMousePressed

    private void bShutdownMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bShutdownMouseReleased
        bShutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicio.png")));
    }//GEN-LAST:event_bShutdownMouseReleased

    private void bShutdownMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bShutdownMouseExited
        bShutdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/inicio.png")));
    }//GEN-LAST:event_bShutdownMouseExited

    private void bUpdateMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUpdateMouseMoved
        lUpdate.setVisible(true);
    }//GEN-LAST:event_bUpdateMouseMoved

    private void bUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUpdateMouseExited
        lUpdate.setVisible(false);
    }//GEN-LAST:event_bUpdateMouseExited

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boffice;
    private javax.swing.JButton bExec;
    private javax.swing.JButton bHelp;
    private javax.swing.JButton bMinimize;
    private javax.swing.JButton bMultimedia;
    private javax.swing.JButton bShutdown;
    private javax.swing.JButton bSoft;
    private javax.swing.JButton bUpdate;
    private javax.swing.JButton bUtil;
    private javax.swing.JButton bWeb;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel fondo2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lHelp;
    private javax.swing.JLabel lMedia;
    private javax.swing.JLabel lOffice;
    private javax.swing.JLabel lSoft;
    private javax.swing.JLabel lTools;
    private javax.swing.JLabel lUpdate;
    private javax.swing.JLabel lWeb;
    private javax.swing.JLabel labelCMD;
    private javax.swing.JLabel labelTime;
    // End of variables declaration//GEN-END:variables

}
