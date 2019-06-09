package app.summarization.quality_measures.basic_measures;

import app.Utils;
import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

import java.util.List;

/*
 * T2
 */
public class DegreeOfImprecision implements QualityMeasure {
    public static double getValue(Summary summary) {
        double operationResult = 1;
        List<FuzzySet> summarizers = summary.getSummarizerSets();
        for (FuzzySet summarizer : summarizers
        ) {
            operationResult *= summarizer.getDegreeOfFuzziness();
        }
        return 1 - Utils.getRoot(operationResult, summarizers.size());
    }
}
