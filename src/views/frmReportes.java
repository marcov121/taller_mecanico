/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controllers.ReporteControlador;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;
import models.entities.Usuario;
import utils.PdfGenerador;

/**
 *
 * @author James
 */
public class frmReportes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmReportes.class.getName());

    // Valores del cboTipoReporte - deben coincidir con los items del combo en el designer
    static final String R_ORDENES   = "Órdenes de Trabajo";
    static final String R_SERVICIOS = "Servicios más solicitados";

    private final ReporteControlador controlador = new ReporteControlador();
    private Usuario usuarioSesion;
    private DefaultTableModel modeloTabla; // no es visual, se gestiona aquí

    /**
     * Creates new form frmReportes
     */
    public frmReportes() {
        initComponents();
        inicializarTabla();
        // Fijar el tamaño tras el pack() de initComponents para evitar que
        // la tabla mueva/redimensione el formulario al cargar datos
        java.awt.EventQueue.invokeLater(() -> setMinimumSize(getSize()));
    }

    public frmReportes(Usuario usuarioSesion) {
        this();
        this.usuarioSesion = usuarioSesion;
    }

    // Configura el DefaultTableModel sobre la JTable que arrastraste en el designer
    private void inicializarTabla() {
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tblPrevia.setModel(modeloTabla);
        tblPrevia.setRowHeight(22);
        tblPrevia.setAutoCreateRowSorter(true);
        // Columnas se ajustan al ancho del JScrollPane; evita expansión horizontal del form
        tblPrevia.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    // Convierte la fecha del JDateChooser a String "yyyy-MM-dd" para el query SQL
    private String getFecha(com.toedter.calendar.JDateChooser dc) {
        if (dc.getDate() == null) return "";
        return new SimpleDateFormat("yyyy-MM-dd").format(dc.getDate());
    }

    // Valida que ambas fechas estén seleccionadas antes de consultar o generar PDF
    private boolean validarFechas() {
        if (dcFechaDesde.getDate() == null || dcFechaHasta.getDate() == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar las fechas Desde y Hasta para continuar.",
                    "Fechas requeridas", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    // =========================================================================
    // Llamados desde los ActionPerformed que NetBeans genera al hacer doble-click
    // en cada botón dentro del designer
    // =========================================================================

    void cargarVistaPrevia() {
        if (!validarFechas()) return;
        String tipo = (String) cboTipoReporte.getSelectedItem();
        if (R_ORDENES.equals(tipo)) {
            cargarPreviaOrdenes();
        } else if (R_SERVICIOS.equals(tipo)) {
            cargarPreviaServicios();
        }
    }

    void cargarPreviaOrdenes() {
        String desde  = getFecha(dcFechaDesde);
        String hasta  = getFecha(dcFechaHasta);
        String estado = (String) cboEstado.getSelectedItem();

        List<OrdenTrabajoDetalle> datos =
                controlador.listarOrdenesParaReporte(desde, hasta, estado);

        modeloTabla.setColumnIdentifiers(new Object[]{
            "#OT", "Placa", "Cliente", "Mecánico", "Estado", "Fecha ingreso", "Total S/."
        });
        modeloTabla.setRowCount(0);

        // Stream + lambda: convierte cada objeto en fila y la agrega a la tabla
        datos.stream()
             .map(o -> new Object[]{
                 o.getIdOrdenTrabajo(),
                 o.getPlacaVehiculo(),
                 o.getClienteNombreCompleto(),
                 o.getMecanicoNombreCompleto(),
                 o.getEstado(),
                 o.getFechaIngreso(),
                 String.format("%.2f", o.getTotal())
             })
             .forEach(modeloTabla::addRow);

        // Stream para calcular el total general
        double total = datos.stream()
                            .mapToDouble(OrdenTrabajoDetalle::getTotal)
                            .sum();

        lblStatus.setText(String.format("  %d registros  |  Total general: S/. %.2f",
                datos.size(), total));
    }

    void cargarPreviaServicios() {
        String desde = getFecha(dcFechaDesde);
        String hasta = getFecha(dcFechaHasta);

        List<ReporteServicioItem> datos =
                controlador.listarServiciosMasSolicitados(desde, hasta);

        modeloTabla.setColumnIdentifiers(new Object[]{
            "Servicio", "Veces solicitado", "Unidades", "Ingresos S/."
        });
        modeloTabla.setRowCount(0);

        // Stream + lambda: map a fila y forEach para agregar a la tabla
        datos.stream()
             .map(s -> new Object[]{
                 s.getServicio(),
                 s.getVecesSolicitado(),
                 s.getTotalUnidades(),
                 String.format("%.2f", s.getTotalIngresos())
             })
             .forEach(modeloTabla::addRow);

        // Stream para calcular total de ingresos del período
        double totalIngresos = datos.stream()
                                    .mapToDouble(ReporteServicioItem::getTotalIngresos)
                                    .sum();

        // Stream + filter: cuántos servicios se solicitaron más de una vez
        long populares = datos.stream()
                              .filter(s -> s.getVecesSolicitado() > 1)
                              .count();

        lblStatus.setText(String.format("  %d servicios  |  %d solicitados más de 1 vez  |  Ingresos: S/. %.2f",
                datos.size(), populares, totalIngresos));
    }

    void generarPdf() {
        if (!validarFechas()) return;
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Primero cargue la vista previa.", "Sin datos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tipo = (String) cboTipoReporte.getSelectedItem();

        new SwingWorker<File, Void>() {
            @Override
            protected File doInBackground() throws Exception {
                String desde = getFecha(dcFechaDesde);
                String hasta = getFecha(dcFechaHasta);
                if (R_ORDENES.equals(tipo)) {
                    return PdfGenerador.generarReporteOrdenes(
                            controlador.listarOrdenesParaReporte(desde, hasta,
                                    (String) cboEstado.getSelectedItem()),
                            desde, hasta, (String) cboEstado.getSelectedItem());
                } else {
                    return PdfGenerador.generarReporteServicios(
                            controlador.listarServiciosMasSolicitados(desde, hasta),
                            desde, hasta);
                }
            }

            @Override
            protected void done() {
                try {
                    File pdf = get();
                    if (pdf != null) {
                        lblStatus.setText("  PDF generado: " + pdf.getAbsolutePath());
                        Desktop.getDesktop().open(pdf); // abre con el visor del sistema
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmReportes.this,
                            "Error al generar PDF:\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    void limpiar() {
        modeloTabla.setColumnIdentifiers(new Object[]{});
        modeloTabla.setRowCount(0);
        lblStatus.setText(" ");
        dcFechaDesde.setDate(null);
        dcFechaHasta.setDate(null);
        cboEstado.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboTipoReporte = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        btnVistaPrevia = new javax.swing.JButton();
        btnGenerarPdf = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrevia = new javax.swing.JTable();
        dcFechaDesde = new com.toedter.calendar.JDateChooser();
        dcFechaHasta = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Reportes – Taller Mecánico");

        jLabel2.setText("DESDE:");

        jLabel3.setText("TIPO:");

        cboTipoReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Órdenes de Trabajo", "Servicios más solicitados" }));

        jLabel4.setText("HASTA:");

        jLabel5.setText("ESTADO:");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "En Proceso", "Finalizado", "Activa", "Cancelada", "Entregada" }));

        btnVistaPrevia.setText("VISTA PREVIA");
        btnVistaPrevia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVistaPreviaActionPerformed(evt);
            }
        });

        btnGenerarPdf.setText("GENERAR PDF");
        btnGenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPdfActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        tblPrevia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPrevia);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnVistaPrevia)
                                .addGap(18, 18, 18)
                                .addComponent(btnGenerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5)
                        .addComponent(dcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(lblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver)
                        .addGap(51, 51, 51))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cboTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVistaPrevia)
                    .addComponent(btnGenerarPdf)
                    .addComponent(btnLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVistaPreviaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVistaPreviaActionPerformed
        // TODO add your handling code here:
        cargarVistaPrevia();
    }//GEN-LAST:event_btnVistaPreviaActionPerformed

    private void btnGenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPdfActionPerformed
        // TODO add your handling code here:
        generarPdf();
    }//GEN-LAST:event_btnGenerarPdfActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        new frmMenuPrincipal(usuarioSesion).setVisible(true);
        dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frmReportes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPdf;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVistaPrevia;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboTipoReporte;
    private com.toedter.calendar.JDateChooser dcFechaDesde;
    private com.toedter.calendar.JDateChooser dcFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTable tblPrevia;
    // End of variables declaration//GEN-END:variables
}
