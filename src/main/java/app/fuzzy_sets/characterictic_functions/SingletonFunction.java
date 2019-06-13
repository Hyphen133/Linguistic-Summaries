package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static app.Config.SMALL_POSITIVE;

@AllArgsConstructor
public class SingletonFunction implements CharacteristicFunction {
    double nonZeroElement;

    public double calculate(double x) {
        return x == nonZeroElement ? 1 : 0;
    }

    public double getBase() {
        return 0;
    }

    public double getArea() {
        return 0.0;
    }


    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(nonZeroElement - SMALL_POSITIVE, calculate(nonZeroElement - SMALL_POSITIVE)),
                new XYChart.Data<>(nonZeroElement, calculate(nonZeroElement)),
                new XYChart.Data<>(nonZeroElement + SMALL_POSITIVE, calculate(nonZeroElement + SMALL_POSITIVE))
        );
    }
}
