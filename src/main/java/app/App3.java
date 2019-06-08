package app;

import app.data.TennisMatch;
import app.loading.TennisCsvLoader;
import app.repositories.TennisMatchRepository;
import app.loading.TennisMatchDatabaseLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

        List<Integer> acesList = tennisMatchDatabaseLoader.getColumn("TENNIS_MATCH", "ACES", Integer.class);
        List<Integer> acesSubList = acesList.subList(0, 100);
        System.out.println(StringUtils.join(acesSubList, ", "));;





        long end = System.currentTimeMillis();
        //finding the time difference and converting it into seconds
        float sec = (end - start) / 1000F;
        System.out.println(sec + " seconds");

        System.out.println("Total records: " + tennisMatchRepository.count());
    }
}