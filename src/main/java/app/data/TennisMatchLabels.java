package app.data;


import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.fuzzy_sets.characterictic_functions.FallingFunction;
import app.fuzzy_sets.characterictic_functions.RisingFunction;
import app.fuzzy_sets.characterictic_functions.TriangularFunction;

import java.util.Arrays;
import java.util.HashMap;

public class TennisMatchLabels {

    enum GamesDifference {
        //max= 15, average 4.94
        ALMOST_NONE("ALMOST NONE", new FallingFunction(1, 2.5)),
        SMALL("SMALL", new TriangularFunction(1.4, 2.5, 3.78)),
        NORMAL(" NORMAL", new TriangularFunction(3, 6, 8)),
        BIG("BIG", new RisingFunction(6, 9, 15));

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
        FEW("FEW", new TriangularFunction(1.5, 2.5, 5)),
        SEVERAL("SEVERAL", new TriangularFunction(3.8, 6, 9)),
        MANY("MANY", new RisingFunction(8, 14, 53));

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
        SEVERAL("SEVERAL", new TriangularFunction(2, 3, 9)),
        MANY("MANY", new RisingFunction(5, 10, 20));

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
        SHORT("SHORT", new TriangularFunction(10, 25, 40)),
        NORMAL("NORMAL", new TriangularFunction(28, 40, 53)),
        LONG("LONG", new TriangularFunction(45, 60, 120)),
        VERY_LONG("VERY LONG", new RisingFunction(80, 100, 655));

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
        LOW("LOW", new TriangularFunction(0.10, 0.25, 0.32)),
        AVERAGE("AVERAGE", new TriangularFunction(0.28, 0.33, 0.45)),
        HIGH("HIGH", new TriangularFunction(0.35, 0.5, 0.65)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.55, 0.8, 1.0));

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
        AVERAGE("AVERAGE", new TriangularFunction(0.4, 0.7, 0.8)),
        HIGH("HIGH", new TriangularFunction(0.72, 0.82, 0.95)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.85, 0.92, 1.0));

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
        VERY_LOW("VERY LOW", new FallingFunction(0.1, 0.25)),
        LOW("LOW", new TriangularFunction(0.13, 0.3, 0.45)),
        AVERAGE("AVERAGE", new TriangularFunction(0.35, 0.51, 0.78)),
        HIGH("HIGH", new TriangularFunction(0.7, 0.8, 0.9)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.85, 0.90, 1.0));

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
        VERY_LOW("VERY LOW", new FallingFunction(0.1, 0.23)),
        LOW("LOW", new TriangularFunction(0.10, 0.25, 0.33)),
        AVERAGE("AVERAGE", new TriangularFunction(0.25, 0.37, 0.52)),
        HIGH("HIGH", new TriangularFunction(0.45, 0.6, 0.9)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.7, 0.8, 1.0));

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
        VERY_LOW("VERY LOW", new FallingFunction(0.15, 0.3)),
        LOW("LOW", new TriangularFunction(0.18, 0.35, 0.5)),
        AVERAGE("AVERAGE", new TriangularFunction(0.4, 0.61, 0.78)),
        HIGH("HIGH", new TriangularFunction(0.7, 0.8, 0.9)),
        VERY_HIGH("VERY HIGH", new RisingFunction(0.85, 0.95, 1.0));

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
        MANY("MANY", new RisingFunction(1.5, 2, 3.0));

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
