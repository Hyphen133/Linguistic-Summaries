package app.fuzzy_sets;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class FuzzySet {

    enum Operation {
        INTERSECTION,
        UNION
    }

    private List<FuzzySetElement> elements; //A
    private CharacteristicFunction characteristicFunction; //u
    private ClassicSet universeOfDiscourse; //X

    public FuzzySet(CharacteristicFunction characteristicFunction, ClassicSet universeOfDiscourse) {
        this.characteristicFunction = characteristicFunction;
        this.universeOfDiscourse = universeOfDiscourse;
        setUpElements();
    }

    public FuzzySet(List<FuzzySetElement> elements, ClassicSet universeOfDiscourse) {
        this.elements = elements;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    private void setUpElements() {
        this.elements = new ArrayList<>();
        for (FuzzySetElement element : universeOfDiscourse.getElements()) {
            this.elements.add(
                    new FuzzySetElement(
                            element.getValue(), characteristicFunction.calculate(element.getValue())));
        }
    }

    boolean setsEquals(FuzzySet object) {
        List<FuzzySetElement> objectElements = object.getElements();
        if (this.elements.size() != object.getElements().size()) {
            return false;
        }
        for (int i = 0; i < elements.size(); i++) {
            if (this.elements.get(i).getMembershipDegree() != objectElements.get(i).getMembershipDegree()) {
                return false;
            }
        }
        return true;
    }

    //cylindric extwnsion

    FuzzySet getComplement() {
        List<FuzzySetElement> complementElements = new ArrayList<>();

        for (FuzzySetElement element : this.elements) {
            complementElements.add(
                    new FuzzySetElement(
                            element.getValue(), 1 - element.getMembershipDegree()));
        }

        return new FuzzySet(complementElements, this.universeOfDiscourse);
    }

    FuzzySet getIntersection(FuzzySet fuzzySetB) {
        return getOperation(fuzzySetB, Operation.INTERSECTION);

    }

    FuzzySet getUnion(FuzzySet fuzzySetB) {
        return getOperation(fuzzySetB, Operation.UNION);
    }

    private FuzzySet getOperation(FuzzySet fuzzySetB, Operation operation) {
        List<FuzzySetElement> fuzzySetBElements = fuzzySetB.getElements();
        List<FuzzySetElement> intersectionElements = new ArrayList<>();

        double membershipDegree;

        for (int i = 0; i < elements.size(); i++) {
            if (Operation.UNION.equals(operation)) {
                membershipDegree = elements.get(i).getMembershipDegree() > fuzzySetBElements.get(i).getMembershipDegree() ?
                        elements.get(i).getMembershipDegree() : fuzzySetBElements.get(i).getMembershipDegree();
            } else {
                membershipDegree = elements.get(i).getMembershipDegree() > fuzzySetBElements.get(i).getMembershipDegree() ?
                        fuzzySetBElements.get(i).getMembershipDegree() : elements.get(i).getMembershipDegree();
            }

            intersectionElements.add(new FuzzySetElement(elements.get(i).getValue(), membershipDegree));
        }
        return new FuzzySet(intersectionElements, this.universeOfDiscourse);
    }

    ClassicSet getAlphaCut(double alpha) {
        if (alpha > 1 || alpha < 0) {
            return null;
        }
        List<FuzzySetElement> alphaCutElements = new ArrayList<>();
        for (FuzzySetElement element : this.elements
        ) {
            if (element.getMembershipDegree() >= alpha) {
                alphaCutElements.add(new FuzzySetElement(element.getValue(), 1));
            }
        }
        return new ClassicSet(alphaCutElements, this.universeOfDiscourse);
    }

    ClassicSet getStrongAlphaCut(double alpha) {
        if (alpha > 1 || alpha < 0) {
            return null;
        }
        List<FuzzySetElement> alphaCutElements = new ArrayList<>();
        for (FuzzySetElement element : this.elements
        ) {
            if (element.getMembershipDegree() > alpha) {
                alphaCutElements.add(new FuzzySetElement(element.getValue(), 1));
            }
        }
        return new ClassicSet(alphaCutElements, this.universeOfDiscourse);
    }

    ClassicSet getSupport() {
        return getStrongAlphaCut(0);
    }





}
