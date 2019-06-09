package app.summarization.summary;

import app.summarization.quality_measures.QualityMeasureEnum;

import java.util.HashMap;
import java.util.Map;

public class GoodnessOfSummary {

    Map<QualityMeasureEnum, Double> measures;
    Summary summary;

    public GoodnessOfSummary(Summary summary) {
        this.summary = summary;
        this.measures = new HashMap<>();
    }

    public GoodnessOfSummary addQualityMeasure(QualityMeasureEnum name) {
        if (!measures.containsKey(name))
            measures.put(name, 0.0);
        return this;
    }

    public double count() {
        for (Map.Entry<QualityMeasureEnum, Double> entry : measures.entrySet()) {
            entry.setValue(QualityMeasureEnum.getValue(entry.getKey(), summary));
        }
        return getWeightedAverage();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<QualityMeasureEnum, Double> entry : measures.entrySet()) {
            builder.append(entry.getKey() + " " + entry.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    private double getWeightedAverage() {
        double weight = 1 / (double) measures.size();
        return measures.values()
                .stream()
                .mapToDouble(m -> m * weight)
                .sum();
    }

    public double getGoodnessOfSummary() {
        this.addQualityMeasure(QualityMeasureEnum.T1)
                .addQualityMeasure(QualityMeasureEnum.T2)
                .addQualityMeasure(QualityMeasureEnum.T3)
                .addQualityMeasure(QualityMeasureEnum.T4)
                .addQualityMeasure(QualityMeasureEnum.T5);
        return count();
    }

    public double getExtendedGoodnessOfSummary() {
        this.addQualityMeasure(QualityMeasureEnum.T1)
                .addQualityMeasure(QualityMeasureEnum.T2)
                .addQualityMeasure(QualityMeasureEnum.T3)
                .addQualityMeasure(QualityMeasureEnum.T4)
                .addQualityMeasure(QualityMeasureEnum.T5)
                .addQualityMeasure(QualityMeasureEnum.T6)
                .addQualityMeasure(QualityMeasureEnum.T7)
                .addQualityMeasure(QualityMeasureEnum.T8)
                .addQualityMeasure(QualityMeasureEnum.T9)
                .addQualityMeasure(QualityMeasureEnum.T10);
        return count();
    }

}
