
package nahum;

import database.SqliteBase;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author NahumFrog
 */
public class VentanaDB extends javax.swing.JFrame {

    /**
     * Creates new form VentanaDB
     */
    ////////////////////////////////////////////////////////////////////////////
    private String nombre,apellido1,apellido2,mail,fechaDB,materia,pass;
    private short dia,mes,anio,hora,minuto;
    private int codigo, nrc;
    private Calendar calendario = new GregorianCalendar();
    private SqliteBase sqliteBase;
    private boolean enableCampos = false;
    Ejecutar ejec = new Ejecutar();
    Pattern patron;
    Matcher mat;
    ////////////////////////////////////////////////////////////////////////////
    public VentanaDB() {
        initComponents();
        this.setLocationRelativeTo(null);
        editableDate();
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/emblem-linux.png")));
    }
    ////////////////////////////////////////////////////////////////////////////
    private void startCampos(){
        
    }
    ////////////////////////////////////////////////////////////////////////////
    private void llamarPass(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PassDB dialog = new PassDB(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////
    private void getDatosUser(){
        nombre = txtName.getText();
        apellido1 = txtApellido1.getText();
        apellido2 = txtApellido2.getText();
        mail = txtMail.getText();
        codigo = Integer.parseInt(txtCode.getText());
        if (checkAdminButton.isSelected()) {
            char pass [] = new char[txtGetNewPass.getPassword().length];
            pass = txtGetNewPass.getPassword();
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void getDatosMateria(){
        materia = txtClassName.getText();
        nrc = Integer.parseInt(txtNRC.getText());
    }
    ////////////////////////////////////////////////////////////////////////////
    private boolean checkCamposVaciosUser(){
        if ((txtName.getText().equals("")||txtApellido1.getText().equals("")||txtApellido2.getText().equals("")
          ||txtMail.getText().equals("")||txtCode.getText().equals(""))&&radAltaUser.isSelected()) {
            return true;//algun campo esta vacio
        }else{
            return false;//ningun campo está vacio
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private boolean checkCamposVaciosClass(){
        if ((txtNRC.getText().equals("")||txtClassName.getText().equals(""))&&radAltaClase.isSelected()) {
            return true;
        }else{
            return false;
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoName(){//revisar que los datos del campo nombre
        try{
            patron = Pattern.compile("(^[A-Z]{1,1})[a-z]+");//expresión para nombres propios
            mat = patron.matcher(nombre);
            if (mat.matches()) {
                //si coincide se guarda en la base de datos
            }else{
                System.out.println(nombre);
                System.out.println("No coincide");
                nombre = null;
            }
        }catch(Exception e){
            
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoApell1(){//metodo para revisar el campo de apellidos
        try{
            patron = Pattern.compile("(^[A-Z]{1,1})[a-z]+");//expresión para nombres propios
            mat = patron.matcher(apellido1);
            if (mat.matches()) {
                //si es correcto se guarda en la base de datos
            }else{
                System.out.println(apellido1);
                System.out.println("No coincide");
            }
        }catch(Exception e){
            
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoApell2(){
        try{
            patron = Pattern.compile("(^[A-Z]{1,1})[a-z]+");//expresión para nombres propios
            mat = patron.matcher(apellido2);
            if (mat.matches()) {
                //si es correcto se guarda en la base de datos
            }else{
                System.out.println(apellido2);
                System.out.println("No coincide");
            }
        }catch(Exception e){
            
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoMail(){//revisa campo de e-mail.
        try{
            patron = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");//para correo
            mat = patron.matcher(mail);
            if (mat.matches()) {
                System.out.println("Dato correcto, guardando en base de datos");
            }else{
                JOptionPane.showMessageDialog(null, mail+"\nes un correo incorrecto","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoCode(){
        try{
            patron = Pattern.compile("[0-9]*");
            mat = patron.matcher(mail);
            if (mat.matches()) {
            
            }else{
                JOptionPane.showMessageDialog(null, mail+"\nCódigo incorrecto","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoClase(){//luego lo reviso
        
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkCampoCC(){//
        
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkUser(){
        checkCampoName();
        checkCampoApell1();
        checkCampoApell2();
        checkCampoMail();
        checkCampoCode();
    }
    ////////////////////////////////////////////////////////////////////////////
    private void checkMateria(){
        checkCampoClase();
        checkCampoCC();
    }
    ////////////////////////////////////////////////////////////////////////////
    private void editableDate(){
        dia = (short)calendario.get(Calendar.DAY_OF_MONTH);
        mes = (short)calendario.get(Calendar.MONTH);
        anio = (short)calendario.get(Calendar.YEAR);
        hora = (short)calendario.get(Calendar.HOUR_OF_DAY);
        minuto = (short)calendario.get(Calendar.MINUTE);
        
        if (autoButton.isSelected()) {
            txtFecha.setEditable(false);
            
            if (hora<10) {
                txtFecha.setText(anio+"-"+(mes+1)+"-"+dia+" "+"0"+hora+":"+minuto);
            }
            if (minuto<10) {
                txtFecha.setText(anio+"-"+(mes+1)+"-"+dia+" "+hora+":"+"0"+minuto);
            }
            if (minuto>10&&hora>10) {
                txtFecha.setText(anio+"-"+(mes+1)+"-"+dia+" "+hora+":"+minuto);
            }
            
            fechaDB = ""+anio+"-"+(mes+1)+"-"+dia;
        }
        if (manualButton.isSelected()) {
            txtFecha.setEditable(true);
            txtFecha.setText(anio+"-"+(mes+1)+"-"+dia);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AutoManualGroup = new javax.swing.ButtonGroup();
        userOrClassGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelAltas = new javax.swing.JPanel();
        labelName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        labelApell1 = new javax.swing.JLabel();
        txtApellido1 = new javax.swing.JTextField();
        labelApell2 = new javax.swing.JLabel();
        txtApellido2 = new javax.swing.JTextField();
        labelCode = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        bGuardarAltas = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNRC = new javax.swing.JTextField();
        bNewUser = new javax.swing.JButton();
        checkAdminButton = new javax.swing.JCheckBox();
        txtGetNewPass = new javax.swing.JPasswordField();
        jLabel13 = new javax.swing.JLabel();
        radAltaUser = new javax.swing.JRadioButton();
        radAltaClase = new javax.swing.JRadioButton();
        panelControl = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        autoButton = new javax.swing.JRadioButton();
        manualButton = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTareasAsistencias = new javax.swing.JTable();
        bGuardar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtControlNRC = new javax.swing.JTextField();
        panelBajas = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtDelCode = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panelStatus = new javax.swing.JPanel();
        panelModificaciones = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(151, 240, 180));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        panelAltas.setBackground(new java.awt.Color(155, 189, 240));

        labelName.setText("Nombre(s)");

        txtName.setEditable(false);

        labelApell1.setText("Apellido paterno");

        txtApellido1.setEditable(false);

        labelApell2.setText("Apellido Materno");

        txtApellido2.setEditable(false);

        labelCode.setText("Código");

        txtCode.setEditable(false);

        jLabel5.setText("e-mail");

        txtMail.setEditable(false);

        bGuardarAltas.setText("Guardar");
        bGuardarAltas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarAltasActionPerformed(evt);
            }
        });

        jLabel10.setText("Materia");

        txtClassName.setEditable(false);

        jLabel11.setText("Código de Materia");

        txtNRC.setEditable(false);

        bNewUser.setText("Nuevo");
        bNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNewUserActionPerformed(evt);
            }
        });

        checkAdminButton.setText("Administrador");
        checkAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAdminButtonActionPerformed(evt);
            }
        });

        txtGetNewPass.setEditable(false);

        jLabel13.setText("Contraseña");

        userOrClassGroup.add(radAltaUser);
        radAltaUser.setSelected(true);
        radAltaUser.setText("Alta de Usuario");
        radAltaUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAltaUserActionPerformed(evt);
            }
        });

        userOrClassGroup.add(radAltaClase);
        radAltaClase.setText("Alta de Asignatura");
        radAltaClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radAltaClaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAltasLayout = new javax.swing.GroupLayout(panelAltas);
        panelAltas.setLayout(panelAltasLayout);
        panelAltasLayout.setHorizontalGroup(
            panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAltasLayout.createSequentialGroup()
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAltasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bNewUser)
                        .addGap(18, 18, 18)
                        .addComponent(bGuardarAltas))
                    .addGroup(panelAltasLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName)
                            .addComponent(labelCode)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNRC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGetNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelAltasLayout.createSequentialGroup()
                                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAltasLayout.createSequentialGroup()
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelApell1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radAltaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkAdminButton)
                                    .addGroup(panelAltasLayout.createSequentialGroup()
                                        .addComponent(labelApell2)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(radAltaClase))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelAltasLayout.setVerticalGroup(
            panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAltasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelApell1)
                    .addComponent(txtApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelApell2)
                    .addComponent(txtApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCode)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkAdminButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radAltaUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radAltaClase)
                .addGap(7, 7, 7)
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNRC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtGetNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(panelAltasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bGuardarAltas)
                    .addComponent(bNewUser))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Altas", panelAltas);

        panelControl.setBackground(new java.awt.Color(204, 255, 204));

        jLabel6.setText("Fecha");

        txtFecha.setEditable(false);

        AutoManualGroup.add(autoButton);
        autoButton.setSelected(true);
        autoButton.setText("Auto");
        autoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoButtonActionPerformed(evt);
            }
        });

        AutoManualGroup.add(manualButton);
        manualButton.setText("Manual");
        manualButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Formato: AAAA-MM-DD");

        tablaTareasAsistencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "NRC/Clase", "Asistencia", "Tarea"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTareasAsistencias);

        bGuardar.setText("Guardar");

        jLabel9.setText("Clase");

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelControlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bGuardar))
                    .addGroup(panelControlLayout.createSequentialGroup()
                        .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelControlLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(panelControlLayout.createSequentialGroup()
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(autoButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(manualButton))))
                            .addGroup(panelControlLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtControlNRC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autoButton)
                    .addComponent(manualButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(14, 14, 14)
                .addGroup(panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtControlNRC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(bGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Control", panelControl);

        panelBajas.setBackground(new java.awt.Color(255, 132, 114));

        jLabel8.setText("Código:");

        jLabel12.setText("NRC:");

        jButton1.setText("Eliminar Usuario");

        jButton3.setText("Eliminar Materia");

        javax.swing.GroupLayout panelBajasLayout = new javax.swing.GroupLayout(panelBajas);
        panelBajas.setLayout(panelBajasLayout);
        panelBajasLayout.setHorizontalGroup(
            panelBajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBajasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(txtDelCode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBajasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        panelBajasLayout.setVerticalGroup(
            panelBajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBajasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDelCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addGroup(panelBajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bajas", panelBajas);

        panelStatus.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout panelStatusLayout = new javax.swing.GroupLayout(panelStatus);
        panelStatus.setLayout(panelStatusLayout);
        panelStatusLayout.setHorizontalGroup(
            panelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelStatusLayout.setVerticalGroup(
            panelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Estadísticas y resultados", panelStatus);

        panelModificaciones.setBackground(new java.awt.Color(255, 255, 153));

        javax.swing.GroupLayout panelModificacionesLayout = new javax.swing.GroupLayout(panelModificaciones);
        panelModificaciones.setLayout(panelModificacionesLayout);
        panelModificacionesLayout.setHorizontalGroup(
            panelModificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelModificacionesLayout.setVerticalGroup(
            panelModificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Modificaciones", panelModificaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void autoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoButtonActionPerformed
        editableDate();
    }//GEN-LAST:event_autoButtonActionPerformed

    private void manualButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualButtonActionPerformed
        editableDate();
    }//GEN-LAST:event_manualButtonActionPerformed

    private void checkAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAdminButtonActionPerformed
        if (checkAdminButton.isSelected()) {
            txtGetNewPass.setEditable(true);
        }else{
            txtGetNewPass.setEditable(false);
        }
        
    }//GEN-LAST:event_checkAdminButtonActionPerformed

    private void bGuardarAltasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarAltasActionPerformed
        enableCampos = false;
        if (checkCamposVaciosUser()) {
            JOptionPane.showMessageDialog(null, "Los campos no deben estar vacíos","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            getDatosUser();
            JOptionPane.showMessageDialog(null, "Los datos se han recibido correctamente","Datos Guardados",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bGuardarAltasActionPerformed

    private void radAltaClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAltaClaseActionPerformed
        txtName.setEditable(false);
        txtApellido1.setEditable(false);
        txtApellido2.setEditable(false);
        txtCode.setEditable(false);
        txtMail.setEditable(false);
        txtClassName.setEditable(true);
        txtNRC.setEditable(true);
    }//GEN-LAST:event_radAltaClaseActionPerformed

    private void radAltaUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radAltaUserActionPerformed
        txtName.setEditable(true);
        txtApellido1.setEditable(true);
        txtApellido2.setEditable(true);
        txtCode.setEditable(true);
        txtMail.setEditable(true);
        txtClassName.setEditable(false);
        txtNRC.setEditable(false);
    }//GEN-LAST:event_radAltaUserActionPerformed

    private void bNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNewUserActionPerformed
        enableCampos = true;
        txtName.setEditable(true);
        txtApellido1.setEditable(true);
        txtApellido2.setEditable(true);
        txtCode.setEditable(true);
        txtMail.setEditable(true);
        txtClassName.setEditable(true);
        txtNRC.setEditable(true);
    }//GEN-LAST:event_bNewUserActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AutoManualGroup;
    private javax.swing.JRadioButton autoButton;
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton bGuardarAltas;
    private javax.swing.JButton bNewUser;
    private javax.swing.JCheckBox checkAdminButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelApell1;
    private javax.swing.JLabel labelApell2;
    private javax.swing.JLabel labelCode;
    private javax.swing.JLabel labelName;
    private javax.swing.JRadioButton manualButton;
    private javax.swing.JPanel panelAltas;
    private javax.swing.JPanel panelBajas;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelModificaciones;
    private javax.swing.JPanel panelStatus;
    private javax.swing.JRadioButton radAltaClase;
    private javax.swing.JRadioButton radAltaUser;
    private javax.swing.JTable tablaTareasAsistencias;
    private javax.swing.JTextField txtApellido1;
    private javax.swing.JTextField txtApellido2;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtControlNRC;
    private javax.swing.JTextField txtDelCode;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JPasswordField txtGetNewPass;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNRC;
    private javax.swing.JTextField txtName;
    private javax.swing.ButtonGroup userOrClassGroup;
    // End of variables declaration//GEN-END:variables
}
