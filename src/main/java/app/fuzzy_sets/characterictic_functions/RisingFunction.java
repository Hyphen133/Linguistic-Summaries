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

    @Override
    public double getBaseAbsolute() {
        return 10000 - a;
    }

    public double getArea() {
        return 0.5 * (b - a) + (b == 1 ? 0 : (1 - b));
    }

    public double getAbsoluteArea() {
        return 0.5 * (b - a) + (b == 1 ? 0 : (10000 - b));
    }

    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(a, calculate(a)),
                new XYChart.Data<>(b + SMALL_POSITIVE, calculate(b + SMALL_POSITIVE))
        );
    }
}
