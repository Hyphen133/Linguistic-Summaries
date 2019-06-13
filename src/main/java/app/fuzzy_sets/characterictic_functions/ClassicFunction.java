package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ClassicFunction implements CharacteristicFunction {
    public double calculate(double x) {
        return 1;
    }

    public double getBase() {
        return 0.0;
    }
    public double getArea() {
        return 0.0;
    }


    @Override
    public List<XYChart.Data<Double,Double>> getCharacteristicPoints() {
        return new ArrayList<>();
    }
}
