package app.controllers.generating;

import app.summarization.summary.Quantifier;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SummaryData {
    private List<String> summarizerVariables;
    private List<String> summarizerTags;
    private List<String> qualifierVariables;
    private List<String> qualifierTags;
    private Quantifier quantifier;
}
