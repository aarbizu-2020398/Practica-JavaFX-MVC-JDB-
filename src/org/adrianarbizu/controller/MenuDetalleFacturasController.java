package org.adrianarbizu.controller;

import org.adrianarbizu.model.DetalleFactu;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.adrianarbizu.dao.Conexion;
import org.adrianarbizu.model.TicketSoporte;
import org.adrianarbizu.system.Main;

public class MenuDetalleFacturasController implements Initializable {
    Main stage;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TextField tfDetalleFactu;
    @FXML
    TextArea taDescripcion;
    @FXML
    ComboBox cmbEstatus, cmbDetalleFacturas, cmbFactura;
    
    
    @FXML
    TableView tblDetalleFacturas;
    @FXML
    TableColumn coldetalleFacturaId, colFacturaId, colProductoId; 
    @FXML
    Button btnAgregar,btnGuardar, btnEliminar, btnRegresar, btnBuscar, btnEditar;
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfDetalleFactu.getText().equals("")){
                agregarTicket();
                cargarDatos();
            }else{
                editarDetalleFacturas();
                cargarDatos();
            }
        }else if(event.getSource() == btnEliminar){
            vaciarForm();
            
        }else if(event.getSource() == btnAgregar){
            //stage.formDetalleFacturasView(1);
        }else if(event.getSource() == btnEditar){
           
        }else if(event.getSource() == btnBuscar){
           
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cargarCmbEstatus();
        cargarCmbFactura();
        cmbDetalleFacturas.setItems(listarDetalleFacturas());
    }

    public void cargarDatos() {
        tblDetalleFacturas.setItems(listarDetalleFacturas());
        coldetalleFacturaId.setCellValueFactory(new PropertyValueFactory<>("detalleFacturaId")); 
        colFacturaId.setCellValueFactory(new PropertyValueFactory<>("facturaId")); 
        colProductoId.setCellValueFactory(new PropertyValueFactory<>("productoId"));  
    }
    
    public void cargarCmbEstatus() {
        cmbEstatus.getItems().add("En proceso");
        cmbEstatus.getItems().add("Finalizado");
    }

    public void cargarCmbFactura() {
        cmbFactura.getItems().add("1");
    }

    public void vaciarForm() {
        tfDetalleFactu.clear();
        taDescripcion.clear();
        cmbEstatus.getSelectionModel().clearSelection();
        cmbDetalleFacturas.getSelectionModel().clearSelection();
        cmbFactura.getSelectionModel().clearSelection();
    }

    @FXML
    public void cargarForm() {
      
    }

    public int obtenerIndexDetalleFacturas() {
        
        return 0;
    }

    public ObservableList<DetalleFactu> listarDetalleFacturas() {
        ArrayList<DetalleFactu> detafacturas = new ArrayList<>();

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarDetalleFactura()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int detalleFacturaId = resultSet.getInt("detalleFacturaId");
                int facturaId = resultSet.getInt("facturaId");
                int productoId = resultSet.getInt("productoId");
                detafacturas.add(new DetalleFactu(detalleFacturaId, facturaId, productoId));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return FXCollections.observableList(detafacturas);
    }

    public void agregarTicket() {
        
    }

    public void editarDetalleFacturas() {
        
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
}
