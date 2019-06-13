package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class TriangularFunction implements CharacteristicFunction {
    double a, b, c;

    public double calculate(double x) {
        if (x <= a)
            return 0;
        else if (a < x && x <= b)
            return (x - a) / (b - a);
        else if (b < x && x <= c)
            return (c - x) / (c - b);
        else
            return 0;
    }

    public double getBase() {
        return c - a;
    }

    public double getArea() {
        return 0.5*getBase();
    }


    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(a,calculate(a)),
                new XYChart.Data<>(b,calculate(b)),
                new XYChart.Data<>(c,calculate(c))
        );
    }
}
