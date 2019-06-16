package app.controllers;

import app.Utils;
import app.data.TennisMatch;
import app.data.TennisMatchLinguisticVariables;
import app.fuzzy_sets.OperationType;
import app.controllers.generating.SummaryData;
import app.loading.TennisCsvLoader;
import app.summarization.LinguisticVariable;
import app.summarization.quality_measures.QualityMeasureEnum;
import app.summarization.summary.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import app.Config;

@Controller
public class MainController implements Initializable {
    private static final String ROOT_NODE = "root";
    private static final String SEPARATOR = ":";
    public CheckBox type1Checkbox;
    public CheckBox type2Checkbox;

    @FXML
    Label summaryLabel;

    @FXML
    Label measuresLabel;

    @FXML
    TableView<SummaryDto> tableView;


    List<CheckBox> checkBoxes;
    Map<String, LinguisticVariable> linguisticVariableMap;
    HashMap<String, Quantifier> quantifierMap;

    @FXML
    ListView<String> summarizerListView;

    @FXML
    ListView<String> qualifierListView;

    @FXML
    ListView<String> quanfierListView;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type1Checkbox.setSelected(true);
        type2Checkbox.setSelected(false);

        checkBoxes = new ArrayList<>();
        checkBoxes.addAll(Arrays.asList(t1CheckBox, t2CheckBox, t3CheckBox, t4CheckBox, t5CheckBox, t6CheckBox, t7CheckBox, t8CheckBox, t9CheckBox, t10CheckBox, t11CheckBox));

        for (int i = 0; i < QualityMeasureEnum.values().length; i++) {
            checkBoxes.get(i).setText(QualityMeasureEnum.values()[i].getName());
        }

        summarizerOrOperation.setText(OperationType.UNION.getOperationName());
        summarizerAndOperation.setText(OperationType.INTERSECTION.getOperationName());
        summarizerAndOperation.setSelected(true);
        qualifierOrOperation.setText(OperationType.UNION.getOperationName());
        qualifierAndOperation.setText(OperationType.INTERSECTION.getOperationName());
        qualifierAndOperation.setSelected(true);

        List<TennisMatch> tennisMatches = TennisCsvLoader.load(Config.RECORDS_COUNT);
        linguisticVariableMap = TennisMatchLinguisticVariables.getVariables(tennisMatches);


        //SUMMARIZERS
        summarizerListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        summarizerListView.setItems(FXCollections.observableArrayList(linguisticVariableMap.keySet()));


        //QUALIFIERS
        qualifierListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        qualifierListView.setItems(FXCollections.observableArrayList(linguisticVariableMap.keySet()));

        //QUANTIFIERS
        quantifierMap = QuantifierLabel.getMap();
        quanfierListView.setItems(FXCollections.observableArrayList(new ArrayList<>(quantifierMap.keySet())));
        quanfierListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        //TABLEVIEW
        tableView.getColumns().clear();
        TableColumn summaryColumn = new TableColumn("Summary");
        summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));

        TableColumn goodnessColumn = new TableColumn("Goodness");
        goodnessColumn.setCellValueFactory(new PropertyValueFactory<>("goodness"));

        TableColumn t1Column = new TableColumn("T1");
        t1Column.setCellValueFactory(new PropertyValueFactory<>("t1"));

        TableColumn t2Column = new TableColumn("T2");
        t2Column.setCellValueFactory(new PropertyValueFactory<>("t2"));

        TableColumn t3Column = new TableColumn("T3");
        t3Column.setCellValueFactory(new PropertyValueFactory<>("t3"));

        TableColumn t4Column = new TableColumn("T4");
        t4Column.setCellValueFactory(new PropertyValueFactory<>("t4"));

        TableColumn t5Column = new TableColumn("T5");
        t5Column.setCellValueFactory(new PropertyValueFactory<>("t5"));

        TableColumn t6Column = new TableColumn("T6");
        t6Column.setCellValueFactory(new PropertyValueFactory<>("t6"));

        TableColumn t7Column = new TableColumn("T7");
        t7Column.setCellValueFactory(new PropertyValueFactory<>("t7"));

        TableColumn t8Column = new TableColumn("T8");
        t8Column.setCellValueFactory(new PropertyValueFactory<>("t8"));

        TableColumn t9Column = new TableColumn("T9");
        t9Column.setCellValueFactory(new PropertyValueFactory<>("t9"));

        TableColumn t10Column = new TableColumn("T10");
        t10Column.setCellValueFactory(new PropertyValueFactory<>("t10"));

        TableColumn t11Column = new TableColumn("T11");
        t11Column.setCellValueFactory(new PropertyValueFactory<>("t11"));

        tableView.getColumns().addAll(summaryColumn, goodnessColumn, t1Column, t2Column, t3Column, t4Column, t5Column, t6Column, t7Column, t8Column, t9Column, t10Column, t11Column);


    }


    public void generateSummaries(ActionEvent actionEvent) {
        tableView.getItems().clear();

        //QUALITY MEASURES
        List<QualityMeasureEnum> chosenMeasures = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                chosenMeasures.add(QualityMeasureEnum.valueOf(checkBox.getText().split(SEPARATOR)[0]));
            }
        }

        //SUMMARIZER OPERATION
        OperationType summarizerOperation = null;
        if (summarizerAndOperation.isSelected()) {
            summarizerOperation = OperationType.INTERSECTION;
        } else if (summarizerOrOperation.isSelected()) {
            summarizerOperation = OperationType.UNION;
        }

        //QUALIFIER OPERATION
        OperationType qualifierOperation = null;
        if (qualifierAndOperation.isSelected()) {
            qualifierOperation = OperationType.INTERSECTION;
        } else if (qualifierOrOperation.isSelected()) {
            qualifierOperation = OperationType.UNION;
        }

        if (qualifierOperation != null) {
            System.out.println(qualifierOperation.getOperationName());
        }


        //SUMMARIZERS
        List<String> selectedSummarizers = summarizerListView.getSelectionModel().getSelectedItems();


        //QUALIFIERS
        List<String> selectedQualifiers = qualifierListView.getSelectionModel().getSelectedItems();


        //QUANTIFIERS
        List<String> selectedItems = quanfierListView.getSelectionModel().getSelectedItems();
        List<Quantifier> quantifiers = selectedItems.stream().map(x -> new Quantifier(QuantifierLabel.valueOf(x.replace(" ", "_")))).collect(Collectors.toList());


        //GENERATING SUMMARIES
        List<SummaryData> dataList = new ArrayList<>();


        if(type1Checkbox.isSelected()){
            //Type1
            for (String selectedSumm : selectedSummarizers) {
                for (String summarizerTag : linguisticVariableMap.get(selectedSumm).getAllTags()) {
                    for (Quantifier quantifier : quantifiers) {
                        dataList.add(SummaryData.builder().summarizerVariables(Arrays.asList(selectedSumm)).summarizerTags(Arrays.asList(summarizerTag)).quantifier(quantifier).build());
                    }
                }
            }


            //Type1 2summ
            for (String selectedSumm1 : selectedSummarizers) {
                for (String summarizerTag1 : linguisticVariableMap.get(selectedSumm1).getAllTags()) {
                    for (String selectedSumm2 : selectedSummarizers) {
                        for (String summarizerTag2 : linguisticVariableMap.get(selectedSumm2).getAllTags()) {
                            for (Quantifier quantifier : quantifiers) {

                                List<String> summarizerStrings = Arrays.asList(selectedSumm1, selectedSumm2);
                                List<String> summarizerTagStrings = Arrays.asList(summarizerTag1, summarizerTag2);

                                if (!Utils.hasDuplicates(summarizerStrings)) {
                                    dataList.add(SummaryData.builder().summarizerVariables(summarizerStrings).summarizerTags(summarizerTagStrings).quantifier(quantifier).build());
                                }
                            }
                        }

                    }
                }
            }
        }

        if(type2Checkbox.isSelected()){
            //Type2
            if (selectedQualifiers.size() > 0) {
                for (String selectedSumm1 : selectedSummarizers) {
                    for (String summarizerTag1 : linguisticVariableMap.get(selectedSumm1).getAllTags()) {
                        for (String selectedQuali : selectedQualifiers) {
                            for (String qualifierTag : linguisticVariableMap.get(selectedQuali).getAllTags()) {
                                for (Quantifier quantifier : quantifiers) {

                                    List<String> summarizerStrings = Arrays.asList(selectedSumm1);
                                    List<String> summarizerTagStrings = Arrays.asList(summarizerTag1);
                                    List<String> qualifierStrings = Arrays.asList(selectedQuali);
                                    List<String> qualifierTagStrings = Arrays.asList(qualifierTag);

                                    if (!Utils.hasDuplicates(ListUtils.union(summarizerStrings, qualifierStrings))) {
                                        dataList.add(SummaryData.builder().summarizerVariables(summarizerStrings).summarizerTags(summarizerTagStrings).qualifierVariables(qualifierStrings).qualifierTags(qualifierTagStrings).quantifier(quantifier).build());
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //Type2 2summ+1quali
            //Type2
            if (selectedQualifiers.size() > 0 && selectedSummarizers.size() > 0) {
                for (String selectedSumm1 : selectedSummarizers) {
                    for (String summarizerTag1 : linguisticVariableMap.get(selectedSumm1).getAllTags()) {
                        for (String selectedSumm2 : selectedSummarizers) {
                            for (String summarizerTag2 : linguisticVariableMap.get(selectedSumm2).getAllTags()) {
                                for (String selectedQuali : selectedQualifiers) {
                                    for (String qualifierTag : linguisticVariableMap.get(selectedQuali).getAllTags()) {
                                        for (Quantifier quantifier : quantifiers) {

                                            List<String> summarizerStrings = Arrays.asList(selectedSumm1, selectedSumm2);
                                            List<String> summarizerTagStrings = Arrays.asList(summarizerTag1, summarizerTag2);
                                            List<String> qualifierStrings = Arrays.asList(selectedQuali);
                                            List<String> qualifierTagStrings = Arrays.asList(qualifierTag);

                                            if (!Utils.hasDuplicates(ListUtils.union(summarizerStrings, qualifierStrings))) {
                                                dataList.add(SummaryData.builder().summarizerVariables(summarizerStrings).summarizerTags(summarizerTagStrings).qualifierVariables(qualifierStrings).qualifierTags(qualifierTagStrings).quantifier(quantifier).build());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            //Type2 2summ+2quali
            //Type2
//        if (selectedQualifiers.size() > 0 && selectedSummarizers.size()>0) {
//            for (String selectedSumm1 : selectedSummarizers) {
//                for (String summarizerTag1 : linguisticVariableMap.get(selectedSumm1).getAllTags()) {
//                    for (String selectedSumm2 : selectedSummarizers) {
//                        for (String summarizerTag2 : linguisticVariableMap.get(selectedSumm2).getAllTags()) {
//                            for (String selectedQuali1 : selectedQualifiers) {
//                                for (String qualifierTag1 : linguisticVariableMap.get(selectedQuali1).getAllTags()) {
//                                    for (String selectedQuali2 : selectedQualifiers) {
//                                        for (String qualifierTag2 : linguisticVariableMap.get(selectedQuali1).getAllTags()) {
//                                            for (Quantifier quantifier : quantifiers) {
//
//                                                List<String> summarizerStrings = Arrays.asList(selectedSumm1, selectedSumm2);
//                                                List<String> summarizerTagStrings = Arrays.asList(summarizerTag1,summarizerTag2);
//                                                List<String> qualifierStrings = Arrays.asList(selectedQuali1,selectedQuali2);
//                                                List<String> qualifierTagStrings = Arrays.asList(qualifierTag1,qualifierTag2);
//
//                                                if(!Utils.hasDuplicates(ListUtils.union(summarizerStrings, qualifierStrings))){
//                                                    dataList.add(SummaryData.builder().summarizerVariables(summarizerStrings).summarizerTags(summarizerTagStrings).qualifierVariables(qualifierStrings).qualifierTags(qualifierTagStrings).quantifier(quantifier).build());
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        }


        for (SummaryData summaryData : dataList) {
            Summary summary = null;
            if (summaryData.getQualifierTags() == null) {
                summary = new TypeOneSummary("Tennis match player", summaryData.getSummarizerVariables().stream().map(x -> linguisticVariableMap.get(x)).collect(Collectors.toList()), summaryData.getSummarizerTags(), summaryData.getQuantifier(), summarizerOperation);
            } else {
                summary = new TypeTwoSummary("Tennis match player", summaryData.getSummarizerVariables().stream().map(x -> linguisticVariableMap.get(x)).collect(Collectors.toList()), summaryData.getSummarizerTags(), summaryData.getQualifierVariables().stream().map(x -> linguisticVariableMap.get(x)).collect(Collectors.toList()), summaryData.getQualifierTags(), summaryData.getQuantifier(), qualifierOperation, summarizerOperation);

            }

            GoodnessOfSummary summaryMeasures = new GoodnessOfSummary(summary);
            for (QualityMeasureEnum chosenMeasure : chosenMeasures) {
                summaryMeasures.addQualityMeasure(chosenMeasure);
            }

            double summaryGoodness = Utils.round(summaryMeasures.count(), 2);

            ArrayList<Double> measures = new ArrayList<>();
            for (QualityMeasureEnum value : QualityMeasureEnum.values()) {
                measures.add(Utils.round(QualityMeasureEnum.getValue(value, summary), 2));
            }

            SummaryDto summaryDto = SummaryDto.builder().summary(summary.getSummary()).goodness(summaryGoodness)
                    .t1(measures.get(0))
                    .t2(measures.get(1))
                    .t3(measures.get(2))
                    .t4(measures.get(3))
                    .t5(measures.get(4))
                    .t6(measures.get(5))
                    .t7(measures.get(6))
                    .t8(measures.get(7))
                    .t9(measures.get(8))
                    .t10(measures.get(9))
                    .t11(measures.get(10))
                    .build();

            tableView.getItems().add(summaryDto);

        }


        List<String> chosenColumns = chosenMeasures.stream().map(x -> x.getName().split(SEPARATOR)[0]).collect(Collectors.toList());
        for (TableColumn<SummaryDto, ?> column : tableView.getColumns()) {
            //Not summary and goodness
            if (column.getText().charAt(0) == 'T') {
                if (chosenColumns.contains(column.getText())) {
                    column.setVisible(true);
                } else {
                    column.setVisible(false);
                }
            }

        }

        tableView.refresh();
    }

    public void defineQuantifier(ActionEvent e) {
        Stage stage = new Stage();
        //Fill stage with content
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/define_function.fxml"));
        Parent parent = null;
        try {
            parent = (Parent) fxmlLoader.load();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void saveToFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showSaveDialog(null);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
            for (SummaryDto item : tableView.getItems()) {
                writer.write(item.toString());
                writer.write('\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void moveToFuzzySetsWindow(ActionEvent event) {
        Stage stage = new Stage();
        //Fill stage with content
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/fuzzy_sets.fxml"));
        Parent parent = null;
        try {
            parent = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
