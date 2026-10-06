
package views;

import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.entities.Especialidad;
import models.dao.EspecialidadDao;
import javax.swing.JOptionPane;


public class frmEspecialidad extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmEspecialidad.class.getName());

    EspecialidadDao daoEspe =new EspecialidadDao();
    
    private models.entities.Usuario usuarioSesion;
    
    public frmEspecialidad() {
        initComponents();
        listado();
    }

    public frmEspecialidad(models.entities.Usuario usuarioSesion) {
        this();
        this.usuarioSesion = usuarioSesion;
    }
    
    void datos() { 
        
        int fila = tblEspecialidad.getSelectedRow();
         
        txtCodigo.setText(tblEspecialidad.getValueAt(fila, 0).toString());
        txtDescripcion .setText(tblEspecialidad.getValueAt(fila, 1).toString());
        
    }
        
        
    void  listado(){
        
        DefaultTableModel modelo= (DefaultTableModel) tblEspecialidad.getModel(); 
        modelo.setRowCount(0);
        List<Especialidad> datos = daoEspe.findAll();
        Iterator<Especialidad> itr=datos.iterator();

        while(itr.hasNext()) {

            Especialidad esp = itr.next();
            modelo.addRow (new Object[]{esp.getIdEspecialidad(), esp.getDescripcion()});

        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEspecialidad = new javax.swing.JTable();
        btnGrabar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblEspecialidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "CODIGO", "NOMBRE"
            }
        ));
        tblEspecialidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEspecialidadMouseClicked(evt);
            }
        });
        tblEspecialidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEspecialidadKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblEspecialidad);

        btnGrabar.setText("GUARDAR");
        btnGrabar.addActionListener(this::btnGrabarActionPerformed);

        btnModificar.setText("EDITAR");
        btnModificar.addActionListener(this::btnModificarActionPerformed);

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        jLabel2.setText("Código");

        jLabel1.setText("Descripción");

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ESPECIALIDAD");
        jLabel3.setToolTipText("");
        jLabel3.setOpaque(true);

        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        jButton1.setText("NUEVO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(45, 45, 45)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGrabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnModificar)
                            .addComponent(jButton1))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(btnGrabar)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblEspecialidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEspecialidadMouseClicked
        datos();
    }//GEN-LAST:event_tblEspecialidadMouseClicked

    private void tblEspecialidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEspecialidadKeyReleased
        datos();
    }//GEN-LAST:event_tblEspecialidadKeyReleased

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed

        Especialidad espe =new Especialidad();

        espe.setIdEspecialidad( Integer.parseInt(txtCodigo.getText()));
        espe.setDescripcion( txtDescripcion.getText());

       
        int resu= daoEspe.save(espe);

        if (resu>0){
            JOptionPane.showMessageDialog(this, "Especialidad registrado");
            listado();
        }
        else
        JOptionPane.showMessageDialog(this, "Error en el registro de Especialidad");
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        Especialidad espe = new Especialidad ();
        espe.setIdEspecialidad(Integer.parseInt(txtCodigo.getText()));
        espe.setDescripcion(txtDescripcion.getText());

        int resu = daoEspe.update(espe);

        if (resu>0){
            JOptionPane.showMessageDialog(this, "Especialidad modificada");
            listado();
        }
        else//resu -1
        JOptionPane.showMessageDialog(this, "Error en la modificacion de Especialidad");
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int boton;
        boton = JOptionPane.showConfirmDialog(this, "Seguro de eliminar Especialidad?", "Sistema", JOptionPane.YES_NO_OPTION);
        if (boton==0) {
            int resu = daoEspe.deleteByCode(Integer.parseInt(txtCodigo.getText()));
            if (resu>0) {

                JOptionPane.showMessageDialog(this, "Especialidad eliminada");
                listado ();
            }
            else//resu -1
            JOptionPane.showMessageDialog(this, "Error en la eliminación de Rol");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        javax.swing.JFrame menuDestion = (usuarioSesion != null) ? new frmMenuPrincipal(usuarioSesion) : new frmMenuPrincipal();
        menuDestion.setVisible(true);
        menuDestion.setLocationRelativeTo(null);
        this.dispose();
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
        java.awt.EventQueue.invokeLater(() -> new frmEspecialidad().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEspecialidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
