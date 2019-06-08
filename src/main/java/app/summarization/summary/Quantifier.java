package app.summarization.summary;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetElement;
import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;

import java.util.List;

// Defines set of quantifiers accessed by label
// size in case of quantifier
//about 100 -> function
public class Quantifier extends FuzzySet {
    private String name;
    private QuantifierType quantifierType;
    private CharacteristicFunction characteristicFunction;

    public Quantifier(CharacteristicFunction characteristicFunction, ClassicSet universeOfDiscourse, String name, QuantifierType quantifierType) {
        super(characteristicFunction, universeOfDiscourse);
        this.characteristicFunction = characteristicFunction;
        this.name = name;
        this.quantifierType = quantifierType;
    }

    public double getValue(double x) {
        return characteristicFunction.calculate(x);
    }

    public String getName() {
        return name;
    }


    public QuantifierType getQuantifierType() {
        return quantifierType;
    }
}
