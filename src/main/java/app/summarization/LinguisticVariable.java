package app.summarization;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, CharacteristicFunction> labelCharacteristicFunctionMap;

    private ClassicSet universeOfDisclouse;

    public LinguisticVariable(String name, Map<String, CharacteristicFunction> labelCharacteristicFunctionMap, ClassicSet universeOfDisclouse) {
        this.name = name;
        this.labelCharacteristicFunctionMap = labelCharacteristicFunctionMap;
        this.universeOfDisclouse = universeOfDisclouse;
    }

    public FuzzySet getFuzzySetForLabel(ClassicSet universeOfDiscourse, String label) {
        return new FuzzySet(labelCharacteristicFunctionMap.get(label), universeOfDiscourse);
    }

    public String print(String tag) {
        return tag + " " + name;
    }

    public ClassicSet getUniverseOfDisclouse() {
        return universeOfDisclouse;
    }
}
