package app.fuzzy_sets;

import java.util.ArrayList;
import java.util.List;

public class FuzzySetOperations {



    public static FuzzySet getNegation(FuzzySet fuzzySetA) {
        return fuzzySetA.getComplement();
    }

    public static FuzzySet getIntersection(FuzzySet fuzzySetA, FuzzySet fuzzySetB) {
        return getOperation(fuzzySetA, fuzzySetB, OperationType.INTERSECTION);
    }

    public static FuzzySet getUnion(FuzzySet fuzzySetA, FuzzySet fuzzySetB) {
        return getOperation(fuzzySetA, fuzzySetB, OperationType.UNION);
    }

    public static FuzzySet getOperation(FuzzySet fuzzySetA, FuzzySet fuzzySetB, OperationType operation) {
        List<FuzzySetElement> fuzzySetAElements = fuzzySetA.getElements();
        List<FuzzySetElement> fuzzySetBElements = fuzzySetB.getElements();
        List<FuzzySetElement> operationElements = new ArrayList<>();

        double membershipDegree;

        for (int i = 0; i < fuzzySetAElements.size(); i++) {
            if (OperationType.UNION.equals(operation)) {
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
