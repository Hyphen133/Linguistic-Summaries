package app.summarization.quality_measures.new_measures;

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
        if (summary instanceof TypeOneSummary)
            return 1;

        List<FuzzySet> qualifiers = ((TypeTwoSummary) summary).getQualifierSets();
        double operationResult = 1;
        for (FuzzySet qualifier : qualifiers) {
            operationResult *= qualifier.getDegreeOfFuzziness();
        }
        return 1 - Math.pow(operationResult, 1 / qualifiers.size());
    }
}
