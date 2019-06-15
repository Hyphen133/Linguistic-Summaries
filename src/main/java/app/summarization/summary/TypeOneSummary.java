package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.fuzzy_sets.OperationType;
import app.summarization.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

/* Q - quantifier (around 1/4)
 * P - subject of summary (of matches -> entities)
 * S - summarizers
 */
public class TypeOneSummary implements Summary {
    private String subject;
    private List<LinguisticVariable> summarizers;
    private List<String> summarizerLabels;
    private Quantifier quantifier;
    private OperationType summarizerOperation;

    private List<FuzzySet> summarizerSets;


    public TypeOneSummary(String subject, List<LinguisticVariable> summarizers, List<String> summarizerLabels, Quantifier quantifier,
                          OperationType summarizerOperation) {
        this.subject = subject;
        this.summarizers = summarizers;
        this.summarizerLabels = summarizerLabels;
        this.quantifier = quantifier;
        this.summarizerOperation = summarizerOperation;
    }

    @Override
    public List<FuzzySet> getSummarizerSets() {
        if (summarizerSets == null) {
            summarizerSets = new ArrayList<>();
            for (int i = 0; i < summarizers.size(); i++) {
                summarizerSets.add(summarizers.get(i).getFuzzySetForLabel(
                        summarizers.get(i).getUniverseOfDiscourse(), summarizerLabels.get(i)));
            }
        }
        return summarizerSets;
    }

    @Override
    public FuzzySet getSummarizer() {
        List<FuzzySet> summarizers = getSummarizerSets();
        FuzzySet summarizer = summarizers.get(0);
        for (int i = 1; i < summarizers.size(); i++) {
            summarizer = FuzzySetOperations.getOperation(
                    summarizer, summarizers.get(i), summarizerOperation);
        }
        return summarizer;
    }

    @Override
    public int getSummarizerCount() {
        return summarizers.size();
    }

    @Override
    public QuantifierType getQuantifierType() {
        return quantifier.getQuantifierType();
    }

    @Override
    public int getSubjectAmount() {
        return summarizers.get(0).getUniverseOfDiscourse().getSize();
    }

    @Override
    public Quantifier getQuantifier() {
        return quantifier;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String getSummary() {
        String quantifierString =quantifier.getName();// Character.toUpperCase(quantifier.getName().charAt(0)) + quantifier.getName().substring(1);
        StringBuilder summarizerStringBuilder = new StringBuilder();

        for (int i = 0; i < summarizers.size() - 1; i++) {
            summarizerStringBuilder.append(summarizers.get(i).print(
                    summarizerLabels.get(i)) + " " + summarizerOperation.getOperationName() + " ");
        }
        int lastIndex = summarizers.size() - 1;
        summarizerStringBuilder.append(summarizers.get(lastIndex).print(summarizerLabels.get(lastIndex)));


        return quantifierString + " " + subject + " have " + summarizerStringBuilder.toString();
    }
}
