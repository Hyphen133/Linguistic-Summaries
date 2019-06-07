package app.summarization.summary;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.summarization.LinguisticVariable;

import java.util.List;

public interface Summary {
    String getSummary();

    QuantifierType getQuantifierType();

    int getSubjectAmount();

    Quantifier getQuantifier();

    List<FuzzySet> getSummarizerSets();
}
