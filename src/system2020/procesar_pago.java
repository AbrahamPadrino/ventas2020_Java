package system2020;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LENOVO
 */
public class procesar_pago extends javax.swing.JFrame {

    public static DefaultTableModel modelo2;
    DefaultTableModel modelClientes;
    Statement sent;

    public procesar_pago() {
        initComponents();
        this.setTitle("System2020 - Procesar Pago");
        this.setResizable(false);
        setLocationRelativeTo(null);
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/pago.png"));

        modelo2 = new DefaultTableModel();
        modelo2.addColumn("Cantidad");
        modelo2.addColumn("Nombre");
        modelo2.addColumn("Monto");
        modelo2.addColumn("Codigo");
        modelo2.addColumn("Hora");
        modelo2.addColumn("Fecha");
        modelo2.addColumn("Vendedor");
        tabla_pago.setModel(modelo2);

        tipo_pago.setVisible(false);
        detalle.setVisible(false);

        btn_boleta.setEnabled(false);
        btn_factura.setEnabled(false);

        nombre_cliente.grabFocus();

        lbl_descuento.setVisible(false);
        txt_total.setVisible(false);

        txt_descuento.setText("0");
        txt_total.setText(txt_subtotal.getText());

        CargarClientes("");
        generarSerie();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formasDePago = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pago = new javax.swing.JTable();
        txt_subtotal = new javax.swing.JTextField();
        n_filas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        hora_pago = new javax.swing.JTextField();
        fecha_pago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_descuento = new javax.swing.JLabel();
        txt_descuento = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        btn_descuento = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        regresar = new javax.swing.JButton();
        btn_boleta = new javax.swing.JButton();
        btn_factura = new javax.swing.JButton();
        btn_cobrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        nombre_cliente = new javax.swing.JTextField();
        documento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcheckRegistrar = new javax.swing.JCheckBox();
        jcheckdesconocido = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        tabla = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        txtEfectivo = new javax.swing.JTextField();
        txtTarjeta = new javax.swing.JTextField();
        txtVale = new javax.swing.JTextField();
        CheckEfectivo = new javax.swing.JRadioButton();
        CheckTarjeta = new javax.swing.JRadioButton();
        CheckVale = new javax.swing.JRadioButton();
        tipo_pago = new javax.swing.JTextField();
        detalle = new javax.swing.JTextField();
        id_usuario = new javax.swing.JLabel();
        txt_serie = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Detalles del Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_pago = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tabla_pago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_pago.setFocusable(false);
        tabla_pago.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla_pago);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 800, 170));

        txt_subtotal.setEditable(false);
        txt_subtotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_subtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_subtotal.setFocusable(false);
        jPanel1.add(txt_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 235, 100, 30));

        n_filas.setEditable(false);
        n_filas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        n_filas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        n_filas.setFocusable(false);
        jPanel1.add(n_filas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 235, 100, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nº");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 215, -1, -1));

        hora_pago.setEditable(false);
        hora_pago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hora_pago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hora_pago.setFocusable(false);
        jPanel1.add(hora_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 235, 100, 30));

        fecha_pago.setEditable(false);
        fecha_pago.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fecha_pago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fecha_pago.setFocusable(false);
        jPanel1.add(fecha_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 235, 100, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Fecha:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 215, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Hora:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 215, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("SubTotal:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 215, -1, -1));

        lbl_descuento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_descuento.setText("Total:");
        jPanel1.add(lbl_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 215, -1, -1));

        txt_descuento.setEditable(false);
        txt_descuento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_descuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_descuento.setFocusable(false);
        jPanel1.add(txt_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 235, 100, 30));

        txt_total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 235, 90, 30));

        btn_descuento.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_descuento.setText("Descontar");
        btn_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_descuentoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 235, 90, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Descuento:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 215, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 860, 280));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        regresar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        regresar.setText("ATRAS");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        jPanel5.add(regresar);

        btn_boleta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_boleta.setText("BOLETA");
        btn_boleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_boletaActionPerformed(evt);
            }
        });
        jPanel5.add(btn_boleta);

        btn_factura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_factura.setText("FACTURA");
        btn_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturaActionPerformed(evt);
            }
        });
        jPanel5.add(btn_factura);

        btn_cobrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cobrar.setText("COBRAR");
        btn_cobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cobrarActionPerformed(evt);
            }
        });
        jPanel5.add(btn_cobrar);

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 550, 60));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nombre_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombre_clienteMouseClicked(evt);
            }
        });
        nombre_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombre_clienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombre_clienteKeyTyped(evt);
            }
        });
        jPanel2.add(nombre_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 50, 180, -1));

        documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                documentoKeyPressed(evt);
            }
        });
        jPanel2.add(documento, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 110, 180, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText(" Documento:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 80, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Cliente:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 50, -1));

        jcheckRegistrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jcheckRegistrar.setText("Registrar.");
        jcheckRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcheckRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(jcheckRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        jcheckdesconocido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jcheckdesconocido.setText("No Definido.");
        jPanel2.add(jcheckdesconocido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 330, 180));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes Registrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_clientes.setFocusable(false);
        tabla_clientes.getTableHeader().setReorderingAllowed(false);
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        tabla.setViewportView(tabla_clientes);

        jPanel3.add(tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 300, 130));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 340, 180));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Método de Pago", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 45, 100, -1));
        jPanel4.add(txtTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 95, 100, -1));
        jPanel4.add(txtVale, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 145, 100, -1));

        formasDePago.add(CheckEfectivo);
        CheckEfectivo.setText("Efectivo");
        CheckEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckEfectivoActionPerformed(evt);
            }
        });
        jPanel4.add(CheckEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        formasDePago.add(CheckTarjeta);
        CheckTarjeta.setText("Tarjeta");
        CheckTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckTarjetaActionPerformed(evt);
            }
        });
        jPanel4.add(CheckTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        formasDePago.add(CheckVale);
        CheckVale.setText("Cortesia");
        CheckVale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckValeActionPerformed(evt);
            }
        });
        jPanel4.add(CheckVale, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 160, 180));

        tipo_pago.setEditable(false);
        getContentPane().add(tipo_pago, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 200, 70, -1));

        detalle.setEditable(false);
        getContentPane().add(detalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 200, 70, -1));

        id_usuario.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        id_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        id_usuario.setText("usuario");
        id_usuario.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        id_usuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        id_usuario.setOpaque(true);
        getContentPane().add(id_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, 70, -1));
        getContentPane().add(txt_serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 510, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String NroSerieVenta() {
        String serie = "";
        String sql = "select max (id_pago) from venta";
        try {
            sent = cn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            while (rs.next()) {
                serie = rs.getString(9);

            }

        } catch (Exception e) {
        }
        return serie;
    }

    void generarSerie() {
        String serie1 = NroSerieVenta();
        if (serie1 == null) {
            txt_serie.setText("0000001");
        }
    }

    void CargarClientes(String valor) {
        try {
            String[] titulos = {"Id", "Nombre", "Documento"};
            String[] registros = new String[3];
            String sql = "SELECT * FROM clientes where documento LIKE '%" + valor + "%' ";

            modelClientes = new DefaultTableModel(null, titulos);
            sent = cn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");
                modelClientes.addRow(registros);
            }
            tabla_clientes.setModel(modelClientes);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void limpia_cliente() {
        nombre_cliente.setText("");
        documento.setText("");
        jcheckRegistrar.setSelected(false);
    }

    void limpiar_check() {
        txtEfectivo.setText("");
        txtTarjeta.setText("");
        txtVale.setText("");
    }

    public void registra_cliente() {
        String nom, doc;
        String regist;
        String sql = ("");
        String exp = documento.getText();

        boolean existe = false; //variable bandera para comprobar si NO existe el expediente en la BD

        try {
            sql = "SELECT * FROM clientes WHERE documento = '" + exp + "'";

            ResultSet rs = sent.executeQuery(sql);

            while (rs.next()) {
                existe = true;
                if (existe == true) {
                    regist = rs.getString("documento");
                    if (exp.equals(regist)) {
                        JOptionPane.showMessageDialog(null, "El Titular de: " + regist + " Está Registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        documento.setText("");
                        documento.grabFocus();
                    }
                }
            }//fin while res.next

            if (existe == false) {
                if (nombre_cliente.getText().equals("") || documento.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Hay Algún Campo de Registro de Cliente Vacío");
                } else {
                    nom = nombre_cliente.getText();
                    doc = documento.getText();
                    sql = "INSERT INTO clientes(nombre, documento) VALUES(?,?)";
                    try {
                        PreparedStatement pst = cn.prepareCall(sql);

                        pst.setString(1, nom);
                        pst.setString(2, doc);
                        int n = pst.executeUpdate();
                        if (n > 0) {
                            JOptionPane.showMessageDialog(null, "Cliente Registrado Exitosamente");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(mozo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    CargarClientes("");
                    jcheckRegistrar.setSelected(false);
                }
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void guardar_venta() {
        try {
            for (int i = 0; i < tabla_pago.getRowCount(); i++) {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO venta(cantidad, producto, monto, codigo, hora, fecha, vendedor)VALUES(?,?,?,?,?,?,?)");
                pst.setString(1, tabla_pago.getValueAt(i, 0).toString());
                pst.setString(2, tabla_pago.getValueAt(i, 1).toString());
                pst.setString(3, tabla_pago.getValueAt(i, 2).toString());
                pst.setString(4, tabla_pago.getValueAt(i, 3).toString());
                pst.setString(5, tabla_pago.getValueAt(i, 4).toString());
                pst.setString(6, tabla_pago.getValueAt(i, 5).toString());
                pst.setString(7, tabla_pago.getValueAt(i, 6).toString());

                pst.executeUpdate();
            }
            //JOptionPane.showMessageDialog(null, "VENTA REGISTRADA... ", "Finalizado", 1);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void guardar_pago() {
        if (nombre_cliente.getText().equals("") || documento.getText().equals("") || tipo_pago.getText().equals("") || detalle.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hay Algún Campo de PAGO Vacío", "aviso", 1);
        } else {
            String nomb, docu, canti, subtotal, descto, total, modo, detal, fech, hor, use;
            String sql = "";
            nomb = nombre_cliente.getText();
            docu = documento.getText();
            canti = n_filas.getText();
            subtotal = txt_subtotal.getText();
            descto = txt_descuento.getText();
            total = txt_total.getText();
            modo = tipo_pago.getText();
            detal = detalle.getText();
            fech = fecha_pago.getText();
            hor = hora_pago.getText();
            use = id_usuario.getText();

            sql = "INSERT INTO pagos(nombre, documento, productos, monto, descuento, total, modalidad, detalles, fecha, hora, usuario) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement pst = cn.prepareCall(sql);

                pst.setString(1, nomb);
                pst.setString(2, docu);
                pst.setString(3, canti);
                pst.setString(4, subtotal);
                pst.setString(5, descto);
                pst.setString(6, total);
                pst.setString(7, modo);
                pst.setString(8, detal);
                pst.setString(9, fech);
                pst.setString(10, hor);
                pst.setString(11, use);
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "* PAGO REGISTRADO\n* VENTA REGISTRADA", "Operación Exitosa", 1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(procesar_pago.class.getName()).log(Level.SEVERE, null, ex);
            }
            guardar_venta();
            btn_boleta.setEnabled(true);
            btn_factura.setEnabled(true);
        }
    }

    public void pago_efectivo() {
        if (txtEfectivo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el Monto en Efectivo", "AVISO IMPORTANTE", 1);
        } else {

            double num1, num2, num3;

            String num1X, num2X;
            String resultadoX;
            num1X = txtEfectivo.getText();
            num2X = txt_total.getText();

            detalle.setText(num1X);

            num1 = Double.parseDouble(num1X);
            num2 = Double.parseDouble(num2X);
            num3 = (num1 - num2);

            resultadoX = String.valueOf(num3);

            JOptionPane.showMessageDialog(null, " El Vuelto es: " + resultadoX);

            guardar_pago();
        }
        txtEfectivo.grabFocus();
    }

    public void pago_tarjeta() {
        if (txtTarjeta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese el Numero de Operación", "AVISO IMPORTANTE", 1);
        } else {
            String ref = txtTarjeta.getText();
            detalle.setText(ref);

            guardar_pago();
        }
        txtTarjeta.grabFocus();
    }

    public void pago_vale() {
        if (txtVale.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la Descripción del Vale", "AVISO IMPORTANTE", 1);
        } else {
            String refe = txtVale.getText();
            detalle.setText(refe);

            guardar_pago();
        }
    }

    public void limpiar_pago() {
        // limpia la tabla de los pedidos
        int borrato = tabla_pago.getRowCount();
        for (int i = borrato - 1; i >= 0; i--) {
            modelo2.removeRow(i);
        }
        // limpia los campos de pago
        nombre_cliente.setText("");
        documento.setText("");
        n_filas.setText("");
        fecha_pago.setText("");
        hora_pago.setText("");
        txt_subtotal.setText("");
        txt_descuento.setText("");
        txt_total.setText("");
        txtEfectivo.setText("");
        txtTarjeta.setText("");
        txtVale.setText("");
        CheckEfectivo.setSelected(false);
        CheckTarjeta.setSelected(false);
        CheckVale.setSelected(false);

    }

    public void metodo_pago() {
        if (CheckEfectivo.isSelected()) {
            tipo_pago.setText("Efectivo");

        } else if (CheckTarjeta.isSelected()) {
            tipo_pago.setText("Tarjeta");

        } else if (CheckVale.isSelected()) {
            tipo_pago.setText("Vale");

        }
    }
    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed

        this.setVisible(false);// TODO add your handling code here:

    }//GEN-LAST:event_regresarActionPerformed

    private void nombre_clienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_clienteKeyPressed
        //Metodo keyPressed con codigo para mover de un campo a otro al presionar Enter
        if (evt.getKeyCode() == evt.VK_ENTER) {
            documento.requestFocus();
        }
        String[] titulos = {"Id", "Nombre", "Documento"};
        String[] registros = new String[40];
        String sql = "SELECT *FROM clientes WHERE nombre LIKE '%" + nombre_cliente.getText() + "%' "
                + "OR documento LIKE '%" + nombre_cliente.getText() + "%' ";

        modelClientes = new DefaultTableModel(null, titulos);
        conectarBD cc = new conectarBD();
        Connection conect = cc.conexion();
        try {
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("documento");

                modelClientes.addRow(registros);
            }
            tabla_clientes.setModel(modelClientes);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_nombre_clienteKeyPressed

    private void documentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_documentoKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            jcheckRegistrar.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_documentoKeyPressed

    private void jcheckRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcheckRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcheckRegistrarActionPerformed

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        if (evt.getButton() == 1) {
            int pst = tabla_clientes.getSelectedRow();
            try {
                String sql = "SELECT * FROM clientes where id= " + tabla_clientes.getValueAt(pst, 0).toString();
                sent = cn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next();
                nombre_cliente.setText("");
                nombre_cliente.setText(rs.getString("nombre"));
                documento.setText(rs.getString("documento"));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_clientesMouseClicked

    private void nombre_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombre_clienteMouseClicked
        limpia_cliente();
        CargarClientes("");// TODO add your handling code here:
    }//GEN-LAST:event_nombre_clienteMouseClicked

    private void btn_boletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_boletaActionPerformed
        try {
            ArrayList<String> listado = new ArrayList<String>();
            String master = System.getProperty("user.dir") + "/pdf/report.jasper";
            HashMap parametros = new HashMap();
            String v = "";
            for (int i = 0; i < tabla_pago.getRowCount(); i++) {
                listado.add(i,
                        tabla_pago.getValueAt(i, 0)
                        + "  " + tabla_pago.getValueAt(i, 1)
                        + "    " + tabla_pago.getValueAt(i, 2));
            }

            for (int i = 0; i < listado.size(); i++) {
                v = v + "\n" + listado.get(i);
            }
            JOptionPane.showMessageDialog(null, v);
            parametros.put("tablareport", v);
            parametros.put("cliente", nombre_cliente.getText());
            parametros.put("documento", documento.getText());
            parametros.put("importe", txt_subtotal.getText());
            parametros.put("descuento", txt_descuento.getText());
            parametros.put("importetotal", txt_total.getText());
            parametros.put("fecha", fecha_pago.getText());
            JasperPrint jp = JasperFillManager.fillReport(master, parametros, new JREmptyDataSource());
            JasperViewer.viewReport(jp, false);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        btn_boleta.setEnabled(false);
        limpiar_pago();
    }//GEN-LAST:event_btn_boletaActionPerformed

    private void btn_cobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cobrarActionPerformed
        if (jcheckRegistrar.isSelected()) {
            registra_cliente();
        }

        if (jcheckdesconocido.isSelected()) {
            nombre_cliente.setText("NoDefinido");
            documento.setText("NoDefinido");
        }

        if (CheckEfectivo.isSelected()) {
            tipo_pago.setText("Efectivo");
            pago_efectivo();

        } else if (CheckTarjeta.isSelected()) {
            tipo_pago.setText("Tarjeta");
            pago_tarjeta();

        } else if (CheckVale.isSelected()) {
            tipo_pago.setText("Vale");
            pago_vale();

        } else {
            JOptionPane.showMessageDialog(null, "Elija Un Metodo de Pago", "Complete la Información", 1);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cobrarActionPerformed

    private void btn_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_facturaActionPerformed

    private void CheckEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckEfectivoActionPerformed
        txtTarjeta.setText("");
        txtVale.setText("");
        txtEfectivo.grabFocus();
    }//GEN-LAST:event_CheckEfectivoActionPerformed

    private void CheckTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckTarjetaActionPerformed
        txtEfectivo.setText("");
        txtVale.setText("");
        txtTarjeta.grabFocus();
    }//GEN-LAST:event_CheckTarjetaActionPerformed

    private void CheckValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckValeActionPerformed
        txtEfectivo.setText("");
        txtTarjeta.setText("");
        txtVale.grabFocus();
    }//GEN-LAST:event_CheckValeActionPerformed

    private void btn_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_descuentoActionPerformed
        metodo_pago();
        if (nombre_cliente.getText().equals("") || documento.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Faltan Datos del Comprador", "Completar Información", 1);
            nombre_cliente.grabFocus();

        } else if (tipo_pago.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Seleccione la Forma de Pago", "Completar Información", 1);
            CheckEfectivo.grabFocus();
        } else {

            lbl_descuento.setVisible(true);
            txt_total.setVisible(true);
            btn_descuento.setVisible(false);
            //Abrir el jDialog Form
            FormDescuento n = new FormDescuento(this, isVisible());
            n.setVisible(true);


    }//GEN-LAST:event_btn_descuentoActionPerformed
    }
    private void nombre_clienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombre_clienteKeyTyped
        char validar = evt.getKeyChar();
        
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "Ingresa Solo Letras");
        }
    }//GEN-LAST:event_nombre_clienteKeyTyped
    
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
            java.util.logging.Logger.getLogger(procesar_pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(procesar_pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(procesar_pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(procesar_pago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new procesar_pago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CheckEfectivo;
    private javax.swing.JRadioButton CheckTarjeta;
    private javax.swing.JRadioButton CheckVale;
    private javax.swing.JButton btn_boleta;
    private javax.swing.JButton btn_cobrar;
    private javax.swing.JButton btn_descuento;
    private javax.swing.JButton btn_factura;
    private javax.swing.JTextField detalle;
    private javax.swing.JTextField documento;
    public static javax.swing.JTextField fecha_pago;
    private javax.swing.ButtonGroup formasDePago;
    public static javax.swing.JTextField hora_pago;
    public static javax.swing.JLabel id_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcheckRegistrar;
    private javax.swing.JCheckBox jcheckdesconocido;
    private javax.swing.JLabel lbl_descuento;
    public static javax.swing.JTextField n_filas;
    public static javax.swing.JTextField nombre_cliente;
    private javax.swing.JButton regresar;
    private javax.swing.JScrollPane tabla;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTable tabla_pago;
    public static javax.swing.JTextField tipo_pago;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JTextField txtVale;
    public static javax.swing.JTextField txt_descuento;
    private javax.swing.JTextField txt_serie;
    public static javax.swing.JTextField txt_subtotal;
    public static javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
// Crear la Conexxion a la BASE de Datos
    conectarBD cc = new conectarBD();
    Connection cn = cc.conexion();
}
