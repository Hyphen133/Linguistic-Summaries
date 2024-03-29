package app.fuzzy_sets;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;

import java.util.List;
import java.util.stream.Collectors;

public class ClassicSet extends FuzzySet {

    public ClassicSet(List<FuzzySetElement> elements) {
        this.elements = elements;
    }

    public ClassicSet(List<Double> elements, CharacteristicFunction characteristicFunction) {
        setUpElements(characteristicFunction, elements);
    }

    protected void setUpElements(CharacteristicFunction characteristicFunction, List<Double> elements) {
        this.elements = elements.stream()
                .map(u -> new FuzzySetElement(u, characteristicFunction.calculate(u)))
                .collect(Collectors.toList());
    }

}
