package org.adrianarbizu.controller;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.adrianarbizu.dao.Conexion;
import org.adrianarbizu.dto.DistribuidorDTO;
import org.adrianarbizu.model.Distribuidores;
import org.adrianarbizu.system.Main;
import org.adrianarbizu.utils.SuperKinalAlert;

public class MenuDistribuidoresController implements Initializable {

    private Main stage;
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    private Button btnRegresar, btnAgregar, btnEditar, btnEliminar, btnBuscar;
    @FXML
    private TableView<Distribuidores> tblDistri;
    @FXML
    private TableColumn<Distribuidores, Integer> col_Id;
    @FXML
    private TableColumn<Distribuidores, String> col_Nom;
    @FXML
    private TableColumn<Distribuidores, String> col_Direccion;
    @FXML
    private TableColumn<Distribuidores, String> col_Nit;
    @FXML
    private TableColumn<Distribuidores, String> col_Telef;
    @FXML
    private TableColumn<Distribuidores, String> col_Web;
    @FXML
    private TextField tf_Buscar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }    
    
    public void cargarDatos() {
        if(op == 3){
            tblDistri.getItems().add(buscarDistribuidor());
            op = 0;
        } else {
            tblDistri.setItems(listarDistribuidor());
        }
        col_Id.setCellValueFactory(new PropertyValueFactory<>("distribuidorId"));
        col_Nom.setCellValueFactory(new PropertyValueFactory<>("nombreDistribuidor"));
        col_Direccion.setCellValueFactory(new PropertyValueFactory<>("direccionDistribuidor"));
        col_Nit.setCellValueFactory(new PropertyValueFactory<>("nitDistribuidor"));
        col_Telef.setCellValueFactory(new PropertyValueFactory<>("telefonoDistribuidor"));
        col_Web.setCellValueFactory(new PropertyValueFactory<>("web"));
    }
    
    public ObservableList<Distribuidores> listarDistribuidor() {
        ArrayList<Distribuidores> distribuidor = new ArrayList<>();
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarDistribuidores();";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("nitDistribuidor");
                String telefonoDistribuidor = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
                distribuidor.add(new Distribuidores(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(conexion != null) conexion.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(distribuidor);
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public Distribuidores buscarDistribuidor() {
        Distribuidores distribuidor = null;
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarDistribuidores(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tf_Buscar.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombreDistribuidor = resultSet.getString("nombreDistribuidor");
                String direccionDistribuidor = resultSet.getString("direccionDistribuidor");
                String nitDistribuidor = resultSet.getString("nitDistribuidor");
                String telefonoDistribuidor = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
                distribuidor = new Distribuidores(distribuidorId, nombreDistribuidor, direccionDistribuidor, nitDistribuidor, telefonoDistribuidor, web);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(conexion != null) conexion.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return distribuidor;
    }
    
    public void eliminarDistribuidores(int distriId) {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarDistribuidores(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, distriId);
            statement.execute();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(statement != null) statement.close();
                if(conexion != null) conexion.close();
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            stage.menuPrincipalView();
        } else if (event.getSource() == btnAgregar) {
            stage.formDistribuidoresView(1);
        } else if (event.getSource() == btnEditar) {
            DistribuidorDTO.getDistribuidorDTO().setDistribuidor((Distribuidores) tblDistri.getSelectionModel().getSelectedItem());
            stage.formDistribuidoresView(2);
        } else if (event.getSource() == btnEliminar) {
            if (SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(700).get() == ButtonType.OK) {
                eliminarDistribuidores(((Distribuidores) tblDistri.getSelectionModel().getSelectedItem()).getDistribuidorId());
                cargarDatos();
            }
        } else if (event.getSource() == btnBuscar) {
            tblDistri.getItems().clear();
            if (tf_Buscar.getText().equals("")) {
                cargarDatos();
            } else {
                op = 3;
                cargarDatos();
            }
        }
    }
}
