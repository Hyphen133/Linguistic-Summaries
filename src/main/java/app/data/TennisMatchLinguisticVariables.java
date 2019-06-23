package app.data;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySetElement;
import app.summarization.LinguisticVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TennisMatchLinguisticVariables {
    enum ColumnName {
        GAMES_DIFFERENCE("GAMES DIFFERENCE"),
        ACES("ACES"),
        DOUBLE_FAULTS("DOUBLE FAULTS"),
        AVERAGE_SET_DURATION_IN_MINUTES("AVERAGE SET DURATION IN MINUTES"),
        SUCCESSFUL_BREAKPOINTS_PERCENTAGE("SUCCESSFUL BREAKPOINTS PERCENTAGE"),
        FIRST_SERVE_WON_POINTS_PERCENTAGE("FIRST SERVE WON POINTS PERCENTAGE"),
        SECOND_SERVE_WON_POINTS_PERCENTAGE("SECOND SERVE WON POINTS PERCENTAGE"),
        RETURN_WON_POINTS_PERCENTAGE("RETURN WON POINTS PERCENTAGE"),
        FIRST_SERVICE_PERCENTAGE("FIRST SERVICE PERCENTAGE"),
        TIE_BREAKS_WON("TIE BREAKS WON");

        String name;

        ColumnName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static Map<String, LinguisticVariable> getVariables(List<TennisMatch> matches) {
        Map<String, LinguisticVariable> variableMap = new HashMap<>();

        //gamesDifference
        variableMap.put(ColumnName.GAMES_DIFFERENCE.getName(),
                new LinguisticVariable(ColumnName.GAMES_DIFFERENCE.getName(),
                        TennisMatchLabels.GamesDifference.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getGamesDifference(), 1))
                                .collect(Collectors.toList()))));

        //aces
        variableMap.put(ColumnName.ACES.getName(),
                new LinguisticVariable(ColumnName.ACES.getName(),
                        TennisMatchLabels.Aces.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getAces(), 1))
                                .collect(Collectors.toList()))));

        //doubleFaults;
        variableMap.put(ColumnName.DOUBLE_FAULTS.getName(),
                new LinguisticVariable(ColumnName.DOUBLE_FAULTS.getName(),
                        TennisMatchLabels.DoubleFaults.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getDoubleFaults(), 1))
                                .collect(Collectors.toList()))));

        //averageSetDurationInMinutes;
        variableMap.put(ColumnName.AVERAGE_SET_DURATION_IN_MINUTES.getName(),
                new LinguisticVariable(ColumnName.AVERAGE_SET_DURATION_IN_MINUTES.getName(),
                        TennisMatchLabels.AverageSetDurationInMinutes.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getAverageSetDurationInMinutes(), 1))
                                .collect(Collectors.toList()))));

        //double successfulBreakPointsPercentage;
        variableMap.put(ColumnName.SUCCESSFUL_BREAKPOINTS_PERCENTAGE.getName(),
                new LinguisticVariable(ColumnName.SUCCESSFUL_BREAKPOINTS_PERCENTAGE.getName(),
                        TennisMatchLabels.SuccessfulBreakPointsPercentage.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getSuccessfulBreakPointsPercentage(), 1))
                                .collect(Collectors.toList()))));

        // firstServeWonPointsPercentage
        variableMap.put(ColumnName.FIRST_SERVE_WON_POINTS_PERCENTAGE.getName(),
                new LinguisticVariable(ColumnName.FIRST_SERVE_WON_POINTS_PERCENTAGE.getName(),
                        TennisMatchLabels.FirstServeWonPointsPercentage.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getFirstServeWonPointsPercentage(), 1))
                                .collect(Collectors.toList()))));

        // secondServeWonPointsPercentage
        variableMap.put(ColumnName.SECOND_SERVE_WON_POINTS_PERCENTAGE.getName(),
                new LinguisticVariable(ColumnName.SECOND_SERVE_WON_POINTS_PERCENTAGE.getName(),
                        TennisMatchLabels.SecondServeWonPointsPercentage.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getSecondServeWonPointsPercentage(), 1))
                                .collect(Collectors.toList()))));

        //returnWonPointsPercentage
        variableMap.put(ColumnName.RETURN_WON_POINTS_PERCENTAGE.getName(),
                new LinguisticVariable(ColumnName.RETURN_WON_POINTS_PERCENTAGE.getName(),
                        TennisMatchLabels.ReturnWonPointsPercentage.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getReturnWonPointsPercentage(), 1))
                                .collect(Collectors.toList()))));

        //firstServicePercentage
        variableMap.put(ColumnName.FIRST_SERVICE_PERCENTAGE.getName(),
                new LinguisticVariable(ColumnName.FIRST_SERVICE_PERCENTAGE.getName(),
                        TennisMatchLabels.FirstServicePercentage.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getFirstServicePercentage(), 1))
                                .collect(Collectors.toList()))));

        //tieBreaksWon
        variableMap.put(ColumnName.TIE_BREAKS_WON.getName(),
                new LinguisticVariable(ColumnName.TIE_BREAKS_WON.getName(),
                        TennisMatchLabels.TieBreaksWon.getMap(),
                        new ClassicSet(matches.stream()
                                .map(m -> new FuzzySetElement(m.getTieBreaksWon(), 1))
                                .collect(Collectors.toList()))));

        return variableMap;
    }
}
