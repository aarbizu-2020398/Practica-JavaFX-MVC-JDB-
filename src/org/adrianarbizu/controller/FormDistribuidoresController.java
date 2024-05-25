package org.adrianarbizu.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.adrianarbizu.model.Distribuidores;
import org.adrianarbizu.system.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class FormDistribuidoresController implements Initializable {
    private Main stage;
    private int op;
    
    @FXML
    private TextField tfNombreDistribuidor, tfDireccionDistribuidor, tfNitDistribuidor, tfTelefonoDistribuidor, tfWebDistribuidor;
    @FXML
    private Button btnGuardar, btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    public void setOperacion(int op) {
        this.op = op;
    }

    @FXML
    public void handleButtonAction(javafx.event.ActionEvent event) {
        if (event.getSource() == btnGuardar) {
       
            guardarDistribuidor();
        } else if (event.getSource() == btnCancelar) {
          
            stage.menuDistribuidoresView();
        }
    }

    private void guardarDistribuidor() {
   
        String nombre = tfNombreDistribuidor.getText();
        String direccion = tfDireccionDistribuidor.getText();
        String nit = tfNitDistribuidor.getText();
        String telefono = tfTelefonoDistribuidor.getText();
        String web = tfWebDistribuidor.getText();

        Distribuidores distribuidor = new Distribuidores(nombre, direccion, nit, telefono, web);
      
        stage.menuDistribuidoresView();
    }
}
