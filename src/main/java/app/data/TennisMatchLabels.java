package app.data;


import app.fuzzy_sets.FuzzySetElement;
import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.fuzzy_sets.characterictic_functions.FallingFunction;
import app.fuzzy_sets.characterictic_functions.RisingFunction;
import app.fuzzy_sets.characterictic_functions.TriangularFunction;
import app.summarization.quality_measures.QualityMeasure;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TennisMatchLabels {

    enum GamesDifference {
        //max= 15, average 4.94
        ALMOST_NONE("ALMOST NONE", new FallingFunction(1, 2)),
        SMALL("SMALL", new TriangularFunction(1.5, 2.5, 4)),
        NORMAL(" NORMAL", new TriangularFunction(3, 6, 8)),
        BIG("BIG", new RisingFunction(7, 9));

        String name;
        CharacteristicFunction characteristicFunction;

        GamesDifference(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(GamesDifference.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum Aces {
        //max=53 average 5.46
        ALMOST_NONE("ALMOST NONE", new FallingFunction(1, 2)),
        FEW("FEW", new TriangularFunction(1.5, 2.5, 4.5)),
        SEVERAL("SEVERAL", new TriangularFunction(4, 6, 9)),
        MANY("MANY", new RisingFunction(8, 14));

        String name;
        CharacteristicFunction characteristicFunction;

        Aces(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(Aces.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum DoubleFaults {
        //max=20 average 2.94
        ALMOST_NONE("ALMOST NONE", new FallingFunction(0.5, 1)),
        FEW("FEW", new TriangularFunction(0.5, 1.5, 3)),
        SEVERAL("SEVERAL", new TriangularFunction(2, 3, 7)),
        MANY("MANY", new RisingFunction(5, 10));

        String name;
        CharacteristicFunction characteristicFunction;

        DoubleFaults(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(DoubleFaults.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    public enum AverageSetDurationInMinutes {
        //max = 655 average 40.03
        VERY_SHORT("VERY SHORT", new FallingFunction(5, 15)),
        SHORT("SHORT", new TriangularFunction(10, 25, 35)),
        NORMAL("NORMAL", new TriangularFunction(30, 40, 50)),
        LONG("LONG", new TriangularFunction(45, 60, 120)),
        VERY_LONG("VERY LONG", new RisingFunction(80, 100));

        String name;
        CharacteristicFunction characteristicFunction;

        AverageSetDurationInMinutes(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(AverageSetDurationInMinutes.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum SuccessfulBreakPointsPercentage {
        //max= 0.5 average 0.33
        VERY_LOW("VERY LOW", new FallingFunction(0.1, 0.2)),
        LOW("LOW", new TriangularFunction(0.15, 0.25, 0.3)),
        AVERAGE("AVERAGE", new TriangularFunction(0.28, 0.33, 0.4)),
        HIGH("HIGH", new TriangularFunction(0.35, 0.5, 0.65)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.6, 0.8));

        String name;
        CharacteristicFunction characteristicFunction;

        SuccessfulBreakPointsPercentage(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(SuccessfulBreakPointsPercentage.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum FirstServeWonPointsPercentage {
        //max = 1.0 average 0.71
        VERY_LOW("VERY LOW", new FallingFunction(0.1, 0.3)),
        LOW("LOW", new TriangularFunction(0.2, 0.3, 0.5)),
        AVERAGE("AVERAGE", new TriangularFunction(0.45, 0.7, 0.8)),
        HIGH("HIGH", new TriangularFunction(0.75, 0.82, 0.9)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.85, 0.92));

        String name;
        CharacteristicFunction characteristicFunction;

        FirstServeWonPointsPercentage(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(FirstServeWonPointsPercentage.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum SecondServeWonPointsPercentage {
        //max = 1.0 average 0.51
        VERY_LOW("VERY LOW", new FallingFunction(0.1, 0.2)),
        LOW("LOW", new TriangularFunction(0.18, 0.3, 0.4)),
        AVERAGE("AVERAGE", new TriangularFunction(0.38, 0.51, 0.78)),
        HIGH("HIGH", new TriangularFunction(0.75, 0.8, 0.9)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.85, 0.90));

        String name;
        CharacteristicFunction characteristicFunction;

        SecondServeWonPointsPercentage(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(SecondServeWonPointsPercentage.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum ReturnWonPointsPercentage {
        //max=0.88 average 0.37
        VERY_LOW("VERY LOW", new FallingFunction(0.1, 0.2)),
        LOW("LOW", new TriangularFunction(0.13, 0.25, 0.32)),
        AVERAGE("AVERAGE", new TriangularFunction(0.28, 0.37, 0.5)),
        HIGH("HIGH", new TriangularFunction(0.45, 0.6, 0.75)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.7, 0.8));

        CharacteristicFunction characteristicFunction;
        String name;

        ReturnWonPointsPercentage(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(ReturnWonPointsPercentage.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum FirstServicePercentage {
        //max = 1.0 average 0.61
        VERY_LOW("VERY LOW", new FallingFunction(0.15, 0.25)),
        LOW("LOW", new TriangularFunction(0.2, 0.35, 0.45)),
        AVERAGE("AVERAGE", new TriangularFunction(0.42, 0.61, 0.75)),
        HIGH("HIGH", new TriangularFunction(0.7, 0.8, 0.9)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.85, 0.95));

        String name;
        CharacteristicFunction characteristicFunction;

        FirstServicePercentage(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(FirstServicePercentage.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }

    enum TieBreaksWon {
        //max=3 average 0.21
        ALMOST_NONE("ALMOST NONE", new FallingFunction(0, 1)),
        SEVERAL("SEVERAL", new TriangularFunction(0.5, 1, 2)),
        MANY("MANY", new RisingFunction(1.5, 2));

        String name;
        CharacteristicFunction characteristicFunction;

        TieBreaksWon(String name, CharacteristicFunction characteristicFunction) {
            this.name = name;
            this.characteristicFunction = characteristicFunction;
        }

        public String getName() {
            return name;
        }

        public CharacteristicFunction getCharacteristicFunction() {
            return characteristicFunction;
        }

        public static HashMap<String, CharacteristicFunction> getMap() {
            HashMap<String, CharacteristicFunction> result = new HashMap<>();
            Arrays.stream(TieBreaksWon.values())
                    .forEach(g -> result.put(g.getName(), g.getCharacteristicFunction()));
            return result;
        }
    }
}