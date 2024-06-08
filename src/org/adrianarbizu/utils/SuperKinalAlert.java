package org.adrianarbizu.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SuperKinalAlert {

    private static SuperKinalAlert instance;

    private SuperKinalAlert() {
    }

    public static SuperKinalAlert getInstance() {
        if (instance == null) {
            instance = new SuperKinalAlert();
        }
        return instance;
    }

    public void alertBienvenida(String usuario) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bienvenida");
        alert.setHeaderText(null);
        alert.setContentText("¡Bienvenido, " + usuario + "!");
        alert.showAndWait();
    }

    public void mostrarAlertaInfo(int codigo) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        
        switch (codigo) {
            case 800:
                alert.setContentText("Contraseña incorrecta.");
                break;
            case 900:
                alert.setContentText("Usuario no encontrado.");
                break;
            default:
                throw new UnsupportedOperationException("Código de alerta no soportado: " + codigo);
        }

        alert.showAndWait();
    }

    public void mostrarAlertaInformacion(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object mostrarAlertaConfirmacion(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
