package app.fuzzy_sets.characterictic_functions;

import lombok.AllArgsConstructor;

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
        return 0.5 * getBase();
    }
}
