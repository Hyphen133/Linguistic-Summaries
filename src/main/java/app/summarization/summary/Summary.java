package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.OperationType;

import java.util.List;

public interface Summary {
    String getSummary();

    QuantifierType getQuantifierType();

    int getSubjectAmount();

    Quantifier getQuantifier();

    List<FuzzySet> getSummarizerSets();

    FuzzySet getSummarizer();

    int getSummarizerCount();
}
