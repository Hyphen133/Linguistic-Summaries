package app.summarization.quality_measures.new_measures;

import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

/*
 * T7
 */
public class DegreeOfQuantifierCardinality implements QualityMeasure {
    public static double getValue(Summary summary) {
        return 1 - summary.getQuantifier().getCardinalityRatio();
    }
}
