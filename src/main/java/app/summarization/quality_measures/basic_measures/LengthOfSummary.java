package app.summarization.quality_measures.basic_measures;

import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

/*
 * T5
 */
public class LengthOfSummary implements QualityMeasure {
    public static double getValue(Summary summary) {
        return 2 * Math.pow(0.5, summary.getSummarizerCount());
    }
}
