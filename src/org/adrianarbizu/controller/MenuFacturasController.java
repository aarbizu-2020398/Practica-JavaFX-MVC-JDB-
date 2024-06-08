package org.adrianarbizu.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.adrianarbizu.model.Empleados;
import org.adrianarbizu.bean.Factura;
import org.adrianarbizu.dao.Conexion;
import org.adrianarbizu.system.Main;
import javafx.scene.control.DatePicker;
import org.adrianarbizu.model.Cliente;


/**
 * FXML Controller class
 *
 */
public class MenuFacturasController implements Initializable {

    private Main escenarioPrincipal;

    private void desactivarControles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void limpiarControles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void activarControles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    private ObservableList<Factura> listaFactura;
    private ObservableList<Empleados> listaEmpleados;
    private ObservableList<Cliente> listaCliente;
    @FXML
    private TableView<Factura> tblFacturas;
    @FXML
    private TextField txtFacturaID;
    @FXML
    private TextField txtTotalF;
    @FXML
    private TextField txtApellidoC;
    @FXML
    private ComboBox<Cliente> cmbCliente;
    @FXML
    private ComboBox<Empleados> cmbEmpleado;
    @FXML
    private Button btnAgregar;
    @FXML
    private ImageView imgAgregar;
    @FXML
    private Button btnEditar;
    @FXML
    private ImageView imgEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private ImageView imgEliminar;
    @FXML
    private Button btnReporte;
    @FXML
    private ImageView imgReporte;
    @FXML
    private TableColumn<Factura, Integer> colFacturaID;
    @FXML
    private TableColumn<Factura, LocalDate> colFecha;
    @FXML
    private TableColumn<Factura, Time> colHora;
    @FXML
    private TableColumn<Factura, Double> colTotal;
    @FXML
    private TableColumn<Factura, Integer> colClientes;
    @FXML
    private TableColumn<Factura, Integer> colEmpleados;
    @FXML
    private Button btnBack;
    @FXML
    private ImageView imgBack;
    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbCliente.setItems(getClientes());
        cmbEmpleado.setItems(getEmpleados());
    }

    public void cargarDatos() {
        tblFacturas.setItems(getFacturas());
        colFacturaID.setCellValueFactory(new PropertyValueFactory<>("facturaId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colClientes.setCellValueFactory(new PropertyValueFactory<>("codigoCliente"));
        colEmpleados.setCellValueFactory(new PropertyValueFactory<>("empleadoId"));
    }

    public void seleccionarElemento() {
        if (tblFacturas.getSelectionModel().getSelectedItem() != null) {
            Factura facturaSeleccionada = tblFacturas.getSelectionModel().getSelectedItem();
            txtFacturaID.setText(String.valueOf(facturaSeleccionada.getFacturaId()));
            txtTotalF.setText(String.valueOf(facturaSeleccionada.getTotal()));
            cmbCliente.getSelectionModel().select(buscarCliente(facturaSeleccionada.getCodigoCliente()));
            cmbEmpleado.getSelectionModel().select(buscarEmpleado(facturaSeleccionada.getEmpleadoId()));
        }
    }

    public Cliente buscarCliente(int codigoCliente) {
        Cliente resultado = null;
        try {
            Connection conn = (Connection) Conexion.getInstance().getConexion();
            PreparedStatement procedimiento = conn.prepareCall("{call sp_BuscarClientes(?)}");
            procedimiento.setInt(1, codigoCliente);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Cliente(
                        registro.getInt("codigoCliente"),
                        registro.getString("NITCliente"),
                        registro.getString("nombreCliente"),
                        registro.getString("apellidoCliente"),
                        registro.getString("direccionCliente"),
                        registro.getString("telefonoCliente"),
                        registro.getString("correoCliente")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Empleados buscarEmpleado(int empleadoId) {
        Empleados resultado = null;
        try {
            Connection conn = (Connection) Conexion.getInstance().getConexion();
            PreparedStatement procedimiento = conn.prepareCall("{call sp_buscarEmpleado(?)}");
            procedimiento.setInt(1, empleadoId);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new Empleados(
                        registro.getInt("empleadoId"),
                        registro.getString("nombreEmpleado"),
                        registro.getString("apellidoEmpleado"),
                        registro.getDouble("sueldo"),
                        registro.getTime("horaEntrada"),
                        registro.getTime("horaSalida"),
                        registro.getInt("cargoId")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public ObservableList<Factura> getFacturas() {
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            Connection conn = (Connection) Conexion.getInstance().getConexion();
            PreparedStatement procedimiento = conn.prepareCall("{call sp_listarFacturas()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                LocalDate fechaF = resultado.getDate("fecha").toLocalDate();
                lista.add(new Factura(
                        resultado.getInt("facturaId"),
                        resultado.getInt("codigoCliente"),
                        resultado.getInt("empleadoId"),
                        fechaF,
                        resultado.getTime("hora"),
                        resultado.getDouble("total")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFactura = FXCollections.observableArrayList(lista);
    }

    public ObservableList<Cliente> getClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            Connection conn = (Connection) Conexion.getInstance().getConexion();
            PreparedStatement procedimiento = conn.prepareCall("{call sp_ListarClientes()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Cliente(
                        resultado.getInt("codigoCliente"),
                        resultado.getString("NITCliente"),
                        resultado.getString("nombreCliente"),
                        resultado.getString("apellidoCliente"),
                        resultado.getString("direccionCliente"),
                        resultado.getString("telefonoCliente"),
                        resultado.getString("correoCliente")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCliente = FXCollections.observableArrayList(lista);
    }

    public ObservableList<Empleados> getEmpleados() {
        ArrayList<Empleados> lista = new ArrayList<>();
        try {
            Connection conn = (Connection) Conexion.getInstance().getConexion();
            PreparedStatement procedimiento = conn.prepareCall("{call sp_listarEmpleados()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Empleados(
                        resultado.getInt("empleadoId"),
                        resultado.getString("nombreEmpleado"),
                        resultado.getString("apellidoEmpleado"),
                        resultado.getDouble("sueldo"),
                        resultado.getTime("horaEntrada"),
                        resultado.getTime("horaSalida"),
                        resultado.getInt("cargoId")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpleados = FXCollections.observableArrayList(lista);
    }

    public void agregar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                activarControles();
                btnAgregar.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                imgAgregar.setImage(new Image("/org/adrianarbizu/images/Guardar.png"));
                imgEliminar.setImage(new Image("/org/adrianarbizu/images/Cancelar.png"));
                tipoDeOperaciones = operaciones.AGREGAR;
                break;
            case AGREGAR:
                guardar();
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgAgregar.setImage(new Image("/org/adrianarbizu/images/Agregar.png"));
                imgEliminar.setImage(new Image("/org/adrianarbizu/images/Eliminar.png"));
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
}
