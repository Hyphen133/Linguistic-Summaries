package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static app.Config.SMALL_POSITIVE;

@AllArgsConstructor
public class RisingFunction implements CharacteristicFunction {
    double a, b;
    double c; //end

    public double calculate(double x) {
        if (x <= a)
            return 0;
        else if (a < x && x <= b)
            return (x - a) / (b - a);
        else
            return 1;
    }

    public double getBase() {
        return c - a;
    }

    public double getArea() {
        return 0.5 * (b - a) + (b == 1 ? 0 : (c - b));
    }


    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(a, calculate(a)),
                new XYChart.Data<>(b + SMALL_POSITIVE, calculate(b + SMALL_POSITIVE))
        );
    }
}
