package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;

// Defines set of quantifiers accessed by label
// size in case of quantifier
//about 100 -> function
public class Quantifier extends FuzzySet {
    QuantifierLabel quantifierLabel;

    public Quantifier(QuantifierLabel quantifierLabel) {
        this.quantifierLabel = quantifierLabel;
        this.characteristicFunction = quantifierLabel.getCharacteristicFunction();

        if (quantifierLabel.getQuantifierType().equals(QuantifierType.RELATIVE))
            this.universeOfDiscourse = 1.0;
        else
            this.universeOfDiscourse = 10000;
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

    public QuantifierLabel getQuantifierLabel() {
        return quantifierLabel;
    }

}
