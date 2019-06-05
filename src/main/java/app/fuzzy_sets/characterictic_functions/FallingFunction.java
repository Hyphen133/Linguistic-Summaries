package app.fuzzy_sets.characterictic_functions;

import lombok.AllArgsConstructor;

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
}
