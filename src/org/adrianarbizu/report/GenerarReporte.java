/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adrianarbizu.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.adrianarbizu.dao.Conexion;
import win.zqxu.jrviewer.JRViewerFX;

/**
 *
 * @author informatica
 */
public class GenerarReporte {
    private static GenerarReporte instance;
    
    private static Connection conexion = null;
    
    private GenerarReporte(){  
    }
    
    public static GenerarReporte getInstance(){
        if(instance == null){
            instance = new GenerarReporte();
        }
        return instance; 
    } 
    public void generarFactura(int factId) throws SQLException, JRException{

        try{
          
        
        conexion = Conexion.getInstance().obtenerConexion();
        
        
        Map<String, Object>parametros = new HashMap<>();
        parametros.put("factId",factId);
        
        Stage reportStage = new Stage();
         
         

                
        JasperPrint reporte = JasperFillManager.fillReport(
            GenerarReporte.class.getResourceAsStream("/org/adrianarbizu/report/Factura.jasper"),
            parametros, conexion);
       
        
        
        JRViewerFX reportViewer = new JRViewerFX(reporte);
        
        Pane root = new Pane();
        
        root.getChildren().add(reportViewer);
        
        reportViewer.setPrefSize(1000, 800);
        
        Scene scene = new Scene(root);
        reportStage.setScene(scene);
        reportStage.setTitle("Factura");
        reportStage.show();
             
        }catch(Exception e){
        e.printStackTrace();
            
        }
    }
}
        
        
       
    
       
