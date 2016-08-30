/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Constructores.Constructor_Usuario;
import BL.Funciones_Generales;
import BL.Funciones_frm_CortesCaja;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Nestor1
 */


public class frm_FacturaAnuladas extends javax.swing.JInternalFrame {
 public String []columnas_inventario = new String[30];
    public String []columnas = new String[6];
    public int[]ancho_columnas = new int[15],columnas_eliminar = new int[3], columnas_eliminar_inventario =new int[17];
    java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
    private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    Constructor_Usuario usuario_activo= new Constructor_Usuario();
        private String user= null;
    /**
     * Creates new form frm_HistorialInforme
     * @param usuario
     */
    public frm_FacturaAnuladas(Object usuario) {
        user=usuario.toString();
        initComponents();
        cargarTable();
        this.setTitle("FACTURAS ANULADAS");
        inicializarForma();
    }
    private void inicializarForma()
    {
        TitledBorder borde1 = new TitledBorder(new EtchedBorder(),"Facturas Anuladas");
        pan_principal.setBorder(borde1);
    }
    
     private void parametrosTabla()
    {
        columnas[0] = "IDENTIFICACION";
        columnas[1] = "NUMERO FACTURA";
        columnas[2] = "FECHA";
        columnas[3] = "HORA";
        columnas[4] = "MOTIVO ";
        columnas[5] = "USUARIO";
        ancho_columnas[0]=200;
        ancho_columnas[1]=0;
        ancho_columnas[2]=0;
        ancho_columnas[3]=0;
        ancho_columnas[4]=0;
        ancho_columnas[5]=0;
        ancho_columnas[6]=100;
        ancho_columnas[7]=0;
        ancho_columnas[8]=10;
        ancho_columnas[9]=0;
        ancho_columnas[10]=50;
        ancho_columnas[11]=0;
        ancho_columnas[12]=50;
//        columnas_eliminar[0] = 6;
//        columnas_eliminar[1] = 6;
//        columnas_eliminar[2] = 6;
    
    }
    private void consultarDatosUsuario()
    {
        BL.Funciones_Generales funciones_producto= new Funciones_Generales();
        usuario_activo=funciones_producto.usuario(user);
        
    }
    private void cargarTable() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
        BL.Funciones_frm_Factura_Anulada n = new BL.Funciones_frm_Factura_Anulada();
        
        try{
            parametrosTabla();
            consultarDatosUsuario();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        tbl_Facturas_Anuladas.setModel(n.llenarFacturasAnuladas(usuario_activo.getId_sucursal(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
//         for (int i = 0; i < columnas_eliminar.length; i++) {
//
//            tbl_Facturas_Anuladas.getColumnModel().removeColumn(tbl_Facturas_Anuladas.getColumnModel().getColumn(columnas_eliminar[i]));
//        }
    }
         
    private void cargarTableBuscar() 
    {
        //Metodo para llenar la tabla producto con los parametros, nombre de columnas y columnas que quiero eliminar
       
        
        try{
            parametrosTabla();
            consultarDatosUsuario();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }  
        try{
        BL.Funciones_frm_Factura_Anulada n = new BL.Funciones_frm_Factura_Anulada();
        tbl_Facturas_Anuladas.setModel(n.buscarFacturaAnulada(usuario_activo.getId_sucursal(),txt_buscar_historial.getText(),cmb_buscar_por_historial.getSelectedItem().toString(),columnas, ancho_columnas));
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
//         for (int i = 0; i < columnas_eliminar.length; i++) {
//
//            tbl_Facturas_Anuladas.getColumnModel().removeColumn(tbl_Facturas_Anuladas.getColumnModel().getColumn(columnas_eliminar[i]));
//        }
         
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan_principal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar_historial = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Facturas_Anuladas = new javax.swing.JTable();
        cmb_buscar_por_historial = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        pan_principal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial Procesos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Buscar:");

        txt_buscar_historial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_historialKeyPressed(evt);
            }
        });

        tbl_Facturas_Anuladas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_Facturas_Anuladas);

        cmb_buscar_por_historial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Identificacion", "Numero Factura", "Fecha", "Hora", "Motivo", "Usuario" }));
        cmb_buscar_por_historial.setToolTipText("");

        jLabel2.setText("Por:");

        jButton1.setText("PDF");

        jButton2.setText("EXCEL");

        javax.swing.GroupLayout pan_principalLayout = new javax.swing.GroupLayout(pan_principal);
        pan_principal.setLayout(pan_principalLayout);
        pan_principalLayout.setHorizontalGroup(
            pan_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_principalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                    .addGroup(pan_principalLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_buscar_por_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(2, 2, 2)
                        .addComponent(jButton2)
                        .addContainerGap())))
        );
        pan_principalLayout.setVerticalGroup(
            pan_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_principalLayout.createSequentialGroup()
                .addGroup(pan_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_principalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pan_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_buscar_historial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_buscar_por_historial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(pan_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan_principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscar_historialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_historialKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
        cargarTableBuscar();
    }//GEN-LAST:event_txt_buscar_historialKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmb_buscar_por_historial;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pan_principal;
    private javax.swing.JTable tbl_Facturas_Anuladas;
    private javax.swing.JTextField txt_buscar_historial;
    // End of variables declaration//GEN-END:variables
}
