package app.controllers;

import app.fuzzy_sets.OperationType;
import app.summarization.quality_measures.QualityMeasureEnum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {
    @FXML
    TreeView<String> treeView;

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

    List<CheckBox> checkBoxes;


    @FXML
    RadioButton summarizerOrOperation;
    @FXML
    RadioButton summarizerAndOperation;
    @FXML
    RadioButton qualifierAndOperation;
    @FXML
    RadioButton qualifierOrOperation;



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


        treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treeView.setRoot(new TreeItem<>("root"));
        treeView.setShowRoot(false);

        treeView.getRoot().getChildren().addAll(new TreeViewHelper().getProducts());


    }


    public void generateSummaries(ActionEvent actionEvent) {

        List<QualityMeasureEnum> chosenMeasures = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if(checkBox.isSelected()){
                chosenMeasures.add(QualityMeasureEnum.valueOf(checkBox.getText().split(":")[0]));
            }
        }

        OperationType summarizerOperation = null;
        if(summarizerAndOperation.isSelected()){
            summarizerOperation = OperationType.UNION;
        }else if(summarizerOrOperation.isSelected()){
            summarizerOperation = OperationType.INTERSECTION;
        }

        OperationType qualifierOperation = null;
        if(qualifierAndOperation.isSelected()){
            qualifierOperation = OperationType.UNION;
        }else if(qualifierOrOperation.isSelected()){
            qualifierOperation = OperationType.INTERSECTION;
        }



    }
}
