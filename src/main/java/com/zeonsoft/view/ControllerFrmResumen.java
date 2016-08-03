package com.zeonsoft.view;

import com.zeonsoft.controller.SistemaGestionStock;
import com.zeonsoft.model.ArticuloView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ControllerFrmResumen implements Initializable {
    @FXML
    DatePicker dtpDesde;
    @FXML
    DatePicker dtpHasta;
    @FXML
    Button btnFiltrar;
    @FXML
    TableView<ArticuloView> tblResumen;
    @FXML
    TableColumn<ArticuloView, String> colNro;
    @FXML
    TableColumn<ArticuloView, String> colArticulo;
    @FXML
    TableColumn<ArticuloView, Integer> colStockUni;
    @FXML
    TableColumn<ArticuloView, Float> colStock$;
    @FXML
    TableColumn<ArticuloView, Integer> colVentaUni;
    @FXML
    TableColumn<ArticuloView, Float> colVenta$;
    @FXML
    TableColumn<ArticuloView, Float> colCmv$;
    @FXML
    TableColumn<ArticuloView, Float> colMargen$;
    @FXML
    TableColumn<ArticuloView, Float> colMargenPor;
    @FXML
    Label lblStock$;
    @FXML
    Label lblVenta$;
    @FXML
    Label lblCmv$;
    @FXML
    Label lblMargen$;
    @FXML
    Label lblMargenPor;


    private ObservableList<ArticuloView> resumen;

    public void initialize(URL location, ResourceBundle resources) {
        resumen = FXCollections.observableArrayList();

        SistemaGestionStock.getInstancia().procesarComprobantes();

        configurarTableViewResumen();
    }

    private void configurarTableViewResumen() {
        colNro.setCellValueFactory(new PropertyValueFactory<ArticuloView, String>("nro"));
        colArticulo.setCellValueFactory(new PropertyValueFactory<ArticuloView, String>("articulo"));
        colStockUni.setCellValueFactory(new PropertyValueFactory<ArticuloView, Integer>("stockUni"));
        colStock$.setCellValueFactory(new PropertyValueFactory<ArticuloView, Float>("stock$"));
        colVentaUni.setCellValueFactory(new PropertyValueFactory<ArticuloView, Integer>("ventaUni"));
        colVenta$.setCellValueFactory(new PropertyValueFactory<ArticuloView, Float>("venta$"));
        colCmv$.setCellValueFactory(new PropertyValueFactory<ArticuloView, Float>("cmv$"));
        colMargen$.setCellValueFactory(new PropertyValueFactory<ArticuloView, Float>("margen$"));
        colMargenPor.setCellValueFactory(new PropertyValueFactory<ArticuloView, Float>("margenPor"));

        formatoCeldasInteger(colStockUni);
        formatoCeldasInteger(colVentaUni);

        formatoCeldasFloat(colStock$);
        formatoCeldasFloat(colVenta$);
        formatoCeldasFloat(colCmv$);
        formatoCeldasFloat(colMargen$);
        formatoCeldasFloat(colMargenPor);

        tblResumen.setItems(resumen);
    }

    private void formatoCeldasInteger(TableColumn col) {
        col.setCellFactory(column -> {
            return new TableCell<ArticuloView, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.toString());
                        setAlignment(Pos.CENTER_RIGHT);
                    }
                }
            };
        });
    }

    private void formatoCeldasFloat(TableColumn col) {
        col.setCellFactory(column -> {
            return new TableCell<ArticuloView, Float>() {
                @Override
                protected void updateItem(Float item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        DecimalFormat df = new DecimalFormat();

                        df.setMaximumFractionDigits(2);
                        df.setMinimumFractionDigits(2);

                        setText(df.format(item));
                        setAlignment(Pos.CENTER_RIGHT);
                    }
                }
            };
        });
    }

    @FXML
    private void btnFiltrarOnMouseClicked(Event event) {
        Date desde = Date.valueOf(dtpDesde.getValue());
        Date hasta = Date.valueOf(dtpHasta.getValue());

        resumen.clear();
        resumen.addAll(SistemaGestionStock.getInstancia().getResumen(desde, hasta));

        calcularTotales();
    }

    private void calcularTotales() {
        float stock$ = 0;
        float venta$ = 0;
        float cmv$ = 0;
        float margen$ = 0;
        float margenPor = 0;

        for (ArticuloView art: resumen) {
            stock$ = stock$ + art.getStock$();
            venta$ = venta$ + art.getVenta$();
            cmv$ = cmv$+ art.getCmv$();
            margen$ = margen$ + art.getMargen$();
        }

        margenPor = (margen$ / venta$) * 100;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        lblStock$.setText("$ " + df.format(stock$));
        lblVenta$.setText("$ " + df.format(venta$));
        lblCmv$.setText("$ " + df.format(cmv$));
        lblMargen$.setText("$ " + df.format(margen$));
        lblMargenPor.setText(df.format(margenPor) + " %");
    }
}
