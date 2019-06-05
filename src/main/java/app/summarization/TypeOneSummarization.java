package app.summarization;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;

public class TypeOneSummarization implements Summarization {
    // Q - kwantyfikator (np. ok polowa) P - podmiot (krotki w bazie -> mecz)  List<FuzzySet>S (duzą roznice w gemach i ...)  T (50%)

    String subject;
    LinguisticVariable summarizer;
    String summarizerTag;
    LinguisticQuantifier quantifier;
    ClassicSet attributeSet;


    public TypeOneSummarization(String subject, LinguisticVariable summarizer, String summarizerTag, LinguisticQuantifier quantifier, ClassicSet attributeSet) {
        this.subject = subject;
        this.summarizer = summarizer;
        this.summarizerTag = summarizerTag;
        this.quantifier = quantifier;
        this.attributeSet = attributeSet;
    }

    public double measureDegreeOfTruth(){
        return quantifier.getValue((summarizer.getFuzzySet(attributeSet,summarizerTag).getCardinality()));
    }

    public String getSummary(){
        return Character.toUpperCase(quantifier.getName().charAt(0))+quantifier.getName().substring(1) + " " + subject + " have " + summarizer.print(summarizerTag);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
