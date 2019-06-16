package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;

import java.util.List;

public interface CharacteristicFunction {
    double calculate(double x);

    double getBase();

    double getBaseAbsolute();

    double getArea();

    double getAbsoluteArea();


    //Returns points where function changes (linear)
    List<XYChart.Data<Number, Number>> getCharacteristicPoints();
}
