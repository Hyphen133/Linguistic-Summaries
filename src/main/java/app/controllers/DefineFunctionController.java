package app.controllers;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.summarization.summary.AllQuantifiers;
import app.summarization.summary.QuantifierLabel;
import app.summarization.summary.QuantifierType;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static app.Config.RECORDS_COUNT;

@Controller
public class DefineFunctionController implements Initializable {

    public LineChart functionPreview;
    public TextField aTextField;
    public TextField bTextField;
    public TextField cTextField;
    public ListView<String> typeListView;
    public ListView<String> functionListView;
    public TextField dTextField;
    public TextField nameTextField;

    private String characteristicFunctionPackage;
    private CharacteristicFunction characteristicFunction;
    private QuantifierType quantifierType;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Function LISTVIEW
        characteristicFunctionPackage =CharacteristicFunction.class.getPackage().getName();

        ImmutableSet<ClassPath.ClassInfo> characteristicFunctions = null;
        try {
            characteristicFunctions = ClassPath.from(getClass().getClassLoader()).getTopLevelClasses(characteristicFunctionPackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        functionListView.getItems().addAll(characteristicFunctions.stream().map(x->x.getSimpleName()).collect(Collectors.toList()));
        //remove base type
        functionListView.getItems().removeIf(x->x.equals(CharacteristicFunction.class.getSimpleName()));
        functionListView.getSelectionModel().selectFirst();


        //Type LISTVIEW
        typeListView.getItems().addAll(QuantifierType.ABSOLUTE.name(), QuantifierType.RELATIVE.name());
        typeListView.getSelectionModel().selectFirst();
    }


    public void showFunction(ActionEvent event) {

        loadForFields();

        functionPreview.getData().clear();
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        List<XYChart.Data<Number, Number>> characteristicPoints = characteristicFunction.getCharacteristicPoints();


        XYChart.Data<Number,Number> firstPoint = new XYChart.Data<Number, Number>(0.0, characteristicFunction.calculate(0.0));
        double maxValue = quantifierType.equals(QuantifierType.RELATIVE) ? 1.0 : RECORDS_COUNT;
        XYChart.Data<Number,Number> lastPoint = new XYChart.Data<Number, Number>(maxValue, characteristicFunction.calculate(maxValue));


        series.getData().add(firstPoint);
        series.getData().addAll(characteristicPoints);
        series.getData().add(lastPoint);

        functionPreview.getData().add(series);

    }

    public void saveQuantifier(ActionEvent event) {
        loadForFields();
        String name = nameTextField.getText().toUpperCase().replace(" ", "_");
        System.out.println(AllQuantifiers.getQuantifiers().keySet().size());
        AllQuantifiers.addQuantifierLabel(name, new QuantifierLabel(name, quantifierType, characteristicFunction));
        System.out.println(AllQuantifiers.getQuantifiers().keySet().size());
        System.out.println("Quantifier added");
    }

    private void loadForFields() {
        quantifierType = QuantifierType.valueOf(typeListView.getSelectionModel().getSelectedItem());

        //Get all potential arguments of constructor
        List<Double> potentialArgs = new ArrayList<>();

        try {
            potentialArgs.add(Double.parseDouble(aTextField.getText()));
            potentialArgs.add(Double.parseDouble(bTextField.getText()));
            potentialArgs.add(Double.parseDouble(cTextField.getText()));
            potentialArgs.add(Double.parseDouble(dTextField.getText()));
        } catch (NumberFormatException e) {
            System.out.println("Field got badly parsed, not essentially bad!!!");
        }

        //Get constructor
        Class<?> cf = null;
        try {
            cf = Class.forName(characteristicFunctionPackage + "." + functionListView.getSelectionModel().getSelectedItem());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<?> allArgsConstructor = Arrays.stream(cf.getConstructors()).max(Comparator.comparingInt(Constructor::getParameterCount)).get();
        System.out.println(allArgsConstructor.getParameterCount());
        int argsCount = allArgsConstructor.getParameterCount();

        //Init function
        try {
            this.characteristicFunction = (CharacteristicFunction) allArgsConstructor.newInstance(potentialArgs.subList(0, argsCount).toArray(new Double[0]));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
