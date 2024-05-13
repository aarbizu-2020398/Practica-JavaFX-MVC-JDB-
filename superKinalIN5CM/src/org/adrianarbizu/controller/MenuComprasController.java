/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adrianarbizu.controller;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuComprasController extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Supermarket Shopping Menu");

        Label productNameLabel = new Label("Product Name:");
        TextField productNameTextField = new TextField();

        Label productQuantityLabel = new Label("Product Quantity:");
        TextField productQuantityTextField = new TextField();

        Button addButton = new Button("Add to Cart");
        addButton.setOnAction(event -> {
        
            System.out.println("Product added to cart!");
        });

        Button viewCartButton = new Button("View Cart");
        viewCartButton.setOnAction(event -> {
            System.out.println("Viewing cart...");
        });

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(productNameLabel, productNameTextField, productQuantityLabel, productQuantityTextField, addButton, viewCartButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}