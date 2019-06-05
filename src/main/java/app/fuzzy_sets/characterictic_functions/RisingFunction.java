package app.fuzzy_sets.characterictic_functions;

import lombok.AllArgsConstructor;

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
}
