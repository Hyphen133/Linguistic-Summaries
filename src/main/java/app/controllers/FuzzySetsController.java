package app.controllers;

import app.data.TennisMatch;
import app.data.TennisMatchLinguisticVariables;
import app.fuzzy_sets.ClassicSet;
import app.loading.TennisCsvLoader;
import app.summarization.LinguisticVariable;
import app.summarization.summary.QuantifierType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static app.Config.RECORDS_COUNT;

public class FuzzySetsController implements Initializable {

    private Map<String, LinguisticVariable> linguisticVariableMap;

    @FXML
    LineChart<Number, Number> quantifierChart;

    @FXML
    LineChart<Number, Number> variableChart;

    @FXML
    ListView<String> variablesListView;

    @FXML
    ListView<String> quantifierListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<TennisMatch> tennisMatches = TennisCsvLoader.load(RECORDS_COUNT);
        linguisticVariableMap = TennisMatchLinguisticVariables.getVariables(tennisMatches);

        //LINE CHARTS

        
        //LINGUISTIC VARIABLES
        variablesListView.setItems(FXCollections.observableArrayList(new ArrayList<>(linguisticVariableMap.keySet())));

        //QUANTIFIERS TYPES
        List<String> quantifierTypes = new ArrayList<>();
        for (QuantifierType value : QuantifierType.values()) {
            quantifierTypes.add(value.name());
        }

        quantifierListView.setItems(FXCollections.observableArrayList(quantifierTypes));
    }


    public void drawCharts(ActionEvent event){

        //Getting selections
        String selectedLingusiticVariable = variablesListView.getSelectionModel().getSelectedItem();
        String selectedQuantifier = quantifierListView.getSelectionModel().getSelectedItem();


        if(selectedLingusiticVariable != null){
            variableChart.setTitle(selectedLingusiticVariable);

            LinguisticVariable linguisticVariable = linguisticVariableMap.get(selectedLingusiticVariable);

            for (String tag : linguisticVariable.getAllTags()) {
//                XYChart.Series<Double,Double> series = new XYChart.Series<>();
//                series.setName(tag);

//                List<XYChart.Data<Double,Double>> characteristicPoints = linguisticVariable.getCharacteristicFunctionForTag(tag).getCharacteristicPoints();
//                ClassicSet universeOfDiscourse = linguisticVariable.getUniverseOfDiscourse();
//                XYChart.Data<Double,Double> firstPoint = new XYChart.Data<>(universeOfDiscourse.getElements().get(0).getValue(), characteristicPoints.get(0).getYValue());
//                XYChart.Data<Double,Double> lastPoint = new XYChart.Data<>(universeOfDiscourse.getElements().get(universeOfDiscourse.getSize()-1).getValue(), characteristicPoints.get(characteristicPoints.size()-1).getYValue());
////

//                characteristicPoints.removeIf(x -> x.getXValue() < firstPoint.getXValue() || x.getXValue() > lastPoint.getXValue());

//                ObservableList<XYChart.Data<Number,Number>> data = FXCollections.observableArrayList();
//                data.add(firstPoint);
//                data.addAll(characteristicPoints);
//                data.add(lastPoint);

//                data.add(new XYChart.Data<Double, Double>(1.0,2.0));
//                data.add(new XYChart.Data<Double, Double>(2.0,4.0));


//                series.getData().add(firstPoint);
//                series.getData().addAll(characteristicPoints);
//                series.getData().add(lastPoint);

//                variableChart.getData().add(new XYChart.Series<>(tag, data));
            }

            System.out.println("Done!!");
        }


        if(selectedQuantifier != null){

        }
    }
}
