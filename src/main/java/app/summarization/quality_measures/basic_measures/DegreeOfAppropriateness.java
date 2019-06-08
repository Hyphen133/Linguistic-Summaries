package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.quality_measures.QualityMeasureEnum;
import app.summarization.summary.Summary;

/*
 * T4
 */
public class DegreeOfAppropriateness implements QualityMeasure {
    public static double getValue(Summary summary) {
        double T3 = QualityMeasureEnum.getValue(QualityMeasureEnum.T3, summary);
        double r = 1;
        for (FuzzySet summarizer : summary.getSummarizerSets()
        ) {
            r *= getR(summarizer, summary);
        }
        return r - T3;
    }

    private static double getR(FuzzySet summarizer, Summary summary) {
        return getG(summarizer) / summary.getSubjectAmount();
    }

    private static double getG(FuzzySet summarizer) {
        return summarizer.getSupport().getSize();
    }
}
