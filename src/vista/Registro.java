/*
 * Fecha de creacion: 29/03/2023 19:08:25
 * Version: v.0.1
 * Proyecto: Ventana login del sistema de venta
 */
package vista;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import modelos.login;
import modelos.loginDAO;


/**
 * @author Willy Stbn
 */
public class Registro extends javax.swing.JFrame {
    
    login lg = new login();
    loginDAO loginDAO = new loginDAO();

    public Registro() {
        initComponents();

        
        this.setLocationRelativeTo(null);
        this.setTitle("Registrar nuevo Usuario.");
        this.setResizable(false);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void validar(){
        String correo = txt_Usuario.getText().trim();
        String pass = txt_Password.getText().trim();
        String nom = txt_nombreUsuario.getText().trim();
        String rol = cmb_Rol_Usuario.getSelectedItem().toString();
        
        if(!"".equals(correo) && !"".equals(pass) && !nom.equals("")){
            
            lg.setNombre(nom);
            lg.setCorreo(correo);
            lg.setPass(pass);
            lg.setRol(rol);
            loginDAO.RegistrarUsuarios(lg);

//            Sistema sis = new Sistema(lg);
//            sis.setVisible(true);
            
            JOptionPane.showMessageDialog(null, "¡¡Registro exitoso");
            dispose();
         
        }  else {
            JOptionPane.showMessageDialog(null, "¡Rellene todos los campos!.");
        }   
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        label_IconoVentana = new javax.swing.JLabel();
        label_Usuario = new javax.swing.JLabel();
        txt_Usuario = new javax.swing.JTextField();
        label_Pass = new javax.swing.JLabel();
        txt_Password = new javax.swing.JPasswordField();
        btn_RegistrarUsuario = new javax.swing.JButton();
        label_nombre = new javax.swing.JLabel();
        txt_nombreUsuario = new javax.swing.JTextField();
        label_Rol = new javax.swing.JLabel();
        cmb_Rol_Usuario = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        label_Logo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        label_Pendiente = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        label_IconoVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/iniciar.png"))); // NOI18N

        label_Usuario.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        label_Usuario.setForeground(new java.awt.Color(0, 102, 255));
        label_Usuario.setText("Usuario:");

        label_Pass.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        label_Pass.setForeground(new java.awt.Color(0, 102, 255));
        label_Pass.setText("Password:");

        btn_RegistrarUsuario.setBackground(new java.awt.Color(102, 153, 255));
        btn_RegistrarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btn_RegistrarUsuario.setText("Registrar");
        btn_RegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarUsuarioActionPerformed(evt);
            }
        });

        label_nombre.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        label_nombre.setForeground(new java.awt.Color(0, 102, 255));
        label_nombre.setText("Nombre:");

        label_Rol.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        label_Rol.setForeground(new java.awt.Color(0, 102, 255));
        label_Rol.setText("Rol:");

        cmb_Rol_Usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asistente", "Administrador" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_RegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Rol, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_Rol_Usuario, 0, 210, Short.MAX_VALUE))
                .addGap(71, 71, 71))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(label_IconoVentana))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(txt_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(label_IconoVentana)
                .addGap(12, 12, 12)
                .addComponent(label_Usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_Pass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_Rol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_Rol_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btn_RegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 350, 430));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        label_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Matura MT Script Capitals", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informatica y programación");

        label_Pendiente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Pendiente.setText("----------------------------------------------------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(label_Logo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_Pendiente, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(label_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(label_Pendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 450));

        jLabel_Wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login.jpg"))); // NOI18N
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 260, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarUsuarioActionPerformed
        
        validar();
        
    }//GEN-LAST:event_btn_RegistrarUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Registro().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_RegistrarUsuario;
    private javax.swing.JComboBox<String> cmb_Rol_Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label_IconoVentana;
    private javax.swing.JLabel label_Logo;
    private javax.swing.JLabel label_Pass;
    private javax.swing.JLabel label_Pendiente;
    private javax.swing.JLabel label_Rol;
    private javax.swing.JLabel label_Usuario;
    private javax.swing.JLabel label_nombre;
    private javax.swing.JPasswordField txt_Password;
    private javax.swing.JTextField txt_Usuario;
    private javax.swing.JTextField txt_nombreUsuario;
    // End of variables declaration//GEN-END:variables
}
