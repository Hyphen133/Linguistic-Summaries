package app.controllers;

import app.data.TennisMatch;
import app.data.TennisMatchLinguisticVariables;
import app.fuzzy_sets.ClassicSet;
import app.loading.TennisCsvLoader;
import app.summarization.LinguisticVariable;
import app.summarization.summary.Quantifier;
import app.summarization.summary.QuantifierLabel;
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
import java.util.*;

import static app.Config.RECORDS_COUNT;

public class FuzzySetsController implements Initializable {

    private HashMap<String, Quantifier> quantifierMap;
    private Map<String, LinguisticVariable> linguisticVariableMap;

    @FXML
    LineChart<Number, Number> quantifierChart;

    @FXML
    LineChart<Number, Number> variableChart;

    @FXML
    ListView<String> variablesListView;

    @FXML
    ListView<String> quantifierListView;;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<TennisMatch> tennisMatches = TennisCsvLoader.load(RECORDS_COUNT);
        linguisticVariableMap = TennisMatchLinguisticVariables.getVariables(tennisMatches);
        quantifierMap = QuantifierLabel.getMap();

        //LINE CHARTS
        variableChart.setTitle("VariableChart");
        quantifierChart.setTitle("QuantifierChart");



        variableChart.setLegendVisible(true);

        //LINGUISTIC VARIABLES
        variablesListView.setItems(FXCollections.observableArrayList(new ArrayList<>(linguisticVariableMap.keySet())));

        //QUANTIFIERS TYPES
        List<String> quantifierTypes = new ArrayList<>();
        for (QuantifierType value : QuantifierType.values()) {
            quantifierTypes.add(value.name());
        }

        quantifierListView.setItems(FXCollections.observableArrayList(quantifierTypes));
        System.out.println("13214");
    }


    public void drawCharts(ActionEvent event){

        //Getting selections
        String selectedLingusiticVariable = variablesListView.getSelectionModel().getSelectedItem();
        String selectedQuantifier = quantifierListView.getSelectionModel().getSelectedItem();


        if(selectedLingusiticVariable != null){
            variableChart.getData().clear();

            variableChart.setTitle(selectedLingusiticVariable);
            LinguisticVariable linguisticVariable = linguisticVariableMap.get(selectedLingusiticVariable);

            double maxValue = linguisticVariable.getUniverseOfDiscourse().getElements().stream().map(x-> x.getValue()).max(Double::compareTo).get();

            for (String tag : linguisticVariable.getAllTags()) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(tag);

                List<XYChart.Data<Number, Number>> characteristicPoints = linguisticVariable.getCharacteristicFunctionForTag(tag).getCharacteristicPoints();


                XYChart.Data<Number,Number> firstPoint = new XYChart.Data<Number, Number>(0.0, linguisticVariable.getCharacteristicFunctionForTag(tag).calculate(0.0));
                XYChart.Data<Number,Number> lastPoint = new XYChart.Data<Number, Number>(maxValue, linguisticVariable.getCharacteristicFunctionForTag(tag).calculate(maxValue));


                series.getData().add(firstPoint);
                series.getData().addAll(characteristicPoints);
                series.getData().add(lastPoint);

                variableChart.getData().add(series);

            }
        }


        if(selectedQuantifier != null){
            quantifierChart.getData().clear();
            quantifierChart.setTitle(selectedQuantifier);

            for (String s : quantifierMap.keySet()) {
                Quantifier quantifier = quantifierMap.get(s);
                if(quantifier.getQuantifierType().name() == selectedQuantifier){
                    XYChart.Series<Number,Number> series = new XYChart.Series<>();
                    series.setName(quantifier.getName());

                    List<XYChart.Data<Number, Number>> characteristicPoints = quantifier.getQuantifierLabel().getCharacteristicFunction().getCharacteristicPoints();
                    XYChart.Data<Number,Number> firstPoint = new XYChart.Data<Number, Number>(0.0, quantifier.getQuantifierLabel().getCharacteristicFunction().calculate(0.0));
                    XYChart.Data<Number,Number> lastPoint = null;
                    if(quantifier.getQuantifierType() == QuantifierType.RELATIVE){
                        lastPoint = new XYChart.Data<Number, Number>(1, quantifier.getQuantifierLabel().getCharacteristicFunction().calculate(1));

                    }else{
                        lastPoint = new XYChart.Data<Number, Number>(RECORDS_COUNT, quantifier.getQuantifierLabel().getCharacteristicFunction().calculate(RECORDS_COUNT));
                    }

                    series.getData().add(firstPoint);
                    series.getData().addAll(characteristicPoints);
                    series.getData().add(lastPoint);

                    quantifierChart.getData().add(series);
                }
            }
        }
    }
}
