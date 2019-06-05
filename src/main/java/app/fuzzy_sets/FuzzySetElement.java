package app.fuzzy_sets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FuzzySetElement {
    private double value;
    private double membershipDegree;
}
