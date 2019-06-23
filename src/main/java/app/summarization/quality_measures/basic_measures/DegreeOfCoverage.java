package app.summarization.quality_measures.basic_measures;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.summarization.quality_measures.QualityMeasure;
import app.summarization.summary.Summary;
import app.summarization.summary.TypeTwoSummary;

/*
 * T3
 */
public class DegreeOfCoverage implements QualityMeasure {
    public static double getValue(Summary summary) {
        return getT(summary) / getH(summary);
    }

    private static double getT(Summary summary) {
        if (summary instanceof TypeTwoSummary) {
            return getTTypeTwo(summary);
        } else {
            return getTTypeOne(summary);
        }
    }

    private static double getTTypeOne(Summary summary) {
        return summary.getSummarizer().getSupportForElements().getSize();
    }

    private static double getTTypeTwo(Summary summary) {
        FuzzySet summarizer = summary.getSummarizer();
        FuzzySet qualifier = ((TypeTwoSummary) summary).getQualifier();
        return FuzzySetOperations.getIntersection(summarizer, qualifier).getSupportForElements().getSize();
    }

    private static double getH(Summary summary) {
        if (summary instanceof TypeTwoSummary) {
            return getHTypeTwo(summary);
        } else {
            return getHTypeOne(summary);
        }
    }

    private static double getHTypeOne(Summary summary) {
        return summary.getSubjectAmount();
    }

    private static double getHTypeTwo(Summary summary) {
        return ((TypeTwoSummary) summary).getQualifier().getSupportForElements().getSize();
    }
}
