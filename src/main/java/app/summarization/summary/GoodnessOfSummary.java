package app.summarization.summary;

import app.summarization.quality_measures.QualityMeasureEnum;

import java.util.HashMap;
import java.util.Map;

public class GoodnessOfSummary {

    public static double getGoodnessOfSummary(Summary summary) {
        GoodnessOfSummaryCounter counter = new GoodnessOfSummaryCounter(summary)
                .addQualityMeasure(QualityMeasureEnum.T1)
                .addQualityMeasure(QualityMeasureEnum.T2)
                .addQualityMeasure(QualityMeasureEnum.T3)
                .addQualityMeasure(QualityMeasureEnum.T4)
                .addQualityMeasure(QualityMeasureEnum.T5);
        return counter.count();
    }

    public static double getExtendedGoodnessOfSummary(Summary summary) {
        GoodnessOfSummaryCounter counter = new GoodnessOfSummaryCounter(summary)
                .addQualityMeasure(QualityMeasureEnum.T1)
                .addQualityMeasure(QualityMeasureEnum.T2)
                .addQualityMeasure(QualityMeasureEnum.T3)
                .addQualityMeasure(QualityMeasureEnum.T4)
                .addQualityMeasure(QualityMeasureEnum.T5)
                .addQualityMeasure(QualityMeasureEnum.T6)
                .addQualityMeasure(QualityMeasureEnum.T7)
                .addQualityMeasure(QualityMeasureEnum.T8)
                .addQualityMeasure(QualityMeasureEnum.T9)
                .addQualityMeasure(QualityMeasureEnum.T10);
        return counter.count();
    }

    static class GoodnessOfSummaryCounter {
        Map<QualityMeasureEnum, Double> measures;
        Summary summary;

        public GoodnessOfSummaryCounter(Summary summary) {
            this.summary = summary;
            this.measures = new HashMap<>();
        }

        GoodnessOfSummaryCounter addQualityMeasure(QualityMeasureEnum name) {
            if (!measures.containsKey(name))
                measures.put(name, 0.0);
            return this;
        }

        double count() {
            for (Map.Entry<QualityMeasureEnum, Double> entry : measures.entrySet()) {
                entry.setValue(QualityMeasureEnum.getValue(entry.getKey(), summary));
            }
            return getWeightedAverage();
        }

        private double getWeightedAverage() {
            int weight = 1 / measures.size();
            return measures.values()
                    .stream()
                    .mapToDouble(m -> m * weight)
                    .sum();
        }
    }
}
