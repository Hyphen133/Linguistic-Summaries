package app.summarization.summary;

import app.fuzzy_sets.characterictic_functions.FallingFunction;
import app.fuzzy_sets.characterictic_functions.RisingFunction;
import app.fuzzy_sets.characterictic_functions.TriangularFunction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class AllQuantifiers {

    private static HashMap<String, QuantifierLabel> quantifiers = null;


    private static void load() {
        quantifiers = new HashMap<>();

        quantifiers.put("ALMOST_NONE", new QuantifierLabel("ALMOST NONE", QuantifierType.RELATIVE, new FallingFunction(0, 0.1)));
        quantifiers.put("FEW", new QuantifierLabel("FEW", QuantifierType.RELATIVE, new TriangularFunction(0.05, 0.15, 0.25)));
        quantifiers.put("ABOUT_QUARTER", new QuantifierLabel("ABOUT QUARTER", QuantifierType.RELATIVE, new TriangularFunction(0.15, 0.25, 0.35)));
        quantifiers.put("SEVERAL", new QuantifierLabel("SEVERAL", QuantifierType.RELATIVE, new TriangularFunction(0.3, 0.4, 0.49)));
        quantifiers.put("ABOUT_HALF", new QuantifierLabel("ABOUT HALF", QuantifierType.RELATIVE, new TriangularFunction(0.44, 0.5, 0.56)));
        quantifiers.put("MORE_THEN_HALF", new QuantifierLabel("MORE THEN HALF", QuantifierType.RELATIVE, new TriangularFunction(0.50, 0.6, 0.7)));
        quantifiers.put("ABOUT_75_PERCENT", new QuantifierLabel("ABOUT 75 PERCENT", QuantifierType.RELATIVE, new TriangularFunction(0.65, 0.75, 0.87)));
        quantifiers.put("MANY", new QuantifierLabel("MANY", QuantifierType.RELATIVE, new TriangularFunction(0.80, 0.9, 0.95)));
        quantifiers.put("ALMOST_ALL", new QuantifierLabel("ALMOST ALL", QuantifierType.RELATIVE, new RisingFunction(0.9, 0.98)));


        quantifiers.put("ABOUT_0", new QuantifierLabel("ABOUT 0", QuantifierType.ABSOLUTE, new FallingFunction(500, 1500)));
        quantifiers.put("ABOUT_2500", new QuantifierLabel("ABOUT 2500", QuantifierType.ABSOLUTE, new TriangularFunction(700, 2500, 4000)));
        quantifiers.put("ABOUT_5000", new QuantifierLabel("ABOUT 5000", QuantifierType.ABSOLUTE, new TriangularFunction(3400, 5000, 6800)));
        quantifiers.put("ABOUT_7500", new QuantifierLabel("ABOUT 7500", QuantifierType.ABSOLUTE, new TriangularFunction(6300, 7500, 8900)));
        quantifiers.put("ABOUT_10000", new QuantifierLabel("ABOUT 10000", QuantifierType.ABSOLUTE, new RisingFunction(8400, 9000)));

    }

    public static HashMap<String, Quantifier> getQuantifiers() {
        if (quantifiers == null) {
            load();
        }

        HashMap<String, Quantifier> result = new HashMap<>();

        for (String s : quantifiers.keySet()) {
            result.put(s, new Quantifier(quantifiers.get(s)));
        }

        return result;
    }

    public static void addQuantifierLabel(String key, QuantifierLabel label) {
        quantifiers.put(key, label);
    }

    public static QuantifierLabel valueOf(String name) {
        return quantifiers.get(name);
    }

}
