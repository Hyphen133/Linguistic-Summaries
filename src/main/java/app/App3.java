//package app;
//
//import app.data.TennisMatch;
//import app.data.TennisMatchLabels;
//import app.data.TennisMatchLinguisticVariables;
//import app.fuzzy_sets.OperationType;
//import app.loading.TennisCsvLoader;
//import app.repositories.TennisMatchRepository;
//import app.loading.TennisMatchDatabaseLoader;
//import app.summarization.LinguisticVariable;
//import app.summarization.quality_measures.QualityMeasureEnum;
//import app.summarization.summary.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.*;
//
//@SpringBootApplication
//public class App3 implements CommandLineRunner {
//
//    @Autowired
//    TennisMatchRepository tennisMatchRepository;
//
//    @Autowired
//    TennisMatchDatabaseLoader tennisMatchDatabaseLoader;
//
//    public static void main(String[] args) {
//        SpringApplication.run(App3.class, args);
//    }
//
//    @Override
//    public void run(String... args) {
//        int maxRecordsCount = 10002;
//        List<TennisMatch> tennisMatches = TennisCsvLoader.load(maxRecordsCount);
//
//        Map<String, LinguisticVariable> variables = TennisMatchLinguisticVariables.getVariables(tennisMatches);
//
//        List<LinguisticVariable> linguisticVariables = new ArrayList<>(variables.values());
//        List<LinguisticVariable> summarizers = new ArrayList<>();
//        summarizers.add(linguisticVariables.get(0));
//        summarizers.add(linguisticVariables.get(1));
//
//        List<String> summarizersLabels = new ArrayList<>();
//        summarizersLabels.add(linguisticVariables.get(0).getLabels().get(0));
//        summarizersLabels.add(linguisticVariables.get(1).getLabels().get(0));
//
//        List<LinguisticVariable> qualifiers = new ArrayList<>();
//        qualifiers.add(linguisticVariables.get(2));
//        qualifiers.add(linguisticVariables.get(3));
//
//        List<String> qualifiersLabels = new ArrayList<>();
//        qualifiersLabels.add(linguisticVariables.get(2).getLabels().get(2));
//        qualifiersLabels.add(linguisticVariables.get(4).getLabels().get(1));
//
//        Quantifier quantifier = new Quantifier(QuantifierLabel.ABOUT_QUARTER);
//
//        Summary summary = new TypeOneSummary("MATCHES-PLAYER", summarizers, summarizersLabels, quantifier, OperationType.INTERSECTION);
//        GoodnessOfSummary counter = new GoodnessOfSummary(summary);
//        counter.getExtendedGoodnessOfSummary();
//        System.out.println(counter.count());
//        System.out.println(summary.getSummary());
//        System.out.println(counter.toString());
//
////        Summary summary = new TypeTwoSummary("MATCHES", summarizers, summarizersLabels,
////                qualifiers, qualifiersLabels, quantifier, OperationType.UNION, OperationType.INTERSECTION);
////        GoodnessOfSummary counter = new GoodnessOfSummary(summary);
////        counter.getExtendedGoodnessOfSummary();
////        System.out.println(counter.count());
////        System.out.println(summary.getSummary());
////        System.out.println(counter.toString());
//    }
//}