

package nahum;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author NahumFrog
 */
public class Arduino extends javax.swing.JFrame {
                                 //  0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25
    private final char CARDMIN [] ={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private final char CARDMAY [] ={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    //private final int enviaInt [] ={1,2,3,4,5,6,7,8,9,0};
    private String puerto="";
    private final int TIMEOUT = 2000;
    private final int DATA_RATE = 9600;
    private short segundos;
    private boolean ban= false;
    private OutputStream salida= null;
    private SerialPort puertoSerial;
    private Timer tiempo;
    private final Ejecutar EJEC = new Ejecutar();
    /**
     * Creates new form Arduino
     */
    public Arduino() {
        initComponents();
        bConect.requestFocus();
        temporizar();
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo.png")));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Close serial conection
     */
    private void closeConexion(){//cierra la conexion con el puerto serial
        puertoSerial.close();
        puerto = "";
        JOptionPane.showMessageDialog(null, "La conexión terminó","Conexion",JOptionPane.INFORMATION_MESSAGE);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void recibeTeclado(java.awt.event.KeyEvent evt){//Lee los datos directamente desde el teclado
        if (toggTeclado.isSelected()) {
            if (evt.getKeyCode()==KeyEvent.VK_1||evt.getKeyCode()==KeyEvent.VK_NUMPAD1) {
                sendCharNum(1,'1');
            }
            if (evt.getKeyCode()==KeyEvent.VK_2||evt.getKeyCode()==KeyEvent.VK_NUMPAD2) {
                sendCharNum(2,'2');
            }
            if (evt.getKeyCode()==KeyEvent.VK_3||evt.getKeyCode()==KeyEvent.VK_NUMPAD3) {
                sendCharNum(3,'3');
            }
            if (evt.getKeyCode()==KeyEvent.VK_4||evt.getKeyCode()==KeyEvent.VK_NUMPAD4) {
                sendCharNum(4,'4');
            }
            if (evt.getKeyCode()==KeyEvent.VK_5||evt.getKeyCode()==KeyEvent.VK_NUMPAD5) {
                sendCharNum(5,'5');
            }
            if (evt.getKeyCode()==KeyEvent.VK_6||evt.getKeyCode()==KeyEvent.VK_NUMPAD6) {
                sendCharNum(6,'6');
            }
            if (evt.getKeyCode()==KeyEvent.VK_7||evt.getKeyCode()==KeyEvent.VK_NUMPAD7) {
                sendCharNum(7,'7');
            }
            if (evt.getKeyCode()==KeyEvent.VK_8||evt.getKeyCode()==KeyEvent.VK_NUMPAD8) {
                sendCharNum(8,'8');
            }
            if (evt.getKeyCode()==KeyEvent.VK_9||evt.getKeyCode()==KeyEvent.VK_NUMPAD9) {
                sendCharNum(9,'9');
            }
            if (evt.getKeyCode()==KeyEvent.VK_0||evt.getKeyCode()==KeyEvent.VK_NUMPAD0) {
                sendCharNum(0,'0');
            }
            if (evt.getKeyCode()==KeyEvent.VK_A) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[0]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[0]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_B) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[1]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[1]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_C) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[2]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[2]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_D) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[3]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[3]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_E) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[4]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[4]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_F) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[5]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[5]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_G) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[6]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[6]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_H) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[7]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[7]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_I) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[8]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[8]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_J) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[9]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[9]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_K) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[10]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[10]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_L) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[11]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[11]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_M) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[12]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[12]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_N) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[13]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[13]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_O) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[14]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[14]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_P) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[15]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[15]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_Q) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[16]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[16]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_R) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[17]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[17]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_S) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[18]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[18]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_T) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[19]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[19]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_U) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[20]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[20]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_V) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[21]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[21]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_W) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[22]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[22]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_X) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[23]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[23]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_Y) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[24]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[24]);
                }
            }
            if (evt.getKeyCode()==KeyEvent.VK_Z) {
                if (rdMin.isSelected()) {
                enviaDatos(CARDMIN[25]);
                }
                if (rdMay.isSelected()) {
                    enviaDatos(CARDMAY[25]);
                }
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void temporizar(){//cada cierto tiempo hace request focus en el objeto que lee los datos del teclado
        segundos = 20;
        tiempo = new Timer(1000, new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            segundos--;
            if (segundos==-1) {
                segundos = 10;
                toggTeclado.requestFocus();
                
            }
        }
    });
        tiempo.start();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initConexion(){
        CommPortIdentifier portID = null;
        System.out.println("pasamos la linea");
        Enumeration portEnum=CommPortIdentifier.getPortIdentifiers();
        try{
            
            while(portEnum.hasMoreElements()){
                CommPortIdentifier actualPortID = (CommPortIdentifier) portEnum.nextElement();
                System.out.println("puerto detectados: "+actualPortID.getName());
                if (puerto.equals(actualPortID.getName())) {
                    portID=actualPortID;
                    System.out.println("conectado");
                    JOptionPane.showMessageDialog(null, "Conexion estabecida...","Conexion",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (portID==null) {
            System.out.println("No hay puerto");
        }
        }catch(Exception e){//se supone que aqui se capturaran los errores si no encuentra la librería
            System.out.println(e);
            EJEC.writeInLog(e.toString()+"\nProbablemente error con la librería");
        }
        
        try{
            puertoSerial = (SerialPort) portID.open(this.getClass().getName(), TIMEOUT);
            //configurar parametros
            
            puertoSerial.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            salida = puertoSerial.getOutputStream();
            
        }catch(PortInUseException PUE){
            mostrarError(PUE.getMessage());
            System.out.println(PUE);
            this.dispose();
        }catch(UnsupportedCommOperationException CommE){
            System.out.println(CommE);
            this.dispose();
        }catch(IOException IOe){
            System.out.println(IOe);
            this.dispose();
        }
        
    }
    /////////////////////////////////////////////////////////////////////////////////////
    private void enviaDatos(char data){
        try{
            salida.write(data);
        }catch(IOException e){
            mostrarError("Error");
            System.out.println(e);
        }
    }
    public void mostrarError(String msg){
        JOptionPane.showMessageDialog(this, msg, "ERROR",JOptionPane.ERROR_MESSAGE);
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public void sendCharNum(int data, char data1){
        if (rNums.isSelected()) {
            try{
                salida.write(data);
            }catch(IOException e){
                mostrarError("Error");
                System.out.println(e);
            }
        }else{
            enviaDatos(data1);
        }
    }
    
    private void blockB0(){
        ban = true;
        b0.setEnabled(false);
    }
    private void releaseB0(){
        ban = false;
        b0.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gLetras = new javax.swing.ButtonGroup();
        gNumsChars = new javax.swing.ButtonGroup();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b0 = new javax.swing.JButton();
        bQ = new javax.swing.JButton();
        bW = new javax.swing.JButton();
        bE = new javax.swing.JButton();
        bR = new javax.swing.JButton();
        bT = new javax.swing.JButton();
        bY = new javax.swing.JButton();
        bU = new javax.swing.JButton();
        bI = new javax.swing.JButton();
        bO = new javax.swing.JButton();
        bP = new javax.swing.JButton();
        bA = new javax.swing.JButton();
        bS = new javax.swing.JButton();
        bD = new javax.swing.JButton();
        bF = new javax.swing.JButton();
        bG = new javax.swing.JButton();
        bH = new javax.swing.JButton();
        bJ = new javax.swing.JButton();
        bK = new javax.swing.JButton();
        bL = new javax.swing.JButton();
        bZ = new javax.swing.JButton();
        bX = new javax.swing.JButton();
        bC = new javax.swing.JButton();
        bV = new javax.swing.JButton();
        bB = new javax.swing.JButton();
        bN = new javax.swing.JButton();
        bM = new javax.swing.JButton();
        bSpace = new javax.swing.JButton();
        bConect = new javax.swing.JButton();
        bDesconect = new javax.swing.JButton();
        bCerrar = new javax.swing.JButton();
        bWebArduino = new javax.swing.JButton();
        txtPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rdMay = new javax.swing.JRadioButton();
        rdMin = new javax.swing.JRadioButton();
        tBBlock0 = new javax.swing.JToggleButton();
        rChars = new javax.swing.JRadioButton();
        rNums = new javax.swing.JRadioButton();
        toggTeclado = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Arduino");
        setResizable(false);

        b1.setText("1");
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b1MouseReleased(evt);
            }
        });

        b2.setText("2");
        b2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b2MouseReleased(evt);
            }
        });

        b3.setText("3");
        b3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b3MouseReleased(evt);
            }
        });

        b4.setText("4");
        b4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b4MouseReleased(evt);
            }
        });

        b5.setText("5");
        b5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b5MouseReleased(evt);
            }
        });

        b6.setText("6");
        b6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b6MouseReleased(evt);
            }
        });

        b7.setText("7");
        b7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b7MouseReleased(evt);
            }
        });

        b8.setText("8");
        b8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b8MouseReleased(evt);
            }
        });

        b9.setText("9");
        b9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b9MouseReleased(evt);
            }
        });

        b0.setText("0");
        b0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                b0MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b0MouseReleased(evt);
            }
        });

        bQ.setText("Q");
        bQ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bQMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bQMouseReleased(evt);
            }
        });

        bW.setText("W");
        bW.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bWMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bWMouseReleased(evt);
            }
        });

        bE.setText("E");
        bE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bEMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bEMouseReleased(evt);
            }
        });

        bR.setText("R");
        bR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bRMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bRMouseReleased(evt);
            }
        });

        bT.setText("T");
        bT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bTMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bTMouseReleased(evt);
            }
        });

        bY.setText("Y");
        bY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bYMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bYMouseReleased(evt);
            }
        });

        bU.setText("U");
        bU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bUMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bUMouseReleased(evt);
            }
        });

        bI.setText("I");
        bI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bIMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bIMouseReleased(evt);
            }
        });

        bO.setText("O");
        bO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bOMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bOMouseReleased(evt);
            }
        });

        bP.setText("P");
        bP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bPMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bPMouseReleased(evt);
            }
        });

        bA.setText("A");
        bA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bAMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bAMouseReleased(evt);
            }
        });
        bA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bAKeyPressed(evt);
            }
        });

        bS.setText("S");
        bS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bSMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bSMouseReleased(evt);
            }
        });

        bD.setText("D");
        bD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bDMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bDMouseReleased(evt);
            }
        });

        bF.setText("F");
        bF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bFMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bFMouseReleased(evt);
            }
        });

        bG.setText("G");
        bG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bGMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bGMouseReleased(evt);
            }
        });

        bH.setText("H");
        bH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bHMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bHMouseReleased(evt);
            }
        });

        bJ.setText("J");
        bJ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bJMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bJMouseReleased(evt);
            }
        });

        bK.setText("K");
        bK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bKMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bKMouseReleased(evt);
            }
        });

        bL.setText("L");
        bL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bLMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bLMouseReleased(evt);
            }
        });

        bZ.setText("Z");
        bZ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bZMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bZMouseReleased(evt);
            }
        });

        bX.setText("X");
        bX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bXMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bXMouseReleased(evt);
            }
        });

        bC.setText("C");
        bC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bCMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bCMouseReleased(evt);
            }
        });

        bV.setText("V");
        bV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bVMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bVMouseReleased(evt);
            }
        });

        bB.setText("B");
        bB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bBMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bBMouseReleased(evt);
            }
        });
        bB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bBKeyPressed(evt);
            }
        });

        bN.setText("N");
        bN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bNMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bNMouseReleased(evt);
            }
        });

        bM.setText("M");
        bM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bMMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bMMouseReleased(evt);
            }
        });

        bSpace.setText("Espacio");

        bConect.setText("Conectar");
        bConect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConectActionPerformed(evt);
            }
        });

        bDesconect.setText("Desconectar");
        bDesconect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDesconectActionPerformed(evt);
            }
        });

        bCerrar.setText("Cerrar");
        bCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCerrarActionPerformed(evt);
            }
        });
        bCerrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bCerrarKeyPressed(evt);
            }
        });

        bWebArduino.setText("Web Arduino");
        bWebArduino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bWebArduinoActionPerformed(evt);
            }
        });

        txtPort.setText("Núm. de Puerto");
        txtPort.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPortFocusGained(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Arduino_Uno_logo.png"))); // NOI18N

        gLetras.add(rdMay);
        rdMay.setText("Mayúsculas");

        gLetras.add(rdMin);
        rdMin.setSelected(true);
        rdMin.setText("Minúsculas");

        tBBlock0.setText("Block 0");
        tBBlock0.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tBBlock0ItemStateChanged(evt);
            }
        });

        gNumsChars.add(rChars);
        rChars.setSelected(true);
        rChars.setText("Enviar Caracteres");

        gNumsChars.add(rNums);
        rNums.setText("Enviar enteros");

        toggTeclado.setText("Leer del Teclado");
        toggTeclado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                toggTecladoFocusLost(evt);
            }
        });
        toggTeclado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                toggTecladoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                toggTecladoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bCerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bWebArduino))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bQ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bW)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bU)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bI)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toggTeclado))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bA)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bS)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bD)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bG)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bH)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bJ))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bZ)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bX)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bC)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bV)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bN)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bM)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bSpace)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(bK)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bL))))
                                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bConect)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bDesconect)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rdMay)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdMin))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rChars)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rNums)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(b0)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tBBlock0)))
                        .addGap(0, 35, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b1)
                    .addComponent(b2)
                    .addComponent(b3)
                    .addComponent(b4)
                    .addComponent(b5)
                    .addComponent(b6)
                    .addComponent(b7)
                    .addComponent(b8)
                    .addComponent(b9)
                    .addComponent(b0)
                    .addComponent(tBBlock0))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bQ)
                    .addComponent(bW)
                    .addComponent(bE)
                    .addComponent(bR)
                    .addComponent(bT)
                    .addComponent(bY)
                    .addComponent(bU)
                    .addComponent(bI)
                    .addComponent(bO)
                    .addComponent(bP)
                    .addComponent(toggTeclado))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bA)
                            .addComponent(bS)
                            .addComponent(bD)
                            .addComponent(bF)
                            .addComponent(bG)
                            .addComponent(bH)
                            .addComponent(bJ)
                            .addComponent(bK)
                            .addComponent(bL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bZ)
                            .addComponent(bX)
                            .addComponent(bC)
                            .addComponent(bV)
                            .addComponent(bB)
                            .addComponent(bN)
                            .addComponent(bM)
                            .addComponent(bSpace))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bConect)
                            .addComponent(bDesconect)
                            .addComponent(rdMay)
                            .addComponent(rdMin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bWebArduino)
                    .addComponent(bCerrar)
                    .addComponent(rChars)
                    .addComponent(rNums))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bWebArduinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bWebArduinoActionPerformed
        
        String dir = "http://www.arduino.cc/es/";
        try{
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(dir));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    
    }//GEN-LAST:event_bWebArduinoActionPerformed

    private void bCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarActionPerformed
        this.dispose();
        closeConexion();
    }//GEN-LAST:event_bCerrarActionPerformed

    private void txtPortFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPortFocusGained
        txtPort.setText("");
    }//GEN-LAST:event_txtPortFocusGained


    private void tBBlock0ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tBBlock0ItemStateChanged
        if (tBBlock0.isSelected()) {
            blockB0();
            //JOptionPane.showMessageDialog(null, "El botón 0 fue bloqueado \nEl valor por defecto para LOW (OFF) es ahora 0\nDesbloquee el botón 0 para cambiar esto","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else{
            releaseB0();
        }
    }//GEN-LAST:event_tBBlock0ItemStateChanged

    private void bConectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConectActionPerformed
        puerto = txtPort.getText();
        if (!puerto.equals("")&&puerto!=null&&!puerto.equalsIgnoreCase("Núm. de Puerto")) {
            try{
                initConexion();
                txtPort.setEditable(false);
                toggTeclado.requestFocus();
            }catch(java.lang.NullPointerException e){
                JOptionPane.showMessageDialog(null, "No se detecta su Arduino en el puerto seleccionado,\npor favor, verifique que su arduino esta conectado, si\nlo está, verifique que sea el puerto correcto","Error",JOptionPane.ERROR_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Puerto incorrecto, por favor escriba el puerto correcto","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bConectActionPerformed

    private void bDesconectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDesconectActionPerformed
        closeConexion();
        txtPort.setEditable(true);
    }//GEN-LAST:event_bDesconectActionPerformed

    private void b0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b0MousePressed
        if (ban != false && !b0.isEnabled()) {
            JOptionPane.showMessageDialog(null, "Botón 0 bloqueado, \nEsto quiere decir que el caracter que se envía al arduino\nal soltar cualquier botón será 0","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }else{
            sendCharNum(0,'0');
        }
    }//GEN-LAST:event_b0MousePressed

    private void b0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b0MouseReleased
        if (ban == false && b0.isEnabled()) {
            
        }else{
            JOptionPane.showMessageDialog(null, "Botón 0 bloqueado, El valor por defecto para LOW (OFF) es 0\nDesbloquee el botón 0 para cambiar esto","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_b0MouseReleased

    private void b1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MousePressed
        sendCharNum(1,'1');
    }//GEN-LAST:event_b1MousePressed

    private void b1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b1MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b1MouseReleased

    private void b2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b2MousePressed
        sendCharNum(2,'2');
    }//GEN-LAST:event_b2MousePressed

    private void b2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b2MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
        }else{
            
        }
    }//GEN-LAST:event_b2MouseReleased

    private void b3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b3MousePressed
        sendCharNum(3,'3');
    }//GEN-LAST:event_b3MousePressed

    private void b3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b3MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b3MouseReleased

    private void b4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b4MousePressed
        sendCharNum(4,'4');
    }//GEN-LAST:event_b4MousePressed

    private void b4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b4MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b4MouseReleased

    private void b5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b5MousePressed
        sendCharNum(5,'5');
    }//GEN-LAST:event_b5MousePressed

    private void b5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b5MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b5MouseReleased

    private void b6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b6MousePressed
        sendCharNum(6,'6');
    }//GEN-LAST:event_b6MousePressed

    private void b6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b6MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b6MouseReleased

    private void b7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b7MousePressed
        sendCharNum(7,'7');
    }//GEN-LAST:event_b7MousePressed

    private void b7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b7MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b7MouseReleased

    private void b8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b8MousePressed
        sendCharNum(8,'8');
    }//GEN-LAST:event_b8MousePressed

    private void b8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b8MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b8MouseReleased

    private void b9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b9MousePressed
        sendCharNum(9,'9');
    }//GEN-LAST:event_b9MousePressed

    private void b9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_b9MouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_b9MouseReleased

    private void bQMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bQMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[16]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[16]);
        }
    }//GEN-LAST:event_bQMousePressed

    private void bQMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bQMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bQMouseReleased

    private void bWMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[22]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[22]);
        }
    }//GEN-LAST:event_bWMousePressed

    private void bWMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bWMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bWMouseReleased

    private void bEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bEMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[4]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[4]);
        }
    }//GEN-LAST:event_bEMousePressed

    private void bEMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bEMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bEMouseReleased

    private void bRMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[17]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[17]);
        }
    }//GEN-LAST:event_bRMousePressed

    private void bRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bRMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bRMouseReleased

    private void bTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bTMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[19]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[19]);
        }
    }//GEN-LAST:event_bTMousePressed

    private void bTMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bTMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bTMouseReleased

    private void bYMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bYMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[24]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[24]);
        }
    }//GEN-LAST:event_bYMousePressed

    private void bYMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bYMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bYMouseReleased

    private void bUMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[20]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[20]);
        }
    }//GEN-LAST:event_bUMousePressed

    private void bUMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bUMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bUMouseReleased

    private void bIMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bIMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[8]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[8]);
        }
    }//GEN-LAST:event_bIMousePressed

    private void bIMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bIMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bIMouseReleased

    private void bOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bOMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[14]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[14]);
        }
    }//GEN-LAST:event_bOMousePressed

    private void bOMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bOMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bOMouseReleased

    private void bPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[15]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[15]);
        }
    }//GEN-LAST:event_bPMousePressed

    private void bPMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bPMouseReleased

    private void bAMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[0]);
            
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[0]);
        }
    }//GEN-LAST:event_bAMousePressed

    private void bAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bAMouseReleased

    private void bSMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[18]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[18]);
        }
    }//GEN-LAST:event_bSMousePressed

    private void bSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bSMouseReleased

    private void bDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[3]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[3]);
        }
    }//GEN-LAST:event_bDMousePressed

    private void bDMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bDMouseReleased

    private void bFMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[5]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[5]);
        }
    }//GEN-LAST:event_bFMousePressed

    private void bFMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bFMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bFMouseReleased

    private void bGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bGMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[6]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[6]);
        }
    }//GEN-LAST:event_bGMousePressed

    private void bGMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bGMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bGMouseReleased

    private void bHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[7]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[7]);
        }
    }//GEN-LAST:event_bHMousePressed

    private void bHMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bHMouseReleased

    private void bJMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bJMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[9]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[9]);
        }
    }//GEN-LAST:event_bJMousePressed

    private void bJMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bJMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bJMouseReleased

    private void bKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bKMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[10]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[10]);
        }
    }//GEN-LAST:event_bKMousePressed

    private void bKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bKMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bKMouseReleased

    private void bLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLMousePressed
         if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[11]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[11]);
        }
    }//GEN-LAST:event_bLMousePressed

    private void bLMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bLMouseReleased

    private void bZMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bZMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[25]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[25]);
        }
    }//GEN-LAST:event_bZMousePressed

    private void bZMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bZMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bZMouseReleased

    private void bXMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bXMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[23]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[23]);
        }
    }//GEN-LAST:event_bXMousePressed

    private void bXMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bXMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bXMouseReleased

    private void bCMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bCMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[2]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[2]);
        }
    }//GEN-LAST:event_bCMousePressed

    private void bCMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bCMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bCMouseReleased

    private void bVMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bVMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[21]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[21]);
        }
    }//GEN-LAST:event_bVMousePressed

    private void bVMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bVMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bVMouseReleased

    private void bBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bBMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[1]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[1]);
        }
    }//GEN-LAST:event_bBMousePressed

    private void bBMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bBMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bBMouseReleased

    private void bNMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bNMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[13]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[13]);
        }
    }//GEN-LAST:event_bNMousePressed

    private void bNMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bNMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bNMouseReleased

    private void bMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMMousePressed
        if (rdMin.isSelected()) {
            enviaDatos(CARDMIN[12]);
        }
        if (rdMay.isSelected()) {
            enviaDatos(CARDMAY[12]);
        }
    }//GEN-LAST:event_bMMousePressed

    private void bMMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMMouseReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_bMMouseReleased

    private void bAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bAKeyPressed
        
    }//GEN-LAST:event_bAKeyPressed

    private void bBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bBKeyPressed
        
    }//GEN-LAST:event_bBKeyPressed

    private void bCerrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bCerrarKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
            System.out.println("Presionando escape");
            this.dispose();
            closeConexion();
        }
    }//GEN-LAST:event_bCerrarKeyPressed

    private void toggTecladoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toggTecladoKeyPressed
        recibeTeclado(evt);
    }//GEN-LAST:event_toggTecladoKeyPressed

    private void toggTecladoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toggTecladoKeyReleased
        if (!ban == false && !b0.isEnabled()) {
            sendCharNum(0,'0');
            System.out.println("Enviando 0");
        }else{
            System.out.println("Boton 0 no bloqueado");
        }
    }//GEN-LAST:event_toggTecladoKeyReleased

    private void toggTecladoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_toggTecladoFocusLost
        
    }//GEN-LAST:event_toggTecladoFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Arduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Arduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Arduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Arduino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Arduino().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b0;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton bA;
    private javax.swing.JButton bB;
    private javax.swing.JButton bC;
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bConect;
    private javax.swing.JButton bD;
    private javax.swing.JButton bDesconect;
    private javax.swing.JButton bE;
    private javax.swing.JButton bF;
    private javax.swing.JButton bG;
    private javax.swing.JButton bH;
    private javax.swing.JButton bI;
    private javax.swing.JButton bJ;
    private javax.swing.JButton bK;
    private javax.swing.JButton bL;
    private javax.swing.JButton bM;
    private javax.swing.JButton bN;
    private javax.swing.JButton bO;
    private javax.swing.JButton bP;
    private javax.swing.JButton bQ;
    private javax.swing.JButton bR;
    private javax.swing.JButton bS;
    private javax.swing.JButton bSpace;
    private javax.swing.JButton bT;
    private javax.swing.JButton bU;
    private javax.swing.JButton bV;
    private javax.swing.JButton bW;
    private javax.swing.JButton bWebArduino;
    private javax.swing.JButton bX;
    private javax.swing.JButton bY;
    private javax.swing.JButton bZ;
    private javax.swing.ButtonGroup gLetras;
    private javax.swing.ButtonGroup gNumsChars;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton rChars;
    private javax.swing.JRadioButton rNums;
    private javax.swing.JRadioButton rdMay;
    private javax.swing.JRadioButton rdMin;
    private javax.swing.JToggleButton tBBlock0;
    private javax.swing.JCheckBox toggTeclado;
    private javax.swing.JTextField txtPort;
    // End of variables declaration//GEN-END:variables
}
