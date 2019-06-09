package app.summarization.quality_measures.new_measures;

import app.Utils;
import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;
import app.summarization.summary.TypeOneSummary;
import app.summarization.summary.TypeTwoSummary;

import java.util.List;

/*
 * T9
 */
public class DegreeOfQualifierImprecision implements QualityMeasure {
    public static double getValue(Summary summary) {
        if (!(summary instanceof TypeTwoSummary))
            return 0;

        List<FuzzySet> qualifiers = ((TypeTwoSummary) summary).getQualifierSets();
        double operationResult = 1;
        for (FuzzySet qualifier : qualifiers) {
            operationResult *= qualifier.getDegreeOfFuzziness();
        }
        return 1 - Utils.getRoot(operationResult, qualifiers.size());
    }
}
