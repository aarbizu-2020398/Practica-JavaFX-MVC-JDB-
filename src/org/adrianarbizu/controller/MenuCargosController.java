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
import javafx.scene.control.Button;
import org.adrianarbizu.system.Main;

/**
 *
 * @author informatica
 */
public class MenuCargosController {
     public Main stage;

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
   */ 
    
    @FXML
    Button button_AgregarCarg, button_Eliminar, button_Editar, button_Buscar, button_Regresar;
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == button_AgregarCarg){
            
        }else if(event.getSource() == button_Eliminar){
            
        }else if(event.getSource() == button_Editar){
            
        }else if(event.getSource() == button_Buscar){
            
        }else if(event.getSource() == button_Regresar){
            stage.menuPrincipalView();
        }
    }
}
