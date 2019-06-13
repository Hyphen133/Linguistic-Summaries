package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(0,calculate(0))
        );
    }
}
