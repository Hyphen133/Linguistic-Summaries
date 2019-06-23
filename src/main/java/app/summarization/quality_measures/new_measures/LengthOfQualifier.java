package app.summarization.quality_measures.new_measures;

import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;
import app.summarization.summary.TypeTwoSummary;

/*
 * T11
 */
public class LengthOfQualifier implements QualityMeasure {
    public static double getValue(Summary summary) {
        if (!(summary instanceof TypeTwoSummary))
            return 1;
        return 2 * Math.pow(0.5, ((TypeTwoSummary) summary).getQualifierCount());
    }
}
