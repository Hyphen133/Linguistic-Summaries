package app.controllers;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.summarization.summary.Quantifier;
import app.summarization.summary.QuantifierLabel;
import app.summarization.summary.QuantifierType;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class DefineFunctionController implements Initializable {

    public LineChart functionPreview;
    public TextField aTextField;
    public TextField bTextField;
    public TextField cTextField;
    public ListView<String> typeListView;
    public ListView<String> functionListView;
    public TextField dTextField;

    private String characteristicFunctionPackage;

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
        functionListView.getSelectionModel().selectFirst();


        //Type LISTVIEW
        typeListView.getItems().addAll(QuantifierType.ABSOLUTE.name(), QuantifierType.RELATIVE.name());
        typeListView.getSelectionModel().selectFirst();




    }


    public void showFunction(ActionEvent event) {


    }

    public void saveQuantifier(ActionEvent event) {
        //Get all potential arguments of constructor
        List<Double> potentialArgs = new ArrayList<>();

        try{
            potentialArgs.add(Double.parseDouble(aTextField.getText()));
            potentialArgs.add(Double.parseDouble(bTextField.getText()));
            potentialArgs.add(Double.parseDouble(cTextField.getText()));
            potentialArgs.add(Double.parseDouble(dTextField.getText()));
        }catch (NumberFormatException e){
            System.out.println("Field got badly parsed, not essentially bad!!!");
        }

        //Get constructor
        Class<?> characteristicFunction = null;
        try {
            characteristicFunction = Class.forName(characteristicFunctionPackage + "." + functionListView.getSelectionModel().getSelectedItem());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor<?> allArgsConstructor = Arrays.stream(characteristicFunction.getConstructors()).max(Comparator.comparingInt(Constructor::getParameterCount)).get();
        System.out.println(allArgsConstructor.getParameterCount());
        int argsCount = allArgsConstructor.getParameterCount();

        //Init function
        CharacteristicFunction function = null;
        try {
            function = (CharacteristicFunction)allArgsConstructor.newInstance(potentialArgs.subList(0, argsCount).toArray(new Double[0]));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
