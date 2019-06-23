package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static app.Config.SMALL_POSITIVE;

@AllArgsConstructor
public class FallingFunction implements CharacteristicFunction {
    double a, b;

    public double calculate(double x) {
        if (x <= a)
            return 1;
        else if (a < x && x <= b)
            return (b - x) / (b - a);
        else
            return 0;
    }

    public double getBase() {
        return b;
    }

    public double getArea() {
        return 0.5 * (b - a) + Math.max(0, a);
    }

    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        List<XYChart.Data<Number, Number>> data = Arrays.asList(
                new XYChart.Data<>(a, calculate(a)),
                new XYChart.Data<>(b + SMALL_POSITIVE, calculate(b + SMALL_POSITIVE)));

        return data;
    }
}
