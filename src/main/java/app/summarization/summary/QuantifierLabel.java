package app.summarization.summary;

import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.fuzzy_sets.characterictic_functions.FallingFunction;
import app.fuzzy_sets.characterictic_functions.RisingFunction;
import app.fuzzy_sets.characterictic_functions.TriangularFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

@AllArgsConstructor
@Getter
public class QuantifierLabel {

//    ALMOST_NONE("ALMOST NONE", QuantifierType.RELATIVE, new FallingFunction(0, 0.1)),
//    FEW("FEW", QuantifierType.RELATIVE, new TriangularFunction(0.05,0.15,0.25)),
//    ABOUT_QUARTER("ABOUT QUARTER", QuantifierType.RELATIVE, new TriangularFunction(0.15,0.25,0.35)),
//    SEVERAL("SEVERAL", QuantifierType.RELATIVE, new TriangularFunction(0.3, 0.4, 0.49)),
//    ABOUT_HALF("ABOUT HALF", QuantifierType.RELATIVE, new TriangularFunction(0.44, 0.5, 0.56)),
//    MORE_THEN_HALF("MORE THEN HALF", QuantifierType.RELATIVE, new TriangularFunction(0.50, 0.6, 0.7)),
//    ABOUT_75_PERCENT("ABOUT 75 PERCENT", QuantifierType.RELATIVE, new TriangularFunction(0.65, 0.75, 0.87)),
//    MANY("MANY", QuantifierType.RELATIVE, new TriangularFunction(0.80,0.9,0.95)),
//    ALMOST_ALL("ALMOST ALL", QuantifierType.RELATIVE, new RisingFunction(0.9, 0.98)),
//
//    ABOUT_0("ABOUT 0", QuantifierType.ABSOLUTE, new FallingFunction(500, 1500)),
//    ABOUT_2500("ABOUT 2500", QuantifierType.ABSOLUTE, new TriangularFunction(700, 2500, 4000)),
//    ABOUT_5000("ABOUT 5000", QuantifierType.ABSOLUTE, new TriangularFunction(3400, 5000, 6800)),
//    ABOUT_7500("ABOUT 7500", QuantifierType.ABSOLUTE, new TriangularFunction(6300, 7500, 8900)),
//    ABOUT_10000("ABOUT 10000", QuantifierType.ABSOLUTE, new RisingFunction(8400,9000));


    //values()
    //getMap()
    //valueOf()


    String name;
    QuantifierType quantifierType;
    CharacteristicFunction characteristicFunction;


//    public static HashMap<String, Quantifier> getMap() {
//        HashMap<String, Quantifier> result = new HashMap<>();
//        Arrays.stream(QuantifierLabel.values())
//                .forEach(g -> result.put(g.getName(), new Quantifier(g)));
//        return result;
//    }


}
