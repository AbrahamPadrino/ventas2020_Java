package system2020;

import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static system2020.FormDescuento.txtCliente;
import static system2020.FormDescuento.txtFecha;
import static system2020.FormDescuento.txtMonto;
import static system2020.procesar_pago.nombre_cliente;

/**
 *
 * @author LENOVO
 */
public class agregar_stock extends javax.swing.JFrame implements Runnable {

    DefaultTableModel model, model2;
    Statement sent;

    public agregar_stock() {
        initComponents();
        Image ico = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/home.png"));
        this.setIconImage(ico); // para asignar icono a la ventana
        this.setTitle("System2020 - Registrar Ingreso"); // titulo de la ventana
        this.setResizable(false); // desactivar maximizar ventana
        setLocationRelativeTo(null); // centrar la ventana
        validaNumero(txt_precio); //invoca el metodo que restringe el ingreso de letras en campos para numeros
        CargarProductos("");
        //txt_busca.grabFocus();

        model2 = new DefaultTableModel();// Crea columnas para tabla del consumo de la mesa
        model2.addColumn("Codigo");
        model2.addColumn("Nombre");
        model2.addColumn("Cantidad");
        model2.addColumn("Fecha");
        model2.addColumn("Usuario");
        this.jTable1.setModel(model2);

        txt_fecha.setVisible(false);
        jdt_fecha.getDateEditor().setEnabled(false); //deshabilitar el campo de editor de fecha

        nuevoP.setVisible(true);
        guardaP.setVisible(false);

        jLabel6.setVisible(false);
        txt_precio.setVisible(false);
    }

    void CargarProductos(String valor) {
        try {
            String[] titulos = {"codigo", "nombre", "precio", "stock"};
            String[] registros = new String[5];
            String sql = "SELECT * FROM productos where codigo LIKE '%" + valor + "%' ";
            model = new DefaultTableModel(null, titulos);
            sent = cn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("codigo");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("stock");

                model.addRow(registros);
            }
            jTablaProductos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void limpiar_datos() {
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_cantidad.setText("");
        txt_fecha.setText("");
        jdt_fecha.setCalendar(null);

        txt_busca.grabFocus();
    }

    void limpiar_tabla() {
        int borrato = jTable1.getRowCount();
        for (int i = borrato - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
    }

    public void guardar_ingreso() {
        try {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                PreparedStatement pst = cn.prepareStatement("INSERT INTO ingresos(codigo, nombre, cantidad, fecha, usuario)VALUES(?,?,?,?,?)");
                pst.setString(1, jTable1.getValueAt(i, 0).toString());
                pst.setString(2, jTable1.getValueAt(i, 1).toString());
                pst.setString(3, jTable1.getValueAt(i, 2).toString());
                pst.setString(4, jTable1.getValueAt(i, 3).toString());
                pst.setString(5, jTable1.getValueAt(i, 4).toString());

                pst.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "INGRESO REGISTRAD0... ", "Finalizado", 1);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public void guardar_producto() {
        if (txt_codigo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta *codigo del producto*", "Completar Información", 1);
            txt_codigo.grabFocus();
        } else if (txt_nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta *nombre del producto*", "Completar Información", 1);
            txt_nombre.grabFocus();
        } else if (txt_cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta *cantidad del producto*", "Completar Información", 1);
            txt_cantidad.grabFocus();
        } else if (txt_precio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Falta *precio del producto*", "Completar Información", 1);
            txt_precio.grabFocus();

        } else {

            String tCodigo, tNombre, tPrecio, tCantidad;
            String sql = "";
            tCodigo = txt_codigo.getText();
            tNombre = txt_nombre.getText();
            tPrecio = txt_precio.getText();
            tCantidad = txt_cantidad.getText();

            sql = "INSERT INTO productos(codigo, nombre, precio, stock) VALUES(?,?,?,?)";
            try {
                PreparedStatement pst = cn.prepareCall(sql);

                pst.setString(1, tCodigo);
                pst.setString(2, tNombre);
                pst.setString(3, tPrecio);
                pst.setString(4, tCantidad);

                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "* Producto Registrado *", "Operación Exitosa", 1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(procesar_pago.class.getName()).log(Level.SEVERE, null, ex);
            }
            CargarProductos("");
            cancelarProducto();
            txt_busca.grabFocus();

        }
    }

    public void registrar_producto() {

        String regist;
        String sql = ("");
        String exp = txt_codigo.getText();

        boolean existe = false; //variable bandera para comprobar si NO existe el expediente en la BD

        try {
            sql = "SELECT * FROM productos WHERE codigo = '" + exp + "'";

            ResultSet rs = sent.executeQuery(sql);

            while (rs.next()) {
                existe = true;
                if (existe == true) {
                    regist = rs.getString("codigo");
                    if (exp.equals(regist)) {
                        JOptionPane.showMessageDialog(null, "El Código: " + regist + " Ya Está Registrado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        txt_codigo.setText("");
                        txt_codigo.grabFocus();
                    }
                }
            }//fin while res.next
            if (existe == false) {
                guardar_producto();
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void cancelarProducto() {
        txt_codigo.setText("");
        txt_nombre.setText("");
        txt_cantidad.setText("");
        jdt_fecha.setDate(null);
        txt_fecha.setText("");
        txt_precio.setText("");

        jLabel6.setVisible(false);
        txt_precio.setVisible(false);

        jdt_fecha.setVisible(true);
        jLabel5.setVisible(true);

        txt_codigo.setEditable(false);
        txt_nombre.setEditable(false);
        txt_codigo.setFocusable(false);
        txt_nombre.setFocusable(false);

        nuevoP.setVisible(true);
        guardaP.setVisible(false);

        txt_busca.setText("nombre ó codigo.");

        insertaP.setEnabled(true);
    }

    // invoca el metodo desde el contructor pasando campo al  que va a restringir
    private void validaNumero(JTextField a) { //metodo general para restringir el ingreso de letras en campos que solo permiten numeros
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                }
                if (c == '.' && txt_precio.getText().contains(".")) {

                }
            }

        });
    }

    public void reporte() {

        List lista = new ArrayList();
        for (int i = 0; i < jTablaProductos.getRowCount(); i++) {
            //listaProductos productos = new listaProductos(jTablaProductos.getValueAt(i, 0).toString(), jTablaProductos.getValueAt(i, 1).toString(), jTablaProductos.getValueAt(i, 2).toString(), jTablaProductos.getValueAt(i, 3).toString());
            //lista.add(productos);
        }
        JasperReport reporte;
        try {
            //String master = System.getProperty("user.dir") + "/pdf/reportProductos.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\System2020\\src\\reportProductos.jasper");
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(imprimir);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_cantidad = new javax.swing.JTextField();
        insertaP = new javax.swing.JButton();
        jdt_fecha = new com.toedter.calendar.JDateChooser();
        txt_fecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        nuevoP = new javax.swing.JButton();
        guardaP = new javax.swing.JButton();
        cancelaP = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaProductos = new javax.swing.JTable();
        txt_busca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_atras = new javax.swing.JButton();
        btn_imprimir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_usuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Datos de Ingreso:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Código:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 28, -1, -1));

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 28, -1, -1));

        jLabel3.setText("Cantidad:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 99, -1, -1));

        txt_codigo.setEditable(false);
        txt_codigo.setFocusable(false);
        jPanel1.add(txt_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 60, 70, -1));

        txt_nombre.setEditable(false);
        txt_nombre.setFocusable(false);
        jPanel1.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 60, 131, -1));
        jPanel1.add(txt_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 122, 70, -1));

        insertaP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        insertaP.setText("Insertar.");
        insertaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertaPActionPerformed(evt);
            }
        });
        jPanel1.add(insertaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 120, 80, -1));

        jdt_fecha.setForeground(new java.awt.Color(255, 255, 255));
        jdt_fecha.setToolTipText("Dia/Mes/Año");
        jdt_fecha.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jPanel1.add(jdt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 122, 118, -1));

        txt_fecha.setEnabled(false);
        jPanel1.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 118, -1));

        jLabel5.setText("Fecha:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 99, -1, -1));

        nuevoP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        nuevoP.setText("Nuevo.");
        nuevoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoPActionPerformed(evt);
            }
        });
        jPanel1.add(nuevoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 60, 80, -1));

        guardaP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        guardaP.setText("Guardar.");
        guardaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardaPActionPerformed(evt);
            }
        });
        jPanel1.add(guardaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 60, 76, -1));

        cancelaP.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cancelaP.setText("Cancelar.");
        cancelaP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelaPActionPerformed(evt);
            }
        });
        jPanel1.add(cancelaP, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 90, 80, -1));

        jLabel6.setText("Precio:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 99, -1, -1));

        txt_precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precioActionPerformed(evt);
            }
        });
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioKeyTyped(evt);
            }
        });
        jPanel1.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 120, 131, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Seleccione Prodúcto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jTablaProductos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablaProductos.setFocusable(false);
        jTablaProductos.getTableHeader().setReorderingAllowed(false);
        jTablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablaProductosMouseClicked(evt);
            }
        });
        jTablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablaProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablaProductos);

        txt_busca.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txt_busca.setText("nombre ó codigo.");
        txt_busca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_buscaMouseClicked(evt);
            }
        });
        txt_busca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscaKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Stock Disponible.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Opciones:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_guardar.setText("GRABAR");
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
        btn_atras.setMaximumSize(new java.awt.Dimension(105, 23));
        btn_atras.setMinimumSize(new java.awt.Dimension(105, 23));
        btn_atras.setPreferredSize(new java.awt.Dimension(105, 23));
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        btn_imprimir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_imprimir.setText("IMP. STOCK");
        btn_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_atras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_guardar)
                .addGap(18, 18, 18)
                .addComponent(btn_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btn_imprimir)
                .addGap(18, 18, 18)
                .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Lista de Registros:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

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
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        txt_usuario.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        txt_usuario.setText("usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_usuario)
                        .addGap(37, 688, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_usuario)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        int sal = JOptionPane.showConfirmDialog(null, "Desea Cancelar El Ingreso?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (sal == 0) {

            this.dispose();
    }//GEN-LAST:event_btn_atrasActionPerformed
    }

    private void jTablaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablaProductosKeyPressed

    }//GEN-LAST:event_jTablaProductosKeyPressed

    private void txt_buscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscaKeyPressed
        String[] titulos = {"codigo", "nombre", "precio", "stock"};
        String[] registros = new String[40];
        String sql = "SELECT *FROM productos WHERE nombre LIKE '%" + txt_busca.getText() + "%' "
                + "OR codigo LIKE '%" + txt_busca.getText() + "%'"
                + "OR precio LIKE '%" + txt_busca.getText() + "%'";

        model = new DefaultTableModel(null, titulos);
        conectarBD cc = new conectarBD();
        Connection conect = cc.conexion();
        try {
            Statement st = (Statement) conect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("codigo");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("stock");
                model.addRow(registros);
            }
            jTablaProductos.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_txt_buscaKeyPressed

    private void jTablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablaProductosMouseClicked
        if (evt.getButton() == 1) {
            int pst = jTablaProductos.getSelectedRow();
            try {
                String sql = "SELECT * FROM productos where codigo= " + jTablaProductos.getValueAt(pst, 0).toString();
                sent = cn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                rs.next();
                txt_codigo.setText(rs.getString("codigo"));
                txt_nombre.setText(rs.getString("nombre"));

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        txt_cantidad.grabFocus();
    }//GEN-LAST:event_jTablaProductosMouseClicked

    private void insertaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertaPActionPerformed
        try {
            if (txt_codigo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Indique El Producto", "aviso", 1);
            } else if (txt_cantidad.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Indique Cantidad Deseada", "aviso", 1);
                txt_cantidad.grabFocus();
            } else {

                SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dformat.format(jdt_fecha.getDate());
                txt_fecha.setText(date);

                String[] info = new String[5];
                info[0] = txt_codigo.getText();
                info[1] = txt_nombre.getText();
                info[2] = txt_cantidad.getText();
                info[3] = txt_fecha.getText();
                info[4] = txt_usuario.getText();
                model2.addRow(info);

                limpiar_datos();
            }
        } catch (Exception e) {
            getToolkit().beep(); // crea un sonido de error
            JOptionPane.showMessageDialog(rootPane, "Elija una fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_insertaPActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        if (txt_codigo.getText().equals("") || txt_nombre.getText().equals("") || txt_cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Algún Campo Está Vacío", "Datos Incompletos", 1);

        } else {

            int vac = JOptionPane.showConfirmDialog(null, "Desea Guardar los Registros?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (vac == 0) {
                guardar_ingreso();
                limpiar_tabla();
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        int borra = jTable1.getSelectedRow();
        if (borra >= 0) {
            int eli = JOptionPane.showConfirmDialog(null, "Desea Borrar el Registro Seleccionado?", "Confirme Opción", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (eli == 0) {
                model2.removeRow(borra);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione Algún Registro", "Debe Especificar", 1);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void nuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoPActionPerformed
        nuevoP.setVisible(false);
        guardaP.setVisible(true);

        jLabel6.setVisible(true);
        txt_precio.setVisible(true);

        jdt_fecha.setVisible(false);
        jLabel5.setVisible(false);

        txt_codigo.setEditable(true);
        txt_nombre.setEditable(true);
        txt_codigo.setFocusable(true);
        txt_nombre.setFocusable(true);
        txt_codigo.grabFocus();

        insertaP.setEnabled(false);
    }//GEN-LAST:event_nuevoPActionPerformed

    private void cancelaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelaPActionPerformed
        cancelarProducto();
    }//GEN-LAST:event_cancelaPActionPerformed

    private void btn_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirActionPerformed

        try {
            JasperReport reporte = null;
            String path = "src\\reportProductos.jasper";

            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(agregar_stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_imprimirActionPerformed

    private void guardaPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardaPActionPerformed
        registrar_producto();

    }//GEN-LAST:event_guardaPActionPerformed

    private void txt_precioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precioActionPerformed

    private void txt_precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyTyped

    }//GEN-LAST:event_txt_precioKeyTyped

    private void txt_buscaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_buscaMouseClicked
        txt_busca.setText("");
    }//GEN-LAST:event_txt_buscaMouseClicked

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
            java.util.logging.Logger.getLogger(agregar_stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agregar_stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agregar_stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agregar_stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agregar_stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_imprimir;
    private javax.swing.JButton cancelaP;
    private javax.swing.JButton guardaP;
    private javax.swing.JButton insertaP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablaProductos;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdt_fecha;
    private javax.swing.JButton nuevoP;
    private javax.swing.JTextField txt_busca;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    public static javax.swing.JLabel txt_usuario;
    // End of variables declaration//GEN-END:variables
// Crear la Conexxion a la BASE de Datos
    conectarBD cc = new conectarBD();
    Connection cn = cc.conexion();

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
