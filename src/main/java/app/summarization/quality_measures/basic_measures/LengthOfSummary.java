package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

import java.util.ArrayList;
import java.util.List;

/*
 * T4
 */
public class LengthOfSummary implements QualityMeasure {
    public static double getValue(Summary summary) {
        //will be taken from suumary
        List<FuzzySet> summarizator = new ArrayList<>();

        return 2 * Math.pow(0.5, summarizator.size());
    }
}
