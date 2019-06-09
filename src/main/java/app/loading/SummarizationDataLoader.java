package app.loading;

import app.summarization.LinguisticVariable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummarizationDataLoader {

    private List<LinguisticVariable> linguisticVariableList;


    public SummarizationDataLoader() {
        load();
    }

    private void load() {

    }

}
