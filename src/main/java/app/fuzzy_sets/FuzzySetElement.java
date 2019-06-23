package app.fuzzy_sets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FuzzySetElement implements Comparable<FuzzySetElement> {
    private double value;
    private double membershipDegree;


    @Override
    public int compareTo(FuzzySetElement o) {
        return Double.compare(this.value, o.value);
    }
}
