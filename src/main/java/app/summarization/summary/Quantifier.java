package app.summarization.summary;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;

// Defines set of quantifiers accessed by label
// size in case of quantifier
//about 100 -> function
public class Quantifier {
    private String name;
    private CharacteristicFunction characteristicFunction;
    private QuantifierType quantifierType;

    public Quantifier(String name, CharacteristicFunction characteristicFunction, QuantifierType quantifierType) {
        this.name = name;
        this.characteristicFunction = characteristicFunction;
        this.quantifierType = quantifierType;
    }

    public double getValue(double x){
        return characteristicFunction.calculate(x);
    }

    public String getName() {
        return name;
    }
}
