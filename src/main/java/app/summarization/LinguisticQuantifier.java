package app.summarization;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.summarization.LinguisticVariable;

import java.util.HashMap;



// Defines set of quantifiers accessed by tag
// size in case of quantifier
//about 100 -> function
public class LinguisticQuantifier {
    private String name;
    private CharacteristicFunction characteristicFunction;

    public LinguisticQuantifier(String name, CharacteristicFunction characteristicFunction) {
        this.name = name;
        this.characteristicFunction = characteristicFunction;
    }

    public double getValue(double x){
        return characteristicFunction.calculate(x);
    }

    public String getName() {
        return name;
    }
}
