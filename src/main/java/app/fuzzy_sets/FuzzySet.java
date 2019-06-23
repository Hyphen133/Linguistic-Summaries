package app.fuzzy_sets;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class FuzzySet {

    protected List<FuzzySetElement> elements; //A
    protected ClassicSet classicSet; //X
    protected CharacteristicFunction characteristicFunction;

    protected double universeOfDiscourse;

    public FuzzySet(CharacteristicFunction characteristicFunction, ClassicSet classicSet) {
        this.classicSet = classicSet;
        this.characteristicFunction = characteristicFunction;
        setUpElements(characteristicFunction);

        this.universeOfDiscourse = getMaxValue();
    }

    public FuzzySet(List<FuzzySetElement> elements, ClassicSet classicSet) {
        this.elements = elements;
        this.classicSet = classicSet;
    }

    protected void setUpElements(CharacteristicFunction characteristicFunction) {
        elements = classicSet.getElements().stream()
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

    public FuzzySet getComplement() {
        List<FuzzySetElement> complementElements;

        complementElements = this.elements.stream()
                .map(e -> new FuzzySetElement(e.getValue(), 1 - e.getMembershipDegree()))
                .collect(Collectors.toList());

        return new FuzzySet(complementElements, this.classicSet);
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

    public ClassicSet getSupportForElements() {
        return getStrongAlphaCut(0);
    }

    public double getSupport() {
        return this.characteristicFunction.getBase();
    }

    public ClassicSet getCore() {
        return getAlphaCut(1);
    }

    public double getCardinalityForElements() {
        return elements.stream().
                mapToDouble(e -> e.getMembershipDegree())
                .sum();
    }

    public double getCardinality() {
        return characteristicFunction.getArea();
    }

    public double getDegreeOfFuzzinessForElements() {
        return (double) this.getSupportForElements().getSize() / (double) this.classicSet.getSize();
    }

    public double getDegreeOfFuzziness() {
        return this.characteristicFunction.getBase() / this.universeOfDiscourse;
    }

    public boolean isEmpty() {
        if (this.elements.isEmpty())
            return true;
        else return getCardinalityForElements() == 0;
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

    public double getCardinalityRatioForElements() {
        return getCardinalityForElements() / classicSet.getSize();
    }

    public double getCardinalityRatio() {
        return getCardinality() / this.universeOfDiscourse;
    }

    public boolean isConvex() {
        List<FuzzySetElement> copy = new ArrayList<>(this.elements);
        Collections.sort(copy);
        boolean falling = false;
        for (int i = 0; i < elements.size() - 2; i++) {
            if (!falling) {
                if (elements.get(i).getMembershipDegree() > elements.get(i + 1).getMembershipDegree()) {
                    falling = true;
                }
            } else {
                if (elements.get(i).getMembershipDegree() < elements.get(i + 1).getMembershipDegree()) {
                    return false;
                }
            }

        }
        return true;
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

    private double getMaxValue() {
        List<FuzzySetElement> copy = new ArrayList<>(this.elements);
        Collections.sort(copy);
        return copy.get(classicSet.getSize() - 1).getValue();
    }

    public double getUniverseOfDiscourse() {
        return universeOfDiscourse;
    }

}
