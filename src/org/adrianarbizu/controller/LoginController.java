package org.adrianarbizu.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.adrianarbizu.dao.Conexion;
import org.adrianarbizu.model.Usuario;
import org.adrianarbizu.system.Main;
import org.adrianarbizu.utils.PasswordUtils;
import org.adrianarbizu.utils.SuperKinalAlert;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    private static Main stage;
    private int op = 0;

    public static Main getStage() {
        return stage;
    }

    public static void setStage(Main stage) {
        LoginController.stage = stage;
    }
    
    @FXML
    Button btn_IniciarSesion, btn_Registrar; 
    
    @FXML
    TextField tf_Usuario;
    
    @FXML
    PasswordField tf_Password;
    
    public Usuario buscarUsuario() {
        Usuario usuario = null;
        
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarUsuario(?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tf_Usuario.getText());
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int usuarioId = resultSet.getInt("usuarioId");
                String usuarios = resultSet.getString("usuario");
                String password = resultSet.getString("contrasenia");
                int nivelesAccesoId = resultSet.getInt("nivelesAccesoId");
                int empleadoId = resultSet.getInt("empleadoId");
                
                usuario = new Usuario(usuarioId, usuarios, password, nivelesAccesoId, empleadoId);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }
    
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_IniciarSesion) {
            if (op == 0) {
                Usuario usuario = buscarUsuario();    
                if (usuario != null) {
                    if (PasswordUtils.getInstance().checkPassword(tf_Password.getText(), usuario.getPassword())) {
                        SuperKinalAlert.getInstance().alertBienvenida(usuario.getUsuario());
                        if (usuario.getNivelesAccesoId() == 1) { // Admin
                            btn_Registrar.setDisable(false);
                            btn_IniciarSesion.setText("Ir al Menú");
                            op = 1;
                        } else if (usuario.getNivelesAccesoId() == 2) { // Empleado
                            stage.menuPrincipalView();
                        }
                    } else {
                        SuperKinalAlert.getInstance().mostrarAlertaInfo(800);
                    }
                } else {
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(900);
                }
            } else {
                stage.menuPrincipalView();
            }
        } else if (event.getSource() == btn_Registrar) {
            stage.formUsuariosView();
        }
    }
}
