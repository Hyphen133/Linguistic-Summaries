package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.QuantifierType;
import app.summarization.summary.Summary;
import app.summarization.summary.TypeTwoSummary;

/*
 * T1
 */
public class DegreeOfTruth implements QualityMeasure {
    public static double getValue(Summary summary) {
        double operationResult = getR(summary);
        if (QuantifierType.RELATIVE.equals(summary.getQuantifierType())) {
            operationResult /= summary.getSubjectAmount();
        }
        return summary.getQuantifier().getValue((operationResult));
    }

    private static double getR(Summary summary) {
        if (summary instanceof TypeTwoSummary) {
            return getRTypeTwo(summary);
        } else {
            return getRTypeOne(summary);
        }
    }

    private static double getRTypeOne(Summary summary) {
        return summary.getSummarizer().getCardinality();
    }

    private static double getRTypeTwo(Summary summary) {
        FuzzySet summarizer = summary.getSummarizer();
        FuzzySet qualifier = ((TypeTwoSummary) summary).getQualifier();
        return FuzzySetOperations.getIntersection(summarizer, qualifier).getCardinality() / qualifier.getCardinality();
    }

}
