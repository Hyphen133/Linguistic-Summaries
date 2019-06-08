package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

import java.util.ArrayList;
import java.util.List;

/*
 * T5
 */
public class LengthOfSummary implements QualityMeasure {
    public static double getValue(Summary summary) {
        return 2 * Math.pow(0.5, summary.getSummarizerCount());
    }
}
