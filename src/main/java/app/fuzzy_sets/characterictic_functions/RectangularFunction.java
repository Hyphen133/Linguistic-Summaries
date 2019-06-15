package app.fuzzy_sets.characterictic_functions;

import javafx.scene.chart.XYChart;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static app.Config.SMALL_POSITIVE;

@AllArgsConstructor
public class RectangularFunction implements CharacteristicFunction {
    double a, b;

    public double calculate(double x) {
        if (x <= a)
            return 0;
        else if (a < x && x < b)
            return 1;
        else
            return 0;
    }

    public double getBase() {
        return b - a;
    }

    @Override
    public double getBaseAbsolute() {
        return getBase();
    }

    public double getArea() {
        return a*b;
    }


    @Override
    public List<XYChart.Data<Number, Number>> getCharacteristicPoints() {
        return Arrays.asList(
                new XYChart.Data<>(a,calculate(a)),
                new XYChart.Data<>(a+SMALL_POSITIVE, calculate(a+SMALL_POSITIVE)),
                new XYChart.Data<>(b-SMALL_POSITIVE, calculate(b-SMALL_POSITIVE)),
                new XYChart.Data<>(b, calculate(b))
        );
    }
}
