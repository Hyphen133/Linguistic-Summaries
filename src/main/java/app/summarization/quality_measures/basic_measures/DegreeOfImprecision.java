package app.summarization.quality_measures.basic_measures;

import app.Utils;
import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;

import java.util.List;
/*
 * T2
 */
public class DegreeOfImprecision implements QualityMeasure {
    public static double getValue(Summary summary) {
        return 0.0;
    }
}
