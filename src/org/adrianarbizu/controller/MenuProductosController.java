package org.adrianarbizu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import org.adrianarbizu.dao.Conexion;
import org.adrianarbizu.model.Producto;
import org.adrianarbizu.system.Main;

public class MenuProductosController implements Initializable {
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    private List<File> files = null;
    private Main stage;

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    

    @FXML
    Button btnCargar, btnBuscar, btnRegresar;
    @FXML
    TextField tfNombreProducto, tfProductoId;
    @FXML
    ImageView imgCargar, imgMostrar;
    @FXML
    Label lblNombreProducto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        try {
            if (event.getSource() == btnCargar) {
                agregarProducto();
            } else if (event.getSource() == btnBuscar) {
                Producto producto = buscarProducto();
                if (producto != null) {
                    lblNombreProducto.setText(producto.getNombre());
                    Blob imagenBlob = (Blob) producto.getImagen();
                    if (imagenBlob != null) {
                        try (InputStream file = imagenBlob.getBinaryStream()) {
                            Image image = new Image(file);
                            imgMostrar.setImage(image);
                        }
                    }
                }
            } else if (event.getSource() == btnRegresar) {
                stage.menuPrincipalView();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleOnDrag(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    public void handleOnDrop(DragEvent event) {
        try {
            files = event.getDragboard().getFiles();
            if (files != null && !files.isEmpty()) {
                FileInputStream file = new FileInputStream(files.get(0));
                Image image = new Image(file);
                imgCargar.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarProducto() {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarProducto(?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreProducto.getText());
            if (files != null && !files.isEmpty()) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(2, img);
            } else {
                statement.setBinaryStream(2, null);
            }
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Producto buscarProducto() {
        Producto producto = null;
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int productoId = resultSet.getInt("productoId");
                String nombreProducto = resultSet.getString("nombreProducto");
                String descripcionProducto = resultSet.getString("descripcionProducto");
                int cantidadStock = resultSet.getInt("cantidadStock");
                double precioVentaUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioVentaMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagen = resultSet.getBlob("imagen");
                int distribuidorId = resultSet.getInt("distribuidorId");
                int categoriaId = resultSet.getInt("categoriaId");

                producto = new Producto(productoId, nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagen, distribuidorId, categoriaId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
        return producto;
    }



}
