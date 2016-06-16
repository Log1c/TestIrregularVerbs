package ua.logic.model;

import ua.logic.entity.Verb;
import ua.logic.entity.VerbStatistic;
import ua.logic.util.Persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Questions {
    private List<Verb> verbsStandard = new ArrayList<>();
    private Set<Verb> questions = new HashSet<>();

    public Questions() {
        Persistence.loadFromCsv(verbsStandard, false);
    }

    public List<Verb> getVerbsStandard() {
        return verbsStandard;
    }

    public Verb getQuestion() {
        int counter = (int) (Math.random() * verbsStandard.size());

        for (Verb verb : verbsStandard) {
            if (counter-- == 0) {
                return verb;
            }
        }
        throw new IllegalStateException();
    }

    public void removeCollectAnswerFromQuestion(List<VerbStatistic> verbsAnswer) {
        Set<Verb> correct = new HashSet<>();

        for (VerbStatistic statistic : verbsAnswer) {
            if (statistic.getVerbStandard().equals(statistic.getVerbAnswer())) {
                correct.add(statistic.getVerbStandard());
            }
        }

        questions.clear();
        questions.addAll(verbsStandard);
        questions.removeAll(correct);
    }
}
