package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Quantifier;
import app.summarization.summary.QuantifierType;
import app.summarization.summary.Summary;
import app.summarization.summary.TypeOneSummary;

/*
 * T1
 */
public class DegreeOfTruth implements QualityMeasure {
    public static double getValue(Summary summary) {
//        double operationResult = 0;
//        operationResult = ((TypeOneSummary) summary).getMembershipDegree();
//        if (QuantifierType.ABSOLUTE.equals(summary.getQuantifierType())) {
//            operationResult /= summary.getSubjectAmount();
//        }
//
//        //TODO drugiego typu
//        return summary.getQuantifier().getValue((operationResult));
        return 0.0;
    }
}
