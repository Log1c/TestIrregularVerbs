package ua.logic.entity;

import ua.logic.util.Persistence;

import java.time.LocalDateTime;

public class VerbStatistic {
    private Verb verbStandard;
    private Verb verbAnswer;
    private LocalDateTime dateTime;
//    private String user;

    public VerbStatistic(Verb verbStandard, Verb verbAnswer, LocalDateTime dateTime) {
        this.verbStandard = verbStandard;
        this.verbAnswer = verbAnswer;
        this.dateTime = dateTime;
    }

    public VerbStatistic(Verb verbStandard, Verb verbAnswer) {
        this(verbStandard, verbAnswer, LocalDateTime.now());
    }

    public Verb getVerbStandard() {
        return verbStandard;
    }

    public Verb getVerbAnswer() {
        return verbAnswer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public void saveToCsv() {
        Persistence.saveToCsv(this);
    }
}
