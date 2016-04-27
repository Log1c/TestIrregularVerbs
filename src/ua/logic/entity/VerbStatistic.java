package ua.logic.entity;

import java.io.FileWriter;
import java.io.IOException;
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

    public void saveToCsv(Verb verbStandard) {
        try(FileWriter writer = new FileWriter("irregularVerbsStatistic.csv", true)) {
            String text = verbStandard.getFirstForm()
                    + "," + verbStandard.getSecondForm()
                    + "," + verbStandard.getThirdForm()
                    + "," + getVerbAnswer().getFirstForm()
                    + "," + getVerbAnswer().getSecondForm()
                    + "," + getVerbAnswer().getThirdForm()
                    +"\n";
            writer.write(text);

            //writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
