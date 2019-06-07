package app.summarization.quality_measures.new_measures;

import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

/*
 * T9
 */
public class DegreeOfQualifierImprecision implements QualityMeasure {
    public static double getValue(Summary summary) {
        return 0.0;
    }
}
