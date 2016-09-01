/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DA;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Nestor1
 */
public class consultas_factura extends Conexion{
    
    private ResultSet rh = null;
    DecimalFormat formateador2 = new DecimalFormat("######");
    Connection conex = null;
    PreparedStatement ps;
    Conexion con;
    String sql = null;
    
    public consultas_factura()
    {
        con = new Conexion();
        conex = con.crearConexionNueva();
    }
    
    private ResultSet consultaResusltados(String sql) {
        try {
            conex = con.crearConexionNueva();
            ps = conex.prepareStatement(sql);
            rh = ps.executeQuery();
           
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rh;
    }
    private boolean insertarResultados(String sql) {
        boolean ban = true;
        try {
            
            conex = con.crearConexionNueva();
            ps = conex.prepareStatement(sql);
            ban = ps.execute();
           
        } catch (SQLException ex) {
            Logger.getLogger(Consultas_informeMasVendido.class.getName()).log(Level.SEVERE, null, ex);
        }
         return true;
    }
    
    public boolean consultaGenerarHistorialCredito(int id_credito,Object[] fechas_pagos)
    {
        
        for (int i = 0; i < fechas_pagos. length; i++) {
             try {
                 CallableStatement cst = conex.prepareCall("Call CON_consultaGenerarHistorialCredito(?,?,?)");
                 cst.setInt("id_creditoLog", id_credito);
                 cst.setInt("i", +1);
                 cst.setObject("fechaPagos", fechas_pagos[i]);
                 cst.execute();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
       return true;
    }
    
    public boolean consultaDescontarCantidadProducto(Object id_producto_inventario,float cantidad)
    {
        
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaDescontarCantidadProducto(?,?)");
            cst.setFloat("cantidad", cantidad);
            cst.setInt("id_producto_inventarioLog",Integer.parseInt( id_producto_inventario.toString()) );
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public float consultaExistencia(Object id_producto_inventario)
    {
        float cantidad =0;
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_consultaExistencia(?)");
            cst.setInt("idProductoinventario", Integer.parseInt(id_producto_inventario.toString()));
            cst. execute();
            rh = cst.getResultSet();
            while(rh.next())
            cantidad = rh.getFloat("cantidad_producto_inventario");
        } catch (SQLException ex) {
            Logger.getLogger(consultas_factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }
    public float consultaStock(Object id_producto_inventario)
    {
        float stock =0;
        try{
            CallableStatement cst = conex.prepareCall("Call IVN_consultaStock(?)");
            cst.setInt("id_producto_inventarioLog", Integer.parseInt(id_producto_inventario.toString()));
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            stock = rh.getFloat("stock_producto_inventario");
        } catch (SQLException ex) {
            Logger.getLogger(consultas_factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }
    
    public ResultSet consultaDatosEmpresaParaFactura(int id_sucursal)
    {
       
       try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaDatosEmpresaParaFactura(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public int cosultaIdCotizacionUltima(int id_sucursal)
    {
        int id_cotizacion=0;
         try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdCotizacionUltima(?)");
            cst.setInt("id_producto_inventarioLog", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_cotizacion = rh.getInt("id_cotizaciones");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_cotizacion;
    }
     
     public int cosultaIdCreditoUltima(int id_sucursal)
    {
        int id_cotizacion=0;
       try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdCreditoUltima(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_cotizacion = rh.getInt("id_credito");
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_cotizacion;
    }
    
    
    public int cosultaIdFacturaUltima(int id_sucursal)
    {
        int id_factura=0;
        try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaIdFacturaUltima(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("id_factura");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
    
    public int cosultaNumeroFactura(int id_sucursal)
    {
        int id_factura=0;
         try{
            CallableStatement cst = conex.prepareCall("Call CON_cosultaNumeroFactura(?)");
            cst.setInt("idSucursal", id_sucursal);
            cst.execute();
            rh = cst.getResultSet();
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("numero_factura");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
    public boolean consultaRegistrarCorteCajaFactura(Object id_corte_caja,Object id_factura, int id_sucursal)
    {
        
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultaRegistrarCorteCajaFactura(?,?,?) ");
            cst.setInt("idCorteCaja", Integer.parseInt(id_corte_caja.toString()));
            cst.setInt("idFactura", Integer.parseInt(id_factura.toString()));
            cst.setInt("idSucursal", id_sucursal);
            return cst.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ResultSet consultallenarComboPagarCon()
    {
        try {
            CallableStatement cst = conex.prepareCall("Call CON_consultallenarComboPagarCon()");
            cst.execute();
            return cst.getResultSet();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean consultaRegistrarUtilidad(int id_factura,float utilidad)
    {
        try {
            CallableStatement cst = conex.prepareCall("Call GEN_consultaRegistrarUtilidad(?,?)");
            cst.setInt("idFactura", id_factura);
            cst.setFloat("utilidadLog", utilidad);
            return cst.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean registrarFacturaDetalle(Object id_producto_inventario,Object item, Object cantidad_factura_detallado,
            Object descuento_factura_detalle, Object valor_unidad_producto, Object subtotal_factura_detalle, Object id_factura,boolean ban)
    {
        
        if(ban==false)
        {
            sql=("INSERT INTO factura_detalle (id_producto_inventario,item,cantidad_factura_detallado,descuento_factura_detalle,"
                + "valor_unidad_producto,subtotal_factura_detalle,id_factura,id_credito) VALUES ("+id_producto_inventario+","+item+","
                + ""+cantidad_factura_detallado+","+descuento_factura_detalle+","+valor_unidad_producto+","
        + ""+subtotal_factura_detalle+","+id_factura+",0)");
            return insertarResultados(sql);
        }
        else
        {
            sql=("INSERT INTO factura_detalle (id_producto_inventario,item,cantidad_factura_detallado,descuento_factura_detalle,"
                + "valor_unidad_producto,subtotal_factura_detalle,id_factura,id_credito) VALUES ("+id_producto_inventario+","+item+","
                + ""+cantidad_factura_detallado+","+descuento_factura_detalle+","+valor_unidad_producto+","
        + ""+subtotal_factura_detalle+",0,"+id_factura+")");
            return insertarResultados(sql);
        }
        
    }
    
     public boolean registrarCotizacionDetalle(Object id_producto_inventario,Object item, Object cantidad_factura_detallado,
            Object descuento_factura_detalle, Object valor_unidad_producto, Object subtotal_factura_detalle, Object id_cotizacion)
    {
        
        
        
        sql=("INSERT INTO cotizaciones_detalle (id_producto_inventario,item,cantidad_factura_detallado,descuento_factura_detalle,"
                + "valor_unidad_producto,subtotal_factura_detalle,id_cotizaciones) VALUES ("+id_producto_inventario+","+item+","
                + ""+cantidad_factura_detallado+","+descuento_factura_detalle+","+valor_unidad_producto+","
        + ""+subtotal_factura_detalle+","+id_cotizacion+")");
        return insertarResultados(sql);
    }
    
    
    public boolean registrarFactura(Object hora,int id_sucursal,Object folio,Object subtotal, Object descuento, Object iva, Object total, Object id_cliente, Object id_forma_pago
    ,Object recibe,Object cambio, Object usuario, Object fecha,Object numero_fact,Object tarjeta)
    {
        if(folio =="")
        {
            folio=null;
        }
    
        if(tarjeta !="")
        {
            tarjeta=0;
        }
          
        sql=("INSERT INTO factura (folio,numero_factura,subtotal,descuento,iva,total,id_cliente,id_sucursal,id_forma_pago,digitos_tarjeta,recibe,cambio,descripcion,id_usuario_creacion"
                + ",fecha_creacion,hora_creacion) VALUES ('"+folio+"',"+numero_fact+",'"+subtotal+"','"+descuento+"','"+iva+"','"+total+"',"
        + ""+id_cliente+","+id_sucursal+","+id_forma_pago+",'"+tarjeta+"','"+recibe+"','"+cambio+"','',"+usuario+",'"+fecha+"','"+hora+"')");
        insertarResultados(sql);
        return true;
    }
    
     public boolean registrarCredito(Object hora,int id_sucursal,Object valor_pagar,Object subtotal, Object descuento, Object iva, Object total, Object id_cliente
    ,Object cuotas, Object id_usuario, Object fecha,Object porcentaje_interes,Object id_estado,Object cliente)
    {
       
                
        sql=("INSERT INTO credito (valor_pagar_mensual,cantidad_credito,subtotal,iva,descuento,"
                + "porcentaje_interes,cuotas,cuotas_pagas,cuotas_pendientes,id_estado,id_usuario,id_sucursal,fecha,hora,id_cliente)"
                + " VALUES ('"+valor_pagar+"',"+total+",'"+subtotal+"','"+iva+"','"+descuento+"','"+porcentaje_interes+"',"
        + ""+cuotas+",0,"+cuotas+",'"+id_estado+"',"+id_usuario+","+id_sucursal+",'"+fecha+"','"+hora+"',"+cliente+")");
        return insertarResultados(sql);
    }
    
    public boolean registrarCotizacion(Object hora,int id_sucursal,Object subtotal, Object descuento, Object iva, Object total, Object id_cliente
    , Object descripcion, Object usuario, Object fecha,Object fec_validez)
    {
           
        sql=("INSERT INTO cotizaciones (cot_subtotal,cot_descuento,cot_iva,cot_total,id_cliente,id_sucursal,"
                + "cot_descripcion,id_usuario"
                + ",fecha_creacion,hora_creacion,fecha_validez) VALUES ('"+subtotal+"','"+descuento+"','"+iva+"','"+total+"',"
        + ""+id_cliente+","+id_sucursal+",'"+descripcion+"',"+usuario+",'"+fecha+"','"+hora+"','"+fec_validez+"')");
        return insertarResultados(sql);
    }
    
    public int cosultaIdCliente(Object cliente)
    {
        int id_cliente=0;
        sql=("SELECT id_cliente FROM cliente WHERE cedula_cliente='"+cliente+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_cliente = rh.getInt("id_cliente");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_cliente;
    }
    
     public int cosultaIdEstadoCredito(Object estado)
    {
        int id_estado=0;
        sql=("SELECT id_estado FROM estado_Credito WHERE estado='"+estado+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_estado = rh.getInt("id_estado");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_estado;
    }
    
    public int cosultaIdFormaPago(Object formaPago)
    {
        int id_forma_pago=0;
        sql=("SELECT id_forma_pago FROM forma_pago WHERE forma_pago='"+formaPago+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_forma_pago = rh.getInt("id_forma_pago");
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return id_forma_pago;
    }
    
     public int cosultaIdFactura()
    {
        int id_factura=0;
        sql=("SELECT id_factura FROM factura");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("id_factura");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
     
     public int cosultaIdFacturaAnulada()
    {
        int id_factura=0;
        sql=("SELECT id_factura_anulada FROM factura_anulada");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            {
                if(rh.last())
                id_factura = rh.getInt("id_factura_anulada");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_factura;
    }
     
     
     public Boolean consultaLlenaarIdAnulacionFactura(Object id_factura_anulada, Object id_factura)
     {
         sql=("UPDATE factura SET id_factura_anulada="+id_factura_anulada+" WHERE id_factura="+id_factura+"");
         return insertarResultados(sql);
     }
     
     public ResultSet consultaLlenarTablaFactura(int id_sucursal, Object fecha,Object fecha_Hasta,boolean ban)
     {
         
         if(ban)
         {
        sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FACT.total,FACT.subtotal,FACT.descuento,"
                 + "FACT.iva,"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FORMA.id_forma_pago,"
                 + "FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI "
                 + "where FACT.id_factura_anulada=0 AND FACT.id_sucursal="+id_sucursal+" AND "
                 + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"' ORDER BY FACT.fecha_creacion DESC");
         return consultaResusltados(sql);
         }
         else
         {
             sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FACT.total,FACT.subtotal,FACT.descuento,"
                 + "FACT.iva,"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,"
                     + "SUC.nombre_sucursal,FORMA.id_forma_pago,"
                 + "FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC "
                 + "where FACT.id_factura_anulada=0 AND FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_sucursal+"  AND "
                 + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"' ORDER BY FACT.fecha_creacion DESC");
         return consultaResusltados(sql); 
         }
     }
     public ResultSet consultaLlenarTablaFacturaXEmpresa(int id_empresa, Object fecha,Object fecha_Hasta)
     {
         //ESTA FUNCION LLENA LA TABLA DE VENTAS DIARIAS
         sql=("SELECT DISTINCT SUC.id_sucursal,FACT.id_sucursal,FACT.fecha_creacion,"
                 + "FACT.total,FACT.iva,FACT.descuento FROM factura AS FACT,sucursal AS SUC\n" +
"WHERE SUC.id_empresa= "+id_empresa+" AND FACT.id_factura_anulada=0 AND SUC.id_sucursal = FACT.id_sucursal AND Date(FACT.fecha_creacion)\n" +
"BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
         return consultaResusltados(sql);
     }
     public ResultSet consultaLlenarVentaXMesXEmpresa(int id_empresa, Object fecha,Object fecha_Hasta)
     {
         //ESTA FUNCION LLENA LA TABLA DE VENTAS DIARIAS POR EMPRESA
         sql=("SELECT DISTINCT SUC.id_sucursal,FACT.id_sucursal,FACT.id_factura,FACT.fecha_creacion,"
                 + "CASE WHEN MONTH(FACT.fecha_creacion)=1 THEN 'ENERO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=2 THEN 'FEBRERO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=3 THEN 'MARZO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=4 THEN 'ABRIL' "
                 + "WHEN MONTH(FACT.fecha_creacion)=5 THEN 'MAYO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=6 THEN 'JUNIO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=7 THEN 'JULIO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=8 THEN 'AGOSTO' "
                 + "WHEN MONTH(FACT.fecha_creacion)=9 THEN 'SEPTIEMBRE' "
                 + "WHEN MONTH(FACT.fecha_creacion)=10 THEN 'OCTUBRE'"
                 + "WHEN MONTH(FACT.fecha_creacion)=11 THEN 'NOVIEMBRE' "
                 + "WHEN MONTH(FACT.fecha_creacion)=12 THEN 'DICIEMBRE' ELSE 'ESTO NO ES UN MES' END AS MESespañol,"
                 + "FACT.total,FACT.iva,FACT.descuento FROM factura AS FACT,sucursal AS SUC \n" +
"WHERE SUC.id_empresa= "+id_empresa+" AND FACT.id_factura_anulada=0 AND SUC.id_sucursal = FACT.id_sucursal AND Date(FACT.fecha_creacion)\n" +
"BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"' ORDER BY FACT.fecha_creacion DESC");
         return consultaResusltados(sql);
     }
     
      public ResultSet consultaLlenarVentaXMesXSucursal(int id_sucursal, Object fecha,Object fecha_Hasta)
     {
         //ESTA FUNCION LLENA LA TABLA DE VENTAS DIARIAS
         sql=("SELECT DISTINCT FACT.id_sucursal,FACT.id_factura,FACT.fecha_creacion,"
                 + "CASE WHEN MONTH(FACT.fecha_creacion)=1 THEN 'ENERO'"
                 + " WHEN MONTH(FACT.fecha_creacion)=2 THEN 'FEBRERO'"
                 + "WHEN MONTH(FACT.fecha_creacion)=3 THEN 'MARZO'"
                 + "WHEN MONTH(FACT.fecha_creacion)=4 THEN 'ABRIL'"
                 + "WHEN MONTH(FACT.fecha_creacion)=5 THEN 'MAYO'"
                 + "WHEN MONTH(FACT.fecha_creacion)=6 THEN 'JUNIO'"
                 + "WHEN MONTH(FACT.fecha_creacion)=7 THEN 'JULIO'"
                 + "WHEN MONTH(FACT.fecha_creacion)=8 THEN 'AGOSTO'"
                 + "WHEN MONTH(FACT.fecha_creacion)=9 THEN 'SEPTIEMBRE'"
                 + "WHEN MONTH(FACT.fecha_creacion)=10 THEN 'OCTUBRE'"
                 + "WHEN MONTH(FACT.fecha_creacion)=11 THEN 'NOVIEMBRE'"
                 + "WHEN MONTH(FACT.fecha_creacion)=12 THEN 'DICIEMBRE' ELSE 'ESTO NO ES UN MES' END AS MESespañol,"
                 + "FACT.total,FACT.iva,FACT.descuento FROM factura AS FACT\n" +
"WHERE FACT.id_sucursal="+id_sucursal+" AND Date(FACT.fecha_creacion)\n" +
"BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
         return consultaResusltados(sql);
     }
     
    public String consultaPrimerFecha()
    {
        String fecha=null;
        sql=("SELECT fecha_creacion FROM factura");
        rh = consultaResusltados(sql);
        try {
            
                if(rh.first())
                fecha = rh.getString("fecha_creacion");
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fecha;
    }
    
    public ResultSet consultaLlenarArticulos(Object id_factura)
    {
        sql=("SELECT DISTINCT FACTDEC.item,PRO.nombre_producto,PRE.presentacion,MED.medicion,MARC.marca,"
                + "FORMAT(FACTDEC.valor_unidad_producto,2),FACTDEC.cantidad_factura_detallado,FACTDEC.descuento_factura_detalle,FORMAT("
                + "FACTDEC.subtotal_factura_detalle,2)\n" +
",PRODIVEN.id_producto_inventario,PRODIVEN.id_producto,PRO.id_produccto,FACT.id_factura,PRO.id_medicion,PRO.id_presentacion,PRO.id_marca,PRE.id_presentacion,"
                + "MARC.id_marca,MED.id_medicion,FACTDEC.id_factura, FACTDEC.id_producto_inventario,FACTDEC.id_factura FROM presentacion AS PRE, "
                + "marca AS MARC, medicion AS MED, producto AS PRO, producto_inventario AS PRODIVEN ,factura AS FACT,factura_detalle AS FACTDEC "
                + "where FACT.id_factura= "+id_factura+" AND FACTDEC.id_factura="+id_factura+" AND PRODIVEN.id_producto_inventario = FACTDEC.id_producto_inventario AND PRO.id_medicion=MED.id_medicion AND PRO.id_marca=MARC.id_marca "
                + "AND PRO.id_presentacion = PRE.id_presentacion AND PRODIVEN.id_producto = PRO.id_produccto ORDER BY FACTDEC.item");
        return consultaResusltados(sql);
    }
    
    public float consultaTotalFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float total = 0;
        sql=("SELECT FACT.total FROM factura AS FACT,sucursal AS SUC WHERE SUC.id_sucursal=FACT.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                + "id_factura_anulada=0 AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta +"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            {
                total=  total +(rh.getFloat("FACT.total"));  
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }
    public float consultaTotalIvaFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float iva=0;
        sql=("SELECT FACT.iva FROM factura AS FACT, sucursal AS SUC WHERE FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                + "id_factura_anulada=0 AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta +"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            {
                iva = iva+ Float.parseFloat(rh.getObject("FACT.iva").toString());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return iva;
    }
    public float consultaTotalIDescuentoFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float descuento=0;
        sql=("SELECT FACT.descuento FROM factura AS FACT,sucursal AS SUC WHERE FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                + "id_factura_anulada=0  AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta +"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            {
                descuento = descuento+ Float.parseFloat(rh.getObject("FACT.descuento").toString());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return descuento;
    }
    
    public float consultaTotalIGananciaFacturacion(int id_empresa,Object fecha,Object fecha_Hasta)
    {
        float ganancia=0;
        sql=("SELECT FACT.ganancia_factura FROM factura AS FACT,sucursal AS SUC WHERE FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                + "id_factura_anulada=0  AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta +"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            {
                ganancia = ganancia+ (rh.getFloat("FACT.ganancia_factura"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ganancia;
    }
    
    public boolean consultaRegistrarFacturaAnulada(Object id_facctura,Object id_motivo, Object id_usuario,Object fecha, Object hora)
    {
        sql=("INSERT INTO factura_anulada (id_factura,id_motivo_factura_anulada,id_usuario_creacion,fecha_anulacion,hora_anulacion) "
                + "VALUES ("+id_facctura+","+id_motivo+","+id_usuario+",'"+fecha+"','"+hora+"')");
        return insertarResultados(sql);
    }
   public boolean consultaAnularFactura(Object id_facctura)
   {
       sql=("DELETE FROM factura WHERE id_factura="+id_facctura+"");
       return insertarResultados(sql);
   }
   public ResultSet consultaMotivoAnulacion()
   {
       sql=("SELECT motivo_anulacion FROM factura_motivo_anulacion");
       return consultaResusltados(sql);
   }
   
    public int cosultaIdMotivo(Object motivo)
    {
        int id_motivo=0;
        sql=("SELECT id_motivo_factura_anulada FROM factura_motivo_anulacion WHERE motivo_anulacion='"+motivo+"'");
        rh = consultaResusltados(sql);
        try {
            while(rh.next())
            id_motivo = rh.getInt("id_motivo_factura_anulada");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id_motivo;
    }
    
    public ResultSet consultaBuscarPorFacaturar( Object fecha,Object fecha_Hasta, String opcion,Object objetoBuscar, int id_empresa)
     {
         //Factura, Folio, Forma Pago, Nombre Cliente, Apellido Cliente, Cedula Cliente, Fecha
         switch (opcion)
         {
             case "Factura":
                 sql =("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,sucursal AS SUC,cliente AS CLI where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.id_factura LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                 rh = consultaResusltados(sql);
                 break;
                case "Forma Pago":
                      
                sql =("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.forma_pago LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                rh = consultaResusltados(sql);
             break;
                case "Nombre Cliente":
                      sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + " FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND CLI.nombre_cliente LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                      rh = consultaResusltados(sql);
             break;
                case "Apellido Cliente":
                    
                sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND CLI.apellido_cliente LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                rh = consultaResusltados(sql);
             break;
                case "Cedula Cliente":
                    
                    sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND CLI.cedula_cliente LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                    rh = consultaResusltados(sql);
             break;
                case "Fecha":
                    sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI, sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND FACT.fecha_creacion LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                    rh = consultaResusltados(sql);
             break;
                 
                    case "Sucursal":
                    sql=("SELECT DISTINCT FACT.id_factura,FACT.folio,FORMAT(FACT.total,2),FORMAT(FACT.subtotal,2),FORMAT(FACT.descuento,2),"
                 + "FORMAT(FACT.iva,2),"
                 + "FORMAT(FACT.recibe,2),FORMAT(FACT.cambio,2),FORMA.forma_pago,FACT.digitos_tarjeta,FACT.descripcion,CLI.nombre_cliente,"
                 + "CLI.apellido_cliente,CLI.cedula_cliente,CLI.direccion_cliente,FACT.fecha_creacion,FACT.hora_creacion,FACT.ganancia_factura,SUC.nombre_sucursal,FORMA.id_forma_pago,FACT.id_cliente,FACT.id_forma_pago,CLI.id_cliente FROM "
                 + "factura AS FACT,forma_pago AS FORMA,cliente AS CLI,sucursal AS SUC where FACT.id_sucursal=SUC.id_sucursal AND SUC.id_empresa="+id_empresa+" AND "
                        + "FACT.id_forma_pago=FORMA.id_forma_pago AND FACT.id_cliente = CLI.id_cliente"
                 + " AND SUC.nombre_sucursal LIKE '"+objetoBuscar+"%' AND DATE(FACT.fecha_creacion) BETWEEN '"+fecha+"' AND '"+fecha_Hasta+"'");
                    rh = consultaResusltados(sql);
             break;
                                     
         }
         
         return rh;
     }
   
    
}