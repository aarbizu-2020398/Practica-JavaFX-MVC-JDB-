/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adrianarbizu.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.adrianarbizu.dao.Conexion;
import org.adrianarbizu.dto.ClienteDTO;
import org.adrianarbizu.model.Cliente;
import org.adrianarbizu.system.Main;
import org.adrianarbizu.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormClienteController implements Initializable {
       private Main stage;
       private int op;
       
       private static Connection conexion = null;
       private static PreparedStatement statement = null;
       
    @FXML
    TextField tfClienteId, tfNombre, tfApellido, tfTelefono, tfDireccion, tfNIT;
    @FXML
    Button btnGuardar, btnCancelar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuClienteView();
            ClienteDTO.getClienteDTO().setCliente(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombre.getText().equals("") && !tfApellido.getText().equals("") && !tfDireccion.getText().equals("")){
                    agregarCliente();
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(400);
                    stage.menuClienteView();
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInformacion(600);
                    tfNombre.requestFocus();
                }
              
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ClienteDTO.getClienteDTO().getCliente()!= null){
            cargarDatos(ClienteDTO.getClienteDTO().getCliente());
        }
    }  
        
    public void cargarDatos(Cliente cliente){
        tfClienteId.setText(Integer.toString(cliente.getClienteId()));
        tfNombre.setText(cliente.getNombre());
        tfApellido.setText(cliente.getApellido());  
        tfTelefono.setText(cliente.getTelefono());        
        tfDireccion.setText(cliente.getDireccion());      
        tfNIT.setText(cliente.getNit());
    }
    
    public void agregarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarClientes(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfClienteId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setString(4, tfTelefono.getText());
            statement.setString(5, tfDireccion.getText());
            statement.setString(6, tfNIT.getText());      
            statement.execute();
            
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
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
    }
        
    public void editarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarClientes(?,?,?,?,?,?);";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfClienteId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setString(4, tfTelefono.getText());
            statement.setString(5, tfDireccion.getText());
            statement.setString(6, tfNIT.getText());      
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
            System.out.println(e.getMessage());                
            }
        }
    }


    public void setOp(int op) {
        this.op = op;
    }  
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}






