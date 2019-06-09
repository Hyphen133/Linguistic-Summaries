package app.loading;

import app.summarization.LinguisticVariable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummarizationDataLoader {



    public SummarizationDataLoader() {
        load();
    }

    private void load() {

    }

    public List<LinguisticVariable> linguisticVariableList;
}
