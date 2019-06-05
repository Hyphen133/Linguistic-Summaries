package app.fuzzy_sets;

import java.util.ArrayList;
import java.util.List;

public class FuzzySetOperations {

    enum Operation {
        INTERSECTION,
        UNION
    }

    FuzzySet getIntersection(FuzzySet fuzzySetA, FuzzySet fuzzySetB) {
        return getOperation(fuzzySetA, fuzzySetB, Operation.INTERSECTION);

    }

    FuzzySet getUnion(FuzzySet fuzzySetA, FuzzySet fuzzySetB) {
        return getOperation(fuzzySetA, fuzzySetB, Operation.UNION);
    }

    private FuzzySet getOperation(FuzzySet fuzzySetA, FuzzySet fuzzySetB, Operation operation) {
        List<FuzzySetElement> fuzzySetAElements = fuzzySetB.getElements();
        List<FuzzySetElement> fuzzySetBElements = fuzzySetB.getElements();
        List<FuzzySetElement> operationElements = new ArrayList<>();

        double membershipDegree;
        
        for (int i = 0; i < fuzzySetAElements.size(); i++) {
            if (Operation.UNION.equals(operation)) {
                membershipDegree = fuzzySetAElements.get(i).getMembershipDegree() > fuzzySetBElements.get(i).getMembershipDegree() ?
                        fuzzySetAElements.get(i).getMembershipDegree() : fuzzySetBElements.get(i).getMembershipDegree();
            } else {
                membershipDegree = fuzzySetAElements.get(i).getMembershipDegree() > fuzzySetBElements.get(i).getMembershipDegree() ?
                        fuzzySetBElements.get(i).getMembershipDegree() : fuzzySetAElements.get(i).getMembershipDegree();
            }

            operationElements.add(new FuzzySetElement(fuzzySetAElements.get(i).getValue(), membershipDegree));
        }
        return new FuzzySet(operationElements, fuzzySetA.universeOfDiscourse);
    }

    //TODO  dopełnienie zbiorów rozmytych wg różnych norm trójkątnych
}
