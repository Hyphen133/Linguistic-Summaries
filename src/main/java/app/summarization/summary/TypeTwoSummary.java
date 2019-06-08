package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;
import app.fuzzy_sets.OperationType;
import app.summarization.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

/* Q - quantifier (around 1/4)
 * P - subject of summary (of matches -> entities)
 * W - qualifiers of summary
 * S - summarizers
 */
public class TypeTwoSummary extends TypeOneSummary {
    private List<LinguisticVariable> qualifiers;
    private List<String> qualifierLabels;
    private OperationType qualifierOperation;

    private List<FuzzySet> qualifierSets;

    public TypeTwoSummary(String subject, List<LinguisticVariable> summarizers, List<String> summarizerLabels, List<LinguisticVariable> qualifier, List<String> qualifierLabels, Quantifier quantifier, OperationType quantifierOperation) {
        super(subject, summarizers, summarizerLabels, quantifier);
        this.qualifiers = qualifier;
        this.qualifierLabels = qualifierLabels;
        this.qualifierOperation = quantifierOperation;
    }

    private List<FuzzySet> getQualifierSets() {
        if (qualifierSets == null) {
            qualifierSets = new ArrayList<>();
            for (int i = 0; i < qualifiers.size(); i++) {
                qualifierSets.add(qualifiers.get(i).getFuzzySetForLabel(
                        qualifiers.get(i).getUniverseOfDiscourse(), qualifierLabels.get(i)));
            }
        }
        return qualifierSets;
    }

    public FuzzySet getQualifier() {
        List<FuzzySet> qualifiers = getQualifierSets();
        FuzzySet qualifier = qualifiers.get(0);
        for (int i = 1; i < qualifiers.size(); i++) {
            qualifier = FuzzySetOperations.getOperation(
                    qualifier, qualifiers.get(i), qualifierOperation);
        }
        return qualifier;
    }

    public OperationType getQualifierOperation() {
        return qualifierOperation;
    }

    @Override
    public String getSummary() {
        String typeOneSummary = super.getSummary();
        StringBuilder typeTwoBuilder = new StringBuilder(typeOneSummary);

        StringBuilder whichClause = new StringBuilder();
        whichClause.append(" which have");
        whichClause.append(qualifiers.get(0).print(qualifierLabels.get(0)));

        for (int i = 1; i < qualifiers.size(); i++) {
            whichClause.append(" " + qualifierOperation + " " + qualifiers.get(i).print(qualifierLabels.get(i)));
        }
        whichClause.append(" ");


        typeTwoBuilder.insert(typeTwoBuilder.indexOf(this.getSubject()) + this.getSubject().length(), whichClause.toString());

        return typeTwoBuilder.toString();
    }
}
