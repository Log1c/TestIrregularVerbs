package ua.logic.model;

import ua.logic.entity.Verb;
import ua.logic.entity.VerbStatistic;
import ua.logic.util.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<VerbStatistic> verbsAnswer = new ArrayList<>();

    public Answers() {
        Persistence.loadFromCsv(verbsAnswer, true);
    }

    public void add(Verb currentVerb, Verb verbAnswer) {
        VerbStatistic statistic = new VerbStatistic(currentVerb, verbAnswer);
        verbsAnswer.add(statistic);
        statistic.saveToCsv();
    }

    public List<VerbStatistic> getAnswers() {
        return verbsAnswer;
    }
}
