package app.summarization.summary;

import app.summarization.quality_measures.basic_measures.*;
import app.summarization.quality_measures.new_measures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodnessOfSummary {

    public static double getGoodnessOfSummary(Summary summary) {
        double weight = 0.2;
        List<Double> measures= new ArrayList<>();
        measures.addAll(Arrays.asList(DegreeOfTruth.getValue(summary),
                DegreeOfImprecision.getValue(summary),
                DegreeOfCoverage.getValue(summary),
                DegreeOfAppropriatness.getValue(summary),
                LengthOfSummary.getValue(summary)));
        return getWeightedAverage(measures,weight);
    }

    public static double getExtendedGoodnessOfSummary(Summary summary) {
        double weight = 0.1;
        List<Double> measures= new ArrayList<>();
        measures.addAll(Arrays.asList(DegreeOfTruth.getValue(summary),
                DegreeOfImprecision.getValue(summary),
                DegreeOfCoverage.getValue(summary),
                DegreeOfAppropriatness.getValue(summary),
                LengthOfSummary.getValue(summary),
                DegreeOfQuantifierImprecision.getValue(summary),
                DegreeOfQuantifierCardinality.getValue(summary),
                DegreeOfSummarizerCardinality.getValue(summary),
                DegreeOfQualifierImprecision.getValue(summary),
                DegreeOfQualifierCardinality.getValue(summary)));
        return getWeightedAverage(measures,weight);
    }

    private static double getWeightedAverage(List<Double> measures, double weight) {
        return measures.stream()
                .mapToDouble(m -> m * weight)
                .sum();
    }
}
