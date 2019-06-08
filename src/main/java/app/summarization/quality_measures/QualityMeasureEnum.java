package app.summarization.quality_measures;

import app.summarization.quality_measures.basic_measures.*;
import app.summarization.quality_measures.new_measures.*;
import app.summarization.summary.Summary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QualityMeasureEnum {
    T1("T1: Degree of truth"),
    T2("T2: Degree of imprecision"),
    T3("T3: Degree of coverage"),
    T4("T4: Degree of appropriateness"),
    T5("T5: Length of summary"),
    T6("T6: Degree of quantifier imprecision"),
    T7("T7: Degree of quantifier cardinality"),
    T8("T8: Degree of summarizer cardinality"),
    T9("T9: Degree of qualifier imprecision"),
    T10("T10: Degree of qualifier cardinality"),
    T11("");

    String name;

    public static double getValue(QualityMeasureEnum qualityMeasure, Summary summary){
        switch (qualityMeasure) {
            case T1:
                return DegreeOfTruth.getValue(summary);
            case T2:
                return DegreeOfImprecision.getValue(summary);
            case T3:
                return DegreeOfCoverage.getValue(summary);
            case T4:
                return DegreeOfAppropriateness.getValue(summary);
            case T5:
                return LengthOfSummary.getValue(summary);
            case T6:
                return DegreeOfQuantifierImprecision.getValue(summary);
            case T7:
                return DegreeOfQuantifierCardinality.getValue(summary);
            case T8:
                return DegreeOfSummarizerCardinality.getValue(summary);
            case T9:
                return DegreeOfQualifierImprecision.getValue(summary);
            case T10:
                return DegreeOfQualifierCardinality.getValue(summary);
            case T11:
                //TODO
                return 0;
            default:
                throw new IllegalStateException("Unexpected value: " + qualityMeasure);
        }
    }
}
