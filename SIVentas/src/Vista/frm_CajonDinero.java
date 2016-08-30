/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import BL.Funciones_Generales;
import BL.Funciones_frm_CajonDinero;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;

/**
 *
 * @author Nestor1
 */
public class frm_CajonDinero extends javax.swing.JInternalFrame {
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;

    /**
     * Creates new form frm_Categorias
     */
    public frm_CajonDinero(Object usuario) {
        user=usuario.toString();
        initComponents();
        inicializarForm();
        this.setTitle("CAJON DINERO");
    }
    private void limpiarForm()
    {
        cmb_motivo_importe.setSelectedIndex(0);
        cmb_tipo_importe.setSelectedIndex(0);
        txt_cantidad_importe.setText("");
        txtAr_descripcion_importe.setText("");
   }
    private void consultarDatosUsuario()
    {
        BL.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
    }
    private void inicializarForm()
    {
        
        Object[] itemsTipoImporte = null;
        TitledBorder borde2 = new TitledBorder(new EtchedBorder(),"IMPORTES");
        pan_central.setBorder(borde2);
        BL.Funciones_frm_CajonDinero fun = new Funciones_frm_CajonDinero();
        itemsTipoImporte=fun.llenarComboTpoimporte();
          for (int h = 0; h < itemsTipoImporte.length; h++) {
            cmb_tipo_importe.addItem(itemsTipoImporte[h]);
        }
          llenarComboMotivoImporte();
        
    }
    
    private void llenarComboMotivoImporte()
    {
        cmb_motivo_importe.removeAllItems();
        Object[] MotivoImporte=null;
        BL.Funciones_frm_CajonDinero fun = new Funciones_frm_CajonDinero();
        Object tipo_importe=null;
        tipo_importe= cmb_tipo_importe.getSelectedItem();
        MotivoImporte=fun.llenarComboMotivoImporte(tipo_importe);
          for (int t = 0; t < MotivoImporte.length; t++) {
            cmb_motivo_importe.addItem(MotivoImporte[t]);
        }
    }
    
    private void guardarImporte()
    {
        consultarDatosUsuario();
        Object tipoImporte=null,motivoImporte=null,importe=null,descrip=null;
        tipoImporte= cmb_tipo_importe.getSelectedItem();
        motivoImporte = cmb_motivo_importe.getSelectedItem();
        importe= txt_cantidad_importe.getText();
        descrip = txtAr_descripcion_importe.getText();
        Funciones_frm_CajonDinero fun = new Funciones_frm_CajonDinero();
        if(fun.registarImporte(usuario_activo.getId_sucursal(),tipoImporte, motivoImporte, importe, descrip,usuario_activo.getId_usuario()))
            JOptionPane.showMessageDialog(null, "Se Genero Importe Exitosamente");
        limpiarForm();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan_central = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_cantidad_importe = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cmb_tipo_importe = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAr_descripcion_importe = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cmb_motivo_importe = new javax.swing.JComboBox();

        jLabel1.setText("Importe:");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmb_tipo_importe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));
        cmb_tipo_importe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_tipo_importeItemStateChanged(evt);
            }
        });
        cmb_tipo_importe.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                cmb_tipo_importeCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jLabel2.setText("Tipo Importe:");

        jLabel3.setText("Detalle Importe:");

        txtAr_descripcion_importe.setColumns(20);
        txtAr_descripcion_importe.setRows(5);
        jScrollPane2.setViewportView(txtAr_descripcion_importe);

        jLabel4.setText("Motivo Importe:");

        cmb_motivo_importe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "..." }));

        javax.swing.GroupLayout pan_centralLayout = new javax.swing.GroupLayout(pan_central);
        pan_central.setLayout(pan_centralLayout);
        pan_centralLayout.setHorizontalGroup(
            pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_centralLayout.createSequentialGroup()
                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pan_centralLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_centralLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_tipo_importe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pan_centralLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_motivo_importe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(pan_centralLayout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pan_centralLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_centralLayout.createSequentialGroup()
                                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(txt_cantidad_importe))))))
                .addGap(28, 28, 28))
        );
        pan_centralLayout.setVerticalGroup(
            pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_centralLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_tipo_importe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_motivo_importe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_cantidad_importe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pan_centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(pan_central, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_central, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        guardarImporte();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmb_tipo_importeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cmb_tipo_importeCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_tipo_importeCaretPositionChanged

    private void cmb_tipo_importeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_tipo_importeItemStateChanged
        // TODO add your handling code here:
        llenarComboMotivoImporte();
    }//GEN-LAST:event_cmb_tipo_importeItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_motivo_importe;
    private javax.swing.JComboBox cmb_tipo_importe;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pan_central;
    private javax.swing.JTextArea txtAr_descripcion_importe;
    private javax.swing.JTextField txt_cantidad_importe;
    // End of variables declaration//GEN-END:variables
}
