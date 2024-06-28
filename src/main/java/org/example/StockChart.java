package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class StockChart extends Application {

    private BarChart<String, Number> barChart;
    public static List<String> symbols;
    public static StockService stockService;

    @Override
    public void start(Stage stage) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Stock Symbol");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Stock Value");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Stock Chart");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Stock Values");

//        for (String symbol : symbols) {
//            double value = stockService.getStockValue(symbol);
//            series.getData().add(new XYChart.Data<>(symbol, value));
//        }

        barChart.getData().add(series);

        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public BarChart<String, Number> getBarChart() {
        return barChart;
    }
}
