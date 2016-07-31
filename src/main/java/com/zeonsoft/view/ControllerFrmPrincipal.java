package com.zeonsoft.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ControllerFrmPrincipal implements Initializable {
    @FXML
    DatePicker dtpDesde;
    @FXML
    DatePicker dtpHasta;
    @FXML
    Button btnSyncArticulos;
    @FXML
    Button btnSyncComprobantes;
    @FXML
    Button btnResumen;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnSyncArticulosOnMouseClicked(Event event) {

    }

    @FXML
    private void btnSyncComprobantesOnMouseClicked(Event event) {
        Date desde = Date.valueOf(dtpDesde.getValue());
        Date hasta = Date.valueOf(dtpHasta.getValue());


    }

    @FXML
    private void btnResumenOnMouseClicked(Event event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FrmResumen.fxml"));

            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root, 1056, 500));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
