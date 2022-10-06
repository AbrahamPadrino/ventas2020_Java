/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system2020;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static system2020.mozo.capturafecha; //importa el metodo desde otra clase

/**
 *
 * @author LENOVO
 */
public class pagar_servicio extends javax.swing.JFrame {

    DefaultTableModel model2, model3;
    Statement sent;

    public pagar_servicio() {
        initComponents();
        this.setResizable(false); // desactivar maximizar ventana
        setLocationRelativeTo(null); // centrar la ventana
        
        CargarProveedores("");

        txt_fecha.setText(capturafecha()); // muestra la fecha capturada

        jP_info.setVisible(false);
        filas.setVisible(false);

        model2 = new DefaultTableModel();// Crea columnas para tabla del consumo de la mesa
        model2.addColumn("Nombre Proveedor");
        model2.addColumn("Factura Nº");
        model2.addColumn("Monto");
        model2.addColumn("Fecha");
        model2.addColumn("Usuario");
        this.table_pagos.setModel(model2);
    }
    
    void CargarProveedores(String valor) {
        try {
            String[] titulos = {"Id","Nombre", "Documento", "Contacto"};
            String[] registros = new String[4];
            String sql = "SELECT * FROM proveedores where nombre LIKE '%" + valor + "%' ";
            
            model3 = new DefaultTableModel(null, titulos);
            sent = cn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");
                registros[3] = rs.getString("contacto");
                
                model3.addRow(registros);
            }
            jTable1.setModel(model3);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            
               
    }
    public void limpiar_datos() {
        txt_proveedor.setText("");
        txt_factura.setText("");
        txt_monto.setText("");

    }

    public void guardar_pago() {
        try {
            for (int i = 0; i < table_pagos.getRowCount(); i++) {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO pagoservicios(proveedor, factura, monto, fecha, usuario)VALUES(?,?,?,?,?)");
                pst.setString(1, table_pagos.getValueAt(i, 0).toString());
                pst.setString(2, table_pagos.getValueAt(i, 1).toString());
                pst.setString(3, table_pagos.getValueAt(i, 2).toString());
                pst.setString(4, table_pagos.getValueAt(i, 3).toString());
                pst.setString(5, table_pagos.getValueAt(i, 4).toString());

                pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "PAGO REGISTRAD0... ", "Finalizado", 1);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    
    public void registrar_proveedor() {
        if (txt_name.getText().equals("") || txt_documento.getText().equals("") || txt_contacto.getText().equals("") || txt_descripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Algún Campo Está Vacío","Datos Incompletos",1);
        } else {
            String nam, doc, cont, desc, use;
            String sql = "";
            nam = txt_name.getText();
            doc = txt_documento.getText();
            cont = txt_contacto.getText();
            desc = txt_descripcion.getText();
            use = txt_usuario.getText();
            
            sql = "INSERT INTO proveedores(nombre, documento, contacto, descripcion, usuario) VALUES(?,?,?,?,?)";
            try {
                PreparedStatement pst = cn.prepareCall(sql);
                
                pst.setString(1, nam);
                pst.setString(2, doc);
                pst.setString(3, cont);
                pst.setString(4, desc);
                pst.setString(5, use);
                int n = pst.executeUpdate();
                    if (n > 0) {
                        JOptionPane.showMessageDialog(null, "Proveedor Registrado Exitosamente");
                    }
            } catch (SQLException ex) {
                 Logger.getLogger(pagar_servicio.class.getName()).log(Level.SEVERE, null, ex);
            }
         CargarProveedores("");
         limpiar_registro();
         jP_lista.setVisible(true);
         jP_info.setVisible(false);
         txt_buscar.grabFocus();
        }
    }
    
    public void contar_filas() {
        for (int i = 0; i <= table_pagos.getRowCount(); i++) {
            filas.setText("" + i);
        }
    }

    void limpiar_tabla() {
        int borrato = table_pagos.getRowCount();
        for (int i = borrato - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
    }
    
    void limpiar_registro() {
        txt_name.setText("");
        txt_documento.setText("");
        txt_contacto.setText("");
        txt_descripcion.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        txt_factura = new javax.swing.JTextField();
        txt_monto = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        inserta = new javax.swing.JButton();
        jP_info = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_documento = new javax.swing.JTextField();
        txt_contacto = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        guardar_proveedor = new javax.swing.JButton();
        atras_proveedor = new javax.swing.JButton();
        jP_lista = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        registrar_proveedor = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_pagos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_atras = new javax.swing.JButton();
        filas = new javax.swing.JTextField();
        txt_usuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(683, 417));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(683, 417));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos de Pago:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel1.setText("Proveedor:");

        jLabel2.setText("Factura Nº :");

        jLabel3.setText("Importe:");

        txt_proveedor.setFocusable(false);

        txt_fecha.setFocusable(false);

        jLabel5.setText("Fecha:");

        inserta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        inserta.setText("Insertar.");
        inserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(inserta)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5)
                                .addComponent(txt_factura, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(txt_fecha)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(inserta)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 310, 180));

        jP_info.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Info. Proveedores:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel4.setText("Proveedor:");

        jLabel6.setText("Documento Nº :");

        jLabel7.setText("Contactos:");

        jLabel8.setText("Descripción:");

        guardar_proveedor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        guardar_proveedor.setText("Guardar.");
        guardar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_proveedorActionPerformed(evt);
            }
        });

        atras_proveedor.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        atras_proveedor.setText("< Atrás");
        atras_proveedor.setBorder(null);
        atras_proveedor.setContentAreaFilled(false);
        atras_proveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        atras_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atras_proveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_infoLayout = new javax.swing.GroupLayout(jP_info);
        jP_info.setLayout(jP_infoLayout);
        jP_infoLayout.setHorizontalGroup(
            jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jP_infoLayout.createSequentialGroup()
                            .addComponent(atras_proveedor)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                            .addComponent(guardar_proveedor))
                        .addGroup(jP_infoLayout.createSequentialGroup()
                            .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(txt_contacto))
                            .addGap(18, 18, 18)
                            .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(jLabel6)
                                .addComponent(txt_documento, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addComponent(txt_descripcion)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jP_infoLayout.setVerticalGroup(
            jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_infoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_contacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jP_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar_proveedor)
                    .addComponent(atras_proveedor))
                .addContainerGap())
        );

        getContentPane().add(jP_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 12, 320, 180));

        jP_lista.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lista. Proveedores:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel9.setText("Buscar:");

        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        registrar_proveedor.setText("Registrar.");
        registrar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar_proveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jP_listaLayout = new javax.swing.GroupLayout(jP_lista);
        jP_lista.setLayout(jP_listaLayout);
        jP_listaLayout.setHorizontalGroup(
            jP_listaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_listaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_listaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_listaLayout.createSequentialGroup()
                        .addComponent(registrar_proveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jP_listaLayout.setVerticalGroup(
            jP_listaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_listaLayout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(jP_listaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(registrar_proveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jP_lista, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 12, 320, 180));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lista de Pagos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        table_pagos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        table_pagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_pagos.setFocusable(false);
        table_pagos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_pagos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 510, 170));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Opciones:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_guardar.setText("GUARDAR");
        btn_guardar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_atras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_atras.setText("ATRÁS");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_guardar)
                    .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_guardar)
                .addGap(18, 18, 18)
                .addComponent(btn_eliminar)
                .addGap(18, 18, 18)
                .addComponent(btn_atras)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, -1, 170));

        filas.setEnabled(false);
        getContentPane().add(filas, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 40, 20));

        txt_usuario.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        txt_usuario.setText("usuario");
        getContentPane().add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static String capturafecha() { //metodo para capturar la fecha del sistema operativo
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YY");
        return formatoFecha.format(fecha);
    }

    private void registrar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar_proveedorActionPerformed
        jP_lista.setVisible(false);
        jP_info.setVisible(true);
        txt_name.grabFocus();
    }//GEN-LAST:event_registrar_proveedorActionPerformed

    private void atras_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atras_proveedorActionPerformed
       int sal = JOptionPane.showConfirmDialog(null, "Desea Cancelar La Operación de Registro?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (sal == 0) {
            limpiar_registro();
            jP_lista.setVisible(true);
            jP_info.setVisible(false);
            txt_buscar.grabFocus();
        }    
     
    }//GEN-LAST:event_atras_proveedorActionPerformed

    private void guardar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_proveedorActionPerformed
        registrar_proveedor();    
    }//GEN-LAST:event_guardar_proveedorActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        if (filas.getText().equals("") || filas.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "La Tabla Está Vacía", "No Hay Registros", 1);

        } else {

            int vac = JOptionPane.showConfirmDialog(null, "Desea Guardar los Registros?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (vac == 0) {
                guardar_pago();
                limpiar_tabla();
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int borra = table_pagos.getSelectedRow();
        if (borra >= 0) {
            int eli = JOptionPane.showConfirmDialog(null, "Desea Borrar el Registro Seleccionado?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (eli == 0) {
                model2.removeRow(borra);
                contar_filas();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione Algún Registro", "Debe Especificar", 1);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        int sal = JOptionPane.showConfirmDialog(null, "Desea Cancelar La Operación de Pago?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (sal == 0) {
            
            this.dispose();
    }//GEN-LAST:event_btn_atrasActionPerformed
    }
    private void insertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertaActionPerformed
        if (txt_proveedor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Indique El Proveedor", "aviso", 1);
        } else if (txt_factura.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Indique El Nº de Factura", "aviso", 1);
            txt_factura.grabFocus();
        } else if (txt_monto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Indique El Monto a Pagar", "aviso", 1);
            txt_monto.grabFocus();
        } else if (txt_fecha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Error en la Fecha", "aviso", 1);

        } else {
            String[] info = new String[5];
            info[0] = txt_proveedor.getText();
            info[1] = txt_factura.getText();
            info[2] = txt_monto.getText();
            info[3] = txt_fecha.getText();
            info[4] = txt_usuario.getText();
            model2.addRow(info);

            limpiar_datos();
            contar_filas();
        }
    }//GEN-LAST:event_insertaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    if (evt.getButton() == 1) {
            int pst = jTable1.getSelectedRow();
            try {
                String sql = "SELECT * FROM proveedores where id= " + jTable1.getValueAt(pst, 0).toString();
                sent = cn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next();
                txt_proveedor.setText(rs.getString("nombre"));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        String[] titulos = {"Id","Nombre", "Documento", "Contacto"};
        String[] registros = new String[40];
        String sql = "SELECT *FROM proveedores WHERE nombre LIKE '%" + txt_buscar.getText() + "%' "
                + "OR documento LIKE '%" + txt_buscar.getText() + "%'";


        model3 = new DefaultTableModel(null, titulos);
        conectarBD cc = new conectarBD();
        Connection conect = cc.conexion();
        try {
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");
                registros[3] = rs.getString("contacto");
                model3.addRow(registros);
            }
            jTable1.setModel(model3);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

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
            java.util.logging.Logger.getLogger(pagar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pagar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pagar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pagar_servicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pagar_servicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atras_proveedor;
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JTextField filas;
    private javax.swing.JButton guardar_proveedor;
    private javax.swing.JButton inserta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP_info;
    private javax.swing.JPanel jP_lista;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton registrar_proveedor;
    private javax.swing.JTable table_pagos;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_contacto;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_documento;
    private javax.swing.JTextField txt_factura;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_proveedor;
    public static javax.swing.JLabel txt_usuario;
    // End of variables declaration//GEN-END:variables
    conectarBD cc = new conectarBD();
    Connection cn = cc.conexion();
}
