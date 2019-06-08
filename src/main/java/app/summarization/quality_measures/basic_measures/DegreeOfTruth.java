package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.fuzzy_sets.OperationType;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.*;

import java.util.List;

/*
 * T1
 */
public class DegreeOfTruth implements QualityMeasure {
    public static double getValue(Summary summary) {
        double operationResult = getR(summary);
        if (QuantifierType.ABSOLUTE.equals(summary.getQuantifierType())) {
            operationResult /= summary.getSubjectAmount();
        }
        return summary.getQuantifier().getValue((operationResult));
    }

    public static double getR(Summary summary) {
        if (summary instanceof TypeOneSummary) {
            return getRTypeOne(summary);
        } else {
            return getRTypeTwo(summary);
        }
    }

    public static double getRTypeOne(Summary summary) {
        return summary.getSummarizer().getCardinality();
    }

    public static double getRTypeTwo(Summary summary) {
        FuzzySet summarizer = summary.getSummarizer();
        FuzzySet qualifier = ((TypeTwoSummary) summary).getQualifier();
        return FuzzySetOperations.getIntersection(summarizer, qualifier).getCardinality() / qualifier.getCardinality();
    }

}
