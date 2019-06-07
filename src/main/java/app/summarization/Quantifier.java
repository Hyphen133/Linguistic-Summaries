package app.summarization;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.summarization.LinguisticVariable;

import java.util.HashMap;



// Defines set of quantifiers accessed by tag
// size in case of quantifier
//about 100 -> function
public class Quantifier {
    enum QuantifierType{
        ABSOLUTE, RELATIVE
    }

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
