package app.summarization.summary;

import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.OperationType;
import app.summarization.LinguisticVariable;

import java.util.ArrayList;
import java.util.List;

/* Q - quantifier (around 1/4)
 * P - subject of summary (of matches -> entities)
 * W - qualifier of summary
 * S - summarizers
 */
public class TypeTwoSummary extends TypeOneSummary{
    private List<LinguisticVariable> qualifier;
    private List<String> qualifierLabels;

    private OperationType quantifierOperation;
    // Q - kwantyfikator (np. ok polowa) P - podmiot (krotki w bazie -> mecz) List <FuzzySet>W (ktore mialy malo asów i ...)  List<FuzzySet>S(duzą roznice w gemach ...)  T (50%)

    public TypeTwoSummary(String subject, List<LinguisticVariable> summarizers, List<String> summarizerLabels, List<LinguisticVariable> qualifier, List<String> qualifierLabels, Quantifier quantifier, OperationType quantifierOperation) {
        super(subject, summarizers, summarizerLabels, quantifier);
        this.qualifier = qualifier;
        this.qualifierLabels = qualifierLabels;
        this.quantifierOperation = quantifierOperation;
    }

    public List<FuzzySet> getQualifierSets() {
        List<FuzzySet> summarizersSet = new ArrayList<>();
        for (int i = 0; i < qualifier.size(); i++) {
            summarizersSet.add(qualifier.get(i).getFuzzySetForLabel(qualifier.get(i).getUniverseOfDisclouse(), qualifierLabels.get(i)));
        }
        return summarizersSet;
    }

    public OperationType getQuantifierOperation() {
        return quantifierOperation;
    }

    @Override
    public String getSummary() {
        String typeOneSummary = super.getSummary();
        StringBuilder typeTwoBuilder = new StringBuilder(typeOneSummary);

        StringBuilder whichClause = new StringBuilder();
        whichClause.append(" which have");
        whichClause.append(qualifier.get(0).print(qualifierLabels.get(0)));

        for (int i = 1  ; i <  qualifier.size(); i++) {
            whichClause.append(" " + quantifierOperation + " " + qualifier.get(i).print(qualifierLabels.get(i)));
        }
        whichClause.append(" ");


        typeTwoBuilder.insert(typeTwoBuilder.indexOf(this.getSubject()) + this.getSubject().length(), whichClause.toString());

        return typeTwoBuilder.toString();
    }
}
