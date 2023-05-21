/*
 * Fecha de creacion: 29/03/2023 19:06:40
 * Version: v.0.1
 * Proyecto: Ventana Principal del sistema
 */
package vista;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Clientes;
import modelos.ClienteDAO;
import modelos.Detalle;
import modelos.Productos;
import modelos.ProductosDAO;
import modelos.Provedor;
import modelos.ProvedorDAO;
import modelos.Venta;
import modelos.VentasDao;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import reportes.Excel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelos.Config;
import modelos.Eventos;
import modelos.login;
import reportes.Grafico;

/**
 * @author Willy Stbn
 */
public class Sistema extends javax.swing.JFrame {

    Clientes cl = new Clientes();
    ClienteDAO client = new ClienteDAO();
    Provedor pr = new Provedor();
    ProvedorDAO prDao = new ProvedorDAO();
    Productos productos = new Productos();
    Venta v = new Venta();
    VentasDao vDao = new VentasDao();
    ProductosDAO productosDAO = new ProductosDAO();
    Detalle dev = new Detalle();
    Config conf = new Config();
    Eventos event = new Eventos();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    Date fechaVenta = new Date();

    String fechaActual;
    int item;
    double totalPagar = 0.00;

    public Sistema() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Ventana de Operaciones");
        
        
        txt_ID_CV.setVisible(false);
        txt_ID_Proveedor.setVisible(false);
        txt_ID_Producto.setVisible(false);
        txt_ID_Venta_NuevaVenta.setVisible(false);
        txt_ID_Config.setVisible(false);
        txt_TelefonoC_VN.setVisible(false);
        txt_DireccionC_VN.setVisible(false);
        txt_RazonC_VN.setVisible(false);
        txt_ID_Venta.setVisible(false);
        
        AutoCompleteDecorator.decorate(cmb_ProvedorProducto);
        productosDAO.ConsultarProvedor(cmb_ProvedorProducto);

        fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
        ListarConfiguracion();
        
    }

    public Sistema(login priv) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Ventana de Operaciones");
        
        txt_ID_CV.setVisible(false);
        txt_ID_Proveedor.setVisible(false);
        txt_ID_Producto.setVisible(false);
        txt_ID_Venta_NuevaVenta.setVisible(false);
        txt_ID_Config.setVisible(false);
        txt_TelefonoC_VN.setVisible(false);
        txt_DireccionC_VN.setVisible(false);
        txt_RazonC_VN.setVisible(false);
        txt_ID_Venta.setVisible(false);
        
        
        AutoCompleteDecorator.decorate(cmb_ProvedorProducto);
        productosDAO.ConsultarProvedor(cmb_ProvedorProducto);

        fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaVenta);
        ListarConfiguracion();
        
        if (priv.getRol().equals("Asistente")) {
            label_User.setText(priv.getNombre());
            btn_ProductosVentana.setEnabled(false);
            btn_Settings.setEnabled(false);
            btn_ProvedorVentana.setEnabled(false);
            btn_RegistrarNuevoUsuario.setEnabled(false);
        } else {
            label_User.setText(priv.getNombre());
        }
    }

    /**
     * ************************************************** Mostrar Clientes la tabla
     * ***************************************
     */
    public void ListarCliente() {
        List<Clientes> ListaCl = client.ListarCliente();
        modelo = (DefaultTableModel) table_Clientes.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListaCl.size(); i++) {
            ob[0] = ListaCl.get(i).getId();
            ob[1] = ListaCl.get(i).getDni();
            ob[2] = ListaCl.get(i).getNombre();
            ob[3] = ListaCl.get(i).getTelefono();
            ob[4] = ListaCl.get(i).getDireccion();
            ob[5] = ListaCl.get(i).getRazon_Social();

            modelo.addRow(ob);
        }
        table_Clientes.setModel(modelo);
    }

    /**
     * ************************************************ Mostrar Provedores la tabla
     * ***************************************
     */
    public void ListarProvedor() {
        List<Provedor> ListaPr = prDao.ListarProvedor();
        modelo = (DefaultTableModel) table_Provedores.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListaPr.size(); i++) {
            ob[0] = ListaPr.get(i).getId();
            ob[1] = ListaPr.get(i).getRucc();
            ob[2] = ListaPr.get(i).getNombre();
            ob[3] = ListaPr.get(i).getTelefono();
            ob[4] = ListaPr.get(i).getDireccion();
            ob[5] = ListaPr.get(i).getRazonSocial();

            modelo.addRow(ob);
        }
        table_Provedores.setModel(modelo);
    }

    /**
     * ************************************************* Mostrar Productosla tabla
     * ***************************************
     */
    public void ListarProductos() {
        List<Productos> ListarProd = productosDAO.ListarProductos();
        modelo = (DefaultTableModel) table_Productos.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListarProd.size(); i++) {
            ob[0] = ListarProd.get(i).getId();
            ob[1] = ListarProd.get(i).getCodigo();
            ob[2] = ListarProd.get(i).getDescripcion();
            ob[3] = ListarProd.get(i).getProveedor();
            ob[4] = ListarProd.get(i).getStock();
            ob[5] = ListarProd.get(i).getPrecio();

            modelo.addRow(ob);
        }
        table_Productos.setModel(modelo);
    }

    public void ListarVentas() {
        List<Venta> ListarVenta = vDao.ListarVentas();
        modelo = (DefaultTableModel) table_VentasTotales.getModel();
        Object[] ob = new Object[4];

        for (int i = 0; i < ListarVenta.size(); i++) {
            ob[0] = ListarVenta.get(i).getId();
            ob[1] = ListarVenta.get(i).getCliente();
            ob[2] = ListarVenta.get(i).getVendedor();
            ob[3] = ListarVenta.get(i).getTotal();

            modelo.addRow(ob);
        }
        table_VentasTotales.setModel(modelo);
    }

    public void ListarConfiguracion() {
        conf = productosDAO.BuscarDatos();
        txt_ID_Config.setText(" " + conf.getId());
        txt_Clave_Setting.setText("" + conf.getRucc());
        txt_Nombre_Setting.setText("" + conf.getNombre());
        txt_Telefono_Setting.setText(" " + conf.getTelefono());
        txt_Direccion_Setting.setText(" " + conf.getDireccion());
        txt_RazonSocial_Setting.setText("" + conf.getRazon());

    }

    /**
     * *************************************** Limpiar la tabla:
     * *********************************************************
     */
    public void ClearTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btn_CerrarSesion = new javax.swing.JButton();
        label_Encabezado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_NuevaVenta_Ventana = new javax.swing.JButton();
        btn_ClientesVentana = new javax.swing.JButton();
        btn_ProvedorVentana = new javax.swing.JButton();
        btn_ProductosVentana = new javax.swing.JButton();
        btn_VentasVentana = new javax.swing.JButton();
        btn_Settings = new javax.swing.JButton();
        label_Logo = new javax.swing.JLabel();
        label_User = new javax.swing.JLabel();
        btn_RegistrarNuevoUsuario = new javax.swing.JButton();
        tabbed_VentanasPrincipal = new javax.swing.JTabbedPane();
        panel_NuevaVenta = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_VentaNueva = new javax.swing.JTable();
        txt_Codigo_NuevaVenta = new javax.swing.JTextField();
        txt_Descripcion_NuevaVenta = new javax.swing.JTextField();
        txt_Cantidad_NuevaVenta = new javax.swing.JTextField();
        txt_Precio_NuevaVenta = new javax.swing.JTextField();
        txt_Stock_NuevaVenta = new javax.swing.JTextField();
        txt_DNI_Cliente_NuevaVenta = new javax.swing.JTextField();
        txt_Nombre_Cliente_VentanNueva = new javax.swing.JTextField();
        btn_GenerarVenta = new javax.swing.JButton();
        btn_EliminarVentaNueva = new javax.swing.JButton();
        txt_TotalPagar = new javax.swing.JTextField();
        txt_TelefonoC_VN = new javax.swing.JTextField();
        txt_DireccionC_VN = new javax.swing.JTextField();
        txt_RazonC_VN = new javax.swing.JTextField();
        txt_ID_Venta_NuevaVenta = new javax.swing.JTextField();
        panel_Clientes = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_DNI_Cliente = new javax.swing.JTextField();
        txt_Nombre_Cliente = new javax.swing.JTextField();
        txt_Telefono_Cliente = new javax.swing.JTextField();
        txt_Direccion_Cliente = new javax.swing.JTextField();
        txt_RazonSocial_Cliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Clientes = new javax.swing.JTable();
        btn_GuardarCliente = new javax.swing.JButton();
        btn_ActualizarCliente = new javax.swing.JButton();
        btn_EliminarCliente = new javax.swing.JButton();
        btn_LimpiarCliente = new javax.swing.JButton();
        txt_ID_CV = new javax.swing.JTextField();
        panel_Provedor = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_Clave_Proveedor = new javax.swing.JTextField();
        txt_Nombre_Provedor = new javax.swing.JTextField();
        txt_Telefono_Provedor = new javax.swing.JTextField();
        txt_Direccion_Provedor = new javax.swing.JTextField();
        txt_RazonSocial_Provedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Provedores = new javax.swing.JTable();
        btn_GuardaProvedor = new javax.swing.JButton();
        btn_ActualizarProvedor = new javax.swing.JButton();
        btn_EliminarProvedor = new javax.swing.JButton();
        btn_LimpiarProvdedor = new javax.swing.JButton();
        txt_ID_Proveedor = new javax.swing.JTextField();
        panel_Productos = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_CodigoProducto = new javax.swing.JTextField();
        txt_Descripcion_Producto = new javax.swing.JTextField();
        txt_CantidaProducto = new javax.swing.JTextField();
        txt_PrecioProducto = new javax.swing.JTextField();
        txt_ID_Producto = new javax.swing.JTextField();
        cmb_ProvedorProducto = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_Productos = new javax.swing.JTable();
        btn_GuardarProducto = new javax.swing.JButton();
        btn_ActualizarProducto = new javax.swing.JButton();
        btn_EliminarProducto = new javax.swing.JButton();
        btn_LimpiarProducto = new javax.swing.JButton();
        txt_GenerarExelProducto = new javax.swing.JButton();
        panel_VentasReal = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_VentasTotales = new javax.swing.JTable();
        btn_ImprimirReporteVenta = new javax.swing.JButton();
        txt_ID_Venta = new javax.swing.JTextField();
        btn_Graficar = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        MyDate = new com.toedter.calendar.JDateChooser();
        panel_Configuracion = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_Clave_Setting = new javax.swing.JTextField();
        txt_Nombre_Setting = new javax.swing.JTextField();
        txt_Telefono_Setting = new javax.swing.JTextField();
        txt_Direccion_Setting = new javax.swing.JTextField();
        txt_RazonSocial_Setting = new javax.swing.JTextField();
        btn_ActualizarSetting = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txt_ID_Config = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_CerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/salir_1.png"))); // NOI18N
        btn_CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CerrarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btn_CerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 50, 40));

        label_Encabezado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/encabezado.png"))); // NOI18N
        getContentPane().add(label_Encabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 960, 180));

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));

        btn_NuevaVenta_Ventana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Nventa.png"))); // NOI18N
        btn_NuevaVenta_Ventana.setText("Nueva Venta");
        btn_NuevaVenta_Ventana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuevaVenta_VentanaActionPerformed(evt);
            }
        });

        btn_ClientesVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Clientes.png"))); // NOI18N
        btn_ClientesVentana.setText("Clientes");
        btn_ClientesVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClientesVentanaActionPerformed(evt);
            }
        });

        btn_ProvedorVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/proveedor.png"))); // NOI18N
        btn_ProvedorVentana.setText("Proveedor");
        btn_ProvedorVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProvedorVentanaActionPerformed(evt);
            }
        });

        btn_ProductosVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/producto.png"))); // NOI18N
        btn_ProductosVentana.setText("Productos");
        btn_ProductosVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProductosVentanaActionPerformed(evt);
            }
        });

        btn_VentasVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/compras.png"))); // NOI18N
        btn_VentasVentana.setText("Ventas");
        btn_VentasVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VentasVentanaActionPerformed(evt);
            }
        });

        btn_Settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/config.png"))); // NOI18N
        btn_Settings.setText("Configuración");
        btn_Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SettingsActionPerformed(evt);
            }
        });

        label_Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo.png"))); // NOI18N

        label_User.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        label_User.setForeground(new java.awt.Color(255, 102, 102));
        label_User.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_User.setText("-");

        btn_RegistrarNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btn_RegistrarNuevoUsuario.setText("Nuevo Usuario");
        btn_RegistrarNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarNuevoUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_RegistrarNuevoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_VentasVentana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ProductosVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ProvedorVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_NuevaVenta_Ventana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ClientesVentana, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(btn_Settings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(label_User, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_Logo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(label_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_NuevaVenta_Ventana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ClientesVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ProvedorVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_ProductosVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_VentasVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Settings, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btn_RegistrarNuevoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(label_User, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Código");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Descripción");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Cantidad");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Precio");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Stock disponible");

        jLabel6.setText("DNI / ID");

        jLabel7.setText("NOMBRE");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/money.png"))); // NOI18N
        jLabel8.setText("Total a pagar:");

        Table_VentaNueva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "DESCRIPCIÓN", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table_VentaNueva);
        if (Table_VentaNueva.getColumnModel().getColumnCount() > 0) {
            Table_VentaNueva.getColumnModel().getColumn(0).setPreferredWidth(30);
            Table_VentaNueva.getColumnModel().getColumn(1).setPreferredWidth(100);
            Table_VentaNueva.getColumnModel().getColumn(2).setPreferredWidth(30);
            Table_VentaNueva.getColumnModel().getColumn(3).setPreferredWidth(30);
            Table_VentaNueva.getColumnModel().getColumn(4).setResizable(false);
            Table_VentaNueva.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        txt_Codigo_NuevaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Codigo_NuevaVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Codigo_NuevaVentaKeyTyped(evt);
            }
        });

        txt_Descripcion_NuevaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Descripcion_NuevaVentaKeyTyped(evt);
            }
        });

        txt_Cantidad_NuevaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Cantidad_NuevaVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Cantidad_NuevaVentaKeyTyped(evt);
            }
        });

        txt_Precio_NuevaVenta.setEditable(false);

        txt_DNI_Cliente_NuevaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_DNI_Cliente_NuevaVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DNI_Cliente_NuevaVentaKeyTyped(evt);
            }
        });

        txt_Nombre_Cliente_VentanNueva.setEditable(false);

        btn_GenerarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/print.png"))); // NOI18N
        btn_GenerarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GenerarVentaActionPerformed(evt);
            }
        });

        btn_EliminarVentaNueva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btn_EliminarVentaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarVentaNuevaActionPerformed(evt);
            }
        });

        txt_TotalPagar.setEditable(false);
        txt_TotalPagar.setForeground(new java.awt.Color(204, 0, 51));

        javax.swing.GroupLayout panel_NuevaVentaLayout = new javax.swing.GroupLayout(panel_NuevaVenta);
        panel_NuevaVenta.setLayout(panel_NuevaVentaLayout);
        panel_NuevaVentaLayout.setHorizontalGroup(
            panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_Codigo_NuevaVenta))
                        .addGap(38, 38, 38)
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 258, Short.MAX_VALUE))
                            .addComponent(txt_Descripcion_NuevaVenta))
                        .addGap(18, 18, 18)
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Cantidad_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_Precio_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                                .addComponent(txt_Stock_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_ID_Venta_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btn_EliminarVentaNueva)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_NuevaVentaLayout.createSequentialGroup()
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_DNI_Cliente_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                                        .addComponent(txt_Nombre_Cliente_VentanNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_TelefonoC_VN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(txt_DireccionC_VN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_RazonC_VN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_GenerarVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_TotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addGap(17, 17, 17))))
        );
        panel_NuevaVentaLayout.setVerticalGroup(
            panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_Codigo_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_Descripcion_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_Cantidad_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_Precio_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_ID_Venta_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Stock_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_EliminarVentaNueva))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_NuevaVentaLayout.createSequentialGroup()
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_DNI_Cliente_NuevaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Nombre_Cliente_VentanNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TelefonoC_VN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DireccionC_VN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_RazonC_VN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_GenerarVenta)
                    .addGroup(panel_NuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txt_TotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        tabbed_VentanasPrincipal.addTab("", panel_NuevaVenta);

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("DNI / ID:");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("NOMBRE:");

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("DIRECCIÓN");

        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("TÉLEFONO:");

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("RAZÓN SOCIAL:");

        txt_DNI_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_DNI_ClienteKeyTyped(evt);
            }
        });

        txt_Telefono_Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Telefono_ClienteKeyTyped(evt);
            }
        });

        table_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI / ID", "NOMBRE", "TELEFONO", "DIRECCIÓN", "RAZÓN SOCIAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_Clientes);
        if (table_Clientes.getColumnModel().getColumnCount() > 0) {
            table_Clientes.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_Clientes.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_Clientes.getColumnModel().getColumn(2).setPreferredWidth(100);
            table_Clientes.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_Clientes.getColumnModel().getColumn(4).setPreferredWidth(80);
            table_Clientes.getColumnModel().getColumn(5).setPreferredWidth(80);
        }

        btn_GuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btn_GuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_GuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarClienteActionPerformed(evt);
            }
        });

        btn_ActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btn_ActualizarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarClienteActionPerformed(evt);
            }
        });

        btn_EliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btn_EliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_EliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarClienteActionPerformed(evt);
            }
        });

        btn_LimpiarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btn_LimpiarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_LimpiarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarClienteActionPerformed(evt);
            }
        });

        txt_ID_CV.setEditable(false);

        javax.swing.GroupLayout panel_ClientesLayout = new javax.swing.GroupLayout(panel_Clientes);
        panel_Clientes.setLayout(panel_ClientesLayout);
        panel_ClientesLayout.setHorizontalGroup(
            panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ClientesLayout.createSequentialGroup()
                .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_DNI_Cliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Nombre_Cliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Telefono_Cliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Direccion_Cliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_RazonSocial_Cliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_ClientesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_GuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ActualizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_EliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_LimpiarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ID_CV, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        panel_ClientesLayout.setVerticalGroup(
            panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ClientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(txt_ID_CV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ClientesLayout.createSequentialGroup()
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DNI_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Nombre_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Telefono_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Direccion_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_RazonSocial_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_GuardarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ActualizarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_EliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LimpiarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        tabbed_VentanasPrincipal.addTab("", panel_Clientes);

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("CLAVE: ");

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("NOMBRE:");

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("TÉLEFONO: ");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("DIRECCIÓN: ");

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("RAZÓN SOCIAL:");

        txt_Clave_Proveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Clave_Proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Clave_ProveedorKeyTyped(evt);
            }
        });

        txt_Nombre_Provedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_Telefono_Provedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Telefono_Provedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Telefono_ProvedorKeyTyped(evt);
            }
        });

        txt_Direccion_Provedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_RazonSocial_Provedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        table_Provedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLAVE", "NOMBRE", "TÉLEFONO", "DIRECCIÓN", "RAZÓN SOCIAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Provedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ProvedoresMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_Provedores);
        if (table_Provedores.getColumnModel().getColumnCount() > 0) {
            table_Provedores.getColumnModel().getColumn(0).setResizable(false);
            table_Provedores.getColumnModel().getColumn(0).setPreferredWidth(10);
            table_Provedores.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_Provedores.getColumnModel().getColumn(2).setPreferredWidth(115);
            table_Provedores.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_Provedores.getColumnModel().getColumn(4).setPreferredWidth(130);
            table_Provedores.getColumnModel().getColumn(5).setPreferredWidth(70);
        }

        btn_GuardaProvedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btn_GuardaProvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardaProvedorActionPerformed(evt);
            }
        });

        btn_ActualizarProvedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btn_ActualizarProvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarProvedorActionPerformed(evt);
            }
        });

        btn_EliminarProvedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btn_EliminarProvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarProvedorActionPerformed(evt);
            }
        });

        btn_LimpiarProvdedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btn_LimpiarProvdedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarProvdedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ProvedorLayout = new javax.swing.GroupLayout(panel_Provedor);
        panel_Provedor.setLayout(panel_ProvedorLayout);
        panel_ProvedorLayout.setHorizontalGroup(
            panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ProvedorLayout.createSequentialGroup()
                .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ProvedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_Nombre_Provedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txt_Telefono_Provedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Direccion_Provedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_RazonSocial_Provedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Clave_Proveedor)))
                    .addGroup(panel_ProvedorLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btn_GuardaProvedor, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ActualizarProvedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_EliminarProvedor, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_LimpiarProvdedor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ProvedorLayout.createSequentialGroup()
                        .addComponent(txt_ID_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_ProvedorLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                        .addGap(27, 27, 27))))
        );
        panel_ProvedorLayout.setVerticalGroup(
            panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ProvedorLayout.createSequentialGroup()
                .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ProvedorLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_ProvedorLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txt_Clave_Proveedor)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txt_Nombre_Provedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_ProvedorLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txt_Telefono_Provedor)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txt_Direccion_Provedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_RazonSocial_Provedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ProvedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_GuardaProvedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_ActualizarProvedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_EliminarProvedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_LimpiarProvdedor, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 145, Short.MAX_VALUE))
                    .addGroup(panel_ProvedorLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txt_ID_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabbed_VentanasPrincipal.addTab("", panel_Provedor);

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 51, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Código:");

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 51, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Descripción:");

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 51, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Cantidad:");

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 51, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Precio:");

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 51, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Provedor:");

        txt_CodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CodigoProductoKeyTyped(evt);
            }
        });

        txt_CantidaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_CantidaProductoKeyTyped(evt);
            }
        });

        txt_PrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_PrecioProductoKeyTyped(evt);
            }
        });

        txt_ID_Producto.setEditable(false);

        cmb_ProvedorProducto.setEditable(true);

        table_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CÓDIGO", "DESCRIPCIÓN", "PROVEDOR", "STOCK", "PRECIO"
            }
        ));
        table_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ProductosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table_Productos);
        if (table_Productos.getColumnModel().getColumnCount() > 0) {
            table_Productos.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_Productos.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_Productos.getColumnModel().getColumn(2).setPreferredWidth(150);
            table_Productos.getColumnModel().getColumn(3).setPreferredWidth(60);
            table_Productos.getColumnModel().getColumn(4).setPreferredWidth(30);
            table_Productos.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        btn_GuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/GuardarTodo.png"))); // NOI18N
        btn_GuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarProductoActionPerformed(evt);
            }
        });

        btn_ActualizarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btn_ActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarProductoActionPerformed(evt);
            }
        });

        btn_EliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btn_EliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarProductoActionPerformed(evt);
            }
        });

        btn_LimpiarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nuevo.png"))); // NOI18N
        btn_LimpiarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarProductoActionPerformed(evt);
            }
        });

        txt_GenerarExelProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/excel.png"))); // NOI18N
        txt_GenerarExelProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GenerarExelProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ProductosLayout = new javax.swing.GroupLayout(panel_Productos);
        panel_Productos.setLayout(panel_ProductosLayout);
        panel_ProductosLayout.setHorizontalGroup(
            panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ProductosLayout.createSequentialGroup()
                .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ProductosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ProductosLayout.createSequentialGroup()
                                .addComponent(btn_GuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(btn_ActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_EliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_ProductosLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(btn_LimpiarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_GenerarExelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(panel_ProductosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_ID_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_ProductosLayout.createSequentialGroup()
                                .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_Descripcion_Producto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_CantidaProducto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_PrecioProducto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_ProvedorProducto, javax.swing.GroupLayout.Alignment.LEADING, 0, 155, Short.MAX_VALUE)
                                    .addComponent(txt_CodigoProducto))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        panel_ProductosLayout.setVerticalGroup(
            panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ProductosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txt_ID_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_ProductosLayout.createSequentialGroup()
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Descripcion_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CantidaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_PrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_ProvedorProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_GuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_EliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_GenerarExelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_LimpiarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        tabbed_VentanasPrincipal.addTab("", panel_Productos);

        table_VentasTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "VENDEDOR", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_VentasTotales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_VentasTotalesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(table_VentasTotales);
        if (table_VentasTotales.getColumnModel().getColumnCount() > 0) {
            table_VentasTotales.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_VentasTotales.getColumnModel().getColumn(1).setPreferredWidth(60);
            table_VentasTotales.getColumnModel().getColumn(2).setPreferredWidth(60);
            table_VentasTotales.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        btn_ImprimirReporteVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/pdf.png"))); // NOI18N
        btn_ImprimirReporteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirReporteVentaActionPerformed(evt);
            }
        });

        txt_ID_Venta.setEditable(false);

        btn_Graficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/torta.png"))); // NOI18N
        btn_Graficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GraficarActionPerformed(evt);
            }
        });

        jLabel31.setText("Seleccionar:");

        javax.swing.GroupLayout panel_VentasRealLayout = new javax.swing.GroupLayout(panel_VentasReal);
        panel_VentasReal.setLayout(panel_VentasRealLayout);
        panel_VentasRealLayout.setHorizontalGroup(
            panel_VentasRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_VentasRealLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panel_VentasRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_VentasRealLayout.createSequentialGroup()
                        .addComponent(txt_ID_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_VentasRealLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(panel_VentasRealLayout.createSequentialGroup()
                        .addComponent(btn_ImprimirReporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Graficar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(panel_VentasRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MyDate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))))
        );
        panel_VentasRealLayout.setVerticalGroup(
            panel_VentasRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_VentasRealLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panel_VentasRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_ImprimirReporteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_VentasRealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_Graficar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel_VentasRealLayout.createSequentialGroup()
                            .addComponent(jLabel31)
                            .addGap(4, 4, 4)
                            .addComponent(MyDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(txt_ID_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabbed_VentanasPrincipal.addTab("", panel_VentasReal);

        jLabel24.setText("CLAVE:");

        jLabel25.setText("NOMBRE:");

        jLabel26.setText("TËLEFONO:");

        jLabel27.setText("DIRECCIÖN:");

        jLabel28.setText("RAZÓN SOCIAL:");

        txt_Clave_Setting.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N

        txt_Nombre_Setting.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N

        txt_Telefono_Setting.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N
        txt_Telefono_Setting.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_Telefono_SettingKeyTyped(evt);
            }
        });

        txt_Direccion_Setting.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N

        txt_RazonSocial_Setting.setFont(new java.awt.Font("Microsoft YaHei", 1, 14)); // NOI18N

        btn_ActualizarSetting.setBackground(new java.awt.Color(102, 255, 102));
        btn_ActualizarSetting.setFont(new java.awt.Font("MS PGothic", 1, 14)); // NOI18N
        btn_ActualizarSetting.setForeground(new java.awt.Color(0, 0, 0));
        btn_ActualizarSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Actualizar (2).png"))); // NOI18N
        btn_ActualizarSetting.setText("Actualizar información");
        btn_ActualizarSetting.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_ActualizarSetting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ActualizarSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ActualizarSettingActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("DATOS DE LA EMPRESA");

        javax.swing.GroupLayout panel_ConfiguracionLayout = new javax.swing.GroupLayout(panel_Configuracion);
        panel_Configuracion.setLayout(panel_ConfiguracionLayout);
        panel_ConfiguracionLayout.setHorizontalGroup(
            panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ConfiguracionLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ConfiguracionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 561, Short.MAX_VALUE)
                        .addComponent(btn_ActualizarSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                        .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
                                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Telefono_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_RazonSocial_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Direccion_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addComponent(txt_ID_Config, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(117, 117, 117))
                    .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                        .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Clave_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Nombre_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel_ConfiguracionLayout.setVerticalGroup(
            panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ConfiguracionLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ConfiguracionLayout.createSequentialGroup()
                        .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Clave_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Telefono_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Nombre_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_RazonSocial_Setting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_ConfiguracionLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_ConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_Direccion_Setting, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(txt_ID_Config))))
                .addGap(52, 52, 52)
                .addComponent(btn_ActualizarSetting, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        tabbed_VentanasPrincipal.addTab("", panel_Configuracion);

        getContentPane().add(tabbed_VentanasPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 970, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * **************************************** Eliminar clientes ******************************************************
     */
    private void btn_EliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarClienteActionPerformed

        String idCliente = txt_ID_CV.getText().trim();

        if (!"".equals(idCliente)) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¡Estas seguro de elimanar!.");

            if (pregunta == 0) {
                int id = Integer.parseInt(idCliente);
                client.EliminarClientes(id);
                ClearTable();
                LimpiarCliente();
                ListarCliente();
            }
        }

    }//GEN-LAST:event_btn_EliminarClienteActionPerformed

    /**
     * ************************************ Guardar un nuevo cliente ***************************************************
     */
    private void btn_GuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarClienteActionPerformed

        String dniCliente = txt_DNI_Cliente.getText().trim();
        String nombreCliente = txt_Nombre_Cliente.getText().trim();
        String telefonoCliente = txt_Telefono_Cliente.getText().trim();
        String direccionCliente = txt_Direccion_Cliente.getText().trim();
        String razonSocial = txt_RazonSocial_Cliente.getText();

        if (!"".equals(dniCliente) || !"".equals(nombreCliente) || !"".equals(telefonoCliente) || !"".equals(direccionCliente)) {

            cl.setDni(Integer.parseInt(dniCliente));
            cl.setNombre(nombreCliente);
            cl.setTelefono(Integer.parseInt(telefonoCliente));
            cl.setDireccion(direccionCliente);
            cl.setRazon_Social(razonSocial);

            client.RegistrarCliente(cl);

            ClearTable();
            LimpiarCliente();
            ListarCliente();

            JOptionPane.showMessageDialog(null, "¡Registro exitoso del cliente!");
        } else {
            JOptionPane.showMessageDialog(null, "¡Rellene todos los campos!.");
        }

    }//GEN-LAST:event_btn_GuardarClienteActionPerformed

    /**
     * ********************************* Abrir panel Clintes*******************************************************
     */
    private void btn_ClientesVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClientesVentanaActionPerformed
        ClearTable();
        ListarCliente();
        tabbed_VentanasPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_btn_ClientesVentanaActionPerformed

    /**
     * ********************************* Capturar la fila donde de hace click de la tabla clientes*****************************
     */
    private void table_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ClientesMouseClicked

        int fila = table_Clientes.rowAtPoint(evt.getPoint());
        txt_ID_CV.setText(table_Clientes.getValueAt(fila, 0).toString());
        txt_DNI_Cliente.setText(table_Clientes.getValueAt(fila, 1).toString());
        txt_Nombre_Cliente.setText(table_Clientes.getValueAt(fila, 2).toString());
        txt_Telefono_Cliente.setText(table_Clientes.getValueAt(fila, 3).toString());
        txt_Direccion_Cliente.setText(table_Clientes.getValueAt(fila, 4).toString());
        txt_RazonSocial_Cliente.setText(table_Clientes.getValueAt(fila, 5).toString());

    }//GEN-LAST:event_table_ClientesMouseClicked

    /**
     * ****************************** Limpiar los txtFild de los clientes al hacer click en la tabla *****************************
     */
    private void btn_LimpiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarClienteActionPerformed

        LimpiarCliente();

    }//GEN-LAST:event_btn_LimpiarClienteActionPerformed

    /**
     * ********************************** Actualiazar informacion del cliente *********************************************
     */
    private void btn_ActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarClienteActionPerformed

        String id = txt_ID_CV.getText().trim();
        String dni = txt_DNI_Cliente.getText().trim();
        String nombre = txt_Nombre_Cliente.getText().trim();
        String telefono = txt_Telefono_Cliente.getText().trim();
        String direccion = txt_Direccion_Cliente.getText().trim();
        String razonSocial = txt_RazonSocial_Cliente.getText().trim();

        if ("".equals(txt_ID_CV.getText().trim())) {
            JOptionPane.showMessageDialog(null, "¡Seleccione una fila!");
        } else {
            if (!"".equals(nombre) && !"".equals(dni) && !"".equals(telefono) && !"".equals(direccion)) {
                cl.setDni(Integer.parseInt(dni));
                cl.setNombre(nombre);
                cl.setTelefono(Integer.parseInt(telefono));
                cl.setDireccion(direccion);
                cl.setRazon_Social(razonSocial);
                cl.setId(Integer.parseInt(id));

                client.ModificarCliente(cl);
                ClearTable();
                LimpiarCliente();
                ListarCliente();
                JOptionPane.showMessageDialog(null, "¡Modificacion Exitosa!.");
            } else {
                JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
            }
        }
    }//GEN-LAST:event_btn_ActualizarClienteActionPerformed

    /**
     * *********************************** Guardar nuevo provedor
     * *******************************************************
     */
    private void btn_GuardaProvedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardaProvedorActionPerformed

        String clave = txt_Clave_Proveedor.getText().trim();
        String nombre = txt_Nombre_Provedor.getText().trim();
        String telefono = txt_Telefono_Provedor.getText().trim();
        String direccion = txt_Direccion_Provedor.getText().trim();
        String RazonSocial = txt_RazonSocial_Provedor.getText().trim();

        if (!"".equals(clave) && !"".equals(nombre) && !"".equals(telefono) && !"".equals(direccion)) {
            pr.setRucc(Integer.parseInt(clave));
            pr.setNombre(nombre);
            pr.setTelefono(Integer.parseInt(telefono));
            pr.setDireccion(direccion);
            pr.setRazonSocial(RazonSocial);

            prDao.RegistrarProvedor(pr);
            ClearTable();
            ListarProvedor();
            LimpiarProvedor();
            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
        } else {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos.");
        }

    }//GEN-LAST:event_btn_GuardaProvedorActionPerformed

    private void btn_ProvedorVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProvedorVentanaActionPerformed

        ClearTable();
        ListarProvedor();
        tabbed_VentanasPrincipal.setSelectedIndex(2);
    }//GEN-LAST:event_btn_ProvedorVentanaActionPerformed

    private void table_ProvedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ProvedoresMouseClicked
        int fila = table_Provedores.rowAtPoint(evt.getPoint());

        txt_ID_Proveedor.setText(table_Provedores.getValueAt(fila, 0).toString());
        txt_Clave_Proveedor.setText(table_Provedores.getValueAt(fila, 1).toString());
        txt_Nombre_Provedor.setText(table_Provedores.getValueAt(fila, 2).toString());
        txt_Telefono_Provedor.setText(table_Provedores.getValueAt(fila, 3).toString());
        txt_Direccion_Provedor.setText(table_Provedores.getValueAt(fila, 4).toString());
        txt_RazonSocial_Provedor.setText(table_Provedores.getValueAt(fila, 5).toString());
    }//GEN-LAST:event_table_ProvedoresMouseClicked

    private void btn_EliminarProvedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarProvedorActionPerformed

        //Verificamos que haya un id seleccionado
        String id = txt_ID_Proveedor.getText().trim();
        if (!id.equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el provedor?");
            if (confirmacion == 0) {
                prDao.EliminarProvedor(Integer.parseInt(id));
                LimpiarProvedor();
                ClearTable();
                ListarProvedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un provedor de la tabla!");
        }
    }//GEN-LAST:event_btn_EliminarProvedorActionPerformed

    private void btn_LimpiarProvdedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarProvdedorActionPerformed

        ListarProvedor();
        LimpiarProvedor();

    }//GEN-LAST:event_btn_LimpiarProvdedorActionPerformed

    private void btn_ActualizarProvedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarProvedorActionPerformed
        String clave = txt_Clave_Proveedor.getText().trim();
        String nombre = txt_Nombre_Provedor.getText().trim();
        String telefono = txt_Telefono_Provedor.getText().trim();
        String direccion = txt_Direccion_Provedor.getText().trim();
        String RazonSocial = txt_RazonSocial_Provedor.getText().trim();

        if (txt_ID_Proveedor.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "¡Seleccione un provedor de la tabla!.");
        } else {
            if (!clave.equals("") && !nombre.equals("") && !telefono.equals("") && !direccion.equals("")) {
                pr.setRucc(Integer.parseInt(clave));
                pr.setNombre(nombre);
                pr.setTelefono(Integer.parseInt(telefono));
                pr.setDireccion(direccion);
                pr.setRazonSocial(RazonSocial);
                pr.setId(Integer.parseInt(txt_ID_Proveedor.getText().trim()));

                prDao.ModificarProvedor(pr);
                ClearTable();
                ListarProvedor();
                LimpiarProvedor();
                JOptionPane.showMessageDialog(null, "¡Modificacion Exitosa!");
            } else {
                JOptionPane.showMessageDialog(null, "¡Rellene todos los campos!.");
            }
        }
    }//GEN-LAST:event_btn_ActualizarProvedorActionPerformed


    private void btn_GuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarProductoActionPerformed

        String codigo = txt_CodigoProducto.getText().trim();
        String nombre = txt_Descripcion_Producto.getText().trim();
        String cantidad = txt_CantidaProducto.getText().trim();
        String precio = txt_PrecioProducto.getText().trim();
        String provedor = cmb_ProvedorProducto.getSelectedItem().toString();

        if (!codigo.equals("") && !nombre.equals("") && !cantidad.equals("") && !precio.equals("")) {

            productos.setCodigo(codigo);;
            productos.setDescripcion(nombre);
            productos.setProveedor(provedor);
            productos.setPrecio(Double.parseDouble(precio));
            productos.setStock(Integer.parseInt(cantidad));
            productosDAO.RegistrarProducto(productos);

            ClearTable();
            ListarProductos();
            LimpiarProductos();
            JOptionPane.showMessageDialog(null, "¡Registro del producto exitosamente!.");

        } else {
            JOptionPane.showMessageDialog(null, "¡Rellene todos los campos!");
        }


    }//GEN-LAST:event_btn_GuardarProductoActionPerformed

    private void btn_ProductosVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProductosVentanaActionPerformed

        ClearTable();
        LimpiarProductos();
        ListarProductos();
        tabbed_VentanasPrincipal.setSelectedIndex(3);

    }//GEN-LAST:event_btn_ProductosVentanaActionPerformed

    private void btn_LimpiarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarProductoActionPerformed

        LimpiarProductos();
        ListarProductos();
    }//GEN-LAST:event_btn_LimpiarProductoActionPerformed

    private void table_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ProductosMouseClicked

        int fila = table_Productos.rowAtPoint(evt.getPoint());

        txt_ID_Producto.setText(table_Productos.getValueAt(fila, 0).toString());
        txt_CodigoProducto.setText(table_Productos.getValueAt(fila, 1).toString());
        txt_Descripcion_Producto.setText(table_Productos.getValueAt(fila, 2).toString());
        txt_CantidaProducto.setText(table_Productos.getValueAt(fila, 4).toString());
        txt_PrecioProducto.setText(table_Productos.getValueAt(fila, 5).toString());
        cmb_ProvedorProducto.setSelectedItem(table_Productos.getValueAt(fila, 3).toString());

    }//GEN-LAST:event_table_ProductosMouseClicked

    private void btn_EliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarProductoActionPerformed

        //Verificamos que haya un id seleccionado
        String id = txt_ID_Producto.getText().trim();
        if (!id.equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el producto?");
            if (confirmacion == 0) {
                productosDAO.EliminarProducto(Integer.parseInt(id));
                LimpiarProductos();
                ClearTable();
                ListarProductos();
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un producto de la tabla!");
        }

    }//GEN-LAST:event_btn_EliminarProductoActionPerformed

    private void btn_ActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarProductoActionPerformed
        String id = txt_ID_Producto.getText().trim();
        String codigo = txt_CodigoProducto.getText().trim();
        String descripcion = txt_Descripcion_Producto.getText().trim();
        String cantidad = txt_CantidaProducto.getText().trim();
        String precio = txt_PrecioProducto.getText().trim();
        String provedor = cmb_ProvedorProducto.getSelectedItem().toString();

        if (id.equals("")) {
            JOptionPane.showMessageDialog(null, "¡Seleccione un producto de la tabla!.");
        } else {
            if (!codigo.equals("") && !descripcion.equals("") && !cantidad.equals("") && !precio.equals("") && !provedor.equals("")) {
                productos.setId(Integer.parseInt(id));
                productos.setCodigo(codigo);
                productos.setDescripcion(descripcion);
                productos.setPrecio(Double.parseDouble(precio));
                productos.setStock(Integer.parseInt(cantidad));
                productos.setProveedor(provedor);

                productosDAO.ModificarProducto(productos);
                LimpiarProductos();
                ClearTable();
                ListarProductos();

                JOptionPane.showMessageDialog(null, "¡Productos modificado exitosamente!.");

            } else {
                JOptionPane.showMessageDialog(null, "¡Rellene todos los campos!.");
            }
        }
    }//GEN-LAST:event_btn_ActualizarProductoActionPerformed

    private void txt_GenerarExelProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GenerarExelProductoActionPerformed
        Excel.reporte();
    }//GEN-LAST:event_txt_GenerarExelProductoActionPerformed


    private void btn_NuevaVenta_VentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuevaVenta_VentanaActionPerformed
        tabbed_VentanasPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_btn_NuevaVenta_VentanaActionPerformed

    private void txt_Codigo_NuevaVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Codigo_NuevaVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //Verificar que el campo codigo no este vacio:
            String codigo = txt_Codigo_NuevaVenta.getText().trim();
            if (!codigo.equals("")) {
                productos = productosDAO.BuscarProductos(codigo);
                if (productos.getDescripcion() != null) {

                    String descripcion = productos.getDescripcion();
                    int stock = productos.getStock();
                    double precio = productos.getPrecio();

                    txt_Descripcion_NuevaVenta.setText("" + descripcion);
                    txt_Stock_NuevaVenta.setText("" + productos.getStock());
                    txt_Precio_NuevaVenta.setText("" + precio);
                    txt_ID_Venta_NuevaVenta.setText("" + productos.getId());
                    txt_Cantidad_NuevaVenta.requestFocus();
                } else {
                    txt_Descripcion_NuevaVenta.setText("");
                    txt_Stock_NuevaVenta.setText("");
                    txt_Precio_NuevaVenta.setText("");
                    txt_Codigo_NuevaVenta.requestFocus();
                    LimpiarNuevaVenta();
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Ingrese el codigo del producto!.");
                txt_Codigo_NuevaVenta.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_Codigo_NuevaVentaKeyPressed

    private void txt_Cantidad_NuevaVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Cantidad_NuevaVentaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String cantidad = txt_Cantidad_NuevaVenta.getText().trim();

            if (!cantidad.equals("")) {
                String codigo = txt_Codigo_NuevaVenta.getText().trim();
                String descripcion = txt_Descripcion_NuevaVenta.getText().trim();
                int cant = Integer.parseInt(cantidad);
                double precio = Double.parseDouble(txt_Precio_NuevaVenta.getText().trim());
                double total = precio * cant;
                int stock = Integer.parseInt(txt_Stock_NuevaVenta.getText().trim());

                //Verificar que la cantidad no supere al stock:
                if (stock >= cant) {
                    item = item + 1;
                    tmp = (DefaultTableModel) Table_VentaNueva.getModel();

                    for (int i = 0; i < Table_VentaNueva.getRowCount(); i++) {
                        if (Table_VentaNueva.getValueAt(i, 1).equals(descripcion)) {
                            JOptionPane.showMessageDialog(null, "¡Ya esta este producto registrado!.");
                            return;
                        }
                    }

                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(codigo);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precio);
                    lista.add(total);

                    Object[] o = new Object[5];
                    o[0] = lista.get(1);
                    o[1] = lista.get(2);
                    o[2] = lista.get(3);
                    o[3] = lista.get(4);
                    o[4] = lista.get(5);

                    tmp.addRow(o);
                    Table_VentaNueva.setModel(tmp);
                    TotalPagar();
                    LimpiarNuevaVenta();
                    txt_Codigo_NuevaVenta.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(null, "¡¡Stock no disponible!!.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡¡Ingrese una cantidad!!.");
            }
        }
    }//GEN-LAST:event_txt_Cantidad_NuevaVentaKeyPressed

    private void btn_EliminarVentaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarVentaNuevaActionPerformed
        if (Table_VentaNueva.getSelectedRow() != -1) {
            modelo = (DefaultTableModel) Table_VentaNueva.getModel();
            modelo.removeRow(Table_VentaNueva.getSelectedRow());
            TotalPagar();
            txt_Codigo_NuevaVenta.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "No hay ninguna fila seleccionada.");
        }
    }//GEN-LAST:event_btn_EliminarVentaNuevaActionPerformed

    private void txt_DNI_Cliente_NuevaVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DNI_Cliente_NuevaVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txt_DNI_Cliente_NuevaVenta.getText().trim())) {
                int dni = Integer.parseInt(txt_DNI_Cliente_NuevaVenta.getText().trim());
                cl = client.BuscarCLiente(dni);
                if (cl.getNombre() != null) {
                    txt_Nombre_Cliente_VentanNueva.setText("" + cl.getNombre());
                    txt_TelefonoC_VN.setText("" + cl.getTelefono());
                    txt_DireccionC_VN.setText("" + cl.getDireccion());
                    txt_RazonC_VN.setText("" + cl.getRazon_Social());
                } else {
                    txt_DNI_Cliente_NuevaVenta.setText("");
                    JOptionPane.showMessageDialog(null, "¡Cliente no registrado!.");
                }
            }
        }
    }//GEN-LAST:event_txt_DNI_Cliente_NuevaVentaKeyPressed

    private void btn_GenerarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GenerarVentaActionPerformed

        if (Table_VentaNueva.getRowCount() > 0) {
            if (!txt_Nombre_Cliente_VentanNueva.getText().trim().equals("")) {
                RegistrarVenta();
                RegistrarDetalle();
                ActualizarStock();
                pdf();
                LimpiarNuevaVenta();
                LimpiarTablaNuevaVenta();
                LimpiarNuevaVentaFinalizada();
            } else {
                JOptionPane.showMessageDialog(null, "¡Ingrese el codigo del cliente!.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe productos en la tabla.");
        }
    }//GEN-LAST:event_btn_GenerarVentaActionPerformed

    /**
     * ****************** ***Validacion de campos - Solo numeros || letras ****************************************************
     */
    private void txt_Codigo_NuevaVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Codigo_NuevaVentaKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_Codigo_NuevaVentaKeyTyped

    private void txt_Descripcion_NuevaVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Descripcion_NuevaVentaKeyTyped
        event.textKeyPress(evt);
    }//GEN-LAST:event_txt_Descripcion_NuevaVentaKeyTyped

    private void txt_Cantidad_NuevaVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Cantidad_NuevaVentaKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_Cantidad_NuevaVentaKeyTyped

    private void txt_DNI_Cliente_NuevaVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DNI_Cliente_NuevaVentaKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_DNI_Cliente_NuevaVentaKeyTyped

    private void txt_DNI_ClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DNI_ClienteKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_DNI_ClienteKeyTyped

    private void txt_Telefono_ClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Telefono_ClienteKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_Telefono_ClienteKeyTyped

    private void txt_Clave_ProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Clave_ProveedorKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_Clave_ProveedorKeyTyped

    private void txt_Telefono_ProvedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Telefono_ProvedorKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_Telefono_ProvedorKeyTyped

    private void txt_CodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CodigoProductoKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_CodigoProductoKeyTyped

    private void txt_CantidaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CantidaProductoKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_CantidaProductoKeyTyped

    private void txt_PrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PrecioProductoKeyTyped
        event.numberDecimalKeyPress(evt, txt_PrecioProducto);
    }//GEN-LAST:event_txt_PrecioProductoKeyTyped

    private void txt_Telefono_SettingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Telefono_SettingKeyTyped
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txt_Telefono_SettingKeyTyped


    private void btn_ActualizarSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ActualizarSettingActionPerformed
        String clave = txt_Clave_Setting.getText().trim();
        String nombre = txt_Nombre_Setting.getText().trim();
        String telefono = txt_Telefono_Setting.getText().trim();
        String razon = txt_RazonSocial_Setting.getText().trim();
        String direccion = txt_Direccion_Setting.getText().trim();
        String id = txt_ID_Config.getText().trim();

        if (txt_ID_Config.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "¡Error al cargar informacion!.\nNo es posible actualizar en este momento.");
        } else {
            if (!clave.equals("") && !nombre.equals("") && !telefono.equals("") && !direccion.equals("")) {
                conf.setRucc(Integer.parseInt(clave));
                conf.setNombre(nombre);
                conf.setTelefono(Integer.parseInt(telefono));
                conf.setDireccion(direccion);
                conf.setRazon(razon);

                productosDAO.ModificarInformacionEmpresa(conf);
                ListarConfiguracion();
                JOptionPane.showMessageDialog(null, "¡Modificacion Exitosa!");
            } else {
                JOptionPane.showMessageDialog(null, "¡Rellene todos los campos!.");
            }
        }
    }//GEN-LAST:event_btn_ActualizarSettingActionPerformed

    private void btn_CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CerrarSesionActionPerformed
        Login iniciar = new Login();
        iniciar.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_CerrarSesionActionPerformed

    private void btn_VentasVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VentasVentanaActionPerformed
        tabbed_VentanasPrincipal.setSelectedIndex(4);
        ClearTable();
        ListarVentas();
    }//GEN-LAST:event_btn_VentasVentanaActionPerformed

    private void table_VentasTotalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_VentasTotalesMouseClicked
        int fila = table_VentasTotales.rowAtPoint(evt.getPoint());

        txt_ID_Venta.setText(table_VentasTotales.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_table_VentasTotalesMouseClicked

    private void btn_ImprimirReporteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirReporteVentaActionPerformed
        int id = Integer.parseInt(txt_ID_Venta.getText().trim());
        if (!txt_ID_Venta.getText().trim().equals("")) {
            File file = new File("src/pdf/venta-" + id + ".pdf");
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.err.println("¡¡Error al abrir el archivo!!\n");
                JOptionPane.showMessageDialog(null, "¡No se encontro el archivo de la venta!");
            }
        }
    }//GEN-LAST:event_btn_ImprimirReporteVentaActionPerformed

    private void btn_SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SettingsActionPerformed
        tabbed_VentanasPrincipal.setSelectedIndex(5);
    }//GEN-LAST:event_btn_SettingsActionPerformed

    private void btn_RegistrarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarNuevoUsuarioActionPerformed
        Registro regN = new Registro();
        regN.setVisible(true);
    }//GEN-LAST:event_btn_RegistrarNuevoUsuarioActionPerformed

    private void btn_GraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GraficarActionPerformed
        String fechaReporte = new SimpleDateFormat("dd/MM/yyyy").format(MyDate.getDate());
        Grafico.Graficar(fechaReporte);
    }//GEN-LAST:event_btn_GraficarActionPerformed

    /**
     * ******************************************** Main *************************************************************
     */
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
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Sistema().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser MyDate;
    private javax.swing.JTable Table_VentaNueva;
    private javax.swing.JButton btn_ActualizarCliente;
    private javax.swing.JButton btn_ActualizarProducto;
    private javax.swing.JButton btn_ActualizarProvedor;
    private javax.swing.JButton btn_ActualizarSetting;
    private javax.swing.JButton btn_CerrarSesion;
    private javax.swing.JButton btn_ClientesVentana;
    private javax.swing.JButton btn_EliminarCliente;
    private javax.swing.JButton btn_EliminarProducto;
    private javax.swing.JButton btn_EliminarProvedor;
    private javax.swing.JButton btn_EliminarVentaNueva;
    private javax.swing.JButton btn_GenerarVenta;
    private javax.swing.JButton btn_Graficar;
    private javax.swing.JButton btn_GuardaProvedor;
    private javax.swing.JButton btn_GuardarCliente;
    private javax.swing.JButton btn_GuardarProducto;
    private javax.swing.JButton btn_ImprimirReporteVenta;
    private javax.swing.JButton btn_LimpiarCliente;
    private javax.swing.JButton btn_LimpiarProducto;
    private javax.swing.JButton btn_LimpiarProvdedor;
    private javax.swing.JButton btn_NuevaVenta_Ventana;
    private javax.swing.JButton btn_ProductosVentana;
    private javax.swing.JButton btn_ProvedorVentana;
    private javax.swing.JButton btn_RegistrarNuevoUsuario;
    private javax.swing.JButton btn_Settings;
    private javax.swing.JButton btn_VentasVentana;
    private javax.swing.JComboBox<String> cmb_ProvedorProducto;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel label_Encabezado;
    private javax.swing.JLabel label_Logo;
    private javax.swing.JLabel label_User;
    private javax.swing.JPanel panel_Clientes;
    private javax.swing.JPanel panel_Configuracion;
    private javax.swing.JPanel panel_NuevaVenta;
    private javax.swing.JPanel panel_Productos;
    private javax.swing.JPanel panel_Provedor;
    private javax.swing.JPanel panel_VentasReal;
    private javax.swing.JTabbedPane tabbed_VentanasPrincipal;
    private javax.swing.JTable table_Clientes;
    private javax.swing.JTable table_Productos;
    private javax.swing.JTable table_Provedores;
    private javax.swing.JTable table_VentasTotales;
    private javax.swing.JTextField txt_CantidaProducto;
    private javax.swing.JTextField txt_Cantidad_NuevaVenta;
    private javax.swing.JTextField txt_Clave_Proveedor;
    private javax.swing.JTextField txt_Clave_Setting;
    private javax.swing.JTextField txt_CodigoProducto;
    private javax.swing.JTextField txt_Codigo_NuevaVenta;
    private javax.swing.JTextField txt_DNI_Cliente;
    private javax.swing.JTextField txt_DNI_Cliente_NuevaVenta;
    private javax.swing.JTextField txt_Descripcion_NuevaVenta;
    private javax.swing.JTextField txt_Descripcion_Producto;
    private javax.swing.JTextField txt_DireccionC_VN;
    private javax.swing.JTextField txt_Direccion_Cliente;
    private javax.swing.JTextField txt_Direccion_Provedor;
    private javax.swing.JTextField txt_Direccion_Setting;
    private javax.swing.JButton txt_GenerarExelProducto;
    private javax.swing.JTextField txt_ID_CV;
    private javax.swing.JTextField txt_ID_Config;
    private javax.swing.JTextField txt_ID_Producto;
    private javax.swing.JTextField txt_ID_Proveedor;
    private javax.swing.JTextField txt_ID_Venta;
    private javax.swing.JTextField txt_ID_Venta_NuevaVenta;
    private javax.swing.JTextField txt_Nombre_Cliente;
    private javax.swing.JTextField txt_Nombre_Cliente_VentanNueva;
    private javax.swing.JTextField txt_Nombre_Provedor;
    private javax.swing.JTextField txt_Nombre_Setting;
    private javax.swing.JTextField txt_PrecioProducto;
    private javax.swing.JTextField txt_Precio_NuevaVenta;
    private javax.swing.JTextField txt_RazonC_VN;
    private javax.swing.JTextField txt_RazonSocial_Cliente;
    private javax.swing.JTextField txt_RazonSocial_Provedor;
    private javax.swing.JTextField txt_RazonSocial_Setting;
    private javax.swing.JTextField txt_Stock_NuevaVenta;
    private javax.swing.JTextField txt_TelefonoC_VN;
    private javax.swing.JTextField txt_Telefono_Cliente;
    private javax.swing.JTextField txt_Telefono_Provedor;
    private javax.swing.JTextField txt_Telefono_Setting;
    private javax.swing.JTextField txt_TotalPagar;
    // End of variables declaration//GEN-END:variables

    /**
     * *********************************** Limpiar los txtFilds de los clientes:
     * *********************************************
     */
    private void LimpiarCliente() {

        txt_ID_CV.setText("");
        txt_DNI_Cliente.setText("");
        txt_Nombre_Cliente.setText("");
        txt_Telefono_Cliente.setText("");
        txt_Direccion_Cliente.setText("");
        txt_RazonSocial_Cliente.setText("");
    }

    private void LimpiarProvedor() {
        txt_ID_Proveedor.setText("");
        txt_Clave_Proveedor.setText("");
        txt_Nombre_Provedor.setText("");
        txt_Telefono_Provedor.setText("");
        txt_Direccion_Provedor.setText("");
        txt_RazonSocial_Provedor.setText("");
    }

    private void LimpiarProductos() {
        txt_CodigoProducto.setText("");
        txt_Descripcion_Producto.setText("");
        txt_CantidaProducto.setText("");
        txt_PrecioProducto.setText("");
        cmb_ProvedorProducto.setSelectedItem("");
    }

    private void TotalPagar() {
        totalPagar = 0.00;
        int numFila = Table_VentaNueva.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(Table_VentaNueva.getModel().getValueAt(i, 4)));
            totalPagar += cal;
        }
        txt_TotalPagar.setText(String.format("%.2f", totalPagar));
    }

    private void LimpiarNuevaVenta() {
        txt_Codigo_NuevaVenta.setText("");
        txt_Descripcion_NuevaVenta.setText("");
        txt_Cantidad_NuevaVenta.setText("");
        txt_Precio_NuevaVenta.setText("");
        txt_Stock_NuevaVenta.setText("");
        txt_ID_Venta_NuevaVenta.setText("");

    }

    private void LimpiarNuevaVentaFinalizada() {
        txt_TotalPagar.setText("");

        txt_DNI_Cliente_NuevaVenta.setText("");
        txt_Nombre_Cliente_VentanNueva.setText("");
        txt_TelefonoC_VN.setText("");
        txt_DireccionC_VN.setText("");
        txt_RazonC_VN.setText("");
    }

    private void RegistrarVenta() {
        String cliente = txt_Nombre_Cliente_VentanNueva.getText().trim();
        String vendedor = label_User.getText();
        Double total = totalPagar;

        v.setCliente(cliente);
        v.setVendedor(vendedor);
        v.setTotal(total);
        v.setFecha(fechaActual);
        vDao.RegistrarVenta(v);
    }

    private void RegistrarDetalle() {
        for (int i = 0; i < Table_VentaNueva.getRowCount(); i++) {

            int id = vDao.IDVenta();
            String cod = Table_VentaNueva.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(Table_VentaNueva.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(Table_VentaNueva.getValueAt(i, 3).toString());

            dev.setId(id);
            dev.setCod_pro(cod);
            dev.setCantidad(cant);
            dev.setPrecio(precio);
            vDao.RegistrarDetalle(dev);
        }
    }

    private void ActualizarStock() {
        for (int i = 0; i < Table_VentaNueva.getRowCount(); i++) {
            String cod = Table_VentaNueva.getValueAt(i, 0).toString();
            int cant = Integer.parseInt(Table_VentaNueva.getValueAt(i, 2).toString());

            productos = productosDAO.BuscarProductos(cod);
            int StockActual = productos.getStock() - cant;

            vDao.ActualizarStock(StockActual, cod);
        }
    }

    private void LimpiarTablaNuevaVenta() {
        tmp = (DefaultTableModel) Table_VentaNueva.getModel();
        int fila = Table_VentaNueva.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    private void pdf() {
        try {
            int id = vDao.IDVenta();
            FileOutputStream archivo;
            File file = new File("src/pdf/venta-" + id + ".pdf");
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            //Informacion de los encabezado de la tabla:
            Image img = Image.getInstance("src/img/logo_pdf.png");
            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            Date date = new Date();
            fecha.add("Factura: " + id + "\n" + "Fecha: " + new SimpleDateFormat("dd-mm-yyyy").format(date) + "\n\n");

            //PDF para el archivo:
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);

            float[] ColumnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            Encabezado.addCell(img);

            String Clave = txt_Clave_Setting.getText().trim();
            String nom = txt_Nombre_Setting.getText().trim();
            String tel = txt_Telefono_Setting.getText().trim();
            String dir = txt_Direccion_Setting.getText().trim();
            String raz = txt_RazonSocial_Setting.getText().trim();

            Encabezado.addCell("");
            Encabezado.addCell("Clave: " + Clave + "\nNombre: " + nom + "\nTelefono: " + tel + "\nDirección: " + dir + "\nRazon social: " + raz);
//            String contenidoCelda = "Clave: " + Clave + "\nNombre: " + nom + "\nTelefono: " + tel + "\nDirección: " + dir + "\nRazon social: " + raz;
//            PdfPCell celda = new PdfPCell(new Phrase(contenidoCelda, negrita));
//            Encabezado.addCell(celda);
            Encabezado.addCell(fecha);
            doc.add(Encabezado);

            //Información de los clientes en el documento:
            Paragraph clie = new Paragraph();
            clie.add(Chunk.NEWLINE);
            clie.add("Datos del clientes: \n\n");
            doc.add(clie);

            PdfPTable tablaClie = new PdfPTable(4);
            tablaClie.setWidthPercentage(100);
            tablaClie.getDefaultCell().setBorder(0);
            float[] ColumnaClie = new float[]{20f, 50f, 15f, 30};
            tablaClie.setWidths(ColumnaClie);
            tablaClie.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cl1 = new PdfPCell(new Phrase("DNI/CLAVE", negrita));
            PdfPCell cl2 = new PdfPCell(new Phrase("NOMBRE", negrita));
            PdfPCell cl3 = new PdfPCell(new Phrase("TELEFONO", negrita));
            PdfPCell cl4 = new PdfPCell(new Phrase("DIRECCION", negrita));
            cl1.setBorder(0);
            cl2.setBorder(0);
            cl3.setBorder(0);
            cl4.setBorder(0);
            tablaClie.addCell(cl1);
            tablaClie.addCell(cl2);
            tablaClie.addCell(cl3);
            tablaClie.addCell(cl4);
            tablaClie.addCell(txt_DNI_Cliente_NuevaVenta.getText().trim());
            tablaClie.addCell(txt_Nombre_Cliente_VentanNueva.getText().trim());
            tablaClie.addCell(txt_TelefonoC_VN.getText().trim());
            tablaClie.addCell(txt_DireccionC_VN.getText().trim());
            doc.add(tablaClie);

            //Información de los productos en el documento:
            Paragraph infPro = new Paragraph();
            infPro.add(Chunk.NEWLINE);
            infPro.add("Productos adquiridos: \n\n");
            doc.add(infPro);

            PdfPTable tablaPro = new PdfPTable(4);
            tablaPro.setWidthPercentage(100);
            tablaPro.getDefaultCell().setBorder(0);
            float[] ColumnaPro = new float[]{20f, 50f, 30f, 40};
            tablaPro.setWidths(ColumnaPro);
            tablaPro.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell pro1 = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell pro2 = new PdfPCell(new Phrase("Descripcion", negrita));
            PdfPCell pro3 = new PdfPCell(new Phrase("Precio Unitario", negrita));
            PdfPCell pro4 = new PdfPCell(new Phrase("Precio Total", negrita));
            pro1.setBorder(0);
            pro2.setBorder(0);
            pro3.setBorder(0);
            pro4.setBorder(0);
            pro1.setBackgroundColor(BaseColor.DARK_GRAY);
            pro2.setBackgroundColor(BaseColor.DARK_GRAY);
            pro3.setBackgroundColor(BaseColor.DARK_GRAY);
            pro4.setBackgroundColor(BaseColor.DARK_GRAY);
            tablaPro.addCell(pro1);
            tablaPro.addCell(pro2);
            tablaPro.addCell(pro3);
            tablaPro.addCell(pro4);
            for (int i = 0; i < Table_VentaNueva.getRowCount(); i++) {
                String producto = Table_VentaNueva.getValueAt(i, 1).toString();
                String cantidad = Table_VentaNueva.getValueAt(i, 2).toString();
                String precio = Table_VentaNueva.getValueAt(i, 3).toString();
                String total = Table_VentaNueva.getValueAt(i, 4).toString();
                tablaPro.addCell(cantidad);
                tablaPro.addCell(producto);
                tablaPro.addCell(precio);
                tablaPro.addCell(total);
            }

            doc.add(tablaPro);

            //Informacion del total a pagar:
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
            info.add("\nTotal a pagar: $" + String.format("%.2f", totalPagar));
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            //Firma:
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("\nCancelación y Firma:\n\n\n");
            firma.add("_____________________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            //Firma:
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("\n******************¡¡Gracias por tu compra!!****************");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (FileNotFoundException e) {
            System.err.println("Error al crear el pdf o abrir.\n" + e);
        } catch (DocumentException | IOException ex) {
            System.err.println("Error al generarl PDF\n" + ex);
        }
    }

}
