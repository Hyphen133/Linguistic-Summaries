package app.controllers;

import app.data.TennisMatch;
import app.data.TennisMatchLinguisticVariables;
import app.loading.TennisCsvLoader;
import app.summarization.LinguisticVariable;
import app.summarization.summary.QuantifierType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
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
    public LineChart quantifierChart;

    @FXML
    public LineChart variableChart;

    @FXML
    ListView variablesListView;

    @FXML
    ListView<String> quantifierListView;

    @FXML
    LineChart lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<TennisMatch> tennisMatches = TennisCsvLoader.load(RECORDS_COUNT);
        linguisticVariableMap = TennisMatchLinguisticVariables.getVariables(tennisMatches);


        //LINGUISTIC VARIABLES
        variablesListView.setItems(FXCollections.observableArrayList(new ArrayList<>(linguisticVariableMap.keySet())));

        //QUANTIFIERS TYPES
        List<String> quantifierTypes = new ArrayList<>();
        for (QuantifierType value : QuantifierType.values()) {
            quantifierTypes.add(value.name());
        }

        quantifierListView.setItems(FXCollections.observableArrayList(quantifierTypes));


    }
}
