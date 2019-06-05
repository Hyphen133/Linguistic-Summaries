package app.summarization;

import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.FuzzySet;
import app.fuzzy_sets.FuzzySetOperations;

public class TypeTwoSummarization implements Summarization{
    private String subject;
    private LinguisticVariable summarizer1;
    private LinguisticVariable summarizer2;
    private String summarizerTag1;
    private String summarizerTag2;
    private LinguisticQuantifier quantifier;
    private ClassicSet attributeSet1;
    private ClassicSet attributeSet2;
    // Q - kwantyfikator (np. ok polowa) P - podmiot (krotki w bazie -> mecz) List <FuzzySet>W (ktore mialy malo asów i ...)  List<FuzzySet>S(duzą roznice w gemach ...)  T (50%)


    public TypeTwoSummarization(String subject, LinguisticVariable summarizer1, LinguisticVariable summarizer2, String summarizerTag1, String summarizerTag2, LinguisticQuantifier quantifier, ClassicSet attributeSet1, ClassicSet attributeSet2) {
        this.subject = subject;
        this.summarizer1 = summarizer1;
        this.summarizer2 = summarizer2;
        this.summarizerTag1 = summarizerTag1;
        this.summarizerTag2 = summarizerTag2;
        this.quantifier = quantifier;
        this.attributeSet1 = attributeSet1;
        this.attributeSet2 = attributeSet2;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public double measureDegreeOfTruth() {
        FuzzySet fuzzySetS = summarizer1.getFuzzySet(attributeSet1, summarizerTag1);
        FuzzySet fuzzySetW = summarizer2.getFuzzySet(attributeSet2, summarizerTag2);
        return quantifier.getValue(FuzzySetOperations.getIntersection(fuzzySetS,fuzzySetW).getCardinality() /fuzzySetW.getCardinality());
    }

    @Override
    public String getSummary() {
        return Character.toUpperCase(quantifier.getName().charAt(0))+quantifier.getName().substring(1) + " " + subject + " which have  " + summarizer1.print(summarizerTag1) + " have " + summarizer2.print(summarizerTag2);

    }
}
