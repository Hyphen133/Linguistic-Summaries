package app.generating;

import app.summarization.summary.Quantifier;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SummaryData {
    private String summarizerVariable;
    private String summarizerTag;
    private String qualifierVariable;
    private String qualifierTag;
    private Quantifier quantifier;
}
