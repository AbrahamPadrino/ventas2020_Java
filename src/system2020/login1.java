/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system2020;


import Animacion.Fade;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Equipo
 */
public class login1 extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public login1() {
        initComponents();
        setLocationRelativeTo(null);
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/home.png"));
        this.setIconImage(ico); // para asignar icono a la ventana
        
        //txtuser.setBackground(new Color(0, 0, 0, 90));
        //txtpass.setBackground(new Color(0, 0, 0, 90));
    }
    
void acceder(String user, String pass){
    String condicion="";
    String sql="SELECT * FROM usuarios WHERE nombre_usuario='"+user+"' && clave_usuario='"+pass+"'"; //consulta sql para comprobar datos de usuario
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
            {
                condicion=rs.getString("tipo_usuario");
            }
            if(condicion.equals("Administrador"))
            {
            //ocultarbotones();
                this.setVisible(false);
                mozo ingreso = new mozo();
                ingreso.setVisible(true);
                ingreso.pack();
                mozo.usuario.setText(user);
                mozo.usuario_tipo.setText("Administrador");
            }
            if(condicion.equals("Invitado"))
            {
            //ocultarbotones();
                this.setVisible(false);
                mozo ingreso = new mozo();
                ingreso.setVisible(true);
                ingreso.pack();
                mozo.usuario.setText(user);
                mozo.usuario_tipo.setText("Invitado");
            }
            if((!condicion.equals("Administrador")) && (!condicion.equals("Invitado")))
            {
                JOptionPane.showMessageDialog(this, "Usuario NO Registrado!");
            }    
        } catch (SQLException ex) {
            Logger.getLogger(login1.class.getName()).log(Level.SEVERE, null, ex);
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

        BioPol2 = new javax.swing.JLabel();
        BioPol = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        txtpass = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BioPol2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BioPol2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BioPol2.setText("EnCaja 1NO");
        getContentPane().add(BioPol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 250, 40));

        BioPol.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        BioPol.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(BioPol, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 100, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("Sistema Automatizado de Registro y Control de Ventas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, 10));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BOTONES/USUARIO.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 20, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BOTONES/CANDADO.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 20, 20));

        txtuser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 140, -1));

        txtpass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 140, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BOTONES/SALIR.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BOTONES/SALIR2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 70, 20));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BOTONES/ENTRAR.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BOTONES/ENTRAR2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 80, 20));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Login", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 260, 110));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo-login.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 240));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 240));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String user= txtuser.getText();
String pass =new String(txtpass.getPassword());
acceder(user, pass);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
Fade.JFrameFadeOut(1f, 0f, 0.1f, 50, this, Fade.EXIT);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BioPol;
    private javax.swing.JLabel BioPol2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
conectarBD cc = new conectarBD();
Connection cn = cc.conexion();
}
