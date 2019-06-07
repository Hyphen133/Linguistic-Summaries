package app.summarization;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;

import java.util.List;

public class TypeOneSummarization implements Summarization {
    // Q - kwantyfikator (np. ok polowa) P - podmiot (krotki w bazie -> mecz)  List<FuzzySet>S (duzą roznice w gemach i ...)  T (50%)

    private String subject;
    private List<LinguisticVariable> summarizers;
    private List<String> summarizerTags;
    private Quantifier quantifier;
    private List<ClassicSet> attributeSets;
    private FuzzySetOperations.Operation summarizerOperation;


    public TypeOneSummarization(String subject, List<LinguisticVariable> summarizers, List<String> summarizerTags,  Quantifier quantifier, List<ClassicSet> attributeSets) {
        this.subject = subject;
        this.summarizers = summarizers;
        this.summarizerTags = summarizerTags;
        this.quantifier = quantifier;
        this.attributeSets = attributeSets;
    }

    public double measureDegreeOfTruth(){
        FuzzySet operationResult = summarizers.get(0).getFuzzySetForTag(attributeSets.get(0),summarizerTags.get(0));
        for (int i = 1; i < summarizers.size(); i++) {
            operationResult = FuzzySetOperations.getOperation(operationResult, summarizers.get(i).getFuzzySetForTag(attributeSets.get(i),summarizerTags.get(i)) , summarizerOperation);
        }


        return quantifier.getValue((operationResult.getCardinality()));
    }

    public String getSummary(){
        String qunatifierString = Character.toUpperCase(quantifier.getName().charAt(0))+quantifier.getName().substring(1);
        //TODO prittify, include operation , last without comma
        StringBuilder summarizerStringBuilder = new StringBuilder();

        String operationString = "";
        if(summarizerOperation.equals(FuzzySetOperations.Operation.INTERSECTION)){
            operationString = "and";
        }
        else if(summarizerOperation.equals(FuzzySetOperations.Operation.UNION)){
            operationString = "or";
        }


        for (int i = 0; i < summarizers.size() -1 ; i++) {
            summarizerStringBuilder.append(summarizers.get(i).print(summarizerTags.get(i)) + " " + summarizerOperation + " ");
        }
        int lastIndex = summarizers.size()-1;
        summarizerStringBuilder.append(summarizers.get(lastIndex).print(summarizerTags.get(lastIndex)));



        return qunatifierString + " " + subject + " have " + summarizerStringBuilder.toString();
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
