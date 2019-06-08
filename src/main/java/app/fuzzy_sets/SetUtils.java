package app.fuzzy_sets;

import java.util.ArrayList;
import java.util.List;

public class SetUtils {
    private static ClassicSet percentageSet = null;

    public static ClassicSet getClassicSetForPercentages(){
        if(percentageSet != null){
            return percentageSet;
        }

        List<FuzzySetElement> elements = new ArrayList<>();

        for (double i = 0.0; i <= 1.0; i+= 0.01) {
            elements.add(new FuzzySetElement(i, 1.0));
        }

        percentageSet = new ClassicSet(elements);
        return percentageSet;
    }

    public static ClassicSet minMaxIntegerSet(int min, int max){
        List<FuzzySetElement> elements = new ArrayList<>();

        for (int i = min; i <= max  ; i++) {
            elements.add(new FuzzySetElement(i, 1.0));
        }

        return new ClassicSet(elements);
    }


}
