package app.fuzzy_sets.characterictic_functions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClassicFunction implements CharacteristicFunction {
    public double calculate(double x) {
        return 1;
    }

    public double getBase() {
        return 0.0;
    }
    public double getArea() {
        return 0.0;
    }
}
