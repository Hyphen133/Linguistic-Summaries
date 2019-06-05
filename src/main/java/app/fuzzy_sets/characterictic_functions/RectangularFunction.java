package app.fuzzy_sets.characterictic_functions;

import lombok.AllArgsConstructor;

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
}
