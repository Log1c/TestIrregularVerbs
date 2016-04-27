package ua.logic.entity;

import java.time.LocalDateTime;

public class VerbStatistic {
    private Verb verbStandard;
    private Verb verbAnswer;
    private LocalDateTime dateTime;
//    private String user;

    public VerbStatistic(Verb verbStandard, Verb verbAnswer) {
        this.verbStandard = verbStandard;
        this.verbAnswer = verbAnswer;
        dateTime = LocalDateTime.now();
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
}
