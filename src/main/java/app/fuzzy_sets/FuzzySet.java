package app.fuzzy_sets;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class FuzzySet {

    protected List<FuzzySetElement> elements; //A
    protected CharacteristicFunction characteristicFunction; //u
    protected ClassicSet universeOfDiscourse; //X -> do not keep?

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

    public int getSize() {
        return this.elements.size();
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

    void getCylyndricExtension(){
        //TODO
    }

    FuzzySet getComplement() {
        List<FuzzySetElement> complementElements = new ArrayList<>();

        for (FuzzySetElement element : this.elements) {
            complementElements.add(
                    new FuzzySetElement(
                            element.getValue(), 1 - element.getMembershipDegree()));
        }

        return new FuzzySet(complementElements, this.universeOfDiscourse);
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
        return new ClassicSet(alphaCutElements);
    }

    ClassicSet getStrongAlphaCut(double alpha) {
        if (alpha > 1 || alpha < 0) {
            return null;
        }
        List<FuzzySetElement> alphaCutElements = new ArrayList<>();
        for (FuzzySetElement element : this.elements) {
            if (element.getMembershipDegree() > alpha) {
                alphaCutElements.add(new FuzzySetElement(element.getValue(), 1));
            }
        }
        return new ClassicSet(alphaCutElements);
    }

    ClassicSet getSupport() {
        return getStrongAlphaCut(0);
    }

    ClassicSet getCore() {
        return getStrongAlphaCut(1);
    }

    double getCardinality() {
        double cardinality = 0;
        for (FuzzySetElement element : this.elements) {
            cardinality += element.getMembershipDegree();
        }
        return cardinality;
    }

    double getDegreeOfFuziness() {
        return this.getSize() / this.universeOfDiscourse.getSize();
    }

    boolean isEmpty() {
        if (this.elements.isEmpty())
            return true;
        else if (getCardinality() == 0)
            return true;
        else
            return false;
    }

    double getCentroid() {
        double numerator = 0;
        double denominator = 0;
        for (FuzzySetElement element : this.elements) {
            numerator += element.getValue() * element.getMembershipDegree();
            denominator += element.getMembershipDegree();
        }
        return numerator / denominator;
    }

    double getHeight() {
        double height = 0;
        for (FuzzySetElement element : this.elements) {
            if (element.getMembershipDegree() > height)
                height = element.getMembershipDegree();
        }
        return height;
    }

    boolean isNormal() {
        return getHeight() == 1;
    }

    double getCardinalityRatio() {
        return getCardinality() / universeOfDiscourse.getSize();
    }

    boolean isConvex() {
        //TODO
        return false;
    }

    boolean isConcave() {
        return !isConvex();
    }
}
