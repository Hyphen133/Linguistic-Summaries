package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static app.Config.SMALL_POSITIVE;

@AllArgsConstructor
public class RisingFunction implements CharacteristicFunction {
    double a, b;

    public double calculate(double x) {
        if (x <= a)
            return 0;
        else if (a < x && x <= b)
            return (x - a) / (b - a);
        else
            return 1;
    }

    public double getBase() {
        return 1 - a;
    }

    public double getArea() {
        return 0.5*(a+b);
    }

    @Override
    public List<XYChart.Data<Double,Double>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(a, calculate(a)),
                new XYChart.Data<>(b+SMALL_POSITIVE, calculate(b+SMALL_POSITIVE))
        );
    }
}
