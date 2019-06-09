package app.summarization.quality_measures.new_measures;

import app.Utils;
import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

import java.util.List;

/*
 * T8
 */
public class DegreeOfSummarizerCardinality implements QualityMeasure {
    public static double getValue(Summary summary) {
        List<FuzzySet> summarizers = summary.getSummarizerSets();
        double operationResult = 1;
        for (FuzzySet summarizer : summarizers) {
            operationResult *= summarizer.getCardinalityRatio();
        }
        return 1 - Utils.getRoot(operationResult, summarizers.size());
    }}
