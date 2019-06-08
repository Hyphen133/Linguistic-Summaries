package app.summarization.summary;

import app.fuzzy_sets.ClassicSet;
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


    public TypeOneSummary(String subject, List<LinguisticVariable> summarizers, List<String> summarizerLabels, Quantifier quantifier) {
        this.subject = subject;
        this.summarizers = summarizers;
        this.summarizerLabels = summarizerLabels;
        this.quantifier = quantifier;
    }

//    public double getMembershipDegree() {
//        FuzzySet operationResult = summarizers.get(0).getFuzzySetForLabel(attributeSets.get(0), summarizerLabels.get(0));
//        for (int i = 1; i < summarizers.size(); i++) {
//            operationResult = FuzzySetOperations.getOperation(operationResult,
//                    summarizers.get(i).getFuzzySetForLabel(attributeSets.get(i), summarizerLabels.get(i)), summarizerOperation);
//        }
//        return operationResult.getCardinality();
//    }

    @Override
    public List<FuzzySet> getSummarizerSets() {
        List<FuzzySet> summarizersSet = new ArrayList<>();
        for (int i = 0; i < summarizers.size(); i++) {
            summarizersSet.add(summarizers.get(i).getFuzzySetForLabel(summarizers.get(i).getUniverseOfDisclouse(), summarizerLabels.get(i)));
        }
        return summarizersSet;
    }

    @Override
    public int getSummarizerCount() {
        return summarizerLabels.size();
    }

    @Override
    public QuantifierType getQuantifierType() {
        return quantifier.getQuantifierType();
    }

    public OperationType getSummarizerOperation() {
        return summarizerOperation;
    }

    @Override
    public int getSubjectAmount() {
        //TODO
        return 0;
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
        String qunatifierString = Character.toUpperCase(quantifier.getName().charAt(0)) + quantifier.getName().substring(1);
        StringBuilder summarizerStringBuilder = new StringBuilder();

        for (int i = 0; i < summarizers.size() - 1; i++) {
            summarizerStringBuilder.append(summarizers.get(i).print(summarizerLabels.get(i)) + " " + summarizerOperation + " ");
        }
        int lastIndex = summarizers.size() - 1;
        summarizerStringBuilder.append(summarizers.get(lastIndex).print(summarizerLabels.get(lastIndex)));


        return qunatifierString + " " + subject + " have " + summarizerStringBuilder.toString();
    }
}
