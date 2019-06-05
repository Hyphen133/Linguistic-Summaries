package app.fuzzy_sets;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FuzzySet {

    protected List<FuzzySetElement> elements; //A
    protected ClassicSet universeOfDiscourse; //X -> do not keep?

    public FuzzySet(CharacteristicFunction characteristicFunction, ClassicSet universeOfDiscourse) {
        this.universeOfDiscourse = universeOfDiscourse;
        setUpElements(characteristicFunction);
    }

    public FuzzySet(List<FuzzySetElement> elements, ClassicSet universeOfDiscourse) {
        this.elements = elements;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    protected void setUpElements(CharacteristicFunction characteristicFunction) {
        elements = universeOfDiscourse.getElements().stream()
                .map(u -> new FuzzySetElement(u.getValue(), characteristicFunction.calculate(u.getValue())))
                .collect(Collectors.toList());
    }

    public int getSize() {
        return this.elements.size();
    }

    public boolean setsAreEquals(FuzzySet object) {
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

    public void getCylyndricExtension() {
        //TODO
    }

    public FuzzySet getComplement() {
        List<FuzzySetElement> complementElements;

        complementElements = this.elements.stream()
                .map(e -> new FuzzySetElement(e.getValue(), 1 - e.getMembershipDegree()))
                .collect(Collectors.toList());

        return new FuzzySet(complementElements, this.universeOfDiscourse);
    }

    public ClassicSet getAlphaCut(double alpha) {
        if (alpha > 1 || alpha < 0) {
            return null;
        }
        List<FuzzySetElement> alphaCutElements;
        alphaCutElements = this.elements.stream()
                .filter(e -> e.getMembershipDegree() >= alpha)
                .map(e -> new FuzzySetElement(e.getValue(), 1))
                .collect(Collectors.toList());
        return new ClassicSet(alphaCutElements);
    }

    public ClassicSet getStrongAlphaCut(double alpha) {
        if (alpha > 1 || alpha < 0) {
            return null;
        }
        List<FuzzySetElement> alphaCutElements;
        alphaCutElements = this.elements.stream()
                .filter(e -> e.getMembershipDegree() > alpha)
                .map(e -> new FuzzySetElement(e.getValue(), 1))
                .collect(Collectors.toList());

        return new ClassicSet(alphaCutElements);
    }

    public ClassicSet getSupport() {
        return getStrongAlphaCut(0);
    }

    public ClassicSet getCore() {
        return getAlphaCut(1);
    }

    public double getCardinality() {
        return elements.stream().
                mapToDouble(e -> e.getMembershipDegree())
                .sum();
    }

    public double getDegreeOfFuziness() {
        return Double.valueOf(this.getSupport().getSize())/ Double.valueOf(this.universeOfDiscourse.getSize());
    }

    public boolean isEmpty() {
        if (this.elements.isEmpty())
            return true;
        else if (getCardinality() == 0)
            return true;
        else
            return false;
    }

    public double getCentroid() {
        double numerator = this.elements.stream().
                mapToDouble(e -> e.getMembershipDegree() * e.getValue())
                .sum();
        double denominator = this.elements.stream().
                mapToDouble(e -> e.getValue())
                .sum();
        return numerator / denominator;
    }

    public double getHeight() {
        return this.elements.stream().
                mapToDouble(e -> e.getMembershipDegree())
                .max()
                .getAsDouble();
    }

    public boolean isNormal() {
        return getHeight() == 1;
    }

    public double getCardinalityRatio() {
        return getCardinality() / universeOfDiscourse.getSize();
    }

    public boolean isConvex() {
        //TODO
        return false;
    }

    public boolean isConcave() {
        return !isConvex();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        this.elements.stream().
                forEach(e -> builder.append(" <" + e.getValue() + " , " + e.getMembershipDegree() + "> "));
        builder.append(" }");
        return builder.toString();
    }
}
