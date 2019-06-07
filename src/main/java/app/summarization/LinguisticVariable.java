package app.summarization;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;

import java.util.HashMap;
import java.util.List;

public class LinguisticVariable {
    private String name;

    // age
    // f.ex young -> function1 ; old -> function2 ...
    // in case of quantifiers
    private HashMap<String, CharacteristicFunction> tagCharacteristicFunctionMap;

    public LinguisticVariable(String name, HashMap<String, CharacteristicFunction> tagCharacteristicFunctionMap) {
        this.name = name;
        this.tagCharacteristicFunctionMap = tagCharacteristicFunctionMap;
    }

    public FuzzySet getFuzzySetForTag(ClassicSet universeOfDiscourse, String tag) {
        return new FuzzySet(tagCharacteristicFunctionMap.get(tag), universeOfDiscourse);
    }

    public String print(String tag){
        return tag + " " + name;
    }

}
