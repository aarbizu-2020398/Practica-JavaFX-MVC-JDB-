/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adrianarbizu.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.adrianarbizu.system.Main;
/**
 *
 * @author Adrian
 */
public class MenuPrincipalController implements Initializable{
    private Main stage;
    @FXML
    MenuItem botClientes, botTicketSoporte,botProductos,botCargos,botCompras, botDetalleFactura,botDistribuidores;
    

    @Override
    public void initialize (URL Location, ResourceBundle resources){
    
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    @FXML

public void handleButtonAction(ActionEvent event) {
    if (event.getSource() == botClientes) {
        stage.menuClienteView();
    } else if (event.getSource() == botTicketSoporte) {
        stage.menuTicketSoporteView();
    } else if (event.getSource() == botProductos) {
        stage.menuProductosControllerView();
    } else if (event.getSource() == botCargos) {
        
    } else if (event.getSource() == botCompras) {
        stage.menuComprasProductosView();
    } else if (event.getSource() == botDetalleFactura) {
        stage.menuDetalleFacturasView();
    }else if (event.getSource() == botDistribuidores) {
        stage.menuDistribuidoresView();
    }
    
    }
}
