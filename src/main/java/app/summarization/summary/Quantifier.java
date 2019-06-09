package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;

// Defines set of quantifiers accessed by label
// size in case of quantifier
//about 100 -> function
public class Quantifier {
    QuantifierLabel quantifierLabel;

    public Quantifier(QuantifierLabel quantifierLabel) {
        //super(quantifierLabel.getCharacteristicFunction(),);
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
