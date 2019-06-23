package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class TrapezoidalFunction implements CharacteristicFunction {
    double a, b, c, d;

    public double calculate(double x) {
        if (x <= a)
            return 0;
        else if (a < x && x <= b)
            return (x - a) / (b - a);
        else if (b < x && x <= c)
            return 1;
        else if (c < x && x <= d)
            return (d - x) / (d - c);
        else
            return 0;
    }

    public double getBase() {
        return d - a;
    }

    public double getArea() {
        return 0.5 * (b - a) + (c - b) + 0.5 * (d - c);
    }

    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(a, calculate(a)),
                new XYChart.Data<>(b, calculate(b)),
                new XYChart.Data<>(c, calculate(c))
        );
    }
}
