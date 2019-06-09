package app.controllers;

import app.data.TennisMatch;
import app.data.TennisMatchLinguisticVariables;
import app.fuzzy_sets.OperationType;
import app.loading.TennisCsvLoader;
import app.summarization.LinguisticVariable;
import app.summarization.quality_measures.QualityMeasureEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController implements Initializable {
    private static final String ROOT_NODE = "root";
    private static final String SEPARATOR = ":";
    private final int RECORDS_COUNT = 1500 ;

    @FXML
    TreeView<String> summarizerTreeView;

    @FXML
    TreeView<String> quantifierTreeView;

    @FXML
    CheckBox t1CheckBox;
    @FXML
    CheckBox t2CheckBox;
    @FXML
    CheckBox t3CheckBox;
    @FXML
    CheckBox t4CheckBox;
    @FXML
    CheckBox t5CheckBox;
    @FXML
    CheckBox t6CheckBox;
    @FXML
    CheckBox t7CheckBox;
    @FXML
    CheckBox t8CheckBox;
    @FXML
    CheckBox t9CheckBox;
    @FXML
    CheckBox t10CheckBox;
    @FXML
    CheckBox t11CheckBox;

    @FXML
    RadioButton summarizerOrOperation;
    @FXML
    RadioButton summarizerAndOperation;
    @FXML
    RadioButton qualifierAndOperation;
    @FXML
    RadioButton qualifierOrOperation;



    List<CheckBox> checkBoxes;
    Map<String, LinguisticVariable> linguisticVariableMap;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        t1CheckBox.setText(QualityMeasureEnum.T1.getName());
        t2CheckBox.setText(QualityMeasureEnum.T2.getName());
        t3CheckBox.setText(QualityMeasureEnum.T3.getName());
        t4CheckBox.setText(QualityMeasureEnum.T4.getName());
        t5CheckBox.setText(QualityMeasureEnum.T5.getName());
        t6CheckBox.setText(QualityMeasureEnum.T6.getName());
        t7CheckBox.setText(QualityMeasureEnum.T7.getName());
        t8CheckBox.setText(QualityMeasureEnum.T8.getName());
        t9CheckBox.setText(QualityMeasureEnum.T9.getName());
        t10CheckBox.setText(QualityMeasureEnum.T10.getName());
        t11CheckBox.setText(QualityMeasureEnum.T11.getName());

        summarizerOrOperation.setText(OperationType.UNION.getOperationName());
        summarizerAndOperation.setText(OperationType.INTERSECTION.getOperationName());
        qualifierOrOperation.setText(OperationType.UNION.getOperationName());
        qualifierAndOperation.setText(OperationType.INTERSECTION.getOperationName());


        checkBoxes = new ArrayList<>();
        checkBoxes.add(t1CheckBox);
        checkBoxes.add(t2CheckBox);
        checkBoxes.add(t3CheckBox);
        checkBoxes.add(t4CheckBox);
        checkBoxes.add(t5CheckBox);
        checkBoxes.add(t6CheckBox);
        checkBoxes.add(t7CheckBox);
        checkBoxes.add(t8CheckBox);
        checkBoxes.add(t9CheckBox);
        checkBoxes.add(t10CheckBox);
        checkBoxes.add(t11CheckBox);



        List<TennisMatch> tennisMatches = TennisCsvLoader.load(RECORDS_COUNT);
        linguisticVariableMap = TennisMatchLinguisticVariables.getVariables(tennisMatches);

        List<TreeItem<String>> linguisticVariableTreeItems = new ArrayList<>();

        for (String key : linguisticVariableMap.keySet()) {
            TreeItem<String> variableItem = new TreeItem<>(key);
            for (String tag : linguisticVariableMap.get(key).getAllTags()) {
                variableItem.getChildren().add(new TreeItem<>(tag));
            }
            linguisticVariableTreeItems.add(variableItem);
        }



        summarizerTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        summarizerTreeView.setRoot(new TreeItem<>(ROOT_NODE));
        summarizerTreeView.setShowRoot(false);
        summarizerTreeView.getRoot().getChildren().addAll(linguisticVariableTreeItems);



        List<TreeItem<String>> linguisticVariableTreeItems2 = new ArrayList<>();

        for (String key : linguisticVariableMap.keySet()) {
            TreeItem<String> variableItem = new TreeItem<>(key);
            for (String tag : linguisticVariableMap.get(key).getAllTags()) {
                variableItem.getChildren().add(new TreeItem<>(tag));
            }
            linguisticVariableTreeItems.add(variableItem);
        }



        quantifierTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        quantifierTreeView.setRoot(new TreeItem<>(ROOT_NODE));
        quantifierTreeView.setShowRoot(false);
        quantifierTreeView.getRoot().getChildren().addAll(linguisticVariableTreeItems2);
    }


    public void generateSummaries(ActionEvent actionEvent) {

        //QUALITY MEASURES
        List<QualityMeasureEnum> chosenMeasures = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if(checkBox.isSelected()){
                chosenMeasures.add(QualityMeasureEnum.valueOf(checkBox.getText().split(SEPARATOR)[0]));
            }
        }
        for (QualityMeasureEnum chosenMeasure : chosenMeasures) {
            System.out.println(chosenMeasure.getName());
        }


        //SUMMARIZER OPERATION
        OperationType summarizerOperation = null;
        if(summarizerAndOperation.isSelected()){
            summarizerOperation = OperationType.INTERSECTION;
        }else if(summarizerOrOperation.isSelected()){
            summarizerOperation = OperationType.UNION;
        }

        if(summarizerOperation != null){
            System.out.println(summarizerOperation.getOperationName());
        }

        //QUALIFIER OPERATION
        OperationType qualifierOperation = null;
        if(qualifierAndOperation.isSelected()){
            qualifierOperation = OperationType.INTERSECTION;
        }else if(qualifierOrOperation.isSelected()){
            qualifierOperation = OperationType.UNION;
        }

        if(qualifierOperation != null){
            System.out.println(qualifierOperation.getOperationName());
        }


        //SUMMARIZERS
        List<String> selectedVariables = summarizerTreeView.getSelectionModel().getSelectedItems().stream().map(x -> x.getParent().getValue() + SEPARATOR + x.getValue()).collect(Collectors.toList());
        List<String> summarizerTags = new ArrayList<>();
        List<LinguisticVariable> summarizerVariables = new ArrayList<>();
        for (String selectedVariable : selectedVariables) {
            System.out.println(selectedVariable);
            String[] splittedString = selectedVariable.split(SEPARATOR);
            if(splittedString[0] != ROOT_NODE){
                summarizerVariables.add(linguisticVariableMap.get(splittedString[0]));
                summarizerTags.add(splittedString[1]);
            }

        }


        //QUALIFIERS
        List<String> selectedVariables1 = quantifierTreeView.getSelectionModel().getSelectedItems().stream().map(x -> x.getParent().getValue() + SEPARATOR + x.getValue()).collect(Collectors.toList());
        List<String> qualifierTags = new ArrayList<>();
        List<LinguisticVariable> qualifierVariables = new ArrayList<>();
        for (String selectedVariable : selectedVariables) {
            System.out.println(selectedVariable);
            String[] splittedString = selectedVariable.split(SEPARATOR);
            if(splittedString[0] != ROOT_NODE){
                qualifierVariables.add(linguisticVariableMap.get(splittedString[0]));
                qualifierTags.add(splittedString[1]);
            }
        }



    }
}
