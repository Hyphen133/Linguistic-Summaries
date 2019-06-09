package app.summarization.summary;

import app.summarization.quality_measures.QualityMeasureEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            builder.append(entry.getKey().toString() + " " + BigDecimal.valueOf(entry.getValue())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue());
            builder.append(" ");
        }
        return builder.toString();
    }

    private double getWeightedAverage() {
        double weight = 1 / (double) measures.size();
        return BigDecimal.valueOf(measures.values()
                .stream()
                .mapToDouble(m -> m * weight)
                .sum()).setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
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

    public double getAll() {
        this.addQualityMeasure(QualityMeasureEnum.T1)
                .addQualityMeasure(QualityMeasureEnum.T2)
                .addQualityMeasure(QualityMeasureEnum.T3)
                .addQualityMeasure(QualityMeasureEnum.T4)
                .addQualityMeasure(QualityMeasureEnum.T5)
                .addQualityMeasure(QualityMeasureEnum.T6)
                .addQualityMeasure(QualityMeasureEnum.T7)
                .addQualityMeasure(QualityMeasureEnum.T8)
                .addQualityMeasure(QualityMeasureEnum.T11);
        if (summary instanceof TypeTwoSummary) {
            this.addQualityMeasure(QualityMeasureEnum.T9)
                    .addQualityMeasure(QualityMeasureEnum.T10);
        }
        return count();
    }

}
