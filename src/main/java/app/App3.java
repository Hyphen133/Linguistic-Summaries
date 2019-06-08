package app;

import app.data.TennisMatch;
import app.fuzzy_sets.ClassicSet;
import app.fuzzy_sets.SetUtils;
import app.fuzzy_sets.characterictic_functions.CharacteristicFunction;
import app.fuzzy_sets.characterictic_functions.FallingFunction;
import app.fuzzy_sets.characterictic_functions.RisingFunction;
import app.fuzzy_sets.characterictic_functions.ClassicFunction;
import app.loading.TennisCsvLoader;
import app.repositories.TennisMatchRepository;
import app.loading.TennisMatchDatabaseLoader;
import app.summarization.LinguisticVariable;
import app.summarization.summary.Quantifier;
import app.summarization.summary.QuantifierType;
import app.summarization.summary.TypeOneSummary;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class App3 implements CommandLineRunner {

    @Autowired
    TennisMatchRepository tennisMatchRepository;

    @Autowired
    TennisMatchDatabaseLoader tennisMatchDatabaseLoader;

    public static void main(String[] args) {
        SpringApplication.run(App3.class, args);
    }

    @Override
    public void run(String... args) {
        int recordsCount = 1000;
        List<TennisMatch> tennisMatches = TennisCsvLoader.load();
        System.out.println("Expected size: " + tennisMatches.size());
        long start = System.currentTimeMillis();
        if (tennisMatchRepository.count() == 0) {
            tennisMatchRepository.save(tennisMatches.subList(0,recordsCount));
//            tennisMatchRepository.save(tennisMatches);
        }


//        tennisMatchService.getObjectColumn("TENNIS_MATCH", "ACES");

        List<Double> acesList = tennisMatchDatabaseLoader.getColumn("TENNIS_MATCH", "ACES", Integer.class).stream().map(x->(double)x).collect(Collectors.toList());
        List<Double> acesSubList = acesList.subList(0, 100);
        System.out.println(StringUtils.join(acesSubList, ", "));;

//        ClassicSet universe = new ClassicSet();
        Map<String, CharacteristicFunction> tagCharacteristicFunctionMap = new HashMap<>();
        tagCharacteristicFunctionMap.put("little", new FallingFunction(2,5));
        tagCharacteristicFunctionMap.put("many" , new RisingFunction(5,8));

        ClassicSet universe = new ClassicSet(acesSubList, new ClassicFunction() );
        LinguisticVariable acesVariable = new LinguisticVariable("aces", tagCharacteristicFunctionMap, universe);

        ClassicSet qUniverse = SetUtils.minMaxIntegerSet(0, 1000);
        Quantifier quantifier = new Quantifier(new RisingFunction(50, 100),qUniverse,"many", QuantifierType.ABSOLUTE);

        TypeOneSummary summary = new TypeOneSummary("tennis matches", Arrays.asList(acesVariable), Arrays.asList("little"),quantifier);
        System.out.println(summary.getSummary());




        long end = System.currentTimeMillis();
        //finding the time difference and converting it into seconds
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds");

        System.out.println("Total records: " + tennisMatchRepository.count());
    }
}