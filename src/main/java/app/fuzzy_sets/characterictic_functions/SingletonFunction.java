package app.fuzzy_sets.characterictic_functions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SingletonFunction implements CharacteristicFunction {
    double nonZeroElement;

    public double calculate(double x) {
        return x == nonZeroElement ? 1 : 0;
    }

    public double getBase() {
        return 0;
    }

    public double getArea() {
        return 0.0;
    }
}
