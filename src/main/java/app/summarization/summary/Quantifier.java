package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;

// Defines set of quantifiers accessed by label
// size in case of quantifier
//about 100 -> function
public class Quantifier {
    QuantifierLabel quantifierLabel;

    public Quantifier(QuantifierLabel quantifierLabel) {
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

    public double getDegreeOfFuzziness() {
        if (quantifierLabel.getQuantifierType().equals(QuantifierType.RELATIVE)) {
            return quantifierLabel.getCharacteristicFunction().getBase();
        }
        return quantifierLabel.getCharacteristicFunction().getBaseAbsolute() / 10000;
    }

    public double getCardinalityRatio() {
        if (quantifierLabel.getQuantifierType().equals(QuantifierType.RELATIVE)) {
            return quantifierLabel.getCharacteristicFunction().getArea();
        }
        return quantifierLabel.getCharacteristicFunction().getAbsoluteArea() / 10000;
    }

    public QuantifierLabel getQuantifierLabel() {
        return quantifierLabel;
    }
}
