package com.util;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.geometry.*;

public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); //Like an alert box, doesn't allow background window to be clicked until current window is somehow closed
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER); //from javafx.geometry and centers nodes

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); //in conjunction with modality, can't do anything else until window closed.
    }
}
