package org.adrianarbizu.system;

import java.io.IOException;


import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.adrianarbizu.controller.FormClienteController;
import org.adrianarbizu.controller.FormDistribuidoresController;
import org.adrianarbizu.controller.FormUsuariosController;
import org.adrianarbizu.controller.LoginController;
import org.adrianarbizu.controller.MenuCargosController;
import org.adrianarbizu.controller.MenuClienteController;
import org.adrianarbizu.controller.MenuComprasController;
import org.adrianarbizu.controller.MenuDetalleFacturasController;
import org.adrianarbizu.controller.MenuDistribuidoresController;
import org.adrianarbizu.controller.MenuPrincipalController;
import org.adrianarbizu.controller.MenuProductosController;
import org.adrianarbizu.controller.MenuTicketSoporteController;

public class Main extends Application {
    private final String URLVIEW = "/org/adrianarbizu/view/";
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Kinal APP");
        loginView(); // Iniciar con la vista de login
        stage.show();
    }

    public Initializable switchScene(String fxmlName, int width, int height) throws Exception {
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        scene = new Scene((AnchorPane) loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        resultado = (Initializable) loader.getController();
        return resultado;
    }

    public void menuPrincipalView() {
        try {
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController) switchScene("MenuPrincipalView.fxml", 900, 675);
            menuPrincipalView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuClienteView() {
        try {
            MenuClienteController menuClientesView = (MenuClienteController) switchScene("MenuClientesView.fxml", 1200, 750);
            menuClientesView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void menuCargoView() {
        try {
            MenuCargosController menuCargoView = (MenuCargosController) switchScene("MenuCargoView.fxml", 650, 700);
            menuCargoView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void formClienteView(int op) {
        try {
            FormClienteController formClienteView = (FormClienteController) switchScene("FormClienteView.fxml", 500, 750);
            formClienteView.setOp(op);
            formClienteView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuTicketSoporteView() {
        try {
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController) switchScene("MenuTicketSoporteView.fxml", 1200, 750);
            menuTicketSoporteView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuProductosControllerView() {
        try {
            MenuProductosController menuProductosView = (MenuProductosController) switchScene("MenuProductosView.fxml", 1200, 750);
            menuProductosView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuComprasProductosView() {
        try {
            MenuComprasController menuComprasView = (MenuComprasController) switchScene("MenuComprasView.fxml", 1200, 750);
            menuComprasView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuDistribuidoresView() {
        try {
            MenuDistribuidoresController menuDistribuidoresView = (MenuDistribuidoresController) switchScene("MenuDistribuidores.fxml", 1200, 750);
            menuDistribuidoresView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void formDistribuidoresView(int op) {
        try {
            FormDistribuidoresController formDistribuidoresController = (FormDistribuidoresController) switchScene("FormDistribuidores.fxml", 500, 400);
            formDistribuidoresController.setStage(this);
            formDistribuidoresController.setOperacion(op);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuDetalleFacturasView() {
        try {
            MenuDetalleFacturasController menuDetalleFacturasView = (MenuDetalleFacturasController) switchScene("MenuDetalleFacturas.fxml", 1200, 750);
            menuDetalleFacturasView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public void loginView() {
        try {
            LoginController loginView = (LoginController) switchScene("LoginView.fxml", 600, 400);
            loginView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
public void formUsuariosView() throws Exception {
    try {
            FormUsuariosController formUsuariosView = (FormUsuariosController) switchScene("FormUsuarios.fxml", 900, 716);
            formUsuariosView.setStage(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void formEmpleadosView(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void formsUsuariosView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public static void main(String[] args) {
        launch(args);
    }

}
