package app.summarization;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * L - the name of variable
 * H - set of labels
 * X - universe of discourse
 * G - syntactic rule
 * K - semantic rule
 */
public class LinguisticVariable {
    private String name;

    // mapping label to function
    private HashMap<String, CharacteristicFunction> labelCharacteristicFunctionMap;

    private ClassicSet universeOfDiscourse;

    public LinguisticVariable(String name, HashMap<String, CharacteristicFunction> labelCharacteristicFunctionMap, ClassicSet universeOfDiscourse) {
        this.name = name;
        this.labelCharacteristicFunctionMap = labelCharacteristicFunctionMap;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public FuzzySet getFuzzySetForLabel(ClassicSet universeOfDiscourse, String label) {
        return new FuzzySet(labelCharacteristicFunctionMap.get(label), universeOfDiscourse);
    }

    public String print(String label) {
        return label + " " + name;
    }

    public ClassicSet getUniverseOfDiscourse() {
        return universeOfDiscourse;
    }

    public List<String> getLabels(){
        return new ArrayList<>(labelCharacteristicFunctionMap.keySet());
    }
}
