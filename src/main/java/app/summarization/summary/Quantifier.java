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
    QuantifierLabel quantifierLabel;

    public Quantifier(QuantifierLabel quantifierLabel) {
        super(quantifierLabel.getCharacteristicFunction(), null);
        this.quantifierLabel = quantifierLabel;
    }

    public double getValue(double x) {
        return quantifierLabel.characteristicFunction.calculate(x);
    }

    public String getName() {
        return quantifierLabel.getName();
    }


    public QuantifierType getQuantifierType() {
        return quantifierLabel.getQuantifierType();
    }
}
