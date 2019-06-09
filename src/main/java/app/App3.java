package app;

import app.data.TennisMatch;
import app.data.TennisMatchLabels;
import app.data.TennisMatchLinguisticVariables;
import app.loading.TennisCsvLoader;
import app.repositories.TennisMatchRepository;
import app.loading.TennisMatchDatabaseLoader;
import app.summarization.LinguisticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

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
        int maxRecordsCount = 10500;
        List<TennisMatch> tennisMatches = TennisCsvLoader.load(maxRecordsCount);

        Map<String, LinguisticVariable> variables = TennisMatchLinguisticVariables.getVariables(tennisMatches);

        int i;
        i=0;

    }
}